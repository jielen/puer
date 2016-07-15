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

package com.ufgov.zc.server.zc.dao;

import com.ufgov.zc.common.zc.model.ZCDiYuCtg;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>PURPOSE:   ��������
 * <p>DESCRIPTION: ����������ݲ����ӿ�
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
public interface ZCDiYuCtgRepository {

  public List getZCDiYuCtgList(String zcDiYuCode) throws SQLException;

  public int updateZCDiYuCtg(ZCDiYuCtg zcDiYuCtgItem) throws SQLException;

  public ZCDiYuCtg insertZCDiYuCtg(ZCDiYuCtg zcDiYuCtgItem) throws SQLException;

  public int deleteZCDiYuCtgList(String zcDiYuCode) throws SQLException;
}
