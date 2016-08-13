package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfEntrustFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("ACCEPTOR", "acceptor");
    fieldMap.put("ACCEPT_DATE", "acceptDate");
    fieldMap.put("BRIEF", "brief");
    fieldMap.put("CODE", "code");
    fieldMap.put("ENTRUSTOR_ID", "entrustorId");
    fieldMap.put("ENTRUSTOR_NAME", "entrustor.name");//方便工作流上能进行定义修改属性
    fieldMap.put("ENTRUST_ID", "entrustId");
    fieldMap.put("INPUTOR", "inputor");
    fieldMap.put("INPUT_DATE", "inputDate");
    fieldMap.put("IS_ACCEPT", "isAccept");
    fieldMap.put("IS_CXJD", "isCxjd");
    fieldMap.put("JD_COMPANY", "jdCompany");
    fieldMap.put("JD_FZR", "jdFzrName");
    fieldMap.put("JD_FHR", "jdFhrName");
    fieldMap.put("JD_HISTORY", "jdHistory");
    fieldMap.put("JD_ORG", "jdOrg");
    fieldMap.put("JD_REQUIRE", "jdRequire");
    fieldMap.put("JD_TARGET_ID", "jdTargetId");
    fieldMap.put("JD_TARGET_NAME", "jdTarget.name");//方便工作流上能进行定义修改属性
    fieldMap.put("MAJOR_CODE", "majorCode");
    fieldMap.put("NAME", "name");
    fieldMap.put("ND", "nd");
    fieldMap.put("NOT_ACCEPT_REASON", "notAcceptReason");
    fieldMap.put("PROCESS_INST_ID", "processInstId");
    fieldMap.put("REMARK", "remark");
    fieldMap.put("SJR", "sjr");
    fieldMap.put("SJR_TEL", "sjrTel");
    fieldMap.put("SJR_ZJ_CODE", "sjrZjCode");
    fieldMap.put("SJR_ZJ_TYPE", "sjrZjType");
    fieldMap.put("SJR_ADDRESS", "sjrAddress");
    fieldMap.put("STATUS", "status");
    fieldMap.put("WT_DATE", "wtDate");
    fieldMap.put("WT_ID_PARENT", "wtIdParent");
    fieldMap.put("JD_DOC_SEND_TYPE", "jdDocSendType");
    fieldMap.put("JD_DOC_SEND_TYPE_FZ", "jdDocSendTypeFz");
    fieldMap.put("JD_CHARGE", "jdCharge");
    fieldMap.put("CO_CODE", "coCode");
    fieldMap.put("JD_ASSISTOR", "jdAssistorName");

  }
}
