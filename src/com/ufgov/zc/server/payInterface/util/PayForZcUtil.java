package com.ufgov.zc.server.payInterface.util;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang.NullArgumentException;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.model.AsOption;
import com.ufgov.zc.common.zc.exception.ZcPayInterfaceException;
import com.ufgov.zc.common.zc.model.ZcPProBal;
import com.ufgov.zc.common.zc.model.ZcPProBalBi;
import com.ufgov.zc.common.zc.model.ZcPProReturnBi;
import com.ufgov.zc.common.zc.model.ZcQb;
import com.ufgov.zc.common.zc.model.ZcQbBi;
import com.ufgov.zc.common.zc.model.ZcQx;
import com.ufgov.zc.common.zc.model.ZcQxBi;
import com.ufgov.zc.server.system.util.AsOptionUtil;

public class PayForZcUtil {

  private static final String RESULT = "result";

  private static final String FALSE = "false";

  private static final String TRUE = "true";

  private static final String MESSAGE = "message";
//支付用户
  private static final String OPT_ZC_PAY_USER_ID="OPT_ZC_PAY_USER_ID";
  //支付角色
  private static final String OPT_ZC_PAY_ROLE_ID="OPT_ZC_PAY_ROLE_ID";
  //区划码
  private static final String OPT_ZC_PAY_RG_CODE="OPT_ZC_PAY_RG_CODE";
  //支付方式
  private static final String OPT_ZC_PAY_ZhiFuFangShi="OPT_ZC_PAY_ZhiFuFangShi";
  //是否直接生成支付凭证
  private static final String OPT_ZC_PAY_IS_ZhiFuPingZhen="OPT_ZC_PAY_IS_ZhiFuPingZhen";
  /**
   * 生成支付凭证
   */

  public  void PayByBal(ZcPProBal zcPProBal, List list, RequestMeta requestMeta) throws ZcPayInterfaceException {

    List biList = zcPProBal.getBiList();
    if (biList == null || biList.size() == 0) {
      return;
    }

    // data_type=save
    // user_id=用户id
    // role_id=角色id
    // isVoucher=true/false（true为生成支付申请，false为生成支付凭证）
    // pk_id=支付方式id
    // Map<String, Object> paramMap = new HashMap<String, Object>();
      
    Map paymap = new HashMap();
//    paymap.put("budget_sum_id", "105786");
    paymap.put("user_id", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_USER_ID));
    paymap.put("role_id", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_ROLE_ID));
    String isZfpz=AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_IS_ZhiFuPingZhen);
//    isVoucher=true/false（true为生成支付申请，false为生成支付凭证）
    if("Y".equalsIgnoreCase(isZfpz)){
      paymap.put("isVoucher", "false");
    }else{
      paymap.put("isVoucher", "true");
    }
    paymap.put("set_year", ""+requestMeta.getSvNd());
    paymap.put("rg_code", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_RG_CODE));
    paymap.put("pk_code", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_ZhiFuFangShi));//支付方式id
    paymap.put("data_type", "save");
    
    StringBuffer str = new StringBuffer();

    for (int i = 0; i < biList.size(); i++) {
      ZcPProBalBi bbi = (ZcPProBalBi) biList.get(i);
      if (bbi.getZcAmBillNo() != null && bbi.getZcAmBillNo().length() > 0) {
        continue;
      }
      if (bbi.getZcBiNo() == null || "".equals(bbi.getZcBiNo()) || bbi.getZcBiNo().startsWith(ZcSettingConstants.No_BI)//自筹资金
        || bbi.getZcBiBcjsSum() == null || bbi.getZcBiBcjsSum().doubleValue() == 0) {
        continue;
      }
 
   // pay_money=1#payee_account_name=政府采购测试单位账户1#payee_account_no=1234567890#payee_account_bank=政府采购银行#bsi_id={87127CAF-5FD7-4668-9DC3-97E8E86CA34A}
      //  data中&连接多条支付数据，#连接同一条支付数据中不同要素，=连接要素名称与要素值。
      if (str.length() > 0) {
        str.append("&");
      }
      str.append("budget_id=").append(bbi.getZcBiNo() == null ? "" : bbi.getZcBiNo().trim());// 指标编号
      str.append("#pay_money=").append(bbi.getZcBiBcjsSum());// 本次结算金额
      str.append("#payee_account_name=").append(zcPProBal.getZcSuAccName().trim());// 供应商银行账户名称
      str.append("#payee_account_no=").append(zcPProBal.getZcSuAccCode().trim());// 供应商银行账户账号
      str.append("#payee_account_bank=").append(zcPProBal.getZcSuBankName().trim());// 供应商银行名称
      str.append("#bsi_id=").append(bbi.getOutLayCode());// 经济科目，指标只到类，支付要到末级
    }
   
    if (str.length() == 0) {
      return;
    }
    paymap.put("data", str.toString());
    Map resultMap = null;
    resultMap = call(paymap);

    if (resultMap == null) {
      throw new ZcPayInterfaceException("支付接口返回结果为空");
    }

    if (!TRUE.equals(resultMap.get(RESULT))) {
      throw new ZcPayInterfaceException(resultMap.get(MESSAGE).toString());
    }
//暂时不处理支付完成后返回的支付单据号 -chenjl 20130819
    /*String[] res = resultMap.get("data").toString().split("&");
    // List<Map<String, String>> beans = new ArrayList<Map<String,
    // String>>();
    List beans = new ArrayList();
    for (int i = 0; i < res.length; i++) {
      String[] record = res[i].split("#");
      Map it = new HashMap();
      for (int j = 0; j < record.length; j++) {
        String[] item = record[j].split("=");
        if (item.length != 2) {
          continue;
        }
        if (item[0].trim().length() > 0 && item[1].trim().length() > 0) {
          it.put(item[0].trim(), item[1].trim());
        }
      }
      beans.add(it);
    }

    for (int i = 0; i < beans.size(); i++) {
      Map mps = (Map) beans.get(i);
      if (zcPProBal.getZcSuAccCode().trim().equals(mps.get("payee_account_no"))
        && zcPProBal.getZcSuBankName().trim().equals(mps.get("payee_account_bank"))) {
        for (int j = 0; j < biList.size(); j++) {
          ZcPProBalBi zcPProBalBi = (ZcPProBalBi) biList.get(j);
          if (zcPProBalBi.getZcBiNo() == null) {
            continue;
          }
          if (zcPProBalBi.getZcBiNo().trim().equals(mps.get("budget_id"))) {
            zcPProBalBi.setZcAmBillNo(mps.get("bill_no") == null ? null : mps.get("bill_no").toString());
          }
        }
      } 
    }*/

  }

  /**
   * 生成支付凭证
   */

  public  void PayByQx(ZcQx qx, RequestMeta requestMeta) throws ZcPayInterfaceException {

    List biList = qx.getBiList();
    if (biList == null || biList.size() == 0) {
      return;
    }

    // data_type=save
    // user_id=用户id
    // role_id=角色id
    // isVoucher=true/false（true为生成支付申请，false为生成支付凭证）
    // pk_id=支付方式id
    // Map<String, Object> paramMap = new HashMap<String, Object>();

    Map paymap = new HashMap();
//    paymap.put("budget_sum_id", "105786");
    paymap.put("user_id", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_USER_ID));
    paymap.put("role_id", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_ROLE_ID));
    String isZfpz=AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_IS_ZhiFuPingZhen);
    if("Y".equalsIgnoreCase(isZfpz)){
      paymap.put("isVoucher", "false");
    }else{
      paymap.put("isVoucher", "true");
    }
    paymap.put("set_year", ""+requestMeta.getSvNd());
    paymap.put("rg_code", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_RG_CODE));
    paymap.put("pk_code", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_ZhiFuFangShi));//支付方式id
    paymap.put("data_type", "save");
    
    StringBuffer str = new StringBuffer();

    for (int i = 0; i < biList.size(); i++) {
      ZcQxBi bbi = (ZcQxBi) biList.get(i);

      if (bbi.getZcAmBillNo() != null && bbi.getZcAmBillNo().length() > 0) {
        continue;
      }
      
      if (bbi.getZcBiNo() == null || "".equals(bbi.getZcBiNo()) || bbi.getZcBiNo().startsWith(ZcSettingConstants.No_BI)//自筹资金
        || bbi.getZcBiJhuaSum() == null || bbi.getZcBiJhuaSum().doubleValue() == 0) {
        continue;
      }
      
      /**
       * data=具体数据， 例如：data=pay_money=1#payee_account_name=政府采购测试单位账户1#
       * payee_account_no=1234567890 #payee_account_bank=政府采购银行#bsi_id=123
       * &pay_money=2#payee_account_name=政府采购测试单位账户2#payee_account_no=
       * 1234567891#payee_account_bank=政府采购银行2#bsi_id=123
       * data中&连接多条支付数据，#连接同一条支付数据中不同要素，=连接要素名称与要素值。
       */
      if (str.length() > 0) {
        str.append("&");
      }
      str.append("budget_id=").append(bbi.getZcBiNo() == null ? "" : bbi.getZcBiNo().trim());// 指标编号
      str.append("#pay_money=").append(bbi.getZcBiJhuaSum());// 本次结算金额
      str.append("#payee_account_name=").append(qx.getSuBank().trim());// 供应商银行账户名称
      str.append("#payee_account_no=").append(qx.getSuBankAccount().trim());// 供应商银行账户账号
      str.append("#payee_account_bank=").append(qx.getSuBank().trim());// 供应商银行名称
      str.append("#bsi_id=").append(bbi.getOutlayCode());// 经济科目，指标只到类，支付要到末级
    }

    if (str.length() == 0) {
      return;
    }
    paymap.put("data", str.toString());
    Map resultMap = null;
    resultMap = call(paymap);

    if (resultMap == null) {
      throw new ZcPayInterfaceException("支付接口返回结果为空");
    }

    if (!TRUE.equals(resultMap.get(RESULT))) {
      throw new ZcPayInterfaceException(resultMap.get(MESSAGE).toString());
    }
  //暂时不处理支付完成后返回的支付单据号 -chenjl 20130819
 /*   String[] res = resultMap.get("data").toString().split("&");
    // List<Map<String, String>> beans = new ArrayList<Map<String,
    // String>>();
    List beans = new ArrayList();
    for (int i = 0; i < res.length; i++) {
      String[] record = res[i].split("#");
      Map it = new HashMap();
      for (int j = 0; j < record.length; j++) {
        String[] item = record[j].split("=");
        if (item.length != 2) {
          continue;
        }
        if (item[0].trim().length() > 0 && item[1].trim().length() > 0) {
          it.put(item[0].trim(), item[1].trim());
        }
      }
      beans.add(it);
    }

    for (int i = 0; i < beans.size(); i++) {
      Map mps = (Map) beans.get(i);
      if (qx.getSuBankAccount().trim().equals(mps.get("payee_account_no")) && qx.getSuBank().trim().equals(mps.get("payee_account_bank"))) {
        for (int j = 0; j < biList.size(); j++) {
          ZcQxBi qxbi = (ZcQxBi) biList.get(j);
          if (qxbi.getZcBiNo() == null) {
            continue;
          }
          if (qxbi.getZcBiNo().trim().equals(mps.get("budget_id"))) {
            qxbi.setZcAmBillNo(mps.get("bill_no") == null ? null : mps.get("bill_no").toString());
          }
        }
      }
    }*/

  }

  /**
   * 修改支付
   * 
   */

  public  void updatePayBillByBal(ZcPProBal zcPProBal, String serverAdd, RequestMeta requestMeta) {
    List biList = zcPProBal.getBiList();

    if (biList == null || biList.size() == 0) {
      return;
    }

    /**
     * data_type=update user_id=用户id role_id=角色id voucher_no=要修改的支付申请号
     * data=修改的要素 ，例如：data=pay_money=1#payee_account_name=政府采购测试单位账户1#
     * payee_account_no =1234567890#payee_account_bank=政府采购银行#bsi_id=123
     * #连接同一条支付数据中不同要素，=连接要素名称与要素值。
     **/

    for (int i = 0; i < biList.size(); i++) {
      ZcPProBalBi zcPProBalBi = (ZcPProBalBi) biList.get(i);
      if (zcPProBalBi.getZcAmBillNo() == null || "".equals(zcPProBalBi.getZcAmBillNo())) {
        continue;
      }
      // Map<String, Object> paramMap = new HashMap<String, Object>();
      Map paramMap = new HashMap();
      paramMap.put("data_type", "update");
      paramMap.put("user_id", requestMeta.getSvUserID());
      //这个暂时注释掉，联调借口的时候再决定用什么角色传递给国库系统
      //paramMap.put("role_id", requestMeta.getSvRoleId());
      String billStr = zcPProBalBi.getZcAmBillNo();
      String[] strArray = billStr.split("&");
      paramMap.put("voucher_no", strArray[0]);
      StringBuffer str = new StringBuffer();
      str.append("pay_money=" + zcPProBalBi.getZcBiBcjsSum());// 本次结算金额
      str.append("#payee_account_name=" + zcPProBal.getZcSuAccName());// 供应商银行账户名称
      str.append("#payee_account_no=" + zcPProBal.getZcSuAccCode());// 供应商银行账户账号
      str.append("#payee_account_bank=" + zcPProBal.getZcSuBankName());// 供应商银行名称
      str.append("#bsi_id=" + zcPProBalBi.getOutLayCode() + "&");// 经济分类编号
      paramMap.put("data", str.toString());
      try {
        call(paramMap);
      } catch (Exception e) {
        // TCJLODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    //
    List returnBiList = zcPProBal.getReturnBiList();
    if (returnBiList != null && returnBiList.size() > 0) {

    }
    for (int i = 0; i < returnBiList.size(); i++) {
      ZcPProReturnBi returnBi = (ZcPProReturnBi) returnBiList.get(i);
      if (returnBi.getZcAmBillNo() == null || "".equals(returnBi.getZcAmBillNo())) {
        continue;
      }
      // Map<String, Object> paramMap = new HashMap<String, Object>();
      Map paramMap = new HashMap();
      paramMap.put("data_type", "update");
      paramMap.put("user_id", requestMeta.getSvUserID());

      //这个暂时注释掉，联调借口的时候再决定用什么角色传递给国库系统
      //			paramMap.put("role_id", requestMeta.getSvRoleId());

      String billStr = returnBi.getZcAmBillNo();
      String[] strArray = billStr.split("&");
      paramMap.put("voucher_no", strArray[0]);
      StringBuffer str = new StringBuffer();
      str.append("pay_money=" + returnBi.getZcBiBal());// 本次结算金额
      str.append("#payee_account_name=" + zcPProBal.getZcCoAccName());// 采购单位银行账户名称
      str.append("#payee_account_no=" + zcPProBal.getZcCoAccCode());// 采购单位银行账户账号
      str.append("#payee_account_bank=" + zcPProBal.getZcCoBankName());// 采购单位银行名称
      paramMap.put("data", str.toString());
      try {
        call(paramMap);
      } catch (Exception e) {
        // TCJLODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }

  /**
   * 调用webservice接口
   * 
   * @param map
   * @throws Exception
   */
  private  Map call(Map map) throws ZcPayInterfaceException {
    Map result = null;
    try {

      String serverAdd = "";
      AsOption opt = AsOptionUtil.getInstance().getOption("OPT_ZC_PAY_SERVER_URL");
      if (opt != null) {
        serverAdd = opt.getOptVal();
      } else {
        throw new NullArgumentException("调用支付接口时出错:没有找到支付系统地址，请检查数据配置值：OPT_ZC_PAY_SERVER_URL");
      }
      String timeoutStr=AsOptionUtil.getInstance().getOptionVal("OPT_ZC_PAY_INTERFACE_TIME_OUT");
      if(timeoutStr==null || timeoutStr.trim().equals("")){
        timeoutStr="600000";
      }
      Service service = new Service();
      Call call = (Call) service.createCall();
      call.setTargetEndpointAddress(serverAdd);
      call.setOperationName(new QName(serverAdd, "invoke"));
      call.setTimeout(new Integer(timeoutStr));
      result = (Map) call.invoke(new Object[] { map });
      if (FALSE.equalsIgnoreCase(result.get(RESULT).toString())) {
        throw new ZcPayInterfaceException(result.get(MESSAGE).toString());
      }
    } catch (ZcPayInterfaceException e) {
      System.out.println("支付参数==>" + map.toString());
      throw new ZcPayInterfaceException("调用支付接口时出错:" + e.getMessage());
    } catch (ServiceException e) {
      // TCJLODO Auto-generated catch block
      throw new ZcPayInterfaceException("调用支付接口时出错:" + e.getMessage(),e);
    } catch (RemoteException e) {
      // TCJLODO Auto-generated catch block
      throw new ZcPayInterfaceException("调用支付接口时出错:" + e.getMessage(),e);
    }
    return result;

  }


  public  void PayByQb(ZcQb qb, RequestMeta requestMeta) throws ZcPayInterfaceException {
    // TCJLODO Auto-generated method stub

    List biList = qb.getBiList();
    if (biList == null || biList.size() == 0) {
      return;
    }

    // data_type=save
    // user_id=用户id
    // role_id=角色id
    // isVoucher=true/false（true为生成支付申请，false为生成支付凭证）
    // pk_id=支付方式id
    // Map<String, Object> paramMap = new HashMap<String, Object>();

    Map paymap = new HashMap();
//    paymap.put("budget_sum_id", "105786");
    paymap.put("user_id", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_USER_ID));
    paymap.put("role_id", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_ROLE_ID));
    String isZfpz=AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_IS_ZhiFuPingZhen);
    if("Y".equalsIgnoreCase(isZfpz)){
      paymap.put("isVoucher", "false");
    }else{
      paymap.put("isVoucher", "true");
    }
    paymap.put("set_year", ""+requestMeta.getSvNd());
    paymap.put("rg_code", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_RG_CODE));
    paymap.put("pk_code", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_PAY_ZhiFuFangShi));//支付方式id
    paymap.put("data_type", "save");
    
    StringBuffer str = new StringBuffer();

    for (int i = 0; i < biList.size(); i++) {
      ZcQbBi bbi = (ZcQbBi) biList.get(i);

      if (bbi.getZcAmBillNo() != null && bbi.getZcAmBillNo().length() > 0) {
        continue;
      }
      
      if (bbi.getZcBiNo() == null || "".equals(bbi.getZcBiNo()) || bbi.getZcBiNo().startsWith(ZcSettingConstants.No_BI)//自筹资金
        || bbi.getZcBiJhuaSum() == null || bbi.getZcBiJhuaSum().doubleValue() == 0) {
        continue;
      }
      
      /**
       * data=具体数据， 例如：data=pay_money=1#payee_account_name=政府采购测试单位账户1#
       * payee_account_no=1234567890 #payee_account_bank=政府采购银行#bsi_id=123
       * &pay_money=2#payee_account_name=政府采购测试单位账户2#payee_account_no=
       * 1234567891#payee_account_bank=政府采购银行2#bsi_id=123
       * data中&连接多条支付数据，#连接同一条支付数据中不同要素，=连接要素名称与要素值。
       */
      if (str.length() > 0) {
        str.append("&");
      }
      str.append("budget_id=").append(bbi.getZcBiNo() == null ? "" : bbi.getZcBiNo().trim());// 指标编号
      str.append("#pay_money=").append(bbi.getZcBiJhuaSum());// 本次结算金额
      str.append("#payee_account_name=").append(qb.getSuBank().trim());// 供应商银行账户名称
      str.append("#payee_account_no=").append(qb.getSuBankAccount().trim());// 供应商银行账户账号
      str.append("#payee_account_bank=").append(qb.getSuBank().trim());// 供应商银行名称
      str.append("#bsi_id=").append(bbi.getOutlayCode());// 经济科目，指标只到类，支付要到末级
    }

    if (str.length() == 0) {
      return;
    }
    paymap.put("data", str.toString());
    Map resultMap = null;
    resultMap = call(paymap);

    if (resultMap == null) {
      throw new ZcPayInterfaceException("支付接口返回结果为空");
    }

    if (!TRUE.equals(resultMap.get(RESULT))) {
      throw new ZcPayInterfaceException(resultMap.get(MESSAGE).toString());
    }
  //暂时不处理支付完成后返回的支付单据号 -chenjl 20130819
 /*   String[] res = resultMap.get("data").toString().split("&");
    // List<Map<String, String>> beans = new ArrayList<Map<String,
    // String>>();
    List beans = new ArrayList();
    for (int i = 0; i < res.length; i++) {
      String[] record = res[i].split("#");
      Map it = new HashMap();
      for (int j = 0; j < record.length; j++) {
        String[] item = record[j].split("=");
        if (item.length != 2) {
          continue;
        }
        if (item[0].trim().length() > 0 && item[1].trim().length() > 0) {
          it.put(item[0].trim(), item[1].trim());
        }
      }
      beans.add(it);
    }

    for (int i = 0; i < beans.size(); i++) {
      Map mps = (Map) beans.get(i);
      if (qx.getSuBankAccount().trim().equals(mps.get("payee_account_no")) && qx.getSuBank().trim().equals(mps.get("payee_account_bank"))) {
        for (int j = 0; j < biList.size(); j++) {
          ZcQxBi qxbi = (ZcQxBi) biList.get(j);
          if (qxbi.getZcBiNo() == null) {
            continue;
          }
          if (qxbi.getZcBiNo().trim().equals(mps.get("budget_id"))) {
            qxbi.setZcAmBillNo(mps.get("bill_no") == null ? null : mps.get("bill_no").toString());
          }
        }
      }
    }*/

  }
}
