package com.superarilo.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExceptionJson extends RuntimeException{

    private Integer code;
    private String message;

    public ExceptionJson(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
