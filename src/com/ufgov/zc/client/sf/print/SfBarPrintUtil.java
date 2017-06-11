/**
 * 
 */
package com.ufgov.zc.client.sf.print;

import java.util.List;

import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.common.sf.model.SfPrintClient;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class SfBarPrintUtil {

  public SfPrintClient getCurrentPrintService() {     
      RequestMeta meta=WorkEnv.getInstance().getRequestMeta();
      ElementConditionDto dto = new ElementConditionDto();
      SfUtil su=new SfUtil();
      dto.setDattr1(su.getMac());
      IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
      List printList = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfPrintClientMapper.selectMainDataLst", dto, meta);
      if (printList == null || printList.size() == 0) { return null; }
      SfPrintClient curentPrint = (SfPrintClient) printList.get(0); 
      return curentPrint;
  }

}
