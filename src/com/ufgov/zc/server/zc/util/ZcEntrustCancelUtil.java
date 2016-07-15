package com.ufgov.zc.server.zc.util;

import com.ufgov.zc.common.zc.ZcEbConstants;
import com.ufgov.zc.common.zc.model.ZcWfCurrentTaskCancel;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;

public class ZcEntrustCancelUtil {

	public static void cancel(BaseDao baseDao, ZcWfCurrentTaskCancel task) {

		// 保存业务表及对应在审工作流数据
		if (ZcEbConstants.PROCESS_INST_ID.equals(task.getKeyColName())) {
			baseDao.insert("ZcWfCurrentTaskCancel.insertZcWfCurrentTaskCancel",
					task);
			baseDao.insert(
					"ZcWfCurrentTaskCancel.insertZcWfCurrentTaskCancelForInstance",
					task);
			baseDao.update("ZcWfCurrentTaskCancel.updateInstanceStatus",
					task.getInstanceId());
		}

		// 取消业务表数据
		baseDao.update("ZcWfCurrentTaskCancel.updateCancel", task);
		Long t = (Long) baseDao.read("ZcWfCurrentTaskCancel.getCount", task);
		long count = t == null ? 0 : t.longValue();
		if (count == 0) {
			// 无工作流在审数据时，保存原业务表状态数据
			baseDao.insert(
					"ZcWfCurrentTaskCancel.insertSimpleZcWfCurrentTaskCancel",
					task);
		} else {
			// 有工作流在审数据，把该数据删除
			baseDao.delete("ZcWfCurrentTaskCancel.deleteByInstanceId", task
					.getInstanceId().toString());
		}
	}

	public static void recovery(BaseDao baseDao, ZcWfCurrentTaskCancel task) {

		// 恢复业务数据状态
		baseDao.update("ZcWfCurrentTaskCancel.updateRecovery", task);

		// 恢复工作流审核状态
		baseDao.insert("ZcWfCurrentTaskCancel.insertWfRecovery", task);

		// 删除备份
		baseDao.delete("ZcWfCurrentTaskCancel.deleteByTable", task);
	}
}
