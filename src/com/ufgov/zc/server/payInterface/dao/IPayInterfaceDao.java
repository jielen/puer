package com.ufgov.zc.server.payInterface.dao;

import java.util.List;

import com.ufgov.zc.common.pay.model.PayVoucher;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface IPayInterfaceDao {
  /**
   * 以查询到这些未生成凭证的用款申请
   */
  public List findUnPayedBill(ElementConditionDto dto);

  public PayVoucher findUnPayedBillByAmBillNo(String id);
}
