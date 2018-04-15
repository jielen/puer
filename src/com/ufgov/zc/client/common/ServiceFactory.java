package com.ufgov.zc.client.common;

public class ServiceFactory {

  public static boolean IS_REMOTE = true;

  public static Object create(Class c, String serviceName) {


    return RemoteServiceFactory.create(c, serviceName);

  }

}
