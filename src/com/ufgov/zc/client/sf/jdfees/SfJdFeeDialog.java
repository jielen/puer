/**
 * 
 */
package com.ufgov.zc.client.sf.jdfees;

import java.awt.BorderLayout;
import java.awt.Dialog;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.common.sf.model.SfCharge;

/**
 * @author Administrator
 *
 */
public class SfJdFeeDialog extends GkBaseDialog {

  /**
   * 
   */
  private static final long serialVersionUID = -4264310120006109713L;

  private SfJdFeeMainPanel listPanel;

  private SfJdFeeEditPanel editPanel;

  public SfJdFeeDialog(SfJdFeeMainPanel listPanel, SfCharge bean) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfJdFeeEditPanel(this, bean, listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

    this.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH, UIConstants.DIALOG_0_LEVEL_HEIGHT);

    this.moveToScreenCenter();

    this.pack();

    this.setVisible(true);

  }

  @Override
  public void closeDialog() {

    this.editPanel.doExit();

  }

}
