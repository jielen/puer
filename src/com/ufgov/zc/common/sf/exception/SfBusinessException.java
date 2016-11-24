/**
 * 
 */
package com.ufgov.zc.common.sf.exception;

import com.ufgov.zc.common.system.exception.BaseException;

/**
 * @author Administrator
 *
 */
public class SfBusinessException extends BaseException {

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
