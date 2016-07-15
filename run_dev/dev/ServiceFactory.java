package com.ufgov.zc.client.common;

public class ServiceFactory {

  public static boolean IS_REMOTE = false;

  public static Object create(Class c, String serviceName) {

    if (!IS_REMOTE) {
      return LocalServiceFactory.create(serviceName);
    }

    return RemoteServiceFactory.create(c, serviceName);
  }

}