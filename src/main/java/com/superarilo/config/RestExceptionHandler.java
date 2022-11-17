package com.superarilo.config;

import com.superarilo.utils.ExceptionJson;
import com.superarilo.utils.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = ExceptionJson.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult parameterIsNull(ExceptionJson json){
        return JsonResult.Error(json.getCode(), json.getMessage());
    }
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult missingParameter(){
        return JsonResult.Error(400, "请求缺少必要参数");
    }
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public JsonResult methodError(){
        return JsonResult.Error(405, "请求方法错误");
    }
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public JsonResult notFound(){
        return JsonResult.Error(404, "路径不存在");
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult authentication(Exception e){
        e.printStackTrace();
        return JsonResult.Error(500, "服务器发生逻辑错误！");
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult parameterTypeError(){
        return JsonResult.Error(400, "参数类型错误！");
    }
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult notReadable(){
        return JsonResult.Error(400, "服务器没有接收到任何的对象参数");
    }
    @ExceptionHandler(value = MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult noMultipartFile(){
        return JsonResult.Error(400, "服务器没有接收到文件");
    }
}
