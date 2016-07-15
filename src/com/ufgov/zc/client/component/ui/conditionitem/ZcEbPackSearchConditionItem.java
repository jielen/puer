package com.ufgov.zc.client.component.ui.conditionitem;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.event.ValueChangeEvent;
import com.ufgov.zc.client.component.event.ValueChangeListener;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityField;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.BeanUtil;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;
import com.ufgov.zc.common.zc.model.ZcEbPack;

/**
 * 
* @ClassName: ZcEbPackSearchConditionItem
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: Dec 1, 2012 9:12:07 AM
* @version: V1.0 
* @since: 1.0
* @author: fanpl
* @modify:
 */
public class ZcEbPackSearchConditionItem extends AbstractSearchConditionItem {

  private String compoId;

  private String fieldName = "packCode";

  public ZcEbPackSearchConditionItem(String displayName) {

    init(displayName);

  }

  public ZcEbPackSearchConditionItem(String displayName, String compoId) {

    this.compoId = compoId;

    init(displayName);

  }

  private ForeignEntityField packSelectField;

  protected JComponent createEditorComponent() {

    if (packSelectField == null) {

      creatField();

      packSelectField.addValueChangeListener(new ValueChangeListener() {

        public void valueChanged(ValueChangeEvent e) {

          ZcEbPack value = (ZcEbPack) packSelectField.getValue();

          String code = null;

          if (value != null) {

            code = value.getPackCode();

          }

          fireSearch();

          fireValueChanged();

        }

      });

    }

    return packSelectField;

  }

  private void creatField() {

    String columNames[] = { "项目编号", "分包ID", "分包编号", "分包名称", "采购方式", "分包预算" };

    ZcEbPackFnHandler packHandler = new ZcEbPackFnHandler(columNames);

    ElementConditionDto dto = new ElementConditionDto();

    dto.setNd(WorkEnv.getInstance().getSysNd());

    packSelectField = new ForeignEntityField("ZcEbProj.getZcEbPack", dto, 20, packHandler, columNames,

    "采购项目");

  }

  @Override
  public void setValue(Object value) {

    packSelectField.setValue(value);

  }

  @Override
  public Object getValue() {

    return packSelectField.getValue();

  }

  @Override
  public void putToElementConditionDto(ElementConditionDto element) {

    String v = "";
    if (packSelectField.getValue() != null) {
      ZcEbPack pack = (ZcEbPack) packSelectField.getValue();
      v = pack.getPackCode();
    }
    BeanUtil.set(fieldName, v.equals("") ? null : v, element);

  }

  private class ZcEbPackFnHandler implements IForeignEntityHandler {

    private String columNames[];

    public ZcEbPackFnHandler(String columNames[]) {

      this.columNames = columNames;

    }

    public void excute(List selectedDatas) {

      // TCJLODO Auto-generated method stub

      for (Object object : selectedDatas) {

        ZcEbPack zcEbPack = (ZcEbPack) object;

        ZcEbPackSearchConditionItem.this.packSelectField.setValue(zcEbPack);

        ZcEbPackSearchConditionItem.this.packSelectField.setText(zcEbPack.getPackName());

        fireSearch();

        fireValueChanged();

      }

    }

    @Override
    public TableModel createTableModel(List showDatas) {

      Object data[][] = new Object[showDatas.size()][columNames.length];

      for (int i = 0; i < showDatas.size(); i++) {

        ZcEbPack rowData = (ZcEbPack) showDatas.get(i);

        int col = 0;

        data[i][col++] = rowData.getProjCode();

        data[i][col++] = rowData.getPackCode();

        data[i][col++] = rowData.getPackName();

        data[i][col++] = rowData.getPackDesc();

        data[i][col++] = AsValDataCache.getName("ZC_VS_PITEM_OPIWAY", rowData.getPurType());

        data[i][col++] = rowData.getPackBudget();

      }

      MyTableModel model = new MyTableModel(data, columNames) {

        public boolean isCellEditable(int row, int colum) {

          return false;

        }

      };

      return model;

    }

    @Override
    public boolean isMultipleSelect() {

      // TCJLODO Auto-generated method stub

      return false;

    }

  }

}
