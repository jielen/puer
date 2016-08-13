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
import com.ufgov.zc.client.sf.materialsTransfer.SfMaterialsTransferEditPanel;
import com.ufgov.zc.client.sf.materialsTransfer.SfMaterialsTransferListPanel;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfMaterialsTransfer;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.common.sf.model.SfOutInfoDetail;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfMaterialsTransferNodeBusiness implements ISfFlowNodeBusiness {

  String compoId = "SF_MATERIALS_TRANSFER";

  @Override
  public void excute(SfDataFowPanel flowPanel, SfFlowNode node, SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    JComponent component = flowPanel.getTabComponent(compoId);
    if (component != null) {
      flowPanel.setSelectedTab(compoId);
    } else {
      List<SfMaterialsTransfer> billLst = getDataLst(entrust.getEntrustId(), meta);
      if (billLst == null || billLst.size() == 0) {
        List<SfMaterialsTransfer> lst = new ArrayList<SfMaterialsTransfer>();
        ListCursor lstCursor = new ListCursor(lst, -1);
        SfMaterialsTransfer m = new SfMaterialsTransfer();
        m.setEntrustCode(entrust.getCode());
        m.setEntrustId(entrust.getEntrustId());
        m.setName(entrust.getName() + "鉴定材料流转");
        m.setDetailLst(initTransferDetail(entrust, meta));
        lstCursor.getDataList().add(m);
        lstCursor.setCurrentObject(m);
        SfMaterialsTransferEditPanel editPanel = new SfMaterialsTransferEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
        flowPanel.addTab(editPanel, compoId);
      } else {
        if (billLst.size() == 1) {//加载编辑界面
          List<SfMaterialsTransfer> lst = new ArrayList<SfMaterialsTransfer>();
          ListCursor lstCursor = new ListCursor(lst, -1);
          lstCursor.getDataList().addAll(billLst);
          SfMaterialsTransfer e = billLst.get(0);
          lstCursor.setCurrentObject(e);
          SfMaterialsTransferEditPanel editPanel = new SfMaterialsTransferEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
          flowPanel.addTab(editPanel, compoId);
        } else {//加载列表界面          
          SfMaterialsTransferListPanel listPanel = new SfMaterialsTransferListPanel(entrust);
          flowPanel.addTab(listPanel, compoId);
        }
      }
      flowPanel.setSelectedTab(compoId);
    }
  }

  /**
   * 选择委托时，将其相关的鉴定材料、外部信息组装成流转明细
   * @param entrust
   * @return
   */
  protected List initTransferDetail(SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    List rtn = new ArrayList();

    IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
      "zcEbBaseServiceDelegate");

    List materialLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfMaterialsMapper.selectByEntrustId",
      entrust.getEntrustId(), meta);
    if (materialLst != null) {
      for (int i = 0; i < materialLst.size(); i++) {
        SfMaterials m = (SfMaterials) materialLst.get(i);
        SfMaterialsTransferDetail d = new SfMaterialsTransferDetail();
        d.setProcessing(SfMaterialsTransferDetail.HANDLE_STATUS_NEI_BU_LIU_ZHUAN);
        d.setQuantity3(m.getQuantity3());
        d.setUnit(m.getUnit());
        d.setMaterial(m);
        rtn.add(d);
      }
    }
    ElementConditionDto dto = new ElementConditionDto();
    dto.setSfId(entrust.getEntrustId());
    List outInfoLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfOutInfoDetailMapper.selectByEntrustId", dto, meta);
    if (outInfoLst != null) {
      for (int i = 0; i < outInfoLst.size(); i++) {
        SfOutInfoDetail m = (SfOutInfoDetail) outInfoLst.get(i);
        SfMaterialsTransferDetail d = new SfMaterialsTransferDetail();
        d.setProcessing(SfMaterialsTransferDetail.HANDLE_STATUS_NEI_BU_LIU_ZHUAN);
        d.setQuantity3(""+(m.getQuantity()==null?0:m.getQuantity().doubleValue()));
        d.setUnit(m.getUnit());
        d.setOutInfoDetail(m);
        rtn.add(d);
      }
    }
    return rtn;
  }

  private List<SfMaterialsTransfer> getDataLst(BigDecimal entrustId, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
      "zcEbBaseServiceDelegate");
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("" + entrustId.intValue());
    List<SfMaterialsTransfer> billLst = zcEbBaseServiceDelegate.queryDataForList(
      "com.ufgov.zc.server.sf.dao.SfMaterialsTransferMapper.selectMainDataLst", dto, meta);
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
