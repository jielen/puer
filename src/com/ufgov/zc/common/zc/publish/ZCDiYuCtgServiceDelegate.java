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

package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.zc.model.ZCDiYuCtg;

/**
 * <p>
 * PURPOSE: �������service�ӿ�
 * <p>
 * DESCRIPTION:
 * <p>
 * CALLED BY: qianmingjin
 * <p>
 * CREATE DATE: 12-3-23
 * <p>
 * UPDATE DATE: 12-3-23
 * <p>
 * UPDATE USER: qianmingjin
 * <p>
 * HISTORY: 1.0
 * 
 * @author qianmingjin
 * @version 1.0
 * @see
 * @since java 1.5.0
 */
public interface ZCDiYuCtgServiceDelegate extends Publishable {
	/**
	 * ��ѯ��������б�
	 * 
	 * @return
	 */
	public List getZCDiYuCtgList(String ZCDiYuCode, RequestMeta meta)
			throws Exception;

	/**
	 * ɾ��һ������
	 */
	public int deleteZCDiYuCtgList(String ZCDiYuCode, RequestMeta meta)
			throws Exception;

	/**
	 * �޸�һ������
	 * 
	 * @param zcDiYuCtgItem
	 * @return
	 */
	public int updateZCDiYuCtg(ZCDiYuCtg zcDiYuCtgItem, RequestMeta meta)
			throws Exception;

	/**
	 * ����һ������
	 * 
	 * @param zcDiYuCtgItem
	 * @return
	 */
	public ZCDiYuCtg insertZCDiYuCtg(ZCDiYuCtg zcDiYuCtgItem, RequestMeta meta)
			throws Exception;

	public ZCDiYuCtg saveZCDiYuCtg(ZCDiYuCtg zcDiYuCtg, RequestMeta meta,
			String pageStatus) throws Exception;

}
