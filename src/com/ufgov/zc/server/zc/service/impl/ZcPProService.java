package com.ufgov.zc.server.zc.service.impl;

import java.util.List;

import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPPro;
import com.ufgov.zc.server.zc.dao.IZcPProDao;
import com.ufgov.zc.server.zc.service.IZcPProService;

public class ZcPProService implements IZcPProService {

	private IZcPProDao zcPProDao;

	public IZcPProDao getZcPProDao() {
		return zcPProDao;
	}

	public void setZcPProDao(IZcPProDao zcPProDao) {
		this.zcPProDao = zcPProDao;
	}

	public void deleteZcPPro(String id) throws Exception {
		// TCJLODO Auto-generated method stub
	  if(zcPProDao.checkUsed(id) > 0){
	    throw new Exception("改预算项目已经被使用，不能删除");
	  }
		zcPProDao.deleteZcPPro(id);
	}

	public void updateZcPPro(ZcPPro zcPPro) throws Exception {
		// TCJLODO Auto-generated method stub
		zcPProDao.updateZcPPro(zcPPro);
	}

	public void saveZcPPro(ZcPPro zcPPro) throws Exception {
		// TCJLODO Auto-generated method stub
		zcPProDao.saveZcPPro(zcPPro);
	}

	public List getZcPPro(ElementConditionDto elementConditionDto)
			throws Exception {
		// TCJLODO Auto-generated method stub
		return zcPProDao.getZcPPro(elementConditionDto);
	}


	public ZcPPro getZcPProById(String id) throws Exception {
		// TCJLODO Auto-generated method stub
		return zcPProDao.getZcPProById(id);
	}

}
