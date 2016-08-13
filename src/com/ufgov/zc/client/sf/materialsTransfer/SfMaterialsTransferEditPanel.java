package com.ufgov.zc.client.sf.materialsTransfer;

import java.awt.Color;
import java.awt.Component;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.smartclient.component.table.fixedtable.JPageableFixedTable;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfMaterialsTransferToTableModelConverter;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.GkCommentDialog;
import com.ufgov.zc.client.component.GkCommentUntreadDialog;
import com.ufgov.zc.client.component.JFuncToolBar;
import com.ufgov.zc.client.component.JTablePanel;
import com.ufgov.zc.client.component.button.AddButton;
import com.ufgov.zc.client.component.button.CallbackButton;
import com.ufgov.zc.client.component.button.DeleteButton;
import com.ufgov.zc.client.component.button.EditButton;
import com.ufgov.zc.client.component.button.ExitButton;
import com.ufgov.zc.client.component.button.FuncButton;
import com.ufgov.zc.client.component.button.ImportButton;
import com.ufgov.zc.client.component.button.NextButton;
import com.ufgov.zc.client.component.button.PreviousButton;
import com.ufgov.zc.client.component.button.PrintButton;
import com.ufgov.zc.client.component.button.SaveButton;
import com.ufgov.zc.client.component.button.SendButton;
import com.ufgov.zc.client.component.button.SendGkButton;
import com.ufgov.zc.client.component.button.SubaddButton;
import com.ufgov.zc.client.component.button.SubdelButton;
import com.ufgov.zc.client.component.button.SubinsertButton;
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.button.zc.CommonButton;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.celleditor.DateCellEditor;
import com.ufgov.zc.client.component.table.celleditor.MoneyCellEditor;
import com.ufgov.zc.client.component.table.celleditor.TextCellEditor;
import com.ufgov.zc.client.component.table.cellrenderer.DateCellRenderer;
import com.ufgov.zc.client.component.table.cellrenderer.NumberCellRenderer;
import com.ufgov.zc.client.component.table.codecelleditor.AsValComboBoxCellEditor;
import com.ufgov.zc.client.component.table.codecellrenderer.AsValCellRenderer;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldCellEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.IntFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowUtil;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.util.SfJdPersonSelectHandler;
import com.ufgov.zc.client.sf.util.SfUserSelectHandler;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfMaterialsTransfer;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.common.sf.model.SfOutInfoDetail;
import com.ufgov.zc.common.sf.model.SfOutInfoType;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfMaterialsTransferServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.system.util.Utils;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfMaterialsTransferEditPanel  extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -4600017142926778017L;

  private static final Logger logger = Logger.getLogger(SfMaterialsTransferEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_MATERIALS_TRANSFER";

  protected FuncButton saveButton = new SaveButton();

  protected FuncButton addButton = new AddButton();

  protected FuncButton editButton = new EditButton();

  private FuncButton traceButton = new TraceButton();

  protected FuncButton callbackButton = new CallbackButton();

  protected FuncButton deleteButton = new DeleteButton();

  private FuncButton previousButton = new PreviousButton();

  private FuncButton nextButton = new NextButton();

  private FuncButton exitButton = new ExitButton();

  protected FuncButton sendButton = new SendButton();

  public FuncButton printButton = new PrintButton();

  public FuncButton importButton = new ImportButton();

  //送国库
  private FuncButton sendGkButton = new SendGkButton();

  // 工作流填写意见审核通过
  protected FuncButton suggestPassButton = new SuggestAuditPassButton();

  // 工作流销审
  protected FuncButton unAuditButton = new UnauditButton();

  // 工作流退回
  protected FuncButton unTreadButton = new UntreadButton();
  
  //导入补充材料,用于流转时，有了新的补充鉴定材料、外部信息  
  private FuncButton tongbuButton=new CommonButton("ftongbu", "导入补充材料", "import.jpg", true);
  
  //接收后，如果需要接着移交，点击这个按钮  
  private FuncButton woyaodengjiBtn=new CommonButton("fwoyaodengji", "我要登记", "group_edit.png", true);

  protected ListCursor<SfMaterialsTransfer> listCursor;

  private SfMaterialsTransfer oldMaterialsTransfer;

  public SfMaterialsTransferListPanel listPanel;

  protected SfMaterialsTransferEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  protected JTablePanel materialTablePanel = new JTablePanel();
  
  protected JTablePanel outInfoTablePanel = new JTablePanel();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfMaterialsTransferServiceDelegate sfMaterialsTransferServiceDelegate;
  private ISfEntrustServiceDelegate sfEntrustServiceDelegate ;
  
  private ElementConditionDto reqDto=new ElementConditionDto();

  public SfMaterialsTransferEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfMaterialsTransferListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfMaterialsTransferEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfMaterialsTransferServiceDelegate = (ISfMaterialsTransferServiceDelegate) ServiceFactory.create(ISfMaterialsTransferServiceDelegate.class, "sfMaterialsTransferServiceDelegate");
    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class,"sfEntrustServiceDelegate");
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = parent;

    this.colCount = 3;

    init();

    requestMeta.setCompoId(getCompoId());

    refreshData();

    setButtonStatus();

    updateFieldEditorsEditable();
  }

  private void refreshData() {
    // TCJLODO Auto-generated method stub

    SfMaterialsTransfer bill = (SfMaterialsTransfer) listCursor.getCurrentObject();

    if (bill != null ) {
      if(bill.getTransferId() != null){//列表页面双击进入
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
        bill = sfMaterialsTransferServiceDelegate.selectByPrimaryKey(bill.getTransferId(), this.requestMeta);
        listCursor.setCurrentObject(bill);
        this.setEditingObject(bill);        
      }else if(bill.getEntrustId()!=null){
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
        setDefaultValue(bill);
        this.setEditingObject(bill);
      }
    } else {//新增按钮进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      bill = new SfMaterialsTransfer();
      setDefaultValue(bill);
      listCursor.getDataList().add(bill);
      listCursor.setCurrentObject(bill);
      this.setEditingObject(bill);
    }
    refreshSubData();
    setOldObject();
    setButtonStatus();
    updateFieldEditorsEditable();
  }

  private void setDefaultValue(SfMaterialsTransfer bill) {
    // TCJLODO Auto-generated method stub
    bill.setNd(this.requestMeta.getSvNd());
    bill.setStatus(SfMaterialsTransfer.STATUS_DAI_YI_JIAO);
    bill.setYiJiaoRen(this.requestMeta.getSvUserID());
  }

  private void refreshSubData() {
    // TCJLODO Auto-generated method stub
    refreshMaterialTableData();
    refreshOutInfoTableData();
  }
  private void refreshMaterialTableData() {
    SfMaterialsTransfer bill = (SfMaterialsTransfer) listCursor.getCurrentObject();
    materialTablePanel.setTableModel(SfMaterialsTransferToTableModelConverter.convertMaterialTableData(bill.getMaterialLst()));
    ZcUtil.translateColName(materialTablePanel.getTable(), SfMaterialsTransferToTableModelConverter.getMaterialInfo());
    setTableProperty(materialTablePanel.getTable());
  }
  
  private void refreshOutInfoTableData() {
    SfMaterialsTransfer bill = (SfMaterialsTransfer) listCursor.getCurrentObject();
    outInfoTablePanel.setTableModel(SfMaterialsTransferToTableModelConverter.convertOutInfoTableData(bill.getOutInfoLst()));
    ZcUtil.translateColName(outInfoTablePanel.getTable(), SfMaterialsTransferToTableModelConverter.getOutInfo());
    setTableProperty(outInfoTablePanel.getTable());
  }

  private void setTableProperty(final JPageableFixedTable table) {    
    table.setDefaultEditor(String.class, new TextCellEditor());

    SwingUtil.setTableCellEditor(table, SfMaterialsTransferDetail.COL_PROCESSING, new AsValComboBoxCellEditor(SfMaterialsTransferDetail.SF_VS_SF_MATERIALS_HANDLE_STATUS));
    SwingUtil.setTableCellRenderer(table, SfMaterialsTransferDetail.COL_PROCESSING, new AsValCellRenderer(SfMaterialsTransferDetail.SF_VS_SF_MATERIALS_HANDLE_STATUS));  

    SwingUtil.setTableCellEditor(table, SfMaterialsTransferDetail.COL_QUANTITY, new MoneyCellEditor(false));
    SwingUtil.setTableCellRenderer(table, SfMaterialsTransferDetail.COL_QUANTITY, new NumberCellRenderer());  

    SwingUtil.setTableCellEditor(table, SfMaterialsTransferDetail.COL_PROCESSING_DATE, new DateCellEditor());
    SwingUtil.setTableCellRenderer(table, SfMaterialsTransferDetail.COL_PROCESSING_DATE, new DateCellRenderer("YY-MM-DD")); 
    

    SfJdPersonSelectHandler personHandler = new SfJdPersonSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        BeanTableModel model = (BeanTableModel) table.getModel();
        int k = table.getSelectedRow();
        if (k < 0) {
          return;
        }

        int k2 = table.convertRowIndexToModel(k);
        SfMaterialsTransferDetail detail = (SfMaterialsTransferDetail) (model.getBean(k2));
        for (Object obj : selectedDatas) {
          User person = (User) obj;
          detail.setProcessingMan(person.getUserId());
          break;
        }
        model.fireTableRowsUpdated(k, k);
      }
    };

    ElementConditionDto dto = new ElementConditionDto();
    ForeignEntityFieldCellEditor foreignPersonEditor = new ForeignEntityFieldCellEditor(personHandler.getSqlId(), dto, 20, personHandler,
      personHandler.getColumNames(), "人员", "userName");
    SwingUtil.setTableCellEditor(table, SfOutInfoType.COL_OUT_INFO_TYPE_NAME, foreignPersonEditor);

  }


  protected void updateFieldEditorsEditable() {

    SfMaterialsTransfer qx = (SfMaterialsTransfer) listCursor.getCurrentObject();
    Long processInstId = qx.getProcessInstId();
    if (processInstId != null && processInstId.longValue() > 0) {
      // 工作流的单据
      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(qx, requestMeta);
      if ("cancel".equals(this.oldMaterialsTransfer.getStatus())) {// 撤销单据设置字段为不可编辑
        wfCanEditFieldMap = null;
      }

      for (AbstractFieldEditor editor : fieldEditors) {
        // 工作流中定义可编辑的字段
//        System.out.println(editor.getFieldName());
        if(editor instanceof NewLineFieldEditor)continue;
        if (wfCanEditFieldMap != null && wfCanEditFieldMap.containsKey(Utils.getDBColNameByFieldName(editor.getEditObject(), editor.getFieldName()))) {
          isEdit = true;
          this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
          if ("entrustCode".equals(editor.getFieldName()) && qx.getParentId()!=null){
            editor.setEnabled(false);            
          }{
            editor.setEnabled(true);
          }
        } else {
          editor.setEnabled(false);
        }
      }
      
      //工作流中该节点选中了保存按钮可用，则当前状态当前人可用编辑
      if (saveButton.isVisible() && saveButton.isEnabled()) {
        isEdit = true;
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
      }
      
    } else {
      
      for (AbstractFieldEditor editor : fieldEditors) {
        if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT) || pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
          if ("yiJiaoRenName".equals(editor.getFieldName())||"yiJiaoDate".equals(editor.getFieldName())||"status".equals(editor.getFieldName())
            ||"nd".equals(editor.getFieldName())) {
            editor.setEnabled(false);
          } else {
            if ("entrustCode".equals(editor.getFieldName()) && qx.getParentId()!=null){
              editor.setEnabled(false);
            }else{
              editor.setEnabled(true);
            }            
          }
          isEdit = true;
        } else {
          editor.setEnabled(false);
        }
      }
    }

    setWFSubTableEditable(materialTablePanel, isEdit);

    setWFSubTableEditable(outInfoTablePanel, isEdit);

  }

  protected void setButtonStatus() {
    SfMaterialsTransfer bill = (SfMaterialsTransfer) listCursor.getCurrentObject();
    setButtonStatus(bill, requestMeta, this.listCursor);
    
    if(SfMaterialsTransfer.STATUS_YI_JIE_SHOU.equals(bill.getStatus())){
      woyaodengjiBtn.setVisible(true);
      woyaodengjiBtn.setEnabled(true);
    }else{
      woyaodengjiBtn.setVisible(false);
      woyaodengjiBtn.setEnabled(false);      
    }

  }

  public void setButtonStatusWithoutWf() {
    super.setButtonStatusWithoutWf();
    if (this.btnStatusList.size() == 0) {

      ButtonStatus bs = new ButtonStatus();

      bs.setButton(this.addButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);
      
      bs = new ButtonStatus();

      bs.setButton(this.editButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(SfMaterialsTransfer.STATUS_DAI_YI_JIAO);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.saveButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_EDIT);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_NEW);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.exitButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_ALL);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.tongbuButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_EDIT);

      bs.addBillStatus(SfMaterialsTransfer.STATUS_DAI_YI_JIAO);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.woyaodengjiBtn);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(SfMaterialsTransfer.STATUS_YI_JIE_SHOU);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.sendButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_EDIT);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.suggestPassButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.callbackButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.unAuditButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(SfMaterialsTransfer.STATUS_YI_JIE_SHOU);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.unTreadButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      bs = new ButtonStatus();

      bs.setButton(this.printButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.sendGkButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_AUDITED);

      btnStatusList.add(bs);

      bs = new ButtonStatus();
      bs.setButton(this.importButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_EDIT);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_NEW);
      btnStatusList.add(bs);

    }

    SfMaterialsTransfer bill = (SfMaterialsTransfer) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, bill.getStatus(), this.pageStatus, getCompoId(), bill.getProcessInstId());

  }

  protected void setOldObject() {

    oldMaterialsTransfer = (SfMaterialsTransfer) ObjectUtil.deepCopy(listCursor.getCurrentObject());

  }

  public String getCompoId() {
    // TCJLODO Auto-generated method stub
    return compoId;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#initToolBar(com.ufgov.zc.client.component.JFuncToolBar)
   */
  @Override
  public void initToolBar(JFuncToolBar toolBar) {
    // TCJLODO Auto-generated method stub

    toolBar.setModuleCode("SF");

    toolBar.setCompoId(getCompoId());

//    toolBar.add(addButton);
    
    toolBar.add(editButton);

    toolBar.add(saveButton);

        toolBar.add(sendButton);
        sendButton.setText("移交");
        
        toolBar.add(woyaodengjiBtn);
        woyaodengjiBtn.setText("我要移交");
        woyaodengjiBtn.setToolTipText("对已接收的材料情况进行状态更新登记,准备移交给其他人，");

//        toolBar.add(saveAndSendButton);
        toolBar.add(suggestPassButton);
        suggestPassButton.setText("接收");

    //    toolBar.add(sendGkButton);

        toolBar.add(unAuditButton);
        
        toolBar.add(unTreadButton);

        toolBar.add(callbackButton);

        toolBar.add(deleteButton);

    //    toolBar.add(importButton);

        toolBar.add(traceButton);
        
        toolBar.add(tongbuButton);
        tongbuButton.setToolTipText("将追加、补充的鉴定材料、外部信息同步到当前的流转记录中，");

        toolBar.add(printButton);

    //    toolBar.add(previousButton);

    //    toolBar.add(nextButton);

    toolBar.add(exitButton);
    
    woyaodengjiBtn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doWoYaoDengJi();

      }

    });

    tongbuButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doTongBu();

      }

    });

    addButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doAdd();

      }

    });

    editButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doEdit();

      }

    });

    previousButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrevious();

      }

    });

    nextButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doNext();

      }

    });

    exitButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doExit();

      }

    });

    saveButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        doSave();

      }

    });

    deleteButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        doDelete();

      }

    });

    printButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrint();

      }

    });

    
    sendButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doSend();
      }
    });
    
    suggestPassButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doSuggestPass();
      }
    });
    
    callbackButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doCallback();
      }
    });
    
    unTreadButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doUnTread();
      }
    });
    
    unAuditButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doUnAudit();
      }
    });
    
    traceButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doTrace();
      }
    });
  }

  /**
   * 接收后，如果想继续移交到下一个环节，则通过这个方法，复制产生一个新的流转记录，id为空，移交人为当前登陆人，接收人、接收时间等为空，parent_id为上条记录的id
   */
  protected void doWoYaoDengJi() {
    // TCJLODO Auto-generated method stub
    SfMaterialsTransfer bill = (SfMaterialsTransfer) this.listCursor.getCurrentObject();
    //检查当前数据是否已经产生新的移交记录
    SfMaterialsTransfer obj=(SfMaterialsTransfer)zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfMaterialsTransferMapper.selectByParentId", bill.getTransferId(), requestMeta);
    if(obj!=null){
      JOptionPane.showMessageDialog(this, "当前记录已经流转走了，不能更新.", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    SfMaterialsTransfer newBill =(SfMaterialsTransfer) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    
    setDefaultValue(newBill);
    
    newBill.setParentId(bill.getTransferId());
    newBill.setTransferId(null);
    newBill.setProcessInstId(null);
    newBill.setJieShouDate(null);
    newBill.setJieShouRen(null);
    newBill.setYiJiaoDate(null);
    newBill.setYiJiaoRen(requestMeta.getSvUserID());
    newBill.setDbDigest(newBill.digest());
    listCursor.getDataList().add(newBill);
    this.listCursor.setCurrentObject(newBill);
    this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
    this.setEditingObject(newBill);
    refreshSubData();
    setOldObject();

    Component[] funcs = toolBar.getComponents();
    for (Component func : funcs) {
      func.setVisible(true);
      func.setEnabled(true);
    }
    setButtonStatus();
    updateFieldEditorsEditable();
  }

  /**
   * 导入补充材料,用于流转时，有了新的补充鉴定材料、外部信息  
   */
  protected void doTongBu() {
    // TCJLODO Auto-generated method stub
    SfMaterialsTransfer bill = (SfMaterialsTransfer) this.listCursor.getCurrentObject();
    if(bill.getParentId()==null && bill.getTransferId()==null)return;
    if(bill.getEntrustId()==null)return;
    
    List newD=initTransferDetail(bill.getEntrustId());
    SfMaterialsTransfer temp=new SfMaterialsTransfer();
    temp.setDetailLst(newD);
    
    List newMaterials=new ArrayList(),newOutInfos=new ArrayList();    
    
    for(int i=0;i<temp.getMaterialLst().size();i++){
      SfMaterialsTransferDetail d=(SfMaterialsTransferDetail)temp.getMaterialLst().get(i);
      boolean find=false;
      for(int j=0;j<bill.getMaterialLst().size();j++){
        SfMaterialsTransferDetail dd=(SfMaterialsTransferDetail)bill.getMaterialLst().get(j);
        if(d.getMaterialId().equals(dd.getMaterialId())){
          find=true;
          break;
        }
      }
      if(!find){
        newMaterials.add(d);
      }
    }
    
    for(int i=0;i<temp.getOutInfoLst().size();i++){
      SfMaterialsTransferDetail d=(SfMaterialsTransferDetail)temp.getOutInfoLst().get(i);
      boolean find=false;
      for(int j=0;j<bill.getOutInfoLst().size();j++){
        SfMaterialsTransferDetail dd=(SfMaterialsTransferDetail)bill.getOutInfoLst().get(j);
        if(d.getOutInfoDetailId().equals(dd.getOutInfoDetailId())){
          find=true;
          break;
        }
      }
      if(!find){
        newOutInfos.add(d);
      }
    }
   bill.getMaterialLst().addAll(newMaterials);
   bill.getOutInfoLst().addAll(newOutInfos);
   
   refreshSubData();   
  }

  protected void doAdd() {
    // TCJLODO Auto-generated method stub
    SfMaterialsTransfer bill=new SfMaterialsTransfer();
    setDefaultValue(bill);
    listCursor.getDataList().add(bill);
    listCursor.setCurrentObject(bill);
    refreshData();
    
  }

  protected void doPrevious() {

    if (isDataChanged()) {

      int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存", "保存确认", 0);

      if (num == JOptionPane.YES_OPTION) {

        if (!doSave()) {

          return;

        }

      } else {

        listCursor.setCurrentObject(oldMaterialsTransfer);

      }

    }

    listCursor.previous();

    refreshData();

  }

  protected void doNext() {

    if (isDataChanged()) {

      int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存", "保存确认", 0);

      if (num == JOptionPane.YES_OPTION) {

        if (!doSave()) {

          return;

        }

      } else {

        listCursor.setCurrentObject(oldMaterialsTransfer);

      }

    }

    listCursor.next();

    refreshData();

  }

  public boolean doSave() {

    if (!isDataChanged()) {

      JOptionPane.showMessageDialog(this, "数据没有发生改变，不用保存.", "提示", JOptionPane.INFORMATION_MESSAGE);

      return true;

    } 

    if (!checkBeforeSave()) {

      return false;

    }

    boolean success = true;

    String errorInfo = "";

    SfMaterialsTransfer bill = (SfMaterialsTransfer) this.listCursor.getCurrentObject();

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      bill = sfMaterialsTransferServiceDelegate.saveFN(bill, this.requestMeta);

      listCursor.setCurrentObject(bill);

    } catch (Exception e) {

      logger.error(e.getMessage(), e);

      success = false;

      errorInfo += e.getMessage();

    }

    if (success) {

      JOptionPane.showMessageDialog(this, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

      refreshListPanel();
      refreshData();
      updateDataFlowDialog();

    } else {

      JOptionPane.showMessageDialog(this, "保存失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

    }

    return success;

  }

  private void refreshListPanel() {
    if (listPanel != null) {
      listPanel.refreshCurrentTabData();
    }
  }

  /**
   * 更新数据流界面
   */
  private void updateDataFlowDialog() {
    // TCJLODO Auto-generated method stub
    SfMaterialsTransfer bill = (SfMaterialsTransfer) this.listCursor.getCurrentObject();
    if(listPanel!=null && listPanel.getParent() instanceof JClosableTabbedPane){
      return;
    }
    if (parent instanceof SfMaterialsTransferDialog) {//新增的，创建数据流界面
      SfDataFlowDialog d = new SfDataFlowDialog(compoId, SfDataFlowUtil.getEntrust(bill.getEntrustId()), listPanel);
      parent.dispose();
    } else {
      SfDataFlowDialog d = (SfDataFlowDialog) parent;
      d.refresh(bill.getEntrustId());
    }
  }

  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfMaterialsTransfer bill = (SfMaterialsTransfer) this.listCursor.getCurrentObject();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(bill, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString());
    }
    
   /* //去除空行
    List emptyLst=new ArrayList();
    for(int i=0;i<bill.getValidateDetailLst().size();i++){
      SfMaterialsTransferValidateDetail d=(SfMaterialsTransferValidateDetail) bill.getValidateDetailLst().get(i);      
      if(d.getInfoReq()==null && d.getInfoReq().getMaterialsTransferReqCode()==null){
        emptyLst.add(d);
      }
    }
    if(emptyLst.size()>0){
      bill.getValidateDetailLst().retainAll(emptyLst);
      refreshValidateTableData();
    }*/
    
    if (errorInfo.length() != 0) {
      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

  protected void doDelete() {
    requestMeta.setFuncId(deleteButton.getFuncId());
    SfMaterialsTransfer bill = (SfMaterialsTransfer) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        sfMaterialsTransferServiceDelegate.deleteByPrimaryKeyFN(bill.getTransferId(), requestMeta);
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        success = false;
        errorInfo += e.getMessage();
      }

      if (success) {
        this.listCursor.removeCurrentObject();
        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        refreshListPanel();
        doExit();
      } else {
        JOptionPane.showMessageDialog(this, "删除失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public boolean isDataChanged() {
    if (!this.saveButton.isVisible() || !saveButton.isEnabled()) {
      return false;
    }
    return !DigestUtil.digest(oldMaterialsTransfer).equals(DigestUtil.digest(listCursor.getCurrentObject()));
  }

  private void doPrint() {

  }

  private void doEdit() {

    this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
    updateFieldEditorsEditable();
    setButtonStatus();
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createFieldEditors()
   */
  @Override
  public List<AbstractFieldEditor> createFieldEditors() {

    List<AbstractFieldEditor> editorList = new ArrayList<AbstractFieldEditor>();

    SfEntrustHandler entrustHandler = new SfEntrustHandler() {

      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfMaterialsTransfer currentBill = (SfMaterialsTransfer) listCursor.getCurrentObject();
          SfEntrust entrust = (SfEntrust) obj;
          entrust=sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
          currentBill.setEntrustId(entrust.getEntrustId());
          currentBill.setEntrustCode(entrust.getCode());
          currentBill.setName(entrust.getName() + "鉴定材料流转");
          currentBill.setDetailLst(initTransferDetail(entrust.getEntrustId()));
          setEditingObject(currentBill);
          refreshSubData();
          break;
        }
      }

      public void afterClear() {
        SfMaterialsTransfer currentBill = (SfMaterialsTransfer) listCursor.getCurrentObject();
        currentBill.setEntrustId(null);
        currentBill.setEntrustCode(null);
        currentBill.setName(null);
        setEditingObject(currentBill);
      }
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("SF_MATERIALS_TRANSFER");
    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler,
      entrustHandler.getColumNames(), LangTransMeta.translate(SfMaterialsTransfer.COL_ENTRUST_CODE), "entrustCode");
    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_NAME), "name");
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_STATUS), "status", SfMaterialsTransfer.SF_VS_SF_MATERIALS_TRANSFER_STATUS);
    
    SfJdPersonSelectHandler yijiaorenHandler = new SfJdPersonSelectHandler() {      
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if(selectedDatas!=null && selectedDatas.size()>0){
          SfMaterialsTransfer cur=listCursor.getCurrentObject();
          User user=(User) selectedDatas.get(0);
          cur.setYiJiaoRen(user.getUserId());
          cur.setYiJiaoRenName(user.getUserName());
          setEditingObject(cur);
        }
      }
      public void afterClear(){
        SfMaterialsTransfer currentBill = (SfMaterialsTransfer) listCursor.getCurrentObject();
        currentBill.setYiJiaoRen(null);
        currentBill.setYiJiaoRenName(null);
        setEditingObject(currentBill);
      }
    };

    dto = new ElementConditionDto();
    dto.setNd(requestMeta.getSvNd());
    dto.setCoCode(requestMeta.getSvCoCode());
    ForeignEntityFieldEditor yjr = new ForeignEntityFieldEditor(yijiaorenHandler.getSqlId(), dto, 20,yijiaorenHandler, yijiaorenHandler.getColumNames(), LangTransMeta.translate(SfMaterialsTransfer.COL_YI_JIAO_REN),"yiJiaoRenName");

    DateFieldEditor yjDate = new DateFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_YI_JIAO_DATE), "yiJiaoDate");
    TextAreaFieldEditor yjDesc = new TextAreaFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_YI_JIAO_DESC), "yiJiaoDesc", 100, 3,5);
    
    SfUserSelectHandler jieShouRenHandler = new SfUserSelectHandler() {      
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if(selectedDatas!=null && selectedDatas.size()>0){
          SfMaterialsTransfer cur=listCursor.getCurrentObject();
          User user=(User) selectedDatas.get(0);
          cur.setJieShouRen(user.getUserId());
          cur.setJieShouRenName(user.getUserName());
          setEditingObject(cur);
        }
      }
      public void afterClear(){
        SfMaterialsTransfer currentBill = (SfMaterialsTransfer) listCursor.getCurrentObject();
        currentBill.setJieShouRen(null);
        currentBill.setJieShouRenName(null);
        setEditingObject(currentBill);
      }
    };

    dto = new ElementConditionDto();
    dto.setNd(requestMeta.getSvNd());
    dto.setCoCode(requestMeta.getSvCoCode());
    ForeignEntityFieldEditor jsr = new ForeignEntityFieldEditor(jieShouRenHandler.getSqlId(), dto, 20,jieShouRenHandler, jieShouRenHandler.getColumNames(), LangTransMeta.translate(SfMaterialsTransfer.COL_JIE_SHOU_REN),"jieShouRenName");

    DateFieldEditor jsDate = new DateFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_JIE_SHOU_DATE), "jieShouDate");
    TextAreaFieldEditor jieShouDesc = new TextAreaFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_JIE_SHOU_DESC), "jieShouDesc", 100, 3,5);
    

    IntFieldEditor clQuality=new IntFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_CL_QUALITY), "clQuality");
    IntFieldEditor ybQuality=new IntFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_YB_QUALITY), "ybQuality");
    TextAreaFieldEditor materialsDesc = new TextAreaFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_MATERIALS_DESC), "materialsDesc", 100, 3,5);
    TextAreaFieldEditor completionDesc = new TextAreaFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_COMPLETION_DESC), "completionDesc", 50, 2,5);
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_REMARK), "remark", 100, 2,5);
    IntFieldEditor nd=new IntFieldEditor(LangTransMeta.translate(SfMaterialsTransfer.COL_ND), "nd");
    

    editorList.add(entrust);
    editorList.add(name);
    editorList.add(status);

    editorList.add(yjr);
    editorList.add(yjDate);
    editorList.add(yjDesc);
    
    editorList.add(jsr);
    editorList.add(jsDate);
    editorList.add(new NewLineFieldEditor());
    
    editorList.add(clQuality);
    editorList.add(ybQuality);
    editorList.add(nd);
    
    editorList.add(materialsDesc);
    editorList.add(completionDesc);
    editorList.add(remark);

    return editorList;
  }

/**
 * 选择委托时，将其相关的鉴定材料、外部信息组装成流转明细
 * @param entrust
 * @return
 */
  protected List initTransferDetail(BigDecimal entrustId) {
    // TCJLODO Auto-generated method stub
    List rtn=new ArrayList();
    List materialLst=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfMaterialsMapper.selectByEntrustId", entrustId, requestMeta);
    if(materialLst!=null){
      for(int i=0;i<materialLst.size();i++){
        SfMaterials m=(SfMaterials)materialLst.get(i);
        SfMaterialsTransferDetail d=new SfMaterialsTransferDetail();
        d.setProcessing(SfMaterialsTransferDetail.HANDLE_STATUS_TUI_HUI);
        d.setQuantity3(m.getQuantity3());
        d.setUnit(m.getUnit());
        d.setMaterial(m);
        rtn.add(d);
      }
    }
    ElementConditionDto dto=new ElementConditionDto();
    dto.setSfId(entrustId);
    List outInfoLst=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfOutInfoDetailMapper.selectByEntrustId", dto, requestMeta);
    if(outInfoLst!=null){
      for(int i=0;i<outInfoLst.size();i++){
        SfOutInfoDetail m=(SfOutInfoDetail)outInfoLst.get(i);
        SfMaterialsTransferDetail d=new SfMaterialsTransferDetail();
        d.setProcessing(SfMaterialsTransferDetail.HANDLE_STATUS_TUI_HUI);
        d.setQuantity3(""+(m.getQuantity()==null?0:m.getQuantity().doubleValue()));
        d.setUnit(m.getUnit());
        d.setOutInfoDetail(m);
        rtn.add(d);
      }
    }
    return rtn;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {
    
    initMaterialTablePanel();
    initOutInfoTablePanel();
    
    JTabbedPane itemTabPane = new JTabbedPane();
    itemTabPane.addTab("鉴定材料", materialTablePanel);
//    itemTabPane.addTab("外部信息", outInfoTablePanel);
    itemTabPane.setMinimumSize(new Dimension(240, 300));
    return itemTabPane;
  }
  private void initOutInfoTablePanel(){

    outInfoTablePanel.init();

    outInfoTablePanel.setPanelId(this.getClass().getName() + "_OutInfoTablePanel");

    outInfoTablePanel.getSearchBar().setVisible(false);

    outInfoTablePanel.setTablePreferencesKey(this.getClass().getName() + "_OutInfoTable");

    outInfoTablePanel.getTable().setShowCheckedColumn(true);

    outInfoTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));


    JFuncToolBar detailBtnBar = new JFuncToolBar();

    FuncButton addBtn2 = new SubaddButton(false);

    JButton insertBtn2 = new SubinsertButton(false);

    JButton delBtn2 = new SubdelButton(false);

    detailBtnBar.add(addBtn2);

    detailBtnBar.add(insertBtn2);

    detailBtnBar.add(delBtn2);

    //不加工具条，不进行增删改
//    outInfoTablePanel.add(detailBtnBar, BorderLayout.SOUTH);

    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfMaterialsTransferDetail item = new SfMaterialsTransferDetail();
        setMaterialDefaultValue(item);
        int rowNum = addSub(outInfoTablePanel, item);
        outInfoTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfMaterialsTransferDetail item = new SfMaterialsTransferDetail();
        setMaterialDefaultValue(item);
        int rowNum = insertSub(outInfoTablePanel, item);
        outInfoTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(outInfoTablePanel);
      }
    });
  }
  protected void setMaterialDefaultValue(SfMaterialsTransferDetail item) {
    // TCJLODO Auto-generated method stub
    SfMaterialsTransfer bill = (SfMaterialsTransfer) this.listCursor.getCurrentObject();
    item.setTransferId(bill.getTransferId());
  }

  private void initMaterialTablePanel(){

    materialTablePanel.init();

    materialTablePanel.setPanelId(this.getClass().getName() + "_materialTablePanel");

    materialTablePanel.getSearchBar().setVisible(false);

    materialTablePanel.setTablePreferencesKey(this.getClass().getName() + "__materialTable");

    materialTablePanel.getTable().setShowCheckedColumn(true);

    materialTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));


    JFuncToolBar detailBtnBar = new JFuncToolBar();

    FuncButton addBtn2 = new SubaddButton(false);

    JButton insertBtn2 = new SubinsertButton(false);

    JButton delBtn2 = new SubdelButton(false);

    detailBtnBar.add(addBtn2);

    detailBtnBar.add(insertBtn2);

    detailBtnBar.add(delBtn2);
    
  //不加工具条，不进行增删改
//    materialTablePanel.add(detailBtnBar, BorderLayout.SOUTH);

    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfMaterialsTransferDetail item = new SfMaterialsTransferDetail();
        setMaterialDefaultValue(item);
        int rowNum = addSub(materialTablePanel, item);
        materialTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfMaterialsTransferDetail item = new SfMaterialsTransferDetail();
        setMaterialDefaultValue(item);
        int rowNum = insertSub(materialTablePanel, item);
        materialTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(materialTablePanel);
      }
    });
  } 

  public void doExit() {
    // TCJLODO Auto-generated method stub

    if (isDataChanged()) {

      int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存", "保存确认", 0);

      if (num == JOptionPane.YES_OPTION) {

        if (!doSave()) {

          return;

        }

      }

    }
    if (this.parent instanceof SfDataFlowDialog) {
      ((SfDataFlowDialog) parent).removeTab(this, compoId);
    } else {
      this.parent.dispose();
    }
  }

  /**
   * 送审
   */
  protected void doSend() {

    boolean success = true;

    SfMaterialsTransfer afterSaveBill = null;

    SfMaterialsTransfer bill = (SfMaterialsTransfer) this.listCursor.getCurrentObject();
    if(bill.getJieShouRen()==null){
      JOptionPane.showMessageDialog(this, "提交前请指定"+LangTransMeta.translate(SfMaterialsTransfer.COL_JIE_SHOU_REN), "错误", JOptionPane.ERROR_MESSAGE); 
      return;
    }
    
    /*if (this.isDataChanged()) {
      JOptionPane.showMessageDialog(this, "数据发生改变，请先保存.", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }*/
    
    try {
      requestMeta.setFuncId(this.sendButton.getFuncId());
      SfMaterialsTransfer qx = (SfMaterialsTransfer) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx.setComment("请接收.");
      qx.setYiJiaoDate(this.requestMeta.getSysDate());
      qx.setYiJiaoRen(this.requestMeta.getSvUserID());
      afterSaveBill = sfMaterialsTransferServiceDelegate.newCommitFN(qx, requestMeta);
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
      success = false;
      UIUtilities.showStaickTraceDialog(ex, this, "错误", ex.getMessage());
    }

    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      JOptionPane.showMessageDialog(this, "提交成功，等待对方接受！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    }else{
      JOptionPane.showMessageDialog(this, "提交失败 ！" , "错误", JOptionPane.ERROR_MESSAGE);
    }
  }
  /**
   * 审核
   */
  protected void doSuggestPass() {
    if (!checkBeforeSave()) {
      return;
    }
    SfMaterialsTransfer qx = (SfMaterialsTransfer) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    requestMeta.setFuncId(this.suggestPassButton.getFuncId());
    int num = JOptionPane.showConfirmDialog(this, "确认接收吗", "接收确认", 0);
    if (num != JOptionPane.YES_OPTION) {
      return;
    }
    boolean success = true;
    String errorInfo = "";
    try {
      qx.setComment("同意接收.");
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx = sfMaterialsTransferServiceDelegate.auditFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(this, "接收成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "接收失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

 /**
  * 销审
  */
  protected void doUnAudit() {
    SfMaterialsTransfer qx = (SfMaterialsTransfer) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    boolean success = true;
    SfMaterialsTransfer afterSaveBill = null;
    String errorInfo = "";
    int i = JOptionPane.showConfirmDialog(this, "是否确定取消接收？", "确认", JOptionPane.INFORMATION_MESSAGE);
    if (i != 0) {
      return;
    }
    try {
      requestMeta.setFuncId(unAuditButton.getFuncId());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfMaterialsTransferServiceDelegate.unAuditFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      JOptionPane.showMessageDialog(this, "取消接收成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "取消接收失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }
 /**
  * 退回
  */
  protected void doUnTread() {
    GkCommentUntreadDialog commentDialog = new GkCommentUntreadDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),ModalityType.APPLICATION_MODAL);
    if (commentDialog.cancel) {
      return;
    }
    boolean success = true;
    SfMaterialsTransfer afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(unTreadButton.getFuncId());
      SfMaterialsTransfer qx = (SfMaterialsTransfer) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx.setComment(commentDialog.getComment());
      afterSaveBill = sfMaterialsTransferServiceDelegate.untreadFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      JOptionPane.showMessageDialog(this, "退回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "退回失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

 /**
  * 收回
  */
  protected void doCallback() {
    boolean success = true;
    SfMaterialsTransfer afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(this.callbackButton.getFuncId());
      SfMaterialsTransfer qx = (SfMaterialsTransfer) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfMaterialsTransferServiceDelegate.callbackFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }

    if (success) {
      JOptionPane.showMessageDialog(this, "收回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "收回失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  /*
   * 流程跟踪
   */
  private void doTrace() {
    ZcBaseBill bean = (ZcBaseBill) this.listCursor.getCurrentObject();
    if (bean == null) {
      return;
    }
    ZcUtil.showTraceDialog(bean, compoId);
  }
}
