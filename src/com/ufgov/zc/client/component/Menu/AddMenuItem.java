package com.ufgov.zc.client.component.Menu;

public class AddMenuItem extends FuncMenuItem {

  public AddMenuItem() {

    super();

  }

  @Override
  protected void init() {

    this.funcCtrl = true;

    this.funcId = "faddMenu";

    this.defaultText = "新增";

    super.init();

  }

}
