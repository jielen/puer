package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfJdResultFileStore;
import java.math.BigDecimal;

public interface SfJdResultFileStoreMapper {
    int deleteByPrimaryKey(BigDecimal sfJdResultFileStoreId);

    int insert(SfJdResultFileStore record);

    int insertSelective(SfJdResultFileStore record);

    SfJdResultFileStore selectByPrimaryKey(BigDecimal sfJdResultFileStoreId);

    int updateByPrimaryKeySelective(SfJdResultFileStore record);

    int updateByPrimaryKey(SfJdResultFileStore record);
}