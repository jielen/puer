package com.ufgov.zc.client.sf.supplier;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.ArrayList;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityDialog;
import com.ufgov.zc.client.sf.entrustor.SfEntrustorEditPanel;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;

public class SfSjSupplierDialog  extends GkBaseDialog {

  /**
   * 
   */
  private static final long serialVersionUID = 8151619380263813339L;
  private SfSjSupplierListPanel listPanel;
  private SfSjSupplierEditPanel editPanel;
  private SfSjSupplierDialog self=this;

  private ForeignEntityDialog forenEntityDialog;

  public SfSjSupplierDialog(SfSjSupplierListPanel listPanel, List beanList, int editingRow, String tabStatus) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfSjSupplierEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

    this.setSize(UIConstants.DIALOG_2_LEVEL_WIDTH, UIConstants.DIALOG_3_LEVEL_HEIGHT);

    this.moveToScreenCenter();

//    this.pack();

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

  public SfSjSupplierDialog(List beanList, int editingRow, ForeignEntityDialog openDialog,int billType) {
    super(openDialog, Dialog.ModalityType.APPLICATION_MODAL);
    this.forenEntityDialog = openDialog; 
    editPanel = new SfSjSupplierEditPanel(this.self, new ListCursor(beanList, editingRow), this.forenEntityDialog,getCompo(billType));
    setLayout(new BorderLayout());
    add(editPanel);
    this.setTitle(LangTransMeta.translate("SF_ENTRUSTOR"));
    this.setSize(UIConstants.DIALOG_3_LEVEL_WIDTH, UIConstants.DIALOG_3_LEVEL_HEIGHT);
    this.moveToScreenCenter();
    this.setVisible(true);
  }

  private String getCompo(int billType) {
    if(ZcSettingConstants.FOREIGNENTITY_NEW_SF_SJ_PRODUCTOR ==billType){
      return "SF_SJ_PRODUCTOR";
    }
    if(ZcSettingConstants.FOREIGNENTITY_NEW_SF_SJ_SUPPLIER ==billType){
      return "SF_SJ_SUPPLIER";
    }
    return "SF_SJ_SUPPLIER";
  }
 

}
