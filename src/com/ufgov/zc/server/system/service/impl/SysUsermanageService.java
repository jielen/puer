package com.ufgov.zc.server.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.console.model.AsUserGroup;
import com.ufgov.zc.common.console.model.SysUserOrg;
import com.ufgov.zc.common.console.model.SysUserRoleRule;
import com.ufgov.zc.common.console.model.SysUsermanage;
import com.ufgov.zc.common.system.constants.ZcElementConstants;
import com.ufgov.zc.common.system.exception.BusinessException;
import com.ufgov.zc.server.system.service.ISysUsermanageService;
import com.ufgov.zc.server.system.util.AsOptionUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao;

public class SysUsermanageService implements ISysUsermanageService {

	private IBaseDao baseDao;

	/**
	 * @return the baseDao
	 */
	public IBaseDao getBaseDao() {
		return baseDao;
	}

	/**
	 * @param baseDao
	 *            the baseDao to set
	 */
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void addSysUsermanage(SysUsermanage sysUsermanage, String orgId,
			String groupId, String roleId, int nd) throws BusinessException {

		if (sysUsermanage == null || sysUsermanage.getUserId() == null) {
			throw new BusinessException("sysUsermanage为空，或者userId为空");
		}

		if (groupId == null || "".equals(groupId)) {
			throw new BusinessException("groupId为空");
		}
		if (roleId == null || "".equals(roleId)) {
			throw new BusinessException("roleId为空");
		}

		/**
		 * 组建AsUserGroup对象
		 */
		AsUserGroup userGroup = new AsUserGroup();
		userGroup.setUserId(sysUsermanage.getUserId());
		userGroup.setGroupId(groupId);
		/**
		 * 组建SysUserRoleRule对象
		 */

		SysUserRoleRule sysUserRoleRule = new SysUserRoleRule();
		sysUserRoleRule.setUserId(sysUsermanage.getUserId());
		sysUserRoleRule.setSetYear(new Short(Short.parseShort(nd + "")));
		sysUserRoleRule.setRoleId(new BigDecimal(roleId));
		sysUserRoleRule.setIsDefined(Short.valueOf("0"));// 这个值不知道做什么用，怎么设置，默认一个值
		sysUserRoleRule.setRgCode(AsOptionUtil.getInstance().getOptionVal(
				ZcElementConstants.OPT_ZC_RG_CODE));
		// sysUserRoleRule.setRuleId(ruleId)
		/**
		 * 组建SysUserOrg对象,分配用户所属于组的权限，可以分配，也可以不分配
		 */
		if (orgId != null && !"".equals(orgId)) {
			SysUserOrg sysUserOrg = new SysUserOrg();
			sysUserOrg.setUserId(sysUsermanage.getUserId());
			sysUserOrg.setOrgId(orgId);
			baseDao.insert("SysUserOrg.abatorgenerated_insert", sysUserOrg);
		}

		// 数据库中插入用户

		baseDao.insert("SysUserManage.abatorgenerated_insert", sysUsermanage);
		baseDao.insert("SysUserRoleRule.abatorgenerated_insert",
				sysUserRoleRule);
		baseDao.insert("User.insertAsUserGroup", userGroup);

	}

	public void addSysUsermanage(SysUsermanage sysUsermanager,
			SysUserOrg sysUserOrg, SysUserRoleRule sysUserRoleRule,
			AsUserGroup asUserGroup) throws BusinessException {
		baseDao.insert("SysUserManage.abatorgenerated_insert", sysUsermanager);
		baseDao.insert("SysUserOrg.abatorgenerated_insert", sysUserOrg);
		baseDao.insert("SysUserRoleRule.abatorgenerated_insert",
				sysUserRoleRule);
		baseDao.insert("User.insertAsUserGroup", asUserGroup);
	}

	public void deleteSysUsermanage(String userId) {
		baseDao.delete("SysUserManage.abatorgenerated_deleteByPrimaryKey",
				userId);
		baseDao.delete("SysUserOrg.abatorgenerated_deleteByPrimaryKey", userId);// sqlMap要进行修改
		baseDao.delete("SysUserRoleRule.abatorgenerated_deleteByPrimaryKey",
				userId);// sqlMap要进行修改
		baseDao.delete("User.deleteAsUserGroupByUserId", userId);
	}

	public void updateSysUsermanage(SysUsermanage sysUsermanager,
			SysUserOrg sysUserOrg, SysUserRoleRule sysUserRoleRule,
			AsUserGroup asUserGroup) throws BusinessException {
		baseDao.update(
				"SysUserManage.abatorgenerated_updateByPrimaryKeySelective",
				sysUsermanager);
		baseDao.update("SysUserOrg.abatorgenerated_updateByPrimaryKey",
				sysUserOrg);// sqlMap要进行修改
		baseDao.update(
				"SysUserRoleRule.abatorgenerated_updateByPrimaryKeySelective",
				sysUserRoleRule);// sqlMap要进行修改
		baseDao.update("User.updateAsUserGroupByUserId", asUserGroup);
	}

	public void updateAsEmpLogin(String userId, boolean isAllowLogin)
			throws BusinessException {
		// TCJLODO Auto-generated method stub

	}

	public List getUserByCaSerial(String userId) {
		// TCJLODO Auto-generated method stub
		return baseDao.query("User.getUserByCAId", userId);
	}

	public int updateUserCaSerial(SysUsermanage sysUsermanager)
			throws BusinessException {
		return baseDao.update("SysUserManage.updateCaSerial", sysUsermanager);
	}
}
