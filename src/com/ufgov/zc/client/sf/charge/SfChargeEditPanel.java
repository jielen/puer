package com.ufgov.zc.client.sf.charge;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.smartclient.component.table.fixedtable.JPageableFixedTable;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfChargeToTableModelConverter;
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
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.celleditor.MoneyCellEditor;
import com.ufgov.zc.client.component.table.celleditor.TextCellEditor;
import com.ufgov.zc.client.component.table.cellrenderer.NumberCellRenderer;
import com.ufgov.zc.client.component.table.codecelleditor.AsValComboBoxCellEditor;
import com.ufgov.zc.client.component.table.codecellrenderer.AsValCellRenderer;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldCellEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.MoneyFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowUtil;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.util.SfUserSelectHandler;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfCharge;
import com.ufgov.zc.common.sf.model.SfChargeDetail;
import com.ufgov.zc.common.sf.model.SfChargeStandard;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.publish.ISfChargeServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.system.util.Utils;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfChargeEditPanel extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -3538752096000004615L;

  private static final Logger logger = Logger.getLogger(SfChargeEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_CHARGE";

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

  protected ListCursor<SfCharge> listCursor;

  private SfCharge oldCharge;

  public SfChargeListPanel listPanel;

  protected SfChargeEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  protected JTablePanel detailTablePanel = new JTablePanel();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfChargeServiceDelegate sfChargeServiceDelegate;

  public SfChargeEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfChargeListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfChargeEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfChargeServiceDelegate = (ISfChargeServiceDelegate) ServiceFactory.create(ISfChargeServiceDelegate.class, "sfChargeServiceDelegate");
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

    SfCharge charge = (SfCharge) listCursor.getCurrentObject();

    if (charge != null) {//列表页面双击进入
      if (charge.getChargeId() != null) {
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
        charge = sfChargeServiceDelegate.selectByPrimaryKey(charge.getChargeId(), this.requestMeta);
        listCursor.setCurrentObject(charge);
        this.setEditingObject(charge);
      } else if (charge.getEntrustId() != null) {//图形界面进来的新增，已经确定了entrust
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
        setDefaultValue(charge);
        this.setEditingObject(charge);
      }
    } else {//新增按钮进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      charge = new SfCharge();
      setDefaultValue(charge);
      listCursor.getDataList().add(charge);
      listCursor.setCurrentObject(charge);
      this.setEditingObject(charge);
    }
    refreshSubData();
    setOldObject();
    setButtonStatus();
    updateFieldEditorsEditable();
  }

  private void setDefaultValue(SfCharge Charge) {
    // TCJLODO Auto-generated method stub
    Charge.setStatus(ZcSettingConstants.WF_STATUS_DRAFT);
    Charge.setNd(this.requestMeta.getSvNd());
    Charge.setInputDate(this.requestMeta.getSysDate());
    Charge.setInputor(requestMeta.getSvUserID());
    Charge.setCashDate(this.requestMeta.getSysDate());
    Charge.setCashier(this.requestMeta.getSvUserID());
  }

  private void refreshSubData() {
    // TCJLODO Auto-generated method stub
    SfCharge Charge = (SfCharge) listCursor.getCurrentObject();
    detailTablePanel.setTableModel(SfChargeToTableModelConverter.convertChargeDetailsTableData(Charge.getChargeDetaillst()));
    ZcUtil.translateColName(detailTablePanel.getTable(), SfChargeToTableModelConverter.getDetailInfo());
    setTablePorperty();
    addTableLisenter();
  }

  private void addTableLisenter() {
    // TCJLODO Auto-generated method stub

    final JPageableFixedTable table = detailTablePanel.getTable();
    final BeanTableModel model = (BeanTableModel) (table.getModel());

    model.addTableModelListener(new TableModelListener() {

      public void tableChanged(TableModelEvent e) {

        if (e.getType() == TableModelEvent.UPDATE) {

          if (e.getColumn() >= 0 && table.getSelectedRow() >= 0) {
            SfCharge bill = (SfCharge) listCursor.getCurrentObject();
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
              bill.setTotalPrice(num);
              setEditingObject(bill);
            } else if (SfChargeDetail.COL_TOTAL_PRICE.equals(model.getColumnIdentifier(e.getColumn()))) {

              BigDecimal num = BigDecimal.ZERO;
              for (int i = 0; i < table.getRowCount(); i++) {
                SfChargeDetail row = (SfChargeDetail) (model.getBean(table.convertRowIndexToModel(i)));
                if (row.getTotalPrice() != null) {
                  num = num.add(row.getTotalPrice());
                }
              }
              bill.setTotalPrice(num);
              setEditingObject(bill);

            }
          }
        }
      }

    });
  }

  private void setTablePorperty() {
    final JPageableFixedTable table = detailTablePanel.getTable();
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

  protected void updateFieldEditorsEditable() {
    SfCharge qx = (SfCharge) listCursor.getCurrentObject();
    Long processInstId = qx.getProcessInstId();
    if (processInstId != null && processInstId.longValue() > 0) {
      // 工作流的单据
      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(qx, requestMeta);
      if ("cancel".equals(this.oldCharge.getStatus())) {// 撤销单据设置字段为不可编辑
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
          if ("status".equals(editor.getFieldName()) || "totalPrice".equals(editor.getFieldName()) || "nd".equals(editor.getFieldName())) {
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

    setWFSubTableEditable(detailTablePanel, isEdit);
  }

  protected void setButtonStatus() {
    SfCharge Charge = (SfCharge) listCursor.getCurrentObject();
    setButtonStatus(Charge, requestMeta, this.listCursor);

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

    SfCharge Charge = (SfCharge) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, Charge.getStatus(), this.pageStatus, getCompoId(), Charge.getProcessInstId());

  }

  protected void setOldObject() {

    oldCharge = (SfCharge) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

    toolBar.add(addButton);

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

    toolBar.add(printButton);

    toolBar.add(traceButton);

    //    toolBar.add(previousButton);

    //    toolBar.add(nextButton);

    toolBar.add(exitButton);

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

        doPrintButton();

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

  protected void doAdd() {
    // TCJLODO Auto-generated method stub
    listCursor.setCurrentObject(null);
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

        listCursor.setCurrentObject(oldCharge);

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

        listCursor.setCurrentObject(oldCharge);

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

    SfCharge Charge = (SfCharge) this.listCursor.getCurrentObject();

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      Charge = sfChargeServiceDelegate.saveFN(Charge, this.requestMeta);

      listCursor.setCurrentObject(Charge);

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
    SfCharge charge = (SfCharge) this.listCursor.getCurrentObject();
    if (listPanel != null && listPanel.getParent() instanceof JClosableTabbedPane) {
      return;
    }
    if (parent instanceof SfChargeDialog) {//新增的，创建数据流界面
      SfDataFlowDialog d = new SfDataFlowDialog(compoId, SfDataFlowUtil.getEntrust(charge.getEntrustId()), listPanel);
      parent.dispose();
    } else {
      SfDataFlowDialog d = (SfDataFlowDialog) parent;
      d.refresh(charge.getEntrustId());
    }
  }

  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfCharge Charge = (SfCharge) this.listCursor.getCurrentObject();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(Charge, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString());
    }
    SfCharge charge = (SfCharge) this.listCursor.getCurrentObject();
    if (charge.getTotalPrice() == null || charge.getTotalPrice().doubleValue() == 0) {
      errorInfo.append("\n").append("没有填写费用.");
    }
    for (int i = 0; i < charge.getChargeDetaillst().size(); i++) {
      SfChargeDetail d = (SfChargeDetail) charge.getChargeDetaillst().get(i);
      if (d.getChargeStandardName() == null && (d.getRemark() == null || d.getRemark().trim().length() == 0)) {
        errorInfo.append("\n").append("收费明细要指明").append(LangTransMeta.translate(SfChargeDetail.COL_CHARGE_STANDARD_NAME));
        errorInfo.append("或者在").append(LangTransMeta.translate(SfChargeDetail.COL_REMARK)).append("说明收费原因");
      }
    }
    if (errorInfo.length() != 0) {
      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

  protected void doDelete() {
    requestMeta.setFuncId(deleteButton.getFuncId());
    SfCharge Charge = (SfCharge) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        sfChargeServiceDelegate.deleteByPrimaryKeyFN(Charge.getChargeId(), requestMeta);
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
    return !DigestUtil.digest(oldCharge).equals(DigestUtil.digest(listCursor.getCurrentObject()));
  }

  private void doPrintButton() {

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
          SfCharge currentBill = (SfCharge) listCursor.getCurrentObject();
          SfEntrust entrust = (SfEntrust) obj;
          currentBill.setEntrustId(entrust.getEntrustId());
          currentBill.setEntrustCode(entrust.getCode());
          currentBill.setName(entrust.getName() + "鉴定费用");
          setEditingObject(currentBill);
        }
      }

      public void afterClear() {
        SfCharge currentBill = (SfCharge) listCursor.getCurrentObject();
        currentBill.setEntrustId(null);
        currentBill.setEntrustCode(null);
        currentBill.setName(null);
        setEditingObject(currentBill);
      }
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("SF_CHARGE");
    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler,
      entrustHandler.getColumNames(), LangTransMeta.translate(SfCharge.COL_ENTRUST_CODE), "entrustCode");

    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfCharge.COL_NAME), "name");
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfCharge.COL_STATUS), "status", SfCharge.SF_VS_CHARGE_STATUS);
    MoneyFieldEditor totalPrice = new MoneyFieldEditor(LangTransMeta.translate(SfCharge.COL_TOTAL_PRICE), "totalPrice");
    TextFieldEditor payer = new TextFieldEditor(LangTransMeta.translate(SfCharge.COL_PAYER), "payer");
    SfUserSelectHandler userHandler = new SfUserSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfCharge currentBill = (SfCharge) listCursor.getCurrentObject();
          User user = (User) obj;
          currentBill.setCashier(user.getUserId());
          setEditingObject(currentBill);
        }
      }

      public void afterClear() {
        SfCharge currentBill = (SfCharge) listCursor.getCurrentObject();
        currentBill.setCashier(null);
        setEditingObject(currentBill);
      }
    };
    dto = new ElementConditionDto();
    dto.setCoCode("000");
    dto.setNd(this.requestMeta.getSvNd());
    ForeignEntityFieldEditor cashier = new ForeignEntityFieldEditor(userHandler.getSqlId(), dto, 20, userHandler, userHandler.getColumNames(),
      LangTransMeta.translate(SfCharge.COL_CASHIER), "cashierName");
    //    TextFieldEditor cashier = new TextFieldEditor(LangTransMeta.translate(SfCharge.COL_CASHIER), "cashierName");
    DateFieldEditor cashDate = new DateFieldEditor(LangTransMeta.translate(SfCharge.COL_CASH_DATE), "cashDate");
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfCharge.COL_REMARK), "remark", 100, 2, 5);

    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfCharge.COL_INPUTOR), "inputor");
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfCharge.COL_INPUT_DATE), "inputDate");

    editorList.add(entrust);
    editorList.add(name);
    editorList.add(status);

    editorList.add(totalPrice);
    editorList.add(payer);
    editorList.add(new NewLineFieldEditor());

    editorList.add(cashier);
    editorList.add(cashDate);
    editorList.add(new NewLineFieldEditor());

    editorList.add(remark);

    return editorList;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {

    JTabbedPane itemTabPane = new JTabbedPane();

    detailTablePanel.init();

    detailTablePanel.setPanelId(this.getClass().getName() + "_detailTablePanel");

    detailTablePanel.getSearchBar().setVisible(false);

    detailTablePanel.setTablePreferencesKey(this.getClass().getName() + "__detailTable");

    detailTablePanel.getTable().setShowCheckedColumn(true);

    detailTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));

    itemTabPane.addTab("收费明细", detailTablePanel);

    JFuncToolBar detailBtnBar = new JFuncToolBar();

    FuncButton addBtn2 = new SubaddButton(false);

    JButton insertBtn2 = new SubinsertButton(false);

    JButton delBtn2 = new SubdelButton(false);

    detailBtnBar.add(addBtn2);

    detailBtnBar.add(insertBtn2);

    detailBtnBar.add(delBtn2);

    detailTablePanel.add(detailBtnBar, BorderLayout.SOUTH);

    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfChargeDetail item = new SfChargeDetail();
        setPersonDefaultValue(item);
        int rowNum = addSub(detailTablePanel, item);
        detailTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfChargeDetail item = new SfChargeDetail();
        setPersonDefaultValue(item);
        int rowNum = insertSub(detailTablePanel, item);
        detailTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(detailTablePanel);
      }
    });
    itemTabPane.setMinimumSize(new Dimension(240, 300));
    return itemTabPane;
  }

  protected void setPersonDefaultValue(SfChargeDetail item) {
    // TCJLODO Auto-generated method stub
    item.setTempId("" + System.currentTimeMillis());
    SfCharge e = listCursor.getCurrentObject();
    //    item.setChargeId(e.getChargeId());
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

    SfCharge afterSaveBill = null;

    if (this.isDataChanged()) {
      JOptionPane.showMessageDialog(this, "数据发生改变，请先保存.", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    try {
      requestMeta.setFuncId(this.sendButton.getFuncId());
      SfCharge qx = (SfCharge) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfChargeServiceDelegate.newCommitFN(qx, requestMeta);
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
    if (!checkBeforeSave()) {
      return;
    }
    SfCharge qx = (SfCharge) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
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
      qx = sfChargeServiceDelegate.auditFN(qx, requestMeta);
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
    SfCharge qx = (SfCharge) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    boolean success = true;
    SfCharge afterSaveBill = null;
    String errorInfo = "";
    int i = JOptionPane.showConfirmDialog(this, "是否确定消审？", "确认", JOptionPane.INFORMATION_MESSAGE);
    if (i != 0) {
      return;
    }
    try {
      requestMeta.setFuncId(unAuditButton.getFuncId());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfChargeServiceDelegate.unAuditFN(qx, requestMeta);
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
    SfCharge afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(unTreadButton.getFuncId());
      SfCharge qx = (SfCharge) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx.setComment(commentDialog.getComment());
      afterSaveBill = sfChargeServiceDelegate.untreadFN(qx, requestMeta);
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
    SfCharge afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(this.callbackButton.getFuncId());
      SfCharge qx = (SfCharge) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfChargeServiceDelegate.callbackFN(qx, requestMeta);
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
