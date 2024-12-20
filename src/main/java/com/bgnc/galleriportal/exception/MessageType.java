package com.bgnc.galleriportal.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXISTS("1001", "The record is not found"),
    USERNAME_NOT_FOUND_EXCEPTION("1002", "Username not found"),
    USERNAME_ALREADY_EXISTS("1003", "Username already exists"),
    REFRESH_TOKEN_IS_NOT_FOUND("1004", "Refresh token not found"),
    REFRESH_TOKEN_IS_ALREADY_EXPIRED("1005", "Refresh token is already expired"),
    USERNAME_OR_PASSWORD_IS_INVALID("1006", "Username or password is invalid"),
    TOKEN_IS_EXPIRED("1007", "The token is expired"),
    CURRENCY_RATE_IS_OCCURED("1008", "The currency rate is occurred"),
    CLIENT_ERROR("1009", "A client error occurred (4xx)"),
    SERVER_ERROR("1010", "A server error occurred (5xx)"),
    GENERAL_EXCEPTION("9999", "The general error.");

    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
