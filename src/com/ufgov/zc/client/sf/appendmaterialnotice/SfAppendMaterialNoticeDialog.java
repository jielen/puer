/**
 * 
 */
package com.ufgov.zc.client.sf.appendmaterialnotice;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;

/**
 * @author Administrator
 *
 */
public class SfAppendMaterialNoticeDialog extends GkBaseDialog {

  /**
   * 
   */
  private static final long serialVersionUID = -8807278537267353608L;
  private SfAppendMaterialNoticeListPanel listPanel;
  private SfAppendMaterialNoticeEditPanel editPanel;
  private SfAppendMaterialNoticeDialog self=this;


  public SfAppendMaterialNoticeDialog(SfAppendMaterialNoticeListPanel listPanel, List beanList, int editingRow, String tabStatus) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfAppendMaterialNoticeEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));
    int WINDOW_WIDTH=Toolkit.getDefaultToolkit().getScreenSize().width-20;
    int WINDOW_HEIGHT=Toolkit.getDefaultToolkit().getScreenSize().height-50;
    int WINDOW_LEFT = 5;
    int WINDOW_TOP = 5;

    this.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH, UIConstants.DIALOG_0_LEVEL_HEIGHT);
    this.moveToScreenCenter();
    this.pack();


    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    this.setLocation(WINDOW_LEFT, WINDOW_TOP);

    //界面显示后，再加载word控件，否则报peer not created错误
    editPanel.refreshWordPanel();
    this.setVisible(true);
    this.dispose();


  }

  
  /* (non-Javadoc)

   * @see com.ufgov.gk.client.component.GkBaseDialog#closeDialog()

   */

  @Override
  public void closeDialog() {

    this.editPanel.doExit();

  }


}
