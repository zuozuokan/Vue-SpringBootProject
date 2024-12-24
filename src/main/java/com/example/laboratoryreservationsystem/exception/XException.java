package com.example.laboratoryreservationsystem.exception;

import lombok.*;
import com.example.laboratoryreservationsystem.exception.Code;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class XException extends RuntimeException{
    public static int ErrorCode = 400;
    private Code code;
    private int number;
    private String message;

}
