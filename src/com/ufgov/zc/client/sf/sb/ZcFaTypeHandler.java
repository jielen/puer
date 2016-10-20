/**
 * 
 */
package com.ufgov.zc.client.sf.sb;

import java.util.List;

import javax.swing.table.TableModel;

import com.ufgov.zc.common.sf.model.ZcFaCard;
import com.ufgov.zc.common.sf.model.ZcFaCardType;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

/**
 * @author Administrator
 *
 */
public class ZcFaTypeHandler implements IForeignEntityHandler {
  private final String columNames[];
  private ZcFaCardEditPanel mainPanel;

  public ZcFaTypeHandler(String columNames[],ZcFaCardEditPanel mainPanel) {

    this.columNames = columNames;
    this.mainPanel=mainPanel;

  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler#excute(java.util.List)
   */
  @Override
  public void excute(List selectedDatas) {
    // TCJLODO Auto-generated method stub
    ZcFaCard card=mainPanel.getCurrentEditingObject();
    if(selectedDatas!=null && selectedDatas.size()>0){
      ZcFaCardType type=(ZcFaCardType) selectedDatas.get(0);
      card.setFatypeCode(type.getFatypeCode());
      card.setFatypeName(type.getFatypeName());
      card.setFaUnit(type.getFatypeDw());
      card.setUseLife(type.getUseLife());
      card.setResiRat(type.getResiRat());
      mainPanel.setEditingObject(card);
    }

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
  public void afterClear() {
    ZcFaCard card=mainPanel.getCurrentEditingObject();
    card.setFatypeCode(null);
    card.setFatypeName(null);
    card.setFaUnit(null);
    card.setUseLife(null);
    card.setResiRat(null);
    mainPanel.setEditingObject(card);
  }

  public boolean beforeSelect(ElementConditionDto dto) {
    
    return true;
  }
}
