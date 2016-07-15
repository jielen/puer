/**
 * 
 */
package com.ufgov.zc.common.zc.exception;

/**
 * 调用支付接口异常
 * @author Administrator
 *
 */
public class ZcPayInterfaceException extends Exception {


  public ZcPayInterfaceException(String msg)  
  {  
      super(msg);  
  } 
  public ZcPayInterfaceException(String message, Throwable cause) {
    super(message, cause);
}
}
