package com.bgnc.galleriportal.exception;

public class BaseException extends RuntimeException {

  BaseException(){

  }
  public BaseException(ErrorMessage message) {
    super(message.prepareMessage());

  }
}
