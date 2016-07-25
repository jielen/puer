package com.ufgov.zc.client.sf.jdresult;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.util.List;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.component.GkBaseDialog;

public class SfJdRecordFileModelDialog  extends GkBaseDialog {

	  private SfJdRecordFileModelListPanel listPanel;
	  private SfJdRecordFileModelEditPanel editPanel;
	  private SfJdRecordFileModelDialog self=this;

	  public SfJdRecordFileModelDialog(SfJdRecordFileModelListPanel listPanel, List beanList, int editingRow, String tabStatus) {

	    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

	    this.listPanel = listPanel;

	    editPanel = new SfJdRecordFileModelEditPanel(this.self, new ListCursor(beanList, editingRow), tabStatus, listPanel);

	    setLayout(new BorderLayout());

	    add(editPanel);

	    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

	    this.setPreferredSize(new Dimension(UIConstants.DIALOG_2_LEVEL_WIDTH, UIConstants.DIALOG_3_LEVEL_HEIGHT));

	    this.moveToScreenCenter();

	    this.pack();

	    //editPanel.refreshData();s

//	    this.setMaxSizeWindow();

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