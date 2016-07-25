package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdReportMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfJdReportMapperImp extends SqlMapClientDaoSupport implements SfJdReportMapper {

	
	public int deleteByPrimaryKey(BigDecimal jdReporId) {
	    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfJdReportMapper.deleteByPrimaryKey", jdReporId);
	}

	
	public int insert(SfJdReport record) {
	    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfJdReportMapper.insert", record);
	    return 1;
	}

	
	public SfJdReport selectByPrimaryKey(BigDecimal jdReporId) {

	    return (SfJdReport) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfJdReportMapper.selectByPrimaryKey", jdReporId);
	}

	
	public int updateByPrimaryKey(SfJdReport record) {
	    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfJdReportMapper.updateByPrimaryKey", record);
	}

	
	public List getMainDataLst(ElementConditionDto elementConditionDto) {

	    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

	    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfJdReportMapper.selectMainDataLst", elementConditionDto);

	}

}
