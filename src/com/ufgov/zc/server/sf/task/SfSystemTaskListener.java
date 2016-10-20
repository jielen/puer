/**
 * 
 */
package com.ufgov.zc.server.sf.task;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Administrator
 *
 */
public class SfSystemTaskListener implements ServletContextListener{

	  private Timer timer = null;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
	}

	
	public void contextInitialized(ServletContextEvent arg0) {
	    timer = new Timer(true);
	    timer.schedule(new SfNoticeTask(), 1000 * 2, 1000 * 60 * 5);
	}

}
