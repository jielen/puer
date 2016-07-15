/**
 * 
 */
package com.ufgov.zc.client.applet.local;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.security.Permission;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

/**
 * @author Administrator
 *
 */
public abstract class MyBaseApplet extends JApplet {

	  private Map extraProp = new HashMap();

	  static {
	    System.setSecurityManager(new SecurityManager() {

	      @Override
	      public void checkAccept(String host, int port) {
	      }

	      @Override
	      public void checkAccess(Thread t) {
	      }

	      @Override
	      public void checkAccess(ThreadGroup g) {
	      }

	      @Override
	      public void checkAwtEventQueueAccess() {
	      }

	      @Override
	      public void checkConnect(String host, int port, Object context) {
	      }

	      @Override
	      public void checkConnect(String host, int port) {
	      }

	      @Override
	      public void checkCreateClassLoader() {
	      }

	      @Override
	      public void checkDelete(String file) {
	      }

	      @Override
	      public void checkExec(String cmd) {
	      }

	      @Override
	      public void checkExit(int status) {
	      }

	      @Override
	      public void checkLink(String lib) {
	      }

	      @Override
	      public void checkListen(int port) {
	      }

	      @Override
	      public void checkMemberAccess(Class<?> clazz, int which) {
	      }

	      @Override
	      public void checkMulticast(InetAddress maddr, byte ttl) {
	      }

	      @Override
	      public void checkMulticast(InetAddress maddr) {
	      }

	      @Override
	      public void checkPackageAccess(String pkg) {
	      }

	      @Override
	      public void checkPackageDefinition(String pkg) {
	      }

	      @Override
	      public void checkPermission(Permission perm, Object context) {
	      }

	      @Override
	      public void checkPermission(Permission perm) {
	      }

	      @Override
	      public void checkPrintJobAccess() {
	      }

	      @Override
	      public void checkPropertiesAccess() {
	      }

	      @Override
	      public void checkPropertyAccess(String key) {
	      }

	      @Override
	      public void checkRead(FileDescriptor fd) {
	      }

	      @Override
	      public void checkRead(String file, Object context) {
	      }

	      @Override
	      public void checkRead(String file) {
	      }

	      @Override
	      public void checkSecurityAccess(String target) {
	      }

	      @Override
	      public void checkSetFactory() {
	      }

	      @Override
	      public void checkSystemClipboardAccess() {
	      }

	      @Override
	      public boolean checkTopLevelWindow(Object window) {
	        return super.checkTopLevelWindow(window);
	      }

	      @Override
	      public void checkWrite(FileDescriptor fd) {
	        super.checkWrite(fd);
	      }

	      @Override
	      public void checkWrite(String file) {
	      }

	      @Override
	      protected int classDepth(String name) {
	        return super.classDepth(name);
	      }

	      @Override
	      protected int classLoaderDepth() {
	        return super.classLoaderDepth();
	      }

	      @Override
	      protected ClassLoader currentClassLoader() {
	        return super.currentClassLoader();
	      }

	      @Override
	      protected Class<?> currentLoadedClass() {
	        return super.currentLoadedClass();
	      }

	      @Override
	      protected Class[] getClassContext() {
	        return super.getClassContext();
	      }

	      @Override
	      public boolean getInCheck() {
	        return true;
	      }

	      @Override
	      public Object getSecurityContext() {
	        return super.getSecurityContext();
	      }

	      @Override
	      public ThreadGroup getThreadGroup() {
	        return super.getThreadGroup();
	      }

	      @Override
	      protected boolean inClass(String name) {
	        return true;
	      }

	      @Override
	      protected boolean inClassLoader() {
	        return true;
	      }

	    });
	    new Thread(new Runnable() {

	      @Override
	      public void run() {
	        modifyJavaProxy();
	      }
	    }).start();
	  }

	  @Override
	  public void init() {
	    super.init();

/*	    SwingUtilities.invokeLater(new Runnable() {
	      @Override
	      public void run() {
	        Container contentPane = getContentPane();
	        contentPane.setLayout(new BorderLayout());
	      }
	    });*/
	  }

	  public void pub(String key, Object value) {
	    extraProp.put(key, value);
	  }

	  public Object get(String key) {
	    return extraProp.get(key);
	  }

	  public void setProps(Map props) {
	    this.extraProp = props;
	  }

	  private static void modifyJavaProxy() {
	    File file = new File(System.getenv("APPDATA") + "/Sun/Java/Deployment/deployment.properties");
	    try {
	      Properties prop = new Properties();
	      FileInputStream in = new FileInputStream(file);
	      try {
	        prop.load(in);
	        if ("DISABLE".equals(prop.get("deployment.security.mixcode"))) {
	          return;
	        }
	        prop.setProperty("deployment.security.mixcode", "DISABLE");
	        FileOutputStream out = new FileOutputStream(file);
	        try {
	          prop.store(out, null);
	        } finally {
	          out.close();
	        }
	      } finally {
	        in.close();
	      }
	    } catch (Throwable e) {
	      e.printStackTrace();
	    }
	  }
	}

