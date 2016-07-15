package com.ufgov.zc.server.zc.proxy.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.zc.ZcEbConstants;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancel;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancelDetail;
import com.ufgov.zc.common.zc.model.ZcEbEvalReport;
import com.ufgov.zc.common.zc.model.ZcWfCurrentTaskCancel;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.proxy.IZcEbEntrustCancel;
import com.ufgov.zc.server.zc.util.ZcEntrustCancelUtil;

public class ZcEbEvalCancelProxyImpl implements IZcEbEntrustCancel {

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
		task.setTblName(ZcEbConstants.ZC_EB_EVAL_REPORT);
		task.setStatusCol(ZcEbConstants.STATUS);
		task.setKeyColName(ZcEbConstants.REPORT_CODE);

		Map eval = new HashMap();
		for (int i = 0; i < bean.getPackList().size(); i++) {

			ZcEbEntrustCancelDetail cd = (ZcEbEntrustCancelDetail) bean
					.getPackList().get(i);
			eval.put("packCode", cd.getPackCode());

			Map isAll = cd.getIsAll();

			// 未立项包不做处理
			if (isAll == null || isAll.isEmpty()) {
				continue;
			}

			Iterator keys = isAll.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next().toString();

				eval.put("projCode", key);

				// 取得业务表格数据
				List list = baseDao.query(
						"ZcEbEval.getZcEbEvalReportByPackCode", eval);
				if (list == null || list.size() == 0) {
					continue;
				}

				for (int j = 0; j < list.size(); j++) {
					ZcEbEvalReport report = (ZcEbEvalReport) list.get(j);
					task.setStatus(report.getStatus());
					task.setInstanceId(report.getReportCode());

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
		task.setTblName(ZcEbConstants.ZC_EB_EVAL_REPORT);
		task.setKeyColName(ZcEbConstants.REPORT_CODE);

		// 取得备份
		List list = baseDao.query(
				"ZcWfCurrentTaskCancel.getZcWfCurrentTaskCancelResult", task);
		if (list == null || list.size() == 0) {
			return null;
		}
		task = (ZcWfCurrentTaskCancel) list.get(0);

		ZcEntrustCancelUtil.recovery(baseDao, task);

		return null;
	}

}
