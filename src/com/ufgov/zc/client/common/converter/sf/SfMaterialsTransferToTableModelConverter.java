package com.ufgov.zc.client.common.converter.sf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.AsEmpMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.ColumnBeanPropertyPair;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.commonbiz.model.BaseElement;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfMaterialsTransfer;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.common.sf.model.SfOutInfoDetail;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfMaterialsTransferToTableModelConverter {
  
public static TableModel convertMainLst(List mainLst) {
  // TCJLODO Auto-generated method stub

  MyTableModel tableModel = null;
  Vector names = new Vector();
  Vector values = new Vector();

  names.add(LangTransMeta.translate(SfMaterialsTransfer.COL_ENTRUST_CODE));
  names.add(LangTransMeta.translate(SfMaterialsTransfer.COL_NAME));
  names.add(LangTransMeta.translate(SfMaterialsTransfer.COL_YI_JIAO_REN));
  names.add(LangTransMeta.translate(SfMaterialsTransfer.COL_YI_JIAO_DATE));
  names.add(LangTransMeta.translate(SfMaterialsTransfer.COL_JIE_SHOU_REN));
  names.add(LangTransMeta.translate(SfMaterialsTransfer.COL_JIE_SHOU_DATE));
  names.add(LangTransMeta.translate(SfMaterialsTransfer.COL_STATUS));
  if (mainLst != null && mainLst.size() > 0) {

    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    for (int i = 0; i < mainLst.size(); i++) {
      Vector rowData = new Vector();
      SfMaterialsTransfer outInfo = (SfMaterialsTransfer) mainLst.get(i);
      rowData.add(outInfo.getEntrustCode());
      rowData.add(outInfo.getName());
      rowData.add(outInfo.getYiJiaoRenName());
      rowData.add(outInfo.getYiJiaoDate()==null?null:df.format(outInfo.getYiJiaoDate()));
      rowData.add(outInfo.getJieShouRenName());
      rowData.add(outInfo.getJieShouDate()==null?null:df.format(outInfo.getJieShouDate()));
      rowData.add(AsValDataCache.getName(SfMaterialsTransfer.SF_VS_SF_MATERIALS_TRANSFER_STATUS, outInfo.getStatus()));
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

public static TableModel convertMaterialTableData(List materialLst) {
  // TCJLODO Auto-generated method stub
  BeanTableModel<SfMaterialsTransferDetail> tm = new BeanTableModel<SfMaterialsTransferDetail>() {
    /**
     * 
     */
    private static final long serialVersionUID = -115294332374634087L;
    
    @Override
    public boolean isCellEditable(int row, int column) {
      String columnId = this.getColumnIdentifier(column);

      if (SfMaterialsTransferDetail.COL_REMARK.equals(columnId) || SfMaterialsTransferDetail.COL_PROCESSING.equals(columnId)) {
        return true;
      }else{
        return false;
      } 
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

      SfMaterialsTransferDetail bean = dataBeanList.get(rowIndex);

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

  tm.setOidFieldName("materialId");
  tm.setDataBean(materialLst, materialInfo);
  return tm;
}

public static TableModel convertOutInfoTableData(List outInfoLst) {
  // TCJLODO Auto-generated method stub
  BeanTableModel<SfMaterialsTransferDetail> tm = new BeanTableModel<SfMaterialsTransferDetail>() {
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

      SfMaterialsTransferDetail bean = dataBeanList.get(rowIndex);

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

  tm.setOidFieldName("outInfoDetailId");
  tm.setDataBean(outInfoLst, outInfo);
  return tm;
}

private static List<ColumnBeanPropertyPair> materialInfo = new ArrayList<ColumnBeanPropertyPair>();

static {
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_MATERIAL_TYPE, "material.materialType", LangTransMeta.translate(SfMaterials.COL_MATERIAL_TYPE)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_BIANHAO, "material.bianhao", LangTransMeta.translate(SfMaterials.COL_BIANHAO))); 
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_NAME, "material.name", LangTransMeta.translate(SfMaterials.COL_NAME)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_QUANTITY3, "material.quantity3", LangTransMeta.translate(SfMaterialsTransferDetail.COL_QUANTITY3)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_UNIT, "material.unit", LangTransMeta.translate(SfMaterialsTransferDetail.COL_UNIT)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_DESCRIPTION, "material.description", LangTransMeta.translate(SfMaterials.COL_DESCRIPTION)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_SAVE_CONDITON, "material.saveConditon", LangTransMeta.translate(SfMaterials.COL_SAVE_CONDITON))); 
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_SAVE_ADDRESS, "material.saveAddress", LangTransMeta.translate(SfMaterials.COL_SAVE_ADDRESS))); 
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_PROCESSING, "processing", LangTransMeta.translate(SfMaterialsTransferDetail.COL_PROCESSING)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_PROCESSING_MAN, "processingMan", LangTransMeta.translate(SfMaterialsTransferDetail.COL_PROCESSING_MAN)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_PROCESSING_DATE, "processingDate", LangTransMeta.translate(SfMaterialsTransferDetail.COL_PROCESSING_DATE)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_REMARK, "remark", LangTransMeta.translate(SfMaterialsTransferDetail.COL_REMARK)));
  
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_SL_CODE, "material.slCode", LangTransMeta.translate(SfMaterials.COL_SL_CODE)));
  
}
public static List<ColumnBeanPropertyPair> getMaterialInfo(){
  return materialInfo;
}

private static List<ColumnBeanPropertyPair> outInfo = new ArrayList<ColumnBeanPropertyPair>();

static {
  outInfo.add(new ColumnBeanPropertyPair(SfOutInfoDetail.COL_NAME, "outInfoDetail.name", LangTransMeta.translate(SfOutInfoDetail.COL_NAME)));
  outInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_QUANTITY3, "quantity3", LangTransMeta.translate(SfMaterialsTransferDetail.COL_QUANTITY3)));
  outInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_UNIT, "unit", LangTransMeta.translate(SfMaterialsTransferDetail.COL_UNIT)));
  outInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_PROCESSING, "processing", LangTransMeta.translate(SfMaterialsTransferDetail.COL_PROCESSING)));
  outInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_PROCESSING_MAN, "processingMan", LangTransMeta.translate(SfMaterialsTransferDetail.COL_PROCESSING_MAN)));
  outInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_PROCESSING_DATE, "processingDate", LangTransMeta.translate(SfMaterialsTransferDetail.COL_PROCESSING_DATE)));
  outInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_REMARK, "remark", LangTransMeta.translate(SfMaterialsTransferDetail.COL_REMARK)));
}

public static List<ColumnBeanPropertyPair> getOutInfo(){
  return outInfo;
}
}
