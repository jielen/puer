package com.ufgov.zc.client.sf.dataflow.nodebusiness;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.sf.agreement.SfAgreementEditPanel;
import com.ufgov.zc.client.sf.dataflow.ISfFlowNodeBusiness;
import com.ufgov.zc.client.sf.dataflow.SfDataFowPanel;
import com.ufgov.zc.client.sf.dataflow.SfFlowNode;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.common.sf.model.SfAgreement;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfAgreementNodeBusiness implements ISfFlowNodeBusiness {

  String compoId = "SF_AGREEMENT";

  @Override
  public void excute(SfDataFowPanel flowPanel, SfFlowNode node, SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    JComponent component = flowPanel.getTabComponent(compoId);
    if (component != null) {
      flowPanel.setSelectedTab(compoId, component);
    } else {
      List<SfAgreement> evalst = getDataLst(entrust.getEntrustId(), meta);
      if (evalst == null || evalst.size() == 0) {
        List<SfAgreement> lst = new ArrayList<SfAgreement>();
        ListCursor lstCursor = new ListCursor(lst, -1);
        SfAgreement agreement = new SfAgreement();
        agreement.setEntrustCode(entrust.getCode());
        agreement.setEntrustId(entrust.getEntrustId());
        lstCursor.getDataList().add(agreement);
        lstCursor.setCurrentObject(agreement);
        SfAgreementEditPanel editPanel = new SfAgreementEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
        flowPanel.addTab(editPanel, compoId);
        //editPanel.refreshWordPanel();
      } else {
        List<SfAgreement> lst = new ArrayList<SfAgreement>();
        ListCursor lstCursor = new ListCursor(lst, -1);
        lstCursor.getDataList().addAll(evalst);
        SfAgreement e = evalst.get(0);
        lstCursor.setCurrentObject(e);
        SfAgreementEditPanel editPanel = new SfAgreementEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
        flowPanel.addTab(editPanel, compoId);
        // editPanel.refreshWordPanel();
      }
    }
  }

  private List<SfAgreement> getDataLst(BigDecimal entrustId, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
      "zcEbBaseServiceDelegate");
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("" + entrustId.intValue());
    List<SfAgreement> evalst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfAgreementMapper.selectMainDataLst", dto, meta);
    return evalst;
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
