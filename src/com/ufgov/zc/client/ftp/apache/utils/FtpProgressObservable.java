/**
 * 
 */
package com.ufgov.zc.client.ftp.apache.utils;

import java.util.Observable;
 

/**
 * ftp传说进度观察对象
 * @author Administrator
 *
 */
public class FtpProgressObservable extends Observable {

	private ProgressArg processArg;

	public ProgressArg getProcessArg() {
		return processArg;
	}

	public void setProcessArg(ProgressArg processArg) {
		this.processArg = processArg;
        setChanged();
        notifyObservers(processArg);		
	}
}

