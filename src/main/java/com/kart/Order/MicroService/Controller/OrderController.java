package com.kart.Order.MicroService.Controller;


import com.kart.Order.MicroService.Service.OrderService;
import com.kart.Order.MicroService.model.OrderRequest;
import com.kart.Order.MicroService.model.OrderResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) throws InterruptedException {
        Thread.sleep(5000);
       long orderId= orderService.placeOrder(orderRequest);
        log.info("Order is placed Suseccfully",orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId){
        OrderResponse orderResponse =  orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }
}
