package com.heyy.study.springbootstudycentral.exception;

import com.heyy.study.springbootstudycentral.constants.CommonConstants;
import heyy.study.common.been.ErrorDetails;
import heyy.study.common.utility.SysDateManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * @Classname CustomizedErrorHandler
 * @Description TODO
 * @Date 2019/5/9 13:45
 * @Created by Breeze
 */
@ControllerAdvice
public class CustomizedErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors =  result.getFieldErrors();
        String errorDetails = fieldErrors.get(0).getDefaultMessage();
        String responseCode = CommonConstants.NEGATIVE_RESPONSE_CODE;
        String errorCode = ExceptionEnum.SSC_0001.getErrorCode();
        MultiValueMap<String, String> headers = getExceptionResponseHeaders(responseCode,errorCode);
        ErrorDetails responseBody = new ErrorDetails(SysDateManager.getCurrentTime(),errorDetails,"");
        return  new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
    }

    private MultiValueMap<String, String> getExceptionResponseHeaders(String responseCode,String  errorCode){
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String,String>();
        headers.set("responseCode",responseCode);
        headers.set("errorCode",errorCode);
        return headers;
    }

}
