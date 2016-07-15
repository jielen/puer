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

package com.ufgov.zc.server.zc.dao.ibatis;

import com.ufgov.zc.common.zc.model.ZCDiYuCtg;
import com.ufgov.zc.server.zc.dao.ZCDiYuCtgRepository;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>PURPOSE:    ����������repository
 * <p>DESCRIPTION:���ĸ�����
 * <p>CALLED BY:	qianmingjin
 * <p>CREATE DATE:  12-3-26
 * <p>UPDATE DATE: 12-3-26
 * <p>UPDATE USER: qianmingjin
 * <p>HISTORY:		1.0
 *
 * @author qianmingjin
 * @version 1.0
 * @see
 * @since java 1.5.0
 */
public class ZCDiYuCtgRepositoryImpl extends SqlMapClientDaoSupport implements ZCDiYuCtgRepository {
  public List getZCDiYuCtgList(String zcDiYuCode) throws SQLException {
    return this.getSqlMapClient().queryForList("ZC_B_DIYUCTG.selectZcDiYuCtgList", zcDiYuCode);
  }

  public int updateZCDiYuCtg(ZCDiYuCtg zcDiYuCtg) throws SQLException {
    return getSqlMapClient().update("ZC_B_DIYUCTG.updateZcDiYuCtg", zcDiYuCtg);
  }

  public ZCDiYuCtg insertZCDiYuCtg(ZCDiYuCtg zcDiYuCtgItem) throws SQLException {
    return (ZCDiYuCtg) getSqlMapClient().insert("ZC_B_DIYUCTG.insertZcDiYuCtg", zcDiYuCtgItem);
  }

  public int deleteZCDiYuCtgList(String zcDiYuCode) throws SQLException {
    return getSqlMapClient().delete("ZC_B_DIYUCTG.deleteZcDiYuCtg", zcDiYuCode);
  }
}
