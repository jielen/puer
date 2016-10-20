/**
 * 
 */
package com.ufgov.zc.client.sf.sb;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;

/**
 * @author Administrator
 *
 */
public class ZcFaCardClientUtil {

  public JPanel createPanel(List<AbstractFieldEditor> editorList,int cols) {
    // TCJLODO Auto-generated method stub

    JPanel panel=new JPanel();

    panel.setLayout(new GridBagLayout());
    
    int row = 0;

    int col = 0;

    for (int i = 0; i < editorList.size(); i++) {

      AbstractFieldEditor comp = editorList.get(i);

      if (comp.isVisible()) {

        if (comp instanceof NewLineFieldEditor) {
          row++;
          col = 0;
          continue;
        } else if (comp instanceof TextAreaFieldEditor) {
          // 转到新的一行
          row++;
          col = 0;
          JLabel label = new JLabel(getLabelText(comp));
          if (comp.getMaxContentSize() != 9999) {
            label.setText(comp.getName() + "("+ comp.getMaxContentSize() + "字内)" + "*");
          }
          comp.setPreferredSize(new Dimension(150,  comp.getOccRow() * 26));
          
          panel.add(label, new GridBagConstraints(col,row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST,GridBagConstraints.NONE, new Insets(4, 0, 4, 4), 0,0));

          panel.add(comp, new GridBagConstraints(col + 1,row, comp.getOccCol(), comp.getOccRow(), 1.0, 1.0,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, new Insets(4, 0, 4,4), 0, 0));

          // 将当前所占的行空间偏移量计算上
          row += comp.getOccRow();
          col = 0;

          continue;
        }

        JLabel label = new JLabel(comp.getName());

        comp.setPreferredSize(new Dimension(200, 23));

        panel.add(label, new GridBagConstraints(col, row, 1,1, 1.0, 1.0, GridBagConstraints.EAST,GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));

        panel.add(comp, new GridBagConstraints(col + 1, row,1, 1, 1.0, 1.0, GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5),0, 0));

        if (col == cols * 2 - 2) {
          row++;
          col = 0;
        } else {
          col += 2;
        }
      }

    }
    return panel;
  }
  public String getLabelText(AbstractFieldEditor comp) {

    StringBuffer buff = new StringBuffer();

    buff.append("<html><a>&nbsp;");

    buff.append(comp.getName());

    if (comp.getMaxContentSize() <= 0) {

      buff.append("</a></html>");

    } else {

      if (comp.getOccRow() >= 2) {

        buff.append("<br>(");

      } else {

        buff.append("(");

      }

      buff.append(comp.getMaxContentSize());

      buff.append("字内)</a></html>");

    }

    return buff.toString();

  }
}
