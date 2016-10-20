package com.ufgov.zc.client.component.zc.fieldeditor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForeignEntityDataCache {

  private static Map<String, List> dataListMap = new HashMap<String, List>();

  public static Map getDataMap() {

    if (dataListMap == null) {

      dataListMap = new HashMap<String, List>();

    }

    return dataListMap;

  }

  public static void setData(String key, List dataList) {

    dataListMap.put(key, dataList);

  }

  public static String CACHE_ZC_B_CATALOGUE = "cacheZcBCatalogue";

  public static String CACHE_ZC_B_CATALOGUE_XYCATALOGUE = "cacheZcBCatalogue_XYCatalogue";

  public static String CACHE_ZC_EB_QUALIFICATION = "cacheZcEbQualification";

  //资产类别
  public static String CACHE_ZC_FA_TYPE = "CACHE_ZC_FA_TYPE";

}
