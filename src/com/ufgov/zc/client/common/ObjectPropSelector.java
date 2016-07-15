package com.ufgov.zc.client.common;

import com.ufgov.zc.common.system.util.ObjectUtil;


public interface ObjectPropSelector {
  public Object select(Object o, int propIndex, String[] propNames);

  public void update(Object o, Object value, int propIndex, String[] propNames);

  public static class DefaultPropSelector implements ObjectPropSelector {

    public Object select(Object o, int propIndex, String[] propNames) {
      if (o == null) {
        return "";
      } else {
        String propName = propNames[propIndex];
        Object value = ObjectUtil.getProperty(o, propName);
        return value;
      }
    }

    public void update(Object o, Object value, int propIndex, String[] propNames) {
      if (o == null)
        return;
      String propName = propNames[propIndex];
      ObjectUtil.setProperty(o, propName, value);
    }

  }
}
