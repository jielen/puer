package com.ufgov.zc.server.zc.dao.ibatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbEntrust;
import com.ufgov.zc.common.zc.model.ZcEbEntrustDetail;
import com.ufgov.zc.common.zc.model.ZcPProMitemBi;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.zc.dao.IZcEbEntrustDao;

public class ZcEbEntrustDao extends SqlMapClientDaoSupport implements IZcEbEntrustDao {

  public List getZcEbEntrust(ElementConditionDto dto, RequestMeta meta) {
    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));
    return this.getSqlMapClientTemplate().queryForList("ZcEbEntrust.getZcEbEntrust", dto);
  }

  public List getZcEbEntrustDetailBySn(String sn) {
    return this.getSqlMapClientTemplate().queryForList("ZcEbEntrust.getZcEbEntrustDetailBySn", sn);
  }

  public void updateZcEbEntrustStatus(ZcEbEntrust entrust) {
    this.getSqlMapClientTemplate().update("ZcEbEntrust.updateZcEbEntrustStatus", entrust);
  }

  public void updateZcEbEntrustStatus(final List entrustList) {
    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
        executor.startBatch();
        for (int i = 0; i < entrustList.size(); i++) {
          executor.update("ZcEbEntrust.updateZcEbEntrustStatus", entrustList.get(i));
        }
        executor.executeBatch();
        return null;
      }
    });
  }

  public List getOriginZcEbEntrustByIdList(List idList) {
    if (idList.isEmpty()) {
      return new ArrayList();
    }
    Map param = new HashMap();
    param.put("idList", idList);
    return this.getSqlMapClientTemplate().queryForList("ZcEbEntrust.getOriginZcEbEntrustByIdList", param);
  }

  public List getZcEbEntrustDetailList(List zcEbEntrustIdList) {
    if (zcEbEntrustIdList.isEmpty()) {
      return new ArrayList();
    }
    Map param = new HashMap();
    param.put("idList", zcEbEntrustIdList);
    return this.getSqlMapClientTemplate().queryForList("ZcEbEntrust.getZcEbEntrustDetailList", param);
  }

  public ZcEbEntrust getZcEbEntrustBySn(String sn) {
    return (ZcEbEntrust) this.getSqlMapClientTemplate().queryForObject("ZcEbEntrust.getZcEbEntrustBySn", sn);
  }

  public ZcEbEntrust getZcEbEntrustByMakeCode(String zcMakeCode) {
    return (ZcEbEntrust) this.getSqlMapClientTemplate().queryForObject("ZcEbEntrust.getZcEbEntrustByMakeCode", zcMakeCode);
  }

  public void deleteByMakeCode(String zcMakeCode) {
    this.getSqlMapClientTemplate().delete("ZcEbEntrust.deleteByMakeCode", zcMakeCode);
  }

  public void insertZcEbEntrust(final ZcEbEntrust entrust) {

    this.getSqlMapClientTemplate().insert("ZcEbEntrust.insertZcEbEntrust", entrust);

    for (int i = 0; i < entrust.getDetailList().size(); i++) {
      ZcEbEntrustDetail p = (ZcEbEntrustDetail) entrust.getDetailList().get(i);
      insertZcEbEntrustDetail(p);
    }

    for (int i = 0; i < entrust.getBiList().size(); i++) {
      ZcPProMitemBi bi = (ZcPProMitemBi) entrust.getBiList().get(i);
      insertZcEbEntrustBiList(bi);
    }
    //insert the detail data
    //  this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
    //    public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
    //      executor.startBatch();
    //      for (int i = 0; i < entrust.getDetailList().size(); i++) {
    //        ZcEbEntrustDetail p=(ZcEbEntrustDetail)entrust.getDetailList().get(i);
    //        executor.insert("ZcEbEntrust.insertZcEbEntrustDetail", p);
    //      }
    //      executor.executeBatch();
    //      return null;
    //    }
    //  });

  }

  public void insertZcEbEntrustDetail(ZcEbEntrustDetail detail) {
    this.getSqlMapClientTemplate().insert("ZcEbEntrust.insertZcEbEntrustDetail", detail);
  }

  public void insertZcEbEntrustBiList(ZcPProMitemBi bi) {
    this.getSqlMapClientTemplate().insert("ZC_P_PRO_MITEM_BI.ibatorgenerated_insert", bi);
  }

  public void updateZcEbEntrust(ZcEbEntrust entrust) {

    this.getSqlMapClientTemplate().update("ZcEbEntrust.updateZcEbEntrust", entrust);

    //删插任务明细的信息
    this.getSqlMapClientTemplate().delete("ZcEbEntrust.deleteZcEbEntrustDetail", entrust.getSn());

    for (int i = 0; i < entrust.getDetailList().size(); i++) {
      ZcEbEntrustDetail detail = (ZcEbEntrustDetail) entrust.getDetailList().get(i);
      detail.setSn(entrust.getSn());
      detail.setSnd(entrust.getSn() + "_" + (i + 1));
    }
    for (int i = 0; i < entrust.getDetailList().size(); i++) {
      ZcEbEntrustDetail p = (ZcEbEntrustDetail) entrust.getDetailList().get(i);
      this.insertZcEbEntrustDetail(p);
    }
    //  删插资金构成的信息
    this.getSqlMapClientTemplate().delete("ZcEbEntrust.deleteZcEbEntrustDetaiBi", entrust.getZcMakeCode());

    for (int i = 0; i < entrust.getBiList().size(); i++) {
      ZcPProMitemBi bi = (ZcPProMitemBi) entrust.getBiList().get(i);
      bi.setZcProBiSeq(entrust.getSn() + "_" + (i + 1));
      bi.setZcMakeCode(entrust.getZcMakeCode());
    }
    for (int i = 0; i < entrust.getBiList().size(); i++) {
      ZcPProMitemBi bi = (ZcPProMitemBi) entrust.getBiList().get(i);
      insertZcEbEntrustBiList(bi);
    }

  }

  public int getCountData(String sn) {
    return ((Integer) this.getSqlMapClientTemplate().queryForObject("ZcEbEntrust.getCountData", sn)).intValue();
  }

  public int deleteByPrimaryKey(String sn) {
    return this.getSqlMapClientTemplate().delete("ZcEbEntrust.deleteByPrimaryKey", sn);
  }

  public int deleteDetalBySn(String sn) {
    return this.getSqlMapClientTemplate().delete("ZcEbEntrust.deleteZcEbEntrustDetail", sn);
  }

  public boolean checkUniqueZcMakeCode(ZcEbEntrust zeas, RequestMeta meta) {
    boolean isUnique = true;
    ZcEbEntrust zcEbEntrust = (ZcEbEntrust) this.getSqlMapClientTemplate().queryForObject("ZcEbEntrust.getZcEbEntrustByMakeCode",
      zeas.getZcMakeCode());
    if (zcEbEntrust != null && !zcEbEntrust.getSn().equals(zeas.getSn())) {
      isUnique = false;
    }
    return isUnique;
  }

}
