package com.kart.Order.MicroService.exception;

import com.kart.Order.MicroService.external.response.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestReponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
public ResponseEntity<ErrorMessage> handleCustomException(CustomException exception){
        return new ResponseEntity<>(ErrorMessage.builder().errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode()).build(),HttpStatus.valueOf(exception.getStatus()));
    }
}
