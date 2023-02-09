package com.kart.Order.MicroService.external.request;


import com.kart.Order.MicroService.model.PaymentMode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {

    private long orderId;
    private long amount;
    private String referenceNumber;
    private PaymentMode paymentMode;

}
