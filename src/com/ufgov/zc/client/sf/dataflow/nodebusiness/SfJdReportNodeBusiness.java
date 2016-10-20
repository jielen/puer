/**
 * 
 */
package com.ufgov.zc.client.sf.dataflow.nodebusiness;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.sf.dataflow.ISfFlowNodeBusiness;
import com.ufgov.zc.client.sf.dataflow.SfDataFowPanel;
import com.ufgov.zc.client.sf.dataflow.SfFlowNode;
import com.ufgov.zc.client.sf.jdresult.SfJdRecordEditPanel;
import com.ufgov.zc.client.sf.jdresult.SfJdRecordListPanel;
import com.ufgov.zc.client.sf.report.SfJdReportEditPanel;
import com.ufgov.zc.client.sf.report.SfJdReportListPanel;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class SfJdReportNodeBusiness implements ISfFlowNodeBusiness {

	  String compoId = "SF_JD_REPORT";
 

	  @Override
	  public void excute(SfDataFowPanel flowPanel, SfFlowNode node, SfEntrust entrust, RequestMeta meta) {
	    // TCJLODO Auto-generated method stub
	    String compoId = "SF_JD_REPORT";
	    JComponent component = flowPanel.getTabComponent(compoId);
	    if (component != null) {
	      flowPanel.setSelectedTab(compoId);
	    } else {
	      List<SfJdReport> billLst = getDataLst(entrust.getEntrustId(), meta);
	      if (billLst == null || billLst.size() == 0) {
	        List<SfJdReport> lst = new ArrayList<SfJdReport>();
	        ListCursor lstCursor = new ListCursor(lst, -1);
	        SfJdReport r = new SfJdReport();
	        r.setEntrustCode(entrust.getCode());
	        r.setEntrustId(entrust.getEntrustId());
	        r.setName(entrust.getName() + "鉴定结果"); 
	        r.setEntrust(entrust);
	        lstCursor.getDataList().add(r);
	        lstCursor.setCurrentObject(r);
	        SfJdReportEditPanel editPanel = new SfJdReportEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
	        flowPanel.addTab(editPanel, compoId);
	      } else {
	        if (billLst.size() == 1) {//加载编辑界面
	          List<SfJdReport> lst = new ArrayList<SfJdReport>();
	          ListCursor lstCursor = new ListCursor(lst, -1);
	          lstCursor.getDataList().addAll(billLst);
	          SfJdReport e = billLst.get(0);
	          lstCursor.setCurrentObject(e);
	          SfJdReportEditPanel editPanel = new SfJdReportEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
	          flowPanel.addTab(editPanel, compoId);
	        } else {//加载列表界面          
	          SfJdReportListPanel listPanel = new SfJdReportListPanel(entrust);
	          flowPanel.addTab(listPanel, compoId);
	        }
	      }
	      flowPanel.setSelectedTab(compoId);
	    }
	  }
	  
	  private List<SfJdReport> getDataLst(BigDecimal entrustId, RequestMeta meta) {
	    // TCJLODO Auto-generated method stub
	    IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
	      "zcEbBaseServiceDelegate");
	    ElementConditionDto dto = new ElementConditionDto();
	    dto.setSfId(entrustId);
	    List<SfJdReport> billLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdReportMapper.selectMainDataLst", dto, meta);
	    return billLst;
	  }

	  @Override
	  public boolean isEnable(SfEntrust entrust, RequestMeta meta) {
	    // TCJLODO Auto-generated method stub
	    if (!isEnougthCondition(entrust)) {
	      return false;
	    }
	    List evalst = getDataLst(entrust.getEntrustId(), meta);
	    if (evalst != null && evalst.size() > 0) {
	      if (SfUtil.haveFunc(compoId, entrust, SfElementConstants.FUNC_WATCH, meta)) {
	        return true;
	      }
	    } else {
	      if (SfUtil.canNew(compoId, entrust, meta)) {
	        return true;
	      }
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

