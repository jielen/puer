package com.ufgov.zc.client.sf.major;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;

public class SfMajorDialog extends GkBaseDialog {

  private SfMajorListPanel listPanel;
  private SfMajorEditPanel editPanel;
  private SfMajorDialog self=this;

  public SfMajorDialog(SfMajorListPanel listPanel, List beanList, int editingRow, String tabStatus) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfMajorEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

    this.setSize(800, 600);

    this.moveToScreenCenter();

      this.validate();
    //editPanel.refreshData();s

//    this.setMaxSizeWindow();

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
