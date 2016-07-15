package com.ufgov.zc.client.sf.dossier;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.common.sf.model.SfDossier;
import com.ufgov.zc.common.system.constants.SfElementConstants;

public class SfDossierUtil {

  public static void setDossierType(SfDossier currentBill) {
    // TCJLODO Auto-generated method stub
    currentBill.setDossierType(SfDossier.DOSSIER_TYPE_WU_ZHENG);
    if (currentBill.getEntrust() != null || currentBill.getEntrust().getMajor() != null) {
      String fayiCode = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_MAJOR_FA_YI_CODE);
      if (fayiCode.equals(currentBill.getEntrust().getMajor().getMajorCode())) {
        currentBill.setDossierType(SfDossier.DOSSIER_TYPE_FA_YI);
      }
    }
  }
}
