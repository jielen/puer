/**
 * 
 */
package com.ufgov.zc.client.ftp.apache.utils;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
 

 
/**
 * ftp传输进程的观察者
 * @author Administrator
 *
 */
public class FtpProgressObserver implements Observer {

  
  private static Logger logger=Logger.getLogger(FtpProgressObserver.class);
  
   
  public  FtpProgressObserver() {
    
  } 
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg!=null){
			ProgressArg pg=(ProgressArg) arg;
			StringBuilder sb=new StringBuilder();
			sb.append("总计:\t").append(pg.getMax()).append("\t当前:\t").append(pg.getValue()).append("\t百分比:\t").append(pg.getPer()).append("%\t速度:\t").append(pg.getSpeed());
//			System.out.println(sb.toString());
			logger.debug(sb.toString()); 
		}
	}

}

