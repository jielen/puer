package com.ufgov.zc.server.zc.proxy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.zc.ZcEbConstants;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancel;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancelDetail;
import com.ufgov.zc.common.zc.model.ZcEbSignup;
import com.ufgov.zc.common.zc.model.ZcEbSignupPackDetail;
import com.ufgov.zc.common.zc.model.ZcWfCurrentTaskCancel;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.proxy.IZcEbEntrustCancel;
import com.ufgov.zc.server.zc.util.ZcEntrustCancelUtil;

public class ZcEbSignupCancelProxyImpl implements IZcEbEntrustCancel {

	public Object entrustCancel(Object o) {
		Map map = (Map) o;
		ZcEbEntrustCancel bean = (ZcEbEntrustCancel) (map
				.get(ZcEbConstants.BEAN));
		BaseDao baseDao = (BaseDao) (map.get(ZcEbConstants.DAO));

		// 未分包任务不处理
		if (bean.getPackNum() == 0) {
			return null;
		}

		ZcWfCurrentTaskCancel task = new ZcWfCurrentTaskCancel();
		task.setEntrustCancelId(bean.getEntrustCancelId());
		task.setTblName(ZcEbConstants.ZC_EB_SIGNUP_PACK);
		task.setStatusCol(ZcEbConstants.OPEN_BID_STATUS);
		task.setKeyColName(ZcEbConstants.SIGNUP_PACK_ID);

		for (int i = 0; i < bean.getPackList().size(); i++) {
			ZcEbEntrustCancelDetail cd = (ZcEbEntrustCancelDetail) bean
					.getPackList().get(i);

			Map isAll = cd.getIsAll();

			// 未立项包不做处理
			if (isAll == null || isAll.isEmpty()) {
				continue;
			}
			Map para = new HashMap();
			para.put("PACK_CODE", cd.getPackCode());

			Iterator keys = isAll.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next().toString();
				para.put("PROJ_CODE", key);

				// 取得业务表格数据
				List list = baseDao.query(
						"ZcEbSignup.getZcEbSignupPackDetailByPackCode", para);
				if (list == null || list.size() == 0) {
					continue;
				}

				for (int j = 0; j < list.size(); j++) {
					ZcEbSignupPackDetail detail = (ZcEbSignupPackDetail) list
							.get(j);
					task.setStatus(detail.getOpenBidStatus());
					task.setInstanceId(detail.getSignupPackId());

					ZcEntrustCancelUtil.cancel(baseDao, task);
				}
			}

		}

		task = new ZcWfCurrentTaskCancel();
		task.setEntrustCancelId(bean.getEntrustCancelId());
		task.setTblName(ZcEbConstants.ZC_EB_SIGNUP);
		task.setStatusCol(ZcEbConstants.STATUS);
		task.setKeyColName(ZcEbConstants.SIGNUP_ID);

		List history = new ArrayList();
		for (int i = 0; i < bean.getPackList().size(); i++) {
			ZcEbEntrustCancelDetail cd = (ZcEbEntrustCancelDetail) bean
					.getPackList().get(i);

			Map isAll = cd.getIsAll();

			// 未立项包不做处理
			if (isAll == null || isAll.isEmpty()) {
				continue;
			}

			Iterator keys = isAll.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next().toString();

				// 只取消项目中一部分包，不处理
				Integer temp1 = (Integer) isAll.get(key);
				int temp2 = temp1 == null ? 0 : temp1.intValue();
				if (temp2 > 0) {
					continue;
				}

				// 处理过的项目不再重复操作
				if (history.contains(key)) {
					continue;
				}

				// 取得业务表格数据
				history.add(key);
				List list = baseDao.query("ZcEbSignup.getZcEbSignupForCancel",
						key);
				if (list == null || list.size() == 0) {
					continue;
				}

				for (int j = 0; j < list.size(); j++) {
					ZcEbSignup sig = (ZcEbSignup) list.get(j);
					task.setStatus(sig.getStatus());
					task.setInstanceId(sig.getSignupId());

					ZcEntrustCancelUtil.cancel(baseDao, task);
				}
			}
		}

		return null;
	}

	public Object entrustRecovery(Object o) {
		Map map = (Map) o;
		ZcEbEntrustCancel bean = (ZcEbEntrustCancel) (map
				.get(ZcEbConstants.BEAN));
		BaseDao baseDao = (BaseDao) (map.get(ZcEbConstants.DAO));

		// 设置业务表相关数据
		ZcWfCurrentTaskCancel task = new ZcWfCurrentTaskCancel();
		task.setEntrustCancelId(bean.getEntrustCancelId());
		task.setTblName(ZcEbConstants.ZC_EB_SIGNUP);
		task.setKeyColName(ZcEbConstants.SIGNUP_PACK_ID);

		// 取得备份
		List list = baseDao.query(
				"ZcWfCurrentTaskCancel.getZcWfCurrentTaskCancelResult", task);
		if (list == null || list.size() == 0) {
			// return null;
		} else {
			for (int i = 0; i < list.size(); i++) {
				task = (ZcWfCurrentTaskCancel) list.get(i);

				ZcEntrustCancelUtil.recovery(baseDao, task);
			}
		}

		task.setTblName(ZcEbConstants.ZC_EB_SIGNUP_PACK);
		task.setKeyColName(ZcEbConstants.SIGNUP_ID);

		// 取得备份
		list = baseDao.query(
				"ZcWfCurrentTaskCancel.getZcWfCurrentTaskCancelResult", task);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			for (int i = 0; i < list.size(); i++) {
				task = (ZcWfCurrentTaskCancel) list.get(i);

				ZcEntrustCancelUtil.recovery(baseDao, task);
			}
		}
		return null;
	}

}
