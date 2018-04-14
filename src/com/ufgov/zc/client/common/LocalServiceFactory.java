package com.ufgov.zc.client.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ufgov.zc.server.system.SpringContext;

public class LocalServiceFactory {
  private static ApplicationContext context;

  private static Map localServiceMap = new HashMap();

  public synchronized static Object create(String serviceName) {
    Object service = null;
    if (context == null) {
      String[] xmls = { "applicationContext_gk_base.xml", "applicationContext_gk_secure.xml",
        "applicationContext_zc.xml", "applicationContext_gk.xml",
        "applicationContext_zc_delegate.xml", "applicationContext_gk_delegate.xml",
        "applicationContext_wfengine.xml","applicationContext_sf.xml",  "applicationContext_sf_delegate.xml"};
      context = new ClassPathXmlApplicationContext(xmls);
      SpringContext.setSpringContext(context);
      String path = SpringContext.class.getResource(".").toString();
      PropertyConfigurator.configureAndWatch(path.substring(6, path.indexOf("/build/") + 15) + "log4j.properties", 50);
    }

    if (localServiceMap.get(serviceName) == null) {
      service = context.getBean(serviceName);
      localServiceMap.put(serviceName, service);
    } else {
      service = localServiceMap.get(serviceName);
    }

    return service;
  }
}
