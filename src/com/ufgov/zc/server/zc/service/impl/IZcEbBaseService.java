package com.ufgov.zc.server.zc.service.impl;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public interface IZcEbBaseService {

  /**
   * 工作流相关的操作
   */

  public abstract ZcBaseBill audit(ZcBaseBill bill);

  public abstract ZcBaseBill callbackFN(ZcBaseBill bill);

}