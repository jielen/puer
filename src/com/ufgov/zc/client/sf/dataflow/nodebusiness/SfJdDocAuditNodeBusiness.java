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
import com.ufgov.zc.client.sf.jddocaudit.SfJdDocAuditEditPanel;
import com.ufgov.zc.client.sf.jddocaudit.SfJdDocAuditListPanel;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfJdDocAuditNodeBusiness implements ISfFlowNodeBusiness {

  IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
    "zcEbBaseServiceDelegate");

  String compoId = "SF_MATERIALS_TRANSFER";

  @Override
  public void excute(SfDataFowPanel flowPanel, SfFlowNode node, SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    String compoId = "SF_JD_DOC_AUDIT";
    JComponent component = flowPanel.getTabComponent(compoId);
    if (component != null) {
      flowPanel.setSelectedTab(compoId);
    } else {
      List<SfJdDocAudit> billLst = getDataLst(entrust.getEntrustId(), meta);
      if (billLst == null || billLst.size() == 0) {
        List<SfJdDocAudit> lst = new ArrayList<SfJdDocAudit>();
        ListCursor lstCursor = new ListCursor(lst, -1);
        SfJdDocAudit currentBill = new SfJdDocAudit();
        currentBill.setEntrust(entrust);
        currentBill.setName(entrust.getName() + "司法鉴定文书审批单");
        currentBill.setWtf(entrust.getEntrustor() == null ? null : entrust.getEntrustor().getName());

        SfJdResult result = (SfJdResult) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfJdResultMapper.selectByEntrustId",
          entrust.getEntrustId(), meta);
        if (result != null) {
          currentBill.setReportType(result.getResultType());
        }

        lstCursor.getDataList().add(currentBill);
        lstCursor.setCurrentObject(currentBill);
        SfJdDocAuditEditPanel editPanel = new SfJdDocAuditEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
        flowPanel.addTab(editPanel, compoId);
      } else {
        if (billLst.size() == 1) {//加载编辑界面
          List<SfJdDocAudit> lst = new ArrayList<SfJdDocAudit>();
          ListCursor lstCursor = new ListCursor(lst, -1);
          lstCursor.getDataList().addAll(billLst);
          SfJdDocAudit e = billLst.get(0);
          lstCursor.setCurrentObject(e);
          SfJdDocAuditEditPanel editPanel = new SfJdDocAuditEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
          flowPanel.addTab(editPanel, compoId);
        } else {//加载列表界面          
          SfJdDocAuditListPanel listPanel = new SfJdDocAuditListPanel();
          listPanel.setEntrust(entrust);
          flowPanel.addTab(listPanel, compoId);
        }
      }
      flowPanel.setSelectedTab(compoId);
    }
  }

  private List<SfJdDocAudit> getDataLst(BigDecimal entrustId, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("" + entrustId.intValue());
    List<SfJdDocAudit> billLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdDocAuditMapper.selectMainDataLst", dto,
      meta);
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
