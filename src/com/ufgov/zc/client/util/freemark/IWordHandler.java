/**
 * 
 */
package com.ufgov.zc.client.util.freemark;

import java.util.Hashtable;

/**
 * 
 * @author Administrator
 *
 */
public interface IWordHandler {

  public String FILE_NAME = "targetFileName";

  /**
   * 创建word文件，返回文件路径
   * @param userDatas 需要填充的数据
   * @return
   */
  String createDocumnet(Hashtable userDatas);
}
