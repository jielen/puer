package com.ufgov.zc.client.sf.dataflow.nodebusiness;

import javax.swing.JComponent;

import com.ufgov.zc.client.sf.dataflow.ISfFlowNodeBusiness;
import com.ufgov.zc.client.sf.dataflow.SfDataFowPanel;
import com.ufgov.zc.client.sf.dataflow.SfFlowNode;
import com.ufgov.zc.client.sf.mobliemsg.ZcMobileMsgListPanel;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.system.RequestMeta;

public class SfMobileMsgNodeBusiness implements ISfFlowNodeBusiness {

	  String compoId = "SF_MOBILE_MSG";


	  @Override
	  public void excute(SfDataFowPanel flowPanel, SfFlowNode node, SfEntrust entrust, RequestMeta meta) {
	    // TCJLODO Auto-generated method stub
	    String compoId = "SF_MOBILE_MSG";
	    JComponent component = flowPanel.getTabComponent(compoId);
	    if (component != null) {
	      flowPanel.setSelectedTab(compoId);
	    } else {         
	          ZcMobileMsgListPanel listPanel = new ZcMobileMsgListPanel();
	          flowPanel.addTab(listPanel, compoId);
	         
	   }
	   flowPanel.setSelectedTab(compoId);
	     
	  }
	  
	 
	  @Override
	  public boolean isEnable(SfEntrust entrust, RequestMeta meta) {
	    // TCJLODO Auto-generated method stub 
	    return true;
	  }

	  
	}

