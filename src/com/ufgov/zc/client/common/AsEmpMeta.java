/**
 * 
 */
package com.ufgov.zc.client.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.console.model.AsEmp;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class AsEmpMeta {


  private static Map asEmpMap = new HashMap();
  
  private static List empLst=new ArrayList();

 

  public static String getEmpName(String empCode) {

    if(empCode==null || empCode.trim().length()==0){
      return null;
    }
    AsEmp emp = (AsEmp) asEmpMap.get(empCode);

    if (emp == null) {

      if(empLst==null || empLst.size()==0){
        IZcEbBaseServiceDelegate baseDataServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate");
        RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();
        empLst= baseDataServiceDelegate.queryDataForList("AsEmp.getAsEmp", null, requestMeta);
      }
      boolean find=false;
      for(int i=0;i<empLst.size();i++){
        emp=(AsEmp) empLst.get(i);
        if(emp.getEmpCode().equals(empCode)){
          asEmpMap.put(empCode, emp); 
          find=true;
          break;
        }
      }
      if(!find){
        emp=null;
      }
    }
    if(emp!=null){
      return emp.getEmpName();
    }else{
      return null;
    }

  }


}
