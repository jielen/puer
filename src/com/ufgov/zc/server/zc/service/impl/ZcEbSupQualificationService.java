package com.ufgov.zc.server.zc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbSupQualification;
import com.ufgov.zc.common.zc.model.ZcEbSupQualificationLev;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.service.IZcEbSupQualificationService;

public class ZcEbSupQualificationService implements
		IZcEbSupQualificationService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List selectSupQualifications(ElementConditionDto dto) {
		// TCJLODO Auto-generated method stub
		return baseDao.query("ZC_EB_SUP_QUALIFICATION.selectForList", dto);
	}

	public ZcEbSupQualification selectById(String id) {
		// TCJLODO Auto-generated method stub
		List list = baseDao.query("ZC_EB_SUP_QUALIFICATION.selectById", id);
		if (list == null || list.size() == 0) {
			return new ZcEbSupQualification();
		}
		ZcEbSupQualification qf = (ZcEbSupQualification) list.get(0);
		qf.setLevs(baseDao.query("ZC_EB_SUP_QUALIFICATION.selectLevsByQId", id));

		if (qf.getLevs() == null) {
			qf.setLevs(new ArrayList());
		}
		return qf;
	}

	public ZcEbSupQualification save(ZcEbSupQualification bean,
			RequestMeta requestMeta) {
		// TCJLODO Auto-generated method stub
		if (bean.getQualifId() != null && !"".equals(bean.getQualifId())) {
			baseDao.update(
					"ZC_EB_SUP_QUALIFICATION.ibatorgenerated_updateById", bean);
		} else {
			String code = NumUtil.getInstance().getNo(requestMeta.getCompoId(),
					"QUALIF_ID", bean);
			bean.setQualifId(code);
			if (bean.getQualifCode() == null || "".equals(bean.getQualifCode())) {
				bean.setQualifCode(code);
			}
			baseDao.insert("ZC_EB_SUP_QUALIFICATION.ibatorgenerated_insert",
					bean);
		}

		baseDao.delete("ZC_EB_SUP_QUALIFICATION.deleteLevsByQId",
				bean.getQualifId());

		if (bean.getLevs() == null) {
			return bean;
		}

		for (int i = 0; i < bean.getLevs().size(); i++) {
			ZcEbSupQualificationLev lev = (ZcEbSupQualificationLev) bean
					.getLevs().get(i);
			lev.setId(bean.getQualifId() + "-" + i);
			lev.setQualifId(bean.getQualifId());
			baseDao.insert("ZC_EB_SUP_QUALIFICATION.insertLev", bean.getLevs()
					.get(i));
		}

		return bean;
	}

	public void enableById(String id) {
		// TCJLODO Auto-generated method stub
		baseDao.update("ZC_EB_SUP_QUALIFICATION.enableById", id);
	}

	public void freezeById(String id) {
		// TCJLODO Auto-generated method stub
		baseDao.update("ZC_EB_SUP_QUALIFICATION.freezeById", id);
	}

	public void deleteById(String id) {
		// TCJLODO Auto-generated method stub
		baseDao.delete("ZC_EB_SUP_QUALIFICATION.deleteById", id);
		baseDao.delete("ZC_EB_SUP_QUALIFICATION.deleteLevsByQId", id);
	}

}
