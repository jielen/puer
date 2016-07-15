package com.ufgov.zc.client.component.button;

public class UnBidButton extends FuncButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2471460823133970878L;

	public UnBidButton() {

	    super();

	  }

	  protected void init() {

	    this.funcId = "funbid";

	    this.defaultText = "撤回投标";

	    this.iconName = "callback.jpg";

	    super.init();

	  }
}
