/**
 * 
 */
package com.ufgov.zc.client.component.ui.fieldeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.ufgov.zc.client.component.YearComboBox;
import com.ufgov.zc.common.system.model.AsVal;
import com.ufgov.zc.common.system.util.BeanUtil;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

/**
 * 年度下拉框，具备自动增长年度
 * @author Administrator
 *
 */
public class YearComboBoxFieldeditor extends AbstractFieldEditor {

  /**
   * 
   */
  private static final long serialVersionUID = 5987586406266341238L;

  private boolean firstAddNull = true;

  private boolean defaultCurrentYear = true;

  private YearComboBox field;

  public YearComboBoxFieldeditor(String name, String fieldName, boolean firstAddNull, boolean defaultCurrentYear) {
    this.fieldName = fieldName;
    this.firstAddNull = firstAddNull;
    this.defaultCurrentYear = defaultCurrentYear;

    init(name);
  }

  @Override
  public void setValue(Object value) {
    // TCJLODO Auto-generated method stub
    if (value == null) {

      field.setSelectedAsVal(null);

    } else if (value instanceof ZcBaseBill) {

      Integer integerV = (Integer) BeanUtil.get(fieldName, value);
      if (integerV != null) {
        field.setSelectedAsValByCode("" + integerV.intValue());
      } else {
        field.setSelectedAsValByCode(null);
      }

    }
  }

  public void setEnabled(boolean enabled) {

    field.setEnabled(enabled);

  }

  @Override
  public Object getValue() {
    // TCJLODO Auto-generated method stub
    return field.getSelectedAsVal();
  }

  @Override
  protected JComponent createEditorComponent() {
    // TCJLODO Auto-generated method stub
    field = new YearComboBox(firstAddNull, defaultCurrentYear);
    field.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        syncEditObject();
        afterChange(field);
      }

    });

    return field;
  }

  protected void afterChange(YearComboBox field) {

  }

  protected void syncEditObject() {

    if (getEditObject() instanceof ZcBaseBill) {

      ZcBaseBill bill = (ZcBaseBill) getEditObject();

      if (bill != null) {

        AsVal val = field.getSelectedAsVal();

        String valId = null;

        if (val != null) {
          valId = val.getValId();
          Integer v = Integer.parseInt(valId);
          BeanUtil.set(fieldName, v, getEditObject());
        } else {
          BeanUtil.set(fieldName, null, getEditObject());
        }
      }

    }

    this.fireEditSynced();

  }
}
