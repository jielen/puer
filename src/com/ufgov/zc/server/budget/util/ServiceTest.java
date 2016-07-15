package com.ufgov.zc.server.budget.util;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class ServiceTest {

  // 释放指标
  private static final String DEL_BUDGET = "delete";

  // 更新原占用指标
  private static final String UPD_BUDGET = "update";

  // 占用指标
  private static final String SAVE_BUDGET = "save";

  private static String BUDGET_TARGET_END_POINT = "http://192.168.1.110:7001/gfmis/services/BudgetZcService";

  //  private static String BUDGET_TARGET_END_POINT = "http://127.0.0.1:7002/gfmis/services/BudgetZcService";

  private static String PAY_TARGET_END_POINT = "http://127.0.0.1:7002/gfmis/services/PayForZCService";

  public void budgetInvoke(Object obj) {
    Service service = new Service();
    try {
      Call call = (Call) service.createCall();
      call.setTargetEndpointAddress(new URL(BUDGET_TARGET_END_POINT));
      call.setOperationName(new QName(BUDGET_TARGET_END_POINT, "invoke"));
      Map result = (Map) call.invoke(new Object[] { obj });
      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void payInvoke(Object obj) {
    Service service = new Service();
    try {
      Call call = (Call) service.createCall();
      call.setTargetEndpointAddress(new URL(PAY_TARGET_END_POINT));
      call.setOperationName(new QName(PAY_TARGET_END_POINT, "invoke"));
      Map result = (Map) call.invoke(new Object[] { obj });
      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    //指标 参数
    // data_type=save或update或者delete
    // vou_id 资金占用号
    // data = "指标编号#占用金额#VOU_ID&指标编号#占用金额#VOU_ID" 批量用&分开 vou_id=政采单号
    //set_year 年度
    //rg_code 区划
    //create_user 用户级id

    Map budgetMap = new HashMap();
    budgetMap.put("data_type", SAVE_BUDGET);
    //    budgetMap.put("data_type", UPD_BUDGET);
    //    budgetMap.put("data", "112356#29#7Fwwerweeee234324e07&112357#40#7F0sf532423432agfs08");
    //    budgetMap.put("data", "112346#10#19234234ete22501&112347#20#652324332423432");
    budgetMap.put("data", "103269#100#20130914eee115fff40001");
    budgetMap.put("create_user", "2529");
    budgetMap.put("set_year", "2013");
    budgetMap.put("rg_code", "321112");
    new ServiceTest().budgetInvoke(budgetMap);
    //    

    // 生成支付凭证 参数
    // budget_sum_id 指标额度id
    // user_id=用户id
    // role_id=角色id
    // isVoucher true/false（true为生成支付申请，false为生成支付凭证）
    // pk_code 支付方式pk_code
    // set_year
    // rg_code
    // data_type=save或update
    // data 具体数据,其中bsi_id指的经济分类id，支付
    // pay_money=1#payee_account_name=政府采购测试单位账户1#payee_account_no=1234567890#payee_account_bank=政府采购银行#bsi_id={87127CAF-5FD7-4668-9DC3-97E8E86CA34A}
    //  data中&连接多条支付数据，#连接同一条支付数据中不同要素，=连接要素名称与要素值。

    //      Map paymap = new HashMap();
    //      paymap.put("user_id", "2529");
    //      paymap.put("role_id", "23");
    //      paymap.put("isVoucher", "false");
    //      paymap.put("set_year", "2013");
    //      paymap.put("rg_code", "321112");
    //      paymap.put("pk_code", "001001001");//支付方式id
    //      paymap.put("data_type", "save");
    //      paymap.put("data", "budget_id=106509#pay_money=319#payee_account_name=采购中心开户银行#payee_account_no=8888888888#payee_account_bank=采购中心开户银行#bsi_id={AE7E05DC-87C6-4C9F-B02E-F72152B96EBF}");
    // new ServiceTest().payInvoke(paymap);
  }

}
