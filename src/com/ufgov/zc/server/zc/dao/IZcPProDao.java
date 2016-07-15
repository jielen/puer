package com.ufgov.zc.server.zc.dao;

import java.util.List;

import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPPro;

public interface IZcPProDao {

	public void deleteZcPPro(String id) throws Exception;

	public void updateZcPPro(ZcPPro zcPPro) throws Exception;

	public void saveZcPPro(ZcPPro zcPPro) throws Exception;

	public List getZcPPro(ElementConditionDto elementConditionDto) throws Exception;

	public ZcPPro getZcPProById(String id) throws Exception;

  public int checkUsed(String id) throws Exception;
}
