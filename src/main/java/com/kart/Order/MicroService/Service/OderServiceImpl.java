package com.kart.Order.MicroService.Service;

import com.kart.Order.MicroService.Repository.OrderRepo;
import com.kart.Order.MicroService.entity.OrderEntity;
import com.kart.Order.MicroService.exception.CustomException;
import com.kart.Order.MicroService.external.client.PaymentService;
import com.kart.Order.MicroService.external.client.ProductService;
import com.kart.Order.MicroService.external.request.PaymentRequest;
import com.kart.Order.MicroService.external.response.PaymentResponse;
import com.kart.Order.MicroService.model.OrderRequest;
import com.kart.Order.MicroService.model.OrderResponse;
import com.kart.Order.MicroService.model.PaymentDetails;
import com.kart.Order.MicroService.model.ProductResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductService productService;


    @Autowired
    private PaymentService paymentService;


    @Autowired
    private RestTemplate restTemplate;


    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("Placing the order");

        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        OrderEntity orderEntity = OrderEntity.builder().amount(orderRequest.getTotalAmount()).productId(orderRequest.getProductId()).orderDate(Instant.now()).quantity(orderRequest.getQuantity()).build();

        orderEntity = orderRepo.save(orderEntity);
        log.info("calling payment service for complete payment");

        PaymentRequest paymentRequest = PaymentRequest.builder().
                orderId(orderEntity.getId()).
                paymentMode(orderRequest.getPaymentMode()).
                amount(orderRequest.getTotalAmount()).
                build();

        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done sucessfully");
            orderStatus = "Placed";
        } catch (Exception e) {
            log.error("Error occurred in payment.Change ikn order status required" + e);
            orderStatus = "Payment_Failed";
        }

        orderEntity.setOrderStatus(orderStatus);
        orderRepo.save(orderEntity);

        log.info("Order is Saved Sucessfully", orderEntity.getId());
        return orderEntity.getId();


    }


    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get order details for order id" + orderId);


        OrderEntity orderEntity = orderRepo.findById(orderId).orElseThrow(() -> new CustomException("Order not found", "NOT_FOUND", 404));

        log.info("Getting product response from OrderResponse by invoking product response" + orderEntity.getProductId());
        //Invoking product service to get product response;
        ProductResponse productResponse = restTemplate.getForObject("http://Product-Service/product/" + orderEntity.getProductId(), ProductResponse.class);


        log.info("Getting the payment response by invoking th payment-service ");
        //Invoking the payment service to get payment response
        PaymentResponse paymentResponse = restTemplate.getForObject("http://Payment-Service/payment/order/"+ orderEntity.getId(),PaymentResponse.class);


        PaymentDetails paymentDetails = PaymentDetails.builder().
                paymentId(paymentResponse.getPaymentId()).
                paymentDate(paymentResponse.getPaymentDate()).
                PaymentStatus(paymentResponse.getStatus()).
                paymentMode(paymentResponse.getPaymentMode()).
                build();

        OrderResponse orderResponse = OrderResponse.builder().
                orderId(orderEntity.getId()).
                orderStatus(orderEntity.getOrderStatus()).
                orderDate(orderEntity.getOrderDate())
                .amount(orderEntity.getAmount()).productResponse(productResponse).
                paymentDetails(paymentDetails)
        .
                build();


        return orderResponse;
    }


}
