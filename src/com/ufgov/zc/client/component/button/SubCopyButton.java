package com.ufgov.zc.client.component.button;

public class SubCopyButton  extends FuncButton {

  /**

   * 

   */

  private static final long serialVersionUID = -4562975339140978995L;

  public SubCopyButton() {

    this.init();

  }

  public SubCopyButton(boolean funcCtrl) {

    this.funcCtrl = funcCtrl;
    this.defaultText = "复制";
    this.init();
  }

  public SubCopyButton(boolean funcCtrl, String defaultText) {
    this.funcCtrl = funcCtrl;
    this.defaultText = defaultText;
    this.init();
  }

  protected void init() {

    this.funcId = "fsubcopy";

    this.iconName = "group_edit.png";

    super.init();

  }

}