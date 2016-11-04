/**
 * 
 */
package com.ufgov.zc.client.sf.dataflow;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.math.BigDecimal;

import javax.swing.JComponent;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ParentWindowAware;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.ui.AbstractEditListBill;
import com.ufgov.zc.common.sf.model.SfEntrust;

/**
 * @author Administrator
 *
 */
public class SfDataFlowDialog extends GkBaseDialog {

  /**
   * 
   */
  private static final long serialVersionUID = 5835774302957459356L;
  private SfDataFowPanel editPanel;
  private SfDataFlowDialog self=this;

  
  public SfDataFlowDialog(String compoId,SfEntrust entrust,AbstractEditListBill listPanel) {
    
    super(((ParentWindowAware)listPanel).getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    editPanel = new SfDataFowPanel(self,compoId,entrust,listPanel);

    setLayout(new BorderLayout());

    add(editPanel);

    StringBuffer sb=new StringBuffer();
    sb.append(entrust.getCode()).append(" ").append(entrust.getName()).append(" ").append(entrust.getEntrustor().getName());
    this.setTitle(sb.toString());

    this.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH, UIConstants.DIALOG_0_LEVEL_HEIGHT);

    this.moveToScreenCenter();

    this.pack();

    //editPanel.refreshData();s

    this.setMaxSizeWindow();

//    editPanel.setSelectedTab();
    /**
     * 界面显示后，再加载word控件，否则报peer not created错误
     */
    editPanel.refreshWordPanel();
    
    this.setVisible(true);
    
    this.dispose();

  }
  /* (non-Javadoc)

   * @see com.ufgov.gk.client.component.GkBaseDialog#closeDialog()

   */

  @Override
  public void closeDialog() {

    dispose();

  }
  public void refresh(BigDecimal entrustId){
    editPanel.refresh(entrustId);
  }
  public void removeTab(JComponent component,String compoId){
    editPanel.removeTab(component, compoId);
  }
}
