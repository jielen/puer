package com.ufgov.zc.client.component.button;

public class AddFormulaButton extends FuncButton {

  public AddFormulaButton() {

    super();

  }

  protected void init() {

    this.funcId = "fnewFormula";

    this.defaultText = "新增评标方法";

    this.iconName = "add.jpg";

    super.init();

  }

  public void setToolTipText(String text) {

    super.setToolTipText(text);

  }

}
