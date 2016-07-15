package com.ufgov.zc.client.sf.evaluation;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.client.util.freemark.WordHandlerAdapter;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfEvaluation;
import com.ufgov.zc.common.sf.model.SfEvaluationPerson;
import com.ufgov.zc.common.system.constants.SfElementConstants;

public class SfEvalutionWordPrintHandler extends WordHandlerAdapter {

  @Override
  public String getTemplateFileId() {
    // TCJLODO Auto-generated method stub
    return "sf_evalution_template";
  }

  @Override
  public Map<String, Object> initTemplateData(Map<String, Object> sourceMap) {
    // TCJLODO Auto-generated method stub
    Map<String, Object> dataMap = new HashMap<String, Object>();

    SfEvaluation evaluation = (SfEvaluation) sourceMap.get("evaluation");

    SfEntrust entrust = evaluation.getEntrust();

    if (entrust.getEntrustor() == null) {
      entrust.setEntrustor(new SfEntrustor());
    }

    String jgmc = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME);
    dataMap.put("jgmc", StringUtil.freeMarkFillWordChar(jgmc));
    dataMap.put("bh", StringUtil.freeMarkFillWordChar("编号KPTJ-469-14"));
    dataMap.put("bb", StringUtil.freeMarkFillWordChar("第1版"));
    dataMap.put("xd", StringUtil.freeMarkFillWordChar("第0次修订"));

    dataMap.put("wtbh", StringUtil.freeMarkFillWordChar(entrust.getCode()));
    dataMap.put("wtf", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getName()));
    dataMap.put("jdsx", StringUtil.freeMarkFillWordChar(entrust.getName()));
    dataMap.put("jdyq", StringUtil.freeMarkFillWordChar(entrust.getJdRequire()));
    dataMap.put("jazy", StringUtil.freeMarkFillWordChar(entrust.getBrief()));
    dataMap.put("psyj", StringUtil.freeMarkFillWordChar(evaluation.getEvaluationOpinions()));

    StringBuffer psry = new StringBuffer();
    for (int i = 0; i < evaluation.getEvaluationPersons().size(); i++) {
      SfEvaluationPerson p = (SfEvaluationPerson) evaluation.getEvaluationPersons().get(i);
      if (i > 0) {
        psry.append("、");
      }
      psry.append(p.getName());
    }
    dataMap.put("psry", StringUtil.freeMarkFillWordChar(psry.toString()));
    dataMap.put("psdd", StringUtil.freeMarkFillWordChar(evaluation.getEvaluateAddress()));
    SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
    dataMap.put("pssj", df.format(evaluation.getEvaluateDate()));
    dataMap.put("remark", StringUtil.freeMarkFillWordChar(evaluation.getRemark()));
    return dataMap;
  }

}
