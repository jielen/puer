/**
 * ForeignEntityDialog.java
 * com.ufgov.gk.client.component.zc.fieldeditor
 * Administrator
 * 2010-4-30
 */
package com.ufgov.zc.client.component.zc.fieldeditor;

import java.awt.Dialog;

import org.apache.log4j.Logger;

import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.JButtonTextField;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class ForeignEntityDialogForF3 extends ForeignEntityDialog {

  private Logger log = Logger.getLogger(ForeignEntityDialogForF3.class);

  private int openAddDialogType = ZcSettingConstants.FOREIGNENTITY_BASE;

  /**
   * 
   */
  private static final long serialVersionUID = -5603007275009072098L;

  private IForeignEntityHandler handler;

  public ForeignEntityDialogForF3(Dialog dialog, boolean modal, JButtonTextField triggerField, IForeignEntityHandler handler,
    String sqlMapSelectedId, String title) {
    super(dialog, modal, triggerField, handler, sqlMapSelectedId, title);

  }

  public ForeignEntityDialogForF3(Dialog dialog, boolean modal, JButtonTextField triggerField, IForeignEntityHandler handler,
    String sqlMapSelectedId, ElementConditionDto elementConditionDto, String title) {
    super(dialog, modal, triggerField, handler, sqlMapSelectedId, elementConditionDto, title);
    this.handler = handler;
  }

  public ForeignEntityDialogForF3(Dialog dialog, boolean modal, ForeignEntityField triggerField, IForeignEntityHandler handler,
    String sqlMapSelectedId, ElementConditionDto elementConditionDto, String title, int dialogType) {
    super(dialog, modal, triggerField, handler, sqlMapSelectedId, elementConditionDto, title, dialogType);
    this.handler = handler;
    this.openAddDialogType = dialogType;
  }

  public void initDataBufferList() {
    int nd = WorkEnv.getInstance().getTransNd();
    IZcEbBaseServiceDelegate baseDataServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
      "zcEbBaseServiceDelegateF3");
    RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();
    if (this.elementConditionDto == null) {
      this.elementConditionDto = new ElementConditionDto();
      this.elementConditionDto.setNd(nd);
    }

    this.dataBufferList = baseDataServiceDelegate.getForeignEntitySelectedData(this.sqlMapSelectedId, this.elementConditionDto, requestMeta);

    if (this.elementConditionDto.getNumLimitStr() == null && this.elementConditionDto.getDataRuleCondiStr() == null) {
      numLimDataList = dataBufferList;
    } else {
      numLimDataList = baseDataServiceDelegate.getForeignEntitySelectedData(this.sqlMapSelectedId, this.elementConditionDto, requestMeta);
    }
  }

}
