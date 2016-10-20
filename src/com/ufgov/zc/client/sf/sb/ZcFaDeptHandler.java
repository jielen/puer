package com.ufgov.zc.client.sf.sb;

import java.util.List;

import javax.swing.table.TableModel;

import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

public class ZcFaDeptHandler  implements IForeignEntityHandler {
  private final String columNames[];
  private ZcFaCardEditPanel mainPanel;

  public ZcFaDeptHandler(String columNames[],ZcFaCardEditPanel mainPanel) {

    this.columNames = columNames;
    this.mainPanel=mainPanel;

  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler#excute(java.util.List)
   */
  @Override
  public void excute(List selectedDatas) {
    // TCJLODO Auto-generated method stub

  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler#isMultipleSelect()
   */
  @Override
  public boolean isMultipleSelect() {
    // TCJLODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler#createTableModel(java.util.List)
   */
  @Override
  public TableModel createTableModel(List showDatas) {
    // TCJLODO Auto-generated method stub
    return null;
  }
  public void afterClear() {}
  public Boolean beforeSelect(ElementConditionDto dto) {
    return false;
  }
}

