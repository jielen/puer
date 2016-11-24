package com.ufgov.zc.client.sf.report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.common.WorkEnv;
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
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.button.zc.CommonButton;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfClientUtil;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowUtil;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.jdresult.RefrenceEntrustDialog;
import com.ufgov.zc.client.sf.util.SfBookmarkUtil;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.WordFileUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.client.zc.activeztb.TbDocService;
import com.ufgov.zc.client.zc.ztb.activex.ExcelPane;
import com.ufgov.zc.client.zc.ztb.activex.WordPane;
import com.ufgov.zc.common.commonbiz.publish.IBaseDataServiceDelegate;
import com.ufgov.zc.common.sf.model.SfBookmark;
import com.ufgov.zc.common.sf.model.SfCertificate;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdRecordFileModel;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdResultFile;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.sf.model.SfReportCode;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfJdPersonServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfJdReportServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfJdResultServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsFile;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.system.util.Utils;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfJdReportEditPanel extends AbstractMainSubEditPanel {

	/**
	   * 
	   */
	private static final long serialVersionUID = -6258499243419971245L;

	private static final Logger logger = Logger.getLogger(SfJdReportEditPanel.class);

	protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

	protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

	private static String compoId = "SF_JD_REPORT";

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

	// 工作流填写意见审核通过
	protected FuncButton suggestPassButton = new SuggestAuditPassButton();

	// 工作流销审
	protected FuncButton unAuditButton = new UnauditButton();

	// 工作流退回
	protected FuncButton unTreadButton = new UntreadButton();

	private FuncButton getNoBtn = new CommonButton("fgetNo", "获取采购编号", null);

	protected ListCursor<SfJdReport> listCursor;

	private SfJdReport oldDossier;

	public SfJdReportListPanel listPanel;

	protected SfJdReportEditPanel self = this;

	protected GkBaseDialog parent;

	private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

	private BillElementMeta mainBillElementMeta = BillElementMeta
			.getBillElementMetaWithoutNd(compoId);

	protected JTablePanel personsTablePanel = new JTablePanel();

	protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

	private ISfJdReportServiceDelegate sfJdReportServiceDelegate;

	private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

	private ISfJdResultServiceDelegate sfJdResultServiceDelegate;
	
	  private ISfJdPersonServiceDelegate sfJdPersonServiceDelegate;
	  

	private  IBaseDataServiceDelegate baseDataServiceDelegate ;

	protected WordPane wordPane;

	private boolean openAndProtect = true;

	protected String fileName = "";

	protected JTabbedPane tabPane = new JTabbedPane();
	
	private boolean recordFileOpened=false;
	

	private boolean recordFaceOpened=false;

	JTabbedPane mainTab=new JTabbedPane();

	JMenu menuInsertModel = new JMenu(SfClientUtil.MENU_INSERT_MODEL);

	JMenu menuInsertContent = new JMenu(SfClientUtil.MENU_INSERT_CONTENT);

	JMenu menuInsertWtf = new JMenu(SfClientUtil.MENU_WTF);

	JMenu menuInsertSjr = new JMenu(SfClientUtil.MENU_SJR);

	JMenu menuInsertJdTarget = new JMenu(SfClientUtil.MENU_JD_TARGET);

	  JMenu menuInsertJdjg=new JMenu(SfClientUtil.MENU_JDJG);
	  

		JMenu menuInsertJdRecord = new JMenu(SfClientUtil.MENU_RECORD);	
		JMenu menuInsertJdReport = new JMenu(SfClientUtil.MENU_REPORT);

	JMenu menuRefrence = new JMenu(SfClientUtil.MENU_REFRENCE);
	JMenu menuLookup = new JMenu(SfClientUtil.MENU_LOOKUP);
	

	JMenu menuCertificateFile = new JMenu(SfClientUtil.MENU_CERTIFICATES);
	JMenu menuCertificateFileJdjg = new JMenu(SfClientUtil.MENU_CERTIFICATES_JDJG);
	JMenu menuCertificateFileJdPerson = new JMenu(SfClientUtil.MENU_CERTIFICATES_JDRY);

	JMenu menuFill = new JMenu(SfClientUtil.MENU_FILL);

	JMenuBar menuBar = new JMenuBar();
	
	private Hashtable<String, SfCertificate> jdjgCerts=new Hashtable<String, SfCertificate>();
	
	private Hashtable<String, SfCertificate> jdPersonCerts=new Hashtable<String, SfCertificate>();

	private JDialog progressDialog;
	private JProgressBar openWordProgressBar = null;

	ElementConditionDto entrustDto = new ElementConditionDto();

	SfReportCodeDialog2 codeDialog = null;
	
	private String modelFileId="";//模板文件id

	// 模板菜单，菜单名称是key，模板文件是value
	private HashMap<String, SfJdRecordFileModel> modelFileMenuMap = new HashMap<String, SfJdRecordFileModel>();
	
	private int referencReportNameIndex=0; 

	public SfJdReportEditPanel(GkBaseDialog parent, ListCursor listCursor,
			String tabStatus, SfJdReportListPanel listPanel) {
		// TCJLODO Auto-generated constructor stub
		super(SfJdReportEditPanel.class, BillElementMeta
				.getBillElementMetaWithoutNd(compoId));
		zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate");

		sfJdReportServiceDelegate = (ISfJdReportServiceDelegate) ServiceFactory.create(ISfJdReportServiceDelegate.class,
						"sfJdReportServiceDelegate");

		sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class,
						"sfEntrustServiceDelegate");

		sfJdResultServiceDelegate = (ISfJdResultServiceDelegate) ServiceFactory.create(ISfJdResultServiceDelegate.class,
						"sfJdResultServiceDelegate");
	    sfJdPersonServiceDelegate = (ISfJdPersonServiceDelegate) ServiceFactory.create(ISfJdPersonServiceDelegate.class, "sfJdPersonServiceDelegate");
	    baseDataServiceDelegate = (IBaseDataServiceDelegate) ServiceFactory.create(IBaseDataServiceDelegate.class, "baseDataServiceDelegate");
		this.workPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				LangTransMeta.translate(compoId), TitledBorder.CENTER,
				TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

		this.listCursor = listCursor;

		this.listPanel = listPanel;

		this.parent = parent;

		this.colCount = 3;

		init();

		requestMeta.setCompoId(getCompoId());

		WordFileUtil.setDir("sf");

//		addSubPane();
		addWordPane();

		refreshMainData();
	}

	private void addWordPane() {

		// 下面一句是为了打开word后刷新窗口
		wordPane = new WordPane();
		wordPane.setMinimumSize(new Dimension(10, 100));
		parent.setSize(parent.getSize().width + 1, parent.getSize().height + 1);
		wordPane.addPropertyChangeListener(WordPane.EVENT_NAME_OPEN_CALLBACK,
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						// 打开文件完成之后的回调函数
						boolean isSuccess = (Boolean) evt.getNewValue();
						if (isSuccess) {
							// 下面一句是为了打开word后刷新窗口
							parent.setSize(parent.getSize().width - 1,
									parent.getSize().height - 1);
						}
					}
				});
		mainTab.addTab("鉴定意见书", wordPane);
		mainTab.validate();
		mainTab.repaint();
	}

	protected void addSubPane() {
		// 下面一句是为了打开word后刷新窗口
		wordPane = new WordPane();
		wordPane.setMinimumSize(new Dimension(10, 100));
		parent.setSize(parent.getSize().width + 1, parent.getSize().height + 1);
		wordPane.addPropertyChangeListener(WordPane.EVENT_NAME_OPEN_CALLBACK,
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						// 打开文件完成之后的回调函数
						boolean isSuccess = (Boolean) evt.getNewValue();
						if (isSuccess) {
							// 下面一句是为了打开word后刷新窗口
							parent.setSize(parent.getSize().width - 1,
									parent.getSize().height - 1);
						}
					}
				});
		tabPane.addTab("鉴定意见书", wordPane);
		tabPane.validate();
		tabPane.repaint();
	}

	private void refreshMainData() {
		// TCJLODO Auto-generated method stub

		SfJdReport bill = (SfJdReport) listCursor.getCurrentObject();

		if (bill != null) {
			if (bill.getJdReporId() != null) {// 列表页面双击进入
				this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
				this.openAndProtect = true;
				bill = sfJdReportServiceDelegate.selectByPrimaryKey(bill.getJdReporId(), this.requestMeta);
				listCursor.setCurrentObject(bill);
				this.setEditingObject(bill);
			} else if (bill.getEntrustId() != null) {// 图形界面进来的新增，已经确定了entrust
				this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
				this.openAndProtect = false;
				setDefaultValue(bill);
				bill.setResult(getJdResult(bill.getEntrustId()));
				listCursor.getDataList().add(bill);
				listCursor.setCurrentObject(bill);
				this.setEditingObject(bill);
			}
		} else {// 新增按钮进入
			this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
			this.openAndProtect = false;
			bill = new SfJdReport();
			setDefaultValue(bill);
			listCursor.getDataList().add(bill);
			listCursor.setCurrentObject(bill);
			this.setEditingObject(bill);
		}
		updateBtnFields();
	}

	private void refreshData() {

		refreshMainData();

		refreshSubTableData();

		updateBtnFields();
	}

	private void updateBtnFields() {
		setOldObject();
		setButtonStatus();
		updateFieldEditorsEditable();
	}

	private void refreshSubTableData() {
		refreshFilePanel();
		updateBtnFields();
	}

	public synchronized void closeWordPanel(WordPane wp, boolean isSave) {
		if (wp != null && wp.isDocOpened()) {
			wp.close(isSave);
		}
	}

	/**
	 * 
	 * 刷新word文本
	 */
	public void refreshFilePanel() {

		SfJdReport bill = (SfJdReport) listCursor.getCurrentObject();
		String fileId = bill.getJdReportFileId();

		closeWordPanel(wordPane, false);

		if (fileId != null && !fileId.equals("")) {

			this.fileName = WordFileUtil.loadMold(fileId);

		} else if (bill.getEntrustId() != null) {
			SfEntrust entrust = getEntrust(bill.getEntrustId());
			selectEntrust(entrust);
			return;
		} else {
			this.fileName = WordFileUtil.loadDefaultMold();
		}
		if (openAndProtect) {
			wordPane.openAndProtect(this.fileName,
					SfElementConstants.WORD_PASSWORD);
		} else {
			wordPane.open(this.fileName);
		}

	}

	private SfEntrust getEntrust(BigDecimal entrustId) {
		// TCJLODO Auto-generated method stub
		SfEntrust entrust = sfEntrustServiceDelegate.selectByPrimaryKey(entrustId, requestMeta);
		return entrust;
	}

	private void setDefaultValue(SfJdReport bill) {
		// TCJLODO Auto-generated method stub
		bill.setStatus(ZcSettingConstants.WF_STATUS_DRAFT);
		bill.setNd(this.requestMeta.getSvNd());
		bill.setInputDate(SfUtil.getSysDate());
		bill.setCoCode(requestMeta.getSvCoCode());
		bill.setInputor(requestMeta.getSvUserID());
		bill.setReportType(SfJdReport.RESULT_TYPE_YJS);
		bill.setReportCode(getExistsNo(bill));
	}

	/**
	 * 一个项目只和一个编号绑定，一次获取编号执行后就固定了
	 * @param bill
	 * @return
	 */
	private String getExistsNo(SfJdReport bill) {
	  if(bill.getEntrust()!=null && bill.getEntrust().getEntrustId()!=null){
	    SfReportCode sr=(SfReportCode) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfJdReportMapper.selectExistsReportCode", bill.getEntrust().getEntrustId(), requestMeta);
	    if(sr!=null){
	      return sr.getReportCode();
	    }
	  }
	  return null;
  }

  protected void updateFieldEditorsEditable() {

		for (AbstractFieldEditor editor : fieldEditors) {
			if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT)
					|| pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
				if ("inputDate".equals(editor.getFieldName())
						|| "inputorName".equals(editor.getFieldName())
						|| "status".equals(editor.getFieldName())) {
					editor.setEnabled(false);
				} else {
					editor.setEnabled(true);
				}
			} else {
				editor.setEnabled(false);
			}
		}

		SfJdReport qx = (SfJdReport) listCursor.getCurrentObject();
		Long processInstId = qx.getProcessInstId();
		if (processInstId != null && processInstId.longValue() > 0) {
			// 工作流的单据
			wfCanEditFieldMap = BillElementMeta.getWfCanEditField(qx,
					requestMeta);
			if ("cancel".equals(this.oldDossier.getStatus())) {// 撤销单据设置字段为不可编辑
				wfCanEditFieldMap = null;
			}

			for (AbstractFieldEditor editor : fieldEditors) {
				if (editor instanceof NewLineFieldEditor) {
					continue;
				}
				// 工作流中定义可编辑的字段
				if (wfCanEditFieldMap != null
						&& wfCanEditFieldMap.containsKey(Utils
								.getDBColNameByFieldName(
										editor.getEditObject(),
										editor.getFieldName()))) {
					isEdit = true;
					this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
					editor.setEnabled(true);
				} else {
					editor.setEnabled(false);
				}
			}

			// 工作流中该节点选中了保存按钮可用，则当前状态当前人可用编辑
			if (saveButton.isVisible() && saveButton.isEnabled()) {
				isEdit = true;
				this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
			}

		} else {

			for (AbstractFieldEditor editor : fieldEditors) {
				if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT)
						|| pageStatus
								.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
					if ("inputDate".equals(editor.getFieldName())
							|| "inputorName".equals(editor.getFieldName())
							|| "status".equals(editor.getFieldName())
							|| "dossierType".equals(editor.getFieldName())) {
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
		// setWFSubTableEditable(biTablePanel, isEdit);
		setMenuEnable(isEdit);
	}

	  private void setMenuEnable(boolean isEdit) {
		  for(int i=0;i<menuBar.getMenuCount();i++){
			  JMenu menu=menuBar.getMenu(i);
			  if(menu.equals(menuCertificateFile) || menu.equals(menuLookup)){
				  continue;
			  }
			  menu.setEnabled(isEdit);
		  }
//		  menuBar.setEnabled(isEdit);
	}
	private void protectWordPanel() {
		wordPane.protectDoc(SfElementConstants.WORD_PASSWORD);
	}

	protected void setButtonStatus() {
		SfJdReport bill = (SfJdReport) listCursor.getCurrentObject();
		setButtonStatus(bill, requestMeta, this.listCursor);
		if(bill.getReportCode()!=null){
		  getNoBtn.setVisible(false);
		}
	}

	public void setButtonStatusWithoutWf() {
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

		}

		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();

		ZcUtil.setButtonEnable(this.btnStatusList, bill.getStatus(),
				this.pageStatus, getCompoId(), bill.getProcessInstId());

	}

	protected void setOldObject() {

		oldDossier = (SfJdReport) ObjectUtil.deepCopy(listCursor
				.getCurrentObject());

	}

	public String getCompoId() {
		// TCJLODO Auto-generated method stub
		return compoId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#initToolBar
	 * (com.ufgov.zc.client.component.JFuncToolBar)
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

		// toolBar.add(saveAndSendButton);

		toolBar.add(suggestPassButton);

		// toolBar.add(sendGkButton);

		toolBar.add(unAuditButton);

		toolBar.add(unTreadButton);

		toolBar.add(callbackButton);

		toolBar.add(deleteButton);

		toolBar.add(getNoBtn);

		// toolBar.add(importButton);

		toolBar.add(printButton);

		toolBar.add(traceButton);

		// toolBar.add(previousButton);

		// toolBar.add(nextButton);

		toolBar.add(exitButton);
		
		addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doAdd();
      }
    });
		
		getNoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getNo();
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

		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doPrint();
			}
		});
	}

	protected void doAdd() {
	  listCursor.setCurrentObject(null);
	  refreshData();
	}

  /**
	 * 获取编号
	 */
	protected void getNo() {
		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();
		codeDialog = new SfReportCodeDialog2(parent,this);
		codeDialog.setSize(UIConstants.DIALOG_3_LEVEL_WIDTH,UIConstants.DIALOG_3_LEVEL_HEIGHT);
		codeDialog.pack();
		codeDialog.moveToScreenCenter();
		codeDialog.setVisible(true);
//		codeDialog.dispose();

	}

	protected void setNo(String reportNo){
		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();
		bill.setReportCode(reportNo);
		SfReportCode sr=new SfReportCode();
		sr.setEntrustId(bill.getEntrust().getEntrustId());
		sr.setReportCode(reportNo);
		zcEbBaseServiceDelegate.insertFN("com.ufgov.zc.server.sf.dao.SfJdReportMapper.insertReportCode", sr, requestMeta);
		getNoBtn.setVisible(false);
		setEditingObject(bill);
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

	protected void doPrevious() {
		if (isDataChanged()) {
			int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存",
					"保存确认", 0);
			if (num == JOptionPane.YES_OPTION) {
				if (!doSave()) {
					return;
				}
			} else {
				listCursor.setCurrentObject(oldDossier);
			}
		}
		listCursor.previous();
		refreshData();
	}

	protected void doNext() {
		if (isDataChanged()) {
			int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存",
					"保存确认", 0);
			if (num == JOptionPane.YES_OPTION) {
				if (!doSave()) {
					return;
				}
			} else {
				listCursor.setCurrentObject(oldDossier);
			}
		}
		listCursor.next();
		refreshData();
	}

	public boolean doSave() {
		/*
		 * if (!isDataChanged()) { JOptionPane.showMessageDialog(this,
		 * "数据没有发生改变，不用保存.", "提示", JOptionPane.INFORMATION_MESSAGE); return
		 * true; }
		 */

		if (!checkBeforeSave()) {
			return false;
		}

		boolean success = true;
		String errorInfo = "";
		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();
		try {
			// 保存word文件
			// 支持直接修改word内容。
			wordPane.save(this.fileName);
			// 每次都保存一文件，产生新的文件id
			if(bill.getJdReportFileId().equals(modelFileId)){
				String fileId=WordFileUtil.insertAsFileContent(fileName);
				bill.setJdReportFileId(fileId);
				modelFileId="";
			}else{
			  WordFileUtil.uploadWordFile(fileName,bill.getJdReportFileId());
			} 
			
			requestMeta.setFuncId(saveButton.getFuncId());
			// System.out.println("before=" + inData.getCoCode() +
			// inData.getCoName());
			bill = sfJdReportServiceDelegate.saveFN(bill, this.requestMeta);
			listCursor.setCurrentObject(bill);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			success = false;
			errorInfo += e.getMessage();
		}
		if (success) {
			JOptionPane.showMessageDialog(this, "保存成功！", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			refreshListPanel();
			refreshMainData();
			updateDataFlowDialog();
		} else {
			JOptionPane.showMessageDialog(this, "保存失败 ！\n" + errorInfo, "错误",
					JOptionPane.ERROR_MESSAGE);
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
		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();
		if (listPanel != null
				&& listPanel.getParent() instanceof JClosableTabbedPane) {
			return;
		}
		if (parent instanceof SfJdReportDialog) {// 新增的委托书，创建数据流界面
			SfDataFlowDialog d = new SfDataFlowDialog(compoId,
					SfDataFlowUtil.getEntrust(bill.getEntrustId()), listPanel);
			parent.dispose();
		} else {
			SfDataFlowDialog d = (SfDataFlowDialog) parent;
			d.refresh(bill.getEntrustId());
		}
	}

	/**
	 * 
	 * 保存前校验
	 * 
	 * @param cpApply
	 * 
	 * @return
	 */

	protected boolean checkBeforeSave() {
		List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();
		StringBuilder errorInfo = new StringBuilder();
		String mainValidateInfo = ZcUtil.validateBillElementNull(bill,
				mainNotNullList);
		if (mainValidateInfo.length() != 0) {
			errorInfo.append("\n").append(mainValidateInfo.toString())
					.append("\n");
		}

		if (errorInfo.length() != 0) {
			JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	protected void doDelete() {
		requestMeta.setFuncId(deleteButton.getFuncId());
		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();

		int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
		if (num == JOptionPane.YES_OPTION) {
			boolean success = true;
			String errorInfo = "";
			try {
				requestMeta.setFuncId(deleteButton.getFuncId());
				sfJdReportServiceDelegate.deleteByPrimaryKeyFN(
						bill.getJdReporId(), requestMeta);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				success = false;
				errorInfo += e.getMessage();
			}

			if (success) {
				this.listCursor.removeCurrentObject();
				JOptionPane.showMessageDialog(this, "删除成功！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				refreshListPanel();
				doExit();
			} else {
				JOptionPane.showMessageDialog(this, "删除失败 ！\n" + errorInfo,
						"错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public boolean isDataChanged() {
		if (!this.saveButton.isVisible() || !saveButton.isEnabled()) {
			return false;
		}
		return !DigestUtil.digest(oldDossier).equals(
				DigestUtil.digest(listCursor.getCurrentObject()));
	}

	private void doPrint() {
		if (wordPane != null) {
			wordPane.print();
		}
	}

	private void doEdit() {

		this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
		this.openAndProtect = false;
		wordPane.unProtectDoc(SfElementConstants.WORD_PASSWORD);
		updateFieldEditorsEditable();
		setButtonStatus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createFieldEditors
	 * ()
	 */
	@Override
	public List<AbstractFieldEditor> createFieldEditors() {

		List<AbstractFieldEditor> editorList = new ArrayList<AbstractFieldEditor>();

		SfEntrustHandler entrustHandler = new SfEntrustHandler() {

			@Override
			public void excute(List selectedDatas) {
				// TCJLODO Auto-generated method stub
				for (Object obj : selectedDatas) {
					SfJdReport currentBill = (SfJdReport) listCursor
							.getCurrentObject();
					SfEntrust entrust = (SfEntrust) obj;
					entrust = getEntrust(entrust.getEntrustId());
					currentBill.setResult(getJdResult(entrust.getEntrustId()));
					currentBill.setEntrust(entrust);
					currentBill.setName(entrust.getName() + "鉴定意见书");
					setEditingObject(currentBill);
					selectEntrust(entrust);
				}
			}

			public void afterClear() {
				SfJdReport currentBill = (SfJdReport) listCursor
						.getCurrentObject();
				currentBill.setResult(new SfJdResult());
				currentBill.setEntrust(new SfEntrust());
				currentBill.setName(null);
				setEditingObject(currentBill);
			}

			/*
			 * public boolean beforeSelect(ElementConditionDto dto) { SfJdReport
			 * bill = (SfJdReport) listCursor.getCurrentObject(); if
			 * (bill.getDossierType() == null ||
			 * bill.getDossierType().trim().length() == 0) {
			 * JOptionPane.showMessageDialog(self, "请先选择卷宗类别！", "提示",
			 * JOptionPane.INFORMATION_MESSAGE); return false; } return true; }
			 */
		};
		entrustDto.setDattr1("SF_JD_REPORT");
		entrustDto.setCoCode(requestMeta.getSvCoCode());
		entrustDto.setUserId(requestMeta.getSvUserID());
		
		ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), entrustDto, 20, entrustHandler,
				entrustHandler.getColumNames(), "鉴定委托", "entrustCode");

		TextFieldEditor reportCode = new TextFieldEditor(
				LangTransMeta.translate(SfJdReport.COL_REPORT_CODE),"reportCode");
		TextFieldEditor name = new TextFieldEditor(	LangTransMeta.translate(SfJdReport.COL_NAME), "name");
		AsValFieldEditor status = new AsValFieldEditor(	LangTransMeta.translate(SfJdReport.COL_STATUS), "status",SfJdReport.SF_VS_REPORT_STATUS);
		AsValFieldEditor dossierType = new AsValFieldEditor(LangTransMeta.translate(SfJdReport.COL_REPORT_TYPE),"reportType", SfJdReport.SF_VS_JD_RESULT_TYPE) {
			@Override
			protected void afterChange(AsValComboBox field) {
				/*
				 * if (field.getSelectedAsVal() == null ||
				 * pageStatus.equals(ZcSettingConstants.PAGE_STATUS_BROWSE)) {
				 * entrustDto.setDattr2(null); return; } String valId =
				 * field.getSelectedAsVal().getValId();
				 * entrustDto.setDattr2(valId); SfJdReport bill = (SfJdReport)
				 * listCursor.getCurrentObject(); if (bill.getEntrustId() !=
				 * null && valId != null) { SfEntrust entrust =
				 * getEntrust(bill.getEntrustId()); selectEntrust(entrust); }
				 */
			}
		};
		// dossierType.addv
		TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfJdReport.COL_REMARK), "remark", 100,	2, 5);
		TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfJdReport.COL_INPUTOR), "inputorName");
		DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfJdReport.COL_INPUT_DATE), "inputDate");

		editorList.add(entrust);
		editorList.add(reportCode);
		editorList.add(name);

		editorList.add(dossierType);
		editorList.add(status);
		editorList.add(inputor);
		editorList.add(inputDate);

		editorList.add(remark);

		return editorList;

	}

	protected SfJdResult getJdResult(BigDecimal entrustId) {
		SfJdResult result=(SfJdResult)zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfJdResultMapper.selectByEntrustId", entrustId, requestMeta);
		if(result==null)return null;
		
		result = sfJdResultServiceDelegate.selectByPrimaryKey(result.getJdResultId(), requestMeta);
		return result;
	}

	protected void init() {

		this.initToolBar(toolBar);

		this.setLayout(new BorderLayout());

		this.add(toolBar, BorderLayout.NORTH);

		if (this.billClass != null && this.eleMeta != null) {

			initFieldEditorPanel(this.billClass, this.eleMeta);

		} else {

			initFieldEditorPanel();

		}

		workPanel.setLayout(new BorderLayout());

		JComponent subPanel = createSubBillPanel();
		if (subPanel == null) {
			subPanel = new JPanel();
		}
		/*
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sp.setOneTouchExpandable(true);// 让分割线显示出箭头
		sp.setContinuousLayout(true);// 操作箭头，重绘图形
		sp.setDividerLocation(0.28);
		sp.setDividerSize(15);// 設置分割線寬度的大小
		sp.add(fieldEditorPanel, JSplitPane.TOP);
		sp.add(subPanel, JSplitPane.BOTTOM);
		workPanel.add(sp, BorderLayout.CENTER);
		this.add(workPanel, BorderLayout.CENTER);
		*/
		JPanel p=new JPanel();
		p.setLayout(new BorderLayout());
		p.add(fieldEditorPanel,BorderLayout.NORTH);
		mainTab.add(p,"基本信息"); 
		workPanel.add(mainTab,BorderLayout.CENTER);
		this.add(workPanel, BorderLayout.CENTER);
		
		// 增加菜单
		initMenu();
		// 初始化提示框
		intProgressDialog();
	}

	private void initMenu() {

		JPopupMenu.setDefaultLightWeightPopupEnabled(false);

		JMenuItem mItemWtfName = new JMenuItem(SfClientUtil.MENU_WTF_NAME);
		mItemWtfName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_WTF_NAME);
			}
		});
		JMenuItem mItemWtfAdress = new JMenuItem(SfClientUtil.MENU_WTF_ADDRESS);
		mItemWtfAdress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_WTF_ADDRESS);
			}
		});
		JMenuItem mItemWtfZip = new JMenuItem(SfClientUtil.MENU_WTF_ZIP);
		mItemWtfZip.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_WTF_ZIP);
			}
		});
		JMenuItem mItemWtfLinkMan = new JMenuItem(
				SfClientUtil.MENU_WTF_LINK_MAN);
		mItemWtfLinkMan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_WTF_LINK_MAN);
			}
		});
		JMenuItem mItemWtfLinkTel = new JMenuItem(
				SfClientUtil.MENU_WTF_LINK_TEL);
		mItemWtfLinkTel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_WTF_LINK_TEL);
			}
		});
		JMenuItem mItemSjrName = new JMenuItem(SfClientUtil.MENU_SJR_SJR);
		mItemSjrName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_SJR_SJR);
			}
		});
		JMenuItem mItemSjrTel = new JMenuItem(SfClientUtil.MENU_SJR_SJR_TEL);
		mItemSjrTel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_SJR_SJR_TEL);
			}
		});
		JMenuItem mItemSjrZjType = new JMenuItem(
				SfClientUtil.MENU_SJR_SJR_ZJ_TYPE);
		mItemSjrZjType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_SJR_SJR_ZJ_TYPE);
			}
		});
		JMenuItem mItemSjrZjCode = new JMenuItem(
				SfClientUtil.MENU_SJR_SJR_ZJ_CODE);
		mItemSjrZjCode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_SJR_SJR_ZJ_CODE);
			}
		});
		JMenuItem mItemJdTargetName = new JMenuItem(
				SfClientUtil.MENU_JD_TARGET_NAME);
		mItemJdTargetName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_NAME);
			}
		});
		JMenuItem mItemJdTargetSex = new JMenuItem(
				SfClientUtil.MENU_JD_TARGET_SEX);
		mItemJdTargetSex.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_SEX);
			}
		});
		JMenuItem mItemJdTargetAge = new JMenuItem(
				SfClientUtil.MENU_JD_TARGET_AGE);
		mItemJdTargetAge.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_AGE);
			}
		});
		JMenuItem mItemJdTargetIdName = new JMenuItem(
				SfClientUtil.MENU_JD_TARGET_ID_NAME);
		mItemJdTargetIdName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_ID_NAME);
			}
		});
		JMenuItem mItemJdTargetIdCode = new JMenuItem(
				SfClientUtil.MENU_JD_TARGET_ID_CODE);
		mItemJdTargetIdCode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_ID_CODE);
			}
		});
		JMenuItem mItemJdTargetPhone = new JMenuItem(
				SfClientUtil.MENU_JD_TARGET_PHONE);
		mItemJdTargetPhone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_PHONE);
			}
		});
		JMenuItem mItemJdTargetAddress = new JMenuItem(
				SfClientUtil.MENU_JD_TARGET_ADDRESS);
		mItemJdTargetAddress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_ADDRESS);
			}
		});
		JMenuItem mItemJdTargetZip = new JMenuItem(
				SfClientUtil.MENU_JD_TARGET_ZIP);
		mItemJdTargetZip.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_ZIP);
			}
		});
		JMenuItem mItemJdBrief = new JMenuItem(SfClientUtil.MENU_JD_BRIEF);
		mItemJdBrief.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_BRIEF);
			}
		});
		JMenuItem mItemJdName = new JMenuItem(SfClientUtil.MENU_JD_NAME);
		mItemJdName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_NAME);
			}
		});
		JMenuItem mItemJdCode = new JMenuItem(SfClientUtil.MENU_JD_CODE);
		mItemJdCode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_CODE);
			}
		});
		JMenuItem mItemJdMaterial = new JMenuItem(
				SfClientUtil.MENU_JD_MATERIALS);
		mItemJdMaterial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_MATERIALS);
			}
		});
		JMenuItem mItemJdRequir = new JMenuItem(SfClientUtil.MENU_JD_REQUIRE);
		mItemJdRequir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_REQUIRE);
			}
		});
		JMenuItem mItemJdMajor = new JMenuItem(SfClientUtil.MENU_JD_MAJOR);
		mItemJdMajor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_MAJOR);
			}
		});
		JMenuItem mItemJdFzr = new JMenuItem(SfClientUtil.MENU_JD_FZR);
		mItemJdFzr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_FZR);
			}
		});
		JMenuItem mItemJdFhr = new JMenuItem(SfClientUtil.MENU_JD_FHR);
		mItemJdFhr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_FHR);
			}
		});
		JMenuItem mItemJdCompany = new JMenuItem(SfClientUtil.MENU_JD_COMPANY);
		mItemJdCompany.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_COMPANY);
			}
		});
		JMenuItem mItemJdAcceptDate = new JMenuItem(
				SfClientUtil.MENU_JD_ACCEPT_DATE);
		mItemJdAcceptDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_ACCEPT_DATE);
			}
		});
		JMenuItem mItemJdAcceptor = new JMenuItem(
				SfClientUtil.MENU_JD_ACCEPT_PERSON);
		mItemJdAcceptor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_ACCEPT_PERSON);
			}
		});
		JMenuItem mItemJdWtDate = new JMenuItem(SfClientUtil.MENU_WT_DATE);
		mItemJdWtDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_WT_DATE);
			}
		});

		  //--------------鉴定机构----start---------

		  JMenuItem mItemJdjgName=new JMenuItem(SfClientUtil.MENU_JDJG_NAME);
		  mItemJdjgName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JDJG_NAME);
			}
		}); 
		  JMenuItem mItemJdjgXkzh=new JMenuItem(SfClientUtil.MENU_JDJG_XKZH);
		  mItemJdjgXkzh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JDJG_XKZH);
			}
		}); 
		  JMenuItem mItemJdjgAddress=new JMenuItem(SfClientUtil.MENU_JDJG_ADDRESS);
		  mItemJdjgAddress.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JDJG_ADDRESS);
			}
		}); 
		  JMenuItem mItemJdjgTel=new JMenuItem(SfClientUtil.MENU_JDJG_TEL);
		  mItemJdjgTel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JDJG_TEL);
			}
		}); 
		  JMenuItem mItemJdjgFax=new JMenuItem(SfClientUtil.MENU_JDJG_FAX);
		  mItemJdjgFax.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JDJG_FAX);
			}
		}); 
		  JMenuItem mItemJdjgLinkMan=new JMenuItem(SfClientUtil.MENU_JDJG_LINK_MAN);
		  mItemJdjgLinkMan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JDJG_LINK_MAN);
			}
		});  
		  JMenuItem mItemJdjgZip=new JMenuItem(SfClientUtil.MENU_JDJG_ZIP);
		  mItemJdjgZip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JDJG_ZIP);
			}
		}); 
		  //--------------鉴定机构----end---------

			
			//---------鉴定记录---------start-----------------		  
		  JMenuItem mItemJdRecordJdDate=new JMenuItem(SfClientUtil.MENU_RECORD_JD_DATE);
		  mItemJdRecordJdDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_RECORD_JD_DATE);
			}
		}); 		  
		  JMenuItem mItemJdRecordJdOpinion=new JMenuItem(SfClientUtil.MENU_RECORD_JD_OPINION);
		  mItemJdRecordJdOpinion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_RECORD_JD_OPINION);
			}
		});		  
		  JMenuItem mItemJdRecordJdProcess=new JMenuItem(SfClientUtil.MENU_RECORD_JD_PROCESS);
		  mItemJdRecordJdProcess.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_RECORD_JD_PROCESS);
			}
		});		  
		  JMenuItem mItemJdRecordJdResult=new JMenuItem(SfClientUtil.MENU_RECORD_JD_RESULT);
		  mItemJdRecordJdResult.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_RECORD_JD_RESULT);
			}
		});		  
		  JMenuItem mItemJdRecordZcPersons=new JMenuItem(SfClientUtil.MENU_RECORD_ZC_PERSONS);
		  mItemJdRecordZcPersons.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_RECORD_ZC_PERSONS);
			}
		});	
			//---------鉴定记录---------end-----------------	  

			
			//---------鉴定报告---------start-----------------
		  
		  JMenuItem mItemJdReportNo=new JMenuItem(SfClientUtil.MENU_REPORT_NO);
		  mItemJdReportNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_REPORT_NO);
			}
		});	  
			
			//---------鉴定报告---------start-----------------
		  
		JMenuItem mItemReferenceReport = new JMenuItem(
				SfClientUtil.MENU_REFRENCE_REORT);
		mItemReferenceReport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				referenceReport(SfClientUtil.MENU_REFRENCE_REORT);
			}
		});

		  
				JMenuItem mItemLookupReport = new JMenuItem(SfClientUtil.MENU_LOOKUP_ENTRUST_REPORT);
				mItemLookupReport.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						lookupMenuPerform(SfClientUtil.MENU_LOOKUP_ENTRUST_REPORT);
					}
				});
				
				  
				JMenuItem mItemLookupRecord = new JMenuItem(SfClientUtil.MENU_LOOKUP_ENTRUST_RECORD);
				mItemLookupRecord.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						lookupMenuPerform(SfClientUtil.MENU_LOOKUP_ENTRUST_RECORD);
					}
				});
				  
				JMenuItem mItemReprotFace = new JMenuItem(SfClientUtil.MENU_LOOKUP_ENTRUST_REPORT_FACE);
				mItemReprotFace.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						lookupMenuPerform(SfClientUtil.MENU_LOOKUP_ENTRUST_REPORT_FACE);
					}
				});
						
		JMenuItem mItemFillModel = new JMenuItem(SfClientUtil.MENU_FILL_CUR);
		mItemFillModel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fillModel(SfClientUtil.MENU_FILL_CUR);
			}
		});

		menuInsertContent.add(mItemJdCode);
		menuInsertContent.add(mItemJdName);
		menuInsertContent.add(mItemJdBrief);
		menuInsertContent.add(mItemJdMaterial);
		menuInsertContent.add(mItemJdRequir);
		menuInsertContent.add(mItemJdMajor);
		menuInsertContent.add(mItemJdFzr);
		menuInsertContent.add(mItemJdFhr);
		menuInsertContent.add(mItemJdWtDate);
		menuInsertContent.add(mItemJdAcceptDate);
		menuInsertContent.add(mItemJdAcceptor);
		menuInsertWtf.add(mItemWtfName);
		menuInsertWtf.add(mItemWtfLinkMan);
		menuInsertWtf.add(mItemWtfLinkTel);
		menuInsertWtf.add(mItemWtfAdress);
		menuInsertWtf.add(mItemWtfZip);
		menuInsertContent.addSeparator();
		menuInsertContent.add(menuInsertWtf);
		menuInsertSjr.add(mItemSjrName);
		menuInsertSjr.add(mItemSjrTel);
		menuInsertSjr.add(mItemSjrZjType);
		menuInsertSjr.add(mItemSjrZjCode);
		menuInsertContent.addSeparator();
		menuInsertContent.add(menuInsertSjr);
		menuInsertJdTarget.add(mItemJdTargetName);
		menuInsertJdTarget.add(mItemJdTargetSex);
		menuInsertJdTarget.add(mItemJdTargetAge);
		menuInsertJdTarget.add(mItemJdTargetIdName);
		menuInsertJdTarget.add(mItemJdTargetIdCode);
		menuInsertJdTarget.add(mItemJdTargetPhone);
		menuInsertJdTarget.add(mItemJdTargetAddress);
		menuInsertJdTarget.add(mItemJdTargetZip);
		menuInsertContent.addSeparator();
		menuInsertContent.add(menuInsertJdTarget);
		
		  menuInsertJdjg.add(mItemJdjgName);
		  menuInsertJdjg.add(mItemJdjgXkzh);
		  menuInsertJdjg.add(mItemJdjgLinkMan);
		  menuInsertJdjg.add(mItemJdjgTel);
		  menuInsertJdjg.add(mItemJdjgFax);
		  menuInsertJdjg.add(mItemJdjgZip);
		  menuInsertJdjg.add(mItemJdjgAddress);  
		  menuInsertContent.addSeparator();
		  menuInsertContent.add(menuInsertJdjg);		  
		  
		  menuInsertJdRecord.add(mItemJdRecordJdDate);
		  menuInsertJdRecord.add(mItemJdRecordJdOpinion);
		  menuInsertJdRecord.add(mItemJdRecordJdProcess);
		  menuInsertJdRecord.add(mItemJdRecordJdResult);
		  menuInsertJdRecord.add(mItemJdRecordZcPersons);
		  menuInsertContent.addSeparator();
		  menuInsertContent.add(menuInsertJdRecord); 
		  
		  menuInsertContent.addSeparator();
			menuInsertContent.add(mItemJdReportNo);
//		  menuInsertContent.add(menuInsertJdReport);

		initModelMenu();
		menuBar.add(menuInsertModel);

		menuFill.add(mItemFillModel);
		menuBar.add(menuFill);
		
//		menuRefrence.add(mItemReferenceReport);
//		menuBar.add(menuRefrence);

		menuBar.add(menuInsertContent);
		parent.setJMenuBar(menuBar);
		
		menuLookup.add(mItemLookupRecord);
		menuLookup.add(mItemLookupReport);
		menuLookup.add(mItemReprotFace);
		menuBar.add(menuLookup);
		
		initCertificateMenu();
		menuBar.add(menuCertificateFile);
		
	}

	private void initCertificateMenu() {
		//get jdjg certificates
		_initCertificateJdjgMenu();
		
		//get jdperson certificates
		_initCertificateJdPersonMenu();
		
		menuCertificateFile.add(menuCertificateFileJdjg);
		menuCertificateFile.add(menuCertificateFileJdPerson);
	}

	private void _initCertificateJdPersonMenu() {
		ElementConditionDto dto=new ElementConditionDto();
		dto.setCoCode(requestMeta.getSvCoCode());
		dto.setDattr2("haveZizhi");
		List personLst=sfJdPersonServiceDelegate.getMainDataLst(dto, requestMeta);
		if(personLst!=null && personLst.size()>0){
			List idLst=new ArrayList();
			for(int i=0;i<personLst.size();i++){
				SfJdPerson jg=(SfJdPerson) personLst.get(i);
				idLst.add(jg.getJdPersonId());
			}
			dto=new ElementConditionDto();
			dto.setDattr1(SfCertificate.VS_SF_CERTIFICATE_TYPE_zizhi);
			dto.setPmAdjustCodeList(idLst);
			List certs=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfCertificateMapper.selectByOwner2", dto, requestMeta); 			
			if(certs!=null){
				for(int i=0;i<certs.size();i++){
					final SfCertificate cer=(SfCertificate) certs.get(i);
					for(int j=0;j<personLst.size();j++){
						SfJdPerson person=(SfJdPerson) personLst.get(j);
						if(cer.getZfOwnerId().equals(person.getJdPersonId())){
							 String name=person.getName(); 
							final String name2=getRightName(name,jdPersonCerts); 
							JMenuItem mitem = new JMenuItem(name2);
							mitem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									showCertPerson(name2);
								}
							});
							menuCertificateFileJdPerson.add(mitem);
							jdPersonCerts.put(name2, cer);
							
						}
						
					}
				}
			}
		}
	}
	
/**
 * 解决多个资质的情况
 * @param name
 * @return
 */
	private String getRightName(String name, Hashtable lst) {

		if(lst.containsKey(name)){
			name=name+">";
			return getRightName(name,lst);
		}else{
			return name;
		} 
	}


	private void _initCertificateJdjgMenu() {
		ElementConditionDto dto=new ElementConditionDto();
		dto.setCoCode(requestMeta.getSvCoCode());
		List jgLst=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdjgMapper.selectMainDataLst", dto, requestMeta);
		if(jgLst!=null && jgLst.size()>0){
			SfJdjg jg=(SfJdjg) jgLst.get(0);
			dto.setSfId(jg.getJgId());
			dto.setDattr1(SfCertificate.VS_SF_CERTIFICATE_TYPE_zizhi);
			List certs=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfCertificateMapper.selectByOwner2", dto, requestMeta);
			if(certs!=null){
				for(int i=0;i<certs.size();i++){
					SfCertificate cer=(SfCertificate) certs.get(i);
					final String name=getRightName(cer.getName(), jdjgCerts);
					JMenuItem mitem = new JMenuItem(name);
					mitem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							showCertJdjg(name);
						}
					});
					menuCertificateFileJdjg.add(mitem);
					jdjgCerts.put(name, cer);
				}
			}
		}
	}

	protected void showCertPerson(String cerName) {
		SfCertificate cer=jdPersonCerts.get(cerName);
		if(cer==null)return;
		showCerFile(cer);
	}
	protected void showCertJdjg(String cerName) {
		SfCertificate cer=jdjgCerts.get(cerName);
		if(cer==null)return;
		showCerFile(cer);
	}

	private void showCerFile(SfCertificate cer) {
		 try {
		      if (cer.getZfFileBlobid() == null || "".equals(cer.getZfFileBlobid())) {
		        return;
		      }

		    AsFile  asFile = baseDataServiceDelegate.downloadFile(cer.getZfFileBlobid(), requestMeta);
		    String defaultSavePath = "C:/ufgov/certificates/";
		    
		        File dir = new File(defaultSavePath);
		        TbDocService.deleteFile(dir);//将下载目录的文件夹清空
		        if (!dir.exists()) {
		          dir.mkdirs();
		        }
		        File defaultFile = new File(defaultSavePath + asFile.getFileName());
		        defaultFile.delete();
		        saveFileToLocal(defaultFile,asFile);
		       
		    } catch (Exception e) {
		      logger.error(e.getMessage(), e);
		      JOptionPane.showMessageDialog(this, "打开文件失败！", "错误", JOptionPane.ERROR_MESSAGE);

		    }
	}

	  private void saveFileToLocal(File defaultFile,AsFile asFile) {

	    int result = JFileChooser.APPROVE_OPTION; //fileChooser.showSaveDialog(this);

	    if (result == JFileChooser.APPROVE_OPTION) {

	      File selectedFile = defaultFile;

	      FileOutputStream os = null;

	      try {

	        os = new FileOutputStream(defaultFile);

	        os.write(asFile.getFileContent());

	      } catch (Exception e) {

	        throw new RuntimeException(e);

	      } finally {

	        try {

	          if (os != null) {

	            os.close();

	          }

	        } catch (Exception e) {

	          new RuntimeException(e.getMessage(), e);

	        }

	      }

	      int yesNoResult = JOptionPane.YES_OPTION;

	      if (yesNoResult == JOptionPane.YES_OPTION) {

	        try {

	          Desktop.getDesktop().open(defaultFile);

	        } catch (Exception e) {

	          JOptionPane.showMessageDialog(this, "抱歉！没有找到合适的程序来打开文件！", "提示", JOptionPane.INFORMATION_MESSAGE);

	          return;

	        }

	      }

	    }

	  }
	protected void lookupMenuPerform(String menuText) {
		if(menuText.equals(SfClientUtil.MENU_LOOKUP_ENTRUST_REPORT)){
			SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();
			RefrenceEntrustDialog entrustDialog = new RefrenceEntrustDialog(parent,this, bill.getEntrust().getEntrustId());
//			entrustDialog.setSize(d)
		    entrustDialog.setMinimumSize(new Dimension(300, 150));
		    entrustDialog.pack();
		    entrustDialog.moveToScreenCenter();
		    entrustDialog.setVisible(true);
		}else if(menuText.equals(SfClientUtil.MENU_LOOKUP_ENTRUST_RECORD)){
			openJdRecord();
		}else if(menuText.equals(SfClientUtil.MENU_LOOKUP_ENTRUST_REPORT_FACE)){
			openJdReportFace();
		}
	}

	/**
	 * 打开鉴定文书封面
	 */
	private void openJdReportFace() {

		if(recordFaceOpened)return;
		
		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();
		if (bill == null || bill.getEntrustCode() == null) {
			JOptionPane.showMessageDialog(this, "请先选择委托.", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} 
		if (bill.getReportCode() == null) {
			JOptionPane.showMessageDialog(this, "请先选择报告编号.", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		String fileId=AsOptionMeta.getOptVal(SfJdReport.OPT_SF_REPORT_FACE_FILE_ID);
		
		if(fileId!=null){
			final WordPane wp=new WordPane(); 
		    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH-10, UIConstants.DIALOG_0_LEVEL_HEIGHT-10);
		    parent.validate();
		    wp.addPropertyChangeListener(WordPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
			      public void propertyChange(PropertyChangeEvent evt) {
			        //打开文件完成之后的回调函数
			        boolean isSuccess = (Boolean) evt.getNewValue();
			        if (isSuccess) {
			          //下面一句是为了打开word后刷新窗口
//			          parent.setSize(new Dimension(parent.getSize().width +200, parent.getSize().height +200));

			    	    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH, UIConstants.DIALOG_0_LEVEL_HEIGHT);
			    	    parent.validate();
			    	    parent.moveToScreenCenter(); 
			    	    _initFaceFile(wp);
			        }
			      }
			    });
			mainTab.add("鉴定意见书封面", wp);
			mainTab.setSelectedIndex(mainTab.getTabCount()-1);
			String ff= WordFileUtil.loadMold(fileId);
			wp.open(ff);
	    	recordFaceOpened=true;
		}
 
	}

	protected void _initFaceFile(WordPane wp) {

		SfJdReport currentBill = (SfJdReport) this.listCursor.getCurrentObject();
		SfBookmarkUtil bku = new SfBookmarkUtil();
		List<SfBookmark> bks = bku.getEntrustBookValueLst(currentBill.getEntrust());
		bks.addAll(bku.getEntrustorBookValueLst(currentBill.getEntrust().getEntrustor()));
		bks.addAll(bku.getJdTargetBookValueLst(currentBill.getEntrust().getJdTarget()));
		bks.addAll(bku.getJdjgBookValueLst(requestMeta.getSvCoCode()));
		bks.addAll(bku.getJdRecordBookValueLst(currentBill.getResult()));
		bks.addAll(bku.getJdReportBookValueLst(currentBill));
		wp.replaceBookMarks(bks);
	}

	private void openJdRecord() {
		if(recordFileOpened)return;

		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();
		if (bill == null || bill.getEntrustCode() == null) {
			JOptionPane.showMessageDialog(this, "请先选择委托.", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		 
		SfJdResult r=getJdResult(bill.getEntrustId());
	    if(r.getJdRecordFileLst()!=null && r.getJdRecordFileLst().size()>0){
	    	for(int i=0;i<r.getJdRecordFileLst().size();i++){
	    		SfJdResultFile rf=(SfJdResultFile) r.getJdRecordFileLst().get(i); 
	    			addTab(rf); 
	    	}
	    	recordFileOpened=true;
	    }
		
	}

	private void addTab(SfJdResultFile rf) {

		  if(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE_word.equalsIgnoreCase(rf.getModel().getFileType())){
			  addSubWordPane(rf);
		  }else if(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE_excel.equalsIgnoreCase(rf.getModel().getFileType())){
			  addSubExcelPane(rf);
		  } 
		    
	}
	private void addSubExcelPane(SfJdResultFile rf) {
		if(rf.getFileId()!=null){
			final ExcelPane wp=new ExcelPane(); 
		    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH-10, UIConstants.DIALOG_0_LEVEL_HEIGHT-10);
		    parent.validate();
		    wp.addPropertyChangeListener(ExcelPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
		      public void propertyChange(PropertyChangeEvent evt) {
		        //打开文件完成之后的回调函数
		        boolean isSuccess = (Boolean) evt.getNewValue();
		        if (isSuccess) {
		          //下面一句是为了打开word后刷新窗口
//		          parent.setSize(new Dimension(parent.getSize().width +200, parent.getSize().height +200));

		    	    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH, UIConstants.DIALOG_0_LEVEL_HEIGHT);
		    	    parent.validate();
		    	    parent.moveToScreenCenter();
//		  	    	parent.validate();  
		        }
		      }
		    });
		    
			mainTab.add(rf.getName(), wp);
			mainTab.setSelectedIndex(mainTab.getTabCount()-1);
			String ff= WordFileUtil.loadMold(rf.getFileId());
			wp.open(ff);
		}
	}

	private void addSubWordPane(SfJdResultFile rf) {
		if(rf.getFileId()!=null){
			final WordPane wp=new WordPane(); 
		    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH-10, UIConstants.DIALOG_0_LEVEL_HEIGHT-10);
		    parent.validate();
		    wp.addPropertyChangeListener(WordPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
			      public void propertyChange(PropertyChangeEvent evt) {
			        //打开文件完成之后的回调函数
			        boolean isSuccess = (Boolean) evt.getNewValue();
			        if (isSuccess) {
			          //下面一句是为了打开word后刷新窗口
//			          parent.setSize(new Dimension(parent.getSize().width +200, parent.getSize().height +200));

			    	    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH, UIConstants.DIALOG_0_LEVEL_HEIGHT);
			    	    parent.validate();
			    	    parent.moveToScreenCenter(); 
			        }
			      }
			    });
			mainTab.add(rf.getName(), wp);
			mainTab.setSelectedIndex(mainTab.getTabCount()-1);
			String ff= WordFileUtil.loadMold(rf.getFileId());
			wp.open(ff);
		}
	}

	private void initModelMenu() {
		ElementConditionDto dto = new ElementConditionDto();
		dto.setDattr1(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_DOC_TYPE_REPORT);
		List modelLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdRecordFileModelMapper.getModelFileMenuItem",
						dto, requestMeta);
		for (int i = 0; i < modelLst.size(); i++) {
			Map m = (Map) modelLst.get(i);
			String name = (String) m.get("MENUITEMNAME");
			SfJdRecordFileModel md = new SfJdRecordFileModel();
			md.setFileId((String) m.get("FILE_ID"));
			md.setFileType((String) m.get("FILE_TYPE"));
			md.setFileName((String) m.get("FILE_NAME"));
			md.setName((String) m.get("NAME"));
			md.setModelId((BigDecimal) m.get("MODEL_ID"));
			modelFileMenuMap.put(name, md);

			JMenuItem item = new JMenuItem(name);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JMenuItem item = (JMenuItem) e.getSource();
					insertFileModel(item.getText());
				}
			});
			menuInsertModel.add(item);
		}
	}

	protected void insertFileModel(String menuText) {
		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();
		if (bill == null || bill.getEntrustCode() == null) {
			JOptionPane.showMessageDialog(this, "请先选择委托.", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		SfJdRecordFileModel model = modelFileMenuMap.get(menuText);
		if (model == null || model.getFileId() == null) {

			JOptionPane.showMessageDialog(this, "没有对应的模板文件.", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		bill.setModel(model);
		bill.setJdReportFileId(model.getFileId());
		modelFileId=model.getFileId();
		refreshFilePanel();
		//根据菜单有无报告两字，变更相关信息 
		if(menuText.indexOf("报告")>0 && bill.getName().indexOf("报告")<0){
			String s1=bill.getName().substring(0, bill.getName().indexOf("意见书"));
			bill.setName(s1+"报告");
			bill.setReportType(SfJdReport.RESULT_TYPE_JYBG);
		}else if(bill.getName().indexOf("报告")>0){
			String s1=bill.getName().substring(0, bill.getName().indexOf("报告"));
			bill.setName(s1+"意见书");
			bill.setReportType(SfJdReport.RESULT_TYPE_YJS);
		}
		setEditingObject(bill);

	}

	protected void referenceReport(String menuRefrenceReort) {
		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();
		if (bill == null || bill.getEntrustCode() == null) {
			JOptionPane.showMessageDialog(this, "请先选择委托.", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} 
		RefrenceEntrustDialog entrustDialog = new RefrenceEntrustDialog(parent,this, bill.getEntrust().getEntrustId());
//		entrustDialog.setSize(d)
	    entrustDialog.setMinimumSize(new Dimension(300, 150));
	    entrustDialog.pack();
	    entrustDialog.moveToScreenCenter();
	    entrustDialog.setVisible(true);
	}

	protected void fillModel(String menuFillCur) {
		SfJdReport currentBill = (SfJdReport) listCursor.getCurrentObject(); 
		if (currentBill.getReportCode() == null) {
			JOptionPane.showMessageDialog(this, "请先选择报告编号.", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		SfBookmarkUtil bku = new SfBookmarkUtil();
		List<SfBookmark> bks = bku.getEntrustBookValueLst(currentBill.getEntrust());
		bks.addAll(bku.getEntrustorBookValueLst(currentBill.getEntrust().getEntrustor()));
		bks.addAll(bku.getJdTargetBookValueLst(currentBill.getEntrust().getJdTarget()));
		bks.addAll(bku.getJdjgBookValueLst(requestMeta.getSvCoCode()));
		bks.addAll(bku.getJdRecordBookValueLst(currentBill.getResult()));
		bks.addAll(bku.getJdReportBookValueLst(currentBill));
		wordPane.replaceBookMarks(bks);

	}

	protected void insertContent(String menuTitle) {
		SfJdReport bill = (SfJdReport) this.listCursor.getCurrentObject();
		if (bill == null || bill.getEntrustCode() == null) {
			JOptionPane.showMessageDialog(this, "请先选择委托.", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		String txt="";
		if(menuTitle.startsWith("鉴定机构")){
			SfJdjg jdjg=getJdjg();
			txt=SfClientUtil.getTxtFromJdjg(menuTitle, jdjg);
		}else{
			txt=SfClientUtil.getTxtFromBill(menuTitle, bill, requestMeta);
		} 
		wordPane.insertTextToDoc(txt); 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel
	 * ()
	 */
	@Override
	public JComponent createSubBillPanel() {

		this.tabPane.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				JTabbedPane tab = (JTabbedPane) e.getSource();
				SfJdReport bulletin = (SfJdReport) self.listCursor
						.getCurrentObject();

				JPanel pan = (JPanel) tab.getSelectedComponent();

				/*
				 * if ("panel_filenamezb".equals(pan.getName())) {
				 * refreshZbFile(zbFileID); }
				 */
				/*
				 * if (isShowPanel && pan!=null) { if
				 * ("panel_filename1".equals(pan.getName()) && cnt1++ < 1) {
				 * refreshWordPaneFile1(bulletin); } else if
				 * ("panel_filename2".equals(pan.getName()) && cnt2++ < 1) {
				 * refreshWordPaneFile2(bulletin); }
				 * 
				 * }
				 */
			}
		});

		return this.tabPane;
	}

	public void doExit() {
		// TCJLODO Auto-generated method stub

		if (isDataChanged()) {

			int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存",
					"保存确认", 0);

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

	protected void selectEntrust(SfEntrust entrust) {

		if (entrust != null) {

			SfJdReport bill = (SfJdReport) listCursor.getCurrentObject();
			bill.setEntrustId(entrust.getEntrustId());
			bill.setName(entrust.getName() + "鉴定意见书");
			bill.setReportCode(getExistsNo(bill));
			if(bill.getReportCode()!=null){
			  getNoBtn.setVisible(false);
			}
			setEditingObject(bill);
			createWord(entrust);
		}
	}

	private void createWord(SfEntrust entrust) {

		/*
		 * SfJdReport bill = (SfJdReport) listCursor.getCurrentObject(); entrust
		 * = bill.getEntrust(); if (entrust != null && bill.getDossierType() !=
		 * null && bill.getDossierType().trim().length() > 0) { Hashtable
		 * userData = new Hashtable(); userData.put("dossier", bill);
		 * userData.put(IWordHandler.FILE_NAME, bill.getEntrustCode());
		 * userData.put("entrust", bill.getEntrust());
		 * 
		 * IWordHandler handler = null; if
		 * (bill.getDossierType().equals(SfJdReport.DOSSIER_TYPE_FA_YI)) {
		 * handler = new SfJdReportFaYiWordHandler(); } else if
		 * (bill.getDossierType().equals(SfJdReport.DOSSIER_TYPE_WU_ZHENG)) {
		 * handler = new SfJdReportWuZhengWordHandler(); } if (handler == null)
		 * { JOptionPane.showMessageDialog(this.parent, "没有找到模版，请手工编制", "提示",
		 * JOptionPane.WARNING_MESSAGE); return; } fileName =
		 * handler.createDocumnet(userData); if (wordPane != null) {
		 * wordPane.close(false); } wordPane.open(this.fileName); }
		 */

	}

	/**
	 * 送审
	 */
	protected void doSend() {

		boolean success = true;

		SfJdReport afterSaveBill = null;

		if (this.isDataChanged()) {
			JOptionPane.showMessageDialog(this, "数据发生改变，请先保存.", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		try {
			requestMeta.setFuncId(this.sendButton.getFuncId());
			SfJdReport qx = (SfJdReport) ObjectUtil.deepCopy(this.listCursor
					.getCurrentObject());
			qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
			afterSaveBill = sfJdReportServiceDelegate.newCommitFN(qx,
					requestMeta);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			success = false;
			UIUtilities.showStaickTraceDialog(ex, this, "错误", ex.getMessage());
		}

		if (success) {
			this.listCursor.setCurrentObject(afterSaveBill);
			JOptionPane.showMessageDialog(this, "送审成功！", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			refreshListPanel();
			refreshMainData();
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
		SfJdReport qx = (SfJdReport) ObjectUtil.deepCopy(this.listCursor
				.getCurrentObject());
		requestMeta.setFuncId(this.suggestPassButton.getFuncId());
		GkCommentDialog commentDialog = new GkCommentDialog(
				DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager()
						.getActiveWindow(), ModalityType.APPLICATION_MODAL);
		if (commentDialog.cancel) {
			return;
		}
		boolean success = true;
		String errorInfo = "";
		try {
			qx.setComment(commentDialog.getComment());
			qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
			qx = sfJdReportServiceDelegate.auditFN(qx, requestMeta);
		} catch (Exception e) {
			success = false;
			logger.error(e.getMessage(), e);
			errorInfo += e.getMessage();
		}
		if (success) {
			JOptionPane.showMessageDialog(this, "审核成功！", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			refreshListPanel();
			refreshMainData();
			updateDataFlowDialog();
		} else {
			JOptionPane.showMessageDialog(this, "审核失败 ！" + errorInfo, "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 销审
	 */
	protected void doUnAudit() {
		SfJdReport qx = (SfJdReport) ObjectUtil.deepCopy(this.listCursor
				.getCurrentObject());
		boolean success = true;
		SfJdReport afterSaveBill = null;
		String errorInfo = "";
		int i = JOptionPane.showConfirmDialog(this, "是否确定消审？", "确认",
				JOptionPane.INFORMATION_MESSAGE);
		if (i != 0) {
			return;
		}
		try {
			requestMeta.setFuncId(unAuditButton.getFuncId());
			qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
			afterSaveBill = sfJdReportServiceDelegate
					.unAuditFN(qx, requestMeta);
		} catch (Exception e) {
			success = false;
			logger.error(e.getMessage(), e);
			errorInfo += e.getMessage();
		}
		if (success) {
			this.listCursor.setCurrentObject(afterSaveBill);
			JOptionPane.showMessageDialog(this, "销审成功！", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			refreshListPanel();
			refreshMainData();
			updateDataFlowDialog();
		} else {
			JOptionPane.showMessageDialog(this, "销审失败 ！" + errorInfo, "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 退回
	 */
	protected void doUnTread() {
		GkCommentUntreadDialog commentDialog = new GkCommentUntreadDialog(
				DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager()
						.getActiveWindow(), ModalityType.APPLICATION_MODAL);
		if (commentDialog.cancel) {
			return;
		}
		boolean success = true;
		SfJdReport afterSaveBill = null;
		String errorInfo = "";
		try {
			requestMeta.setFuncId(unTreadButton.getFuncId());
			SfJdReport qx = (SfJdReport) ObjectUtil.deepCopy(this.listCursor
					.getCurrentObject());
			qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
			qx.setComment(commentDialog.getComment());
			afterSaveBill = sfJdReportServiceDelegate
					.untreadFN(qx, requestMeta);
		} catch (Exception e) {
			success = false;
			logger.error(e.getMessage(), e);
			errorInfo += e.getMessage();
		}
		if (success) {
			this.listCursor.setCurrentObject(afterSaveBill);
			JOptionPane.showMessageDialog(this, "退回成功！", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			refreshListPanel();
			refreshMainData();
			updateDataFlowDialog();
		} else {
			JOptionPane.showMessageDialog(this, "退回失败 ！" + errorInfo, "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 收回
	 */
	protected void doCallback() {
		boolean success = true;
		SfJdReport afterSaveBill = null;
		String errorInfo = "";
		try {
			requestMeta.setFuncId(this.callbackButton.getFuncId());
			SfJdReport qx = (SfJdReport) ObjectUtil.deepCopy(this.listCursor
					.getCurrentObject());
			qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
			afterSaveBill = sfJdReportServiceDelegate.callbackFN(qx,
					requestMeta);
		} catch (Exception e) {
			success = false;
			logger.error(e.getMessage(), e);
			errorInfo += e.getMessage();
		}

		if (success) {
			JOptionPane.showMessageDialog(this, "收回成功！", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			refreshListPanel();
			refreshMainData();
			updateDataFlowDialog();
		} else {
			JOptionPane.showMessageDialog(this, "收回失败 ！" + errorInfo, "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void intProgressDialog() {

		initProgressBar();

		progressDialog = new JDialog(null, "提示", Dialog.ModalityType.MODELESS);

		progressDialog.add(openWordProgressBar, BorderLayout.CENTER);

		progressDialog.setUndecorated(true);

		progressDialog.setAlwaysOnTop(true);

	}

	private void initProgressBar() {

		if (openWordProgressBar == null) {

			openWordProgressBar = new JProgressBar(0, 100);

			openWordProgressBar.setStringPainted(true);

			// openWordProgressBar.setBounds(new Rectangle(101, 305, 420, 30));

		}

	}
	private SfJdjg getJdjg() {
		ElementConditionDto dto=new ElementConditionDto();
		dto.setCoCode(requestMeta.getSvCoCode());
		SfJdjg jdjg=(SfJdjg) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfJdjgMapper.selectMainDataLst", dto, requestMeta);
		return jdjg;
	}

	  public JMenuBar getMenuBar(){
		  return menuBar;
	  }

	public void referenceEntrust(SfEntrust entrust) {
		if(entrust!=null){
//			System.out.println(entrust.getCode()); 
			
			List lst=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdReportMapper.selectByEntrustId", entrust.getEntrustId(), requestMeta);
			if(lst!=null && lst.size()>0){
				SfJdReport bill=(SfJdReport) lst.get(0);
				bill=sfJdReportServiceDelegate.selectByPrimaryKey(bill.getJdReporId(), requestMeta); 

			     //getJdReportFileId
				SfJdReport inData = (SfJdReport) this.listCursor.getCurrentObject();
				//直接替换当前的模板
//				inData.setJdReportFileId(bill.getJdReportFileId());				
//			    refreshFilePanel();
				//打开报告文件，进行参照
				addWordPane(bill);
			}
		}
	}

	private void addWordPane(SfJdReport bill) {
		if(bill.getJdReportFileId()!=null){
			WordPane wp=new WordPane();
			referencReportNameIndex=referencReportNameIndex+1;
			mainTab.add("鉴定报告"+referencReportNameIndex, wp);
			mainTab.setSelectedIndex(mainTab.getTabCount()-1);
			String ff= WordFileUtil.loadMold(bill.getJdReportFileId());
			wp.open(ff);
		}
	}

}
