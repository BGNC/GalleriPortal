package com.bgnc.galleriportal.exception;


import lombok.Getter;

@Getter
public enum MessageType {


    NO_RECORD_EXISTS("1001","The record is not found"),
    USERNAME_NOT_FOUND_EXCEPTION("1002","Username not found"),
    TOKEN_IS_EXPIRED("1003","The token is expired"),
    GENERAL_EXCEPTION("1002","The general error.");

    private String code;
    private String message;



    MessageType(String code,String message) {
        this.code=code;
        this.message=message;
    }




}
