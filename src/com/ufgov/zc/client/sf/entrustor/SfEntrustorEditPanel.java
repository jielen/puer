package com.ufgov.zc.client.sf.entrustor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.JFuncToolBar;
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
import com.ufgov.zc.client.component.button.SaveSendButton;
import com.ufgov.zc.client.component.button.SendButton;
import com.ufgov.zc.client.component.button.SendGkButton;
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityDialog;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.PasswordFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfEntrustorUser;
import com.ufgov.zc.common.sf.publish.ISfEntrustorServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfEntrustorEditPanel extends AbstractMainSubEditPanel {

  private static final Logger logger = Logger.getLogger(SfEntrustorEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_ENTRUSTOR";

  protected FuncButton saveButton = new SaveButton();

  protected FuncButton addButton = new AddButton();

  protected FuncButton editButton = new EditButton();

  private FuncButton traceButton = new TraceButton();

  protected FuncButton callbackButton = new CallbackButton();

  protected FuncButton deleteButton = new DeleteButton();

  private FuncButton previousButton = new PreviousButton();

  private FuncButton nextButton = new NextButton();

  private FuncButton exitButton = new ExitButton();

  private FuncButton saveAndSendButton = new SaveSendButton();

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

  protected ListCursor<SfEntrustor> listCursor;

  private SfEntrustor oldentrustor;

  public SfEntrustorListPanel listPanel;

  protected SfEntrustorEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd("SF_ENTRUSTOR");

  private ElementConditionDto eaccDto = new ElementConditionDto();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfEntrustorServiceDelegate sfEntrutstorServiceDelegate;

  private ForeignEntityDialog forenEntityDialog;

  public SfEntrustorEditPanel(SfEntrustorDialog parent, ListCursor listCursor, String tabStatus, SfEntrustorListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfEntrustorEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfEntrutstorServiceDelegate = (ISfEntrustorServiceDelegate) ServiceFactory.create(ISfEntrustorServiceDelegate.class,
      "sfEntrutstorServiceDelegate");

    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = parent;

    this.colCount = 2;

    init();

    requestMeta.setCompoId(getCompoId());

    refreshData();

    setButtonStatus();

    updateFieldEditorsEditable();
  }

  public SfEntrustorEditPanel(SfEntrustorDialog dialog, ListCursor listCursor, ForeignEntityDialog forenEntityDialog) {

    super(SfEntrustorEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));

    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfEntrutstorServiceDelegate = (ISfEntrustorServiceDelegate) ServiceFactory.create(ISfEntrustorServiceDelegate.class,
      "sfEntrutstorServiceDelegate");

    this.listCursor = listCursor;

    this.parent = dialog;

    this.forenEntityDialog = forenEntityDialog;

    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.colCount = 2;

    init();

    requestMeta.setCompoId(compoId);

    refreshData();

    setButtonStatus();

    updateFieldEditorsEditable();

  }

  private void refreshData() {
    // TCJLODO Auto-generated method stub

    SfEntrustor entrustor = (SfEntrustor) listCursor.getCurrentObject();

    if (entrustor != null && entrustor.getEntrustorId() != null) {//列表页面双击进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

      entrustor = sfEntrutstorServiceDelegate.selectByPrimaryKey(entrustor.getEntrustorId(), this.requestMeta);
      listCursor.setCurrentObject(entrustor);
      this.setEditingObject(entrustor);
    } else {//新增按钮进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;

      entrustor = new SfEntrustor();

      listCursor.getDataList().add(entrustor);

      listCursor.setCurrentObject(entrustor);

      this.setEditingObject(entrustor);

    }
    setOldObject();

    setButtonStatus();

    updateFieldEditorsEditable();

  }

  protected void updateFieldEditorsEditable() {

    for (AbstractFieldEditor editor : fieldEditors) {
      if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT) || pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
        editor.setEnabled(true);
      } else {
        editor.setEnabled(false);
      }
    }

  }

  protected void setButtonStatus() {
    SfEntrustor entrustor = (SfEntrustor) listCursor.getCurrentObject();
    setButtonStatus(entrustor, requestMeta, this.listCursor);
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

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

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

    SfEntrustor entrustor = (SfEntrustor) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, null, this.pageStatus, getCompoId(), entrustor.getProcessInstId());

  }

  protected void setOldObject() {

    oldentrustor = (SfEntrustor) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

    //    toolBar.add(sendButton);

    //    toolBar.add(saveAndSendButton);

    //    toolBar.add(suggestPassButton);

    //    toolBar.add(sendGkButton);

    //    toolBar.add(unAuditButton);

    //    toolBar.add(unTreadButton);

    //    toolBar.add(callbackButton);

    toolBar.add(deleteButton);

    //    toolBar.add(importButton);

    //    toolBar.add(printButton);

    //    toolBar.add(traceButton);

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

    unAuditButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        // 销审

        //        doUnAudit();

      }

    });

    printButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrintButton();

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

        listCursor.setCurrentObject(oldentrustor);

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

        listCursor.setCurrentObject(oldentrustor);

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

    SfEntrustor entrustor = (SfEntrustor) this.listCursor.getCurrentObject();

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      entrustor = sfEntrutstorServiceDelegate.saveFN(entrustor, this.requestMeta);

      listCursor.setCurrentObject(entrustor);

    } catch (Exception e) {

      logger.error(e.getMessage(), e);

      success = false;

      errorInfo += e.getMessage();

    }

    if (success) {

      JOptionPane.showMessageDialog(this, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

      setOldObject();

      if (this.forenEntityDialog == null) {
        this.listPanel.refreshCurrentTabData();
        refreshData();
      } else {
        refreshParentForeignDialog(entrustor);
      }

    } else {

      JOptionPane.showMessageDialog(this, "保存失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

    }

    return success;

  }

  void refreshParentForeignDialog(SfEntrustor entrustor) {

    this.forenEntityDialog.refresh(entrustor);
    this.parent.dispose();

  }

  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfEntrustor entrustor = (SfEntrustor) this.listCursor.getCurrentObject();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(entrustor, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString()).append("\n");
    }

    //检查是否由同名的数据
    if (haveSameName()) {
      errorInfo.append("\n").append("已存在同名的委托方！");
    }
    
    if(isLongin()){
	    if(!checkPasswd()){
	    	errorInfo.append("\n").append("密码不正确！");
	    }
	    if(entrustor.getUser().getUserId()==null || entrustor.getUser().getUserId().trim().length()==0){
	    	errorInfo.append("\n").append("登陆账号不能为空，请输入登陆账号！");
	    }else   if(haveSameUserId()){
	    	errorInfo.append("\n").append("登陆账号已经存在，请重新输入登陆账号！");
	    } 
	    
    }
    if (errorInfo.length() != 0) {
	      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.WARNING_MESSAGE);
	      return false;
	    }
    return true;
  }

  private boolean haveSameUserId() {

	  SfEntrustor entrustor = (SfEntrustor) this.listCursor.getCurrentObject();//
	  SfEntrustorUser wtfUser=(SfEntrustorUser) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.getWtfUser", entrustor.getEntrustorId(), requestMeta);
	  User existsUser=(User) zcEbBaseServiceDelegate.queryObject("User.getUserById", entrustor.getUser().getUserId(), requestMeta);
	  if(entrustor.getEntrustorId()==null){//新建委托方
		  if(existsUser!=null){
			  return true;
		  }
	  }else{//更新委托方信息
		  if(existsUser!=null){ 
			  if(wtfUser!=null ){ 
				  if(!wtfUser.getUserId().equals(existsUser.getUserId())){
					  return true;
				  } 
			  }	else{
				 return true;
			  }
		  } 
	  }
	return false;
}

private boolean isLongin() {
	  SfEntrustor entrustor = (SfEntrustor) this.listCursor.getCurrentObject();
	  if(SfElementConstants.VAL_Y.equals(entrustor.getIsLogin())){
		  return true;
	  }
	return false;
}

private boolean checkPasswd() {

	    SfEntrustor entrustor = (SfEntrustor) this.listCursor.getCurrentObject();
	    if(entrustor.getUser().getPassword()==null){
	    	if(entrustor.getUser().getPasswordConfrim()!=null)return false;	    	
	    }else{
	    	if(!entrustor.getUser().getPassword().trim().equals(entrustor.getUser().getPasswordConfrim())){
	    		return false;
	    	}
	    }
	return true;
}

private boolean haveSameName() {
    // TCJLODO Auto-generated method stub
    SfEntrustor entrustor = (SfEntrustor) this.listCursor.getCurrentObject();

    SfEntrustor s = sfEntrutstorServiceDelegate.selectByName(entrustor.getName(), requestMeta);

    if (s != null) {
      if (entrustor.getEntrustorId() == null || s.getEntrustorId().intValue() != entrustor.getEntrustorId().intValue()) {
        return true;
      }
    }
    return false;
  }

  protected void doDelete() {

    requestMeta.setFuncId(deleteButton.getFuncId());

    SfEntrustor entrustor = (SfEntrustor) this.listCursor.getCurrentObject();

    if (sfEntrutstorServiceDelegate.isUsing(entrustor.getEntrustorId(), requestMeta)) {
      JOptionPane.showMessageDialog(this, "已经被使用，不能删除 ！\n", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);

    if (num == JOptionPane.YES_OPTION) {

      boolean success = true;

      String errorInfo = "";

      try {

        requestMeta.setFuncId(deleteButton.getFuncId());

        sfEntrutstorServiceDelegate.deleteByPrimaryKeyFN(entrustor.getEntrustorId(), requestMeta);

      } catch (Exception e) {

        logger.error(e.getMessage(), e);

        success = false;

        errorInfo += e.getMessage();

      }

      if (success) {

        this.listCursor.removeCurrentObject();

        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        this.refreshData();
        if (this.forenEntityDialog == null) {
          this.listPanel.refreshCurrentTabData();
        } else {
          refreshParentForeignDialog(null);
        }
      } else {

        JOptionPane.showMessageDialog(this, "删除失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

      }

    }

  }

  public boolean isDataChanged() {

    if (!this.saveButton.isVisible() || !saveButton.isEnabled()) {
      return false;
    }

    return !DigestUtil.digest(oldentrustor).equals(DigestUtil.digest(listCursor.getCurrentObject()));

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

    //    AutoNumFieldEditor code = new AutoNumFieldEditor(LangTransMeta.translate(SfEntrustor.CODE), "code");
    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.NAME), "name");
    TextFieldEditor zip = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.ZIP), "zip");
    TextFieldEditor linkMan = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.LINK_MAN), "linkMan");
    TextFieldEditor linkTel = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.LINK_TEL), "linkTel");
    TextAreaFieldEditor address = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrustor.ADDRESS), "address", 100, 1, 3);
    AsValFieldEditor type = new AsValFieldEditor(LangTransMeta.translate(SfEntrustor.ENTRUSTOR_TYPE), "entrustorType", SfEntrustor.SF_VS_ENTRUSTOR_TYPE);
    TextFieldEditor userId = new TextFieldEditor("登陆账号", "user.userId");
    PasswordFieldEditor passWd = new PasswordFieldEditor("登陆密码", "user.password");
    PasswordFieldEditor passWdConfirm = new PasswordFieldEditor("确认密码", "user.passwordConfrim"); 
    AsValFieldEditor isLogin = new AsValFieldEditor("是否登陆系统", "isLogin", SfElementConstants.VS_Y_N);

    //    editorList.add(code);
    editorList.add(type);
    editorList.add(name);
    editorList.add(linkMan);
    editorList.add(linkTel);
    editorList.add(address);
    editorList.add(zip);
    editorList.add(new NewLineFieldEditor());
    editorList.add(isLogin);
    editorList.add(userId);
//    editorList.add(new NewLineFieldEditor());
    editorList.add(passWd);
    editorList.add(passWdConfirm); 

    return editorList;

  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {
    return null;
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

    this.parent.dispose();

  }

}
