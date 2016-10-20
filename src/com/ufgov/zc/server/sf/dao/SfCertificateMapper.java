package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfCertificate;
import java.math.BigDecimal;

public interface SfCertificateMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(SfCertificate record);

    int insertSelective(SfCertificate record);

    SfCertificate selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SfCertificate record);

    int updateByPrimaryKey(SfCertificate record);
}