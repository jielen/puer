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
import com.ufgov.zc.common.commonbiz.model.BaseElement;
import com.ufgov.zc.common.sf.model.SfCertificate;
import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.sf.model.SfDocSendDetail;
import com.ufgov.zc.common.sf.model.SfDocSendDetail;
import com.ufgov.zc.common.sf.model.SfDocSendMaterial;
import com.ufgov.zc.common.sf.model.SfJdPersonMajor;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.common.system.model.AsFile;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfDocSendToTableModelConverter {
  public static TableModel convertMainLst(List mainLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;
    Vector names = new Vector();
    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfDocSend.COL_ENTRUST_CODE));
    names.add(LangTransMeta.translate(SfDocSend.COL_NAME));
    names.add(LangTransMeta.translate(SfDocSend.COL_WTF));
    names.add(LangTransMeta.translate(SfDocSend.COL_TI_JIAO_REN));
    names.add(LangTransMeta.translate(SfDocSend.COL_TI_JIAO_DATE));
    names.add(LangTransMeta.translate(SfDocSend.COL_JIE_SHOU_REN));
    names.add(LangTransMeta.translate(SfDocSend.COL_JIE_SHOU_DATE));
    names.add(LangTransMeta.translate(SfDocSend.COL_RECIEVOR));
    names.add(LangTransMeta.translate(SfDocSend.COL_RECIEVOR_TEL));
    names.add(LangTransMeta.translate(SfDocSend.COL_SEND_DATE));
    names.add(LangTransMeta.translate(SfDocSend.COL_SENDOR)); 
    if (mainLst != null && mainLst.size() > 0) {

      SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
      
      for (int i = 0; i < mainLst.size(); i++) {
        Vector rowData = new Vector();
        SfDocSend jdPerson = (SfDocSend) mainLst.get(i);
        rowData.add(jdPerson.getEntrust().getCode());
        rowData.add(jdPerson.getEntrust().getName());
        rowData.add(jdPerson.getEntrust().getEntrustor().getName());
        rowData.add(jdPerson.getTiJiaoRenName());
        rowData.add(jdPerson.getTiJiaoDate()==null?null:df.format(jdPerson.getTiJiaoDate()));
        rowData.add(jdPerson.getJieShouRenName());
        rowData.add(jdPerson.getJieShouDate()==null?null:df.format(jdPerson.getJieShouDate()));
        rowData.add(jdPerson.getRecievor());
        rowData.add(jdPerson.getRecievorTel()); 
        rowData.add(jdPerson.getSendDate()==null?null:df.format(jdPerson.getSendDate()));
        rowData.add(jdPerson.getSendorName()); 
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

public static TableModel convertDetailTableData(List itemList) {
  // TCJLODO Auto-generated method stub
    BeanTableModel<SfDocSendDetail> tm = new BeanTableModel<SfDocSendDetail>() {
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
  
        SfDocSendDetail bean = dataBeanList.get(rowIndex);
  
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
  
    tm.setOidFieldName("docName");
    tm.setDataBean(itemList, detailInfo);
    return tm;
  }

private static List<ColumnBeanPropertyPair> detailInfo = new ArrayList<ColumnBeanPropertyPair>();

static {
  detailInfo.add(new ColumnBeanPropertyPair(SfDocSendDetail.COL_DOC_NAME, "docAuditDetail.docName", LangTransMeta.translate(SfDocSendDetail.COL_DOC_NAME)));
  detailInfo.add(new ColumnBeanPropertyPair(SfDocSendDetail.COL_QUANTITY, "docAuditDetail.quantity", LangTransMeta.translate(SfDocSendDetail.COL_QUANTITY)));
  detailInfo.add(new ColumnBeanPropertyPair(SfDocSendDetail.COL_REMARK, "remark", LangTransMeta.translate(SfDocSendDetail.COL_REMARK)));
}
public static List<ColumnBeanPropertyPair> getDetailInfo(){
  return detailInfo;
}
  

  public static TableModel convertCertTableData(List itemList) {
    // TCJLODO Auto-generated method stub

    BeanTableModel<SfCertificate> tm = new BeanTableModel<SfCertificate>() {
      /**
       * 
       */ 
      
      @Override
      public boolean isCellEditable(int row, int column) {
        return super.isCellEditable(row, column);
      }
      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        SfCertificate bean = dataBeanList.get(rowIndex);

        String currentColName = this.getColumnIdentifier(columnIndex);

        if (aValue instanceof BaseElement) {

          BeanUtil.set(columnBeanPropertyPairList.get(columnIndex).getBeanPropertyName(), ((BaseElement) aValue).getName(), bean);

          fireTableCellUpdated(rowIndex, columnIndex);

          putEditedData(dataBeanList.get(rowIndex));

        } else  if (SfCertificate.COL_ZF_FILE.equals(this.getColumnIdentifier(columnIndex))) {

            if (aValue == null) {

              this.getBean(rowIndex).setZfFile(null);

              this.getBean(rowIndex).setZfFileBlobid(null);

            } else {

              this.getBean(rowIndex).setZfFile(((AsFile) aValue).getFileName());

              this.getBean(rowIndex).setZfFileBlobid(((AsFile) aValue).getFileId());

            }

            fireTableCellUpdated(rowIndex, columnIndex);

            putEditedData(dataBeanList.get(rowIndex));
        }else {
          super.setValueAt(aValue, rowIndex, columnIndex);
        }
      }
    };

    tm.setOidFieldName("tempId");
    tm.setDataBean(itemList, certInfo);
    return tm;
  }

  private static List<ColumnBeanPropertyPair> certInfo = new ArrayList<ColumnBeanPropertyPair>();

  static {

    certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_OWNER_TYPE, "zfOwnerType", LangTransMeta.translate(SfCertificate.COL_ZF_OWNER_TYPE)));
    certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_NAME, "name", LangTransMeta.translate(SfCertificate.COL_NAME)));
    certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_CODE, "zfCode", LangTransMeta.translate(SfCertificate.COL_ZF_CODE)));
    certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_AGENCY, "zfAgency", LangTransMeta.translate(SfCertificate.COL_ZF_AGENCY)));
    certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_DESC, "zfDesc", LangTransMeta.translate(SfCertificate.COL_ZF_DESC)));
    certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_FILE, "zfFile", LangTransMeta.translate(SfCertificate.COL_ZF_FILE)));
    certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_BEGIN_DATE, "zfBeginDate", LangTransMeta.translate(SfCertificate.COL_ZF_BEGIN_DATE)));
    certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_END_DATE, "zfEndDate", LangTransMeta.translate(SfCertificate.COL_ZF_END_DATE))); 
    certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_IS_NOTICE_EXPIRE, "isNoticeExpire", LangTransMeta.translate(SfCertificate.COL_IS_NOTICE_EXPIRE)));
    certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_NOTICE_DAYS, "zfNoticeDays", LangTransMeta.translate(SfCertificate.COL_ZF_NOTICE_DAYS)));

 }
  public static List<ColumnBeanPropertyPair> getCertInfo(){
    return certInfo;
  }

public static TableModel convertMaterialTableData(List materialLst) {
  // TCJLODO Auto-generated method stub
  BeanTableModel<SfDocSendMaterial> tm = new BeanTableModel<SfDocSendMaterial>() {
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

      SfDocSendMaterial bean = dataBeanList.get(rowIndex);

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
private static List<ColumnBeanPropertyPair> materialInfo = new ArrayList<ColumnBeanPropertyPair>();

static {
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_MATERIAL_TYPE, "materialTransfer.material.materialType", LangTransMeta.translate(SfMaterials.COL_MATERIAL_TYPE)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_BIANHAO, "materialTransfer.material.bianhao", LangTransMeta.translate(SfMaterials.COL_BIANHAO))); 
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_NAME, "materialTransfer.material.name", LangTransMeta.translate(SfMaterials.COL_NAME)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_QUANTITY3, "materialTransfer.material.quantity3", LangTransMeta.translate(SfMaterialsTransferDetail.COL_QUANTITY3)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_UNIT, "materialTransfer.material.unit", LangTransMeta.translate(SfMaterialsTransferDetail.COL_UNIT)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_DESCRIPTION, "materialTransfer.material.description", LangTransMeta.translate(SfMaterials.COL_DESCRIPTION)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_SAVE_CONDITON, "materialTransfer.material.saveConditon", LangTransMeta.translate(SfMaterials.COL_SAVE_CONDITON))); 
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterials.COL_SAVE_ADDRESS, "materialTransfer.material.saveAddress", LangTransMeta.translate(SfMaterials.COL_SAVE_ADDRESS))); 
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_PROCESSING, "processing", LangTransMeta.translate(SfMaterialsTransferDetail.COL_PROCESSING)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_PROCESSING_MAN, "materialTransfer.processingMan", LangTransMeta.translate(SfMaterialsTransferDetail.COL_PROCESSING_MAN)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_PROCESSING_DATE, "materialTransfer.processingDate", LangTransMeta.translate(SfMaterialsTransferDetail.COL_PROCESSING_DATE)));
  materialInfo.add(new ColumnBeanPropertyPair(SfMaterialsTransferDetail.COL_REMARK, "remark", LangTransMeta.translate(SfMaterialsTransferDetail.COL_REMARK)));
 
  
}
public static List<ColumnBeanPropertyPair> getMaterialInfo(){
  return materialInfo;
}
}
