package com.ufgov.zc.server.zc.service.impl;

import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPProMitemBi;
import com.ufgov.zc.common.zc.model.ZcYearPlan;
import com.ufgov.zc.server.budget.util.BudgetUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao;
import com.ufgov.zc.server.zc.service.IZcYearEndService;

public class ZcYearEndService implements IZcYearEndService {

	private IBaseDao baseDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List getZcPProMake(ElementConditionDto dto, RequestMeta meta)
			throws Exception {
		// TCJLODO Auto-generated method stub
		return baseDao.query("ZC_YEAR_END.selectZcProEnd", dto);
	}

	public void updateProEnd(String zcMakeCode, boolean flag, String serverAdd,
			RequestMeta requestMeta) throws Exception {
		// TCJLODO Auto-generated method stub
		// 取得要解冻的指标
		Map map = new BudgetUtil().getProEndBudget(baseDao, true, zcMakeCode);
		// 更新计划表，yepflag变为10
		baseDao.update("ZC_YEAR_END.updateMakeYepFlag", zcMakeCode);

		new BudgetUtil().callService(map, requestMeta.getSvNd());

	}

	public void updateYearEnd(String zcMakeCode, RequestMeta requestMeta)
			throws Exception {

	}

	/**
	 * 获取结转锁定的项目
	 */
	public List getZcCarraryMake(ElementConditionDto dto, RequestMeta meta)
			throws Exception {
		return baseDao.query("ZC_YEAR_END.selectCarraryMake", dto);
	}

	public void updateMakeYepFlagByFlag(ElementConditionDto dto,
			String serverAdd, RequestMeta meta) throws Exception {

		String flag = dto.getDattr1();
		if ("20".equals(flag)) {
			// 取得要解冻的指标
			Map map = new BudgetUtil().getProEndBudget(baseDao, true,
					dto.getZcMakeCode());
			baseDao.update("ZC_YEAR_END.updateMakeYepFlagByFlag", dto);
			new BudgetUtil().callService(map, meta.getSvNd());
		} else {
			baseDao.update("ZC_YEAR_END.updateMakeYepFlagByFlag", dto);
		}

	}

	public void carraryNewMake(ElementConditionDto dto, String serverAdd,
			RequestMeta meta) throws Exception {

		baseDao.update("ZC_YEAR_END.carraryNewMake", dto);
		try {
			Map map = new BudgetUtil().getProEndBudget(baseDao, true,
					dto.getZcText0());
			new BudgetUtil().callService(map, meta.getSvNd());
		} catch (Exception e) {
			baseDao.update("ZC_YEAR_END.cancelCarraryMake", dto);
			throw new Exception(e);
		}

	}

	/**
	 * 手动结转立项
	 */
	public void carraryNewMakeByManual(ZcYearPlan yearPlan, String serverAdd,
			RequestMeta meta) throws Exception {
		List makeBiList = yearPlan.getMakeBiList();
		if (null != makeBiList && makeBiList.size() > 0) {
			for (int i = 0; i < makeBiList.size(); i++) {
				ZcPProMitemBi bi = (ZcPProMitemBi) makeBiList.get(i);
				baseDao.insert("ZC_YEAR_END.saveMakeBi", bi);
			}
			// for(ZcPProMitemBi bi : makeBiList){
			// baseDao.insert("ZC_YEAR_END.saveMakeBi", bi);
			// }
		}

		List htBiList = yearPlan.getHtBiList();
		if (null != htBiList && htBiList.size() > 0) {
			for (int i = 0; i < makeBiList.size(); i++) {
				ZcPProMitemBi bi = (ZcPProMitemBi) htBiList.get(i);
				baseDao.insert("ZC_YEAR_END.saveHtBi", bi);
			}
			// for (ZcPProMitemBi bi : htBiList) {
			// baseDao.insert("ZC_YEAR_END.saveHtBi", bi);
			// }
		}

		baseDao.update("ZC_YEAR_END.carrarySdNewMake", yearPlan);

		// 调用指标接口，冻结新指标中用到的金额
		Map map = new BudgetUtil().getProEndBudget(baseDao, true,
				"'" + yearPlan.getZcMakeCode() + "_1'");
		new BudgetUtil().callService(map, meta.getSvNd());
	}

}
