package com.ufgov.zc.client.sf.entrustmanage;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Window;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.sf.entrust.SfEntrustEditPanel;

public class SfEntrustManageDialog  extends GkBaseDialog {

  /**
   * 
   */
  private static final long serialVersionUID = 3874532519369068370L;

  private SfEntrustManageListPanel listPanel;
  private SfEntrustManageEditPanel editPanel;
  private SfEntrustManageDialog self=this;


  public SfEntrustManageDialog(Window owner, ListCursor lc){

    super(owner, Dialog.ModalityType.APPLICATION_MODAL);
 

    editPanel = new SfEntrustManageEditPanel(this.self, lc, null, null);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate("SF_ENTRUST_MANAGE"));

    this.setSize(900, 400);

    this.moveToScreenCenter();

    this.validate();

    //editPanel.refreshData();s

//    this.setMaxSizeWindow();

    this.setVisible(true);

  }

  public SfEntrustManageDialog(SfEntrustEditPanel entrustPanel, ListCursor lc){

    super(entrustPanel.getOwner(), Dialog.ModalityType.APPLICATION_MODAL);
 

    editPanel = new SfEntrustManageEditPanel(this.self, lc, null, null,entrustPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate("SF_ENTRUST_MANAGE"));

    this.setSize(900, 400);

    this.moveToScreenCenter();

    this.validate();

    //editPanel.refreshData();s

//    this.setMaxSizeWindow();

    this.setVisible(true);

  }
  public SfEntrustManageDialog(SfEntrustManageListPanel listPanel, List beanList, int editingRow, String tabStatus) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfEntrustManageEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

    this.setSize(900, 400);

    this.moveToScreenCenter();

    this.validate();

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