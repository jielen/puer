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
import com.ufgov.zc.client.sf.receipt.SfReceiptEditPanel;
import com.ufgov.zc.client.sf.receipt.SfReceiptListPanel;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfReceipt;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfReceiptNodeBusiness implements ISfFlowNodeBusiness {

  String compoId = "SF_RECEIPT";

  @Override
  public void excute(SfDataFowPanel flowPanel, SfFlowNode node, SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    JComponent component = flowPanel.getTabComponent(compoId);
    if (component != null) {
      flowPanel.setSelectedTab(compoId, component);
    } else {
      List<SfReceipt> billLst = getDataLst(entrust.getEntrustId(), meta);
      if (billLst == null || billLst.size() == 0) {
        List<SfReceipt> lst = new ArrayList<SfReceipt>();
        ListCursor lstCursor = new ListCursor(lst, -1);
        SfReceipt agreement = new SfReceipt();
        agreement.setEntrustCode(entrust.getCode());
        agreement.setEntrustId(entrust.getEntrustId());
        agreement.setEntrust(entrust);
        lstCursor.getDataList().add(agreement);
        lstCursor.setCurrentObject(agreement);
        SfReceiptEditPanel editPanel = new SfReceiptEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
        flowPanel.addTab(editPanel, compoId);
        //editPanel.refreshWordPanel();
      } else {
        if (billLst.size() == 1) {//加载编辑界面
          List<SfReceipt> lst = new ArrayList<SfReceipt>();
          ListCursor lstCursor = new ListCursor(lst, -1);
          lstCursor.getDataList().addAll(billLst);
          SfReceipt e = billLst.get(0);
          lstCursor.setCurrentObject(e);
          SfReceiptEditPanel editPanel = new SfReceiptEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
          flowPanel.addTab(editPanel, compoId);
          //editPanel.refreshWordPanel();
        } else {//加载列表界面          
          SfReceiptListPanel listPanel = new SfReceiptListPanel(entrust);
          flowPanel.addTab(listPanel, compoId);
          flowPanel.setSelectedTab(compoId);
        }
      }
    }
  }

  private List<SfReceipt> getDataLst(BigDecimal entrustId, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
      "zcEbBaseServiceDelegate");
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("" + entrustId.intValue());
    List<SfReceipt> evalst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfReceiptMapper.selectMainDataLst", dto, meta);
    return evalst;
  }

  @Override
  public boolean isEnable(SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
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
}
