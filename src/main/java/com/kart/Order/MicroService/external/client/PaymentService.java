package com.kart.Order.MicroService.external.client;


import com.kart.Order.MicroService.exception.CustomException;
import com.kart.Order.MicroService.external.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CircuitBreaker(name = "external",fallbackMethod = "fallback")
@FeignClient(name = "Payment-Service/payment")
public interface PaymentService {

    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);

        default void fallback(Exception e){
            throw new CustomException("Payment Service is not available","Unavailable",500);
        }

}
