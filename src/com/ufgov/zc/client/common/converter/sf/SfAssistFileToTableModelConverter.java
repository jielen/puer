package com.ufgov.zc.client.common.converter.sf;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.ColumnBeanPropertyPair;
import com.ufgov.zc.common.commonbiz.model.BaseElement;
import com.ufgov.zc.common.sf.model.SfAssistFile;
import com.ufgov.zc.common.system.model.AsFile;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfAssistFileToTableModelConverter {

  public static TableModel convertFileLst(List fileLst,boolean isEdit) {
    // TCJLODO Auto-generated method stub

    BeanTableModel<SfAssistFile> tm = new BeanTableModel<SfAssistFile>() {
      /**
       * 
       */
      private static final long serialVersionUID = -115294332374634087L;
      
      @Override
      public boolean isCellEditable(int row, int column) {
        String columnId = this.getColumnIdentifier(column);
        if (SfAssistFile.COL_FILE_NAME.equals(columnId)) {
            return true;
          }
          return super.isCellEditable(row, column);
      }
      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        SfAssistFile bean = dataBeanList.get(rowIndex);

        String currentColName = this.getColumnIdentifier(columnIndex);

        if (aValue instanceof BaseElement) {

          BeanUtil.set(columnBeanPropertyPairList.get(columnIndex).getBeanPropertyName(), ((BaseElement) aValue).getName(), bean);

          fireTableCellUpdated(rowIndex, columnIndex);

          putEditedData(dataBeanList.get(rowIndex));

        }if (SfAssistFile.COL_FILE_NAME.equals(this.getColumnIdentifier(columnIndex))) {

          if (aValue == null) {

            this.getBean(rowIndex).setFileDesc(null);
            this.getBean(rowIndex).setFileName(null);
            this.getBean(rowIndex).setFileId(null);            

          } else {

            this.getBean(rowIndex).setFileDesc(((AsFile) aValue).getFileName());
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
    tm.setDataBean(fileLst, detailInfo);
    return tm;
  }
  private static List<ColumnBeanPropertyPair> detailInfo = new ArrayList<ColumnBeanPropertyPair>();

  static {

    detailInfo.add(new ColumnBeanPropertyPair(SfAssistFile.COL_FILE_TYPE, "fileType", LangTransMeta.translate(SfAssistFile.COL_FILE_TYPE)));
    detailInfo.add(new ColumnBeanPropertyPair(SfAssistFile.COL_FILE_NAME, "fileName", LangTransMeta.translate(SfAssistFile.COL_FILE_NAME)));
    detailInfo.add(new ColumnBeanPropertyPair(SfAssistFile.COL_FILE_DESC, "fileDesc", LangTransMeta.translate(SfAssistFile.COL_FILE_DESC)));
  }
  public static List<ColumnBeanPropertyPair> getDetailInfo(){
    return detailInfo;
  }
}
