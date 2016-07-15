/**
 * 
 */
package com.ufgov.zc.client.sf.dataflow.nodebusiness;

import javax.swing.JComponent;

import com.ufgov.zc.client.sf.dataflow.ISfFlowNodeBusiness;
import com.ufgov.zc.client.sf.dataflow.SfDataFowPanel;
import com.ufgov.zc.client.sf.dataflow.SfFlowNode;
import com.ufgov.zc.client.sf.jdfees.SfJdFeeMainPanel;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;

/**
 * @author Administrator
 *
 */
public class SfJdFeeNodeBusiness implements ISfFlowNodeBusiness {

  String compoId = "SF_JD_FEES";

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.sf.dataflow.ISfFlowNodeBusiness#excute(com.ufgov.zc.client.sf.dataflow.SfDataFowPanel, com.ufgov.zc.client.sf.dataflow.SfFlowNode, com.ufgov.zc.common.sf.model.SfEntrust, com.ufgov.zc.common.system.RequestMeta)
   */
  @Override
  public void excute(SfDataFowPanel flowPanel, SfFlowNode node, SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    JComponent component = flowPanel.getTabComponent(compoId);
    if (component != null) {
      flowPanel.setSelectedTab(compoId);
    } else {
      SfJdFeeMainPanel jdFeeMainPanel = new SfJdFeeMainPanel(entrust, true);
      flowPanel.addTab(jdFeeMainPanel, compoId);
      flowPanel.setSelectedTab(compoId);
    }
  }

  @Override
  public boolean isEnable(SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    if (!isEnougthCondition(entrust)) {
      return false;
    }
    if (SfUtil.haveFunc(compoId, entrust, SfElementConstants.FUNC_WATCH, meta) || SfUtil.canNew(compoId, entrust, meta)) {
      return true;
    }
    return false;
  }

  /**
   * 检查其业务前提条件是否满足
   * @param entrust
   * @return
   */
  private boolean isEnougthCondition(SfEntrust entrust) {
    // TCJLODO Auto-generated method stub
    if (SfElementConstants.VAL_Y.equals(entrust.getIsAccept())) {
      return true;
    }
    return false;
  }

}
