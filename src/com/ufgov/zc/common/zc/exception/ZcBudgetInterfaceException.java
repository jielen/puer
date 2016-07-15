/**
 * 
 */
package com.ufgov.zc.common.zc.exception;

/**
 *调用指标接口异常
 * @author Administrator
 *
 */
public class ZcBudgetInterfaceException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = -1264134963540434128L;

  public ZcBudgetInterfaceException(String msg)  
  {  
      super(msg);  
  } 
  public ZcBudgetInterfaceException(String message, Throwable cause) {
    super(message, cause);
}
}
