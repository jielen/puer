/**
 * 
 */
package com.ufgov.zc.server.payInterface.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.ufgov.zc.common.system.model.AsOption;
import com.ufgov.zc.server.system.util.AsOptionUtil;

/**
 * 国库测试类
 * @author Administrator
 *
 */
public class GkInterfaceTest {
  //释放指标
  private static final String DEL_BUDGET = "delete";
  //更新原占用指标
  private static final String UPD_BUDGET = "update";
  //释放原先占用的指标
  public static final String UPD_BUDGET_OLD = "update_old";
  //占用指标
  private static final String SAVE_BUDGET = "save";

  private static final String CREATE_USER = "create_user";

  private static final String CREATE_USER_VEL = "政府采购";

  private static final String FALSE = "false";
  //指标webservice地址
  private static final String WSDL_URL_BUDET = "/gfmis/services/BudgetZcService";
  //支付webservice地址
  private static final String WSDL_URL_PAY = "/gfmis/services/PayForZCService";



  /**
   * @param args
   */
  public static void main(String[] args) {
    // TCJLODO Auto-generated method stub
    

  }
  
  void testBudget(){
    //采购计划新增保存时，占用指标
    //采购计划修改保存时，有的指标时新占用，有的指标时调整金额，有的指标是不占用了
    //因此设计了四种操作类型：save update update_old delete
    //单条数据格式：指标编号#占用金额#VOU_ID
    //多条数据格式：指标编号#占用金额#VOU_ID&指标编号#占用金额#VOU_ID&指标编号#占用金额#VOU_ID&.........
    
    HashMap map=new HashMap();
    map.put(SAVE_BUDGET, "指标编号#占用金额#VOU_ID&指标编号#占用金额#VOU_ID");
    map.put(UPD_BUDGET, "指标编号#占用金额#VOU_ID&指标编号#占用金额#VOU_ID");
    map.put(UPD_BUDGET_OLD, "指标编号#释放金额#VOU_ID&指标编号#释放金额#VOU_ID");
    map.put(DEL_BUDGET, "指标编号#释放金额#VOU_ID&指标编号#释放金额#VOU_ID");
    map.put("serverType", "ZC");
    map.put(CREATE_USER, CREATE_USER_VEL);
    map.put("set_year", "2013");
    map.put("rg_code", "");
    
    //调用webservice接口
    
    /*try {
    Service service = new Service();
    Call call = (Call) service.createCall();
    call.setTargetEndpointAddress(serverAdd + WSDL_URL_BUDET);
    call.setOperationName("invoke");

    Map result = (Map) call.invoke(new Object[] { map });
    if (FALSE.equalsIgnoreCase(result.get(RESULT).toString())) {
      throw new Exception(result.get(MESSAGE).toString());
    }

  } catch (Exception e) {
    System.out.println("指标传参==>" + map.toString());
    throw new Exception("调用指标接口时出错:" + e.getMessage());
  }*/
    
  }

  void testPay(){
    
//    生成支付凭证
//  data_type=save或update
//  budget_id=指标额度id
//  user_id=用户id
//  role_id=角色id
//  isVoucher=true/false（true为生成支付申请，false为生成支付凭证）
//  pk_id=支付方式id
//  data=具体数据，
//          例如：data=pay_money=1#payee_account_name=政府采购测试单位账户1#payee_account_no=1234567890#payee_account_bank=政府采购银行#bsi_id=123#jjfl=6353252
//  &pay_money=2#payee_account_name=政府采购测试单位账户2#payee_account_no=1234567891#payee_account_bank=政府采购银行2#bsi_id=123
//  data中&连接多条支付数据，#连接同一条支付数据中不同要素，=连接要素名称与要素值。
    Map paramMap = new HashMap();
    paramMap.put("user_id", "用户");
    paramMap.put("role_id", "角色");
    paramMap.put("isVoucher", "false");
    paramMap.put("set_year", "2013");
    paramMap.put("no_budget", FALSE);
    paramMap.put("pk_code", "支付方式id");//支付方式id
    paramMap.put("data_type", "save");
    paramMap.put("data", "pay_money=1#payee_account_name=政府采购测试单位账户1#payee_account_no=1234567890#payee_account_bank=政府采购银行#bsi_id=123#jjfl=6353252&pay_money=1#payee_account_name=政府采购测试单位账户1#payee_account_no=1234567890#payee_account_bank=政府采购银行#bsi_id=123#jjfl=6353252");

    /*try {
      
      Service service = new Service();
      Call call = (Call) service.createCall();
      call.setTargetEndpointAddress(serverAdd + WSDL_URL_PAY);
      call.setOperationName(INTERFASE);
      result = (Map) call.invoke(new Object[] { paramMap });
      if (FALSE.equalsIgnoreCase(result.get(RESULT).toString())) {
        throw new Exception(result.get(MESSAGE).toString());
      }
    } catch (Exception e) {
      System.out.println("支付参数==>" + map.toString());
      throw new Exception("调用支付接口时出错:" + e.getMessage());
    }*/
  }
}
