/**
 * 
 */
package com.ufgov.zc.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.console.model.AsEmp;

/**
 * 用户信息
 * @author Administrator
 *
 */
public class EmpMeta {

  private static Map asEmpMap = new HashMap();
  
  private static List empLst=new ArrayList();

  private EmpMeta(){
    
  }

  public static String getEmpName(String empCode) {

    if(empCode==null || empCode.trim().length()==0){
      return null;
    }
    AsEmp emp = (AsEmp) asEmpMap.get(empCode);

    if (emp == null) {

      if(empLst==null || empLst.size()==0){
        return null;
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
  
  public static void setEmpLst(List asEmpLst){
    empLst=(asEmpLst==null?new ArrayList():asEmpLst);
  }

}
