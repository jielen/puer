package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfEvaluationFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("ENTRUST_CODE", "entrustCode");
    fieldMap.put("ENTRUST_ID", "entrustId");
    fieldMap.put("EVALUATE_DATE", "evaluateDate");
    fieldMap.put("EVALUATE_ADDRESS", "evaluateAddress");
    fieldMap.put("EVALUATION_ID", "evaluationId");
    fieldMap.put("EVALUATION_OPINIONS", "evaluationOpinions");
    fieldMap.put("INPUTOR", "inputor");
    fieldMap.put("INPUT_DATE", "inputDate");
    fieldMap.put("IS_ACCEPT", "isAccept");
    fieldMap.put("NAME", "name");
    fieldMap.put("ND", "nd");
    fieldMap.put("NOT_ACCEPT_REASON", "notAcceptReason");
    fieldMap.put("STATUS", "status");
    fieldMap.put("REMARK", "remark");

  }
}
