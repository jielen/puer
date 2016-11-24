package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfSjSupplier;
import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfSjSupplierServiceDelegate extends Publishable {

  List getMainLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfSjSupplier selectByPrimaryKey(BigDecimal supplierId, RequestMeta requestMeta);

  SfSjSupplier saveFN(SfSjSupplier inData, RequestMeta requestMeta) throws SfBusinessException;

  void deleteByPrimaryKeyFN(BigDecimal supplierId, RequestMeta requestMeta) throws SfBusinessException ;

  boolean isUsing(BigDecimal supplierId, RequestMeta requestMeta);
}
