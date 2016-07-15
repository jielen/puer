package com.ufgov.zc.common.zc.publish;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcDdcgHt;

public interface IZcDdcgHtServiceDelegate extends Publishable {

  public List getZcDdcgHt(ElementConditionDto dto, RequestMeta meta) throws SQLException;

  public ZcDdcgHt selectByPrimaryKey(String zcHtCode, RequestMeta requestMeta);

  public void deleteByPrimaryKeyFN(ZcDdcgHt zcDdcgHt, RequestMeta requestMeta) throws Exception;

  public ZcDdcgHt updateZcDdcgHtFN(ZcDdcgHt zcDdcgHt, RequestMeta requestMeta) throws Exception;

}
