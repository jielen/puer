package com.ufgov.zc.client.component.zc.fieldeditor;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.ufgov.zc.client.component.RegexDocument;

public class TextFilePathFieldEditor extends TextFieldEditor {

  public TextFilePathFieldEditor(String name, String fieldName) {
    super(name, fieldName);
  }


  public TextFilePathFieldEditor(String name, String fieldName, int maxLength) {

    super(name, fieldName, maxLength);

  }

  public TextFilePathFieldEditor(String name, String fieldName, boolean isEditable) {

    super(name, fieldName, isEditable);

  }

  public TextFilePathFieldEditor(String name, String fieldName, int occCol, boolean isEditable) {

    super(name, fieldName, occCol, isEditable);

  }

  protected JComponent createEditorComponent() { //生成下面的控件，并将改变的内容同步到对象中

    JTextField field = (JTextField) super.createEditorComponent();

    RegexDocument rd = new RegexDocument();
    rd.setRegex("[^ 　\\\\/:*?\"'<>|\n\r\t]*");
    field.setDocument(rd);

    return field;

  }

}
