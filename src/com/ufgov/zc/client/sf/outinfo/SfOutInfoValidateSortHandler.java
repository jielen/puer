/**
 * 
 */
package com.ufgov.zc.client.sf.outinfo;

import java.math.BigDecimal;
import java.util.Comparator;

import com.ufgov.zc.common.sf.model.SfOutInfoValidateDetail;

/**
 * @author Administrator
 *
 */
public class SfOutInfoValidateSortHandler implements Comparator {

  @Override
  public int compare(Object o1, Object o2) {
    // TCJLODO Auto-generated method stub
    SfOutInfoValidateDetail d1 = (SfOutInfoValidateDetail) o1;
    SfOutInfoValidateDetail d2 = (SfOutInfoValidateDetail) o2;

    BigDecimal b1 = new BigDecimal(d1.getOutInfoReqCode());
    BigDecimal b2 = new BigDecimal(d2.getOutInfoReqCode());

    return b1.compareTo(b2);
  }

}
