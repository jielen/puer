/**
 * 
 */
package com.ufgov.zc.client.sf.mobliemsg;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.component.GkBaseDialog;

/**
 * @author Administrator
 */
public class ZcMobileMsgDialog extends GkBaseDialog {

  private ZcMobileMsgListPanel listPanel;

  private ZcMobileMsgEditPanel editPanel;

  private ZcMobileMsgDialog self = this;

  public ZcMobileMsgDialog(ZcMobileMsgListPanel listPanel, List beanList, int editingRow, String tabStatus) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new ZcMobileMsgEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

    this.setSize(600, 1000);

    this.moveToScreenCenter();

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
