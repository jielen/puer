/**
 * 
 */
package com.ufgov.zc.client.component.sf.fieldeditor;

import javax.swing.JComponent;

import com.ufgov.zc.client.component.event.ValueChangeEvent;
import com.ufgov.zc.client.component.event.ValueChangeListener;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.common.sf.model.ZcFaCardType;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.BeanUtil;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

/**
 * @author Administrator
 *
 */
public class ZcFaCardTypeTreeSelectEditor extends AbstractFieldEditor {

  /**
   * 
   */
  private static final long serialVersionUID = -3330207807171628480L;

  ZcFaCardTypeTreeSelectField coField ;
  
  boolean selectedTailFlag;
  
  ElementConditionDto selectConditonDto;
  
  IForeignEntityHandler handler;
  
  public ZcFaCardTypeTreeSelectEditor(String name, String fieldName, boolean selectedTailFlag,ElementConditionDto selectConditonDto,IForeignEntityHandler handler){
    this.fieldName = fieldName;
    this.selectedTailFlag=selectedTailFlag;
    this.selectConditonDto=selectConditonDto;
    this.handler=handler;
    init(name);
  }
  
  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor#setValue(java.lang.Object)
   */
  @Override
  public void setValue(Object value) {
    // TCJLODO Auto-generated method stub
    coField.setValueByCode((String) BeanUtil.get(fieldName, value));
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor#getValue()
   */
  @Override
  public Object getValue() {
    // TCJLODO Auto-generated method stub
    return coField.getZcFaCardType();
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor#createEditorComponent()
   */
  @Override
  protected JComponent createEditorComponent() {
    // TCJLODO Auto-generated method stub
    coField=new ZcFaCardTypeTreeSelectField(this.selectedTailFlag, this.selectConditonDto,this.handler);
    coField.addValueChangeListener(new ValueChangeListener() {

      public void valueChanged(ValueChangeEvent e) {

        syncEditObject();

      }

    });

    return coField;
  }
  protected void syncEditObject() {

    if (getEditObject() instanceof ZcBaseBill) {

      ZcBaseBill bill = (ZcBaseBill) getEditObject();

      if (bill != null) {

        ZcFaCardType cardType = coField.getZcFaCardType();

        BeanUtil.set("faCartype", cardType == null ? null : cardType.getCode(), getEditObject());

        BeanUtil.set("fatypeName", cardType == null ? null : cardType.getCode()+cardType.getName(), getEditObject());

      }

    }

    this.fireEditSynced();

  }
}
