package com.vc.core.exception;

public class ParameterException extends RuntimeException
{
  private static final long serialVersionUID = 7102847322824699289L;

  public ParameterException()
  {
    super();
  }

  public ParameterException(String message)
  {
    super(message);
  }

  public ParameterException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public ParameterException(Throwable cause)
  {
    super(cause);
  }

}
