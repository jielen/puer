package com.ufgov.zc.client.component.Menu;

public class DeleteMenuItem extends FuncMenuItem {

  public DeleteMenuItem() {

    super();

  }

  @Override
  protected void init() {

    this.funcCtrl = true;

    this.funcId = "fdeleteMenu";

    this.defaultText = "新增";

    super.init();

  }

}
