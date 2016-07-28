/**
 * 
 */
package com.ufgov.zc.server.sf.service.impl;

import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfJdDocCode;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper;
import com.ufgov.zc.server.sf.service.ISfJdDocCodeService;

/**
 * @author Administrator
 *
 */
public class SfJdDocCodeService implements ISfJdDocCodeService {
	
	private SfJdDocCodeMapper jdDocCodeMapper;

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.service.ISfJdDocCodeService#getMainDataLst(com.ufgov.zc.common.system.dto.ElementConditionDto, com.ufgov.zc.common.system.RequestMeta)
	 */
	
	public List getMainDataLst(ElementConditionDto dto, RequestMeta requestMeta) {
		
		return jdDocCodeMapper.getSfJdDocCodeLst(dto);
	}

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.service.ISfJdDocCodeService#selectByPrimaryKey(java.lang.String, com.ufgov.zc.common.system.RequestMeta)
	 */
	
	public SfJdDocCode selectByPrimaryKey(String pinJieCode,RequestMeta requestMeta) {
		return jdDocCodeMapper.selectByPrimaryKey(pinJieCode);
	}

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.service.ISfJdDocCodeService#saveFN(com.ufgov.zc.common.sf.model.SfJdDocCode, com.ufgov.zc.common.system.RequestMeta)
	 */
	
	public SfJdDocCode saveFN(SfJdDocCode inData, RequestMeta requestMeta) throws SfBusinessException{
		SfJdDocCode t=selectByPrimaryKey(inData.getPinJieCode(), requestMeta);
		 
		if(t!=null){
			if(t.getNum().intValue()>=inData.getNum().intValue()){
				throw new SfBusinessException("编号"+inData.getCode()+"已被占用，请重新选择");
			}else{
				jdDocCodeMapper.updateByPrimaryKey(inData);
			}
		}else{
			jdDocCodeMapper.insert(inData);
		}
		return inData;
	}

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.service.ISfJdDocCodeService#deleteByPrimaryKeyFN(java.lang.String, com.ufgov.zc.common.system.RequestMeta)
	 */
	
	public void deleteByPrimaryKeyFN(String pinJieCode, RequestMeta requestMeta) {
		jdDocCodeMapper.deleteByPrimaryKey(pinJieCode);
	}

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.service.ISfJdDocCodeService#updateByPrimaryKeyFN(com.ufgov.zc.common.sf.model.SfJdDocCode, com.ufgov.zc.common.system.RequestMeta)
	 */
	
	public int updateByPrimaryKeyFN(SfJdDocCode inData, RequestMeta requestMeta) throws SfBusinessException{
		jdDocCodeMapper.updateByPrimaryKey(inData);
		return 0;
	}

	public SfJdDocCodeMapper getJdDocCodeMapper() {
		return jdDocCodeMapper;
	}

	public void setJdDocCodeMapper(SfJdDocCodeMapper jdDocCodeMapper) {
		this.jdDocCodeMapper = jdDocCodeMapper;
	}

}
