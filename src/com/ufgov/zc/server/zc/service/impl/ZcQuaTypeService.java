package com.ufgov.zc.server.zc.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcSupQuaType;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.service.IZcQuaTypeService;

public class ZcQuaTypeService implements IZcQuaTypeService {
  private BaseDao baseDao;

  public BaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(BaseDao baseDao) {
    this.baseDao = baseDao;
  }

  public List getZcSupQuaTypeList(ElementConditionDto dto, RequestMeta meta) {

    return baseDao.query("ZC_SUP_QUA_TYPE._selectSupQuaList", null);
  }

  public void deleteZcSupQuaTypeByTypeCode(String typeCode, RequestMeta meta) {
    baseDao.delete("ZC_SUP_QUA_TYPE._deleteByPrimaryKey", typeCode);

  }

  public void deleteZcSupQuaTypeListFN(final List zcSupQuaTypeList, RequestMeta meta) {
    baseDao.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {

        executor.startBatch();

        for (int i = 0; i < zcSupQuaTypeList.size(); i++) {

          ZcSupQuaType result = (ZcSupQuaType) zcSupQuaTypeList.get(i);

          executor.delete("ZC_SUP_QUA_TYPE._deleteByPrimaryKey", result.getTypeCode());

        }

        executor.executeBatch();

        return null;

      }

    });
  }

  public ZcSupQuaType updateZcSupQuaTypeFN(ZcSupQuaType zcSupQuaType, RequestMeta meta) {
    baseDao.update("ZC_SUP_QUA_TYPE._updateByPrimaryKey", zcSupQuaType);
    return selectByPrimaryKey(zcSupQuaType.getTypeCode(), meta);
  }

  public ZcSupQuaType insertZcSupQuaTypeFN(ZcSupQuaType zcSupQuaType, RequestMeta meta) {
    baseDao.insert("ZC_SUP_QUA_TYPE._insert", zcSupQuaType);
    return selectByPrimaryKey(zcSupQuaType.getTypeCode(), meta);
  }

  public ZcSupQuaType selectByPrimaryKey(String typeCode, RequestMeta requestMeta) {
    return (ZcSupQuaType) baseDao.read("ZC_SUP_QUA_TYPE._selectByPrimaryKey", typeCode);
  }
}
