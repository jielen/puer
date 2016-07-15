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
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfEntrustToTableModelConverter {

  public static TableModel convertEntrustLst(List entrustLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;

    Vector names = new Vector();

    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfEntrust.COL_CODE));
    names.add(LangTransMeta.translate(SfEntrust.COL_NAME));
    names.add(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME));
    names.add(LangTransMeta.translate(SfEntrust.COL_ENTRUSTOR_NAME));
    names.add(LangTransMeta.translate(SfEntrust.COL_SJR));
    names.add(LangTransMeta.translate(SfEntrust.COL_SJR_TEL));
    names.add(LangTransMeta.translate(SfEntrust.COL_IS_ACCEPT));
    names.add(LangTransMeta.translate(SfEntrust.COL_ACCEPT_DATE));
    names.add(LangTransMeta.translate(SfEntrust.COL_STATUS));
    if (entrustLst != null && entrustLst.size() > 0) {

      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < entrustLst.size(); i++) {
        Vector rowData = new Vector();
        SfEntrust entrust = (SfEntrust) entrustLst.get(i);
        rowData.add(entrust.getCode());
        rowData.add(entrust.getName());
        rowData.add(AsValDataCache.getName(SfMajor.SF_VS_MAJOR, entrust.getMajorCode()));
        rowData.add(entrust.getEntrustor().getName());
        rowData.add(entrust.getSjr());
        rowData.add(entrust.getSjrTel());
        rowData.add(AsValDataCache.getName(SfElementConstants.VS_Y_N, entrust.getIsAccept()));
        rowData.add(entrust.getAcceptDate() == null ? null : df.format(entrust.getAcceptDate()));
        rowData.add(AsValDataCache.getName(SfEntrust.SF_VS_ENTRUST_STATUS, entrust.getStatus()));
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

    tableModel.setList(entrustLst);

    return tableModel;
  }

  public static TableModel convertMaterialsTableData(List itemList) {
    // TCJLODO Auto-generated method stub

    BeanTableModel<SfMaterials> tm = new BeanTableModel<SfMaterials>() {
      /**
       * 
       */
      private static final long serialVersionUID = 1614780332598039135L;

      @Override
      public boolean isCellEditable(int row, int column) {
        return super.isCellEditable(row, column);
      }

      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        SfMaterials bean = dataBeanList.get(rowIndex);

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
    tm.setDataBean(itemList, materialInfo);
    return tm;
  }

  private static List<ColumnBeanPropertyPair> materialInfo = new ArrayList<ColumnBeanPropertyPair>();

  static {

	materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_MATERIAL_TYPE, "materialType", LangTransMeta.translate(SfMaterials.COL_MATERIAL_TYPE)));
    materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_BIANHAO, "bianhao", LangTransMeta.translate(SfMaterials.COL_BIANHAO)));
    materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_NAME, "name", LangTransMeta.translate(SfMaterials.COL_NAME)));
//    materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_QUANTITY, "quantity", LangTransMeta.translate(SfMaterials.COL_QUANTITY)));
//    materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_QUANTITY2, "quantity2", LangTransMeta.translate(SfMaterials.COL_QUANTITY2)));
    materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_QUANTITY3, "quantity3", LangTransMeta.translate(SfMaterials.COL_QUANTITY3)));
        materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_UNIT, "unit", LangTransMeta.translate(SfMaterials.COL_UNIT)));
    materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_DESCRIPTION, "description", LangTransMeta.translate(SfMaterials.COL_DESCRIPTION)));
  }

  public static List<ColumnBeanPropertyPair> getMaterialInfo() {
    return materialInfo;
  }
}
