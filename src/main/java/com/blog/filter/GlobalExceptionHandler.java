package com.blog.filter;

/*全局统一异常处理的handler*/

import com.blog.common.apiresult.ApiResponse;
import com.blog.common.apiresult.AppExceptionCodeMsg;
import com.blog.common.apiresult.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //系统异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        logger.error("Default Exception : ",e);
        return ApiResponse.error(AppExceptionCodeMsg.SYSTEM_ERROR);
    }

    //业务异常
    @ExceptionHandler(AppException.class)
    @ResponseBody
    public Object handleAllException(AppException e){
        logger.error("AppException : ",e);
        return ApiResponse.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResponse handeMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.error("MethodArgumentNotValidException : ",e);
        return handleBindingResult(e.getBindingResult());
    }
    private ApiResponse handleBindingResult(BindingResult result){
        //把异常处理为对外暴露的提示
        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        if (list.size() == 0) {
            return ApiResponse.error(AppExceptionCodeMsg.REQUEST_PARAM_ERROR);
        }
        return ApiResponse.error(AppExceptionCodeMsg.REQUEST_PARAM_ERROR.getCode(),list.toString());
    }
}
