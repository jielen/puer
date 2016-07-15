/**
 * 
 */
package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcQx;

/**
 * @author Administrator
 *
 */
public interface IZcQxServiceDelegate extends Publishable {

  List getQxLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  void cancelFn(ZcQx qx, RequestMeta requestMeta);

  ZcQx unAuditFN(ZcQx qx, RequestMeta requestMeta);

  ZcQx untreadFN(ZcQx qx, RequestMeta requestMeta);

  ZcQx auditFN(ZcQx qx, RequestMeta requestMeta) throws Exception;

  ZcQx updateFN(ZcQx qx, RequestMeta requestMeta) throws Exception;

  void commitFN(List beanList, RequestMeta requestMeta);

  void deleteListFN(List beanList, RequestMeta requestMeta);

  void deleteFN(ZcQx qx, RequestMeta requestMeta);

  ZcQx selectByPrimaryKey(String qxCode, RequestMeta requestMeta);

  ZcQx callbackFN(ZcQx qx, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(String qxCode, RequestMeta requestMeta);

  ZcQx newCommitFN(ZcQx qx, RequestMeta requestMeta);

  ZcQx sendPayFN(ZcQx qx, RequestMeta requestMeta) throws Exception;


}
