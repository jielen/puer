package com.ufgov.zc.client.sf.materialsTransfer;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;

public class SfMaterialsTransferDialog  extends GkBaseDialog {


  /**
   * 
   */
  private static final long serialVersionUID = 7384361521915374310L;
  
  private SfMaterialsTransferListPanel listPanel;
  private SfMaterialsTransferEditPanel editPanel;
  private SfMaterialsTransferDialog self=this;


  public SfMaterialsTransferDialog(SfMaterialsTransferListPanel listPanel, List beanList, int editingRow, String tabStatus) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfMaterialsTransferEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

    this.setSize(UIConstants.DIALOG_3_LEVEL_WIDTH, UIConstants.DIALOG_3_LEVEL_HEIGHT);

    this.moveToScreenCenter();

    this.pack();

    //editPanel.refreshData();s

    this.setMaxSizeWindow();

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