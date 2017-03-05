package com.vc.core.exception;

public class BusinessException extends RuntimeException
{
  private static final long serialVersionUID = 7584344243805543246L;

  public BusinessException()
  {
    super();
  }

  public BusinessException(String message)
  {
    super(message);
  }

  public BusinessException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public BusinessException(Throwable cause)
  {
    super(cause);
  }
}
