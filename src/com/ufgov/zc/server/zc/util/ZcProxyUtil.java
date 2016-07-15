package com.ufgov.zc.server.zc.util;

import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ZcProxyUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TCJLODO Auto-generated method stub
		this.context = arg0;
	}

	/**
	 * 
	 * @param cName
	 *            spring中定义的bean的id
	 * @param mName
	 *            方法名称
	 * @param para
	 *            参数
	 * @throws Exception
	 */
	public static void proxy(List cName, List mName, List para)
			throws Exception {
		for (int i = 0; i < cName.size(); i++) {

			MethodUtils.invokeMethod(context.getBean((String) cName.get(i)),
					(String) mName.get(i), para.get(i));

		}
	}

	/**
	 * 
	 * @param cName
	 *            spring中定义的bean的id
	 * @param mName
	 *            方法名称
	 * @param para
	 *            参数
	 * @throws Exception
	 */
	public static void proxy(List cName, String mName, List para)
			throws Exception {
		for (int i = 0; i < cName.size(); i++) {

			MethodUtils.invokeMethod(context.getBean((String) cName.get(i)),
					mName, para.get(i));

		}
	}

	/**
	 * 
	 * @param cName
	 *            spring中定义的bean的id
	 * @param mName
	 *            方法名称
	 * @param para
	 *            参数
	 * @throws Exception
	 */
	public static void proxy(List cName, String mName, Object para)
			throws Exception {
		for (int i = 0; i < cName.size(); i++) {

			MethodUtils.invokeMethod(context.getBean((String) cName.get(i)),
					mName, para);

		}
	}

	/**
	 * 
	 * @param o
	 *            类
	 * @param mName
	 *            方法名称
	 * @param para
	 *            参数
	 * @throws Exception
	 */
	public static void proxyC(List o, List mName, List para) throws Exception {
		for (int i = 0; i < o.size(); i++) {

			MethodUtils.invokeMethod(o, (String) mName.get(i), para.get(i));

		}
	}

	/**
	 * 
	 * @param o
	 *            类
	 * @param mName
	 *            方法名称
	 * @param para
	 *            参数
	 * @throws Exception
	 */
	public static void proxyC(List o, String mName, List para) throws Exception {
		for (int i = 0; i < o.size(); i++) {

			MethodUtils.invokeMethod(o, mName, para.get(i));

		}
	}

	/**
	 * 
	 * @param o
	 *            类
	 * @param mName
	 *            方法名称
	 * @param para
	 *            参数
	 * @throws Exception
	 */
	public static void proxyC(List o, String mName, Object para)
			throws Exception {
		for (int i = 0; i < o.size(); i++) {

			MethodUtils.invokeMethod(o, mName, para);

		}
	}
}
