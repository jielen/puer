package com.ufgov.zc.client.common.converter.sf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.ColumnBeanPropertyPair;
import com.ufgov.zc.common.commonbiz.model.BaseElement;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdPersonMajor;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfMajorToTableModelConverter {

  public static TableModel convertToTableModel(List majorLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;

    Vector names = new Vector();

    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfMajor.COL_MAJOR_CODE));
    names.add(LangTransMeta.translate(SfMajor.COL_MAJOR_NAME));
//    names.add(LangTransMeta.translate(SfElementConstants.FIELD_TRANS_SF_MAJOR_PARENT_CODE));

    if (majorLst != null && majorLst.size() > 0) {

      for (int i = 0; i < majorLst.size(); i++) {

        Vector rowData = new Vector();

        SfMajor major = (SfMajor) majorLst.get(i);

        rowData.add(major.getMajorCode());
        rowData.add(major.getMajorName());
//        rowData.add(major.getParentCode());
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

    tableModel.setList(majorLst);

    return tableModel;
  }

  public static TableModel convertJdPersonsTableData(List itemList) {
    // TCJLODO Auto-generated method stub

    BeanTableModel<SfJdPerson> tm = new BeanTableModel<SfJdPerson>() {
      /**
       * 
       */
      private static final long serialVersionUID = -115294332374634087L;
      
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        SfJdPerson bean = dataBeanList.get(rowIndex);

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

    tm.setOidFieldName("name");
    tm.setDataBean(itemList, detailInfo);
    return tm;
  }
  private static List<ColumnBeanPropertyPair> detailInfo = new ArrayList<ColumnBeanPropertyPair>();

  static {

    detailInfo.add(new ColumnBeanPropertyPair(SfJdPerson.COL_NAME, "name", LangTransMeta.translate(SfJdPerson.COL_NAME)));
    detailInfo.add(new ColumnBeanPropertyPair(SfJdPerson.COL_SEX, "sex", LangTransMeta.translate(SfJdPerson.COL_SEX)));
    detailInfo.add(new ColumnBeanPropertyPair(SfJdPerson.COL_BIRTHDAY, "birthday", LangTransMeta.translate(SfJdPerson.COL_BIRTHDAY)));
    detailInfo.add(new ColumnBeanPropertyPair(SfJdPerson.COL_TECH_TITLE, "techTitle", LangTransMeta.translate(SfJdPerson.COL_TECH_TITLE)));
    detailInfo.add(new ColumnBeanPropertyPair(SfJdPerson.COL_CERTIFICATE_NO, "certificateNo", LangTransMeta.translate(SfJdPerson.COL_CERTIFICATE_NO)));
    detailInfo.add(new ColumnBeanPropertyPair(SfJdPerson.COL_STATUS, "status", LangTransMeta.translate(SfJdPerson.COL_STATUS)));
  }
  public static List<ColumnBeanPropertyPair> getDetailInfo(){
    return detailInfo;
  }
}
