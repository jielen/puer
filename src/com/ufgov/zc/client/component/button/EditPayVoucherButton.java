package com.ufgov.zc.client.component.button;

public class EditPayVoucherButton extends FuncButton {

  /**

   * 

   */

  private static final long serialVersionUID = -2564446291342163428L;

  public EditPayVoucherButton() {

    super();

  }

  protected void init() {

    this.funcId = "zc_feditPayVoucher";

    this.defaultText = "修改支付单";

    this.iconName = "send.jpg";

    super.init();

  }

  public void setToolTipText(String text) {

    String tipText = this.getText();

    super.setToolTipText(tipText);

  }

}
