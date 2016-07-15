package com.ufgov.zc.server.budget.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.budget.model.VwBudgetGp;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.budget.dao.IVwBudgetGpDao;

public class VwBudgetGpDaoImpl extends SqlMapClientDaoSupport implements
		IVwBudgetGpDao {

	public List findBudgetGp(ElementConditionDto dto) {
		// TCJLODO Auto-generated method stub
		return (List) this.getSqlMapClientTemplate().queryForList(
				"VwBudgetGp.getVwBudgetGp", dto);
	}

	public VwBudgetGp findBudgetGpById(String id) {
		// TCJLODO Auto-generated method stub
		return (VwBudgetGp) this.getSqlMapClientTemplate().queryForObject(
				"VwBudgetGp.getVwBudgetGpById", id);
	}

}
