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
import com.ufgov.zc.client.sf.sfdocsend.SfDocSendEditPanel;
import com.ufgov.zc.client.sf.sfdocsend.SfDocSendListPanel;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.sf.model.SfDocSendDetail;
import com.ufgov.zc.common.sf.model.SfDocSendMaterial;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdDocAuditDetail;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.common.sf.publish.ISfJdDocAuditServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfDocSendNodeBusiness  implements ISfFlowNodeBusiness {

  IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
    "zcEbBaseServiceDelegate");

  String compoId = "SF_DOC_SEND";

  @Override
  public void excute(SfDataFowPanel flowPanel, SfFlowNode node, SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    String compoId = "SF_DOC_SEND";
    JComponent component = flowPanel.getTabComponent(compoId);
    if (component != null) {
      flowPanel.setSelectedTab(compoId);
    } else {
      List<SfDocSend> billLst = getDataLst(entrust.getEntrustId(), meta);
      if (billLst == null || billLst.size() == 0) {
        List<SfDocSend> lst = new ArrayList<SfDocSend>();
        ListCursor lstCursor = new ListCursor(lst, -1);
        SfDocSend currentBill = new SfDocSend();
        currentBill.setEntrust(entrust); 
        ElementConditionDto dto=new ElementConditionDto();
        dto.setNd(meta.getSvNd());
        dto.setCoCode(meta.getSvCoCode());
        dto.setSfId(entrust.getEntrustId());
        List jdLst=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdDocAuditMapper.selectMainDataLst", dto, meta);
        SfJdDocAudit da=new SfJdDocAudit();
        if(jdLst!=null && jdLst.size()>0){
          da=(SfJdDocAudit) jdLst.get(0);
          ISfJdDocAuditServiceDelegate sfJdDocAuditServiceDelegate = (ISfJdDocAuditServiceDelegate) ServiceFactory.create(ISfJdDocAuditServiceDelegate.class, "sfJdDocAuditServiceDelegate");
          da=sfJdDocAuditServiceDelegate.selectByPrimaryKey(da.getJdDocAuditId(), meta);
        }
        currentBill.setEntrust(da.getEntrust()); 
        currentBill.setJdDocAuditId(da.getJdDocAuditId()); 
        
        currentBill.getDocDetailLst().clear(); 
        if (da.getDetailLst()!= null) {
          for (int i = 0; i < da.getDetailLst().size(); i++) {
            SfJdDocAuditDetail m = (SfJdDocAuditDetail) da.getDetailLst().get(i);
            SfDocSendDetail d=new SfDocSendDetail();
            d.setDocAuditDetail(m); 
//            d.setProcessingMan(entrust.getJdFzr());
            currentBill.getDocDetailLst().add(d);
          }
        } 

        currentBill.getMaterialLst().clear();
        if(da.getMaterialLst()!=null){
          for (int i = 0; i < da.getMaterialLst().size(); i++) {
            SfMaterialsTransferDetail mt=(SfMaterialsTransferDetail)da.getMaterialLst().get(i);
            SfDocSendMaterial sm=new SfDocSendMaterial();
            sm.setMaterialTransfer(mt);
            sm.setProcessing(mt.getProcessing());
            sm.setRemark(mt.getRemark());
            currentBill.getMaterialLst().add(sm);
          }            
        }
        
        lstCursor.getDataList().add(currentBill);
        lstCursor.setCurrentObject(currentBill);
        SfDocSendEditPanel editPanel = new SfDocSendEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
        flowPanel.addTab(editPanel, compoId);
      } else {
        if (billLst.size() == 1) {//加载编辑界面
          List<SfDocSend> lst = new ArrayList<SfDocSend>();
          ListCursor lstCursor = new ListCursor(lst, -1);
          lstCursor.getDataList().addAll(billLst);
          SfDocSend e = billLst.get(0);
          lstCursor.setCurrentObject(e);
          SfDocSendEditPanel editPanel = new SfDocSendEditPanel(flowPanel.getParentDlg(), lstCursor, null, null);
          flowPanel.addTab(editPanel, compoId);
        } else {//加载列表界面          
          SfDocSendListPanel listPanel = new SfDocSendListPanel();
          listPanel.setEntrust(entrust);
          flowPanel.addTab(listPanel, compoId);
        }
      }
      flowPanel.setSelectedTab(compoId);
    }
  }

  private List<SfDocSend> getDataLst(BigDecimal entrustId, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    ElementConditionDto dto = new ElementConditionDto();
    dto.setSfId(entrustId);
    List<SfDocSend> billLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfDocSendMapper.selectMainDataLst", dto,meta);
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