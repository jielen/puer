/**
 * 
 */
package com.ufgov.zc.client.sf.jdfees;

import java.awt.BorderLayout;
import java.awt.Dialog;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;

/**
 * @author Administrator
 *
 */
public class SfJdFeeBigWindowDialog extends GkBaseDialog {

  /**
   * 
   */
  private static final long serialVersionUID = -7512731355659115011L;

  private SfJdFeeMainPanel listPanel;

  private SfJdFeeMainPanel editPanel;

  public SfJdFeeBigWindowDialog(SfJdFeeMainPanel listPanel) {

    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    this.listPanel = listPanel;

    editPanel = new SfJdFeeMainPanel(true);

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

    this.dispose();

  }

}
