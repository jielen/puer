package com.ufgov.zc.client.sf.entrust;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.smartclient.component.table.fixedtable.JPageableFixedTable;
import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfChargeToTableModelConverter;
import com.ufgov.zc.client.common.converter.sf.SfEntrustToTableModelConverter;
import com.ufgov.zc.client.component.AsValComboBox;
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
import com.ufgov.zc.client.component.sf.fieldeditor.SfEntrustorNewFieldEditor;
import com.ufgov.zc.client.component.sf.fieldeditor.SfJdTargetNewFieldEditor;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.celleditor.MoneyCellEditor;
import com.ufgov.zc.client.component.table.celleditor.TextCellEditor;
import com.ufgov.zc.client.component.table.cellrenderer.NumberCellRenderer;
import com.ufgov.zc.client.component.table.codecelleditor.AsValComboBoxCellEditor;
import com.ufgov.zc.client.component.table.codecellrenderer.AsValCellRenderer;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.AutoNumFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldCellEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.IntFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.MoneyFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.charge.ChargeStandardHandler;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.entrustor.SfEntrustorHandler;
import com.ufgov.zc.client.sf.jdtarget.SfJdTargethandler;
import com.ufgov.zc.client.sf.util.SfJdPersonSelectHandler;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.util.freemark.IWordHandler;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.WordFileUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.commonbiz.model.BillElement;
import com.ufgov.zc.common.commonbiz.model.WfAware;
import com.ufgov.zc.common.sf.model.SfChargeDetail;
import com.ufgov.zc.common.sf.model.SfChargeStandard;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.system.util.Utils;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfEntrustEditPanel extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -251257356778588783L;

  private static final Logger logger = Logger.getLogger(SfEntrustEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_ENTRUST";

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

  public FuncButton printWtButton = new PrintButton();

  public FuncButton printXyButton = new CommonButton("fprintXy", "print.gif");
  public FuncButton printConfirmButton = new CommonButton("fprintConfirm", "print.gif");

  public FuncButton importButton = new ImportButton();

  //送国库
  private FuncButton sendGkButton = new SendGkButton();

  // 工作流填写意见审核通过
  protected FuncButton suggestPassButton = new SuggestAuditPassButton();

  // 工作流销审
  protected FuncButton unAuditButton = new UnauditButton();

  // 工作流退回
  protected FuncButton unTreadButton = new UntreadButton();

  protected ListCursor<SfEntrust> listCursor;

  private SfEntrust oldentrust;

  public SfEntrustListPanel listPanel;

  protected SfEntrustEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);

  protected JTablePanel materialsTablePanel = new JTablePanel();

  protected JTablePanel jdChargeTablePanel = new JTablePanel();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

  protected List<AbstractFieldEditor> headFieldEditors = new ArrayList<AbstractFieldEditor>();//头部列表

  protected List<AbstractFieldEditor> jazyFieldEditors = new ArrayList<AbstractFieldEditor>();//案事件简要情况列表
  
  protected List<AbstractFieldEditor> jdyqFieldEditors = new ArrayList<AbstractFieldEditor>();//鉴定要求事项列表

  protected List<AbstractFieldEditor> jddxFieldEditors = new ArrayList<AbstractFieldEditor>();//鉴定对象事项列表

  protected List<AbstractFieldEditor> bslFieldEditors = new ArrayList<AbstractFieldEditor>();//不受理列表

  protected List<AbstractFieldEditor> historyFieldEditors = new ArrayList<AbstractFieldEditor>();//历史鉴定列表

  protected List<AbstractFieldEditor> xysxFieldEditors = new ArrayList<AbstractFieldEditor>();//协议事项列表

  protected List<AbstractFieldEditor> footFieldEditors = new ArrayList<AbstractFieldEditor>();//底部列表

  private AsValFieldEditor jdDocSendType;

  private TextFieldEditor jdDocSendTypeFz;

  private Hashtable<BigDecimal, JComponent> xysxComponents = new Hashtable<BigDecimal, JComponent>();// 协议事项部件

  final ElementConditionDto majorPersonDto = new ElementConditionDto();

  public SfEntrustEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfEntrustListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfEntrustEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");

    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = parent;

    this.colCount = 3;

    WordFileUtil.setDir("sf");

    init();

    requestMeta.setCompoId(getCompoId());

    refreshData();

    setButtonStatus();

    updateFieldEditorsEditable();
  }

  private void refreshData() {
    // TCJLODO Auto-generated method stub

    SfEntrust entrust = (SfEntrust) listCursor.getCurrentObject();

    if (entrust != null && !"".equals(ZcUtil.safeString(entrust.getCode()))) {//列表页面双击进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

      entrust = sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), this.requestMeta);

      listCursor.setCurrentObject(entrust);
      this.setEditingObject(entrust);
    } else {//新增按钮进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;

      entrust = new SfEntrust();
      setDefaultValue(entrust);
      listCursor.getDataList().add(entrust);

      listCursor.setCurrentObject(entrust);

      this.setEditingObject(entrust);

    }
    refreshSubData();
    XysxPanelUtil.setValue(entrust, xysxComponents);
    setOldObject();

    setButtonStatus();

    updateFieldEditorsEditable();

  }

  private void setDefaultValue(SfEntrust entrust) {
    // TCJLODO Auto-generated method stub

    entrust.setStatus(ZcSettingConstants.WF_STATUS_DRAFT);
    entrust.setNd(this.requestMeta.getSvNd());
    entrust.setInputDate(this.requestMeta.getSysDate());
    entrust.setInputor(requestMeta.getSvUserID());
    entrust.setAcceptDate(requestMeta.getSysDate());
    entrust.setAcceptor(requestMeta.getSvUserID());
    entrust.setJdCompany(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME));//天津市开平司法鉴定中心
    entrust.setWtDate(requestMeta.getSysDate());
    entrust.setJdDocSendType(SfEntrust.SF_VS_ENTRUST_DOC_SEND_TYPE_ZIQU);
    //获取空的协议事项
    List xysxLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfXysxMapper.selectByPrimaryKey", null, requestMeta);
    xysxLst = xysxLst == null ? new ArrayList() : xysxLst;
    entrust.setXysxLst(xysxLst);
    entrust.setIsCxjd("N");
  }

  private void refreshSubData() {
    // TCJLODO Auto-generated method stub
    SfEntrust entrust = (SfEntrust) listCursor.getCurrentObject();
    materialsTablePanel.setTableModel(SfEntrustToTableModelConverter.convertMaterialsTableData(entrust.getMaterials()));
    ZcUtil.translateColName(materialsTablePanel.getTable(), SfEntrustToTableModelConverter.getMaterialInfo());
    setMaterialsTablePorperty();

    jdChargeTablePanel.setTableModel(SfChargeToTableModelConverter.convertChargeDetailsTableData(entrust.getJdChargeDetaillst()));
    ZcUtil.translateColName(jdChargeTablePanel.getTable(), SfChargeToTableModelConverter.getDetailInfo());
    setJdChargeTablePorperty();
    addJdChargeTableLisenter();
  }

  private void addJdChargeTableLisenter() {
    // TCJLODO Auto-generated method stub

    final JPageableFixedTable table = jdChargeTablePanel.getTable();
    final BeanTableModel model = (BeanTableModel) (table.getModel());

    model.addTableModelListener(new TableModelListener() {

      public void tableChanged(TableModelEvent e) {

        if (e.getType() == TableModelEvent.UPDATE) {

          if (e.getColumn() >= 0 && table.getSelectedRow() >= 0) {
            SfEntrust bill = (SfEntrust) listCursor.getCurrentObject();
            int k = table.getSelectedRow();
            if (SfChargeDetail.COL_PRICE.equals(model.getColumnIdentifier(e.getColumn()))
              || SfChargeDetail.COL_QUANTITY.equals(model.getColumnIdentifier(e.getColumn()))) {

              SfChargeDetail item = (SfChargeDetail) (model.getBean(table.convertRowIndexToModel(k)));

              BigDecimal price = item.getPrice() == null ? BigDecimal.ZERO : item.getPrice();
              BigDecimal quantity = item.getQuantity() == null ? BigDecimal.ZERO : item.getQuantity();

              item.setTotalPrice(price.multiply(quantity));

              BigDecimal num = BigDecimal.ZERO;
              for (int i = 0; i < table.getRowCount(); i++) {
                SfChargeDetail row = (SfChargeDetail) (model.getBean(table.convertRowIndexToModel(i)));
                if (row.getTotalPrice() != null) {
                  num = num.add(row.getTotalPrice());
                }
              }
              bill.setJdCharge(num);
              setEditingObject(bill);
            } else if (SfChargeDetail.COL_TOTAL_PRICE.equals(model.getColumnIdentifier(e.getColumn()))) {

              BigDecimal num = BigDecimal.ZERO;
              for (int i = 0; i < table.getRowCount(); i++) {
                SfChargeDetail row = (SfChargeDetail) (model.getBean(table.convertRowIndexToModel(i)));
                if (row.getTotalPrice() != null) {
                  num = num.add(row.getTotalPrice());
                }
              }
              bill.setJdCharge(num);
              setEditingObject(bill);

            }
          }
        }
      }

    });
  }

  private void setJdChargeTablePorperty() {
    final JPageableFixedTable table = jdChargeTablePanel.getTable();
    table.setDefaultEditor(String.class, new TextCellEditor());
    ChargeStandardHandler handler = new ChargeStandardHandler() {
      @Override
      public void excute(List selectedDatas) {
        BeanTableModel model = (BeanTableModel) table.getModel();
        int k = table.getSelectedRow();
        if (k < 0) {
          return;
        }

        int k2 = table.convertRowIndexToModel(k);
        SfChargeDetail detail = (SfChargeDetail) (model.getBean(k2));
        for (Object obj : selectedDatas) {
          SfChargeStandard standard = (SfChargeStandard) obj;
          detail.setChargeStandardId(standard.getChargeStandardId());
          detail.setChargeStandardName(standard.getName());
          detail.setPrice(standard.getPrice());
        }
        model.fireTableRowsUpdated(k, k);
      }
    };

    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("enable");
    ForeignEntityFieldCellEditor foreignExpertCodeEditor = new ForeignEntityFieldCellEditor(handler.getSqlId(), dto, 20, handler,
      handler.getColumNames(), "收费标准", "name");

    SwingUtil.setTableCellEditor(table, SfChargeDetail.COL_CHARGE_STANDARD_NAME, foreignExpertCodeEditor);
    SwingUtil.setTableCellEditor(table, SfChargeDetail.COL_PRICE_TYPE, new AsValComboBoxCellEditor(SfChargeDetail.SF_VS_CHARGE_PRICE_TYPE));
    SwingUtil.setTableCellRenderer(table, SfChargeDetail.COL_PRICE_TYPE, new AsValCellRenderer(SfChargeDetail.SF_VS_CHARGE_PRICE_TYPE));

    SwingUtil.setTableCellEditor(table, SfChargeDetail.COL_PRICE, new MoneyCellEditor(false));
    SwingUtil.setTableCellRenderer(table, SfChargeDetail.COL_PRICE, new NumberCellRenderer());

    SwingUtil.setTableCellEditor(table, SfChargeDetail.COL_TOTAL_PRICE, new MoneyCellEditor(false));
    SwingUtil.setTableCellRenderer(table, SfChargeDetail.COL_TOTAL_PRICE, new NumberCellRenderer());

    SwingUtil.setTableCellEditor(table, SfChargeDetail.COL_QUANTITY, new MoneyCellEditor(false));
    SwingUtil.setTableCellRenderer(table, SfChargeDetail.COL_QUANTITY, new NumberCellRenderer());
  }

  private void setMaterialsTablePorperty() {
    JPageableFixedTable table = materialsTablePanel.getTable();
    table.setDefaultEditor(String.class, new TextCellEditor());
    SwingUtil.setTableCellEditor(table, SfMaterials.COL_QUANTITY, new MoneyCellEditor(false));
    SwingUtil.setTableCellRenderer(table, SfMaterials.COL_QUANTITY, new NumberCellRenderer());
    SwingUtil.setTableCellEditor(table, SfMaterials.COL_MATERIAL_TYPE, new AsValComboBoxCellEditor(SfMaterials.SF_VS_MATERIAL_TYPE));
    SwingUtil.setTableCellRenderer(table, SfMaterials.COL_MATERIAL_TYPE, new AsValCellRenderer(SfMaterials.SF_VS_MATERIAL_TYPE));
  }

  protected void updateFieldEditorsEditable() {

    SfEntrust qx = (SfEntrust) listCursor.getCurrentObject();
    Long processInstId = qx.getProcessInstId();
    isEdit = false;
    if (processInstId != null && processInstId.longValue() > 0) {
      // 工作流的单据
      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(qx, requestMeta);
      if ("cancel".equals(this.oldentrust.getStatus())) {// 撤销单据设置字段为不可编辑
        wfCanEditFieldMap = null;
      }

      for (AbstractFieldEditor editor : fieldEditors) {
        // 工作流中定义可编辑的字段
        //        System.out.println(editor.getFieldName());
        if (editor instanceof NewLineFieldEditor)
          continue;
        if (wfCanEditFieldMap != null && wfCanEditFieldMap.containsKey(Utils.getDBColNameByFieldName(editor.getEditObject(), editor.getFieldName()))) {
          isEdit = true;
          this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
          editor.setEnabled(true);
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
          if ("code".equals(editor.getFieldName()) || "status".equals(editor.getFieldName()) || "inputor".equals(editor.getFieldName())
            || "inputDate".equals(editor.getFieldName()) || "jdCharge".equals(editor.getFieldName())) {
            editor.setEnabled(false);
          } else {
            editor.setEnabled(true);
          }
          isEdit = true;
        } else {
          editor.setEnabled(false);
        }
      }
    }

    setWFSubTableEditable(materialsTablePanel, isEdit);
    setWFSubTableEditable(jdChargeTablePanel, isEdit);
    XysxPanelUtil.updateEditable(isEdit, xysxComponents);
  }

  protected void setButtonStatus() {
    SfEntrust entrust = (SfEntrust) listCursor.getCurrentObject();
    setButtonStatus(entrust, requestMeta, this.listCursor);

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

      bs.addBillStatus(ZcSettingConstants.WF_STATUS_DRAFT);

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

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.unTreadButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      bs = new ButtonStatus();

      bs.setButton(this.printWtButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.printXyButton);

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

    SfEntrust entrust = (SfEntrust) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, entrust.getStatus(), this.pageStatus, getCompoId(), entrust.getProcessInstId());

  }

  protected void setOldObject() {

    oldentrust = (SfEntrust) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

    toolBar.add(editButton);

    toolBar.add(saveButton);

    toolBar.add(sendButton);

    //    toolBar.add(saveAndSendButton);

    toolBar.add(suggestPassButton);

    //    toolBar.add(sendGkButton);

    toolBar.add(unAuditButton);

    toolBar.add(unTreadButton);

    toolBar.add(callbackButton);

    toolBar.add(deleteButton);

    //    toolBar.add(importButton);

    toolBar.add(printWtButton);
    printWtButton.setText("委托书");
    printWtButton.setToolTipText("委托书");

    /*toolBar.add(printXyButton);
    printXyButton.setText("委托协议");
    printXyButton.setToolTipText("委托协议");*/
    
    toolBar.add(printConfirmButton);
    printConfirmButton.setText("委托确认书");
    printConfirmButton.setToolTipText("委托确认书");

    toolBar.add(traceButton);

    //    toolBar.add(previousButton);

    //    toolBar.add(nextButton);

    toolBar.add(exitButton);

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

    printWtButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrintWt();

      }

    });

    printXyButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrintXy();

      }

    });
    printConfirmButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          doPrintConfirm();
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

  protected void doPrevious() {

    if (isDataChanged()) {

      int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存", "保存确认", 0);

      if (num == JOptionPane.YES_OPTION) {

        if (!doSave()) {

          return;

        }

      } else {

        listCursor.setCurrentObject(oldentrust);

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

        listCursor.setCurrentObject(oldentrust);

      }

    }

    listCursor.next();

    refreshData();

  }

  public boolean doSave() {

    SfEntrust entrust = (SfEntrust) this.listCursor.getCurrentObject();
    //    System.out.println("doSave 1++++++++++++++++++++++++++=" + entrust.getAcceptDate());
    XysxPanelUtil.getValue(entrust, xysxComponents);

    /* if (!isDataChanged()) {

       JOptionPane.showMessageDialog(this, "数据没有发生改变，不用保存.", "提示", JOptionPane.INFORMATION_MESSAGE);

       return true;

     }*/

    if (!checkBeforeSave()) {

      return false;

    }

    boolean success = true;

    String errorInfo = "";

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      //      System.out.println("doSave 2++++++++++++++++++++++++++=" + entrust.getAcceptDate());
      entrust = sfEntrustServiceDelegate.saveFN(entrust, this.requestMeta);

      //      System.out.println("doSave 3++++++++++++++++++++++++++=" + entrust.getAcceptDate());
      listCursor.setCurrentObject(entrust);

      //      System.out.println("doSave 4++++++++++++++++++++++++++=" + entrust.getAcceptDate());
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
    SfEntrust entrust = (SfEntrust) this.listCursor.getCurrentObject();
    if (listPanel != null && listPanel.getParent() instanceof JClosableTabbedPane) {
      return;
    }
    if (parent instanceof SfEntrustDialog) {//新增的委托书，创建数据流界面
      SfDataFlowDialog d = new SfDataFlowDialog(compoId, entrust, listPanel);
      parent.dispose();
    } else {
      SfDataFlowDialog d = (SfDataFlowDialog) parent;
      d.refresh(entrust.getEntrustId());
    }
  }

  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    SfEntrust entrust = (SfEntrust) this.listCursor.getCurrentObject();

    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(entrust, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString()).append("\n");
    }

    if (errorInfo.length() != 0) {
      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

  protected void doDelete() {

    requestMeta.setFuncId(deleteButton.getFuncId());

    SfEntrust entrust = (SfEntrust) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);

    if (num == JOptionPane.YES_OPTION) {

      boolean success = true;

      String errorInfo = "";

      try {

        requestMeta.setFuncId(deleteButton.getFuncId());

        sfEntrustServiceDelegate.deleteByPrimaryKeyFN(entrust.getEntrustId(), requestMeta);

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

    return !DigestUtil.digest(oldentrust).equals(DigestUtil.digest(listCursor.getCurrentObject()));

  }

  private void doPrintWt() {
    Hashtable userData = new Hashtable();
    SfEntrust entrust = (SfEntrust) this.listCursor.getCurrentObject();
    userData.put("entrust", entrust);
    userData.put(IWordHandler.FILE_NAME, entrust.getCode() + "委托书");
    IWordHandler handler = new SfEntrustWordPrintHandler2();
    String fileName = handler.createDocumnet(userData);
    try {
      Desktop.getDesktop().open(new File(fileName));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "抱歉！没有找到合适的程序来打开文件！" + fileName, "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
  }

  private void doPrintXy() {
    Hashtable userData = new Hashtable();
    SfEntrust entrust = (SfEntrust) this.listCursor.getCurrentObject();
    userData.put("entrust", entrust);
    userData.put(IWordHandler.FILE_NAME, entrust.getCode() + "委托协议书");
    IWordHandler handler = new SfAgreementWordHandler();
    String fileName = handler.createDocumnet(userData);
    try {
      Desktop.getDesktop().open(new File(fileName));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "抱歉！没有找到合适的程序来打开文件！" + fileName, "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
  }

  private void doPrintConfirm() {
    Hashtable userData = new Hashtable();
    SfEntrust entrust = (SfEntrust) this.listCursor.getCurrentObject();
    userData.put("entrust", entrust);
    userData.put(IWordHandler.FILE_NAME, entrust.getCode() + "鉴定事项确认书");
    IWordHandler handler = new SfEntrustConfirmWordHandler2();
    String fileName = handler.createDocumnet(userData);
    try {
      Desktop.getDesktop().open(new File(fileName));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "抱歉！没有找到合适的程序来打开文件！" + fileName, "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
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

    AutoNumFieldEditor code = new AutoNumFieldEditor(LangTransMeta.translate(SfEntrust.COL_CODE), "code");
    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_NAME), "name");
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_STATUS), "status", SfEntrust.SF_VS_ENTRUST_STATUS);
    SfEntrustorHandler entrustorHandler = new SfEntrustorHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
          currentBill.setEntrustor((SfEntrustor) obj);
          setEditingObject(currentBill);
        }
      }

      public void afterClear() {
        SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
        currentBill.setEntrustor(new SfEntrustor());
        setEditingObject(currentBill);
      }
    };
    SfEntrustorNewFieldEditor entrustor = new SfEntrustorNewFieldEditor(entrustorHandler.getSqlId(), 20, entrustorHandler,
      entrustorHandler.getColumNames(), LangTransMeta.translate(SfEntrust.COL_ENTRUSTOR_NAME), "entrustor.name");
    TextFieldEditor entrustorAddress = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.ADDRESS), "entrustor.address");
    TextFieldEditor entrustorLinkMan = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.LINK_MAN), "entrustor.linkMan");
    TextFieldEditor entrustorTel = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.LINK_TEL), "entrustor.linkTel");

    DateFieldEditor wtDate = new DateFieldEditor(LangTransMeta.translate(SfEntrust.COL_WT_DATE), "wtDate");

    TextFieldEditor sjr = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR), "sjr");
    TextFieldEditor sjrTel = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_TEL), "sjrTel");
    TextFieldEditor sjrZjType = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_TYPE), "sjrZjType");
    TextFieldEditor sjrZjCode = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_CODE), "sjrZjCode");
    TextAreaFieldEditor sjrAddress = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_ADDRESS), "sjrAddress", -1, 1, 5);

    AsValFieldEditor majorCode = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME), "majorCode", "SF_VS_MAJOR") {
      @Override
      protected void afterChange(AsValComboBox field) {
        if (field.getSelectedAsVal() == null) {
          majorPersonDto.setDattr1(null);
          return;
        }
        String valId = field.getSelectedAsVal().getValId();
        valId=valId.substring(0, 3);//取前面三位
        majorPersonDto.setDattr1(valId);
      }
    };

    AsValFieldEditor jdOrg = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_ORG), "jdOrg", SfElementConstants.VS_SF_ORG);

    SfJdPersonSelectHandler jdFzrHandler = new SfJdPersonSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if (selectedDatas != null && selectedDatas.size() > 0) {
          SfEntrust cur = listCursor.getCurrentObject();
          SfJdPerson user = (SfJdPerson) selectedDatas.get(0);
          cur.setJdFzr(user.getAccount());
          cur.setJdFzrName(user.getName());
          setEditingObject(cur);
        }
      }

      public void afterClear() {
        SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
        currentBill.setJdFzr(null);
        setEditingObject(currentBill);
      }

      public boolean beforeSelect(ElementConditionDto dto) {
        SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
        if (currentBill.getMajorCode() == null || currentBill.getMajorCode().trim().length() == 0) {
          JOptionPane
            .showMessageDialog(self, "请先选择" + LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME) + "！", "提示", JOptionPane.INFORMATION_MESSAGE);
          return false;
        }
        return true;
      }
    };

    majorPersonDto.setNd(requestMeta.getSvNd());
    majorPersonDto.setCoCode(requestMeta.getSvCoCode());
    ForeignEntityFieldEditor jdFzr = new ForeignEntityFieldEditor(jdFzrHandler.getSqlId(), majorPersonDto, 20, jdFzrHandler,
      jdFzrHandler.getColumNames(), LangTransMeta.translate(SfEntrust.COL_JD_FZR), "jdFzrName");

    SfJdPersonSelectHandler jdFhrHandler = new SfJdPersonSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if (selectedDatas != null && selectedDatas.size() > 0) {
          SfEntrust cur = listCursor.getCurrentObject();
          SfJdPerson user = (SfJdPerson) selectedDatas.get(0);
          cur.setJdFhr(user.getAccount());
          cur.setJdFhrName(user.getName());
          setEditingObject(cur);
        }
      }

      public void afterClear() {
        SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
        currentBill.setJdFhr(null);
        setEditingObject(currentBill);
      }

      public boolean beforeSelect(ElementConditionDto dto) {
        SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
        if (currentBill.getMajorCode() == null || currentBill.getMajorCode().trim().length() == 0) {
          JOptionPane
            .showMessageDialog(self, "请先选择" + LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME) + "！", "提示", JOptionPane.INFORMATION_MESSAGE);
          return false;
        }
        return true;
      }
    };
    ForeignEntityFieldEditor jdFhr = new ForeignEntityFieldEditor(jdFhrHandler.getSqlId(), majorPersonDto, 20, jdFhrHandler,
      jdFhrHandler.getColumNames(), LangTransMeta.translate(SfEntrust.COL_JD_FHR), "jdFhrName");

    TextAreaFieldEditor jdHistory = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_HISTORY), "jdHistory", -1, 3, 5);
    TextAreaFieldEditor jdRequire = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_REQUIRE), "jdRequire", -1, 9, 5);
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_REMARK), "remark", -1, 2, 5);
    AsValFieldEditor isCxjd = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_IS_CXJD), "isCxjd", "VS_Y/N");

    ElementConditionDto parentEntrustDto = new ElementConditionDto();
    parentEntrustDto.setDattr1("SF_ENTRUST");
    SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
    parentEntrustDto.setSfId(currentBill == null ? null : currentBill.getEntrustId());
    SfEntrustHandler cxjdHandler = new SfEntrustHandler() {

      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if (selectedDatas != null && selectedDatas.size() > 0) {
          SfEntrust cur = listCursor.getCurrentObject();
          SfEntrust parentEntrust = (SfEntrust) selectedDatas.get(0);
          parentEntrust = sfEntrustServiceDelegate.selectByPrimaryKey(parentEntrust.getEntrustId(), requestMeta);
          cur.setOldEntrust(parentEntrust);
          cur.setWtIdParent(parentEntrust.getEntrustId());
          cur.setWtCodeParent(parentEntrust.getCode());

          List jdResultLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdResultMapper.selectByEntrustId",
            parentEntrust.getEntrustId(), requestMeta);
          if (jdResultLst != null && jdResultLst.size() > 0) {
            SfJdResult jrlt = (SfJdResult) jdResultLst.get(0);
            StringBuffer sb = new StringBuffer();
            sb.append("检验结果:\n").append(jrlt.getJdResult()).append("鉴定意见:\n").append(jrlt.getJdOpinion());
            cur.setJdHistory(sb.toString());
          }
          setEditingObject(cur);
        }
      }

      public void afterClear() {
        SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
        currentBill.setOldEntrust(new SfEntrust());
        currentBill.setWtIdParent(null);
        currentBill.setWtCodeParent(null);
        currentBill.setJdHistory(null);
        setEditingObject(currentBill);
      }

      public boolean beforeSelect(ElementConditionDto dto) {
        SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
        if (currentBill.getIsCxjd() == null || currentBill.getIsCxjd().trim().equalsIgnoreCase(SfElementConstants.VAL_N)) {
          JOptionPane.showMessageDialog(self, "请先选择" + LangTransMeta.translate(SfEntrust.COL_IS_CXJD) + "！", "提示", JOptionPane.INFORMATION_MESSAGE);
          return false;
        }
        return true;
      }
    };
    ForeignEntityFieldEditor parentEntrust = new ForeignEntityFieldEditor(cxjdHandler.getSqlId(), parentEntrustDto, 20, cxjdHandler,
      cxjdHandler.getColumNames(), LangTransMeta.translate(SfEntrust.COL_WT_ID_PARENT), "wtCodeParent");
    TextFieldEditor lsJdFzr = new TextFieldEditor("上次鉴定负责人", "lsJdFzrName");
    TextFieldEditor lsJdFhr = new TextFieldEditor("上次鉴定复核人", "lsJdFhrName");

    TextAreaFieldEditor brief = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_BRIEF), "brief", -1, 9, 5);
    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_INPUTOR), "inputorName");
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfEntrust.COL_INPUT_DATE), "inputDate");
    TextFieldEditor acceptor = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_ACCEPTOR), "acceptorName");
    DateFieldEditor acceptDate = new DateFieldEditor(LangTransMeta.translate(SfEntrust.COL_ACCEPT_DATE), "acceptDate");
    AsValFieldEditor isAccept = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_IS_ACCEPT), "isAccept", "VS_Y/N");
    TextAreaFieldEditor notAcceptReason = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_NOT_ACCEPT_REASON), "notAcceptReason", -1, 5,
      5);
    IntFieldEditor nd = new IntFieldEditor(LangTransMeta.translate(SfEntrust.COL_ND), "nd");
    SfJdTargethandler targetHandler = new SfJdTargethandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
          currentBill.setJdTarget((SfJdTarget) obj);
          setEditingObject(currentBill);
        }
      }

      public void afterClear() {
        SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
        currentBill.setJdTarget(new SfJdTarget());
        setEditingObject(currentBill);
      }
    };
    SfJdTargetNewFieldEditor jdTarget = new SfJdTargetNewFieldEditor(targetHandler.getSqlId(), 20, targetHandler, targetHandler.getColumNames(),
      LangTransMeta.translate(SfJdTarget.COL_NAME), "jdTarget.name");
    IntFieldEditor jdTargetAge = new IntFieldEditor(LangTransMeta.translate(SfJdTarget.COL_AGE), "jdTarget.age");
    AsValFieldEditor jdTargetSex = new AsValFieldEditor(LangTransMeta.translate(SfJdTarget.COL_SEX), "jdTarget.sex", SfElementConstants.VS_SEX);
    TextFieldEditor jdTargetIdName = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ID_NAME), "jdTarget.idName");
    TextFieldEditor jdTargetIdCode = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ID_CODE), "jdTarget.idCode");
    TextFieldEditor jdTargetPhone = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_PHONE), "jdTarget.phone");
    TextFieldEditor jdTargetAddress = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ADDRESS), "jdTarget.address");
    MoneyFieldEditor jdCharge = new MoneyFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_CHARGE), "jdCharge");

    jdDocSendType = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_DOC_SEND_TYPE), "jdDocSendType",
      SfEntrust.SF_VS_ENTRUST_DOC_SEND_TYPE);
    jdDocSendTypeFz = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_DOC_SEND_TYPE_FZ), "jdDocSendTypeFz");

    headFieldEditors.add(new NewLineFieldEditor());

    headFieldEditors.add(code);
    headFieldEditors.add(name);
    headFieldEditors.add(isAccept);

    headFieldEditors.add(entrustor);
    headFieldEditors.add(wtDate);
    headFieldEditors.add(status);

    headFieldEditors.add(sjr);
    headFieldEditors.add(sjrTel);
    headFieldEditors.add(inputor);

    headFieldEditors.add(sjrZjType);
    headFieldEditors.add(sjrZjCode);
    headFieldEditors.add(inputDate);

    headFieldEditors.add(sjrAddress);

    headFieldEditors.add(majorCode);
    //    editorList.add(jdOrg);
    headFieldEditors.add(jdFzr);
    headFieldEditors.add(jdFhr);

    headFieldEditors.add(jdCharge);
    headFieldEditors.add(acceptor);
    headFieldEditors.add(acceptDate);

    jdyqFieldEditors.add(jdRequire);

    jazyFieldEditors.add(brief);

    bslFieldEditors.add(notAcceptReason);

    jddxFieldEditors.add(jdTarget);
    jddxFieldEditors.add(jdTargetAge);
    jddxFieldEditors.add(jdTargetSex);

    headFieldEditors.add(remark);
    //    footFieldEditors.add(inputor);
    //    footFieldEditors.add(inputDate);

    historyFieldEditors.add(isCxjd);
    historyFieldEditors.add(parentEntrust);
    historyFieldEditors.add(lsJdFzr);
    historyFieldEditors.add(lsJdFhr);
    historyFieldEditors.add(jdHistory);

    xysxFieldEditors.add(jdDocSendType);
    xysxFieldEditors.add(jdDocSendTypeFz);

    fieldEditors = new ArrayList<AbstractFieldEditor>();
    fieldEditors.addAll(headFieldEditors);
    fieldEditors.addAll(jazyFieldEditors);
    fieldEditors.addAll(jdyqFieldEditors);
    fieldEditors.addAll(jddxFieldEditors);
    fieldEditors.addAll(bslFieldEditors);
    fieldEditors.addAll(historyFieldEditors);
    fieldEditors.addAll(xysxFieldEditors);
    fieldEditors.addAll(footFieldEditors);

    return fieldEditors;

  }

  protected void init() {

    this.initToolBar(toolBar);

    this.setLayout(new BorderLayout());

    this.add(toolBar, BorderLayout.NORTH);

    createFieldEditors();

    JPanel headPanel = initFieldEditorPanel(this.billClass, this.eleMeta, headFieldEditors);
    JPanel jazyPanel = initJazyPanel(this.billClass, this.eleMeta, jazyFieldEditors);
    JPanel jdyqPanel = initJdyqPanel(this.billClass, this.eleMeta, jdyqFieldEditors);
    JPanel jddxPanel = initJddxPanel(this.billClass, this.eleMeta, jddxFieldEditors);
    JPanel bslPanel = initBslPanel(this.billClass, this.eleMeta, bslFieldEditors);
    JPanel historyPanel = initJdlslPanel(this.billClass, this.eleMeta, historyFieldEditors);
    JComponent xysxPanel = XysxPanelUtil.createXysxPanel(this.billClass, this.eleMeta, jdDocSendType, jdDocSendTypeFz, xysxComponents);
    JPanel footPanel = initFieldEditorPanel(this.billClass, this.eleMeta, footFieldEditors);

    workPanel.setLayout(new BorderLayout());

    workPanel.add(headPanel, BorderLayout.NORTH);

    JComponent materialPanel = createSubBillPanel();

    JComponent jdChargePanel = createJdChargePanel();

    JTabbedPane itemTabPane = new JTabbedPane();
    itemTabPane.setMinimumSize(new Dimension(240, 300));

    itemTabPane.addTab("检案摘要", jazyPanel);
    itemTabPane.addTab("鉴定对象", jddxPanel);
    itemTabPane.addTab("送检材料", materialPanel);
    itemTabPane.addTab("鉴定要求", jdyqPanel);
    itemTabPane.addTab("协议事项", xysxPanel);
    itemTabPane.addTab("不受理原因", bslPanel);
    itemTabPane.addTab("历史鉴定", historyPanel);
    itemTabPane.addTab("鉴定收费", jdChargePanel);

    workPanel.add(itemTabPane, BorderLayout.CENTER);

    workPanel.add(footPanel, BorderLayout.SOUTH);

    JScrollPane js = new JScrollPane(workPanel);
    this.add(js, BorderLayout.CENTER);
  }

  private JComponent createJdChargePanel() {
    // TCJLODO Auto-generated method stub
    jdChargeTablePanel.init();

    jdChargeTablePanel.setPanelId(this.getClass().getName() + "_JdChargeTablePanel");

    jdChargeTablePanel.getSearchBar().setVisible(false);

    jdChargeTablePanel.setTablePreferencesKey(this.getClass().getName() + "_JdChargeTablePanel");

    jdChargeTablePanel.getTable().setShowCheckedColumn(true);

    jdChargeTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));

    JFuncToolBar detailBtnBar = new JFuncToolBar();

    FuncButton addBtn2 = new SubaddButton(false);

    JButton insertBtn2 = new SubinsertButton(false);

    JButton delBtn2 = new SubdelButton(false);

    detailBtnBar.add(addBtn2);

    detailBtnBar.add(insertBtn2);

    detailBtnBar.add(delBtn2);

    jdChargeTablePanel.add(detailBtnBar, BorderLayout.SOUTH);

    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfChargeDetail item = new SfChargeDetail();
        setJdChargeDefaultValue(item);
        int rowNum = addSub(jdChargeTablePanel, item);
        jdChargeTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfChargeDetail item = new SfChargeDetail();
        setJdChargeDefaultValue(item);
        int rowNum = insertSub(jdChargeTablePanel, item);
        jdChargeTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(jdChargeTablePanel);
      }
    });
    return jdChargeTablePanel;
  }

  protected void setJdChargeDefaultValue(SfChargeDetail item) {
    // TCJLODO Auto-generated method stub
    item.setTempId("" + System.currentTimeMillis());
    SfEntrust entrust = (SfEntrust) this.listCursor.getCurrentObject();
    item.setEntrustId(entrust.getEntrustId());
  }

  protected JPanel initJdlslPanel(Class billClass, BillElementMeta eleMeta, List<AbstractFieldEditor> editors) {
    int row = 0;
    int col = 0;

    List<BillElement> notNullFields = eleMeta.getNotNullBillElement();
    JPanel topPanel = new JPanel(), centerPanel = new JPanel(), contentPanl = new JPanel();
    topPanel.setLayout(new GridBagLayout());
    List<AbstractFieldEditor> centerPanelEditors = new ArrayList<AbstractFieldEditor>();

    for (int i = 0; i < editors.size(); i++) {
      AbstractFieldEditor comp = editors.get(i);
      if (comp.isVisible()) {
        if (comp instanceof NewLineFieldEditor) {
          row++;
          col = 0;
          continue;
        } else if (comp instanceof TextAreaFieldEditor) {
          centerPanelEditors.add(comp);
          /*// 转到新的一行
          row++;
          col = 0;
          JLabel label = new JLabel(getLabelText(comp));
          if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
            label.setText(label.getText() + "*");
            label.setForeground(new Color(254, 100, 1));
            label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
          }
          comp.setPreferredSize(new Dimension(150 * comp.getOccCol(), comp.getOccRow() * 26));
          topPanel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 0,
            4, 4), 0, 0));
          topPanel.add(comp, new GridBagConstraints(col + 1, row, comp.getOccCol(), comp.getOccRow(), 1.0, 1.0, GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL, new Insets(4, 0, 4, 4), 0, 0));
          // 将当前所占的行空间偏移量计算上
          row += comp.getOccRow();
          col = 0;*/
          continue;
        }

        JLabel label = new JLabel(comp.getName());
        if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
          label.setText(label.getText() + "*");
          label.setForeground(new Color(254, 100, 1));
          label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
        }
        comp.setPreferredSize(new Dimension(150, 23));
        topPanel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
          new Insets(5, 0, 5, 5), 0, 0));
        topPanel.add(comp, new GridBagConstraints(col + 1, row, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,
          0, 5, 5), 0, 0));
        if (col == colCount * 2 - 2) {
          row++;
          col = 0;
        } else {
          col += 2;
        }
      }
    }
    centerPanel.setLayout(new GridLayout(centerPanelEditors.size(), 1));
    for (int i = 0; i < centerPanelEditors.size(); i++) {
      AbstractFieldEditor comp = centerPanelEditors.get(i);
      JPanel p = new JPanel();
      p.setLayout(new BorderLayout());
      JLabel label = new JLabel(getLabelText(comp), SwingConstants.RIGHT);
      if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
        label.setText(label.getText() + "*");
        label.setForeground(new Color(254, 100, 1));
        label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
      }
      comp.setPreferredSize(new Dimension(150 * comp.getOccCol(), comp.getOccRow() * 26));
      p.add(label, BorderLayout.WEST);
      p.add(comp, BorderLayout.CENTER);
      centerPanel.add(p);
    }
    contentPanl.setLayout(new BorderLayout());
    contentPanl.add(topPanel, BorderLayout.NORTH);
    contentPanl.add(centerPanel, BorderLayout.CENTER);
    return contentPanl;
  }

  protected JPanel initBslPanel(Class billClass, BillElementMeta eleMeta, List<AbstractFieldEditor> editors) {

    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    List<BillElement> notNullFields = eleMeta.getNotNullBillElement();
    for (int i = 0; i < bslFieldEditors.size(); i++) {
      JPanel p = new JPanel();
      p.setLayout(new FlowLayout(FlowLayout.LEFT));
      AbstractFieldEditor comp = editors.get(i);
      if (comp instanceof NewLineFieldEditor) {
        continue;
      } else {
        JLabel label = new JLabel(getLabelText(comp), SwingConstants.RIGHT);
        if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
          label.setText(label.getText() + "*");
          label.setForeground(new Color(254, 100, 1));
          label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
        }
        label.setPreferredSize(new Dimension(120, 25));
        if (comp instanceof TextAreaFieldEditor) {
          comp.setPreferredSize(new Dimension(230 * comp.getOccCol(), comp.getOccRow() * 26));
        } else {
          comp.setPreferredSize(new Dimension(150, 23));
        }
        p.add(label);
        p.add(comp);
      }
      contentPanel.add(p);
    }
    return contentPanel;
  }

  protected JPanel initJddxPanel(Class billClass, BillElementMeta eleMeta, List<AbstractFieldEditor> editors) {

    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    List<BillElement> notNullFields = eleMeta.getNotNullBillElement();
    for (int i = 0; i < jddxFieldEditors.size(); i++) {
      JPanel p = new JPanel();
      p.setLayout(new FlowLayout(FlowLayout.LEFT));
      AbstractFieldEditor comp = editors.get(i);
      if (comp instanceof NewLineFieldEditor) {
        continue;
      } else {
        JLabel label = new JLabel(getLabelText(comp), SwingConstants.RIGHT);
        if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
          label.setText(label.getText() + "*");
          label.setForeground(new Color(254, 100, 1));
          label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
        }
        label.setPreferredSize(new Dimension(120, 25));
        if (comp instanceof TextAreaFieldEditor) {
          comp.setPreferredSize(new Dimension(230 * comp.getOccCol(), comp.getOccRow() * 26));
        } else {
          comp.setPreferredSize(new Dimension(150, 23));
        }
        p.add(label);
        p.add(comp);
      }
      contentPanel.add(p);
    }
    return contentPanel;
  }

  protected JPanel initJazyPanel(Class billClass, BillElementMeta eleMeta, List<AbstractFieldEditor> editors) {

    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new BorderLayout());
    List<BillElement> notNullFields = eleMeta.getNotNullBillElement();
    for (int i = 0; i < jazyFieldEditors.size(); i++) {
      JPanel p = new JPanel();
      p.setLayout(new FlowLayout(FlowLayout.LEFT));
      AbstractFieldEditor comp = editors.get(i);
      if (comp instanceof NewLineFieldEditor) {
        continue;
      } else {
        JLabel label = new JLabel(getLabelText(comp));
        if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
          label.setText(label.getText() + "*");
          label.setForeground(new Color(254, 100, 1));
          label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
        }
        label.setPreferredSize(new Dimension(120, 25));
        if (comp instanceof TextAreaFieldEditor) {
          comp.setPreferredSize(new Dimension(230 * comp.getOccCol(), comp.getOccRow() * 26));
        } else {
          comp.setPreferredSize(new Dimension(150, 23));
        }
        p.add(label);
        p.add(comp);
      }
      if (i == 0) {
        contentPanel.add(p, BorderLayout.CENTER);
      } else {
        contentPanel.add(p, BorderLayout.SOUTH);
      }
    }
    return contentPanel;
  }
  protected JPanel initJdyqPanel(Class billClass, BillElementMeta eleMeta, List<AbstractFieldEditor> editors) {

    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new BorderLayout());
    List<BillElement> notNullFields = eleMeta.getNotNullBillElement();
    for (int i = 0; i < jdyqFieldEditors.size(); i++) {
      JPanel p = new JPanel();
      p.setLayout(new FlowLayout(FlowLayout.LEFT));
      AbstractFieldEditor comp = editors.get(i);
      if (comp instanceof NewLineFieldEditor) {
        continue;
      } else {
        JLabel label = new JLabel(getLabelText(comp));
        if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
          label.setText(label.getText() + "*");
          label.setForeground(new Color(254, 100, 1));
          label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
        }
        label.setPreferredSize(new Dimension(120, 25));
        if (comp instanceof TextAreaFieldEditor) {
          comp.setPreferredSize(new Dimension(230 * comp.getOccCol(), comp.getOccRow() * 26));
        } else {
          comp.setPreferredSize(new Dimension(150, 23));
        }
        p.add(label);
        p.add(comp);
      }
      if (i == 0) {
        contentPanel.add(p, BorderLayout.CENTER);
      } else {
        contentPanel.add(p, BorderLayout.SOUTH);
      }
    }
    return contentPanel;
  }

  protected JPanel initFieldEditorPanel(Class billClass, BillElementMeta eleMeta, List<AbstractFieldEditor> editors) {
    int row = 0;
    int col = 0;

    List<BillElement> notNullFields = eleMeta.getNotNullBillElement();
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new GridBagLayout());

    for (int i = 0; i < editors.size(); i++) {
      AbstractFieldEditor comp = editors.get(i);
      if (comp.isVisible()) {
        if (comp instanceof NewLineFieldEditor) {
          row++;
          col = 0;
          continue;
        } else if (comp instanceof TextAreaFieldEditor) {
          // 转到新的一行
          row++;
          col = 0;
          JLabel label = new JLabel(getLabelText(comp));
          if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
            label.setText(label.getText() + "*");
            label.setForeground(new Color(254, 100, 1));
            label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
          }
          comp.setPreferredSize(new Dimension(150 * comp.getOccCol(), comp.getOccRow() * 26));
          contentPanel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 0,
            4, 4), 0, 0));
          contentPanel.add(comp, new GridBagConstraints(col + 1, row, comp.getOccCol(), comp.getOccRow(), 1.0, 1.0, GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL, new Insets(4, 0, 4, 4), 0, 0));
          // 将当前所占的行空间偏移量计算上
          row += comp.getOccRow();
          col = 0;
          continue;
        }

        JLabel label = new JLabel(comp.getName());
        if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
          label.setText(label.getText() + "*");
          label.setForeground(new Color(254, 100, 1));
          label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
        }
        comp.setPreferredSize(new Dimension(150, 23));
        contentPanel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0,
          5, 5), 0, 0));
        contentPanel.add(comp, new GridBagConstraints(col + 1, row, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
          new Insets(5, 0, 5, 5), 0, 0));
        if (col == colCount * 2 - 2) {
          row++;
          col = 0;
        } else {
          col += 2;
        }
      }
    }
    return contentPanel;
  }

  public void setEditingObject(WfAware editingObject) {

    this.editingObject = editingObject;

    updateFieldEditors(headFieldEditors);
    updateFieldEditors(jazyFieldEditors);
    updateFieldEditors(jdyqFieldEditors);
    updateFieldEditors(jddxFieldEditors);
    updateFieldEditors(bslFieldEditors);
    updateFieldEditors(historyFieldEditors);
    updateFieldEditors(xysxFieldEditors);
    updateFieldEditors(footFieldEditors);

    SfEntrust bill = this.listCursor.getCurrentObject();
    XysxPanelUtil.setValue(bill, xysxComponents);

  }

  protected void updateFieldEditors(List<AbstractFieldEditor> editors) {

    for (AbstractFieldEditor editor : editors) {
      editor.setEditObject(editingObject);
    }

  }

  public JComponent createSubBillPanel() {

    materialsTablePanel.init();

    materialsTablePanel.setPanelId(this.getClass().getName() + "_materialTablePanel");

    materialsTablePanel.getSearchBar().setVisible(false);

    materialsTablePanel.setTablePreferencesKey(this.getClass().getName() + "__materialTable");

    materialsTablePanel.getTable().setShowCheckedColumn(true);

    materialsTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));

    JFuncToolBar materialBtnBar = new JFuncToolBar();

    FuncButton addBtn2 = new SubaddButton(false);

    JButton insertBtn2 = new SubinsertButton(false);

    JButton delBtn2 = new SubdelButton(false);

    materialBtnBar.add(addBtn2);

    materialBtnBar.add(insertBtn2);

    materialBtnBar.add(delBtn2);

    materialsTablePanel.add(materialBtnBar, BorderLayout.SOUTH);

    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfMaterials item = new SfMaterials();
        setMaterialDefaultValue(item);
        int rowNum = addSub(materialsTablePanel, item);
        materialsTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfMaterials item = new SfMaterials();
        setMaterialDefaultValue(item);
        int rowNum = insertSub(materialsTablePanel, item);
        materialsTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(materialsTablePanel);
      }
    });
    return materialsTablePanel;
    //    return null;
  }

  protected void setMaterialDefaultValue(SfMaterials item) {
    // TCJLODO Auto-generated method stub
    item.setTempId("" + System.currentTimeMillis());
    SfEntrust e = listCursor.getCurrentObject();
    item.setEntrustId(e.getEntrustId());
    item.setMaterialType(SfMaterials.SF_VS_MATERIAL_TYPE_jiancai);
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

    SfEntrust bill = this.listCursor.getCurrentObject();

    //    System.out.println("doSend 1++++++++++++++++++++++++++=" + bill.getAcceptDate());
    XysxPanelUtil.getValue(bill, xysxComponents);

    /* if (bill.getJdFzr() == null) {
       JOptionPane.showMessageDialog(this, "请指定鉴定负责人.", "提示", JOptionPane.INFORMATION_MESSAGE);
       return;
     }*/

    SfEntrust afterSaveBill = null;

    /* if (this.isDataChanged()) {
       JOptionPane.showMessageDialog(this, "数据发生改变，请先保存.", "提示", JOptionPane.INFORMATION_MESSAGE);
       return;
     }*/

    try {
      requestMeta.setFuncId(this.sendButton.getFuncId());
      //      System.out.println("doSend 2++++++++++++++++++++++++++=" + bill.getAcceptDate());
      SfEntrust qx = (SfEntrust) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      //      System.out.println("doSend 3++++++++++++++++++++++++++=" + qx.getAcceptDate());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfEntrustServiceDelegate.newCommitFN(qx, requestMeta);
      //      System.out.println("doSend 4++++++++++++++++++++++++++=" + afterSaveBill.getAcceptDate());
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
      success = false;
      UIUtilities.showStaickTraceDialog(ex, this, "错误", ex.getMessage());
    }

    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      JOptionPane.showMessageDialog(this, "送审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    }
  }

  /**
   * 审核
   */
  protected void doSuggestPass() {

    SfEntrust bill = this.listCursor.getCurrentObject();

    //    System.out.println("doSuggestPass 1++++++++++++++++++++++++++=" + bill.getAcceptDate());
    XysxPanelUtil.getValue(bill, xysxComponents);

    if (!checkBeforeSave()) {
      return;
    }
    SfEntrust qx = (SfEntrust) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    //    System.out.println("doSuggestPass 2++++++++++++++++++++++++++=" + qx.getAcceptDate());
    requestMeta.setFuncId(this.suggestPassButton.getFuncId());
    GkCommentDialog commentDialog = new GkCommentDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),
      ModalityType.APPLICATION_MODAL);
    if (commentDialog.cancel) {
      return;
    }
    boolean success = true;
    String errorInfo = "";
    try {
      qx.setComment(commentDialog.getComment());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      //      System.out.println("doSuggestPass 3++++++++++++++++++++++++++=" + qx.getAcceptDate());
      qx = sfEntrustServiceDelegate.auditFN(qx, requestMeta);
      //      System.out.println("doSuggestPass 4++++++++++++++++++++++++++=" + qx.getAcceptDate());
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(this, "审核成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "审核失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * 销审
   */
  protected void doUnAudit() {
    SfEntrust qx = (SfEntrust) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    boolean success = true;
    SfEntrust afterSaveBill = null;
    String errorInfo = "";
    int i = JOptionPane.showConfirmDialog(this, "是否确定消审？", "确认", JOptionPane.INFORMATION_MESSAGE);
    if (i != 0) {
      return;
    }
    try {
      requestMeta.setFuncId(unAuditButton.getFuncId());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfEntrustServiceDelegate.unAuditFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      JOptionPane.showMessageDialog(this, "销审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "销审失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * 退回
   */
  protected void doUnTread() {
    GkCommentUntreadDialog commentDialog = new GkCommentUntreadDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),
      ModalityType.APPLICATION_MODAL);
    if (commentDialog.cancel) {
      return;
    }
    boolean success = true;
    SfEntrust afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(unTreadButton.getFuncId());
      SfEntrust qx = (SfEntrust) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx.setComment(commentDialog.getComment());
      afterSaveBill = sfEntrustServiceDelegate.untreadFN(qx, requestMeta);
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
    SfEntrust afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(this.callbackButton.getFuncId());
      SfEntrust qx = (SfEntrust) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfEntrustServiceDelegate.callbackFN(qx, requestMeta);
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
