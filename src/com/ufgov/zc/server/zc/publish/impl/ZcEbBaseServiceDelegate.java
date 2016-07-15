package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class ZcEbBaseServiceDelegate implements IZcEbBaseServiceDelegate {

  private IZcEbBaseService zcEbBaseService;

  /**

   * @return the zcEbBaseService

   */

  public IZcEbBaseService getZcEbBaseService() {

    return zcEbBaseService;

  }

  /**

   * @param zcEbBaseService the zcEbBaseService to set

   */

  public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {

    this.zcEbBaseService = zcEbBaseService;

  }

  public List getForeignEntitySelectedData(String sqlMapSelectId, ElementConditionDto dto, RequestMeta meta) {

    return this.zcEbBaseService.getForeignEntitySelectedData(sqlMapSelectId, dto, meta);

  }

  public List queryDataForList(String sqlMapSelectId, Map parameter, RequestMeta meta) {

    return this.zcEbBaseService.queryDataForList(sqlMapSelectId, parameter);

  }

  public String getSequenceNextVal(String sequenceName, RequestMeta meta) {

    // TCJLODO Auto-generated method stub

    return this.zcEbBaseService.getSequenceNextVal(sequenceName);

  }

  public List queryDataForList(String sqlMapSelectId, Object param, RequestMeta meta) {

    return this.zcEbBaseService.queryDataForList(sqlMapSelectId, param);

  }

  public void updateObjectList(String sqlMapUpdateId, List list, RequestMeta meta) {

    this.zcEbBaseService.updateObjectList(sqlMapUpdateId, list);

  }

  public Object queryObject(String sqlMapSelectId, Object param, RequestMeta meta) {

    return this.zcEbBaseService.queryObject(sqlMapSelectId, param);

  }

  public ZcBaseBill auditFN(ZcBaseBill bill, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbBaseService.auditFN(bill, meta);
  }

  public ZcBaseBill callbackFN(ZcBaseBill bill, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbBaseService.callbackFN(bill, meta);
  }

  public ZcBaseBill newCommitFN(ZcBaseBill bill, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbBaseService.newCommitFN(bill, meta);
  }

  public ZcBaseBill unAuditFN(ZcBaseBill bill, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbBaseService.unauditFN(bill, meta);
  }

  public ZcBaseBill untreadFN(ZcBaseBill bill, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbBaseService.untreadFN(bill, meta);
  }

  public void auditFN(List billList, RequestMeta meta) {

    for (int i = 0; i < billList.size(); i++) {
      ZcBaseBill bill = (ZcBaseBill) billList.get(i);
      zcEbBaseService.auditFN(bill, meta);

    }
  }

  public void callbackFN(List billList, RequestMeta meta) {
    for (int i = 0; i < billList.size(); i++) {
      ZcBaseBill bill = (ZcBaseBill) billList.get(i);
      zcEbBaseService.callbackFN(bill, meta);

    }

  }

  public void newCommitFN(List billList, RequestMeta meta) {
    for (int i = 0; i < billList.size(); i++) {
      ZcBaseBill bill = (ZcBaseBill) billList.get(i);
      zcEbBaseService.newCommitFN(bill, meta);

    }

  }

  public void unAuditFN(List billList, RequestMeta meta) {
    for (int i = 0; i < billList.size(); i++) {
      ZcBaseBill bill = (ZcBaseBill) billList.get(i);
      zcEbBaseService.unauditFN(bill, meta);

    }
  }

  public void untreadFN(List billList, RequestMeta meta) {
    for (int i = 0; i < billList.size(); i++) {
      ZcBaseBill bill = (ZcBaseBill) billList.get(i);
      zcEbBaseService.untreadFN(bill, meta);

    }
  }


}
