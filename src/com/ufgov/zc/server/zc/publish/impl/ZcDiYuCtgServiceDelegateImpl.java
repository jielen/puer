/*
 * *
 *  Copyright 2012 by Beijing UFIDA Government Affairs Software Co.,Ltd.,
 *  All rights reserved.
 *
 *  ��Ȩ���У�������������������޹�˾
 *  δ������˾��ɣ��������κη�ʽ���ƻ�ʹ�ñ������κβ��֣�
 *  ��Ȩ�߽��ܵ�����׷����
 * /
 */

package com.ufgov.zc.server.zc.publish.impl;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.zc.model.ZCDiYuCtg;
import com.ufgov.zc.common.zc.publish.ZCDiYuCtgServiceDelegate;
import com.ufgov.zc.server.zc.dao.ZCDiYuCtgRepository;

import java.util.List;

/**
 * <p>PURPOSE:
 * <p>DESCRIPTION:
 * <p>CALLED BY:	qianmingjin
 * <p>CREATE DATE:  12-3-23
 * <p>UPDATE DATE: 12-3-23
 * <p>UPDATE USER: qianmingjin
 * <p>HISTORY:		1.0
 *
 * @author qianmingjin
 * @version 1.0
 * @see
 * @since java 1.5.0
 */

public class ZcDiYuCtgServiceDelegateImpl implements ZCDiYuCtgServiceDelegate {
  private ZCDiYuCtgRepository zcDiYuCtgRepository;

  public void setZcDiYuCtgRepository(ZCDiYuCtgRepository zcDiYuCtgRepository) {
    this.zcDiYuCtgRepository = zcDiYuCtgRepository;
  }

  public int deleteZCDiYuCtgList(String zcDiYuCode, RequestMeta meta) throws Exception {
    return zcDiYuCtgRepository.deleteZCDiYuCtgList(zcDiYuCode);
  }

  public int updateZCDiYuCtg(ZCDiYuCtg zcDiYuCtg, RequestMeta meta) throws Exception {
    return zcDiYuCtgRepository.updateZCDiYuCtg(zcDiYuCtg);
  }

  public ZCDiYuCtg insertZCDiYuCtg(ZCDiYuCtg zcDiYuCtgItem, RequestMeta meta) throws Exception {
    return zcDiYuCtgRepository.insertZCDiYuCtg(zcDiYuCtgItem);
  }

  
  public ZCDiYuCtg saveZCDiYuCtg(ZCDiYuCtg zcDiYuCtg, RequestMeta meta, String pageStatus) throws Exception {
    if (ZcSettingConstants.PAGE_STATUS_NEW.equals(pageStatus)) {
      return insertZCDiYuCtg(zcDiYuCtg, meta);
    }
    if (ZcSettingConstants.PAGE_STATUS_EDIT.equals(pageStatus)) {
      updateZCDiYuCtg(zcDiYuCtg, meta);
      return zcDiYuCtg;
    }
    return null;
  }

  public List getZCDiYuCtgList(String zcDiYuCode, RequestMeta meta) throws Exception {
    return zcDiYuCtgRepository.getZCDiYuCtgList(zcDiYuCode);
  }
}
