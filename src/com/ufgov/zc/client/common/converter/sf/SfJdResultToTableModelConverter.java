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
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdResultFile;
import com.ufgov.zc.common.system.Guid;
import com.ufgov.zc.common.system.constants.ZcElementConstants;
import com.ufgov.zc.common.system.model.AsFile;
import com.ufgov.zc.common.system.util.BeanUtil;
import com.ufgov.zc.common.zc.model.TreeNodeValueObject;

public class SfJdResultToTableModelConverter {

  public static TableModel convertMainLst(List mainLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;
    Vector names = new Vector();
    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfJdResult.COL_ENTRUST_CODE));
    names.add(LangTransMeta.translate(SfJdResult.COL_NAME));
    names.add(LangTransMeta.translate(SfJdResult.COL_JD_DATE));
    names.add(LangTransMeta.translate(SfJdResult.COL_JDR));
    names.add(LangTransMeta.translate(SfJdResult.COL_STATUS));
    if (mainLst != null && mainLst.size() > 0) {
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < mainLst.size(); i++) {
        Vector rowData = new Vector();
        SfJdResult outInfo = (SfJdResult) mainLst.get(i);
        rowData.add(outInfo.getEntrustCode());
        rowData.add(outInfo.getName());
        rowData.add(outInfo.getJdDate() == null ? null : df.format(outInfo.getJdDate()));
        rowData.add(outInfo.getJdrName());
        rowData.add(AsValDataCache.getName(SfJdResult.SF_VS_JD_RESULT_STATUS, outInfo.getStatus()));
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

  @SuppressWarnings("unchecked")
  public static TableModel convertAttacheFileToTableModel(List itemList) {

    BeanTableModel<SfJdResultFile> tm = new BeanTableModel<SfJdResultFile>() {

      private static final long serialVersionUID = 6888363838628062064L;

      @Override
      public boolean isCellEditable(int row, int column) { 

        return super.isCellEditable(row, column);

      }

      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        SfJdResultFile bean = dataBeanList.get(rowIndex);

        if (aValue instanceof BaseElement) {

          BeanUtil.set(columnBeanPropertyPairList.get(columnIndex).getBeanPropertyName(), ((BaseElement) aValue).getName(), bean);

          fireTableCellUpdated(rowIndex, columnIndex);

          putEditedData(dataBeanList.get(rowIndex));

        } else  if (SfJdResultFile.COL_FILE_NAME.equals(this.getColumnIdentifier(columnIndex))) {

          if (aValue == null) {

            this.getBean(rowIndex).setFileId(null);

            this.getBean(rowIndex).setFileName(null);

          } else {

            this.getBean(rowIndex).setFileName(((AsFile) aValue).getFileName());

            this.getBean(rowIndex).setFileId(((AsFile) aValue).getFileId());

          }

          fireTableCellUpdated(rowIndex, columnIndex);

          putEditedData(dataBeanList.get(rowIndex));

        } else {

          super.setValueAt(aValue, rowIndex, columnIndex);

        }

      }

    };

    tm.setOidFieldName("tempId");

    for (Object o : itemList) {

      ((SfJdResultFile) o).setTempId(Guid.genID());

    }

    tm.setDataBean(itemList, itemInfo);

    return tm;

  }
  private static List<ColumnBeanPropertyPair> itemInfo = new ArrayList<ColumnBeanPropertyPair>();

  static { 

    itemInfo.add(new ColumnBeanPropertyPair(SfJdResultFile.COL_FILE_NAME, "fileName", LangTransMeta.translate(SfJdResultFile.COL_FILE_NAME))); 
    itemInfo.add(new ColumnBeanPropertyPair(SfJdResultFile.COL_REMARK, "remark", LangTransMeta.translate(SfJdResultFile.COL_REMARK))); 

  }
public static List<ColumnBeanPropertyPair> getItemInfo() {

    return itemInfo;

  }
}
