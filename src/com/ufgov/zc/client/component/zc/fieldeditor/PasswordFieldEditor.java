package com.ufgov.zc.client.component.zc.fieldeditor;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JPasswordField;

import com.ufgov.zc.client.component.RegexDocument;
import com.ufgov.zc.client.component.TextAreaUtil;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.common.system.util.BeanUtil;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class PasswordFieldEditor extends AbstractFieldEditor {

  /**
   * 
   */
  private static final long serialVersionUID = -7385259630680023139L;

  private JPasswordField field;

  private int maxLength = 60;

  public PasswordFieldEditor(String name, String fieldName) {

    this.fieldName = fieldName;

    init(name);

  }

  public Object getValue() {

    String pwd = field.getPassword() == null ? "" : new String(field.getPassword());
    return pwd;

  }

  public void setValue(Object value) { //在选择的上面的行发生改变的时候所触发的事件

    if (value == null) {

      field.setText(null);

      field.setToolTipText(null);

    } else if (value instanceof ZcBaseBill) {
      Object vt = BeanUtil.get(fieldName, value);
      String v = null;
      if (vt == null) {
        v = null;
      } else {
        v = vt.toString();
      }

      field.setText(v);

    } else if (value instanceof String) {

      String v = (String) value;

      field.setText(v);

    } else if (value instanceof Serializable) {

      String v = (String) BeanUtil.get(fieldName, value);

      field.setText(v);

    }

  }

  protected JComponent createEditorComponent() { //生成下面的控件，并将改变的内容同步到对象中

    field = new JPasswordField(45);

    RegexDocument rd = new RegexDocument();

    rd.setMaxLength(this.maxLength);

    field.setDocument(rd);

    TextAreaUtil.AddRightMouseUtil(field);

    field.addKeyListener(new KeyAdapter() {

      public void keyReleased(KeyEvent e) {

        syncEditObject();

      }

    });

    if (this.occCol > 1) {

      field.setPreferredSize(new Dimension(120 * this.occCol, 24));

    }

    return field;

  }

  protected void syncEditObject() {

    if (getEditObject() != null) {

      if ("".equals(field.getText())) {

        BeanUtil.set(fieldName, null, getEditObject());

      } else {

        BeanUtil.set(fieldName, field.getText(), getEditObject());

      }

    }

    this.fireEditSynced();

  }

  public void setEnabled(boolean enabled) {

    field.setEditable(enabled);

    //field.setEnabled(enabled);

  }

}
