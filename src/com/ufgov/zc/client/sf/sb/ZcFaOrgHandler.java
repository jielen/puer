package com.ufgov.zc.client.sf.sb;

import java.util.HashMap;
import java.util.List;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.common.sf.model.ZcFaCard;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

public class ZcFaOrgHandler implements IForeignEntityHandler {
  private final String columNames[];
  private ZcFaCardEditPanel mainPanel;

  public ZcFaOrgHandler(String columNames[],ZcFaCardEditPanel mainPanel) {

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
      HashMap map=(HashMap) selectedDatas.get(0);
      String orgCode=map.get("ORG_CODE")==null?null:(String)map.get("ORG_CODE");
      String orgName=map.get("ORG_NAME")==null?null:(String)map.get("ORG_NAME");
      card.setUseOrgCode(orgCode);
      card.setUseOrgName(orgName);
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

    Object data[][] = new Object[showDatas.size()][columNames.length];
    for (int i = 0; i < showDatas.size(); i++) {
      HashMap rowData = (HashMap) showDatas.get(i);
      int col = 0;
      data[i][col++] = rowData.get("ORG_CODE");
      data[i][col++] = rowData.get("ORG_NAME");
    }

    MyTableModel model = new MyTableModel(data, columNames) {

      public static final long serialVersionUID = 1821460782676810898L;

      @Override
      public boolean isCellEditable(int row, int colum) {
        return false;
      }

      @Override
      public Class getColumnClass(int column) {
        if (column >= 0 && column < getColumnCount() && this.getRowCount() > 0) {
          for (int row = 0; row < this.getRowCount(); row++) {
            if (getValueAt(row, column) != null) {
              return getValueAt(row, column).getClass();
            }
          }
        }
        return Object.class;
      }
    };
    return model;
  }
  public void afterClear() {
    ZcFaCard card=mainPanel.getCurrentEditingObject();
    card.setUseOrgCode(null);
    card.setUseOrgName(null);
    mainPanel.setEditingObject(card);
  }

  public boolean beforeSelect(ElementConditionDto dto) {
    return true;
  }
}

