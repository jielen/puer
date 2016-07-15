package com.ufgov.zc.server.payInterface.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.pay.model.PayVoucher;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.payInterface.dao.IPayInterfaceDao;

public class PayInterfaceDaoImpl extends SqlMapClientDaoSupport implements IPayInterfaceDao {
  public List findUnPayedBill(ElementConditionDto dto) {
    return this.getSqlMapClientTemplate().queryForList("PAY_VOUCHER.findUnPayedBill", dto);
  }

  public PayVoucher findUnPayedBillByAmBillNo(String id) {
    return (PayVoucher) this.getSqlMapClientTemplate().queryForObject("PAY_VOUCHER.findUnPayedBillById", id);
  }

}
