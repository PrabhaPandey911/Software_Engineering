package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

/* Used to combine errorCode and errorParameter in an object */

public class ErrorCodeAndParameter {
  private String errorParameter = null;
  private ArgsException.ErrorCode errorCode = OK;


  public ErrorCodeAndParameter(ArgsException.ErrorCode errorCode, String errorParameter) {
    this.errorCode = errorCode;
    this.errorParameter = errorParameter;
  }

  public ArgsException.ErrorCode getErrorCode() {
  	return errorCode;
  }

  public String getErrorParameter() {
  	return errorParameter;
  }
}