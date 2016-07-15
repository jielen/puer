package com.ufgov.zc.server.zc.dao.ibatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbProjPrintPermit;
import com.ufgov.zc.common.zc.model.ZcEbProjSupport;
import com.ufgov.zc.common.zc.model.ZcEbSupplier;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.zc.dao.IZcEbEntrustBulletinDao;

public class ZcEbEntrustBulletinDao extends SqlMapClientDaoSupport implements IZcEbEntrustBulletinDao {

  /**
   * <p> 获取已下达的采购任务 </p>
   * @param dto
   * @return
   * @see com.ufgov.zc.server.zc.dao.IZcEbEntrustBulletinDao#getZcEbEntrustBull(com.ufgov.zc.common.system.dto.ElementConditionDto)
   * @author yuzz
   * @since Sep 15, 2012 9:16:47 AM
   */
  public List getZcEbEntrustBull(ElementConditionDto dto, RequestMeta meta) {

    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));

    return this.getSqlMapClientTemplate().queryForList("ZcEbEntrustBull.getZcEbEntrustBullList", dto);
  }

  /**
   * <p> 获取已上网的采购任务 </p>
   * @param dto
   * @return
   * @see com.ufgov.zc.server.zc.dao.IZcEbEntrustBulletinDao#getZcEbEntrustBullin(com.ufgov.zc.common.system.dto.ElementConditionDto)
   * @author yuzz
   * @since Sep 15, 2012 9:17:16 AM
   */
  public List getZcEbEntrustBullin(ElementConditionDto dto, RequestMeta meta) {

    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));

    return this.getSqlMapClientTemplate().queryForList("ZcEbEntrustBull.getZcEbEntrustBullinList", dto);
  }

  /**
   * <p> 获取已完成的采购任务 </p>
   * @param dto
   * @return
   * @see com.ufgov.zc.server.zc.dao.IZcEbEntrustBulletinDao#getZcEbEntrustReport(com.ufgov.zc.common.system.dto.ElementConditionDto)
   * @author yuzz
   * @since Sep 15, 2012 9:17:22 AM
   */
  public List getZcEbEntrustReport(ElementConditionDto dto, RequestMeta meta) {

    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));

    return this.getSqlMapClientTemplate().queryForList("ZcEbEntrustBull.getZcEbEntrustReportList", dto);
  }

  /**
   * <p> 获取招标项目情况 </p>
   * @param dto
   * @return
   * @see com.ufgov.zc.server.zc.dao.IZcEbEntrustBulletinDao#getZcEbProjectSupport(com.ufgov.zc.common.system.dto.ElementConditionDto)
   * @author yuzz
   * @since Sep 19, 2012 3:10:05 PM
   */
  public List getZcEbProjectSupport(ElementConditionDto dto, RequestMeta meta) {

    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));

    return this.getSqlMapClientTemplate().queryForList("ZcEbEntrustBull.getZcEbProjSupportList", dto);
  }

  public void deletePrintPermit(ZcEbProjPrintPermit proj) {
    this.getSqlMapClientTemplate().delete("ZcEbEntrustBull.deletePrintPermit", proj);
  }

  public void insertPrintPermit(ZcEbProjPrintPermit proj) {
    this.getSqlMapClientTemplate().insert("ZcEbEntrustBull.insertPrintPermit", proj);
  }

  public void updatePrintPermit(ZcEbProjPrintPermit proj) {
    this.getSqlMapClientTemplate().update("ZcEbEntrustBull.updatePrintPermit", proj);
  }

  public List getZcEbProjPrintPermit(ZcEbProjPrintPermit proj) {
    ElementConditionDto dto = new ElementConditionDto();
    dto.setProjCode(proj.getProjCode());
    return this.getSqlMapClientTemplate().queryForList("ZcEbEntrustBull.getZcEbProjPrintPermitList", dto);
  }

  public void updateSupplierIsSite(final List zcEbProjSupportList) {

    for (int i = 0; i < zcEbProjSupportList.size(); i++) {

      ZcEbProjSupport result = (ZcEbProjSupport) zcEbProjSupportList.get(i);
      System.out.println(result.getZcEbSupplier().getCode());

      this.getSqlMapClientTemplate().update("ZcEbEntrustBull.updateZcEbSignupIsSite", result);

    }

  }

  /**
  * <p>Description：判断冻结供应商</p>
  * @param zcEbProjSupportList
  * @return
  * @see com.ufgov.zc.server.zc.dao.IZcEbEntrustBulletinDao#frozenZcBSupplier(java.util.List)
  * @author yuzz
  * @since Nov 23, 2012 2:42:09 PM
  */
  public List frozenZcBSupplier(List zcEbProjSupportList) {

    List frozenLst = new ArrayList();

    for (int i = 0; i < zcEbProjSupportList.size(); i++) {

      ZcEbProjSupport result = (ZcEbProjSupport) zcEbProjSupportList.get(i);

      if (ZcSettingConstants.IS_SITE_N.equals(result.getIsSite())) {

        List lst = findZcEbSignupSite(result);

        if (lst.size() != 0 && lst.size() % 3 == 0) {

          ZcEbSupplier sup = new ZcEbSupplier();

          sup.setCode(result.getZcEbSupplier().getCode());

          sup.setZcAuditDate(new Date());

          sup.setStatus(ZcSettingConstants.FROZEN_SUPPLIER_STATUS);

          updateZcBSupplier(sup);

          frozenLst.add(result);

        }

      }

    }

    return frozenLst;
  }

  public List findZcEbSignupSite(ZcEbProjSupport proj) {

    return this.getSqlMapClientTemplate().queryForList("ZcEbSignup.getSignupSiteList", proj);

  }

  public void updateZcBSupplier(ZcEbSupplier sup) {

    this.getSqlMapClientTemplate().update("ZcEbSupplier.updateSupplierStatus", sup);

  }

  public List getZcEbPackHistory(ElementConditionDto dto, RequestMeta meta) {
    // TCJLODO Auto-generated method stub

    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));

    return this.getSqlMapClientTemplate().queryForList("ZcEbEntrustBull.getHistoryList", dto);
  }

}
