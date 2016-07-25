package com.ufgov.zc.client.sf.jdresult;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Dialog;
import java.awt.Rectangle;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
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
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.IntFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowUtil;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.util.SfBookmarkUtil;
import com.ufgov.zc.client.sf.util.SfJdPersonSelectHandler;
import com.ufgov.zc.client.util.freemark.IWordHandler;
import com.ufgov.zc.client.util.freemark.WordHandlerFactory;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.WordFileUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.client.zc.ztb.activex.WordPane;
import com.ufgov.zc.client.zc.ztb.util.GV;
import com.ufgov.zc.common.sf.model.SfBookmark;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdRecordFileModel;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdResultFile;
import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfJdResultServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.system.util.DateUtil;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.system.util.Utils;
import com.ufgov.zc.common.util.EmpMeta;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * 鉴定记录
 * 用于鉴定过程中记录鉴定步骤、情况和结论
 * 是鉴定文书的来源
 * 针对不同的鉴定专业，其鉴定记录的word模板是不一样的
 * 待办：毒物、法医尸检有多个word记录表，目前合并到一个word里面，后续要转换为一个树，将这几个word挂到树上，分别打开
 * @author Administrator
 *
 */
public class SfJdRecordEditPanel  extends AbstractMainSubEditPanel {

	  /**
	   * 
	   */
	  private static final long serialVersionUID = -3893361301490283305L;

	  private static final Logger logger = Logger.getLogger(SfJdRecordEditPanel.class);

	  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

	  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

	  private static String compoId = "SF_JD_RESULT";

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

	  protected ListCursor<SfJdResult> listCursor;

	  private SfJdResult oldAppendMaterialNotice;

	  public SfJdRecordListPanel listPanel;

	  protected SfJdRecordEditPanel self = this;

	  protected GkBaseDialog parent;

	  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

	  private BillElementMeta mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);

	  protected JTablePanel personsTablePanel = new JTablePanel();

	  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

	  private ISfJdResultServiceDelegate sfJdResultServiceDelegate;

	  private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

	  protected WordPane defaultWordPane = new WordPane();

	  private boolean openAndProtect = true;

	  protected String fileName = "";


	  JTabbedPane mainTab=new JTabbedPane();
	  
	  protected JClosableTabbedPane recordFileTabPan = new JClosableTabbedPane(JTabbedPane.BOTTOM){
		  /**
		 * 
		 */
		private static final long serialVersionUID = 2187722020350731719L;
		public boolean beforeCloseTab(){
			  return beforeClsTab();
		  }
		  public boolean afterCloseTab(){
			  return afterClsTab();
		  }
	  };
	  
	  private static final String MENU_INSERT_CONTENT ="插入内容";
	  private static final String MENU_WTF="委托方";  
	  private static final String MENU_SJR="送检人"; 
	  private static final String MENU_JD_TARGET="鉴定对象"; 
	  private static final String MENU_WTF_NAME=LangTransMeta.translate(SfEntrustor.NAME); 
	  private static final String MENU_WTF_ADDRESS="委托方"+LangTransMeta.translate(SfEntrustor.ADDRESS); 
	  private static final String MENU_WTF_ZIP="委托方"+LangTransMeta.translate(SfEntrustor.ZIP); 
	  private static final String MENU_WTF_LINK_MAN="委托方"+LangTransMeta.translate(SfEntrustor.LINK_MAN); 
	  private static final String MENU_WTF_LINK_TEL="委托方"+LangTransMeta.translate(SfEntrustor.LINK_TEL);
	  private static final String MENU_SJR_SJR=LangTransMeta.translate(SfEntrust.COL_SJR); 
	  private static final String MENU_SJR_SJR_TEL=LangTransMeta.translate(SfEntrust.COL_SJR_TEL);
	  private static final String MENU_SJR_SJR_ZJ_TYPE=LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_TYPE);
	  private static final String MENU_SJR_SJR_ZJ_CODE=LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_CODE); 
	  private static final String MENU_JD_TARGET_NAME=LangTransMeta.translate(SfJdTarget.COL_NAME); 
	  private static final String MENU_JD_TARGET_SEX=LangTransMeta.translate(SfJdTarget.COL_SEX); 
	  private static final String MENU_JD_TARGET_AGE="鉴定对象"+LangTransMeta.translate(SfJdTarget.COL_AGE); 
	  private static final String MENU_JD_TARGET_ID_NAME="鉴定对象"+LangTransMeta.translate(SfJdTarget.COL_ID_NAME); 
	  private static final String MENU_JD_TARGET_ID_CODE="鉴定对象"+LangTransMeta.translate(SfJdTarget.COL_ID_CODE); 
	  private static final String MENU_JD_TARGET_PHONE=LangTransMeta.translate(SfJdTarget.COL_PHONE); 
	  private static final String MENU_JD_TARGET_ADDRESS="鉴定对象"+LangTransMeta.translate(SfJdTarget.COL_ADDRESS); 
	  private static final String MENU_JD_TARGET_ZIP="鉴定对象"+LangTransMeta.translate(SfJdTarget.COL_ZIP);  
	  private static final String MENU_JD_BRIEF=LangTransMeta.translate(SfEntrust.COL_BRIEF); 
	  private static final String MENU_JD_NAME=LangTransMeta.translate(SfEntrust.COL_NAME); 
	  private static final String MENU_JD_CODE=LangTransMeta.translate(SfEntrust.COL_CODE); 
	  private static final String MENU_JD_MATERIALS="检材样本";  
	  private static final String MENU_JD_REQUIRE=LangTransMeta.translate(SfEntrust.COL_JD_REQUIRE); 
	  private static final String MENU_JD_MAJOR=LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME); 
	  private static final String MENU_JD_FZR=LangTransMeta.translate(SfEntrust.COL_JD_FZR);  
	  private static final String MENU_JD_FHR=LangTransMeta.translate(SfEntrust.COL_JD_FHR);  
	  private static final String MENU_JD_COMPANY=LangTransMeta.translate(SfEntrust.COL_JD_COMPANY); 
	  private static final String MENU_JD_ACCEPT_DATE=LangTransMeta.translate(SfEntrust.COL_ACCEPT_DATE); 
	  private static final String MENU_JD_ACCEPT_PERSON=LangTransMeta.translate(SfEntrust.COL_ACCEPTOR); 
	  private static final String MENU_WT_DATE=LangTransMeta.translate(SfEntrust.COL_WT_DATE); 
	  

	  private static final String MENU_REFRENCE="引用";
	  private static final String MENU_REFRENCE_ENTRUST="历史委托";
	  private static final String MENU_REFRENCE_ENTRUST_RECORD="记录文件"; 
	  private static final String MENU_FILL="填充模板";
	  private static final String MENU_FILL_CUR="填充当前模板";

	  private static final String MENU_INSERT_MODEL ="插入模板";
	  private static final String MENU_SELECT_MODEL ="选择";
//	  private static final String MENU_MODEL_BLANK="默认模板"; 
	  JMenu menuInsertModel=new JMenu(MENU_INSERT_MODEL);
	  
	  JMenu menuInsertContent=new JMenu(MENU_INSERT_CONTENT);

	  JMenu menuInsertWtf=new JMenu(MENU_WTF);

	  JMenu menuInsertSjr=new JMenu(MENU_SJR);

	  JMenu menuInsertJdTarget=new JMenu(MENU_JD_TARGET);
	  
	  JMenu menuRefrence=new JMenu(MENU_REFRENCE);

	  JMenu menuFill=new JMenu(MENU_FILL);
	  
	  JMenuBar menuBar=new JMenuBar();
	  
	  //模板菜单，菜单名称是key，模板文件是value
	  private HashMap<String, SfJdRecordFileModel> modelFileMenuMap=new HashMap<String, SfJdRecordFileModel>();
	  //记录文件页签表,key是文件模板的模板id
//	  private HashMap<BigDecimal, SfJdRecordFileModel> modelMaps=new HashMap<BigDecimal, SfJdRecordFileModel>();
	  //记录文件页签表,key是文件模板的模板id
//	  private HashMap<BigDecimal, JComponent> recordTabMaps=new HashMap<BigDecimal, JComponent>();
	  
	  //录文件页签表,key是文件模板的名称
	  private HashMap<String, MyTab> recordFileTabs=new HashMap<String, SfJdRecordEditPanel.MyTab>();

	  private JProgressBar openWordProgressBar = null;

	  private JDialog progressDialog;


	  public SfJdRecordEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfJdRecordListPanel listPanel) {
	    // TCJLODO Auto-generated constructor stub
	    super(SfJdRecordEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
	    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");

	    sfJdResultServiceDelegate = (ISfJdResultServiceDelegate) ServiceFactory.create( 		ISfJdResultServiceDelegate.class, "sfJdResultServiceDelegate");

	    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");

	    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
	      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

	    this.listCursor = listCursor;

	    this.listPanel = listPanel;

	    this.parent = parent;

	    this.colCount = 3;

	    init();

	    requestMeta.setCompoId(getCompoId());

	    WordFileUtil.setDir("sf");

//	    addSubPane();

	    refreshMainData();
	  }

	  protected boolean afterClsTab() {
		return false;
	}

	  public JMenuBar getMenuBar(){
		  return menuBar;
	  }
	protected boolean beforeClsTab() {
		 int num = JOptionPane.showConfirmDialog(this, "确定要删除这个文档吗", "删除确认", 0);
		    if (num == JOptionPane.YES_OPTION) {
		    	String title=recordFileTabPan.getTitleAt(recordFileTabPan.getSelectedIndex());
		    	recordFileTabs.remove(title);
		    	return true;
		    }
		return false;
	}

	protected void addSubPane() {
	    //下面一句是为了打开word后刷新窗口
	    parent.setSize(parent.getSize().width + 1, parent.getSize().height + 1);
	    defaultWordPane.addPropertyChangeListener(WordPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
	      public void propertyChange(PropertyChangeEvent evt) {
	        //打开文件完成之后的回调函数
	        boolean isSuccess = (Boolean) evt.getNewValue();
	        if (isSuccess) {
	          //下面一句是为了打开word后刷新窗口
	          parent.setSize(parent.getSize().width - 1, parent.getSize().height - 1);
	        }
	      }
	    });
	    recordFileTabPan.addTab("检验记录", defaultWordPane);
	  }

	  protected void addSubWordPane(SfJdResultFile rf) {
	    //下面一句是为了打开word后刷新窗口
//	    parent.setSize(new Dimension(parent.getSize().width - 200, parent.getSize().height - 200));
	    workPanel.setPreferredSize(new Dimension(workPanel.getSize().width - 10, workPanel.getSize().height - 10));
	    parent.pack();
	    WordPane wp=new WordPane();
	    wp.addPropertyChangeListener(WordPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
	      public void propertyChange(PropertyChangeEvent evt) {
	        //打开文件完成之后的回调函数
	        boolean isSuccess = (Boolean) evt.getNewValue();
	        if (isSuccess) {
	          //下面一句是为了打开word后刷新窗口
//	          parent.setSize(new Dimension(parent.getSize().width +200, parent.getSize().height +200));

	    	    workPanel.setPreferredSize(new Dimension(workPanel.getSize().width + 10, workPanel.getSize().height + 10));
	    	    parent.pack();
//	  	    	parent.validate(); 
		        progressDialog.setVisible(false);
		        recordFileTabPan.setSelectedIndex(recordFileTabPan.getTabCount()-1);
	        }
	      }
	    });
	    recordFileTabPan.addTab(rf.getName(), wp);
	    
	    MyTab mt=new MyTab();
	    mt.setRecordFile(rf);
	    mt.setTabCompo(wp);
	    mt.setTabTitle(rf.getName());
	    recordFileTabs.put(rf.getName(), mt);
//	    recordTabMaps.put(model.getModelId(), wp);

        progressDialog.setSize(new Dimension(600, 60));

        double x=(parent.getLocation().getX()+parent.getSize().getWidth())/2-300;
        logger.debug(x);
        int xi=Double.valueOf(x).intValue();

        double y=(parent.getLocation().getY()+parent.getSize().getHeight())/2-30;
        int yi=Double.valueOf(y).intValue();
       progressDialog.setLocation(xi, yi);

       

        String openWordMessage = rf.getModel().getName();

        openWordProgressBar.setString("正在加载模板文件:"+openWordMessage+",请稍等。"  );

        progressDialog.setVisible(true);
        
	    loadWordFile(wp,rf);
	  }
	  protected void addSubExcelPane(SfJdResultFile rf) {
		  
	  }

	  private void loadWordFile(WordPane wp, SfJdResultFile rf) {

		  String fn="";
		    if (rf.getFileId() != null && !rf.getFileId().equals("")) {

		      fn = WordFileUtil.loadMold(rf.getFileId());

		    } else {
		    	fn = WordFileUtil.loadDefaultMold();
		    }
		    if (openAndProtect) {
		    	wp.openAndProtect(fn, SfElementConstants.WORD_PASSWORD);
		    } else {
		    	wp.open(fn);
		    }
		    rf.setFileName(fn);
//		    modelMaps.put(model.getModelId(), model);
	}

	private void refreshMainData() {
	    // TCJLODO Auto-generated method stub

	    SfJdResult bill = (SfJdResult) listCursor.getCurrentObject();

	    if (bill != null) {
	      if (bill.getJdResultId() != null) {//列表页面双击进入
	        this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
	        this.openAndProtect = true;
	        bill = sfJdResultServiceDelegate.selectByPrimaryKey(bill.getJdResultId(), this.requestMeta);
	        listCursor.setCurrentObject(bill);
	        this.setEditingObject(bill);
	      } else if (bill.getEntrustId() != null) {//图形界面进来的新增，已经确定了entrust
	        this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
	        this.openAndProtect = false;
	        setDefaultValue(bill);
	        listCursor.getDataList().add(bill);
	        listCursor.setCurrentObject(bill);
	        this.setEditingObject(bill);
	      }
	    } else {//新增按钮进入
	      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
	      this.openAndProtect = false;
	      bill = new SfJdResult();
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
	  }

	  public synchronized void closeWordPanel(WordPane wp, boolean isSave) {
	    if (wp != null && wp.isDocOpened()) {
	      wp.close(isSave);
	    }
	  }

	  /**
	   * 
	  * 刷新word文本
	  * 判断当前tab页签上的文件是否属于记录文件列表，如果不是，则关闭对应页签
	  * 如果记录文件列表上的文件，不在tab页签上，则增加一个页签 
	   */
	  public void refreshFilePanel() {

	    SfJdResult bill = (SfJdResult) listCursor.getCurrentObject();
	    if(bill.getJdRecordFileLst()!=null && bill.getJdRecordFileLst().size()>0){
	    	for(int i=0;i<bill.getJdRecordFileLst().size();i++){
	    		SfJdResultFile rf=(SfJdResultFile) bill.getJdRecordFileLst().get(i);
	    		if(recordFileTabs.containsKey(rf.getName())){
	    			continue;
	    		}else{
	    			addTab(rf);
	    		}
	    	}
	    }

	  }

	  private void addTab(SfJdResultFile rf) {

		  if(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE_word.equalsIgnoreCase(rf.getModel().getFileType())){
			  addSubWordPane(rf);
		  }else if(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE_excel.equalsIgnoreCase(rf.getModel().getFileType())){
			  addSubExcelPane(rf);
		  } 
		    
	}

	private SfEntrust getEntrust(BigDecimal entrustId) {
	    // TCJLODO Auto-generated method stub
	    return sfEntrustServiceDelegate.selectByPrimaryKey(entrustId, requestMeta);
	  }

	  private void setDefaultValue(SfJdResult bill) {
	    // TCJLODO Auto-generated method stub

	    bill.setStatus(ZcSettingConstants.WF_STATUS_DRAFT);
	    bill.setNd(this.requestMeta.getSvNd());
	    bill.setInputDate(this.requestMeta.getSysDate());
	    bill.setInputor(requestMeta.getSvUserID());
	    bill.setCoCode(requestMeta.getSvCoCode());
	    bill.setResultType(SfJdResult.RESULT_TYPE_YJS);
	  }

	  protected void updateFieldEditorsEditable() {

	    for (AbstractFieldEditor editor : fieldEditors) {
	      if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT) || pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
	        if ("inputDate".equals(editor.getFieldName()) || "inputorName".equals(editor.getFieldName()) || "status".equals(editor.getFieldName())) {
	          editor.setEnabled(false);
	        } else {
	          editor.setEnabled(true);
	        }
	      } else {
	        editor.setEnabled(false);
	      }
	    }

	    SfJdResult qx = (SfJdResult) listCursor.getCurrentObject();
	    Long processInstId = qx.getProcessInstId();
	    if (processInstId != null && processInstId.longValue() > 0) {
	      // 工作流的单据
	      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(qx, requestMeta);
	      if ("cancel".equals(this.oldAppendMaterialNotice.getStatus())) {// 撤销单据设置字段为不可编辑
	        wfCanEditFieldMap = null;
	      }

	      for (AbstractFieldEditor editor : fieldEditors) {
	        // 工作流中定义可编辑的字段
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
	          if ("inputDate".equals(editor.getFieldName()) || "inputorName".equals(editor.getFieldName()) || "status".equals(editor.getFieldName())) {
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
	    //      setWFSubTableEditable(biTablePanel, isEdit);
	  }

	  private void protectWordPanel() {
	    defaultWordPane.protectDoc(SfElementConstants.WORD_PASSWORD);
	  }

	  protected void setButtonStatus() {
	    SfJdResult bill = (SfJdResult) listCursor.getCurrentObject();
	    setButtonStatus(bill, requestMeta, this.listCursor);
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

	    SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();

	    ZcUtil.setButtonEnable(this.btnStatusList, bill.getStatus(), this.pageStatus, getCompoId(), bill.getProcessInstId());

	  }

	  protected void setOldObject() {

	    oldAppendMaterialNotice = (SfJdResult) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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
	    // TCJLODO Auto-generated method stub
	    this.listCursor.setCurrentObject(null);
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
	        listCursor.setCurrentObject(oldAppendMaterialNotice);
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
	        listCursor.setCurrentObject(oldAppendMaterialNotice);
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
	    SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
	    try {
	    	//保存鉴定记录文件
	    	saveJdRecordFile();
	     /* //保存word文件
	      // 支持直接修改word内容。
	      defaultWordPane.save(this.fileName);
	      //每次都保存一文件，产生新的文件id
	      String fileId = WordFileUtil.uploadWordFile(fileName, bill.getFileId());
	      bill.setFileId(fileId);*/
	      requestMeta.setFuncId(saveButton.getFuncId());
	      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());
	      bill = sfJdResultServiceDelegate.saveFN(bill, this.requestMeta);
	      listCursor.setCurrentObject(bill);
	    } catch (Exception e) {
	      logger.error(e.getMessage(), e);
	      success = false;
	      errorInfo += e.getMessage();
	    }
	    if (success) {
	      JOptionPane.showMessageDialog(this, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
	      refreshListPanel();
	      refreshMainData();
	      updateBtnFields();
	      updateDataFlowDialog();
	    } else {
	      JOptionPane.showMessageDialog(this, "保存失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
	    }
	    return success;
	  }

	  private void saveJdRecordFile() {

		    SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
		    List lst=new ArrayList(); 
		    if(recordFileTabPan.getTabCount()<1){
		    	bill.setJdRecordFileLst(lst);
		    	return;
		    }
		    for(int j=0;j<recordFileTabPan.getTabCount();j++){
		    	String fileName=recordFileTabPan.getTitleAt(j);
		    	MyTab mb=recordFileTabs.get(fileName);
		    	if(mb.getTabCompo() instanceof WordPane){
		    		WordPane wp=(WordPane) mb.getTabCompo();
		    		wp.save();
		    		String fileId= WordFileUtil.uploadWordFile(mb.getRecordFile().getFileName(), mb.getRecordFile().getFileId());
		    		mb.getRecordFile().setFileId(fileId);
		    		lst.add(mb.getRecordFile());
		    	}/*else if(mb.getTabCompo() instanceof WordPane){
		    		
		    	}*/
		    }
		    bill.setJdRecordFileLst(lst);
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
	    SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
	    if (listPanel != null && listPanel.getParent() instanceof JClosableTabbedPane) {
	      return;
	    }
	    if (parent instanceof SfJdRecordDialog) {//新增的委托书，创建数据流界面
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
	    SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
	    StringBuilder errorInfo = new StringBuilder();
	    String mainValidateInfo = ZcUtil.validateBillElementNull(bill, mainNotNullList);
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
	    SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();

	    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
	    if (num == JOptionPane.YES_OPTION) {
	      boolean success = true;
	      String errorInfo = "";
	      try {
	        requestMeta.setFuncId(deleteButton.getFuncId());
	        sfJdResultServiceDelegate.deleteByPrimaryKeyFN(bill.getJdResultId(), requestMeta);
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
	    return !DigestUtil.digest(oldAppendMaterialNotice).equals(DigestUtil.digest(listCursor.getCurrentObject()));
	  }

	  private void doPrint() { 
	    Component compo=recordFileTabPan.getSelectedComponent();
	    if(compo instanceof WordPane){
	    	WordPane wp=(WordPane)compo;
	    	wp.print();
	    } 
	  }

	  private void doEdit() {

	    this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
	    this.openAndProtect = false;
	    defaultWordPane.unProtectDoc(SfElementConstants.WORD_PASSWORD);
	    updateFieldEditorsEditable();
	    setButtonStatus();
	  }

	  /* (non-Javadoc)
	   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createFieldEditors()
	   */
	  @Override
	  public List<AbstractFieldEditor> createFieldEditors() {
/*
	    List<AbstractFieldEditor> editorList = new ArrayList<AbstractFieldEditor>();

	    SfEntrustHandler entrustHandler = new SfEntrustHandler() {

	      @Override
	      public void excute(List selectedDatas) {
	        // TCJLODO Auto-generated method stub
	        for (Object obj : selectedDatas) {
	          SfJdResult currentBill = (SfJdResult) listCursor.getCurrentObject();
	          SfEntrust entrust = (SfEntrust) obj;
	          entrust = getEntrust(entrust.getEntrustId());
	          currentBill.setEntrustId(entrust.getEntrustId());
	          currentBill.setEntrustCode(entrust.getCode());
	          currentBill.setName(entrust.getName() + "补充材料通知书");
	          setEditingObject(currentBill);
	          selectEntrust(entrust);
	        }
	      }

	      public void afterClear() {
	        SfJdResult currentBill = (SfJdResult) listCursor.getCurrentObject();
	        currentBill.setEntrustId(null);
	        currentBill.setEntrustCode(null);
	        currentBill.setName(null);
	        setEditingObject(currentBill);
	      }
	    };
	    ElementConditionDto dto = new ElementConditionDto();
	    dto.setDattr1("SF_APPEND_MATERIAL_NOTICE");
	    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler,
	      entrustHandler.getColumNames(), LangTransMeta.translate(SfJdResult.COL_ENTRUST_CODE), "entrustCode");

	    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_NAME), "name");
	    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfJdResult.COL_STATUS), "status",    SfJdResult.SF_VS_JD_RESULT_STATUS);
	    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfJdResult.COL_REMARK), "remark", 100, 2, 5);
	    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_INPUTOR), "inputorName");
	    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfJdResult.COL_INPUT_DATE), "inputDate");

	    editorList.add(entrust);
	    editorList.add(name);
	    editorList.add(status);

	    editorList.add(remark);

	    editorList.add(inputor);
	    editorList.add(inputDate);*/

		    List<AbstractFieldEditor> editorList = new ArrayList<AbstractFieldEditor>();

		    SfEntrustHandler entrustHandler = new SfEntrustHandler() {

		      @Override
		      public void excute(List selectedDatas) {
		        // TCJLODO Auto-generated method stub
		        for (Object obj : selectedDatas) {
		          SfJdResult currentBill = (SfJdResult) listCursor.getCurrentObject();
		          SfEntrust entrust = (SfEntrust) obj;
		          entrust = sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
		          currentBill.setEntrustId(entrust.getEntrustId());
		          currentBill.setEntrustCode(entrust.getCode());
		          currentBill.setName(entrust.getName() + "鉴定记录");
		          currentBill.setJdr(entrust.getJdFzr());
		          currentBill.setBrief(entrust.getBrief());
		          currentBill.setEntrust(entrust);
		          setEditingObject(currentBill); 
		          break;
		        }
		      }

		      public void afterClear() {
		        SfJdResult currentBill = (SfJdResult) listCursor.getCurrentObject();
		        currentBill.setEntrustId(null);
		        currentBill.setEntrustCode(null);
		        currentBill.setName(null);
		        currentBill.setJdr(null);
		        currentBill.setBrief(null);
		        currentBill.setJdDate(null);
		        currentBill.setJdDate(null);
		        currentBill.setZcPersons(null);
		        currentBill.setJdProcess(null);
		        currentBill.setJdResult(null);
		        currentBill.setJdOpinion(null);
		        currentBill.setRemark(null);
		        currentBill.setEntrust(new SfEntrust());
		        setEditingObject(currentBill); 
		      }
		    };
		    ElementConditionDto dto = new ElementConditionDto();
		    dto.setDattr1("SF_JD_RESULT");
		    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler,
		      entrustHandler.getColumNames(), LangTransMeta.translate(SfJdResult.COL_ENTRUST_CODE), "entrustCode");
		    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_NAME), "name");
		    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfJdResult.COL_STATUS), "status", SfJdResult.SF_VS_JD_RESULT_STATUS);
		    AsValFieldEditor resultType = new AsValFieldEditor(LangTransMeta.translate(SfJdResult.COL_RESULT_TYPE), "resultType",SfJdResult.SF_VS_JD_RESULT_TYPE);
		    DateFieldEditor jdDate = new DateFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_DATE), "jdDate");
		    TextFieldEditor jdAddress = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_ADDRESS), "jdAddress");
		    TextAreaFieldEditor zcPersons = new TextAreaFieldEditor(LangTransMeta.translate(SfJdResult.COL_ZC_PERSONS), "zcPersons", -1, 1, 5);
		    AsValFieldEditor majorCode = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME), "entrust.majorCode", "SF_VS_MAJOR");

		    SfJdPersonSelectHandler jdrHandler = new SfJdPersonSelectHandler() {

		      @Override
		      public void excute(List selectedDatas) {
		        // TCJLODO Auto-generated method stub
		        for (Object obj : selectedDatas) {
		          SfJdResult currentBill = (SfJdResult) listCursor.getCurrentObject();
		          User jdr = (User) obj;
		          currentBill.setJdr(jdr.getUserId());
		          break;
		        }
		      }
		    };
		    dto = new ElementConditionDto();
		    ForeignEntityFieldEditor jdr = new ForeignEntityFieldEditor(jdrHandler.getSqlId(), dto, 20, jdrHandler, jdrHandler.getColumNames(),
		      LangTransMeta.translate(SfJdResult.COL_JDR), "jdrName");
		    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_INPUTOR), "inputorName");
		    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfJdResult.COL_INPUT_DATE), "inputDate");

		    TextFieldEditor jdTargetName = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_TARGET), "jdTargetName");
		    TextFieldEditor jdTargetName2 = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_TARGET), "jdTargetName");
		    IntFieldEditor jdTargetAge = new IntFieldEditor(LangTransMeta.translate(SfJdTarget.COL_AGE), "jdTarget.age");
		    AsValFieldEditor jdTargetSex = new AsValFieldEditor(LangTransMeta.translate(SfJdTarget.COL_SEX), "jdTarget.sex", SfElementConstants.VS_SEX);
		    TextFieldEditor jdTargetIdName = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ID_NAME), "jdTarget.idName");
		    TextFieldEditor jdTargetIdCode = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ID_CODE), "jdTarget.idCode");
		    TextFieldEditor jdTargetPhone = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_PHONE), "jdTarget.phone");
		    TextFieldEditor jdTargetAddress = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ADDRESS), "jdTarget.address");

		    TextFieldEditor jdMethod = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_METHOD), "jdMethod");
		    

		    editorList.add(entrust);
		    editorList.add(name);
		    editorList.add(status);

		    editorList.add(jdr);
		    editorList.add(jdDate);
		    editorList.add(jdAddress);

		    editorList.add(jdTargetName);
		    editorList.add(jdMethod);
		    editorList.add(majorCode);

		    editorList.add(zcPersons);

		    editorList.add(resultType);
		    editorList.add(inputor);
		    editorList.add(inputDate);
		    
	    return editorList;

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
	   /* JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	    sp.setOneTouchExpandable(true);//让分割线显示出箭头
	    sp.setContinuousLayout(true);//操作箭头，重绘图形
	    sp.setDividerLocation(0.28);
	    sp.setDividerSize(15);//設置分割線寬度的大小
	    sp.add(fieldEditorPanel, JSplitPane.TOP);
	    sp.add(subPanel, JSplitPane.BOTTOM);*/
	    
	    JPanel p=new JPanel();
	    p.setLayout(new BorderLayout());
	    p.add(fieldEditorPanel, BorderLayout.NORTH);
	    p.add(new JPanel(), BorderLayout.CENTER);
	    mainTab.add("基本信息", p);
	    mainTab.add("检验记录", subPanel);
	    workPanel.add(mainTab, BorderLayout.CENTER);
	    this.add(workPanel, BorderLayout.CENTER);
	    
	    //增加菜单
	    initMenu();
	    //初始化提示框
	    intProgressDialog();
	  }

	  private void initMenu() {
		  
		  JPopupMenu.setDefaultLightWeightPopupEnabled(false);
 
		  JMenuItem mItemWtfName=new JMenuItem(MENU_WTF_NAME);
		  mItemWtfName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_WTF_NAME);
			}
		}); 
		  JMenuItem mItemWtfAdress=new JMenuItem(MENU_WTF_ADDRESS);
		  mItemWtfAdress.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_WTF_ADDRESS);
			}
		});
		  JMenuItem mItemWtfZip=new JMenuItem(MENU_WTF_ZIP);
		  mItemWtfZip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_WTF_ZIP);
			}
		});
		  JMenuItem mItemWtfLinkMan=new JMenuItem(MENU_WTF_LINK_MAN);
		  mItemWtfLinkMan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_WTF_LINK_MAN);
			}
		});
		  JMenuItem mItemWtfLinkTel=new JMenuItem(MENU_WTF_LINK_TEL);
		  mItemWtfLinkTel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_WTF_LINK_TEL);
			}
		});
		  JMenuItem mItemSjrName=new JMenuItem(MENU_SJR_SJR);
		  mItemSjrName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_SJR_SJR);
			}
		});
		  JMenuItem mItemSjrTel=new JMenuItem(MENU_SJR_SJR_TEL);
		  mItemSjrTel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_SJR_SJR_TEL);
			}
		});
		  JMenuItem mItemSjrZjType=new JMenuItem(MENU_SJR_SJR_ZJ_TYPE);
		  mItemSjrZjType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_SJR_SJR_ZJ_TYPE);
			}
		});
		  JMenuItem mItemSjrZjCode=new JMenuItem(MENU_SJR_SJR_ZJ_CODE);
		  mItemSjrZjCode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_SJR_SJR_ZJ_CODE);
			}
		}); 
		  JMenuItem mItemJdTargetName=new JMenuItem(MENU_JD_TARGET_NAME);
		  mItemJdTargetName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_TARGET_NAME);
			}
		});
		  JMenuItem mItemJdTargetSex=new JMenuItem(MENU_JD_TARGET_SEX);
		  mItemJdTargetSex.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_TARGET_SEX);
			}
		});
		  JMenuItem mItemJdTargetAge=new JMenuItem(MENU_JD_TARGET_AGE);
		  mItemJdTargetAge.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_TARGET_AGE);
			}
		});
		  JMenuItem mItemJdTargetIdName=new JMenuItem(MENU_JD_TARGET_ID_NAME);
		  mItemJdTargetIdName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_TARGET_ID_NAME);
			}
		});
		  JMenuItem mItemJdTargetIdCode=new JMenuItem(MENU_JD_TARGET_ID_CODE);
		  mItemJdTargetIdCode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_TARGET_ID_CODE);
			}
		});
		  JMenuItem mItemJdTargetPhone=new JMenuItem(MENU_JD_TARGET_PHONE);
		  mItemJdTargetPhone.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_TARGET_PHONE);
			}
		});
		  JMenuItem mItemJdTargetAddress=new JMenuItem(MENU_JD_TARGET_ADDRESS);
		  mItemJdTargetAddress.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_TARGET_ADDRESS);
			}
		});
		  JMenuItem mItemJdTargetZip=new JMenuItem(MENU_JD_TARGET_ZIP);
		  mItemJdTargetZip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_TARGET_ZIP);
			}
		});
		  JMenuItem mItemJdBrief=new JMenuItem(MENU_JD_BRIEF);
		  mItemJdBrief.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_BRIEF);
			}
		});
		  JMenuItem mItemJdName=new JMenuItem(MENU_JD_NAME);
		  mItemJdName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_NAME);
			}
		}); 
		  JMenuItem mItemJdCode=new JMenuItem(MENU_JD_CODE);
		  mItemJdCode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_CODE);
			}
		}); 
		  JMenuItem mItemJdMaterial=new JMenuItem(MENU_JD_MATERIALS);
		  mItemJdMaterial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_MATERIALS);
			}
		}); 
		  JMenuItem mItemJdRequir=new JMenuItem(MENU_JD_REQUIRE);
		  mItemJdRequir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_REQUIRE);
			}
		});
		  JMenuItem mItemJdMajor=new JMenuItem(MENU_JD_MAJOR);
		  mItemJdMajor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_MAJOR);
			}
		});
		  JMenuItem mItemJdFzr=new JMenuItem(MENU_JD_FZR);
		  mItemJdFzr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_FZR);
			}
		});
		  JMenuItem mItemJdFhr=new JMenuItem(MENU_JD_FHR);
		  mItemJdFhr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_FHR);
			}
		});
		  JMenuItem mItemJdCompany=new JMenuItem(MENU_JD_COMPANY);
		  mItemJdCompany.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_COMPANY);
			}
		});
		  JMenuItem mItemJdAcceptDate=new JMenuItem(MENU_JD_ACCEPT_DATE);
		  mItemJdAcceptDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_ACCEPT_DATE);
			}
		});
		  JMenuItem mItemJdAcceptor=new JMenuItem(MENU_JD_ACCEPT_PERSON);
		  mItemJdAcceptor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_JD_ACCEPT_PERSON);
			}
		});
		  JMenuItem mItemJdWtDate=new JMenuItem(MENU_WT_DATE);
		  mItemJdWtDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(MENU_WT_DATE);
			}
		}); 
		  JMenuItem mItemReferenceEntrust=new JMenuItem(MENU_REFRENCE_ENTRUST);
		  mItemReferenceEntrust.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				referenceEntrust(MENU_REFRENCE_ENTRUST);
			}
		}); 
		  JMenuItem mItemReferenceEntrustFile=new JMenuItem(MENU_REFRENCE_ENTRUST_RECORD);
		  mItemReferenceEntrustFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				referenceFile(MENU_REFRENCE_ENTRUST_RECORD);
			}
		}); 
		  
		  JMenuItem mItemFillModel=new JMenuItem(MENU_FILL_CUR);
		  mItemFillModel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fillModel(MENU_FILL_CUR);
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
		  
		  initModelMenu();
		  menuBar.add(menuInsertModel);
		  
		  menuFill.add(mItemFillModel);
		  menuBar.add(menuFill);
		  
		  menuRefrence.add(mItemReferenceEntrust);
		  menuRefrence.add(mItemReferenceEntrustFile);
		  menuBar.add(menuRefrence);
		  
		  
		  menuBar.add(menuInsertContent);
		  parent.setJMenuBar(menuBar);
	}

	protected void fillModel(String menuFillCur) {
		//获取当前页签
		if(recordFileTabPan==null || recordFileTabPan.getTabCount()==0){
			return;
		}
		int selectedId=recordFileTabPan.getSelectedIndex();
		
		Component comp=recordFileTabPan.getSelectedComponent();
		if(comp instanceof WordPane){
			WordPane wp=(WordPane)comp;
			
			SfJdResult currentBill = (SfJdResult) listCursor.getCurrentObject();
			SfBookmarkUtil bku=new SfBookmarkUtil();
			List<SfBookmark> bks=bku.getEntrustBookValueLst(currentBill.getEntrust());
			bks.addAll(bku.getEntrustorBookValueLst(currentBill.getEntrust().getEntrustor()));
			bks.addAll(bku.getJdTargetBookValueLst(currentBill.getEntrust().getJdTarget()));
			bks.addAll(bku.getJdjgBookValueLst(requestMeta.getSvCoCode()));
			wp.replaceBookMarks(bks);
		}
		

		mainTab.setSelectedIndex(1);
		
	}

	private void initModelMenu() {
		List modelLst=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdRecordFileModelMapper.getModelFileMenuItem", new HashMap(), requestMeta);
		for(int i=0;i<modelLst.size();i++){
			Map m=(Map) modelLst.get(i);
			String name=(String) m.get("MENUITEMNAME"); 
			SfJdRecordFileModel md=new SfJdRecordFileModel();
			md.setFileId((String) m.get("FILE_ID"));
			md.setFileType((String) m.get("FILE_TYPE"));
			md.setFileName((String) m.get("FILE_NAME"));
			md.setName((String) m.get("NAME"));
			md.setModelId((BigDecimal)m.get("MODEL_ID"));
			modelFileMenuMap.put(name, md);

			  JMenuItem item=new JMenuItem(name);
			  item.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JMenuItem item=(JMenuItem)e.getSource();
					insertFileModel(item.getText());
				}
			}); 
			  menuInsertModel.add(item); 
		}
	}

	protected void insertFileModel(String menuText) {
		SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
		if(bill==null || bill.getEntrustCode()==null){
			JOptionPane.showMessageDialog(this, "请先选择委托.", "提示", JOptionPane.INFORMATION_MESSAGE);
		      return ;
		}
		
		 SfJdRecordFileModel model=modelFileMenuMap.get(menuText);
		 if(recordFileTabs.get(model.getName())!=null){
				JOptionPane.showMessageDialog(this, "模板已经添加.", "提示", JOptionPane.INFORMATION_MESSAGE);
			      return ;			 
		 }
		 if(model==null || model.getFileId()==null){

				JOptionPane.showMessageDialog(this, "没有对应的模板文件.", "提示", JOptionPane.INFORMATION_MESSAGE);
			      return ;
		 }
		 mainTab.setSelectedIndex(1);
		SfJdResultFile rf=new SfJdResultFile();
		rf.setModel(model); 
		rf.setFileId(model.getFileId()); 
		addTab(rf);
		
	}

	protected void referenceFile(String menuRefrenceEntrustRecord) {
	}

	protected void referenceEntrust(String menuRefrenceEntrust) {
	}

	protected void insertContent(String menuTitle) {
		SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
		if(bill==null || bill.getEntrustCode()==null){
			JOptionPane.showMessageDialog(this, "请先选择委托.", "提示", JOptionPane.INFORMATION_MESSAGE);
		      return ;
		}
		
		mainTab.setSelectedIndex(1);
		
		if(recordFileTabPan.getTabCount()<1){
			JOptionPane.showMessageDialog(this, "请先创建记录文件.", "提示", JOptionPane.INFORMATION_MESSAGE);
		      return ;
		}
		
		String title=recordFileTabPan.getTitleAt(recordFileTabPan.getSelectedIndex());
		
		MyTab tb=recordFileTabs.get(title);
		
		if(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE_word.equals(tb.getRecordFile().getModel().getFileType())){
			WordPane wp=(WordPane)tb.getTabCompo();
			String txt=getTxtFromBill(menuTitle);
			wp.insertTextToDoc(txt);
		}else if(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE_excel.equals(tb.getRecordFile().getModel().getFileType())){
				
		}
		
		mainTab.setSelectedIndex(1);
		
	}

	private String getTxtFromBill(String menuTitle) {

		SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
		
		StringBuffer sb=new StringBuffer();

		if(MENU_WTF_NAME.equals(menuTitle)){
			sb.append(bill.getEntrust().getEntrustor().getName());
		}else if(MENU_WTF_ADDRESS.equals(menuTitle)){
			sb.append(bill.getEntrust().getEntrustor().getAddress());
		}else if(MENU_WTF_ZIP.equals(menuTitle)){
			sb.append(bill.getEntrust().getEntrustor().getZip());
		}else if(MENU_WTF_LINK_MAN.equals(menuTitle)){
			sb.append(bill.getEntrust().getEntrustor().getLinkMan());
		}else if(MENU_WTF_LINK_TEL.equals(menuTitle)){
			sb.append(bill.getEntrust().getEntrustor().getLinkTel());
		}else if(MENU_SJR_SJR.equals(menuTitle)){
			sb.append(bill.getEntrust().getSjr());
		}else if(MENU_SJR_SJR_TEL.equals(menuTitle)){
			sb.append(bill.getEntrust().getSjrTel());
		}else if(MENU_SJR_SJR_ZJ_TYPE.equals(menuTitle)){
			sb.append(bill.getEntrust().getSjrZjType());
		}else if(MENU_SJR_SJR_ZJ_CODE.equals(menuTitle)){
			sb.append(bill.getEntrust().getSjrZjCode());
		}else if(MENU_JD_TARGET_NAME.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getName());
		}else if(MENU_JD_TARGET_SEX.equals(menuTitle)){
			sb.append(AsValDataCache.getName(SfElementConstants.VS_SEX, bill.getEntrust().getJdTarget().getSex()));
		}else if(MENU_JD_TARGET_AGE.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getAge());
		}else if(MENU_JD_TARGET_ID_NAME.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getIdName());
		}else if(MENU_JD_TARGET_ID_CODE.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getIdCode());
		}else if(MENU_JD_TARGET_PHONE.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getPhone());
		}else if(MENU_JD_TARGET_ADDRESS.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getAddress());
		}else if(MENU_JD_TARGET_ZIP.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getZip());
		}else if(MENU_JD_BRIEF.equals(menuTitle)){
			sb.append(bill.getEntrust().getBrief());
		}else if(MENU_JD_NAME.equals(menuTitle)){
			sb.append(bill.getEntrust().getName());
		}else if(MENU_JD_CODE.equals(menuTitle)){
			sb.append(bill.getEntrust().getCode());
		}else if(MENU_JD_MATERIALS.equals(menuTitle)){
			sb.append(SfBookmarkUtil.getJdclString(bill.getEntrust()));
		}else if(MENU_JD_REQUIRE.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdRequire());
		}else if(MENU_JD_MAJOR.equals(menuTitle)){
			sb.append(bill.getEntrust().getMajor().getMajorName());
		}else if(MENU_JD_FZR.equals(menuTitle)){
			sb.append(EmpMeta.getEmpName(bill.getEntrust().getJdFzr()));
		}else if(MENU_JD_FHR.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdFhrName());
		}else if(MENU_JD_COMPANY.equals(menuTitle)){
			sb.append(requestMeta.getSvCoName());
		}else if(MENU_JD_ACCEPT_DATE.equals(menuTitle)){
			sb.append(DateUtil.dateToChinaString(bill.getEntrust().getAcceptDate()));
		}else if(MENU_JD_ACCEPT_PERSON.equals(menuTitle)){
			sb.append(bill.getEntrust().getAcceptorName());
		}else if(MENU_WT_DATE.equals(menuTitle)){
			sb.append(DateUtil.dateToChinaString(bill.getEntrust().getWtDate()));
		}
		
		return sb.toString().equalsIgnoreCase("null")?"":sb.toString();
	}

	/* (non-Javadoc)
	   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
	   */
	  @Override
	  public JComponent createSubBillPanel() {

	  /*  defaultWordPane.setMinimumSize(new Dimension(10, 100));
	    this.tabPane.addChangeListener(new ChangeListener() {

	      public void stateChanged(ChangeEvent e) {
	        JTabbedPane tab = (JTabbedPane) e.getSource();
	        if(tab.getTabCount()==0){
	        	addSubPane();
	        }
//	        SfJdResult bulletin = (SfJdResult) self.listCursor.getCurrentObject();
//
//	        JPanel pan = (JPanel) tab.getSelectedComponent();

	         if ("panel_filenamezb".equals(pan.getName())) {
	           refreshZbFile(zbFileID);
	         }
	        
	        if (isShowPanel && pan!=null) {
	          if ("panel_filename1".equals(pan.getName()) && cnt1++ < 1) {
	            refreshWordPaneFile1(bulletin);
	          } else if ("panel_filename2".equals(pan.getName()) && cnt2++ < 1) {
	            refreshWordPaneFile2(bulletin);
	          }

	        }
	        
	      }
	    });*/

	    return this.recordFileTabPan;
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

	  protected void selectEntrust(SfEntrust entrust) {

	    if (entrust != null) {

	      SfJdResult bill = (SfJdResult) listCursor.getCurrentObject();
	      bill.setEntrustCode(entrust.getCode());
	      bill.setEntrustId(entrust.getEntrustId());
	      bill.setName(entrust.getCode() + "鉴定记录");
	      setEditingObject(bill);
	      /*Hashtable userData = new Hashtable();
	      userData.put("bill", bill);
	      userData.put(IWordHandler.FILE_NAME, bill.getEntrustCode());
	      userData.put("entrust", entrust);

	      IWordHandler handler = WordHandlerFactory.getInstance().getHandler(bill);
	      if (handler == null) {
	        JOptionPane.showMessageDialog(this.parent, "没有找到模版，请手工编制", "提示", JOptionPane.WARNING_MESSAGE);
	        return;
	      }
	      fileName = handler.createDocumnet(userData);
	      if (defaultWordPane != null) {
	        defaultWordPane.close(false);
	      }
	      defaultWordPane.open(this.fileName);*/
	    }
	  }

	  /**
	   * 送审
	   */
	  protected void doSend() {

	    boolean success = true;

	    SfJdResult afterSaveBill = null;

	    if (this.isDataChanged()) {
	      JOptionPane.showMessageDialog(this, "数据发生改变，请先保存.", "提示", JOptionPane.INFORMATION_MESSAGE);
	      return;
	    }

	    try {
	      requestMeta.setFuncId(this.sendButton.getFuncId());
	      SfJdResult qx = (SfJdResult) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
	      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
	      afterSaveBill = sfJdResultServiceDelegate.newCommitFN(qx, requestMeta);
	    } catch (Exception ex) {
	      logger.error(ex.getMessage(), ex);
	      success = false;
	      UIUtilities.showStaickTraceDialog(ex, this, "错误", ex.getMessage());
	    }

	    if (success) {
	      this.listCursor.setCurrentObject(afterSaveBill);
	      JOptionPane.showMessageDialog(this, "送审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
	      refreshListPanel();
	      refreshMainData();
	      updateBtnFields();
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
	    SfJdResult qx = (SfJdResult) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
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
	      qx = sfJdResultServiceDelegate.auditFN(qx, requestMeta);
	    } catch (Exception e) {
	      success = false;
	      logger.error(e.getMessage(), e);
	      errorInfo += e.getMessage();
	    }
	    if (success) {
	      JOptionPane.showMessageDialog(this, "审核成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
	      refreshListPanel();
	      refreshMainData();
	      updateBtnFields();
	      updateDataFlowDialog();
	    } else {
	      JOptionPane.showMessageDialog(this, "审核失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
	    }
	  }

	  /**
	   * 销审
	   */
	  protected void doUnAudit() {
	    SfJdResult qx = (SfJdResult) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
	    boolean success = true;
	    SfJdResult afterSaveBill = null;
	    String errorInfo = "";
	    int i = JOptionPane.showConfirmDialog(this, "是否确定消审？", "确认", JOptionPane.INFORMATION_MESSAGE);
	    if (i != 0) {
	      return;
	    }
	    try {
	      requestMeta.setFuncId(unAuditButton.getFuncId());
	      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
	      afterSaveBill = sfJdResultServiceDelegate.unAuditFN(qx, requestMeta);
	    } catch (Exception e) {
	      success = false;
	      logger.error(e.getMessage(), e);
	      errorInfo += e.getMessage();
	    }
	    if (success) {
	      this.listCursor.setCurrentObject(afterSaveBill);
	      JOptionPane.showMessageDialog(this, "销审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
	      refreshListPanel();
	      refreshMainData();
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
	    SfJdResult afterSaveBill = null;
	    String errorInfo = "";
	    try {
	      requestMeta.setFuncId(unTreadButton.getFuncId());
	      SfJdResult qx = (SfJdResult) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
	      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
	      qx.setComment(commentDialog.getComment());
	      afterSaveBill = sfJdResultServiceDelegate.untreadFN(qx, requestMeta);
	    } catch (Exception e) {
	      success = false;
	      logger.error(e.getMessage(), e);
	      errorInfo += e.getMessage();
	    }
	    if (success) {
	      this.listCursor.setCurrentObject(afterSaveBill);
	      JOptionPane.showMessageDialog(this, "退回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
	      refreshListPanel();
	      refreshMainData();
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
	    SfJdResult afterSaveBill = null;
	    String errorInfo = "";
	    try {
	      requestMeta.setFuncId(this.callbackButton.getFuncId());
	      SfJdResult qx = (SfJdResult) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
	      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
	      afterSaveBill = sfJdResultServiceDelegate.callbackFN(qx, requestMeta);
	    } catch (Exception e) {
	      success = false;
	      logger.error(e.getMessage(), e);
	      errorInfo += e.getMessage();
	    }

	    if (success) {
	      JOptionPane.showMessageDialog(this, "收回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
	      refreshListPanel();
	      refreshMainData();
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
	  
	  protected class MyTab{
		  JComponent tabCompo;
		  String tabTitle;
		  SfJdResultFile recordFile;
		public JComponent getTabCompo() {
			return tabCompo;
		}
		public void setTabCompo(JComponent tabCompo) {
			this.tabCompo = tabCompo;
		}
		public String getTabTitle() {
			return tabTitle;
		}
		public void setTabTitle(String tabTitle) {
			this.tabTitle = tabTitle;
		}
		public SfJdResultFile getRecordFile() {
			return recordFile;
		}
		public void setRecordFile(SfJdResultFile recordFile) {
			this.recordFile = recordFile;
		}
	  }
	  
	  private void intProgressDialog() {

		    initProgressBar();

		    progressDialog = new JDialog(null, "提示", Dialog.ModalityType.MODELESS  );

		    progressDialog.add(openWordProgressBar, BorderLayout.CENTER);

		    progressDialog.setUndecorated(true);

		    progressDialog.setAlwaysOnTop(true);

		  }

		  private void initProgressBar() {

		    if (openWordProgressBar == null) {

		      openWordProgressBar = new JProgressBar(0, 100);

		      openWordProgressBar.setStringPainted(true);

//		      openWordProgressBar.setBounds(new Rectangle(101, 305, 420, 30));

		    }

		  }
	}
