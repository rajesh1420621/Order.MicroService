package com.kart.Order.MicroService.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kart.Order.MicroService.exception.CustomException;
import com.kart.Order.MicroService.external.response.ErrorMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

// custom error decoder
@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        log.info("::{}",response.request().url());
        log.info("::{}",response.request().headers());


        try {
            ErrorMessage errorMessage = objectMapper.readValue(response.body().asInputStream(),ErrorMessage.class);
            return new CustomException(errorMessage.getErrorMessage(),errorMessage.getErrorCode(),response.status());
        } catch (IOException e) {
            throw new CustomException("Internal Server Error","INTERNAL-SERVER-ERROR",500);
        }
    }
}
