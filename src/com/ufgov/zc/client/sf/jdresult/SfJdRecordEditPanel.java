package com.ufgov.zc.client.sf.jdresult;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;
 



import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfJdResultToTableModelConverter;
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
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.celleditor.TextCellEditor;
import com.ufgov.zc.client.component.table.codecelleditor.FileCellEditor;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.IntFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfClientUtil;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowUtil;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.util.SfBookmarkUtil;
import com.ufgov.zc.client.sf.util.SfJdPersonSelectHandler;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ExcelFileUtil;
import com.ufgov.zc.client.zc.WordFileUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.client.zc.ztb.activex.ExcelPane;
import com.ufgov.zc.client.zc.ztb.activex.WordPane;
import com.ufgov.zc.common.sf.model.SfBookmark;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdRecordFileModel;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdResultFile;
import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfJdResultServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.system.util.Utils;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * 鉴定记录
 * 用于鉴定过程中记录鉴定步骤、情况和结论
 * 是鉴定文书的来源
 * 针对不同的鉴定专业，其鉴定记录的word模板是不一样的
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

	  private JTablePanel attacheFileTable = new JTablePanel();

	  //附件明细

	  JTabbedPane attacheFileTab = null;
	  
	  private JFuncToolBar subPackTableToolbar;
	  
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
	  
//	  private static final String MENU_MODEL_BLANK="默认模板"; 
	  JMenu menuInsertModel=new JMenu(SfClientUtil.MENU_INSERT_MODEL);
	  
	  JMenu menuInsertContent=new JMenu(SfClientUtil.MENU_INSERT_CONTENT);

	  JMenu menuInsertWtf=new JMenu(SfClientUtil.MENU_WTF);

	  JMenu menuInsertJdjg=new JMenu(SfClientUtil.MENU_JDJG);

	  JMenu menuInsertSjr=new JMenu(SfClientUtil.MENU_SJR);

	  JMenu menuInsertJdTarget=new JMenu(SfClientUtil.MENU_JD_TARGET);
	  
	  JMenu menuRefrence=new JMenu(SfClientUtil.MENU_REFRENCE);

	  JMenu menuFill=new JMenu(SfClientUtil.MENU_FILL);
	  
	  JPopupMenu menuPopup;
	  
	  JMenuBar menuBar=new JMenuBar();
	  
	  private int lookupIndex=0;
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
	  
	  private List<String> modelFileIdLst=new ArrayList<String>();

	   

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
//		  System.out.println("with="+parent.getSize().width);
//		  System.out.println("height="+parent.getSize().height);
//	    workPanel.setPreferredSize(new Dimension(workPanel.getSize().width - 10, workPanel.getSize().height - 10));
	    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH-10, UIConstants.DIALOG_0_LEVEL_HEIGHT-10);
	    parent.validate();

//		  System.out.println("with2="+parent.getSize().width);
//		  System.out.println("height2="+parent.getSize().height);
	    WordPane wp=new WordPane();
	    wp.addPropertyChangeListener(WordPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
	      public void propertyChange(PropertyChangeEvent evt) {
	        //打开文件完成之后的回调函数
	        boolean isSuccess = (Boolean) evt.getNewValue();
	        if (isSuccess) {
	          //下面一句是为了打开word后刷新窗口
//	          parent.setSize(new Dimension(parent.getSize().width +200, parent.getSize().height +200));

//	    	    workPanel.setPreferredSize(new Dimension(workPanel.getSize().width + 10, workPanel.getSize().height + 10));
	    	    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH, UIConstants.DIALOG_0_LEVEL_HEIGHT);
	    	    parent.validate();
	    	    parent.moveToScreenCenter();

//	  		  System.out.println("with3="+parent.getSize().width);
//	  		  System.out.println("height3="+parent.getSize().height);
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

        String openWordMessage =rf.getName()==null?rf.getModel().getName():rf.getName();
        openWordProgressBar.setString("正在加载文件:"+openWordMessage+",请稍等。"  );
        progressDialog.setVisible(true);
        
	    loadWordFile(wp,rf);
	  }
	  protected void addSubExcelPane(SfJdResultFile rf) {
		    //下面一句是为了打开word后刷新窗口
//		    parent.setSize(new Dimension(parent.getSize().width - 200, parent.getSize().height - 200));
		    workPanel.setPreferredSize(new Dimension(workPanel.getSize().width - 10, workPanel.getSize().height - 10));
		    parent.pack();
		    ExcelPane wp=new ExcelPane();
		    wp.addPropertyChangeListener(ExcelPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
		      public void propertyChange(PropertyChangeEvent evt) {
		        //打开文件完成之后的回调函数
		        boolean isSuccess = (Boolean) evt.getNewValue();
		        if (isSuccess) {
		          //下面一句是为了打开word后刷新窗口
//		          parent.setSize(new Dimension(parent.getSize().width +200, parent.getSize().height +200));

		    	    workPanel.setPreferredSize(new Dimension(workPanel.getSize().width + 10, workPanel.getSize().height + 10));
		    	    parent.pack();
//		  	    	parent.validate(); 
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
//		    recordTabMaps.put(model.getModelId(), wp);

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
	        
	        loadExcelFile(wp,rf);
	  }

	  private void loadExcelFile(ExcelPane wp, SfJdResultFile rf) {

		  String fn="";
		    if (rf.getFileId() != null && !rf.getFileId().equals("")) {

		      fn = ExcelFileUtil.loadMold(rf.getFileId());

		    } else {
		    	fn = ExcelFileUtil.loadDefaultMold();
		    }
		    if (openAndProtect) {
//		    	wp.openAndProtect(fn, SfElementConstants.WORD_PASSWORD);
		    	wp.open(fn);
		    } else {
		    	wp.open(fn);
		    }
		    rf.setFileName(fn);
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

	    refreshSubTableData();
	    
	    updateBtnFields();
	  }

	  private void refreshData() {

	    refreshMainData();

	    refreshSubData();

	    updateBtnFields();
	  }

	  private void updateBtnFields() {
	    setOldObject();
	    setButtonStatus();
	    updateFieldEditorsEditable();
	    if(ZcSettingConstants.PAGE_STATUS_BROWSE.equals(pageStatus)){
	    	recordFileTabPan.setCloseAble(false);
	    }else{
	    	recordFileTabPan.setCloseAble(true);
	    }
	  }

	  private void refreshSubData() {
		  
	    refreshFilePanel(); 
	  }

	  private void refreshSubTableData() {
		  SfJdResult bill = (SfJdResult) listCursor.getCurrentObject();
		    attacheFileTable.setTableModel(SfJdResultToTableModelConverter.convertAttacheFileToTableModel(bill.getAttacheFileLst())); 
		    ZcUtil.translateColName(this.attacheFileTable.getTable(), SfJdResultToTableModelConverter.getItemInfo()); 
		    setTableItemEditor(this.attacheFileTable.getTable());
	  }

	  public synchronized void closeWordPanel(WordPane wp, boolean isSave) {
	    if (wp != null && wp.isDocOpened()) {
	      wp.close(isSave);
	    }
	  }

	  private void setTableItemEditor(JTable table) {

	    table.setDefaultEditor(String.class, new TextCellEditor());
 
	    FileCellEditor fileEditor=new FileCellEditor("fileId",false,(BeanTableModel) table.getModel());
	    fileEditor.setDownloadFileEnable(true);
	    SwingUtil.setTableCellEditor(table, SfJdResultFile.COL_FILE_NAME,fileEditor ); 
	    
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
	    setParentSize();
	  }

	  private void setParentSize() {

		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  /*  Dimension frameSize=parent.getSize();
		    Dimension nw=new Dimension(screenSize);
		    boolean big=false;
		    if(frameSize.width+5>screenSize.width || frameSize.height+5>screenSize.height){
		    	parent.setSize(new Dimension(screenSize.width-10, screenSize.height-100));
		    	parent.pack();
		    	parent.moveToScreenCenter();
		    }*/
		    parent.setSize(new Dimension(screenSize.width-10, screenSize.height-100));
	    	parent.pack();
	    	parent.moveToScreenCenter();
		    		
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
	    bill.setInputDate(SfUtil.getSysDate());
	    bill.setInputor(requestMeta.getSvUserID());
	    bill.setCoCode(requestMeta.getSvCoCode());
	    bill.setResultType(SfJdReport.RESULT_TYPE_YJS);
	    bill.setJdAddress(requestMeta.getSvCoName());
	    bill.setJdDate(ZcUtil.getServerSysDate(requestMeta));
	    SfUtil su=new SfUtil();
	    SfJdjg jg=su.getJdjgInfo(requestMeta.getSvCoCode());
	    if(jg!=null){
	      bill.setJdAddress(jg.getName());
	    }
	  }

	  protected void updateFieldEditorsEditable() {

	    for (AbstractFieldEditor editor : fieldEditors) {
	      if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT) || pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
	        if ("inputDate".equals(editor.getFieldName()) || "inputorName".equals(editor.getFieldName())
	        		|| "entrust.majorCode".equals(editor.getFieldName()) || "status".equals(editor.getFieldName())) {
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
	          setWFSubTableEditable(attacheFileTable, isEdit);
	          setMenuEnable(isEdit);
	          
	  }

	  private void setMenuEnable(boolean isEdit) {
		  menuBar.setEnabled(isEdit);
		  for(int i=0;i<menuBar.getMenuCount();i++){
			  menuBar.getMenu(i).setEnabled(isEdit);
		  }
	}

	private void protectWordPanel() {
	    defaultWordPane.protectDoc(SfElementConstants.WORD_PASSWORD);
	  }

	  protected void setButtonStatus() {
	    SfJdResult bill = (SfJdResult) listCursor.getCurrentObject();
	    setButtonStatus(bill, requestMeta, this.listCursor);
	    //文书审批单终审后，锁定修改等操作,审批单没有终审时，不能打印
	    SfUtil su=new SfUtil();
	    su.lockBillWithDocAudit(toolBar.getComponents(),bill.getEntrust()); 
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
	        doExit(false);
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
	    /*if (!isDataChanged()) {
	      JOptionPane.showMessageDialog(this, "数据没有发生改变，不用保存.", "提示", JOptionPane.INFORMATION_MESSAGE);
	      return true;
	    }*/

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
//	      updateBtnFields();
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
		    		String modelId=mb.getRecordFile().getFileId();
		    		if(modelFileIdLst.contains(modelId)){//产生一个新的文件号，否则使用的是模板文件的id
		    			String fileId= WordFileUtil.insertAsFileContent(mb.getRecordFile().getFileName());
			    		mb.getRecordFile().setFileId(fileId);
			    		modelFileIdLst.remove(modelId);
		    		}else{
			    		WordFileUtil.uploadWordFile(mb.getRecordFile().getFileName(), mb.getRecordFile().getFileId());
		    		} 
		    		lst.add(mb.getRecordFile());
		    	}else if(mb.getTabCompo() instanceof ExcelPane){
		    		ExcelPane wp=(ExcelPane) mb.getTabCompo();
		    		wp.save();
		    		String modelId=mb.getRecordFile().getFileId();
		    		if(modelFileIdLst.contains(modelId)){//产生一个新的文件号，否则使用的是模板文件的id
		    			String fileId= WordFileUtil.insertAsFileContent(mb.getRecordFile().getFileName());
			    		mb.getRecordFile().setFileId(fileId);
			    		modelFileIdLst.remove(modelId);
		    		}else{
			    		WordFileUtil.uploadWordFile(mb.getRecordFile().getFileName(), mb.getRecordFile().getFileId());
		    		} 
		    		lst.add(mb.getRecordFile());		    		
		    	}
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
	        doExit(true);
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
	    }else if(compo instanceof ExcelPane){
	    	ExcelPane wp=(ExcelPane)compo;
	    	wp.print();
	    }
	  }

	  private void doEdit() {

	    this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
	    this.openAndProtect = false;
	    setFilepanelEditAble(true);
//	    defaultWordPane.unProtectDoc(SfElementConstants.WORD_PASSWORD);
	    updateFieldEditorsEditable();
	    setButtonStatus();
	    if(ZcSettingConstants.PAGE_STATUS_BROWSE.equals(pageStatus)){
	    	recordFileTabPan.setCloseAble(false);
	    }else{
	    	recordFileTabPan.setCloseAble(true);
	    }
	  }

	  private void setFilepanelEditAble(boolean isEdit) {

		    Iterator<String> keys=recordFileTabs.keySet().iterator();
		    while(keys.hasNext()){
		    	MyTab tb=recordFileTabs.get(keys.next());
		    	if(tb.getTabCompo()!=null){
		    		if(tb.getTabCompo() instanceof WordPane){
		    			WordPane wp=(WordPane)tb.getTabCompo();
		    			if(isEdit){
		    			wp.unProtectDoc(SfElementConstants.WORD_PASSWORD);
		    			}else{
		    				wp.protectDoc(SfElementConstants.WORD_PASSWORD);
		    			}
		    		}
		    	}
		    }
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
		    dto.setUserId(requestMeta.getSvUserID());
		    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler,
		      entrustHandler.getColumNames(), LangTransMeta.translate(SfJdResult.COL_ENTRUST_CODE), "entrustCode");
		    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_NAME), "name");
		    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfJdResult.COL_STATUS), "status", SfJdResult.SF_VS_JD_RESULT_STATUS);
		    AsValFieldEditor resultType = new AsValFieldEditor(LangTransMeta.translate(SfJdResult.COL_RESULT_TYPE), "resultType",SfJdReport.SF_VS_JD_RESULT_TYPE);
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
		          SfJdPerson jdr = (SfJdPerson) obj;
		          currentBill.setJdr(jdr.getAccount());
		          currentBill.setJdrName(jdr.getName());
		          setEditingObject(currentBill);
		          break;
		        }
		      }
		    };
		    dto = new ElementConditionDto();
		    ForeignEntityFieldEditor jdr = new ForeignEntityFieldEditor(jdrHandler.getSqlId(), dto, 20, jdrHandler, jdrHandler.getColumNames(),
		      LangTransMeta.translate(SfJdResult.COL_JDR), "jdrName");

//		    TextFieldEditor jdr = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_JDR), "jdr");
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

//		    editorList.add(resultType);
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
	    p.add(createSubBillPanel(), BorderLayout.CENTER);
      mainTab.add("基本信息", p);
      mainTab.add("检验记录", recordFileTabPan);
	    workPanel.add(mainTab, BorderLayout.CENTER);
	    this.add(workPanel, BorderLayout.CENTER);
	    
	    recordFileTabPan.addMouseListener(new MouseAdapter() {
	    	 public void mouseClicked(MouseEvent e) {
	    		 if(e.getButton()==e.BUTTON1){
//	    			  System.out.println("you jian");
	    			 super.mouseClicked(e);
	    			  return;
	    		  }else if(e.getButton()==e.BUTTON3){
	    			  doPopupMenu(e);
	    		  }
	    	 }
		});
	    //增加菜单
	    initMenu();
	    //初始化提示框
	    intProgressDialog();
	  }
	 

	protected void changeRecodFileName() {

		  System.out.println("change file name");
		  
		  if(recordFileTabPan.getSelectedIndex()<0){
			  return;
		  }
		  String txt=recordFileTabPan.getTitleAt(recordFileTabPan.getSelectedIndex());

		  System.out.println("change file name="+txt);
		  showChangeNameDialog(false,txt,null);
	}

	private void showChangeNameDialog(boolean isNew,String oldTitle,String menuTxt) {

		  
		  ChangeNameDialog entrustDialog = new ChangeNameDialog(parent,this.self,isNew,oldTitle,menuTxt);
//			entrustDialog.setSize(d)
		    entrustDialog.setMinimumSize(new Dimension(300, 150));
		    entrustDialog.pack();
		    entrustDialog.moveToScreenCenter();
		    entrustDialog.setVisible(true);
	}

	protected void doPopupMenu(MouseEvent e) {
//		  System.out.println("hello");
		  menuPopup=new JPopupMenu();		  
		  JMenuItem changeRecordFileName=new JMenuItem("重命名");
		  menuPopup.add(changeRecordFileName);
		  menuPopup.show(this, e.getX(), e.getY());
		  changeRecordFileName.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					changeRecodFileName();
				}
			}); 
	}

	private void initMenu() {
		  
		  JPopupMenu.setDefaultLightWeightPopupEnabled(false);
 
		  JMenuItem mItemWtfName=new JMenuItem(SfClientUtil.MENU_WTF_NAME);
		  mItemWtfName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_WTF_NAME);
			}
		}); 
		  JMenuItem mItemWtfAdress=new JMenuItem(SfClientUtil.MENU_WTF_ADDRESS);
		  mItemWtfAdress.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_WTF_ADDRESS);
			}
		});
		  JMenuItem mItemWtfZip=new JMenuItem(SfClientUtil.MENU_WTF_ZIP);
		  mItemWtfZip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_WTF_ZIP);
			}
		});
		  JMenuItem mItemWtfLinkMan=new JMenuItem(SfClientUtil.MENU_WTF_LINK_MAN);
		  mItemWtfLinkMan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_WTF_LINK_MAN);
			}
		});
		  JMenuItem mItemWtfLinkTel=new JMenuItem(SfClientUtil.MENU_WTF_LINK_TEL);
		  mItemWtfLinkTel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_WTF_LINK_TEL);
			}
		});
		  JMenuItem mItemSjrName=new JMenuItem(SfClientUtil.MENU_SJR_SJR);
		  mItemSjrName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_SJR_SJR);
			}
		});
		  JMenuItem mItemSjrTel=new JMenuItem(SfClientUtil.MENU_SJR_SJR_TEL);
		  mItemSjrTel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_SJR_SJR_TEL);
			}
		});
		  JMenuItem mItemSjrZjType=new JMenuItem(SfClientUtil.MENU_SJR_SJR_ZJ_TYPE);
		  mItemSjrZjType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_SJR_SJR_ZJ_TYPE);
			}
		});
		  JMenuItem mItemSjrZjCode=new JMenuItem(SfClientUtil.MENU_SJR_SJR_ZJ_CODE);
		  mItemSjrZjCode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_SJR_SJR_ZJ_CODE);
			}
		}); 
		  JMenuItem mItemJdTargetName=new JMenuItem(SfClientUtil.MENU_JD_TARGET_NAME);
		  mItemJdTargetName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_NAME);
			}
		});
		  JMenuItem mItemJdTargetSex=new JMenuItem(SfClientUtil.MENU_JD_TARGET_SEX);
		  mItemJdTargetSex.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_SEX);
			}
		});
		  JMenuItem mItemJdTargetAge=new JMenuItem(SfClientUtil.MENU_JD_TARGET_AGE);
		  mItemJdTargetAge.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_AGE);
			}
		});
		  JMenuItem mItemJdTargetIdName=new JMenuItem(SfClientUtil.MENU_JD_TARGET_ID_NAME);
		  mItemJdTargetIdName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_ID_NAME);
			}
		});
		  JMenuItem mItemJdTargetIdCode=new JMenuItem(SfClientUtil.MENU_JD_TARGET_ID_CODE);
		  mItemJdTargetIdCode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_ID_CODE);
			}
		});
		  JMenuItem mItemJdTargetPhone=new JMenuItem(SfClientUtil.MENU_JD_TARGET_PHONE);
		  mItemJdTargetPhone.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_PHONE);
			}
		});
		  JMenuItem mItemJdTargetAddress=new JMenuItem(SfClientUtil.MENU_JD_TARGET_ADDRESS);
		  mItemJdTargetAddress.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_ADDRESS);
			}
		});
		  JMenuItem mItemJdTargetZip=new JMenuItem(SfClientUtil.MENU_JD_TARGET_ZIP);
		  mItemJdTargetZip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_TARGET_ZIP);
			}
		});
		  JMenuItem mItemJdBrief=new JMenuItem(SfClientUtil.MENU_JD_BRIEF);
		  mItemJdBrief.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_BRIEF);
			}
		});
		  JMenuItem mItemJdName=new JMenuItem(SfClientUtil.MENU_JD_NAME);
		  mItemJdName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_NAME);
			}
		}); 
		  JMenuItem mItemJdCode=new JMenuItem(SfClientUtil.MENU_JD_CODE);
		  mItemJdCode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_CODE);
			}
		}); 
		  JMenuItem mItemJdMaterial=new JMenuItem(SfClientUtil.MENU_JD_MATERIALS);
		  mItemJdMaterial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_MATERIALS);
			}
		}); 
		  JMenuItem mItemJdRequir=new JMenuItem(SfClientUtil.MENU_JD_REQUIRE);
		  mItemJdRequir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_REQUIRE);
			}
		});
		  JMenuItem mItemJdMajor=new JMenuItem(SfClientUtil.MENU_JD_MAJOR);
		  mItemJdMajor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_MAJOR);
			}
		});
		  JMenuItem mItemJdFzr=new JMenuItem(SfClientUtil.MENU_JD_FZR);
		  mItemJdFzr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_FZR);
			}
		});
		  JMenuItem mItemJdFhr=new JMenuItem(SfClientUtil.MENU_JD_FHR);
		  mItemJdFhr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_FHR);
			}
		});
		  JMenuItem mItemJdCompany=new JMenuItem(SfClientUtil.MENU_JD_COMPANY);
		  mItemJdCompany.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_COMPANY);
			}
		});
		  JMenuItem mItemJdAcceptDate=new JMenuItem(SfClientUtil.MENU_JD_ACCEPT_DATE);
		  mItemJdAcceptDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_ACCEPT_DATE);
			}
		});
		  JMenuItem mItemJdAcceptor=new JMenuItem(SfClientUtil.MENU_JD_ACCEPT_PERSON);
		  mItemJdAcceptor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContent(SfClientUtil.MENU_JD_ACCEPT_PERSON);
			}
		});
		  JMenuItem mItemJdWtDate=new JMenuItem(SfClientUtil.MENU_WT_DATE);
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
		  
		  
		  JMenuItem mItemReferenceEntrust=new JMenuItem(SfClientUtil.MENU_REFRENCE_ENTRUST);
		  mItemReferenceEntrust.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				referenceEntrust(SfClientUtil.MENU_REFRENCE_ENTRUST);
			}
		}); 
		  JMenuItem mItemReferenceEntrustFile=new JMenuItem(SfClientUtil.MENU_REFRENCE_ENTRUST_RECORD);
		  mItemReferenceEntrustFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				referenceFile(SfClientUtil.MENU_REFRENCE_ENTRUST_RECORD);
			}
		}); 
		  
		  JMenuItem mItemFillModel=new JMenuItem(SfClientUtil.MENU_FILL_CUR);
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
		  
		  initModelMenu();
		  menuBar.add(menuInsertModel);
		  
		  menuFill.add(mItemFillModel);
		  menuBar.add(menuFill);
		  
		  menuRefrence.add(mItemReferenceEntrust);
//		  menuRefrence.add(mItemReferenceEntrustFile);
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
		SfJdResult currentBill = (SfJdResult) listCursor.getCurrentObject();
		SfBookmarkUtil bku=new SfBookmarkUtil();
		List<SfBookmark> bks=bku.getEntrustBookValueLst(currentBill.getEntrust());
		bks.addAll(bku.getEntrustorBookValueLst(currentBill.getEntrust().getEntrustor()));
		bks.addAll(bku.getJdTargetBookValueLst(currentBill.getEntrust().getJdTarget()));
		bks.addAll(bku.getJdjgBookValueLst(requestMeta.getSvCoCode())); 
		bks.addAll(bku.getJdRecordBookValueLst(currentBill)); 
		if(comp instanceof WordPane){
			WordPane wp=(WordPane)comp;	
			wp.replaceBookMarks(bks);
		}else if(comp instanceof ExcelPane){
			ExcelPane ep=(ExcelPane)comp;			
			ep.insertTextToNamedRangs(bks);
		}
		

		mainTab.setSelectedIndex(1);
		
	}

	private void initModelMenu() {
		ElementConditionDto dto=new ElementConditionDto();
		dto.setDattr1(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_DOC_TYPE_RECORD);
		List modelLst=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdRecordFileModelMapper.getModelFileMenuItem", dto, requestMeta);
		Hashtable<String, JMenu> menuGroups=new Hashtable<String, JMenu>();
		for(int i=0;i<modelLst.size();i++){
			Map m=(Map) modelLst.get(i);
			String name=(String) m.get("MENUITEMNAME"); 
			String groupName=(String)m.get("MAJOR_NAME");
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
			  if(groupName==null || groupName.length()==0){
			    menuInsertModel.add(item); 
			  }else{
			    if(menuGroups.containsKey(groupName)){
			      menuGroups.get(groupName).add(item);
			    }else{
			      JMenu child=new JMenu(groupName);
			      child.add(item);
			      menuGroups.put(groupName, child);
			    }
			  }
		}
		Enumeration childs=menuGroups.elements();
		while(childs.hasMoreElements()){
		  menuInsertModel.add((JMenuItem) childs.nextElement());
		}
		
	}

	protected void insertFileModel(String menuText) {
		SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
		if(bill==null || bill.getEntrustCode()==null){
			JOptionPane.showMessageDialog(this, "请先选择委托.", "提示", JOptionPane.INFORMATION_MESSAGE);
		      return ;
		}
		 SfJdRecordFileModel model=modelFileMenuMap.get(menuText);
		 if(model==null || model.getFileId()==null){

				JOptionPane.showMessageDialog(this, "没有对应的模板文件.", "提示", JOptionPane.INFORMATION_MESSAGE);
			      return ;
		 }
		
		 if(recordFileTabs.get(model.getName())!=null){
			 showChangeNameDialog(true,model.getName(),menuText);
				/*JOptionPane.showMessageDialog(this, "模板已经添加.", "提示", JOptionPane.INFORMATION_MESSAGE);
			      return ;	*/		 
		 }else{
			 mainTab.setSelectedIndex(1);
			SfJdResultFile rf=new SfJdResultFile();
			SfJdRecordFileModel model2 = (SfJdRecordFileModel) ObjectUtil.deepCopy(model);
			rf.setModel(model2); 
			rf.setFileId(model2.getFileId()); 
			modelFileIdLst.add(model2.getFileId());
			addTab(rf);
		 }
		
	}

	protected void referenceFile(String menuRefrenceEntrustRecord) {
	}
	//引用其他委托的的检验记录
	protected void referenceEntrust(String menuRefrenceEntrust) {

		SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
		if(bill.getEntrust()==null || bill.getEntrust().getEntrustId()==null){
			JOptionPane.showMessageDialog(this, "请先选择要记录的委托.", "提示", JOptionPane.INFORMATION_MESSAGE);
			mainTab.setSelectedIndex(0);
		      return ;			
		} 
		
		
		RefrenceEntrustDialog entrustDialog = new RefrenceEntrustDialog(parent,this, bill.getEntrust().getEntrustId());
//		entrustDialog.setSize(d)
	    entrustDialog.setMinimumSize(new Dimension(300, 150));
	    entrustDialog.pack();
	    entrustDialog.moveToScreenCenter();
	    entrustDialog.setVisible(true);
	}

	public boolean changeName(String newTitle,String oldTitle,String menuTxt, boolean isNew) {
		System.out.println(newTitle);
		if(isNew){
			for(int i=0;i<recordFileTabPan.getTabCount();i++){
				if(newTitle.equals(recordFileTabPan.getTitleAt(i))){
					return false;
				}
			}
			System.out.println(oldTitle);
			 SfJdRecordFileModel model=modelFileMenuMap.get(menuTxt);

				SfJdRecordFileModel model2 = (SfJdRecordFileModel) ObjectUtil.deepCopy(model);
				model2.setName(newTitle);
			 mainTab.setSelectedIndex(1);
			SfJdResultFile rf=new SfJdResultFile();
			rf.setModel(model2); 
			rf.setFileId(model2.getFileId()); 
			modelFileIdLst.add(model2.getFileId());
			addTab(rf);
			return true;
		}else{
			int oldIndex=recordFileTabPan.getSelectedIndex();
			int newIndex=-1;
			for(int i=0;i<recordFileTabPan.getTabCount();i++){
				
				if(newTitle.equals(recordFileTabPan.getTitleAt(i))){
					newIndex=i;
					break;
				}
			}
			if(oldIndex==newIndex){
				return true;
			}
			if(oldIndex!=newIndex && newIndex>=0){
				return false;
			}
			if(newIndex==-1){
				recordFileTabPan.setTitleAt(oldIndex, newTitle);
				MyTab m=recordFileTabs.get(oldTitle);
				m.getRecordFile().setName(newTitle);
				recordFileTabs.remove(oldTitle);
				recordFileTabs.put(newTitle, m);
				return true;
			}
		}
		return false;
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
//			String txt=getTxtFromBill(menuTitle); 
			String txt="";
			if(menuTitle.startsWith("鉴定机构")){
				SfJdjg jdjg=getJdjg();
				txt=SfClientUtil.getTxtFromJdjg(menuTitle, jdjg);
			}else{
				txt=SfClientUtil.getTxtFromBill(menuTitle, bill.getEntrust(), requestMeta);
			}
			wp.insertTextToDoc(txt);
		}else if(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE_excel.equals(tb.getRecordFile().getModel().getFileType())){
			ExcelPane wp=(ExcelPane)tb.getTabCompo();
//			String txt=getTxtFromBill(menuTitle); 
			String txt="";
			if(menuTitle.startsWith("鉴定机构")){
				SfJdjg jdjg=getJdjg();
				txt=SfClientUtil.getTxtFromJdjg(menuTitle, jdjg);
			}else{
				txt=SfClientUtil.getTxtFromBill(menuTitle, bill.getEntrust(), requestMeta);
			}
			wp.insertTextToCurCell(txt);	
		}
		
		mainTab.setSelectedIndex(1);
		
	}
/*
	private String getTxtFromBill(String menuTitle) {

		SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
		
		StringBuffer sb=new StringBuffer();

		if(SfClientUtil.MENU_WTF_NAME.equals(menuTitle)){
			sb.append(bill.getEntrust().getEntrustor().getName());
		}else if(SfClientUtil.MENU_WTF_ADDRESS.equals(menuTitle)){
			sb.append(bill.getEntrust().getEntrustor().getAddress());
		}else if(SfClientUtil.MENU_WTF_ZIP.equals(menuTitle)){
			sb.append(bill.getEntrust().getEntrustor().getZip());
		}else if(SfClientUtil.MENU_WTF_LINK_MAN.equals(menuTitle)){
			sb.append(bill.getEntrust().getEntrustor().getLinkMan());
		}else if(SfClientUtil.MENU_WTF_LINK_TEL.equals(menuTitle)){
			sb.append(bill.getEntrust().getEntrustor().getLinkTel());
		}else if(SfClientUtil.MENU_SJR_SJR.equals(menuTitle)){
			sb.append(bill.getEntrust().getSjr());
		}else if(SfClientUtil.MENU_SJR_SJR_TEL.equals(menuTitle)){
			sb.append(bill.getEntrust().getSjrTel());
		}else if(SfClientUtil.MENU_SJR_SJR_ZJ_TYPE.equals(menuTitle)){
			sb.append(bill.getEntrust().getSjrZjType());
		}else if(SfClientUtil.MENU_SJR_SJR_ZJ_CODE.equals(menuTitle)){
			sb.append(bill.getEntrust().getSjrZjCode());
		}else if(SfClientUtil.MENU_JD_TARGET_NAME.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getName());
		}else if(SfClientUtil.MENU_JD_TARGET_SEX.equals(menuTitle)){
			sb.append(AsValDataCache.getName(SfElementConstants.VS_SEX, bill.getEntrust().getJdTarget().getSex()));
		}else if(SfClientUtil.MENU_JD_TARGET_AGE.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getAge());
		}else if(SfClientUtil.MENU_JD_TARGET_ID_NAME.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getIdName());
		}else if(SfClientUtil.MENU_JD_TARGET_ID_CODE.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getIdCode());
		}else if(SfClientUtil.MENU_JD_TARGET_PHONE.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getPhone());
		}else if(SfClientUtil.MENU_JD_TARGET_ADDRESS.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getAddress());
		}else if(SfClientUtil.MENU_JD_TARGET_ZIP.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdTarget().getZip());
		}else if(SfClientUtil.MENU_JD_BRIEF.equals(menuTitle)){
			sb.append(bill.getEntrust().getBrief());
		}else if(SfClientUtil.MENU_JD_NAME.equals(menuTitle)){
			sb.append(bill.getEntrust().getName());
		}else if(SfClientUtil.MENU_JD_CODE.equals(menuTitle)){
			sb.append(bill.getEntrust().getCode());
		}else if(SfClientUtil.MENU_JD_MATERIALS.equals(menuTitle)){
			sb.append(SfBookmarkUtil.getJdclString(bill.getEntrust()));
		}else if(SfClientUtil.MENU_JD_REQUIRE.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdRequire());
		}else if(SfClientUtil.MENU_JD_MAJOR.equals(menuTitle)){
			sb.append(bill.getEntrust().getMajor().getMajorName());
		}else if(SfClientUtil.MENU_JD_FZR.equals(menuTitle)){
			sb.append(EmpMeta.getEmpName(bill.getEntrust().getJdFzr()));
		}else if(SfClientUtil.MENU_JD_FHR.equals(menuTitle)){
			sb.append(bill.getEntrust().getJdFhrName());
		}else if(SfClientUtil.MENU_JD_COMPANY.equals(menuTitle)){
			sb.append(requestMeta.getSvCoName());
		}else if(SfClientUtil.MENU_JD_ACCEPT_DATE.equals(menuTitle)){
			sb.append(DateUtil.dateToChinaString(bill.getEntrust().getAcceptDate()));
		}else if(SfClientUtil.MENU_JD_ACCEPT_PERSON.equals(menuTitle)){
			sb.append(bill.getEntrust().getAcceptorName());
		}else if(SfClientUtil.MENU_WT_DATE.equals(menuTitle)){
			sb.append(DateUtil.dateToChinaString(bill.getEntrust().getWtDate()));
		}
		
		return sb.toString().equalsIgnoreCase("null")?"":sb.toString();
	}*/

	private SfJdjg getJdjg() {
		ElementConditionDto dto=new ElementConditionDto();
		dto.setCoCode(requestMeta.getSvCoCode());
		SfJdjg jdjg=(SfJdjg) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfJdjgMapper.selectMainDataLst", dto, requestMeta);
		return jdjg;
	}

	/* (non-Javadoc)
	   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
	   */
	  @Override
	  public JComponent createSubBillPanel() {		 

		    attacheFileTab = new JTabbedPane();

		    attacheFileTable.init();

		    attacheFileTable.setTablePreferencesKey(this.getClass().getName() + "_attacheFiletable");

		    attacheFileTable.getTable().setShowCheckedColumn(true);

		    attacheFileTable.getSearchBar().setVisible(false);

		    attacheFileTable.getTable().getTableRowHeader().setPreferredSize(new Dimension(50, 0));

		    this.subPackTableToolbar = new JFuncToolBar();

		    JButton addBtn1 = new JButton("添加");

		    JButton insertBtn1 = new JButton("插入");

		    JButton delBtn1 = new JButton("删除");

		    this.subPackTableToolbar.add(addBtn1);

		    this.subPackTableToolbar.add(insertBtn1);

		    this.subPackTableToolbar.add(delBtn1);

		    attacheFileTable.add(this.subPackTableToolbar, BorderLayout.SOUTH);

		    addBtn1.addActionListener(new ActionListener() {

		      public void actionPerformed(ActionEvent e) {
		    	  SfJdResult entrust = (SfJdResult) listCursor.getCurrentObject();
		    	  SfJdResultFile attachFile=new SfJdResultFile();  
		    	  attachFile.setJdResultId(entrust.getJdResultId()); 
		        addSub(attacheFileTable, attachFile);
		      }

		    });

		    insertBtn1.addActionListener(new ActionListener() {

		      public void actionPerformed(ActionEvent e) {

		    	  SfJdResult entrust = (SfJdResult) listCursor.getCurrentObject();
		    	  SfJdResultFile attachFile=new SfJdResultFile();  
		    	  attachFile.setJdResultId(entrust.getJdResultId()); 
		        insertSub(attacheFileTable, attachFile);

		      }

		    });

		    delBtn1.addActionListener(new ActionListener() {

		      public void actionPerformed(ActionEvent e) {

		        deleteSub(attacheFileTable); 

		      }

		    });

		    attacheFileTab.setMinimumSize(new Dimension(240, 300));
		    attacheFileTab.add("相关检验过程文档", attacheFileTable);
		    
		    return attacheFileTab; 
//		  return recordFileTabPan;
	  }

	  public void doExit(boolean isDelete) {
	    // TCJLODO Auto-generated method stub

	    if (!isDelete&&isDataChanged()) {

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
		  public JDialog getParentDialog() {
			    return parent;
			  }
		public void referenceEntrust(SfEntrust entrust) {
			if(entrust!=null){
//				System.out.println(entrust.getCode());
				entrust=sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
				ElementConditionDto dto=new ElementConditionDto();
				
				List lst=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdResultMapper.selectByEntrustId", entrust.getEntrustId(), requestMeta);
				if(lst!=null && lst.size()>0){
					SfJdResult bill=(SfJdResult) lst.get(0);
					bill=sfJdResultServiceDelegate.selectByPrimaryKey(bill.getJdResultId(), requestMeta); 

				    if(bill.getJdRecordFileLst()!=null && bill.getJdRecordFileLst().size()>0){

				    	JTabbedPane tp=new JTabbedPane(JTabbedPane.BOTTOM);
				    	++lookupIndex;
				    	mainTab.add("检验记录-"+lookupIndex,tp);
				    	mainTab.setSelectedIndex(mainTab.getTabCount()-1);
//					    SfJdResult inData = (SfJdResult) this.listCursor.getCurrentObject();
				    	for(int i=0;i<bill.getJdRecordFileLst().size();i++){
				    		SfJdResultFile rf=(SfJdResultFile) bill.getJdRecordFileLst().get(i);
				    		 if(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE_word.equalsIgnoreCase(rf.getModel().getFileType())){
				   			  	addSubWordPane(rf,tp);
					   		  }else if(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE_excel.equalsIgnoreCase(rf.getModel().getFileType())){
					   			  addSubExcelPane(rf,tp);
					   		  } 
				    		/*rf=fitFile(rf);
				    		addTab(rf); 
				    		rf.setJdResultId(inData.getJdResultId());
				    		rf.setSfJdResultFileId(null);
				    		inData.getJdRecordFileLst().add(rf);*/
				    	}
				    }
				}
			}
		}

		private void addSubExcelPane(SfJdResultFile rf, JTabbedPane tp) {
		    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH-10, UIConstants.DIALOG_0_LEVEL_HEIGHT-10);
		    parent.validate();
			    parent.pack();
			    ExcelPane wp=new ExcelPane();
			    wp.addPropertyChangeListener(ExcelPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
			      public void propertyChange(PropertyChangeEvent evt) {
			        //打开文件完成之后的回调函数
			        boolean isSuccess = (Boolean) evt.getNewValue();
			        if (isSuccess) {
			          //下面一句是为了打开word后刷新窗口
//			          parent.setSize(new Dimension(parent.getSize().width +200, parent.getSize().height +200));

			    	    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH, UIConstants.DIALOG_0_LEVEL_HEIGHT);
			    	    parent.validate();
			    	    parent.moveToScreenCenter();
				        progressDialog.setVisible(false);
				        recordFileTabPan.setSelectedIndex(recordFileTabPan.getTabCount()-1);
			        }
			      }
			    });
			    tp.addTab(rf.getName(), wp);
			    loadExcelFile(wp,rf);
		}

		private void addSubWordPane(SfJdResultFile rf, JTabbedPane tp) {

		    workPanel.setPreferredSize(new Dimension(workPanel.getSize().width - 10, workPanel.getSize().height - 10));
		    parent.pack();
		    WordPane wp=new WordPane();
		    wp.addPropertyChangeListener(WordPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
		      public void propertyChange(PropertyChangeEvent evt) {
		        //打开文件完成之后的回调函数
		        boolean isSuccess = (Boolean) evt.getNewValue();
		        if (isSuccess) {
		          //下面一句是为了打开word后刷新窗口 
		    	    workPanel.setPreferredSize(new Dimension(workPanel.getSize().width + 10, workPanel.getSize().height + 10));
		    	    parent.pack(); 
		        }
		      }
		    });
		    tp.addTab(rf.getName(), wp);
		    loadWordFile(wp,rf);
		}

		private SfJdResultFile fitFile(SfJdResultFile rf) {
			if(recordFileTabs.containsKey(rf.getName())){
    			rf.setName(rf.getName()+"-1");
				return fitFile(rf);
    		}else{
    			return rf;
    		}
			
		}
	}
