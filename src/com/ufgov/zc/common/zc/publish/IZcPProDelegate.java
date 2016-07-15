package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPPro;

public interface IZcPProDelegate extends Publishable {

	public void deleteZcPPro(String id, RequestMeta requestMeta)
			throws Exception;

	public void updateZcPPro(ZcPPro zcPPro, RequestMeta requestMeta)
			throws Exception;

	public void saveZcPPro(ZcPPro zcPPro, RequestMeta requestMeta)
			throws Exception;

	public List getZcPPro(ElementConditionDto elementConditionDto,
			RequestMeta requestMeta) throws Exception;

	public ZcPPro getZcPProById(String id, RequestMeta requestMeta)
			throws Exception;
}
