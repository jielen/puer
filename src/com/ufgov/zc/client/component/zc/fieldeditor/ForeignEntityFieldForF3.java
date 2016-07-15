/**
 * ForeignEntityFieldEditor.java
 * com.ufgov.gk.client.component.zc.fieldeditor
 * Administrator
 * 2010-4-30
 */
package com.ufgov.zc.client.component.zc.fieldeditor;

import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

/**
 * 外部部件选择框
 * @author Administrator
 *
 */
public class ForeignEntityFieldForF3 extends ForeignEntityField {

  /**
   * 
   */
  private static final long serialVersionUID = 6486922572479576827L;

  private int dialogType = ZcSettingConstants.FOREIGNENTITY_BASE;

  public ForeignEntityFieldForF3(String sqlMapSelectedId, int col, IForeignEntityHandler handler, String[] columNames, String title) {

    super(sqlMapSelectedId, col, handler, columNames, title);

  }

  public ForeignEntityFieldForF3(String sqlMapSelectedId, ElementConditionDto elementConditionDto, int col, IForeignEntityHandler handler,
    String[] columNames, String title) {

    super(sqlMapSelectedId, elementConditionDto, col, handler, columNames, title);

  }

  public ForeignEntityFieldForF3(String sqlMapSelectedId, ElementConditionDto elementConditionDto, int col, IForeignEntityHandler handler,
    String[] columNames, String dialogTitle, int foreignentityNewSupplier) {
    super(sqlMapSelectedId, elementConditionDto, col, handler, columNames, dialogTitle, foreignentityNewSupplier);

  }

  void init() {
    if (this.dialogType != ZcSettingConstants.FOREIGNENTITY_BASE) {
      selectDialog = new ForeignEntityDialogForF3(owner, true, this, this.getHandler(), this.getSqlMapSelectedId(), this.getElementConditionDto(),
        this.getTitle(), this.dialogType);
    } else {
      selectDialog = new ForeignEntityDialogForF3(owner, true, this, this.getHandler(), this.getSqlMapSelectedId(), this.getElementConditionDto(),
        this.getTitle());
    }
  }

}
