package com.ufgov.zc.server.zc.service;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcXmcgHt;
import com.ufgov.zc.common.zc.model.ZcXmcgHtChg;

public interface IZcXmcgHtChgService {

	public List getZcXmcgHtChg(ElementConditionDto dto, RequestMeta meta)
			throws Exception;

	public ZcXmcgHtChg updateZcXmcgHtChgFN(ZcXmcgHtChg zcXmcgHtChg,
			boolean flag, String serverAdd, RequestMeta requestMeta)
			throws Exception;

	public ZcXmcgHtChg selectByPrimaryKey(String zcHtChgId,
			RequestMeta requestMeta);

	public void deleteByPrimaryKeyFN(String zcHtChgId, boolean flag,
			String serverAdd, RequestMeta requestMeta) throws Exception;

	public ZcXmcgHt selectZcXmcgHtByPrimaryKey(String zcHtCode,
			RequestMeta requestMeta);

	public ZcXmcgHtChg newCommitFN(ZcXmcgHtChg ht, boolean flag,
			RequestMeta requestMeta) throws Exception;

	public ZcXmcgHtChg callbackFN(ZcXmcgHtChg ht, RequestMeta requestMeta);

	public ZcXmcgHtChg untreadFN(ZcXmcgHtChg ht, RequestMeta requestMeta);

	public ZcXmcgHtChg unAuditFN(ZcXmcgHtChg ht, RequestMeta requestMeta);

	public ZcXmcgHtChg auditFN(ZcXmcgHtChg ht, RequestMeta requestMeta);
}
