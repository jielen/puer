package com.ufgov.zc.client.component.ui.conditionitem;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JComponent;

import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.event.ValueChangeEvent;
import com.ufgov.zc.client.component.event.ValueChangeListener;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityField;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.util.NumLimUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfEntrustSearchConditionItem extends AbstractSearchConditionItem {

  private String compoId="SF_ENTRUST";

  private String fieldName = "dattr5";

  public SfEntrustSearchConditionItem(String displayName) {

    init(displayName);

  }

  public SfEntrustSearchConditionItem(String displayName, String compoId) {

    this.compoId = compoId;

    init(displayName);

  }

  private ForeignEntityField entrustSelectField;

  @Override
  protected JComponent createEditorComponent() {

    if (entrustSelectField == null) {

      creatField();

      entrustSelectField.addValueChangeListener(new ValueChangeListener() {

        public void valueChanged(ValueChangeEvent e) {

          SfEntrust value = (SfEntrust) entrustSelectField.getValue();

          BigDecimal id = null;

          if (value != null) {

            id = value.getEntrustId();

          }

          fireSearch();

          fireValueChanged();

        }

      });

    }

    return entrustSelectField;

  }

  private void creatField() {


    SfEntrustHandler proMakeHandler = new SfEntrustHandler() {
      
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub


        for (Object object : selectedDatas) {

          SfEntrust entrust = (SfEntrust) object;

          SfEntrustSearchConditionItem.this.entrustSelectField.setValue(entrust);

          SfEntrustSearchConditionItem.this.entrustSelectField.setText(entrust.getCode());

          fireSearch();

          fireValueChanged();

        }

      }
    };

    ElementConditionDto dto = new ElementConditionDto();

    // 这里要取业务年度

//    dto.setNd(WorkEnv.getInstance().getTransNd());

    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(compoId, NumLimConstants.FWATCH, "ENTRUST_ID"));

    entrustSelectField = new ForeignEntityField(proMakeHandler.getSqlId(), dto, 20, proMakeHandler, proMakeHandler.getColumNames(), "委托");

  }

  public void setEntrust(SfEntrust proj) {

    if (entrustSelectField != null) {

      entrustSelectField.setValue(proj);

    }

  }

  @Override
  public void setValue(Object value) {

    SfEntrust entrust=(SfEntrust)value;
    entrustSelectField.setValue(entrust);
    entrustSelectField.setText(entrust.getCode());

  }

  @Override
  public Object getValue() {

    return entrustSelectField.getValue();

  }

  @Override
  public void putToElementConditionDto(ElementConditionDto element) {

    BigDecimal v = new BigDecimal(-1);

    if (entrustSelectField.getValue() != null) {

      SfEntrust entrust = (SfEntrust) entrustSelectField.getValue();

      v = entrust.getEntrustId();

    }

    BeanUtil.set(fieldName, v.intValue()==-1 ? null : ""+v.intValue(), element);

  }

}
