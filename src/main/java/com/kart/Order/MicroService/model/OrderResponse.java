package com.kart.Order.MicroService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private long amount;
    private ProductResponse productResponse;
    private PaymentDetails paymentDetails;


//    private  ProductResponse productResponse;
//
//    @Builder
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class ProductResponse {
//
//        private String productName;
//        private long productId;
//        private long quantity;
//        private long price;
//
//    }


//    ProductResponse productResponse = new ProductResponse();
    }
