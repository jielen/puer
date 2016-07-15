package com.ufgov.zc.client.common.converter.sf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.ColumnBeanPropertyPair;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.commonbiz.model.BaseElement;
import com.ufgov.zc.common.sf.model.SfEvaluation;
import com.ufgov.zc.common.sf.model.SfEvaluationPerson;
import com.ufgov.zc.common.sf.model.SfEvaluationPerson;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcElementConstants;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfEvaluationToTableModelConverter {

  public static TableModel convertMainLst(List mainLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;
    Vector names = new Vector();
    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfEvaluation.COL_ENTRUST_CODE));
    names.add(LangTransMeta.translate(SfEvaluation.COL_NAME));
    names.add(LangTransMeta.translate(SfEvaluation.COL_IS_ACCEPT));
    names.add(LangTransMeta.translate(SfEvaluation.COL_EVALUATE_DATE));
    names.add(LangTransMeta.translate(SfEvaluation.COL_STATUS));
    if (mainLst != null && mainLst.size() > 0) {

      SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < mainLst.size(); i++) {
        Vector rowData = new Vector();
        SfEvaluation evaluation = (SfEvaluation) mainLst.get(i);
        rowData.add(evaluation.getEntrustCode());
        rowData.add(evaluation.getName());
        rowData.add(AsValDataCache.getName(SfElementConstants.VS_Y_N, evaluation.getIsAccept()));
        rowData.add(evaluation.getEvaluateDate()==null?null:df.format(evaluation.getEvaluateDate()));
        rowData.add(AsValDataCache.getName(SfEvaluation.SF_VS_EVALUATION_STATUS, evaluation.getStatus()));
        values.add(rowData);
      }
    }

    tableModel = new MyTableModel(values, names) {
      public Class getColumnClass(int column) {
        if ((column >= 0) && (column < getColumnCount()) && this.getRowCount() > 0) {
          for (int row = 0; row < this.getRowCount(); row++) {
            if (getValueAt(row, column) != null) {
              return getValueAt(row, column).getClass();
            }
          }
        }
        return Object.class;
      }

      public boolean isCellEditable(int row, int colum) {
        return false;
      }
    };
    tableModel.setList(mainLst);
    return tableModel;
  }

  public static TableModel convertPersonsTableData(List itemList) {
    // TCJLODO Auto-generated method stub

    BeanTableModel<SfEvaluationPerson> tm = new BeanTableModel<SfEvaluationPerson>() {
      /**
       * 
       */
      private static final long serialVersionUID = -115294332374634087L;
      
      @Override
      public boolean isCellEditable(int row, int column) {
        return super.isCellEditable(row, column);
      }
      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        SfEvaluationPerson bean = dataBeanList.get(rowIndex);

        String currentColName = this.getColumnIdentifier(columnIndex);

        if (aValue instanceof BaseElement) {

          BeanUtil.set(columnBeanPropertyPairList.get(columnIndex).getBeanPropertyName(), ((BaseElement) aValue).getName(), bean);

          fireTableCellUpdated(rowIndex, columnIndex);

          putEditedData(dataBeanList.get(rowIndex));

        } else {
          super.setValueAt(aValue, rowIndex, columnIndex);
        }
      }
    };

    tm.setOidFieldName("tempId");
    tm.setDataBean(itemList, personInfo);
    return tm;
  }
  private static List<ColumnBeanPropertyPair> personInfo = new ArrayList<ColumnBeanPropertyPair>();

  static {

    personInfo.add(new ColumnBeanPropertyPair(SfEvaluationPerson.COL_NAME, "name", LangTransMeta.translate(SfEvaluationPerson.COL_NAME)));
  }
  public static List<ColumnBeanPropertyPair> getPersonInfo(){
    return personInfo;
  }
}
