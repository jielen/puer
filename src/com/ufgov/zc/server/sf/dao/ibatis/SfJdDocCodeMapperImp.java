/**
 * 
 */
package com.ufgov.zc.server.sf.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfJdDocCode;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

/**
 * @author Administrator
 *
 */
public class SfJdDocCodeMapperImp extends SqlMapClientDaoSupport implements SfJdDocCodeMapper {

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper#deleteByPrimaryKey(java.lang.String)
	 */
	
	public int deleteByPrimaryKey(String pinJieCode) {

	    return this.getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper.deleteByPrimaryKey", pinJieCode);
	}

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper#insert(com.ufgov.zc.common.sf.model.SfJdDocCode)
	 */
	
	public int insert(SfJdDocCode record) {
	     getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper.insert",record);
	     return 1;
	}

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper#selectByPrimaryKey(java.lang.String)
	 */
	
	public SfJdDocCode selectByPrimaryKey(String pinJieCode) {
		return (SfJdDocCode) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper.selectByPrimaryKey", pinJieCode);
		  
	}

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper#updateByPrimaryKey(com.ufgov.zc.common.sf.model.SfJdDocCode)
	 */
	
	public int updateByPrimaryKey(SfJdDocCode record) {

	    getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper.updateByPrimaryKey", record);
	    return 1;
	}

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper#getSfJdDocCodeLst(com.ufgov.zc.common.system.dto.ElementConditionDto)
	 */
	
	public List getSfJdDocCodeLst(ElementConditionDto elementConditionDto) {
		 elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

		    List list = getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper.getSfJdDocCodeLst", elementConditionDto);

		    return list;
	}

}
