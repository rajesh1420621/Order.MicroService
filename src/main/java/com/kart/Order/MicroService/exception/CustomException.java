package com.kart.Order.MicroService.exception;


import lombok.Data;
import org.apache.logging.log4j.message.Message;

@Data
public class CustomException extends RuntimeException{

    private String ErrorCode;
    private int Status;

    public CustomException(String message, String errorCode, int status) {
        super(message);
        ErrorCode = errorCode;
        Status = status;
    }
}
