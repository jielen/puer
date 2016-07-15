package com.ufgov.zc.server.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.zc.model.EleRegion;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.dao.IBaseDao;

public class EleRegionUsingUtil {

	private IBaseDao BaseDao = (IBaseDao) SpringContext.getBean("zcBaseDao");

	private static EleRegionUsingUtil eleRegionUsingUtil = new EleRegionUsingUtil();

	private static Map eleRegions = new HashMap();

	private static List dataList = new ArrayList();

	public synchronized EleRegion getEleRegion(String code) {
		EleRegion region = (EleRegion) eleRegions.get(code);
		if (region == null) {
			region = (EleRegion) BaseDao.read("ELE_REGION.getEleRegionByCode",
					code);
			eleRegions.put(code, region);
		}
		return region;
	}

	public synchronized List getAllEleRegion() {

		if (dataList == null || dataList.size() == 0) {
			dataList = BaseDao.query("ELE_REGION.getAllEleRegions", null);
			for (int i = 0; i < dataList.size(); i++) {
				EleRegion region = (EleRegion) dataList.get(i);
				eleRegions.put(region.getChrCode(), region);
			}
		}
		return dataList;
	}

	public EleRegion getTopEleRegion() {
		EleRegion result = null;

		if (dataList == null || dataList.size() == 0) {
			getAllEleRegions();
		}

		for (int i = 0; i < dataList.size(); i++) {
			EleRegion region = (EleRegion) dataList.get(i);
			if (region.getIsTop() != null || region.getIsTop().intValue() == 1) {
				result = region;
			}
		}
		return result;
	}

	private synchronized void getAllEleRegions() {
		dataList = BaseDao.query("ELE_REGION.getAllEleRegions", null);
		for (int i = 0; i < dataList.size(); i++) {
			EleRegion region = (EleRegion) dataList.get(i);
			eleRegions.put(region.getChrCode(), region);
		}
	}

	public String getTopEleRegionCode() {
		EleRegion result = null;

		if (dataList == null || dataList.size() == 0) {
			getAllEleRegions();
		}

		for (int i = 0; i < dataList.size(); i++) {
			EleRegion region = (EleRegion) dataList.get(i);
			if (region.getIsTop() != null || region.getIsTop().intValue() == 1) {
				result = region;
			}
		}

		return result == null ? "" : result.getChrCode();
	}

	public static EleRegionUsingUtil getInstance() {

		return eleRegionUsingUtil;

	}
}
