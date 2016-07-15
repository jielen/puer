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
import com.ufgov.zc.client.sf.evaluation.SfEvaluationEditPanel;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEvaluation;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfEvaluationNodeBusiness implements ISfFlowNodeBusiness {

  String compoId = "SF_EVALUATION";

  @Override
  public void excute(SfDataFowPanel flowPanel, SfFlowNode node, SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    JComponent component = flowPanel.getTabComponent(compoId);
    if (component != null) {
      flowPanel.setSelectedTab(compoId);
    } else {
      List<SfEvaluation> evalst = getEvalution(entrust.getEntrustId(), meta);
      if (evalst == null || evalst.size() == 0) {
        if (SfUtil.canNew(compoId, entrust, meta)) {
          List<SfEvaluation> lst = new ArrayList<SfEvaluation>();
          ListCursor lstCursor = new ListCursor(lst, -1);
          SfEvaluation e = new SfEvaluation();
          e.setEntrustCode(entrust.getCode());
          e.setEntrustId(entrust.getEntrustId());
          e.setName(entrust.getName() + "委托评审记录");
          e.setIsAccept(entrust.getIsAccept());
          e.setEntrust(entrust);
          lstCursor.getDataList().add(e);
          lstCursor.setCurrentObject(e);
          SfEvaluationEditPanel editPanel = new SfEvaluationEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
          flowPanel.addTab(editPanel, compoId);
        }
      } else {
        List<SfEvaluation> lst = new ArrayList<SfEvaluation>();
        ListCursor lstCursor = new ListCursor(lst, -1);
        lstCursor.getDataList().addAll(evalst);
        SfEvaluation e = evalst.get(0);
        lstCursor.setCurrentObject(e);
        SfEvaluationEditPanel editPanel = new SfEvaluationEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
        flowPanel.addTab(editPanel, compoId);
      }
      flowPanel.setSelectedTab(compoId);
    }
  }

  private List<SfEvaluation> getEvalution(BigDecimal entrustId, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
      "zcEbBaseServiceDelegate");
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("" + entrustId.intValue());
    List<SfEvaluation> evalst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfEvaluationMapper.selectEvaluationLst", dto,
      meta);
    return evalst;
  }

  @Override
  public boolean isEnable(SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    List<SfEvaluation> evalst = getEvalution(entrust.getEntrustId(), meta);
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
