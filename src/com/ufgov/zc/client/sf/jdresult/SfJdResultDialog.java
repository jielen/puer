package com.ufgov.zc.client.sf.jdresult;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;

public class SfJdResultDialog extends GkBaseDialog {

  /**
   * 
   */
  private static final long serialVersionUID = 3874532519369068370L;

  private SfJdResultListPanel listPanel;
  private SfJdResultEditPanel editPanel;
  private SfJdResultDialog self=this;


  public SfJdResultDialog(SfJdResultListPanel listPanel, List beanList, int editingRow, String tabStatus) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfJdResultEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

    /*this.setSize(UIConstants.DIALOG_3_LEVEL_WIDTH, UIConstants.DIALOG_3_LEVEL_HEIGHT);

    this.moveToScreenCenter();

    this.pack();*/

    int WINDOW_WIDTH=Toolkit.getDefaultToolkit().getScreenSize().width-20;
    int WINDOW_HEIGHT=Toolkit.getDefaultToolkit().getScreenSize().height-50;
    int WINDOW_LEFT = 5;
    int WINDOW_TOP = 5;

//    this.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH, UIConstants.DIALOG_0_LEVEL_HEIGHT);
//    this.moveToScreenCenter();


    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    this.setLocation(WINDOW_LEFT, WINDOW_TOP);
    this.pack();


    //editPanel.refreshData();s

//    this.setMaxSizeWindow();

    this.setVisible(true);

  }

  
  /* (non-Javadoc)

   * @see com.ufgov.gk.client.component.GkBaseDialog#closeDialog()

   */

  @Override
  public void closeDialog() {

    this.editPanel.doExit();

  }


}