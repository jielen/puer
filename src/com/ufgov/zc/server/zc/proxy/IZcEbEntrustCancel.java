package com.ufgov.zc.server.zc.proxy;

public interface IZcEbEntrustCancel {

  /**
   * 任务取消调用方法，该方法包含：
   *   保存原业务数据状态及工作流状态、
   *   设置业务数据状态为取消、
   *   删除业务数据关联的待审状态
   * @param o
   * @return
   */
  public Object entrustCancel(Object o);

  /**
   * 任务恢复调用方法，该方法包含：
   *   恢复业务数据状态、
   *   恢复工作流状态
   *   删除取消时的备份
   * @param o
   * @return
   */
  public Object entrustRecovery(Object o);
}
