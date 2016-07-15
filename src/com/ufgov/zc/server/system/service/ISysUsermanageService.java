package com.ufgov.zc.server.system.service;

import java.util.List;

import com.ufgov.zc.common.console.model.AsUserGroup;
import com.ufgov.zc.common.console.model.SysUserOrg;
import com.ufgov.zc.common.console.model.SysUserRoleRule;
import com.ufgov.zc.common.console.model.SysUsermanage;
import com.ufgov.zc.common.system.exception.BusinessException;

public interface ISysUsermanageService {

  void addSysUsermanage(SysUsermanage sysUsermanager, SysUserOrg sysUserOrg, SysUserRoleRule sysUserRoleRule, AsUserGroup asUserGroup)
    throws BusinessException;

  void deleteSysUsermanage(String userId);

  void addSysUsermanage(SysUsermanage sysUsermanage, String orgId, String groupId, String roleId, int nd) throws BusinessException;

  void updateSysUsermanage(SysUsermanage sysUsermanager, SysUserOrg sysUserOrg, SysUserRoleRule sysUserRoleRule, AsUserGroup asUserGroup)
    throws BusinessException;

  /*
   * 此处待定，没有确定修改SYS_USERMANAGE的那个字段
   */
  void updateAsEmpLogin(String userId, boolean isAllowLogin) throws BusinessException;

  /*
   * 以前是从AS_emp表中取数，现在要在SYS_USERMANAGE表中取数
   */
  public List getUserByCaSerial(String userId);

  /*
   * 将CA序列窜维护到SYS_USERMANAGE表中 CA_SERIAL字段中
   */
  public int updateUserCaSerial(SysUsermanage sysUsermanager) throws BusinessException;

}
