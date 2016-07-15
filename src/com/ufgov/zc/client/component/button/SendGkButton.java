package com.ufgov.zc.client.component.button;

public class SendGkButton extends FuncButton {

  /**

   * 

   */

  private static final long serialVersionUID = -2564446291342163428L;

  public SendGkButton() {

    super();

  }

  protected void init() {

    this.funcId = "zc_fsend";

    this.defaultText = "送国库";

    super.init();

  }

  public void setToolTipText(String text) {

    String tipText = this.getText();

    super.setToolTipText(tipText);

  }

}
