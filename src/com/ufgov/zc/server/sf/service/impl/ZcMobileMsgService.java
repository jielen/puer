package com.ufgov.zc.server.sf.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ufgov.zc.common.sf.model.SmsBoxsending;
import com.ufgov.zc.common.sf.model.ZcMobileMsg;
import com.ufgov.zc.common.sf.model.ZcMobileMsgNumber;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto; 
import com.ufgov.zc.server.sf.service.IZcMobileMsgService;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao; 

public class ZcMobileMsgService implements IZcMobileMsgService {

  private IBaseDao baseDao;

  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return baseDao.query("ZcMobileMsgMapper.getMainLst", elementConditionDto);
  }

  public ZcMobileMsg updateFN(ZcMobileMsg qx, RequestMeta requestMeta) throws Exception {
    if (qx.getCode() == null) {
      qx.setCode(getCode());
      baseDao.insert("ZcMobileMsgMapper.insert", qx);
      insertDetail(qx);
    } else {
      baseDao.update("ZcMobileMsgMapper.updateByPrimaryKey", qx);
      deleteDetail(qx);
      insertDetail(qx);
    }
    //往发送表中发送数据
    sendToBox(qx, requestMeta);
    return selectByPrimaryKey(qx.getCode(), requestMeta);
  }

  private void sendToBox(ZcMobileMsg qx, RequestMeta requestMeta) {
    //往发送表中发送数据
    SmsBoxsending sd = new SmsBoxsending();
    SimpleDateFormat sdf = new SimpleDateFormat(ZcSettingConstants.SIMPLE_DATE_FORMAT_FULL);
    sd.setAppid(qx.getCode());
    sd.setSender(qx.getInputor());
    sd.setContent(qx.getContent());
    sd.setSendtime(sdf.format(qx.getSendTime()));
    sd.setInserttime(sdf.format(qx.getSendTime()));
    sd.setPri("1");
    sd.setInpool("0");
    sd.setSendmode("3");
    for (int i = 0; i < qx.getNumberLst().size(); i++) {
      ZcMobileMsgNumber d = (ZcMobileMsgNumber) qx.getNumberLst().get(i);
      sd.setReceiver(d.getMobile());
      baseDao.insert("ZcMobileMsgMapper.insertSmsBoxsending", sd);
    }
  }

  private String getCode() {
    return ZcSUtil.getNextVal("SF_SEQ_MOBILE_MSG_CODE");
  }

  private void deleteDetail(ZcMobileMsg qx) {
    baseDao.delete("ZcMobileMsgMapper.deleteDetail", qx.getCode());
  }

  private void insertDetail(ZcMobileMsg qx) {
    if (qx.getNumberLst() != null) {
      for (int i = 0; i < qx.getNumberLst().size(); i++) {
        ZcMobileMsgNumber d = (ZcMobileMsgNumber) qx.getNumberLst().get(i);
        d.setCode(qx.getCode());
        baseDao.insert("ZcMobileMsgMapper.insertDetail", d);
      }
    }
  }

  public void deleteFN(ZcMobileMsg qx, RequestMeta requestMeta) {
    baseDao.delete("ZcMobileMsgMapper.deleteByPrimaryKey", qx.getCode());
    deleteDetail(qx);
  }

  public ZcMobileMsg selectByPrimaryKey(String qxCode, RequestMeta requestMeta) {
    ZcMobileMsg rtn = (ZcMobileMsg) baseDao.read("ZcMobileMsgMapper.selectByPrimaryKey", qxCode);
    if (rtn == null) return null;
    List numbersLst = baseDao.query("ZcMobileMsgMapper.selectDetail", qxCode);
    rtn.setNumberLst(numbersLst == null ? new ArrayList() : numbersLst);
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  public ZcMobileMsg callbackFN(ZcMobileMsg qx, RequestMeta requestMeta) {
    return null;
  }

  public IBaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(IBaseDao baseDao) {
    this.baseDao = baseDao;
  }

}
