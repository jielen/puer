package com.ufgov.zc.client.sf.sjgroup;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityDialog;
import com.ufgov.zc.client.sf.entrustor.SfEntrustorEditPanel;

public class SfSjGroupDialog extends GkBaseDialog {

  private SfSjGroupListPanel listPanel;
  private SfSjGroupEditPanel editPanel;
  private SfSjGroupDialog self=this;
  private ForeignEntityDialog forenEntityDialog;

  public SfSjGroupDialog(SfSjGroupListPanel listPanel, List beanList, int editingRow, String tabStatus) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfSjGroupEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

    this.setSize(UIConstants.DIALOG_3_LEVEL_WIDTH, UIConstants.DIALOG_3_LEVEL_HEIGHT);

    this.moveToScreenCenter();

    this.pack();

    //editPanel.refreshData();s

//    this.setMaxSizeWindow();

    this.setVisible(true);

    this.dispose();
  }


  public SfSjGroupDialog(List beanList, int editingRow, ForeignEntityDialog openDialog) {
    super(openDialog, Dialog.ModalityType.APPLICATION_MODAL);
    this.forenEntityDialog = openDialog;
    editPanel = new SfSjGroupEditPanel(this.self, new ListCursor(beanList, editingRow), this.forenEntityDialog);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

    this.setSize(UIConstants.DIALOG_3_LEVEL_WIDTH, UIConstants.DIALOG_3_LEVEL_HEIGHT);

    this.moveToScreenCenter();

    this.pack(); 

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
