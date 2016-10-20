/**
 * 
 */
package com.ufgov.zc.client.sf.sb;

import java.awt.Dialog;
import java.awt.LayoutManager;

import swing2swt.layout.BorderLayout;

import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;

/**
 * @author Administrator
 *
 */
public class ZcFaCardDeprStyleDialog extends GkBaseDialog {

  /**
   * 
   */
  private static final long serialVersionUID = 1139950492663756084L;
  

  public ZcFaCardDeprStyleDialog(ZcFaCardListPanel listPanel){
    super(listPanel.getParentWindow(),"选择卡片样式",Dialog.ModalityType.APPLICATION_MODAL);
     
    ZcFaCardDeprStylePanel panel=new ZcFaCardDeprStylePanel(this,listPanel);
    add(panel);

    this.setSize(UIConstants.DIALOG_3_LEVEL_WIDTH, UIConstants.DIALOG_4_LEVEL_HEIGHT);

    this.moveToScreenCenter();

    this.pack();

    this.setMaxSizeWindow();

    this.setVisible(true);

  }
}
