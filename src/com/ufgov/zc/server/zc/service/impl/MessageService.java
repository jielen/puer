package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.service.IMessageService;

public class MessageService implements IMessageService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public boolean isCgzxGroup(String userId) {
		Integer t = (Integer) baseDao.read("message.isCgzxGroup", userId);
		int tt = t == null ? 0 : t.intValue();
		return tt > 0;
	}

	public List getMessage(String userId) {
		List result = new ArrayList();

		// 工作流审批
		List list = baseDao.query("message.getMessage", userId);
		if (list != null && list.size() > 0) {
			result.add("待审核工作流：");
			for (int i = 0; i < list.size(); i++) {
				String str1 = list.get(i).toString();
				String[] strarray = str1.split(",nextline");
				for (int j = 0; j < strarray.length; j++) {
					result.add(strarray[j]);
				}
				// for(String str : list.get(i).split(",nextline")){
				// result.add(str);
				// }
			}
		}

		// 工作待处理
		List addList = new ArrayList();
		BigDecimal comp = new BigDecimal("0.9");

		// 批办单可以
		// Map sheet = (Map) baseDao.read("message.getSheetMessage", userId);
		// if( sheet != null && sheet.get("SZ") != null && ((BigDecimal)
		// sheet.get("SZ")).compareTo(comp) > 0){
		// addList.add((String) sheet.get("MSG"));
		// }

		// 需求可以
		// 征求意见公告可以
		// 资格预审公告可以、变更采购方式的？
		// 招标文件可以、变更后可能不可以
		// 采购公告可以
		// 变更、延期公告、采购方式变更不可
		// 评标控制台可以、开标评标组应该可以
		// 预中标可以、质疑后有问题
		// 中标可以、不准确
		// 成交可以、但不准确
		// 合同可以

		// 预中标提示
		Map yzb = (Map) baseDao.read("message.getYzbMessage", userId);
		if (yzb != null && yzb.get("SZ") != null
				&& ((BigDecimal) yzb.get("SZ")).compareTo(comp) > 0) {
			addList.add(yzb.get("MSG"));
		}

		if (addList != null && addList.size() > 0) {
			result.add("");
			result.add("待进行工作：");
			result.addAll(addList);
		}

		// if(result.size() > 0){
		// baseDao.delete("message.deleteHistoryByUserId", userId);
		// baseDao.insert("message.insertHistoryByUserId", userId);
		// }
		return result;

	}

	public void logout(String userId) {
		baseDao.delete("message.deleteHistoryByUserId", userId);
	}

}
