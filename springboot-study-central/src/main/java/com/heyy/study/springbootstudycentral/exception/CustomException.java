package com.heyy.study.springbootstudycentral.exception;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -8110487680027737284L;
    private ExceptionEnum exceptionEnum;
    private String errorMessage;

    public CustomException(ExceptionEnum exceptionEnum, String errorMessage){
        super(errorMessage);
        this.exceptionEnum = exceptionEnum;
        this.errorMessage = errorMessage;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
