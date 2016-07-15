/**
 * 
 */
package com.ufgov.zc.client.sf.assistFile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.plaf.BlueLookAndFeel;
import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfAssistFileToTableModelConverter;
import com.ufgov.zc.client.component.JFuncToolBar;
import com.ufgov.zc.client.component.JTablePanel;
import com.ufgov.zc.client.component.button.EditButton;
import com.ufgov.zc.client.component.button.FuncButton;
import com.ufgov.zc.client.component.button.SaveButton;
import com.ufgov.zc.client.component.button.SubaddButton;
import com.ufgov.zc.client.component.button.SubdelButton;
import com.ufgov.zc.client.component.button.SubinsertButton;
import com.ufgov.zc.client.component.button.zc.CommonButton;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.celleditor.TextCellEditor;
import com.ufgov.zc.client.component.table.codecelleditor.FileCellEditor;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfAssistFile;
import com.ufgov.zc.common.sf.publish.ISfAssistFileServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcYearPlan;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class SfAssistFilePanel extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -1228677383801254847L;

  private static final Logger logger = Logger.getLogger(SfAssistFilePanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_ASSIST_FILE";

  private List<SfAssistFile> fileLst = new ArrayList<SfAssistFile>();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfAssistFileServiceDelegate sfAssistFileServiceDelegate;

  protected FuncButton saveButton = new SaveButton();

  protected FuncButton editButton = new EditButton();

  private CommonButton fjieZhuanBaseData = new CommonButton("fjieZhuanBaseData", "开始新年度", "", true);

  protected JTablePanel detailTablePanel;

  static {

    LangTransMeta.init("SF%");

  }

  public SfAssistFilePanel() {
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfAssistFileServiceDelegate = (ISfAssistFileServiceDelegate) ServiceFactory.create(ISfAssistFileServiceDelegate.class,
      "sfAssistFileServiceDelegate");

    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

    detailTablePanel = new JTablePanel("SfAssistFilePanel_detail", AsOptionMeta.getOptVal("SF_OPTION_SF_ASSIST_FILE_HELP_MSG"));

    init();

    requestMeta.setCompoId(getCompoId());

    refreshData();

    setButtonStatus();

    updateFieldEditorsEditable();

  }

  private void refreshData() {
    // TCJLODO Auto-generated method stub
    this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
    fileLst = sfAssistFileServiceDelegate.getMainDataLst(new ElementConditionDto(), requestMeta);
    refreshSubData();
    setButtonStatus();
  }

  private void refreshSubData() {
    detailTablePanel.setTableModel(SfAssistFileToTableModelConverter.convertFileLst(fileLst, false));
    ZcUtil.translateColName(detailTablePanel.getTable(), SfAssistFileToTableModelConverter.getDetailInfo());
    setTablePorperty();
  }

  private void setTablePorperty() {
    // TCJLODO Auto-generated method stub
    detailTablePanel.getTable().setDefaultEditor(String.class, new TextCellEditor());
    SwingUtil.setTableCellEditor(detailTablePanel.getTable(), SfAssistFile.COL_FILE_NAME, new FileCellEditor("fileId",
      (BeanTableModel) detailTablePanel.getTable().getModel()));
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

    toolBar.add(fjieZhuanBaseData);

    fjieZhuanBaseData.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doJieZhuanBaseData();

      }

    });

    editButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doEdit();

      }

    });

    saveButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        doSave();

      }

    });

  }

  protected void doJieZhuanBaseData() {
    // TCJLODO Auto-generated method stub
    int num = JOptionPane.showConfirmDialog(this, "确认要结束" + requestMeta.getSvNd() + "年，进入" + (requestMeta.getSvNd() + 1) + "年吗", "确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      ZcYearPlan p = new ZcYearPlan();
      p.setNd(requestMeta.getSvNd());
      zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfAssistFileMapper.jiChuZiLiaoJieZhuan", p, requestMeta);
      JOptionPane.showMessageDialog(this, "进入新年度成功！新录入的委托单将以" + (requestMeta.getSvNd() + 1) + "重新开始编号，", "提示", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private void doSave() {
    if (fileLst == null)
      return;
    for (int i = 0; i < fileLst.size(); i++) {
      SfAssistFile file = (SfAssistFile) fileLst.get(i);
      if (file.getFileName() == null || file.getFileName().trim().length() == 0) {
        JOptionPane.showMessageDialog(this, "文档名称不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
        return;
      }
    }

    boolean success = true;

    String errorInfo = "";

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      fileLst = sfAssistFileServiceDelegate.saveFN(fileLst, this.requestMeta);

    } catch (Exception e) {

      logger.error(e.getMessage(), e);

      success = false;

      errorInfo += e.getMessage();

    }

    if (success) {

      JOptionPane.showMessageDialog(this, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
      this.isEdit = false;
      refreshSubData();
      setButtonStatus();

    } else {

      JOptionPane.showMessageDialog(this, "保存失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

    }
  }

  private void doEdit() {

    this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
    this.isEdit = true;
    setButtonStatus();
  }

  protected void setButtonStatus() {

    editButton.setVisible(SfUtil.haveFunc(compoId, null, editButton.getFuncId(), requestMeta));
    saveButton.setVisible(SfUtil.haveFunc(compoId, null, saveButton.getFuncId(), requestMeta));

    if (this.pageStatus.equals(ZcSettingConstants.PAGE_STATUS_BROWSE)) {
      editButton.setEnabled(true);
      saveButton.setEnabled(false);
      setWFSubTableEditable(detailTablePanel, false);
    } else {
      editButton.setEnabled(false);
      saveButton.setEnabled(true);
      setWFSubTableEditable(detailTablePanel, true);
    }
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createFieldEditors()
   */
  @Override
  public List<AbstractFieldEditor> createFieldEditors() {
    // TCJLODO Auto-generated method stub
    return new ArrayList<AbstractFieldEditor>();
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {
    // TCJLODO Auto-generated method stub
    JTabbedPane itemTabPane = new JTabbedPane();

    detailTablePanel.init();

    detailTablePanel.setPanelId(this.getClass().getName() + "_detailTablePanel");

    detailTablePanel.getSearchBar().setVisible(true);

    detailTablePanel.setTablePreferencesKey(this.getClass().getName() + "__detailTable");

    detailTablePanel.getTable().setShowCheckedColumn(true);

    detailTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));

    itemTabPane.addTab("相关文档", detailTablePanel);

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
        SfAssistFile item = new SfAssistFile();
        setFileDefaultValue(item);
        int rowNum = addSub(detailTablePanel, item);
        detailTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfAssistFile item = new SfAssistFile();
        setFileDefaultValue(item);
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

  protected void setFileDefaultValue(SfAssistFile item) {
    // TCJLODO Auto-generated method stub
    item.setTempId("" + System.currentTimeMillis());
  }

  /**
   * @param args
   */
  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {

      public void run() {

        try {

          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

          UIManager.setLookAndFeel(new BlueLookAndFeel());

        } catch (Exception e) {

          e.printStackTrace();

        }
        SfAssistFilePanel bill = new SfAssistFilePanel();

        JFrame frame = new JFrame("frame");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);

        frame.setLocationRelativeTo(null);

        frame.getContentPane().add(bill);

        frame.setVisible(true);

      }

    });

  }

  public String getCompoId() {
    // TCJLODO Auto-generated method stub
    return compoId;
  }
}
