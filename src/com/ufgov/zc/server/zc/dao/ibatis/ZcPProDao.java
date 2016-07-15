package com.ufgov.zc.server.zc.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPPro;
import com.ufgov.zc.server.zc.dao.IZcPProDao;

public class ZcPProDao extends SqlMapClientDaoSupport implements IZcPProDao {

	public void deleteZcPPro(String id) throws Exception {
		// TCJLODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("ZC_P_PRO.deleteZcPPro", id);
	}

	public void updateZcPPro(ZcPPro zcPPro) throws Exception {
		// TCJLODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("ZC_P_PRO.updateZcPPro", zcPPro);
	}

	public void saveZcPPro(ZcPPro zcPPro) throws Exception {
		// TCJLODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("ZC_P_PRO.saveZcPPro", zcPPro);
	}

	public List getZcPPro(ElementConditionDto elementConditionDto)
			throws Exception {
		// TCJLODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				"ZC_P_PRO.getZcPPro", elementConditionDto);
	}

	public ZcPPro getZcPProById(String id) throws Exception {
		// TCJLODO Auto-generated method stub
		List list = this.getSqlMapClientTemplate().queryForList(
				"ZC_P_PRO.getZcPProById", id);
		return (ZcPPro) (list == null || list.size() == 0 ? null : list.get(0));
	}

	public int checkUsed(String id) throws Exception {
		// TCJLODO Auto-generated method stub
		Integer rtn = (Integer) this.getSqlMapClientTemplate().queryForObject(
				"ZC_P_PRO.checkUsed", id);
		return rtn == null ? 0 : rtn.intValue();
	}

}
