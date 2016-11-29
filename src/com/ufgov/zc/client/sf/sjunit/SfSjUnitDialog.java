package com.ufgov.zc.client.sf.sjunit;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityDialog;

public class SfSjUnitDialog extends GkBaseDialog {

  private SfSjUnitListPanel listPanel;
  private SfSjUnitEditPanel editPanel;
  private SfSjUnitDialog self=this;
  private ForeignEntityDialog forenEntityDialog;

  public SfSjUnitDialog(SfSjUnitListPanel listPanel, List beanList, int editingRow, String tabStatus) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfSjUnitEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate("SF_SJ_UNIT"));

    this.setSize(400,150);

    this.moveToScreenCenter();

    this.validate();

    //editPanel.refreshData();s

//    this.setMaxSizeWindow();

    this.setVisible(true);

    this.dispose();
  }


  public SfSjUnitDialog(List beanList, int editingRow, ForeignEntityDialog openDialog) {
    super(openDialog, Dialog.ModalityType.APPLICATION_MODAL);
    this.forenEntityDialog = openDialog;
    editPanel = new SfSjUnitEditPanel(this.self, new ListCursor(beanList, editingRow), this.forenEntityDialog);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate("SF_SJ_UNIT"));

    this.setSize(400,150);

    this.moveToScreenCenter();

    this.validate();

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
