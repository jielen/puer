/**
 * 
 */
package com.ufgov.zc.client.sf.dataflow;

import java.math.BigDecimal;

import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfMajorServiceDelegate;

/**
 * @author Administrator
 *
 */
public class SfDataFlowUtil {
  
  public static SfEntrust getEntrust(BigDecimal entrustId){

    ISfEntrustServiceDelegate entrustService = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");
    return entrustService.selectByPrimaryKey(entrustId, WorkEnv.getInstance().getRequestMeta());
    
  }
}
