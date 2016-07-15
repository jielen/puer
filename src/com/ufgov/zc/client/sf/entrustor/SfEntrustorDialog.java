package com.ufgov.zc.client.sf.entrustor;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityDialog;

public class SfEntrustorDialog extends GkBaseDialog {

  /**
   * 
   */
  private static final long serialVersionUID = -4607722811578562848L;
  private SfEntrustorListPanel listPanel;
  private SfEntrustorEditPanel editPanel;
  private SfEntrustorDialog self=this;

  private ForeignEntityDialog forenEntityDialog;

  public SfEntrustorDialog(SfEntrustorListPanel listPanel, List beanList, int editingRow, String tabStatus) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfEntrustorEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

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
  /**

   * 通过外部部件选择打开的增加界面

   * @param listPanel

   * @param beanList

   * @param editingRow

   * @param tabStatus

   * @param openDialog 外部部件的dialog

   */

  public SfEntrustorDialog(List beanList, int editingRow, ForeignEntityDialog openDialog) {
    super(openDialog, Dialog.ModalityType.APPLICATION_MODAL);
    this.forenEntityDialog = openDialog;
    editPanel = new SfEntrustorEditPanel(this.self, new ListCursor(beanList, editingRow), this.forenEntityDialog);
    setLayout(new BorderLayout());
    add(editPanel);
    this.setTitle(LangTransMeta.translate("SF_ENTRUSTOR"));
    this.setSize(UIConstants.DIALOG_3_LEVEL_WIDTH, UIConstants.DIALOG_3_LEVEL_HEIGHT);
    this.moveToScreenCenter();
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
