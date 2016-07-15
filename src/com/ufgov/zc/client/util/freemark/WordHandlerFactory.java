/**
 * 
 */
package com.ufgov.zc.client.util.freemark;

import com.ufgov.zc.client.sf.appendmaterialnotice.SfAppendMNWordHandler;
import com.ufgov.zc.client.sf.entrust.SfAgreementWordHandler;
import com.ufgov.zc.common.sf.model.SfAgreement;
import com.ufgov.zc.common.sf.model.SfAppendMaterialNotice;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

/**
 * 
 * @author Administrator
 *
 */
public class WordHandlerFactory {
  

  
  static WordHandlerFactory self=new WordHandlerFactory();
  
  public static WordHandlerFactory getInstance(){
    return self;
  }
  private WordHandlerFactory(){
    
  }
  public IWordHandler getHandler(ZcBaseBill bill){
    
    if(bill instanceof SfAgreement){
      return new SfAgreementWordHandler();
    }else if(bill instanceof SfAppendMaterialNotice){
      return new SfAppendMNWordHandler();
    }
    return null;
  }
}
