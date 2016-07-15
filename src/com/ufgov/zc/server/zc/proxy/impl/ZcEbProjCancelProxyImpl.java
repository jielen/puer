package com.ufgov.zc.server.zc.proxy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.zc.ZcEbConstants;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancel;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancelDetail;
import com.ufgov.zc.common.zc.model.ZcEbPack;
import com.ufgov.zc.common.zc.model.ZcEbProj;
import com.ufgov.zc.common.zc.model.ZcWfCurrentTaskCancel;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.proxy.IZcEbEntrustCancel;
import com.ufgov.zc.server.zc.util.ZcEntrustCancelUtil;

public class ZcEbProjCancelProxyImpl implements IZcEbEntrustCancel {

	public Object entrustCancel(Object o) {
		Map map = (Map) o;
		ZcEbEntrustCancel bean = (ZcEbEntrustCancel) (map
				.get(ZcEbConstants.BEAN));
		BaseDao baseDao = (BaseDao) (map.get(ZcEbConstants.DAO));
		// 未分包任务无立项信息
		if (bean.getPackNum() == 0) {
			return null;
		}
		// 处理分包信息
		if (bean.getPackList() != null && bean.getPackNum() > 0
				&& bean.getPackList().size() > 0) {

			// 设置业务表相关数据
			ZcWfCurrentTaskCancel task = new ZcWfCurrentTaskCancel();
			task.setEntrustCancelId(bean.getEntrustCancelId());
			task.setTblName(ZcEbConstants.ZC_EB_PACK);
			task.setKeyColName(ZcEbConstants.PACK_CODE);
			task.setStatusCol(ZcEbConstants.STATUS);

			Map para = new HashMap();
			for (int i = 0; i < bean.getPackList().size(); i++) {
				ZcEbEntrustCancelDetail item = (ZcEbEntrustCancelDetail) bean
						.getPackList().get(i);
				para.put("packCode", item.getPackCode());
				Iterator keys = item.getIsAll().keySet().iterator();
				while (keys.hasNext()) {
					para.put("projCode", keys.next());
					List list = baseDao.query("ZcEbProj.getZcEbPackByPackCode",
							para);
					if (list == null || list.size() == 0) {
						continue;
					}

					// 保存分包的状态
					for (int j = 0; j < list.size(); j++) {
						ZcEbPack pack = (ZcEbPack) list.get(j);
						task.setStatus(pack.getStatus());
						task.setInstanceId(pack.getPackCode());
						task.setKeyColName(ZcEbConstants.PROJ_CODE + "='"
								+ pack.getProjCode() + "' and "
								+ ZcEbConstants.PACK_CODE);

						ZcEntrustCancelUtil.cancel(baseDao, task);
					}
				}
			}

		}

		// 设置业务表相关数据
		ZcWfCurrentTaskCancel task = new ZcWfCurrentTaskCancel();
		task.setEntrustCancelId(bean.getEntrustCancelId());
		task.setTblName(ZcEbConstants.ZC_EB_PROJ);
		task.setStatusCol(ZcEbConstants.STATUS);
		task.setKeyColName(ZcEbConstants.PROCESS_INST_ID);

		List history = new ArrayList();
		for (int i = 0; i < bean.getPackList().size(); i++) {
			ZcEbEntrustCancelDetail cd = (ZcEbEntrustCancelDetail) bean
					.getPackList().get(i);
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
						"ZcEbProj.getOriginalZcEbProjByProjCode", key);
				if (list == null || list.size() == 0) {
					continue;
				}

				for (int j = 0; j < list.size(); j++) {
					ZcEbProj proj = (ZcEbProj) list.get(j);
					task.setStatus(proj.getStatus());
					task.setInstanceId(proj.getProcessInstId().toString());

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
		task.setTblName(ZcEbConstants.ZC_EB_PROJ);
		task.setKeyColName(ZcEbConstants.PROCESS_INST_ID);

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

		task = new ZcWfCurrentTaskCancel();
		task.setEntrustCancelId(bean.getEntrustCancelId());
		task.setTblName(ZcEbConstants.ZC_EB_PACK);
		task.setKeyColName(ZcEbConstants.PACK_CODE);

		// 取得备份
		list = baseDao.query(
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
