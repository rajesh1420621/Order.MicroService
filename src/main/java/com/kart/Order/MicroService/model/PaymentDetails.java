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
public class PaymentDetails {

        private long paymentId;
        private String PaymentStatus;
        private PaymentMode paymentMode;
        private Instant paymentDate;


}
