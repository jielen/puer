package com.ufgov.zc.client.component.table.celleditor;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import com.ufgov.zc.client.component.table.GkAbstractCellEditor;

public class TextFilePathCellEditor extends GkAbstractCellEditor implements TableCellEditor {

  protected JTextField field = new JTextField();

  public TextFilePathCellEditor() {

  }


  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

    if (value == null) {

      field.setText(null);

    } else {

      field.setText(value.toString().replaceAll("[\\\\/:*?\"'<>|\n\r\t]*", "").trim());

    }

    return field;

  }

  public Object getCellEditorValue() {

    Object obj = field.getText();

    if (obj != null && !((String) obj).equals("")) {

      return obj.toString().replaceAll("[\\\\/:*?\"'<>|\n\r\t]*", "").trim();

    } else

      return null;

  }

}