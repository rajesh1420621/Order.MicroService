package com.kart.Order.MicroService.config;


import com.kart.Order.MicroService.external.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// configuration to make spring understand to use the custom error decoder rather than error decoder
@Configuration
public class FeignConfig {


    //used to return the custom error decoder inspite of errorDecoder
    @Bean
    ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }
}
