package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfSjSupplier;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfSjSupplierMapper;
import com.ufgov.zc.server.sf.service.ISfSjSupplierService;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SfSjSupplierService implements ISfSjSupplierService {

  private IZcEbBaseService zcEbBaseService;
  
  private SfSjSupplierMapper sfSjSupplierMapper;
  
  public List getMainLst(ElementConditionDto dto, RequestMeta requestMeta) {
    List rtn=sfSjSupplierMapper.getMainLst(dto);
    return rtn;
  }

  
  public SfSjSupplier selectByPrimaryKey(BigDecimal supplierId, RequestMeta requestMeta) {
    return sfSjSupplierMapper.selectByPrimaryKey(supplierId);
  }

  
  public SfSjSupplier saveFN(SfSjSupplier record, RequestMeta requestMeta) throws SfBusinessException {
    if(sameName(record)){
      throw new SfBusinessException(record.getName()+"已经存在，请重新输入名称。");
    }
    if(record.getSupplierId()==null){
      ZcSUtil su=new ZcSUtil();
          BigDecimal id = new BigDecimal(su.getNextVal(SfSjSupplier.SEQ_SF_SJ_SUPPLIER));
          record.setSupplierId(id); 
          sfSjSupplierMapper.insert(record);          
    }else{
      sfSjSupplierMapper.updateByPrimaryKey(record);
    }
    return record; 
  }

  private boolean sameName(SfSjSupplier bill) { 
    ElementConditionDto dto=new ElementConditionDto();
    dto.setDattr1(bill.getName());
    dto.setDattr2(bill.getSupplierType());
    dto.setSfId(bill.getSupplierId());
    dto.setCoCode(bill.getCoCode());
    List  b=zcEbBaseService.queryDataForList("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.selectByName", dto);
    if(b!=null && b.size()>0){
      return true;
    }
 return false;
}
  
  public void deleteByPrimaryKeyFN(BigDecimal supplierId, RequestMeta requestMeta) throws SfBusinessException {
    if(isUsing(supplierId, requestMeta)){
      throw new SfBusinessException("已经被其他业务数据引用，不能删除。");
    }
    sfSjSupplierMapper.deleteByPrimaryKey(supplierId);
  }

  
  public boolean isUsing(BigDecimal supplierId, RequestMeta requestMeta) {

    ElementConditionDto dto = new ElementConditionDto();
    dto.setSfId(supplierId);
    List usingLst = zcEbBaseService.queryDataForList("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.selectUsingById", dto);

    if (usingLst != null && usingLst.size() > 0) {
      
      return true;
    }
    return false;
  }

 


  public SfSjSupplierMapper getSfSjSupplierMapper() {
    return sfSjSupplierMapper;
  }


  public void setSfSjSupplierMapper(SfSjSupplierMapper sfSjSupplierMapper) {
    this.sfSjSupplierMapper = sfSjSupplierMapper;
  }


  public IZcEbBaseService getZcEbBaseService() {
    return zcEbBaseService;
  }


  public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
    this.zcEbBaseService = zcEbBaseService;
  }

}
