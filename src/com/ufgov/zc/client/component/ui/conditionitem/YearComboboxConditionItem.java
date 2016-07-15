/**
 * 
 */
package com.ufgov.zc.client.component.ui.conditionitem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.ufgov.zc.client.component.YearComboBox;

/**
 * @author Administrator
 *
 */
public class YearComboboxConditionItem extends AbstractSearchConditionItem {

  /**
   * 
   */
  private static final long serialVersionUID = -7297508406689077417L;

  private boolean firstAddNull = true;

  private boolean defaultCurYear = true;

  public YearComboboxConditionItem(String displayName) {

    this(displayName,false,false);

  }
  public YearComboboxConditionItem(String displayName,boolean firstAddNull,boolean defaultCurYear) {

    init(displayName);
    
    this.firstAddNull=firstAddNull;
    this.defaultCurYear=defaultCurYear;

  }
  private YearComboBox yearComboBox;

  protected JComponent createEditorComponent() {

    if (yearComboBox == null) {

      yearComboBox = new YearComboBox(this.firstAddNull,this.defaultCurYear);

      yearComboBox.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {

          fireSearch();

          fireValueChanged();

        }

      });

    }

    return yearComboBox;

  }

  public void setValue(Object value) {

    yearComboBox.setSelectedItem(value);

  }

  public Object getValue() {

    return yearComboBox.getSelectedItem();

  }

  public void setValueByCode(String code) {

    yearComboBox.setSelectedAsValByCode(code);

  }
}
