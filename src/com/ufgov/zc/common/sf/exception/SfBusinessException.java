/**
 * 
 */
package com.ufgov.zc.common.sf.exception;

/**
 * @author Administrator
 *
 */
public class SfBusinessException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public SfBusinessException(String msg)  
  {  
      super(msg);  
  } 
  public SfBusinessException(String message, Throwable cause) {
    super(message, cause);
}
}
