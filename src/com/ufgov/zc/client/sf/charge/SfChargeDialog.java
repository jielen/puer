package com.ufgov.zc.client.sf.charge;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;

public class SfChargeDialog extends GkBaseDialog {

  /**
   * 
   */
  private static final long serialVersionUID = 3874532519369068370L;

  private SfChargeListPanel listPanel;

  private SfChargeEditPanel editPanel;

  private SfChargeDialog self = this;

  public SfChargeDialog(SfChargeListPanel listPanel, List beanList, int editingRow, String tabStatus) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfChargeEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

    this.setSize(UIConstants.DIALOG_3_LEVEL_WIDTH, UIConstants.DIALOG_3_LEVEL_HEIGHT);

    this.moveToScreenCenter();

    this.pack();

    //editPanel.refreshData();s

    //    this.setMaxSizeWindow();

    this.setVisible(true);

  }

  @Override
  public void closeDialog() {

    this.editPanel.doExit();

  }

}
