package com.ufgov.zc.server.zc.proxy.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.zc.ZcEbConstants;
import com.ufgov.zc.common.zc.model.ZcEbBulletin;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancel;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancelDetail;
import com.ufgov.zc.common.zc.model.ZcWfCurrentTaskCancel;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.proxy.IZcEbEntrustCancel;
import com.ufgov.zc.server.zc.util.ZcEntrustCancelUtil;

public class ZcEbBullitenCancelProxyImpl implements IZcEbEntrustCancel {

	public Object entrustCancel(Object o) {
		Map map = (Map) o;
		ZcEbEntrustCancel bean = (ZcEbEntrustCancel) (map
				.get(ZcEbConstants.BEAN));
		BaseDao baseDao = (BaseDao) (map.get(ZcEbConstants.DAO));
		Integer tt = (Integer) baseDao.read("ZcEbProj.selectNOCancelPack",
				bean.getEntrustCancelId());
		int size = tt == null ? 0 : tt.intValue();
		// 未分包任务或取消全部任务分包，立项前的公告需取消
		if (bean.getPackNum() == 0
				|| (bean.getPackNum() > 0 && bean.getPackList() != null && size == bean
						.getPackList().size())) {
			ZcWfCurrentTaskCancel task = new ZcWfCurrentTaskCancel();
			task.setEntrustCancelId(bean.getEntrustCancelId());
			task.setTblName(ZcEbConstants.ZC_EB_BULLETIN);
			task.setStatusCol(ZcEbConstants.BULLETIN_STATUS);
			task.setKeyColName(ZcEbConstants.PROCESS_INST_ID);
			List list = baseDao.query("ZcEbBulletin.selectBulletinByProjCode",
					bean.getSnCode());
			if (list != null) {
				for (int j = 0; j < list.size(); j++) {
					ZcEbBulletin tin = (ZcEbBulletin) list.get(j);
					task.setStatus(tin.getBulletinStatus());
					task.setInstanceId(tin.getProcessInstId().toString());

					ZcEntrustCancelUtil.cancel(baseDao, task);
				}
			}
		}

		if (bean.getPackNum() == 0) {
			return null;
		}

		// 设置业务表相关数据
		ZcWfCurrentTaskCancel task = new ZcWfCurrentTaskCancel();
		task.setEntrustCancelId(bean.getEntrustCancelId());
		task.setTblName(ZcEbConstants.ZC_EB_BULLETIN);
		task.setStatusCol(ZcEbConstants.BULLETIN_STATUS);
		task.setKeyColName(ZcEbConstants.PROCESS_INST_ID);

		List history = new ArrayList();
		for (int i = 0; i < bean.getPackList().size(); i++) {
			ZcEbEntrustCancelDetail cd = (ZcEbEntrustCancelDetail) bean
					.getPackList().get(i);
			List chgs = baseDao.query("ZcEbBulletin.selectBulletinByPackCode",
					cd.getPackCode());

			for (int j = 0; chgs != null && j < chgs.size(); j++) {
				ZcEbBulletin tin = (ZcEbBulletin) chgs.get(j);
				task.setStatus(tin.getBulletinStatus());
				task.setInstanceId(tin.getProcessInstId().toString());

				ZcEntrustCancelUtil.cancel(baseDao, task);
			}

			Map isAll = cd.getIsAll();
			// Map isAll = bean.getPackList().get(i).getIsAll();

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
				List list = baseDao.query(
						"ZcEbBulletin.selectBulletinByProjCode", key);
				if (list == null || list.size() == 0) {
					continue;
				}

				for (int j = 0; j < list.size(); j++) {
					ZcEbBulletin tin = (ZcEbBulletin) list.get(j);
					task.setStatus(tin.getBulletinStatus());
					task.setInstanceId(tin.getProcessInstId().toString());

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
		task.setTblName(ZcEbConstants.ZC_EB_BULLETIN);
		task.setKeyColName(ZcEbConstants.PROCESS_INST_ID);

		// 取得备份
		List list = baseDao.query(
				"ZcWfCurrentTaskCancel.getZcWfCurrentTaskCancelResult", task);
		if (list == null || list.size() == 0) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			task = (ZcWfCurrentTaskCancel) list.get(i);

			ZcEntrustCancelUtil.recovery(baseDao, task);
		}

		return null;
	}

}
