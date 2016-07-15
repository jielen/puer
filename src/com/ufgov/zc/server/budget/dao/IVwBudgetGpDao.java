package com.ufgov.zc.server.budget.dao;

import java.util.List;

import com.ufgov.zc.common.budget.model.VwBudgetGp;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface IVwBudgetGpDao {

	public List findBudgetGp(ElementConditionDto dto);

	public VwBudgetGp findBudgetGpById(String id);
}
