package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfDocSendMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfDocSendMapperImp  extends SqlMapClientDaoSupport implements SfDocSendMapper {

  public int deleteByPrimaryKey(BigDecimal sendId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfDocSendMapper.deleteByPrimaryKey", sendId);

  }

  public int insert(SfDocSend record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfDocSendMapper.insert", record);
    return 1;
  }

  public int insertSelective(SfDocSend record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public SfDocSend selectByPrimaryKey(BigDecimal sendId) {
    // TCJLODO Auto-generated method stub
    return (SfDocSend) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfDocSendMapper.selectByPrimaryKey", sendId);
  }

  public int updateByPrimaryKeySelective(SfDocSend record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public int updateByPrimaryKey(SfDocSend record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfDocSendMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfDocSendMapper.selectMainDataLst", elementConditionDto);
  }
}
