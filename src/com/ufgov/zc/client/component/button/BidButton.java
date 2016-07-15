package com.ufgov.zc.client.component.button;

public class BidButton extends FuncButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8053555807074280258L;

	public BidButton() {

	    super();

	  }

	  protected void init() {

	    this.funcId = "fbid";

	    this.defaultText = "投标";

	    this.iconName = "audit.jpg";

	    super.init();

	  }
}
