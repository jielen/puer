/**   
* @(#) project: zc_xa
* @(#) file: IZcBdZbpcServiceDelegate.java
* Copyright 2010 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.common.zc.publish;
import java.sql.SQLException;import java.util.List;import com.ufgov.zc.common.system.Publishable;import com.ufgov.zc.common.system.RequestMeta;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.common.zc.model.EmExpert;
public interface IZcEmExpertServiceDelegate extends Publishable {
  public List getEmExpertList(ElementConditionDto dto, RequestMeta meta) throws SQLException;
  public EmExpert selectByPrimaryKey(String zcPrimKey, RequestMeta requestMeta) throws SQLException;
  public void deleteByPrimaryKey(String zcPrimKey, RequestMeta requestMeta) throws SQLException;

  public EmExpert updateByPrimaryKey(EmExpert bean, RequestMeta meta) throws SQLException;

}
