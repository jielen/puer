package com.ufgov.zc.client.common;

import java.awt.DefaultKeyboardFocusManager;
import java.awt.Dialog.ModalityType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.zc.client.component.GkCommentDialog;
import com.ufgov.zc.client.component.GkCommentUntreadDialog;
import com.ufgov.zc.client.component.button.FuncButton;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class ZcWorkFlowAdapter {

  private static final Logger logger = Logger.getLogger(ZcWorkFlowAdapter.class);

  public static IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,

  "zcEbBaseServiceDelegate");

  /*
   * 工作流相关
   */
  public static ZcBaseBill auditFN(ZcBaseBill bill, JComponent parentComponent, FuncButton button, RequestMeta requestMeta, String defaultMsg) {

    GkCommentDialog commentDialog = null;

    if (defaultMsg == null) {

      commentDialog = new GkCommentDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),

      ModalityType.APPLICATION_MODAL);

    } else {

      commentDialog = new GkCommentDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),

      ModalityType.APPLICATION_MODAL, defaultMsg);

    }

    if (commentDialog.cancel) {

      return null;

    }

    boolean success = true;

    String errorInfo = "";

    ZcBaseBill afterSaveBill = null;

    try {

      bill.setComment(commentDialog.getComment());

      bill.setAuditorId(WorkEnv.getInstance().getCurrUserId());

      afterSaveBill = zcEbBaseServiceDelegate.auditFN(bill, requestMeta);

    } catch (Exception e) {

      success = false;

      logger.error(e.getMessage(), e);

      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(parentComponent, "审核成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      return afterSaveBill;
    } else {
      JOptionPane.showMessageDialog(parentComponent, "审核失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
      return null;
    }
  }

  public static ZcBaseBill callbackFN(ZcBaseBill bill, JComponent parentComponent, FuncButton button, RequestMeta requestMeta, String defaultStr) {

    boolean success = true;

    ZcBaseBill afterBill = null;

    String errorInfo = "";

    try {
      if (button != null) {
        requestMeta.setFuncId(button.getFuncId());
      }
      bill.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterBill = zcEbBaseServiceDelegate.callbackFN(bill, requestMeta);

    } catch (Exception e) {

      success = false;

      logger.error(e.getMessage(), e);

      errorInfo += e.getMessage();

    }

    if (success) {
      JOptionPane.showMessageDialog(parentComponent, "收回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      return afterBill;

    } else {

      JOptionPane.showMessageDialog(parentComponent, "收回失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
      return null;

    }

  }

  public static ZcBaseBill newCommitFN(ZcBaseBill bill, JComponent parentComponent, FuncButton button, RequestMeta requestMeta, String defaultStr) {

    boolean success = true;

    ZcBaseBill afterSendBill = null;

    String errorInfo = "";

    GkCommentDialog commentDialog = new GkCommentDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),

    ModalityType.APPLICATION_MODAL);

    if (commentDialog.cancel) {

      return null;

    }

    try {
      if (button != null) {
        requestMeta.setFuncId(button.getFuncId());
      }
      bill.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      if (defaultStr == null || "".equals(defaultStr)) {
        bill.setComment("同意");
      } else {
        bill.setComment(defaultStr);
      }
      afterSendBill = zcEbBaseServiceDelegate.newCommitFN(bill, requestMeta);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);

      errorInfo += e.getMessage();

      success = false;

      UIUtilities.showStaickTraceDialog(e, parentComponent, "错误", e.getMessage());
      return null;
    }

    if (success) {

      JOptionPane.showMessageDialog(parentComponent, "送审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

    }

    return afterSendBill;

  }

  public static ZcBaseBill unAuditFN(ZcBaseBill bill, JComponent parentComponent, FuncButton button, RequestMeta requestMeta, String defaultStr) {
    boolean success = true;
    ZcBaseBill afterBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(button.getFuncId());
      bill.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterBill = zcEbBaseServiceDelegate.unAuditFN(bill, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(parentComponent, "销审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      return afterBill;
    } else {

      JOptionPane.showMessageDialog(parentComponent, "销审失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

    }

    return null;

  }

  public static ZcBaseBill untreadFN(ZcBaseBill bill, JComponent parentComponent, FuncButton button, RequestMeta requestMeta, String defaultStr) {

    boolean success = true;

    GkCommentUntreadDialog commentDialog = new GkCommentUntreadDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),

    ModalityType.APPLICATION_MODAL);

    if (commentDialog.cancel) {

      return null;

    }

    ZcBaseBill afterBill = null;

    String errorInfo = "";

    try {
      if (button != null) {
        requestMeta.setFuncId(button.getFuncId());
      }
      bill.setAuditorId(WorkEnv.getInstance().getCurrUserId());

      bill.setComment(commentDialog.getComment());

      afterBill = zcEbBaseServiceDelegate.untreadFN(bill, requestMeta);

    } catch (Exception e) {

      success = false;

      logger.error(e.getMessage(), e);

      errorInfo += e.getMessage();

    }

    if (success) {

      JOptionPane.showMessageDialog(parentComponent, "退回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      return afterBill;

    } else {

      JOptionPane.showMessageDialog(parentComponent, "退回失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

    }

    return null;

  }

  public static void auditFN(List<ZcBaseBill> billList, JComponent parentComponent, FuncButton button, RequestMeta requestMeta, String defaultMsg) {

    GkCommentDialog commentDialog = null;

    if (defaultMsg == null) {

      commentDialog = new GkCommentDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),

      ModalityType.APPLICATION_MODAL);

    } else {

      commentDialog = new GkCommentDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),

      ModalityType.APPLICATION_MODAL, defaultMsg);

    }

    if (commentDialog.cancel) {

      return;

    }

    boolean success = true;

    String errorInfo = "";

    ZcBaseBill afterSaveBill = null;

    for (ZcBaseBill bill : billList) {
      bill.setComment(commentDialog.getComment());
      bill.setAuditorId(WorkEnv.getInstance().getCurrUserId());
    }

    try {

      zcEbBaseServiceDelegate.auditFN(billList, requestMeta);

    } catch (Exception e) {

      success = false;

      logger.error(e.getMessage(), e);

      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(parentComponent, "审核成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(parentComponent, "审核失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void callbackFN(List<ZcBaseBill> billList, JComponent parentComponent, FuncButton button, RequestMeta requestMeta, String defaultStr) {

    boolean success = true;
    String errorInfo = "";

    if (button != null) {
      requestMeta.setFuncId(button.getFuncId());
    }
    for (ZcBaseBill bill : billList) {
      bill.setAuditorId(WorkEnv.getInstance().getCurrUserId());
    }

    try {
      zcEbBaseServiceDelegate.callbackFN(billList, requestMeta);

    } catch (Exception e) {

      success = false;

      logger.error(e.getMessage(), e);

      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(parentComponent, "收回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

    } else {
      JOptionPane.showMessageDialog(parentComponent, "收回失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void newCommitFN(List<ZcBaseBill> billList, JComponent parentComponent, FuncButton button, RequestMeta requestMeta, String defaultStr) {

    boolean success = true;

    String errorInfo = "";

    GkCommentDialog commentDialog = new GkCommentDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),

    ModalityType.APPLICATION_MODAL);

    if (commentDialog.cancel) {

      return;

    }
    if (button != null) {
      requestMeta.setFuncId(button.getFuncId());
    }
    for (ZcBaseBill bill : billList) {
      bill.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      if (defaultStr == null || "".equals(defaultStr)) {
        bill.setComment("同意");
      } else {
        bill.setComment(defaultStr);
      }
    }

    try {

      zcEbBaseServiceDelegate.newCommitFN(billList, requestMeta);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);

      errorInfo += e.getMessage();

      success = false;

      UIUtilities.showStaickTraceDialog(e, parentComponent, "错误", e.getMessage());
      return;
    }

    if (success) {

      JOptionPane.showMessageDialog(parentComponent, "送审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

    }

  }

  public static void unAuditFN(List<ZcBaseBill> billList, JComponent parentComponent, FuncButton button, RequestMeta requestMeta, String defaultStr) {
    boolean success = true;
    ZcBaseBill afterBill = null;
    String errorInfo = "";

    if (button != null) {
      requestMeta.setFuncId(button.getFuncId());
    }
    for (ZcBaseBill bill : billList) {
      bill.setAuditorId(WorkEnv.getInstance().getCurrUserId());
    }
    try {
      zcEbBaseServiceDelegate.unAuditFN(billList, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(parentComponent, "销审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(parentComponent, "销审失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }

  }

  public static void untreadFN(List<ZcBaseBill> billList, JComponent parentComponent, FuncButton button, RequestMeta requestMeta, String defaultStr) {

    boolean success = true;

    GkCommentUntreadDialog commentDialog = new GkCommentUntreadDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),

    ModalityType.APPLICATION_MODAL);

    if (commentDialog.cancel) {

      return;

    }

    if (button != null) {
      requestMeta.setFuncId(button.getFuncId());
    }
    for (ZcBaseBill bill : billList) {
      bill.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      bill.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      bill.setComment(commentDialog.getComment());
    }

    String errorInfo = "";

    try {

      zcEbBaseServiceDelegate.untreadFN(billList, requestMeta);

    } catch (Exception e) {

      success = false;

      logger.error(e.getMessage(), e);

      errorInfo += e.getMessage();

    }

    if (success) {

      JOptionPane.showMessageDialog(parentComponent, "退回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

    } else {

      JOptionPane.showMessageDialog(parentComponent, "退回失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

    }

  }

  public static boolean methodinvoke(String methodName, Object[] args, JComponent parentComponent, ZcBaseBill bill) {

    Method method = null;
    try {
      method = parentComponent.getClass().getMethod(methodName, null);
    } catch (SecurityException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      method.invoke(parentComponent, args);
    } catch (IllegalArgumentException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    }

    return true;

  }
}
