package com.ufgov.zc.server.zc.proxy.impl;

import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.zc.ZcEbConstants;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancel;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancelDetail;
import com.ufgov.zc.common.zc.model.ZcEbPack;
import com.ufgov.zc.common.zc.model.ZcEbRequirement;
import com.ufgov.zc.common.zc.model.ZcWfCurrentTaskCancel;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.proxy.IZcEbEntrustCancel;
import com.ufgov.zc.server.zc.util.ZcEntrustCancelUtil;

public class ZcEbRequirementCancelProxyImpl implements IZcEbEntrustCancel {

	public Object entrustCancel(Object o) {
		Map map = (Map) o;
		ZcEbEntrustCancel bean = (ZcEbEntrustCancel) (map
				.get(ZcEbConstants.BEAN));
		BaseDao baseDao = (BaseDao) (map.get(ZcEbConstants.DAO));

		// 处理分包信息
		if (bean.getPackList() != null && bean.getPackNum() > 0
				&& bean.getPackList().size() > 0) {

			// 设置业务表相关数据
			ZcWfCurrentTaskCancel task = new ZcWfCurrentTaskCancel();
			task.setEntrustCancelId(bean.getEntrustCancelId());
			task.setTblName(ZcEbConstants.ZC_EB_REQ_PACK);
			task.setKeyColName(ZcEbConstants.PACK_CODE);
			task.setStatusCol(ZcEbConstants.STATUS);

			for (int i = 0; i < bean.getPackList().size(); i++) {
				ZcEbEntrustCancelDetail item = (ZcEbEntrustCancelDetail) bean
						.getPackList().get(i);
				List list = baseDao.query("ZcEbReqPack.getZcEbPackByPackCode",
						item.getPackCode());
				if (list == null || list.size() == 0) {
					continue;
				}

				// 保存分包的状态
				for (int j = 0; j < list.size(); j++) {
					ZcEbPack pack = (ZcEbPack) list.get(j);
					task.setStatus(pack.getStatus());
					task.setInstanceId(pack.getPackCode());

					ZcEntrustCancelUtil.cancel(baseDao, task);
				}
			}

		}
		Integer tt = (Integer) baseDao.read("ZcEbProj.selectNOCancelPack",
				bean.getEntrustCancelId());
		int size = tt == null ? 0 : tt.intValue();

		// 已经分包且只取消一部分，需求不处理
		if (bean.getPackList() != null && bean.getPackNum() > 0 && size > 0) {
			return null;
		}

		// 设置业务表相关数据
		ZcWfCurrentTaskCancel task = new ZcWfCurrentTaskCancel();
		task.setEntrustCancelId(bean.getEntrustCancelId());
		task.setTblName(ZcEbConstants.ZC_EB_REQUIREMENT);
		task.setKeyColName(ZcEbConstants.PROCESS_INST_ID);
		task.setStatusCol(ZcEbConstants.STATUS);

		// 取得业务表格数据
		List list = baseDao.query(
				"ZcEbRequirement.getOriginZcEbRequirementBySn", bean.getSn());
		if (list == null || list.size() == 0) {
			return null;
		}

		for (int i = 0; i < list.size(); i++) {
			ZcEbRequirement req = (ZcEbRequirement) list.get(i);
			task.setStatus(req.getStatus());
			if (req.getProcessInstId() == null) {
				task.setKeyColName(ZcEbConstants.REQ_CODE);
				task.setInstanceId(req.getReqCode());
			} else {
				task.setKeyColName(ZcEbConstants.PROCESS_INST_ID);
				task.setInstanceId(req.getProcessInstId().toString());
			}

			ZcEntrustCancelUtil.cancel(baseDao, task);
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
		task.setTblName(ZcEbConstants.ZC_EB_REQUIREMENT);
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
		task.setTblName(ZcEbConstants.ZC_EB_REQ_PACK);
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
