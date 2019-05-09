package com.heyy.study.springbootstudycentral.exception;

/**
 * @Classname ExceptionEnum
 * @Description TODO
 * @Date 2019/5/9 13:52
 * @Created by Breeze
 */
public enum ExceptionEnum {
    SSC_0001("SSC_0001","request field checked failed!"),
    SSC_0002("SSC_0002","unknow exception!"),
    ;
    private String errorCode;
    private String errorDetails;

    ExceptionEnum(String errorCode,String errorDetails){
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
