package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.publish.ISfJdPersonServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfJdPersonService;

public class SfJdPersonServiceDelegate implements ISfJdPersonServiceDelegate {

  private ISfJdPersonService jdPersonServic;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdPersonServic.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfJdPerson selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdPersonServic.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfJdPerson saveFN(SfJdPerson inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdPersonServic.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdPersonServic.deleteByPrimaryKeyFN(id, requestMeta);
  }


  public ISfJdPersonService getJdPersonServic() {
    return jdPersonServic;
  }


  public void setJdPersonServic(ISfJdPersonService jdPersonServic) {
    this.jdPersonServic = jdPersonServic;
  }

}
