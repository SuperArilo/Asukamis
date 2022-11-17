package com.superarilo.utils;

import org.springframework.http.HttpStatus;
import java.util.HashMap;

public class JsonResult extends HashMap<String, Object> {

    public JsonResult(Integer code, String message) {
        this.put("code", code);
        this.put("message", message);
    }

    public JsonResult(Integer code, String message, Object data) {
        this.put("code", code);
        this.put("message", message);
        this.put("data", data);
    }

    public JsonResult(String message) {
        this.put("message", message);
    }

    public static JsonResult OK(String message, Object data){
        return new JsonResult(HttpStatus.OK.value(), message, data);
    }
    public static JsonResult OK(String message){
        return new JsonResult(HttpStatus.OK.value(), message);
    }
    public static JsonResult Error(Integer code, String message){
        return new JsonResult(code, message);
    }
    public static JsonResult Error(Integer code, String message, Object data){
        return new JsonResult(code, message, data);
    }

    public static JsonResult OK(Integer code, String message, Object data) {
        return new JsonResult(code, message, data);
    }
}
