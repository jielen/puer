/**
 * 
 */
package com.ufgov.zc.client.component.sf.fieldeditor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityField;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

/**
 * 委托人外部选择部件，带有新增按钮
 * @author Administrator
 *
 */
public class SfEntrustorNewFieldEditor extends ForeignEntityFieldEditor {

  /**
   * 
   */
  private static final long serialVersionUID = 4883803236861089119L;

  public SfEntrustorNewFieldEditor(String sqlMapSelectedId, int col, IForeignEntityHandler handler, String[] columNames, String name, String fieldName) {
    super(sqlMapSelectedId, col, handler, columNames, name, fieldName);
    // TCJLODO Auto-generated constructor stub
  }
  @Override
  protected JComponent createEditorComponent() {

    // TCJLODO Auto-generated method stub

    //    System.out.println("createEditorComponent "+this.sqlMapSelectedId);

    this.field = new ForeignEntityField(this.sqlMapSelectedId, this.elementConditionDto, this.col,
    this.handler, this.columNames, this.dialogTitle, ZcSettingConstants.FOREIGNENTITY_NEW_SF_ENTRUSTOR);

    field.addKeyListener(new KeyAdapter() {

      public void keyReleased(KeyEvent e) {

        syncEditObject();

      }

    });

    return this.field;

  }

  public ForeignEntityField getField() {

    return field;

  }

}
