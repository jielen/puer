package com.ufgov.zc.client.applet.local;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;

public class ClassLoaderUtil {

	  private static Method addURL = initAddMethod();

	  /** 初始化方法 */
	  private static final Method initAddMethod() {
	    try {
	      Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
	      add.setAccessible(true);
	      return add;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }

	  private static URLClassLoader system = (URLClassLoader) ClassLoader.getSystemClassLoader();

	  /**  
	   * 循环遍历目录，找出所有的JAR包  
	   */
	  private static final void loopFiles(File file, List<File> files) {
	    if (file.isDirectory()) {
	      File[] tmps = file.listFiles();
	      for (File tmp : tmps) {
	        loopFiles(tmp, files);
	      }
	    } else {
	      if (file.getAbsolutePath().endsWith(".jar") || file.getAbsolutePath().endsWith(".zip")) {
	        files.add(file);
	      }
	    }
	  }

	  /**  
	   * <pre>  
	   * 加载JAR文件  
	   * </pre>  
	   *  
	   * @param file  
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws MalformedURLException 
	 * @throws IllegalArgumentException 
	   */
	  private   void loadJarFile(File file) throws Exception {
	      addURL.invoke(system, new Object[] { file.toURI().toURL() });
	      System.out.println("加载JAR包：" + file.getAbsolutePath());
	  }

	  /**  
	   * <pre>  
	   * 从一个目录加载所有JAR文件  
	   * </pre>  
	   *  
	   * @param path  
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws MalformedURLException 
	 * @throws IllegalArgumentException 
	   */
	  public synchronized  void loadJarPath(String path) throws Exception {
	    List<File> files = new ArrayList<File>();
	    File lib = new File(path);
	    loopFiles(lib, files);
	    for (File file : files) {
	      loadJarFile(file);
	    }
	  }

	  public static void main(String[] args) {
	    ClassLoaderUtil lu = new ClassLoaderUtil();
	    try {
			lu.loadJarPath("D:\\workplace\\eclipseWorkplace\\JilinApplet\\tesLib");
		} catch (Exception e) {
			// TCJLODO Auto-generated catch block
			e.printStackTrace();
		} 
	    lu.testClass(); 
	  }
	  
	  void testClass(){

		    String className="com.ufgov.zc.server.zc.util.GeneralFunc";
		    String pass="123456";
		  try {
	            Class c = Class.forName(className);
	            System.out.println("类名称：" + c.getName());
	            System.out.println("是否为接口：" + c.isInterface());
	            System.out.println("是否为基本类型：" + c.isPrimitive());
	            System.out.println("是否为数组：" + c.isArray());
	            System.out.println("父类：" + c.getSuperclass().getName());
	            
	            Object[] args=new Object[]{pass};
	            
	            String _encodePwd2="_encodePwd2",recodePwd="recodePwd";
	            
	            Class[] argsClass = new Class[args.length]; 	            
	            for (int i = 0, j = args.length; i < j; i++) {  
	                argsClass[i] = args[i].getClass();  
	            } 
	            Method method = c.getMethod(_encodePwd2,argsClass);
	            Object rtn=method.invoke(c, args);
	            System.out.println("_encodePwd2：" + rtn.toString());
	            
	            args=new Object[]{rtn};
	            argsClass = new Class[args.length]; 	            
	            for (int i = 0, j = args.length; i < j; i++) {  
	                argsClass[i] = args[i].getClass();  
	            }  
	            method = c.getMethod(recodePwd,argsClass);
	            rtn=method.invoke(c, args);
	            System.out.println("recodePwd：" + rtn.toString());
	            
	        } catch (ArrayIndexOutOfBoundsException e) {
	            System.out.println("没有指定类名称");
	        } catch (ClassNotFoundException e) {
	            System.out.println("找不到指定的类");
	        } catch (SecurityException e) {
				// TCJLODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TCJLODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TCJLODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TCJLODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TCJLODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
}
