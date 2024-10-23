package com.bgnc.galleriportal.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class RootEntity<T> {
    private T data;
    private String errorMessage;
    private Integer status;


    public static <T>  RootEntity<T> ok (T data) {
        RootEntity<T> rootEntity = new RootEntity<T>();
        rootEntity.setData(data);
        rootEntity.setErrorMessage(null);
        rootEntity.setStatus(HttpStatus.OK.value());
        return rootEntity;
    }

    public static <T>  RootEntity<T> error (String errorMessage) {
        RootEntity<T> rootEntity = new RootEntity<T>();
        rootEntity.setData(null);
        rootEntity.setErrorMessage(errorMessage);
        rootEntity.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return rootEntity;
    }
}
