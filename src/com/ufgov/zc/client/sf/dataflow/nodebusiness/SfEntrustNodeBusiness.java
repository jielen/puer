/**
 * 
 */
package com.ufgov.zc.client.sf.dataflow.nodebusiness;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.sf.dataflow.ISfFlowNodeBusiness;
import com.ufgov.zc.client.sf.dataflow.SfDataFowPanel;
import com.ufgov.zc.client.sf.dataflow.SfFlowNode;
import com.ufgov.zc.client.sf.entrust.SfEntrustEditPanel;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;

/**
 * @author Administrator
 *
 */
public class SfEntrustNodeBusiness implements ISfFlowNodeBusiness {

 
  @Override
  public void excute(SfDataFowPanel flowPanel, SfFlowNode node, SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
   String compoId="SF_ENTRUST";
    JComponent component=flowPanel.getTabComponent(compoId);
    if(component!=null){
      flowPanel.setSelectedTab(compoId);
    }else{
      
      List<SfEntrust> lst=new ArrayList<SfEntrust>();
      ListCursor lstCursor=new ListCursor(lst, -1);
      lstCursor.getDataList().add(entrust);
      lstCursor.setCurrentObject(entrust);
      SfEntrustEditPanel editPanel=new SfEntrustEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
      //SfEntrustEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfEntrustListPanel listPanel) 
      flowPanel.addTab(editPanel, compoId);
      flowPanel.setSelectedTab(compoId);
    }
  }


  @Override
  public boolean isEnable(SfEntrust entrust,RequestMeta meta) {
    // TCJLODO Auto-generated method stub
   return true;
  }
}
