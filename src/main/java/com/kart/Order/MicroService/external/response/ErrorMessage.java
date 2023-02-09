package com.kart.Order.MicroService.external.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {

    private String errorMessage;
    private String errorCode;
}
