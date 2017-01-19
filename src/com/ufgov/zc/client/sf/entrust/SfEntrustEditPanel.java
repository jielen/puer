package com.ufgov.zc.client.sf.entrust;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Component;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.smartclient.component.table.fixedtable.JPageableFixedTable;
import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.UIConstants;
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
import com.ufgov.zc.client.component.button.ConfirmButton;
import com.ufgov.zc.client.component.button.CopyButton;
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
import com.ufgov.zc.client.component.button.SubCopyButton;
import com.ufgov.zc.client.component.button.SubaddButton;
import com.ufgov.zc.client.component.button.SubdelButton;
import com.ufgov.zc.client.component.button.SubinsertButton;
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.button.zc.CommonButton;
import com.ufgov.zc.client.component.element.UserTreeSelectDialog;
import com.ufgov.zc.client.component.sf.fieldeditor.SfEntrustorNewFieldEditor;
import com.ufgov.zc.client.component.sf.fieldeditor.SfJdTargetNewFieldEditor;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.celleditor.MoneyCellEditor;
import com.ufgov.zc.client.component.table.celleditor.TextCellEditor;
import com.ufgov.zc.client.component.table.cellrenderer.NumberCellRenderer;
import com.ufgov.zc.client.component.table.codecelleditor.AsValComboBoxCellEditor;
import com.ufgov.zc.client.component.table.codecelleditor.FileCellEditor;
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
import com.ufgov.zc.client.sf.entrustmanage.SfEntrustManageDialog;
import com.ufgov.zc.client.sf.entrustor.SfEntrustorHandler;
import com.ufgov.zc.client.sf.jdtarget.SfJdTargethandler;
import com.ufgov.zc.client.sf.util.SfJdPersonSelectHandler;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.util.freemark.IWordHandler;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.WordFileUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.commonbiz.model.BaseElement;
import com.ufgov.zc.common.commonbiz.model.BillElement;
import com.ufgov.zc.common.commonbiz.model.WfAware;
import com.ufgov.zc.common.sf.model.SfChargeDetail;
import com.ufgov.zc.common.sf.model.SfChargeStandard;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustManage;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdResultFile;
import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.sf.model.SfMajor;
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

  public FuncButton printMastTmButton = new CommonButton("fprintMastTm", "print.gif");

  public FuncButton printDetailTmConfirmButton = new CommonButton("fprintDetailTm", "print.gif");

  public FuncButton acceptBtn = new CommonButton("faccepted", "audit.jpg");

  public FuncButton unAccetpBtn = new CommonButton("fback", "untread.jpg");

  //送科室确认委托信息
  public FuncButton songkeshiBtn = new CommonButton("fsongkeshi", "audit.jpg");
  
  //同意委托信息，用于综合岗进行初审
  public FuncButton agreeWtInfoBtn = new CommonButton("fagreeWtInfo", "audit.jpg");

  public FuncButton getAcceptCodeBtn = new CommonButton("fgetAcceptCode", "audit.jpg");

  public FuncButton importButton = new ImportButton();

  //送国库
  private FuncButton sendGkButton = new SendGkButton();

  // 工作流填写意见审核通过
  protected FuncButton suggestPassButton = new SuggestAuditPassButton();

  // 工作流销审
  protected FuncButton unAuditButton = new UnauditButton();

  // 工作流退回
  protected FuncButton unTreadButton = new UntreadButton();
  
  public FuncButton delayBtn = new CommonButton("fdelay", "audit.jpg");
  public FuncButton pauseBtn = new CommonButton("fpause", "audit.jpg");
  public FuncButton stopBtn = new CommonButton("fstop", "audit.jpg");
  public FuncButton startBtn = new CommonButton("fstart", "audit.jpg");
  public FuncButton zhuansongBtn = new CommonButton("fzhuansong", "audit.jpg");

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

  private JTabbedPane subTabPanel;

  private JPanel bslPanel;

  private Hashtable<BigDecimal, JComponent> xysxComponents = new Hashtable<BigDecimal, JComponent>();// 协议事项部件

  final ElementConditionDto majorPersonDto = new ElementConditionDto();

  public SfEntrustEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfEntrustListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfEntrustEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");

    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId), TitledBorder.CENTER, TitledBorder.TOP,
      new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = parent;

    this.colCount = 4;

    WordFileUtil.setDir("sf");

    init();

    requestMeta.setCompoId(getCompoId());

    refreshData();

    //    setButtonStatus();

    //    updateFieldEditorsEditable();
  }

  public void refreshData() {
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

    hideCols();
    hideTabs();
  }

  private void hideTabs() {
    SfEntrust entrust = (SfEntrust) listCursor.getCurrentObject();

    if (SfUtil.isWtf() && (entrust.getIsAccept() == null || entrust.getIsAccept().equalsIgnoreCase("n"))) {
      subTabPanel.remove(bslPanel);
    }
  }

  private void hideCols() {

    if (SfUtil.isWtf()) {
      JPageableFixedTable ta = materialsTablePanel.getTable();
      hideCol(ta, SfMaterials.COL_SAVE_ADDRESS);
    }
  }

  private void setDefaultValue(SfEntrust entrust) {
    // TCJLODO Auto-generated method stub

    entrust.setStatus(ZcSettingConstants.WF_STATUS_DRAFT);
    entrust.setNd(this.requestMeta.getSvNd());
    entrust.setInputDate(SfUtil.getSysDate());
    entrust.setInputor(requestMeta.getSvUserID());
    if (SfUtil.isJdjg()) {
      entrust.setAcceptDate(SfUtil.getSysDate());
      entrust.setAcceptor(requestMeta.getSvUserID());
      entrust.setCoCode(requestMeta.getSvCoCode());//设定鉴定机构，目前是满足单一普洱市鉴定所，后续支持多家时，这个值需要在界面上选择
      entrust.setJdCompany(requestMeta.getSvCoName());//
    } else if (SfUtil.isWtf()) {
      //设置委托方
      SfEntrustor entrustor = getEntrustor();
      entrust.setEntrustor(entrustor);
//      entrust.setCoCode("000");//设定鉴定机构，目前是满足单一普洱市鉴定所，后续支持多家时，这个值需要在界面上选择
      entrust.setJdCompany(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME));//    	
    } else {
//      entrust.setCoCode("000");//设定鉴定机构，目前是满足单一普洱市鉴定所，后续支持多家时，这个值需要在界面上选择
      entrust.setJdCompany(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME));//      	
    }
    entrust.setWtDate(SfUtil.getSysDate());
    entrust.setJdDocSendType(SfEntrust.SF_VS_ENTRUST_DOC_SEND_TYPE_ZIQU);
    //获取空的协议事项
    List xysxLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfXysxMapper.selectByPrimaryKey", null, requestMeta);
    xysxLst = xysxLst == null ? new ArrayList() : xysxLst;
    entrust.setXysxLst(xysxLst);
    entrust.setIsCxjd("N");
    entrust.setUrgentLevel(SfEntrust.SF_VS_ENTRUST_URGENT_LEVEL_normal);
    entrust.setExpectedTime(new BigDecimal(7));
  }

  private SfEntrustor getEntrustor() {
    SfEntrustor rtn = (SfEntrustor) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.selectByLoginAccount", requestMeta.getSvUserID(), requestMeta);
    return rtn == null ? new SfEntrustor() : rtn;
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
            if (SfChargeDetail.COL_PRICE.equals(model.getColumnIdentifier(e.getColumn())) || SfChargeDetail.COL_QUANTITY.equals(model.getColumnIdentifier(e.getColumn()))) {

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
        if (k < 0) { return; }

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
    ForeignEntityFieldCellEditor foreignExpertCodeEditor = new ForeignEntityFieldCellEditor(handler.getSqlId(), dto, 20, handler, handler.getColumNames(), "收费标准", "name");

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

    FileCellEditor fileEditor = new FileCellEditor("attachFileBlobid", false, (BeanTableModel) table.getModel());
    fileEditor.setDownloadFileEnable(true);
    SwingUtil.setTableCellEditor(table, SfMaterials.COL_ATTACH_FILE, fileEditor);
    SwingUtil.setTableCellEditor(table, SfMaterials.COL_JIAN_HOU_STORE_TIME, new MoneyCellEditor(false));
    SwingUtil.setTableCellRenderer(table, SfMaterials.COL_JIAN_HOU_STORE_TIME, new NumberCellRenderer());
    SwingUtil.setTableCellEditor(table, SfMaterials.COL_JIAN_HOU_CHULI_TYPE, new AsValComboBoxCellEditor(SfMaterials.SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE));
    SwingUtil.setTableCellRenderer(table, SfMaterials.COL_JIAN_HOU_CHULI_TYPE, new AsValCellRenderer(SfMaterials.SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE));
    /*table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    TableColumn c=table.getColumn(SfMaterials.COL_NAME);
    c.setPreferredWidth(200);*/
  }

  protected void updateFieldEditorsEditable() {

    SfEntrust qx = (SfEntrust) listCursor.getCurrentObject();
    Long processInstId = qx.getProcessInstId();
    isEdit = false;
    if (processInstId != null && processInstId.longValue() > 0) {
      // 工作流的单据
      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(qx, requestMeta);
      //科室受理的可写字段
      if (wfCanEditFieldMap == null || wfCanEditFieldMap.isEmpty()) {
        wfCanEditFieldMap = getKeshiShouliEnableField(qx, requestMeta);
      }
      //综合值班的可写字段
      if (wfCanEditFieldMap == null || wfCanEditFieldMap.isEmpty()) {
        wfCanEditFieldMap = getZongheShouliEnableField(qx, requestMeta);
      }
      if ("cancel".equals(this.oldentrust.getStatus())) {// 撤销单据设置字段为不可编辑
        wfCanEditFieldMap = null;
      }

      for (AbstractFieldEditor editor : fieldEditors) {
        // 工作流中定义可编辑的字段
        //        System.out.println(editor.getFieldName());
        if (editor instanceof NewLineFieldEditor) continue;
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
          if ("code".equals(editor.getFieldName()) || "status".equals(editor.getFieldName()) || "inputor".equals(editor.getFieldName()) || "inputDate".equals(editor.getFieldName())
            || "jdCharge".equals(editor.getFieldName()) || "acceptCode".equals(editor.getFieldName()) || "acceptDate".equals(editor.getFieldName()) || "acceptorName".equals(editor.getFieldName())) {
            editor.setEnabled(false);
          } else {
            if (SfUtil.isWtf()
              && ("isAccept".equals(editor.getFieldName()) || "jdAssistor".equals(editor.getFieldName()) || "jdFhr".equals(editor.getFieldName()) || "jdFzr".equals(editor.getFieldName())
                || "jdFzrName".equals(editor.getFieldName()) || "jdFhrName".equals(editor.getFieldName()) || "entrustor.name".equals(editor.getFieldName()) || "jdAssistorName".equals(editor
                .getFieldName()))) {//委托方不能设置是否受理
              editor.setEnabled(false);
            } else {
              editor.setEnabled(true);
            }
          }
        //鉴定机构的选择，委托方可以选，鉴定机构的人登录时不可选
          if ("coCode".equals(editor.getFieldName())){
            if(SfUtil.isWtf()){
              editor.setEnabled(true);
            }else{
              editor.setEnabled(false);
            }
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
    //特殊业务处理
    //在等待接收检材及以后的业务环节中，可以打印委托书
    if(SfUtil.isWtf()){
      if(entrust.getStatus().equalsIgnoreCase("4")
        ||entrust.getStatus().equalsIgnoreCase("5")
        ||entrust.getStatus().equalsIgnoreCase("8")
        ||entrust.getStatus().equalsIgnoreCase("10")
        ||entrust.getStatus().equalsIgnoreCase("complete")
        ||entrust.getStatus().equalsIgnoreCase("doing")
        ||entrust.getStatus().equalsIgnoreCase("exec")
        ||entrust.getStatus().equalsIgnoreCase("expire")
        ||entrust.getStatus().equalsIgnoreCase("pause")
        ||entrust.getStatus().equalsIgnoreCase("stop")
        ||entrust.getStatus().equalsIgnoreCase("delay")
        ){
        printWtButton.setVisible(true); 
      }
      delayBtn.setVisible(false);
      pauseBtn.setVisible(false);        
      stopBtn.setVisible(false);
      startBtn.setVisible(false);
      zhuansongBtn.setVisible(false);
      agreeWtInfoBtn.setVisible(false);
    }else{
      if(entrust.getStatus().equalsIgnoreCase("5")
        ||entrust.getStatus().equalsIgnoreCase("8")
        ||entrust.getStatus().equalsIgnoreCase("10")
        ||entrust.getStatus().equalsIgnoreCase("complete")
        ||entrust.getStatus().equalsIgnoreCase("doing")
        ||entrust.getStatus().equalsIgnoreCase("exec")
        ||entrust.getStatus().equalsIgnoreCase("expire")
        ||entrust.getStatus().equalsIgnoreCase("pause")
        ||entrust.getStatus().equalsIgnoreCase("stop")
        ||entrust.getStatus().equalsIgnoreCase("delay")
        ){
        printWtButton.setVisible(true);
        if(entrust.getAcceptCode() ==null){
          printConfirmButton.setVisible(false);
          printXyButton.setVisible(false);
        }else{
          printConfirmButton.setVisible(true);
          printXyButton.setVisible(true);
        }
      }else if(entrust.getStatus().equalsIgnoreCase("4")){
        printWtButton.setVisible(true);
        if(entrust.getAcceptCode() ==null){
          printConfirmButton.setVisible(false);
          printXyButton.setVisible(false);
        }else{
          printConfirmButton.setVisible(true);
          printXyButton.setVisible(true);
        } 
      }
     //受理之后才处理这些按钮操作，受理前不能操作
      delayBtn.setVisible(false);
      pauseBtn.setVisible(false);        
      stopBtn.setVisible(false);
      startBtn.setVisible(false);
      zhuansongBtn.setVisible(false);
      if(entrust.getStatus().equalsIgnoreCase(SfEntrust.STATUS_DOING) && entrust.getAcceptor()!=null){
        delayBtn.setVisible(true);
        pauseBtn.setVisible(true);        
        stopBtn.setVisible(true);
        startBtn.setVisible(false);
        zhuansongBtn.setVisible(true);
      }
      if(entrust.getStatus().equalsIgnoreCase(SfEntrust.STATUS_COMPLETE) && entrust.getAcceptor()!=null){
        delayBtn.setVisible(false);
        pauseBtn.setVisible(false);        
        stopBtn.setVisible(false);
        startBtn.setVisible(false);
        zhuansongBtn.setVisible(false);
      }
      if(entrust.getStatus().equalsIgnoreCase(SfEntrust.STATUS_PAUSE) && entrust.getAcceptor()!=null){
        delayBtn.setVisible(false);
        pauseBtn.setVisible(false);        
        stopBtn.setVisible(true);
        startBtn.setVisible(true);
        zhuansongBtn.setVisible(true);
      }
      if(entrust.getStatus().equalsIgnoreCase(SfEntrust.STATUS_STOP) && entrust.getAcceptor()!=null){
        delayBtn.setVisible(false);
        pauseBtn.setVisible(false);        
        stopBtn.setVisible(false);
        startBtn.setVisible(true);
        zhuansongBtn.setVisible(false);
      }
      if(entrust.getStatus().equalsIgnoreCase(SfEntrust.STATUS_ZHUANSONG) && entrust.getAcceptor()!=null){
        delayBtn.setVisible(true);
        pauseBtn.setVisible(true);        
        stopBtn.setVisible(true);
        startBtn.setVisible(false);
        zhuansongBtn.setVisible(false);
      }
      if(entrust.getStatus().equalsIgnoreCase(SfEntrust.STATUS_DELAY) && entrust.getAcceptor()!=null){
        delayBtn.setVisible(true);
        pauseBtn.setVisible(true);        
        stopBtn.setVisible(true);
        startBtn.setVisible(false);
        zhuansongBtn.setVisible(true);
      }
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

      bs.addBillStatus(ZcSettingConstants.WF_STATUS_DRAFT);

      btnStatusList.add(bs);

      bs = new ButtonStatus();
      bs.setButton(this.deleteButton);
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
      bs.setButton(this.songkeshiBtn);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);
      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);
      btnStatusList.add(bs);
      
      bs = new ButtonStatus();
      bs.setButton(this.agreeWtInfoBtn);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);
      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);
      btnStatusList.add(bs);

      bs = new ButtonStatus();
      bs.setButton(this.acceptBtn);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);
      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);
      btnStatusList.add(bs);

      bs = new ButtonStatus();
      bs.setButton(this.unAccetpBtn);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);
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

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_AUDITED);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.printXyButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_AUDITED);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.printConfirmButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_AUDITED);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.printMastTmButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_AUDITED);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.printDetailTmConfirmButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_AUDITED);

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

    agreeWtInfoBtn.setText("确认");//综合确认
    agreeWtInfoBtn.setToolTipText("确认委托信息");
    toolBar.add(agreeWtInfoBtn);
    
    songkeshiBtn.setText("送科室确认");//综合确认
    songkeshiBtn.setToolTipText("送科室确认");
    toolBar.add(songkeshiBtn);

    /* songkeshi2Btn.setText("送科室确认");//送科室确认
     songkeshi2Btn.setToolTipText("送科室确认");    
     toolBar.add(songkeshi2Btn);*/

    toolBar.add(unAuditButton);

    toolBar.add(unTreadButton);

    toolBar.add(callbackButton);

    toolBar.add(deleteButton);

    toolBar.add(acceptBtn);

    toolBar.add(unAccetpBtn);

    //    toolBar.add(importButton);

    toolBar.add(printWtButton);
    printWtButton.setText("委托书");
    printWtButton.setToolTipText("委托书");

    toolBar.add(printConfirmButton);
    printConfirmButton.setText("委托确认书");
    printConfirmButton.setToolTipText("委托确认书");

    toolBar.add(printXyButton);
    printXyButton.setText("委托确认书(协议)");
    printXyButton.setToolTipText("委托确认书(协议)");

/*    toolBar.add(printMastTmButton);
    printMastTmButton.setText("打印总条码");
    printMastTmButton.setToolTipText("打印总条码");*/

    /* toolBar.add(printDetailTmConfirmButton);
     printDetailTmConfirmButton.setText("打印检材条码");
     printDetailTmConfirmButton.setToolTipText("打印检材条码");*/

    /* toolBar.add(getAcceptCodeBtn);
     getAcceptCodeBtn.setText("获取受理编号");
     getAcceptCodeBtn.setToolTipText("获取受理编号");*/

    toolBar.add(traceButton);

    //    toolBar.add(previousButton);

    //    toolBar.add(nextButton);

    toolBar.add(delayBtn);
    delayBtn.setText("延期");
    delayBtn.setToolTipText("延期");
    
    toolBar.add(pauseBtn);
    pauseBtn.setText("暂停");
    pauseBtn.setToolTipText("暂停");
    
    toolBar.add(startBtn);
    startBtn.setText("启动");
    startBtn.setToolTipText("启动");
    
    toolBar.add(stopBtn);
    stopBtn.setText("终止");
    stopBtn.setToolTipText("终止");
    
    toolBar.add(zhuansongBtn);
    zhuansongBtn.setText("转送");
    zhuansongBtn.setToolTipText("转送其他鉴定中心");
    
    toolBar.add(exitButton);
    toolBar.add(exitButton);

    zhuansongBtn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doZhuansong();

      }

    });
    delayBtn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doDelay();

      }

    });
    pauseBtn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPause();

      }

    });
    stopBtn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doStop();

      }

    });
    startBtn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doStart();

      }

    });
    
    agreeWtInfoBtn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doAgreeWtInfo();

      }

    });

    songkeshiBtn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doSendKeshi();

      }

    });
    acceptBtn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doAccept();

      }

    });
    unAccetpBtn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doUnAccept();

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
        doSuggestPass(null);
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

  protected void doZhuansong() {
    showManageDialog(SfEntrustManage.MANAGE_TYPE_ZHUANSONG);
  }

  protected void doStart() {
    showManageDialog(SfEntrustManage.MANAGE_TYPE_START);
  }

  protected void doStop() {
    showManageDialog(SfEntrustManage.MANAGE_TYPE_STOP);
  }

  protected void doPause() {
    showManageDialog(SfEntrustManage.MANAGE_TYPE_PAUSE);
  }

  protected void doDelay() {
    showManageDialog(SfEntrustManage.MANAGE_TYPE_DELAY);
  }

  void showManageDialog(String manageType){
    SfEntrust bill = this.listCursor.getCurrentObject();
    SfEntrustManage m=new SfEntrustManage();
    m.setEntrust(bill);
    m.setEntrustCode(bill.getCode());
    m.setEntrustId(bill.getEntrustId());
    m.setManageType(manageType);
    m.setInputDate(SfUtil.getSysDate());
    m.setInputor(requestMeta.getSvUserID());
    m.setNd(requestMeta.getSvNd());
    m.setCoCode(requestMeta.getSvCoCode());
    m.setManageTime(SfUtil.getSysDate());
    if(SfEntrustManage.MANAGE_TYPE_DELAY.equalsIgnoreCase(manageType)){
      m.setOldEndDate(bill.getExpetedCompleteDate());
    }
    List l=new ArrayList();
    l.add(m);
    ListCursor lc=new ListCursor(l, 0);
    new SfEntrustManageDialog(this, lc);
  }
  protected void doAgreeWtInfo() {
    SfEntrust bill = this.listCursor.getCurrentObject();
    bill.setIsAccept("Y");
    doSuggestPass("同意");
  }

  protected void doSendKeshi() {

    //使用弹出选择用户的形式，作为下一岗的接收人
    /*SfEntrust bill = this.listCursor.getCurrentObject();

    //    System.out.println("doSuggestPass 1++++++++++++++++++++++++++=" + bill.getAcceptDate());
    XysxPanelUtil.getValue(bill, xysxComponents);

    if (!checkBeforeSave()) {
      return;
    }
    SfEntrust qx = (SfEntrust) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    ElementConditionDto dto=new ElementConditionDto();
    UserTreeSelectDialog dialog=new UserTreeSelectDialog(parent, true, this, true, true, dto);
    

    //	    dialog.setTitle();

    dialog.setSize(UIConstants.DIALOG_4_LEVEL_WIDTH, UIConstants.DIALOG_4_LEVEL_HEIGHT);

    dialog.moveToScreenCenter();

    dialog.pack();

    //editPanel.refreshData();s

    //	    this.setMaxSizeWindow();

    dialog.setVisible(true);*/

    //使用了公用的科室受理人模式，直接s调用审核方法
    doSuggestPass("请确认委托相关信息");
  }

  public void auditWithNextExcuter(List nextExcuters) {
    requestMeta.getWfNextExcuters().clear();
    if (nextExcuters == null || nextExcuters.size() == 0) {
      JOptionPane.showMessageDialog(this, "请指定审核人！", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    for (int i = 0; i < nextExcuters.size(); i++) {
      BaseElement e = (BaseElement) nextExcuters.get(0);
      requestMeta.getWfNextExcuters().add(e.getCode());
    }
    doSuggestPass(null);
  }

  /**
   * 不受理，配合工作流，直接结束
   */
  protected void doUnAccept() {

    SfEntrust bill = this.listCursor.getCurrentObject();

    //    System.out.println("doSuggestPass 1++++++++++++++++++++++++++=" + bill.getAcceptDate());
    XysxPanelUtil.getValue(bill, xysxComponents);
    if (bill.getNotAcceptReason() == null) {
      JOptionPane.showMessageDialog(this, "请说明不受理原因！", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;

    }

    /*if (!checkBeforeSave()) {
      return;
    }*/
    SfEntrust qx = (SfEntrust) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    //    System.out.println("doSuggestPass 2++++++++++++++++++++++++++=" + qx.getAcceptDate());
    requestMeta.setFuncId(this.unAccetpBtn.getFuncId());
    boolean success = true;
    String errorInfo = "";
    try {
      qx.setComment("不接受委托");
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      //      System.out.println("doSuggestPass 3++++++++++++++++++++++++++=" + qx.getAcceptDate());
      qx = sfEntrustServiceDelegate.unAcceptFN(qx, requestMeta);
      //      System.out.println("doSuggestPass 4++++++++++++++++++++++++++=" + qx.getAcceptDate());
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(this, "完成操作！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "操作败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * 受理,配合工作流,送给对应的鉴定负责人
   */
  protected void doAccept() {

    SfEntrust bill = this.listCursor.getCurrentObject();

    //    System.out.println("doSuggestPass 1++++++++++++++++++++++++++=" + bill.getAcceptDate());
    XysxPanelUtil.getValue(bill, xysxComponents);

    /*if (!checkBeforeSave()) {
      return;
    }*/
    SfEntrust qx = (SfEntrust) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    //    System.out.println("doSuggestPass 2++++++++++++++++++++++++++=" + qx.getAcceptDate());
    requestMeta.setFuncId(this.acceptBtn.getFuncId());
    boolean success = true;
    String errorInfo = "";
    try {
      qx.setComment("接受委托");
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx.setAcceptDate(SfUtil.getSysDate());
      qx.setAcceptor(requestMeta.getSvUserID());
      qx.setJdFzr(requestMeta.getSvUserID());
      qx.setIsAccept("Y");
      //      System.out.println("doSuggestPass 3++++++++++++++++++++++++++=" + qx.getAcceptDate());
      qx = sfEntrustServiceDelegate.acceptFN(qx, requestMeta);
      //      System.out.println("doSuggestPass 4++++++++++++++++++++++++++=" + qx.getAcceptDate());
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(this, "成功受理！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "受理失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
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
  public void updateDataFlowDialog() {
    // TCJLODO Auto-generated method stub
    SfEntrust entrust = (SfEntrust) this.listCursor.getCurrentObject();
    if (listPanel != null && listPanel.getParent() instanceof JClosableTabbedPane) { return; }
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
    boolean errorTel=false;
    if(entrust.getSjrTel()!=null && !SfUtil.isMobile(entrust.getSjrTel())){
      errorInfo.append("送检人电话请留手机号,方便接收鉴定相关短信信息").append("\n");
      errorTel=true;
    }
    if(entrust.getSjr2Tel()!=null && !SfUtil.isMobile(entrust.getSjr2Tel()) &&!errorTel ){
      errorInfo.append("\n").append("送检人电话请留手机号,方便接收鉴定相关短信信息");
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

    if (!this.saveButton.isVisible() || !saveButton.isEnabled()) { return false; }

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
//    IWordHandler handler = new SfAgreementWordHandler();
    IWordHandler handler = new SfEntrustConfirmXyWordHandler();
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
    //    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_NAME), "name");
    TextAreaFieldEditor name = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_NAME), "name", -1, 1, 3);
    TextFieldEditor anjianCode = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_ANJIAN_CODE), "anjianCode");
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_STATUS), "status", SfEntrust.SF_VS_ENTRUST_STATUS);
    SfEntrustorHandler entrustorHandler = new SfEntrustorHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
          SfEntrustor t=(SfEntrustor) obj;
          currentBill.setEntrustor(t);
          currentBill.setSjrZip(t.getZip());
          currentBill.setSjrAddress(t.getAddress());
          setEditingObject(currentBill);
        }
      }

      public void afterClear() {
        SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
        currentBill.setEntrustor(new SfEntrustor());
        currentBill.setSjrZip(null);
        currentBill.setSjrAddress(null);
        setEditingObject(currentBill);
      }
    };
    SfEntrustorNewFieldEditor entrustor = new SfEntrustorNewFieldEditor(entrustorHandler.getSqlId(), 20, entrustorHandler, entrustorHandler.getColumNames(),
      LangTransMeta.translate(SfEntrust.COL_ENTRUSTOR_NAME), "entrustor.name");
    TextFieldEditor entrustorAddress = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.ADDRESS), "entrustor.address");
    TextFieldEditor entrustorLinkMan = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.LINK_MAN), "entrustor.linkMan");
    TextFieldEditor entrustorTel = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.LINK_TEL), "entrustor.linkTel");

    DateFieldEditor wtDate = new DateFieldEditor(LangTransMeta.translate(SfEntrust.COL_WT_DATE), "wtDate");

    TextFieldEditor sjr = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR), "sjr");
    TextFieldEditor sjrTel = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_TEL), "sjrTel");
    //    TextFieldEditor sjrZjType = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_TYPE), "sjrZjType");
    AsValFieldEditor sjrZjType = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_TYPE), "sjrZjType", SfEntrust.SF_VS_ZHENGJIAN);
    TextFieldEditor sjrZjCode = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_CODE), "sjrZjCode");
    TextFieldEditor sjr2 = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR2), "sjr2");
    TextFieldEditor sjr2Tel = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR2_TEL), "sjr2Tel");
    //    TextFieldEditor sjr2ZjType = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR2_ZJ_TYPE), "sjr2ZjType");
    AsValFieldEditor sjr2ZjType = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_TYPE), "sjr2ZjType", SfEntrust.SF_VS_ZHENGJIAN);
    TextFieldEditor sjr2ZjCode = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR2_ZJ_CODE), "sjr2ZjCode");

    TextAreaFieldEditor sjrAddress = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_ADDRESS), "sjrAddress", -1, 1, 3);

    TextFieldEditor sjrZip = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_ZIP), "sjrZip");

    TextFieldEditor sjrFax = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_FAX), "sjrFax");

    TextFieldEditor acceptCode = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_ACCEPT_CODE), "acceptCode");
    TextFieldEditor barCode = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_BAR_CODE), "barCode");

    AsValFieldEditor jdjg = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_CO_CODE), "coCode", SfJdjg.SF_VS_JDJG);
    
    AsValFieldEditor majorCode = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME), "majorCode", SfMajor.SF_VS_MAJOR) {
      @Override
      protected void afterChange(AsValComboBox field) {
        if (field.getSelectedAsVal() == null) {
          majorPersonDto.setDattr1(null);
          return;
        }
        String valId = field.getSelectedAsVal().getValId();
        valId = valId.substring(0, 3);//取前面三位
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
          JOptionPane.showMessageDialog(self, "请先选择" + LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME) + "！", "提示", JOptionPane.INFORMATION_MESSAGE);
          return false;
        }
        return true;
      }
    };

    majorPersonDto.setNd(requestMeta.getSvNd());
    majorPersonDto.setCoCode(requestMeta.getSvCoCode());
    ForeignEntityFieldEditor jdFzr = new ForeignEntityFieldEditor(jdFzrHandler.getSqlId(), majorPersonDto, 20, jdFzrHandler, jdFzrHandler.getColumNames(),
      LangTransMeta.translate(SfEntrust.COL_JD_FZR), "jdFzrName");

    SfJdPersonSelectHandler jdAssistorHandler = new SfJdPersonSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if (selectedDatas != null && selectedDatas.size() > 0) {
          SfEntrust cur = listCursor.getCurrentObject();
          SfJdPerson user = (SfJdPerson) selectedDatas.get(0);
          cur.setJdAssistor(user.getAccount());
          cur.setJdAssistorName(user.getName());
          setEditingObject(cur);
        }
      }

      public void afterClear() {
        SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
        currentBill.setJdAssistor(null);
        setEditingObject(currentBill);
      }

      public boolean beforeSelect(ElementConditionDto dto) {
        SfEntrust currentBill = (SfEntrust) listCursor.getCurrentObject();
        if (currentBill.getMajorCode() == null || currentBill.getMajorCode().trim().length() == 0) {
          JOptionPane.showMessageDialog(self, "请先选择" + LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME) + "！", "提示", JOptionPane.INFORMATION_MESSAGE);
          return false;
        }
        return true;
      }
    };

    majorPersonDto.setNd(requestMeta.getSvNd());
    majorPersonDto.setCoCode(requestMeta.getSvCoCode());
    ForeignEntityFieldEditor jdAssistor = new ForeignEntityFieldEditor(jdAssistorHandler.getSqlId(), majorPersonDto, 20, jdAssistorHandler, jdAssistorHandler.getColumNames(),
      LangTransMeta.translate(SfEntrust.COL_JD_ASSISTOR), "jdAssistorName");

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
          JOptionPane.showMessageDialog(self, "请先选择" + LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME) + "！", "提示", JOptionPane.INFORMATION_MESSAGE);
          return false;
        }
        return true;
      }
    };
    ForeignEntityFieldEditor jdFhr = new ForeignEntityFieldEditor(jdFhrHandler.getSqlId(), majorPersonDto, 20, jdFhrHandler, jdFhrHandler.getColumNames(),
      LangTransMeta.translate(SfEntrust.COL_JD_FHR), "jdFhrName");

    TextAreaFieldEditor jdHistory = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_HISTORY), "jdHistory", -1, 3, 5);
    TextAreaFieldEditor jdRequire = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_REQUIRE), "jdRequire", -1, 9, 5);
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_REMARK), "remark", -1, 1, 7);
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

          List jdResultLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdResultMapper.selectByEntrustId", parentEntrust.getEntrustId(), requestMeta);
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
    ForeignEntityFieldEditor parentEntrust = new ForeignEntityFieldEditor(cxjdHandler.getSqlId(), parentEntrustDto, 20, cxjdHandler, cxjdHandler.getColumNames(),
      LangTransMeta.translate(SfEntrust.COL_WT_ID_PARENT), "wtCodeParent");
    TextFieldEditor lsJdFzr = new TextFieldEditor("上次鉴定负责人", "lsJdFzrName");
    TextFieldEditor lsJdFhr = new TextFieldEditor("上次鉴定复核人", "lsJdFhrName");

    TextAreaFieldEditor brief = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_BRIEF), "brief", -1, 9, 5);
    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_INPUTOR), "inputorName");
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfEntrust.COL_INPUT_DATE), "inputDate");
    TextFieldEditor acceptor = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_ACCEPTOR), "acceptorName");
    DateFieldEditor acceptDate = new DateFieldEditor(LangTransMeta.translate(SfEntrust.COL_ACCEPT_DATE), "acceptDate");
    AsValFieldEditor isAccept = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_IS_ACCEPT), "isAccept", "VS_Y/N");
    TextAreaFieldEditor notAcceptReason = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_NOT_ACCEPT_REASON), "notAcceptReason", -1, 5, 5);
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
//    SfJdTargetNewFieldEditor jdTarget = new SfJdTargetNewFieldEditor(targetHandler.getSqlId(), 20, targetHandler, targetHandler.getColumNames(), LangTransMeta.translate(SfJdTarget.COL_NAME),
//      "jdTarget.name");

    TextFieldEditor jdTarget = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_NAME), "jdTarget.name");
    IntFieldEditor jdTargetAge = new IntFieldEditor(LangTransMeta.translate(SfJdTarget.COL_AGE), "jdTarget.age");
    AsValFieldEditor jdTargetSex = new AsValFieldEditor(LangTransMeta.translate(SfJdTarget.COL_SEX), "jdTarget.sex", SfElementConstants.VS_SEX);
    TextFieldEditor jdTargetIdName = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ID_NAME), "jdTarget.idName");
    TextFieldEditor jdTargetIdCode = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ID_CODE), "jdTarget.idCode");
    TextFieldEditor jdTargetPhone = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_PHONE), "jdTarget.phone");
    TextAreaFieldEditor jdTargetAddress = new TextAreaFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ADDRESS), "jdTarget.address", -1, 1, 3); 
    TextAreaFieldEditor jdTargetCompany = new TextAreaFieldEditor(LangTransMeta.translate(SfJdTarget.COL_COMPANY), "jdTarget.company", -1, 1, 3); 
    
    MoneyFieldEditor jdCharge = new MoneyFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_CHARGE), "jdCharge");

    jdDocSendType = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_DOC_SEND_TYPE), "jdDocSendType", SfEntrust.SF_VS_ENTRUST_DOC_SEND_TYPE);
    jdDocSendTypeFz = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_DOC_SEND_TYPE_FZ), "jdDocSendTypeFz");

    AsValFieldEditor urgentLevel = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_URGENT_LEVEL), "urgentLevel", SfEntrust.SF_VS_ENTRUST_URGENT_LEVEL);
    MoneyFieldEditor expectedTime = new MoneyFieldEditor(LangTransMeta.translate(SfEntrust.COL_EXPECTED_TIME), "expectedTime");
    DateFieldEditor completeTime = new DateFieldEditor(LangTransMeta.translate(SfEntrust.COL_COMPLETE_TIME), "completeTime");

    headFieldEditors.add(new NewLineFieldEditor());

    headFieldEditors.add(code);
    headFieldEditors.add(name);
    headFieldEditors.add(anjianCode);

    headFieldEditors.add(jdjg);
    headFieldEditors.add(majorCode);
    headFieldEditors.add(entrustor);
    headFieldEditors.add(wtDate); 

    headFieldEditors.add(sjr);
    headFieldEditors.add(sjrTel);
    headFieldEditors.add(sjrZjType);
    headFieldEditors.add(sjrZjCode);

    headFieldEditors.add(sjr2);
    headFieldEditors.add(sjr2Tel);
    headFieldEditors.add(sjr2ZjType);
    headFieldEditors.add(sjr2ZjCode);

    headFieldEditors.add(sjrZip);
    headFieldEditors.add(sjrAddress);
    headFieldEditors.add(sjrFax);

    //    editorList.add(jdOrg);

    if (!SfUtil.isWtf()) {
      headFieldEditors.add(jdFzr);
      headFieldEditors.add(jdFhr);
      headFieldEditors.add(jdAssistor);
    }

    headFieldEditors.add(isAccept);
    headFieldEditors.add(acceptCode);
    headFieldEditors.add(acceptor);
    headFieldEditors.add(acceptDate);
    if (!SfUtil.isWtf()) {
      headFieldEditors.add(urgentLevel);
      headFieldEditors.add(expectedTime);
      headFieldEditors.add(completeTime);
    }
    headFieldEditors.add(status);
    if (!SfUtil.isWtf()) {
      headFieldEditors.add(barCode);
    }

    headFieldEditors.add(inputor);
    headFieldEditors.add(inputDate);
    
    headFieldEditors.add(remark);

    jdyqFieldEditors.add(jdRequire);

    jazyFieldEditors.add(brief);

    bslFieldEditors.add(notAcceptReason);

    jddxFieldEditors.add(jdTarget);
    jddxFieldEditors.add(jdTargetAge);
    jddxFieldEditors.add(jdTargetSex);
    
    jddxFieldEditors.add(jdTargetIdName);
    jddxFieldEditors.add(jdTargetIdCode);
    jddxFieldEditors.add(jdTargetPhone);

    jddxFieldEditors.add(jdTargetCompany); 
    jddxFieldEditors.add(jdTargetAddress);

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
    bslPanel = initBslPanel(this.billClass, this.eleMeta, bslFieldEditors);
    JPanel historyPanel = initJdlslPanel(this.billClass, this.eleMeta, historyFieldEditors);
    JComponent xysxPanel = XysxPanelUtil.createXysxPanel(this.billClass, this.eleMeta, jdDocSendType, jdDocSendTypeFz, xysxComponents);
    JPanel footPanel = initFieldEditorPanel(this.billClass, this.eleMeta, footFieldEditors);

    workPanel.setLayout(new BorderLayout());

    workPanel.add(headPanel, BorderLayout.NORTH);

    JComponent materialPanel = createSubBillPanel();

    JComponent jdChargePanel = createJdChargePanel();

    subTabPanel = new JTabbedPane();
    subTabPanel.setMinimumSize(new Dimension(240, 300));

    subTabPanel.addTab("检案摘要", jazyPanel);
    subTabPanel.addTab("鉴定对象", jddxPanel);
    subTabPanel.addTab("送检材料", materialPanel);
    subTabPanel.addTab("鉴定要求", jdyqPanel);
    subTabPanel.addTab("协议事项", xysxPanel);
    subTabPanel.addTab("不受理原因", bslPanel);
    subTabPanel.addTab("历史鉴定", historyPanel);
    //    itemTabPane.addTab("鉴定收费", jdChargePanel);

    workPanel.add(subTabPanel, BorderLayout.CENTER);

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
        topPanel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
        topPanel.add(comp, new GridBagConstraints(col + 1, row, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
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
    JPanel p=new JPanel();
    p.setLayout(new BorderLayout());
    p.add(SfUtil.createFieldEditorPanel(billClass, eleMeta, editors, 3),BorderLayout.NORTH);
    return p;
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
          // 不够一行时，转到新的一行
          if (comp.getOccCol() + 1 > colCount * 2 - col) {
            row++;
            col = 0;
          }
          JLabel label = new JLabel(getLabelText(comp));
          if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
            label.setText(label.getText() + "*");
            label.setForeground(new Color(254, 100, 1));
            label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
          }
          comp.setPreferredSize(new Dimension(150 * comp.getOccCol(), comp.getOccRow() * 26));
          contentPanel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 0, 4, 4), 0, 0));
          contentPanel.add(comp, new GridBagConstraints(col + 1, row, comp.getOccCol(), comp.getOccRow(), 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4, 0, 4, 4), 0,
            0));
          //计算当前占用的情况，如果不需要换行就继续，否则新启动一行
          //          col=comp.getOccCol()+1;
          if (comp.getOccCol() + 1 > colCount * 2 - 2) {
            row += comp.getOccRow();
            col = 0;
          } else {
            col = col + 1 + comp.getOccCol();
          }
          // 将当前所占的行空间偏移量计算上
          //          row += comp.getOccRow();
          //          col = 0;
          continue;
        } else {
          JLabel label = new JLabel(comp.getName());
          if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
            label.setText(label.getText() + "*");
            label.setForeground(new Color(254, 100, 1));
            label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
          }
          comp.setPreferredSize(new Dimension(150, 23));
          contentPanel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
          contentPanel.add(comp, new GridBagConstraints(col + 1, row, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
          if (col == colCount * 2 - 2) {
            row++;
            col = 0;
          } else {
            col += 2;
          }
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
    
    JButton copyBtn=new SubCopyButton(false);

    materialBtnBar.add(addBtn2);

    materialBtnBar.add(insertBtn2);

    materialBtnBar.add(delBtn2);

    materialBtnBar.add(copyBtn);
    

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
    copyBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        copySub(materialsTablePanel);
      }
    });
    return materialsTablePanel;
    //    return null;
  }

  protected void copySub(JTablePanel tablePanel) {
    JPageableFixedTable table = tablePanel.getTable();

    Integer[] checkedRows;

    // 是否显示行选择框

    if (tablePanel.getTable().isShowCheckedColumn()) {

      checkedRows = table.getCheckedRows();

    } else {

      int[] selectedRows = table.getSelectedRows();
      int selectrow=table.getSelectedRow();

      checkedRows = new Integer[selectedRows.length];

      for (int i = 0; i < checkedRows.length; i++) {

        checkedRows[i] = selectedRows[i];

      }

    }

    if (checkedRows.length == 0) {

      JOptionPane.showMessageDialog(this, "请勾选一条期望复制的数据！", "提示",JOptionPane.INFORMATION_MESSAGE);

    }else if (checkedRows.length >1) {

        JOptionPane.showMessageDialog(this, "请勾选一条期望复制的数据,不能选择多条！", "提示",JOptionPane.INFORMATION_MESSAGE);

      }  else {

      if (table.isEditing()) {

        table.getCellEditor().stopCellEditing();

      }

      BeanTableModel tableModel = (BeanTableModel) table.getModel();

      int[] selRows = new int[checkedRows.length];

      for (int i = 0; i < selRows.length; i++) {

        selRows[i] = table.convertRowIndexToModel(checkedRows[i]);

      }

      Arrays.sort(selRows);

      for (int i = selRows.length - 1; i >= 0; i--) {
//        SfEntrust qx = (SfEntrust) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
        SfMaterials item = (SfMaterials) tableModel.getBean(selRows[i]);
        SfMaterials copyItem =(SfMaterials)ObjectUtil.deepCopy(item);
        copyItem.setTempId("" + System.currentTimeMillis());
        copyItem.setAttachFile(null);
        copyItem.setAttachFileBlobid(null);
        copyItem.setMaterialId(null);
        tableModel.insertRow(tableModel.getRowCount(), copyItem); 

      }
      table.setCheckedRows(checkedRows, false);
    }

    fitColumnWidth(tablePanel.getTable()); 
 
  }

  protected void setMaterialDefaultValue(SfMaterials item) {
    // TCJLODO Auto-generated method stub
    item.setTempId("" + System.currentTimeMillis());
    SfEntrust e = listCursor.getCurrentObject();
    item.setEntrustId(e.getEntrustId());
    item.setMaterialType(SfMaterials.SF_VS_MATERIAL_TYPE_jiancai);
    item.setJianHouChuliType(SfMaterials.SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE_tuihui);
    item.setJianHouStoreTime(new BigDecimal(AsOptionMeta.getOptVal(SfMaterials.OPT_SF_MATERIALS_STORE_DAYS)));
  }

  protected void hideCol(JTable table, String colName) {

    TableColumn tc = table.getColumn(colName);

    table.getColumnModel().removeColumn(tc);

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
   * @param comment 
   */
  protected void doSuggestPass(String comment) {

    SfEntrust bill = this.listCursor.getCurrentObject();

    //    System.out.println("doSuggestPass 1++++++++++++++++++++++++++=" + bill.getAcceptDate());
    XysxPanelUtil.getValue(bill, xysxComponents);

//    if (!checkBeforeSave()) { return; }
    SfEntrust qx = (SfEntrust) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    //    System.out.println("doSuggestPass 2++++++++++++++++++++++++++=" + qx.getAcceptDate());
    requestMeta.setFuncId(this.suggestPassButton.getFuncId());
    if(comment==null||comment.trim().length()==0){
      comment="同意";
    }
    GkCommentDialog commentDialog = new GkCommentDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(), ModalityType.APPLICATION_MODAL,comment);
    if (commentDialog.cancel) { return; }
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
    if (i != 0) { return; }
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
    GkCommentUntreadDialog commentDialog = new GkCommentUntreadDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(), ModalityType.APPLICATION_MODAL);
    if (commentDialog.cancel) { return; }
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
    if (bean == null) { return; }
    ZcUtil.showTraceDialog(bean, compoId);
  }

  protected void setButtonStatus(WfAware baseBill, RequestMeta requestMeta, ListCursor listCursor) {

    Long processInstId = baseBill.getProcessInstId();

    if (processInstId == null || processInstId.longValue() < 0) {

      // 新增单据,草稿单据或不挂接工作流的单据

      Component[] funcs = toolBar.getComponents();

      String funcId;

      for (Component func : funcs) {

        funcId = ((FuncButton) func).getFuncId();

        if ("fauditfinal" == funcId || "fcallback" == funcId

        || "fautocommit" == funcId || "funaudit" == funcId

        || "funtread" == funcId

        || "fshowinstancetrace" == funcId

        || "f_uncollectcreate" == funcId

        || "fconfirmsup" == funcId || "fmanualcommit" == funcId

        || "fsendnextcommit" == funcId

        ) {
          func.setVisible(false);
        }

      }
      setButtonStatusWithoutWf();

    } else {

      // 流程已经启动

      List enableFuncs = this.getWFNodeEnableFunc(baseBill, requestMeta);

      if (enableFuncs == null || enableFuncs.size() == 0) {//委托里面，委托流转到科室时，不是指定科室的具体人，而是用了一个公用的用户(KESHI_SHOULI 科室受理人)，根据当前单据流程号、鉴定专业判断当当前单据是否应该接受这个单据

        enableFuncs = getKeshiShouliEnableFunc(baseBill, requestMeta);
      }


      if ((enableFuncs == null || enableFuncs.size() == 0) //综合值班不指定到具体人，，而是用了一个公用的用户(ZONGHE_SHOULI 综合受理人)，
        ||(enableFuncs.size()==1 && enableFuncs.contains("fcallback"))//如果当前登录人是综合值班人，又这个专业是他的专业，需要获取值班按钮。
        ) {//综合值班不指定到具体人，，而是用了一个公用的用户(ZONGHE_SHOULI 综合受理人)，

        enableFuncs = getZhibanEnableFunc(baseBill, requestMeta);
      }
      

      ZcUtil.setWfNodeEnableFunc(toolBar, enableFuncs, processInstId, requestMeta);

    }

    if (listCursor == null || listCursor.getDataList() == null || listCursor.getDataList().size() <= 1) {

      // 如果listCursor中只有一条记录，就隐藏上一条下一条按钮

      FuncButton previousButton = toolBar.getButtonByDefaultText("上一条");

      FuncButton nextButton = toolBar.getButtonByDefaultText("下一条");

      if (previousButton != null) {

        previousButton.setVisible(false);

      }

      if (nextButton != null) {

        nextButton.setVisible(false);

      }

    }

  }
  private List getZhibanEnableFunc(WfAware baseBill, RequestMeta meta) {
    ElementConditionDto dto = new ElementConditionDto();
    dto.setProcessInstId(baseBill.getProcessInstId());
    dto.setExecutor(meta.getSvUserID());
    dto.setCompoId(getCompoId());
    List funcs = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfEntrustMapper.getZongheShouliEnableFunc", dto, meta);

    return funcs == null ? new ArrayList() : funcs;
  }

  private List getKeshiShouliEnableFunc(WfAware baseBill, RequestMeta meta) {
    ElementConditionDto dto = new ElementConditionDto();
    dto.setProcessInstId(baseBill.getProcessInstId());
    dto.setExecutor(meta.getSvUserID());
    dto.setCompoId(getCompoId());
    List funcs = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfEntrustMapper.getKeshiShouliEnableFunc", dto, meta);

    return funcs == null ? new ArrayList() : funcs;
  }


  private Map getZongheShouliEnableField(WfAware baseBill, RequestMeta meta) {
    ElementConditionDto dto = new ElementConditionDto();
    dto.setProcessInstId(baseBill.getProcessInstId());
    dto.setExecutor(meta.getSvUserID());
    dto.setCompoId(getCompoId());
    List funcs = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfEntrustMapper.getZongheShouliEnableField", dto, meta);

    if (funcs == null) { return null; }
    Map rtn = new HashMap();
    for (int i = 0; i < funcs.size(); i++) {
      HashMap row = (HashMap) funcs.get(i);
      rtn.put(row.get("DATA_ITEM"), row.get("READ_WRITE"));
    }

    return rtn;
  }
  private Map getKeshiShouliEnableField(WfAware baseBill, RequestMeta meta) {
    ElementConditionDto dto = new ElementConditionDto();
    dto.setProcessInstId(baseBill.getProcessInstId());
    dto.setExecutor(meta.getSvUserID());
    dto.setCompoId(getCompoId());
    List funcs = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfEntrustMapper.getKeshiShouliEnableField", dto, meta);

    if (funcs == null) { return null; }
    Map rtn = new HashMap();
    for (int i = 0; i < funcs.size(); i++) {
      HashMap row = (HashMap) funcs.get(i);
      rtn.put(row.get("DATA_ITEM"), row.get("READ_WRITE"));
    }

    return rtn;
  }
  public GkBaseDialog getOwner(){
    return parent;
  }
}
