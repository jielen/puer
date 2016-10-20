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
import com.ufgov.zc.common.sf.model.SfAppendMaterial;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.system.model.AsFile;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfAppendMaterialToTableModelConverter {

  public static TableModel convertMainLst(List mainLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;
    Vector names = new Vector();
    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfAppendMaterial.COL_ENTRUST_CODE));
    names.add(LangTransMeta.translate(SfAppendMaterial.COL_NAME));
    names.add(LangTransMeta.translate(SfAppendMaterial.COL_SJR));
    names.add(LangTransMeta.translate(SfAppendMaterial.COL_SJR_TEL));
    names.add(LangTransMeta.translate(SfAppendMaterial.COL_ACCEPT_DATE));
    names.add(LangTransMeta.translate(SfAppendMaterial.COL_STATUS));
    if (mainLst != null && mainLst.size() > 0) {

      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < mainLst.size(); i++) {
        Vector rowData = new Vector();
        SfAppendMaterial material = (SfAppendMaterial) mainLst.get(i);
        rowData.add(material.getEntrustCode());
        rowData.add(material.getName());
        rowData.add(material.getSjr());
        rowData.add(material.getSjrTel());
        rowData.add(material.getAcceptDate() == null ? null : df.format(material.getAcceptDate()));
        rowData.add(AsValDataCache.getName(SfAppendMaterial.SF_VS_APPEND_MATERIAL_STATUS, material.getStatus()));
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
 

}
