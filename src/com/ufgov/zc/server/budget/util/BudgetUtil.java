package com.ufgov.zc.server.budget.util;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang.NullArgumentException;

import com.ufgov.zc.common.system.Guid;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsOption;
import com.ufgov.zc.common.zc.exception.ZcBudgetInterfaceException;
import com.ufgov.zc.common.zc.model.ZcPProBalBi;
import com.ufgov.zc.common.zc.model.ZcPProBalChgBi;
import com.ufgov.zc.common.zc.model.ZcPProMitemBi;
import com.ufgov.zc.common.zc.model.ZcPProMitemBiExample;
import com.ufgov.zc.common.zc.model.ZcQb;
import com.ufgov.zc.common.zc.model.ZcQbBi;
import com.ufgov.zc.common.zc.model.ZcQx;
import com.ufgov.zc.common.zc.model.ZcQxBi;
import com.ufgov.zc.common.zc.model.ZcXmcgHtBi;
import com.ufgov.zc.common.zc.model.ZcXmcgHtBiExample;
import com.ufgov.zc.server.system.util.AsOptionUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao;
import com.ufgov.zc.server.zc.dao.IZcPProMitemBiDao;
import com.ufgov.zc.server.zc.dao.IZcQbDao;
import com.ufgov.zc.server.zc.dao.IZcQxDao;
import com.ufgov.zc.server.zc.dao.IZcXmcgHtBiDao;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;

public class BudgetUtil {

  public static final String DEL_BUDGET = "delete";
//
  public static final String UPD_BUDGET = "update";
//
  public static final String UPD_BUDGET_OLD = "update_old";

  public static final String SAVE_BUDGET = "save";

  private static final String VOU_ID = "vou_id";

  private static final String VOU_MONEY = "vou_money";

  public static final String FROM_CTRL_ID = "fromctrlid";

  private static final String ITEM_ISP = "#";

  private static final String RECORD_ISP = "&";

  //  private static final String CREATE_USER = "create_user";
  //
  //  private static final String CREATE_USER_VEL = "政府采购";

  private static final String RESULT = "result";

  private static final String FALSE = "false";

  private static final String MESSAGE = "Message";

  private static final String OPT_ZC_BUDGET_CREATE_USER = "OPT_ZC_BUDGET_CREATE_USER";

  private static final String OPT_ZC_BUDGET_RG_CODE = "OPT_ZC_BUDGET_RG_CODE";

  /**
   * 采购计划保存时，调用指标接口
   * 
   * @param zcPProMitemBiDao
   *            政府采购指标表dao
   * @param flag
   *            是否需要指标接口
   * @param code
   *            采购计划编号
   * @param biList
   *            界面输入的采购指标列表,ZcPProMitemBi
   * @return
   * @throws Exception
   */
  public Map getSaveBudgetByZcpproMake(IZcPProMitemBiDao zcPProMitemBiDao, IBaseDao baseDao, boolean flag, String code, List biList) {
    if (!flag) {
      return null;
    }

    // List<Map<String, Object>> saves = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> upds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> updolds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> dels = new ArrayList<Map<String,
    // Object>>();

    List saves = new ArrayList();
    List upds = new ArrayList();
//    List updolds = new ArrayList();
    List dels = new ArrayList();

    ZcPProMitemBiExample bi = new ZcPProMitemBiExample();

    bi.createCriteria().andZcMakeCodeEqualTo(code);
    //将当前计划存在后台的资金数据查出来，用于和当前前台传过来的计划带的资金数据进行比较，方便更新指标占用情况，这种情况适用于已经存在的采购计划进行再编辑后的保存
    List biListOld = zcPProMitemBiDao.selectByExample(bi);

    if (biListOld == null) {
      biListOld = new ArrayList();
    }
    if (biList == null) {
      biList = new ArrayList();
    }

    StringBuffer ids = new StringBuffer("''");
    for (int i = 0; i < biList.size(); i++) {
      ZcPProMitemBi mbi = (ZcPProMitemBi) biList.get(i);
      if (mbi.getZcBiNo() != null && mbi.getZcBiNo().trim().length() > 0) {
        ids.append(",'").append(mbi.getZcBiNo()).append("'");
      }
    }
    for (int i = 0; i < biListOld.size(); i++) {
      ZcPProMitemBi mbi = (ZcPProMitemBi) biListOld.get(i);
      if (mbi.getZcBiNo() != null && mbi.getZcBiNo().trim().length() > 0) {
        ids.append(",'").append(mbi.getZcBiNo()).append("'");
      }
    }
    //将前台传过来指标编号和后台存在的指标编号，去数据库重新查询一下，去一下重，形成一个指标编号列表，涵盖当前采购计划涉及的前台指标数据和后台指标数（后台的只有编辑情况下存在）
    List allBiNoLst = baseDao.query("VwBudgetGp.getExistsBudget", ids.toString());
    
    long vou_id = 0;
    String vouId = code.replaceAll("\\D", "");
    vouId = zcPProMitemBiDao.getMaxVouId(vouId);
    if (vouId != null && !"".equals(vouId)) {
      vou_id = Long.parseLong(vouId);
    } else {
      vou_id = Long.parseLong(code.replaceAll("\\D", "") + "0000");
    }
    
    for (int i = 0; i < biList.size(); i++) {
          ZcPProMitemBi biItem = (ZcPProMitemBi) biList.get(i);
          if (biItem.getZcBiNo() == null || "".equals(biItem.getZcBiNo().trim())) {//自筹资金
            if (biItem.getZcUseBiId() == null || "".equals(biItem.getZcUseBiId().trim())) {
              biItem.setZcUseBiId(++vou_id + "");
            }
            continue;
          }
    
          //biItem.getZcBiNo()不为空时,资金为指标，执行下面的操作
          boolean isUpd = false;//判断当前操作时已有采购计划保存后的编辑更新操作，true:更新操作；false：新增操作
    
          for (int j = biListOld.size() - 1; j >= 0; j--) {
            ZcPProMitemBi oldBiItem = (ZcPProMitemBi) biListOld.get(j);
            if (biItem.getZcBiNo().equals(oldBiItem.getZcBiNo())) {
              // biList.get(i).setZcBiJhuaSum(biList.get(i).getZcBiJhuaSum().subtract(bis.get(j).getZcBiJhuaSum()));
              if (biItem.getZcBiJhuaSum().compareTo(oldBiItem.getZcBiJhuaSum()) != 0 && allBiNoLst.contains(biItem.getZcBiNo())) {//同一指标编号，存在金额不一致，需要更新了
                upds.add(biToMap(biItem));
              }
              isUpd = true;
              biListOld.remove(j);//这里移除已经比较过的指标,方便最后计算需要释放的指标，见下面的for循环AAA
              break;
            }
          }
          if (!isUpd) {
            biItem.setZcUseBiId(++vou_id + "");
            saves.add(biToMap(biItem));
          }
    }
    //AAA:这里指的是后台查出的当前计划编号的指标资金数据,前台传过来的数据里已经不含了,因此需要删除,恢复指标占用金额，
    for (int i = 0; i < biListOld.size(); i++) {
      ZcPProMitemBi oldMbi = (ZcPProMitemBi) biListOld.get(i);
      if (!(oldMbi.getZcBiNo() == null || "".equals(oldMbi.getZcBiNo().trim())) && allBiNoLst.contains(oldMbi.getZcBiNo())) {
        dels.add(biToMap(oldMbi));
      }
    }

    Map result = new HashMap();
    if (dels.size() > 0) {
      result.put(DEL_BUDGET, createData(dels));
    }
    if (saves.size() > 0) {
      result.put(SAVE_BUDGET, createData(saves));
    }
    if (upds.size() > 0) {
      result.put(UPD_BUDGET, createData(saves));
    }
    return result;
  }

  /**
   * @param zcPProMitemBiDao
   * @return
   */
  private String getVoucherId() {
    long vou_id = 0;
    //    String vouId = code.replaceAll("\\D", "");
    //    vouId = baseDao.getMaxVouId(vouId);
    //    if (vouId != null && !"".equals(vouId)) {
    //      vou_id = Long.parseLong(vouId);
    //    } else {
    //      vou_id = Long.parseLong(code.replaceAll("\\D", "") + "0000");
    //    }

    return Guid.genID();
  }

  /**
   * 汽修保存时，调用指标接口
   * 
   * @param qxDao
   *            政府采购指标表dao
   * @param flag
   *            是否需要指标接口
   * @param qxCode
   *            采购计划编号
   * @param biList
   *            界面输入的采购指标列表,zcqxbi
   * @return
   * @throws Exception
   */
  public Map getSaveBudgetByZcQx(IZcQxDao qxDao, IBaseDao baseDao, boolean flag, ZcQx qx, List biList) {

    if (!flag) {
      return null;
    }

    // List<Map<String, Object>> saves = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> upds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> updolds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> dels = new ArrayList<Map<String,
    // Object>>();

    List saves = new ArrayList();
    List updates = new ArrayList();
    List dels = new ArrayList();

    //将当前汽修单存在后台的资金数据查出来，用于和当前前台传过来的汽修单带的资金数据进行比较，方便更新指标占用情况，这种情况适用于已经存在的汽修单进行再编辑后的保存
    List biListOld = qxDao.selectBiByQxCode(qx.getQxCode());

    if (biListOld == null) {
      biListOld = new ArrayList();
    }
    if (biList == null) {
      biList = new ArrayList();
    }

    StringBuffer ids = new StringBuffer("''");
    for (int i = 0; i < biList.size(); i++) {
      ZcQxBi mbi = (ZcQxBi) biList.get(i);
      if (mbi.getZcBiNo() != null && mbi.getZcBiNo().trim().length() > 0) {
        ids.append(",'").append(mbi.getZcBiNo()).append("'");
      }
    }
    for (int i = 0; i < biListOld.size(); i++) {
      ZcQxBi mbi = (ZcQxBi) biListOld.get(i);
      if (mbi.getZcBiNo() != null && mbi.getZcBiNo().trim().length() > 0) {
        ids.append(",'").append(mbi.getZcBiNo()).append("'");
      }
    }
    //将前台传过来指标编号和后台存在的指标编号，去数据库重新查询一下，去一下重，形成一个指标编号列表，涵盖当前采购计划涉及的前台指标数据和后台指标数（后台的只有编辑情况下存在）
    List allBiNoLst = baseDao.query("VwBudgetGp.getExistsBudget", ids.toString());
    
    
    boolean isUpd = false;//判断当前操作时已有采购计划保存后的编辑更新操作，true:更新操作；false：新增操作

    for (int i = 0; i < biList.size(); i++) {
      ZcQxBi mbi = (ZcQxBi) biList.get(i);
      if (mbi.getZcBiNo() == null || "".equals(mbi.getZcBiNo().trim())) {//自筹资金
        if (mbi.getZcUseBiId() == null || "".equals(mbi.getZcUseBiId().trim())) {
          mbi.setZcUseBiId(getVoucherId());
        }
        continue;
      }

      //biItem.getZcBiNo()不为空时,资金为玉树指标，执行下面的操作

      //判断当前操作时已有采购计划保存后的编辑更新操作，true:更新操作；false：新增操作
      isUpd = false;

      for (int j = biListOld.size() - 1; j >= 0; j--) {
        ZcQxBi oldBiItem = (ZcQxBi) biListOld.get(j);
        if (mbi.getZcBiNo().equals(oldBiItem.getZcBiNo())) {
          // biList.get(i).setZcBiJhuaSum(biList.get(i).getZcBiJhuaSum().subtract(bis.get(j).getZcBiJhuaSum()));
          if (mbi.getZcBiJhuaSum().compareTo(oldBiItem.getZcBiJhuaSum()) != 0 && allBiNoLst.contains(mbi.getZcBiNo())) {//同一指标编号，存在金额不一致，需要更新了
            updates.add(biToMap(mbi));
          }
          isUpd = true;
          biListOld.remove(j);
          break;
        }
      }
      if (!isUpd) {
        mbi.setZcUseBiId(getVoucherId());
        saves.add(biToMap(mbi));
      }
    }
    //这里指的是后台查出的当前计划编号的指标资金数据,前台传过来的数据里已经不含了,因此需要删除,恢复指标占用金额，
    for (int i = 0; i < biListOld.size(); i++) {
      ZcQxBi oldMbi = (ZcQxBi) biListOld.get(i);
      if (!(oldMbi.getZcBiNo() == null || "".equals(oldMbi.getZcBiNo().trim())) && allBiNoLst.contains(oldMbi.getZcBiNo())) {
        dels.add(biToMap(oldMbi));
      }
    }

    Map result = new HashMap();
    if (dels.size() > 0) {
      result.put(DEL_BUDGET, createData(dels));
    }
    if (updates.size() > 0) {
      result.put(UPD_BUDGET, createData(updates));
    }
    if (saves.size() > 0) {
      result.put(SAVE_BUDGET, createData(saves));
    }
    return result;
  }

  private static Object qxbiToMap(ZcQxBi bi) {
    // TCJLODO Auto-generated method stub
    if (bi == null) {
      return null;
    }
    Map dto = new HashMap();
    if (dto != null) {
      dto.put(VOU_MONEY, bi.getZcBiJhuaSum());
      dto.put(VOU_ID, bi.getZcUseBiId());
      dto.put(FROM_CTRL_ID, bi.getZcBiNo());
    }

    return dto;
  }

  /**
   * 删除采购计划时，调用指标接口
   * 
   * @param zcPProMitemBiDao
   *            政府采购指标表dao
   * @param flag
   *            是否需要指标接口
   * @param code
   *            采购计划编号
   * @throws Exception
   */
  public Map getDelBudget(IZcPProMitemBiDao zcPProMitemBiDao, boolean flag, String code) {
    if (!flag) {
      return null;
    }

    // List<Map<String, Object>> dels = new ArrayList<Map<String,
    // Object>>();
    List dels = new ArrayList();
    ZcPProMitemBiExample bi = new ZcPProMitemBiExample();

    bi.createCriteria().andZcMakeCodeEqualTo(code);
    List bis = (List) zcPProMitemBiDao.selectByExample(bi);

    if (bis == null) {
      return null;
    }

    for (int i = 0; i < bis.size(); i++) {
      ZcPProMitemBi mbi = (ZcPProMitemBi) bis.get(i);
      if (!(mbi.getZcBiNo() == null || "".equals(mbi.getZcBiNo()))) {
        dels.add(biToMap(mbi));
      }
    }

    if (dels.size() > 0) {
      Map result = new HashMap();
      //      result.put(CREATE_USER, CREATE_USER_VEL);
      result.put(DEL_BUDGET, createData(dels));
      return result;
    }
    return null;
  }

  /**
   * 补充合同保存时，调用指标接口
   * 
   * @param zcXmcgHtBiDao
   *            政府采购指标表dao
   * @param flag
   *            是否需要指标接口
   * @param code
   *            采购计划编号
   * @param htCd
   *            合同编号
   * @param biList
   *            界面输入的指标列表
   * @return
   * @throws Exception
   */
  public Map getSaveBudgetBySubHt(IZcXmcgHtBiDao zcXmcgHtBiDao, String code, String htCd, List biList) {

    // List<Map<String, Object>> saves = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> upds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> updolds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> dels = new ArrayList<Map<String,
    // Object>>();

    List saves = new ArrayList();
    List updates = new ArrayList();
    List dels = new ArrayList();

    ZcXmcgHtBiExample bi = new ZcXmcgHtBiExample();

    bi.createCriteria().andZcHtCodeEqualTo(htCd);
    List bis = zcXmcgHtBiDao.selectByExample(bi);

    if (bis == null) {
      bis = new ArrayList();
    }

    boolean isUpd = false;

    for (int i = 0; i < biList.size(); i++) {
      ZcXmcgHtBi hbi = (ZcXmcgHtBi) biList.get(i);
      if (hbi.getZcBiNo() == null || "".equals(hbi.getZcBiNo())) {
        if (hbi.getZcUseBiId() == null || "".equals(hbi.getZcUseBiId())) {
          hbi.setZcUseBiId(getVoucherId());
        }
        continue;
      }
      isUpd = false;
      for (int j = bis.size() - 1; j >= 0; j--) {
        ZcXmcgHtBi oldBi = (ZcXmcgHtBi) bis.get(j);
        if (hbi.getZcBiNo().equals(oldBi.getZcBiNo())) {
          if (hbi.getZcBiBcsySum().compareTo(oldBi.getZcBiBcsySum()) != 0) {//同一指标编号，存在金额不一致，需要更新了
            updates.add(biToMap(hbi));
          }
          hbi.setZcUseBiId(oldBi.getZcUseBiId());
          isUpd = true;
          bis.remove(j);
          break;
        }
      }
      if (!isUpd) {
        hbi.setZcUseBiId(getVoucherId());
        saves.add(biToMap(hbi));
      }
    }
    for (int i = 0; i < bis.size(); i++) {
      ZcXmcgHtBi t3 = (ZcXmcgHtBi) bis.get(i);
      if (!(t3.getZcBiNo() == null || "".equals(t3.getZcBiNo()))) {
        dels.add(biToMap(t3));
      }
    }

    Map result = new HashMap();
    if (dels.size() > 0) {
      result.put(DEL_BUDGET, createData(updates));
    }
    if (updates.size() > 0) {
      result.put(UPD_BUDGET, createData(updates));
    }
    if (saves.size() > 0) {
      result.put(SAVE_BUDGET, createData(saves));
    }
    //    if (result.size() > 0 && !result.isEmpty()) {
    //      result.put(CREATE_USER, CREATE_USER_VEL);
    //    }
    return result;
  }

  /**
   * 删除补充合同时，调用指标接口
   * 
   * @param zcXmcgHtBiDao
   *            政府采购指标表dao
   * @param flag
   *            是否需要指标接口
   * @param htCd
   *            补充合同编号
   * @throws Exception
   */
  public Map getDelBudget(IZcXmcgHtBiDao zcXmcgHtBiDao, boolean flag, String htCd) {
    if (!flag) {
      return null;
    }

    // List<Map<String, Object>> dels = new ArrayList<Map<String,
    // Object>>();
    List dels = new ArrayList();

    ZcXmcgHtBiExample bi = new ZcXmcgHtBiExample();

    bi.createCriteria().andZcHtCodeEqualTo(htCd);
    // List<ZcXmcgHtBi> bis = (List<ZcXmcgHtBi>) zcXmcgHtBiDao
    // .selectByExample(bi);
    List bis = zcXmcgHtBiDao.selectByExample(bi);

    if (bis == null) {
      return null;
    }

    for (int i = 0; i < bis.size(); i++) {
      ZcXmcgHtBi hb = (ZcXmcgHtBi) bis.get(i);
      if (!(hb.getZcBiNo() == null || "".equals(hb.getZcBiNo()))) {
        dels.add(biToMap(hb));
      }
    }

    if (dels.size() > 0) {
      Map result = new HashMap();
      //      result.put(CREATE_USER, CREATE_USER_VEL);
      result.put(DEL_BUDGET, createData(dels));
      return result;
    }
    return null;
  }

  /**
   * 支付方式变更时，调用指标接口
   * 
   * @param baseDao
   * @param flag
   *            是否需要指标接口
   * @param code
   *            采购计划编号
   * @param chgid
   *            变更编号
   * @param biList
   *            界面输入的指标列表
   * @return
   * @throws Exception
   */
  public Map getBalChgSaveBudget(BaseDao baseDao, boolean flag, String code, String chgid, List biList) {
    if (!flag) {
      return null;
    }

    // List<Map<String, Object>> saves = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> upds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> updolds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> dels = new ArrayList<Map<String,
    // Object>>();
    List saves = new ArrayList();
    List upds = new ArrayList();
    List updolds = new ArrayList();
    List dels = new ArrayList();

    boolean isUpd = false;

    ElementConditionDto dto = new ElementConditionDto();
    dto.setZcText0(chgid);
    List bis = baseDao.query("ZC_P_PRO_BAL_CHG_BI.abatorgenerated_selectByElementChgId", dto);
    if (bis == null || bis.size() == 0) {

      ZcPProMitemBiExample bi = new ZcPProMitemBiExample();

      bi.createCriteria().andZcMakeCodeEqualTo(code);
      bis = baseDao.query("ZC_P_PRO_MITEM_BI.ibatorgenerated_selectByExample", bi);

      if (bis == null) {
        bis = new ArrayList();
      }
    }

    for (int i = 0; i < biList.size(); i++) {
      ZcPProBalChgBi cbi = (ZcPProBalChgBi) biList.get(i);
      if (cbi.getZcBiNo() == null || "".equals(cbi.getZcBiNo())) {
        if (cbi.getZcUseBiId() == null || "".equals(cbi.getZcUseBiId())) {
          cbi.setZcUseBiId(getVoucherId());
        }
        continue;
      }
      isUpd = false;
      for (int j = bis.size() - 1; j >= 0; j--) {
        ZcPProMitemBi mbi = (ZcPProMitemBi) bis.get(j);
        if (cbi.getZcBiNo().equals(mbi.getZcBiNo())) {
          if (cbi.getZcBiJhuaSum().compareTo(mbi.getZcBiJhuaSum()) != 0) {
            upds.add(biToMap(cbi));
            updolds.add(biToMap(mbi));
          }
          cbi.setZcUseBiId(mbi.getZcUseBiId());
          isUpd = true;
          bis.remove(j);
          break;
        }
      }
      if (!isUpd) {
        cbi.setZcUseBiId(getVoucherId());
        saves.add(biToMap(cbi));
      }
    }
    for (int i = 0; i < bis.size(); i++) {
      ZcPProMitemBi mbi = (ZcPProMitemBi) bis.get(i);
      if (!(mbi.getZcBiNo() == null || "".equals(mbi.getZcBiNo()))) {
        dels.add(biToMap(mbi));
      }
    }

    Map result = new HashMap();
    if (dels.size() > 0) {
      result.put(DEL_BUDGET, createData(dels));
    }
    if (saves.size() > 0) {
      result.put(SAVE_BUDGET, createData(saves));
    }
    //    if (result.size() > 0 && !result.isEmpty()) {
    //      result.put(CREATE_USER, CREATE_USER_VEL);
    //    }
    return result;
  }

  /**
   * 删除支付方式变更时，调用指标接口
   * 
   * @param baseDao
   * @param flag
   *            是否需要指标接口
   * @param chgid
   *            支付方式变更编号
   * @throws Exception
   */
  public Map getBalChgDelBudget(IBaseDao baseDao, boolean flag, String code, String chgid) {
    if (!flag) {
      return null;
    }

    // List<Map<String, Object>> saves = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> upds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> updolds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> dels = new ArrayList<Map<String,
    // Object>>();

    List saves = new ArrayList();
    List upds = new ArrayList();
    List updolds = new ArrayList();
    List dels = new ArrayList();

    boolean isUpd = false;

    ElementConditionDto dto = new ElementConditionDto();
    dto.setZcText0(chgid);
    List bis = baseDao.query("ZC_P_PRO_BAL_CHG_BI.abatorgenerated_selectByElementChgId", dto);

    ZcPProMitemBiExample bi = new ZcPProMitemBiExample();

    bi.createCriteria().andZcMakeCodeEqualTo(code);
    List biList = baseDao.query("ZC_P_PRO_MITEM_BI.ibatorgenerated_selectByExample", bi);

    if (biList == null) {
      biList = new ArrayList();
    }

    for (int i = 0; i < biList.size(); i++) {
      ZcPProMitemBi mbi = (ZcPProMitemBi) biList.get(i);
      if (mbi.getZcBiNo() == null || "".equals(mbi.getZcBiNo())) {
        if (mbi.getZcUseBiId() == null || "".equals(mbi.getZcUseBiId())) {
          mbi.setZcUseBiId(getVoucherId());
        }
        continue;
      }
      isUpd = false;
      for (int j = bis.size() - 1; j >= 0; j--) {
        ZcPProMitemBi tt = (ZcPProMitemBi) bis.get(j);
        if (mbi.getZcBiNo().equals(tt.getZcBiNo())) {
          if (mbi.getZcBiJhuaSum().compareTo(tt.getZcBiJhuaSum()) != 0) {
            upds.add(biToMap(mbi));
            updolds.add(biToMap(tt));
          }
          mbi.setZcUseBiId(tt.getZcUseBiId());
          isUpd = true;
          bis.remove(j);
          break;
        }
      }
      if (!isUpd) {
        mbi.setZcUseBiId(getVoucherId());
        saves.add(biToMap(mbi));
      }
    }
    for (int i = 0; i < bis.size(); i++) {
      ZcPProMitemBi t = (ZcPProMitemBi) bis.get(i);
      if (!(t.getZcBiNo() == null || "".equals(t.getZcBiNo()))) {
        dels.add(biToMap(t));
      }
    }

    Map result = new HashMap();
    if (dels.size() > 0) {
      result.put(DEL_BUDGET, createData(dels));
    }
    if (saves.size() > 0) {
      result.put(SAVE_BUDGET, createData(saves));
    }
    return result;

  }

  /**
   * 支付时，调用指标接口释放指标
   * 
   * @param biDao
   *            IZcPProBalBiDao
   * @param flag
   *            是否需要指标接口
   * @param code
   *            采购计划编号
   * @param balId
   *            支付编号
   * @param isRet
   *            是否有返回单位金额
   * @return
   * @throws Exception
   */
  public Map getBalShifangBudget(IBaseDao baseDao, boolean flag, String code, String balId, boolean isRet) throws ZcBudgetInterfaceException {
    if (!flag) {
      return null;
    }

    // List<ZcPProBalBi> rets = null;
    // Map<String, BigDecimal> del = new HashMap<String, BigDecimal>();
    // List<Map<String, Object>> upds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> updolds = new ArrayList<Map<String,
    // Object>>();
    List rets = null;
    Map delmap = new HashMap();
    List upds = new ArrayList();
    List updOlds = new ArrayList();

    // 查询本次支付记录
    List balBiLst = baseDao.query("ZC_P_PRO_BAL_BI.getBalBudget", balId);

    for (int i = 0; i < balBiLst.size(); i++) {
      ZcPProBalBi bi = (ZcPProBalBi) balBiLst.get(i);
      if (bi.getZcBiNo() == null || bi.getZcBiNo().startsWith(ZcSettingConstants.No_BI)) {
        continue;
      }

      // 未结算金额大于本次支付金额
      if (bi.getZcBiYjjsSum().compareTo(bi.getZcBiBcjsSum()) >= 0) {//这个ZcBiYjjsSum是从后台查询出的指标未支付金额，它=采购计划指标金额-累次已经支付金额合计（不含本次),使用ZcBiYjjsSum作为名称，有歧义
        // 解冻支付金额
        upds.add(biToMap(bi.getZcBiYjjsSum().subtract(bi.getZcBiBcjsSum()), bi));//通过update的模式进行释放金额，所以update到指标系统的指标金额为本次支付后剩余的未支付金额
        updOlds.add(biToMap(bi.getZcBiYjjsSum(), bi));//这个值用于调用支付接口失败后，重新占用上指标--chenjl 20130910
      } else {
        throw new ZcBudgetInterfaceException("支付超额，指标【" + bi.getZcBiNo() + "】剩余未支付金额【" + bi.getZcBiYjjsSum().toString() + "】本次预支付金额【"
          + bi.getZcBiBcjsSum().toString() + "】");
      }
    }

    Map result = new HashMap();

    if (upds.size() > 0) {
      result.put(BudgetUtil.UPD_BUDGET, createData(upds));
    }
    if (updOlds.size() > 0) {
      result.put(BudgetUtil.UPD_BUDGET_OLD, createData(updOlds));
    }
    //    if (result.size() > 0 && !result.isEmpty()) {
    //      result.put(CREATE_USER, CREATE_USER_VEL);
    //    }
    return result;
  }

  /**
   * 支付时，调用指标接口释放指标
   * 
   * @param biDao
   *            IZcPProBalBiDao
   * @param flag
   *            是否需要指标接口
   * @param code
   *            采购计划编号
   * @param balId
   *            支付编号
   * @param isRet
   *            是否有返回单位金额
   * @return
   * @throws Exception
   */
  public Map getQxShifangBudget(IBaseDao baseDao, boolean flag, ZcQx qx) {
    if (!flag) {
      return null;
    }

    // List<ZcPProBalBi> rets = null;
    // Map<String, BigDecimal> del = new HashMap<String, BigDecimal>();
    // List<Map<String, Object>> upds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> updolds = new ArrayList<Map<String,
    // Object>>();
    List rets = null;
    Map del = new HashMap();
    List dels = new ArrayList();

    for (int i = 0; i < qx.getBiList().size(); i++) {
      ZcQxBi bi = (ZcQxBi) qx.getBiList().get(i);
      if (bi.getZcBiNo() == null || bi.getZcBiNo().startsWith(ZcSettingConstants.No_BI)) {
        continue;
      }
      // 解冻支付金额
      dels.add(biToMap(bi));

    }

    Map result = new HashMap();

    if (dels.size() > 0) {
      result.put(BudgetUtil.DEL_BUDGET, createData(dels));//用于指标释放
      result.put(BudgetUtil.UPD_BUDGET_OLD, createData(dels));//用于指标释放成功，调用支付接口出错时，将释放的指标重新占用回去
    }
    //    if (result.size() > 0 && !result.isEmpty()) {
    //      result.put(CREATE_USER, CREATE_USER_VEL);
    //    }
    return result;
  }

  /**
   * 结项释放指标
   * 
   * @param baseDao
   * @param flag
   * @param code
   * @return
   * @throws Exception
   */
  public Map getProEndBudget(IBaseDao baseDao, boolean flag, String code) {
    if (!flag) {
      return null;
    }

    // List<Map<String, Object>> upds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> updolds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> dels = new ArrayList<Map<String,
    // Object>>();

    List upds = new ArrayList();
    List updolds = new ArrayList();
    List dels = new ArrayList();

    List bis = baseDao.query("ZC_YEAR_END.selectBudget", code);

    for (int i = 0; i < bis.size(); i++) {
      ZcPProMitemBi bi = (ZcPProMitemBi) bis.get(i);
      if (bi.getZcBiYjbaSum() == null || bi.getZcBiYjbaSum().compareTo(new BigDecimal(0)) == 0) {
        dels.add(biToMap(bi));
      } else {
        bi.setZcBiJhuaSum(bi.getZcBiYjbaSum());
        upds.add(biToMap(bi));
      }
    }

    Map result = new HashMap();
    if (dels.size() > 0) {
      result.put(DEL_BUDGET, createData(dels));
    }
    return result;
  }

  /**
   * 正常执行调用指标接口
   * 
   * @param map
   * @throws Exception
   */
  //  public static void callService(Map map, String serverAdd) throws Exception {
  //    if (map == null || map.isEmpty()) {
  //      return;
  //    }
  //    // 调用webservice接口
  //    call(map, serverAdd);
  //
  //  }

  /**
   * 程序异常调用指标接口取消操作
   * 
   * @param oldMap
   * @throws Exception
   */
  public void cancelCallService(Map oldMap, int year) throws Exception {
    if (oldMap == null || oldMap.isEmpty()) {
      return;
    }
    Map map = new HashMap();

    if (oldMap.get(UPD_BUDGET_OLD) != null) {
      map.put(UPD_BUDGET, oldMap.get(UPD_BUDGET_OLD));
    }
    // 调用webservice接口
    callService(map, year);
  }

  /**
   * 程序异常调用指标接口取消操作
   * 
   * @param oldMap
   * @throws Exception
   */
  //  public static void cancelCallService(Map oldMap) throws Exception {
  //    AsOption opt = AsOptionUtil.getInstance().getOption("OPT_SERVICE_URL");
  //    if (opt != null) {
  //      String serverAdd = opt.getOptVal();
  //      cancelCallService(oldMap, serverAdd);
  //    } else {
  //      throw new Exception("调用指标接口时出错:没有找到指标借口地址OPT_SERVICE_URL");
  //    }
  //  }

  /**
   * ZcPProMitemBi转换为map
   * 
   * @param name
   * @param bi
   * @return
   * @throws Exception
   */
  private Map biToMap(ZcPProMitemBi bi) {
    if (bi == null) {
      return null;
    }
    Map dto = new HashMap();
   
      dto.put(VOU_MONEY, bi.getZcBiJhuaSum());
      dto.put(VOU_ID, bi.getZcUseBiId());
      dto.put(FROM_CTRL_ID, bi.getZcBiNo());
    

    return dto;
  }

  private Map biToMap(ZcQxBi bi) {
    if (bi == null) {
      return null;
    }
    Map dto = new HashMap();
   
      dto.put(VOU_MONEY, bi.getZcBiJhuaSum());
      dto.put(VOU_ID, bi.getZcUseBiId());
      dto.put(FROM_CTRL_ID, bi.getZcBiNo());    

    return dto;
  }
  /**
   * ZcXmcgHtBi转换为map
   * 
   * @param code
   * @param bi
   * @return
   * @throws Exception
   */
  private Map biToMap(ZcXmcgHtBi bi) {
    if (bi == null) {
      return null;
    }
    Map dto = new HashMap();
    if (dto != null) {
      dto.put(VOU_MONEY, bi.getZcBiBcsySum());
      dto.put(VOU_ID, bi.getZcUseBiId());
      dto.put(FROM_CTRL_ID, bi.getZcBiNo());
    }

    return dto;
  }

  /**
   * 
   * @param money
   * @param bi
   * @return
   * @throws Exception
   */
  private Map biToMap(BigDecimal money, ZcPProBalBi bi) {
    if (bi == null) {
      return null;
    }
    Map dto = new HashMap();
    if (dto != null) {
      dto.put(VOU_MONEY, money);
      dto.put(VOU_ID, bi.getZcHtBiNo());
      dto.put(FROM_CTRL_ID, bi.getZcBiNo());
    }

    return dto;
  }

  /**
   * 
   * @param money
   * @param bi
   * @return
   * @throws Exception
   */
  private Map biToMap(BigDecimal money, ZcQxBi bi) {
    if (bi == null) {
      return null;
    }
    Map dto = new HashMap();
    if (dto != null) {
      dto.put(VOU_MONEY, money);
      dto.put(VOU_ID, getVoucherId());
      dto.put(FROM_CTRL_ID, bi.getZcBiNo());
    }

    return dto;
  }

  /**
   * 
   * @param money
   * @param bi
   * @return
   * @throws Exception
   */
  private Map biToMap(ZcQbBi bi) {
    if (bi == null) {
      return null;
    }
    Map dto = new HashMap();
    if (dto != null) {
      dto.put(VOU_MONEY, bi.getZcBiJhuaSum());
      dto.put(VOU_ID, bi.getZcUseBiId());
      dto.put(FROM_CTRL_ID, bi.getZcBiNo());
    }

    return dto;
  }

  /**
   * 转换成webservice需要的string
   * 
   * @param list
   * @return
   */
  public String createData(List list) {
    if (list == null || list.size() == 0) {
      return "";
    }

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < list.size(); i++) {
      if (sb.length() > 0) {
        sb.append(RECORD_ISP);
      }
      Map map = (Map) list.get(i);
      sb.append(map.get(FROM_CTRL_ID)).append(ITEM_ISP);
      sb.append(map.get(VOU_MONEY)).append(ITEM_ISP);
      sb.append(map.get(VOU_ID));
    }
    return sb.toString();
  }

  private void callService(Map map) throws ZcBudgetInterfaceException {
    // TCJLODO Auto-generated method stub
    //先注释掉，目前没有指标借口
    try {
      String serverAdd = "";
      AsOption opt = AsOptionUtil.getInstance().getOption("OPT_ZC_BUDGET_SERVER_URL");
      if (opt != null) {
        serverAdd = opt.getOptVal();
      } else {
        throw new NullArgumentException("调用指标接口时出错:没有找到指标系统地址，请检查数据配置值：OPT_ZC_BUDGET_SERVER_URL");
      }

      String timeoutStr=AsOptionUtil.getInstance().getOptionVal("OPT_ZC_BUDGET_INTERFACE_TIME_OUT");
      if(timeoutStr==null || timeoutStr.trim().equals("")){
        timeoutStr="600000";
      }
      Service service = new Service();
      Call call = (Call) service.createCall();
      call.setTargetEndpointAddress(new URL(serverAdd));
      call.setOperationName(new QName(serverAdd, "invoke"));
      call.setTimeout(new Integer(timeoutStr));
      Map result = (Map) call.invoke(new Object[] { map });
      System.out.println(result);

      if (FALSE.equalsIgnoreCase(result.get(RESULT).toString())) {
        throw new ZcBudgetInterfaceException(result.get(MESSAGE).toString());
      }

    } catch (ServiceException e) {
      System.out.println("指标传参==>" + map.toString());
      throw new ZcBudgetInterfaceException("调用指标接口时出错:" + e.getMessage(), e);
    } catch (MalformedURLException e) {
      // TCJLODO Auto-generated catch block
      throw new ZcBudgetInterfaceException("调用指标接口时出错:" + e.getMessage(), e);
    } catch (RemoteException e) {
      // TCJLODO Auto-generated catch block
      throw new ZcBudgetInterfaceException("调用指标接口时出错:" + e.getMessage(), e);
    }
  }

  public Map getSaveBudgetByZcQb(IZcQbDao qbDao, IBaseDao baseDao, boolean useBi, ZcQb qb, List biList) {
    // TCJLODO Auto-generated method stub
    if (!useBi) {
      return null;
    }
    List saves = new ArrayList();
    List dels = new ArrayList();
    List updates = new ArrayList();

    //将当前计划存在后台的资金数据查出来，用于和当前前台传过来的计划带的资金数据进行比较，方便更新指标占用情况，这种情况适用于已经存在的采购计划进行再编辑后的保存
    List biListOld = qbDao.getQbBiLst(qb.getQbCode());

    if (biListOld == null) {
      biListOld = new ArrayList();
    }
    if (biList == null) {
      biList = new ArrayList();
    }

    StringBuffer ids = new StringBuffer("''");
    for (int i = 0; i < biList.size(); i++) {
      ZcQbBi mbi = (ZcQbBi) biList.get(i);
      if (mbi.getZcBiNo() != null && mbi.getZcBiNo().trim().length() > 0) {
        ids.append(",'").append(mbi.getZcBiNo()).append("'");
      }
    }
    for (int i = 0; i < biListOld.size(); i++) {
      ZcQbBi mbi = (ZcQbBi) biListOld.get(i);
      if (mbi.getZcBiNo() != null && mbi.getZcBiNo().trim().length() > 0) {
        ids.append(",'").append(mbi.getZcBiNo()).append("'");
      }
    }
    //将前台传过来指标编号和后台存在的指标编号，去数据库重新查询一下，去一下重，形成一个指标编号列表，涵盖当前采购计划涉及的前台指标数据和后台指标数（后台的只有编辑情况下存在）
    List allBiNoLst = baseDao.query("VwBudgetGp.getExistsBudget", ids.toString());

    boolean isUpd = false;//判断当前操作时已有采购计划保存后的编辑更新操作，true:更新操作；false：新增操作

    for (int i = 0; i < biList.size(); i++) {
      ZcQbBi biItem = (ZcQbBi) biList.get(i);
      if (biItem.getZcBiNo() == null || "".equals(biItem.getZcBiNo().trim())) {//自筹资金
        if (biItem.getZcUseBiId() == null || "".equals(biItem.getZcUseBiId().trim())) {
          biItem.setZcUseBiId(getVoucherId());
        }
        continue;
      }

      //biItem.getZcBiNo()不为空时,资金为玉树指标，执行下面的操作

      //判断当前操作时已有采购计划保存后的编辑更新操作，true:更新操作；false：新增操作
      isUpd = false;

      for (int j = biListOld.size() - 1; j >= 0; j--) {
        ZcQbBi oldBiItem = (ZcQbBi) biListOld.get(j);
        if (biItem.getZcBiNo().equals(oldBiItem.getZcBiNo())) {
          // biList.get(i).setZcBiJhuaSum(biList.get(i).getZcBiJhuaSum().subtract(bis.get(j).getZcBiJhuaSum()));
          if (biItem.getZcBiJhuaSum().compareTo(oldBiItem.getZcBiJhuaSum()) != 0 && allBiNoLst.contains(biItem.getZcBiNo())) {//同一指标编号，存在金额不一致，需要更新了
            updates.add(biToMap(biItem));
          }
          isUpd = true;
          biListOld.remove(j);
          break;
        }
      }
      if (!isUpd) {
        biItem.setZcUseBiId(getVoucherId());
        saves.add(biToMap(biItem));
      }
    }
    //这里指的是后台查出的当前计划编号的指标资金数据,前台传过来的数据里已经不含了,因此需要删除,恢复指标占用金额，
    for (int i = 0; i < biListOld.size(); i++) {
      ZcQbBi oldMbi = (ZcQbBi) biListOld.get(i);
      if (!(oldMbi.getZcBiNo() == null || "".equals(oldMbi.getZcBiNo().trim())) && allBiNoLst.contains(oldMbi.getZcBiNo())) {
        dels.add(biToMap(oldMbi));
      }
    }

    Map result = new HashMap();
    if (dels.size() > 0) {
      result.put(DEL_BUDGET, createData(dels));
    }
    if (updates.size() > 0) {
      result.put(UPD_BUDGET, createData(updates));
    }
    if (saves.size() > 0) {
      result.put(SAVE_BUDGET, createData(saves));
    }
    return result;
  }

  public Map getQbShiFangBudget(IBaseDao baseDao, boolean flag, ZcQb qb) {
    // TCJLODO Auto-generated method stub
    if (!flag) {
      return null;
    }

    // List<ZcPProBalBi> rets = null;
    // Map<String, BigDecimal> del = new HashMap<String, BigDecimal>();
    // List<Map<String, Object>> upds = new ArrayList<Map<String,
    // Object>>();
    // List<Map<String, Object>> updolds = new ArrayList<Map<String,
    // Object>>();
    List rets = null;
    Map del = new HashMap();
    List dels = new ArrayList();

    for (int i = 0; i < qb.getBiList().size(); i++) {
      ZcQbBi bi = (ZcQbBi) qb.getBiList().get(i);
      if (bi.getZcBiNo() == null || bi.getZcBiNo().startsWith(ZcSettingConstants.No_BI)) {
        continue;
      }
      // 解冻支付金额
      dels.add(biToMap(bi));

    }

    Map result = new HashMap();

    if (dels.size() > 0) {
      result.put(BudgetUtil.DEL_BUDGET, createData(dels));//用于指标释放
      result.put(BudgetUtil.UPD_BUDGET_OLD, createData(dels));//用于指标释放成功，调用支付接口出错时，将释放的指标重新占用回去
    }
    //    if (result.size() > 0 && !result.isEmpty()) {
    //      result.put(CREATE_USER, CREATE_USER_VEL);
    //    }
    return result;
  }

  public void callService(Map map, int year) throws ZcBudgetInterfaceException {
    // TCJLODO Auto-generated method stub
    if (map == null || map.size() == 0)
      return;
    //先释放指标
    String delStr = (String) map.get(DEL_BUDGET);
    delBudget(delStr, year);

    //更新原指标占用
    String updateStr = (String) map.get(UPD_BUDGET);
    updateBudget(updateStr, year);

    //新占用指标
    String zhanYongStr = (String) map.get(SAVE_BUDGET);
    zhanYongBudget(zhanYongStr, year);
  }
  public void callShifangService(Map map, int year) throws ZcBudgetInterfaceException {
    // TCJLODO Auto-generated method stub
    if (map == null || map.size() == 0)
      return;
    //释放指标
    String shifangStr = (String) map.get(UPD_BUDGET);
    updateBudget(shifangStr, year);   
  }
  
  private void delBudget(String delStr, int year) throws ZcBudgetInterfaceException {
    // TCJLODO Auto-generated method stub
    if (delStr == null || delStr.trim().length() == 0)
      return;
    Map budgetMap = new HashMap();
    budgetMap.put("data_type", DEL_BUDGET);
    budgetMap.put("data", delStr);
    budgetMap.put("create_user", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_BUDGET_CREATE_USER));
    budgetMap.put("set_year", "" + year);
    budgetMap.put("rg_code", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_BUDGET_RG_CODE));

    callService(budgetMap);

  }
  private void zhanYongBudget(String zhanYongStr, int year) throws ZcBudgetInterfaceException {
    // TCJLODO Auto-generated method stub
    if (zhanYongStr == null || zhanYongStr.trim().length() == 0)
      return;
    Map budgetMap = new HashMap();
    budgetMap.put("data_type", SAVE_BUDGET);
    budgetMap.put("data", zhanYongStr);
    budgetMap.put("create_user", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_BUDGET_CREATE_USER));
    budgetMap.put("set_year", "" + year);
    budgetMap.put("rg_code", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_BUDGET_RG_CODE));

    callService(budgetMap);

  }

  private void updateBudget(String shifangStr, int year) throws ZcBudgetInterfaceException {
    // TCJLODO Auto-generated method stub
    if (shifangStr == null || shifangStr.trim().length() == 0)
      return;

    Map budgetMap = new HashMap();
    budgetMap.put("data_type", UPD_BUDGET);
    budgetMap.put("data", shifangStr);
    budgetMap.put("create_user", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_BUDGET_CREATE_USER));
    budgetMap.put("set_year", "" + year);
    budgetMap.put("rg_code", AsOptionUtil.getInstance().getOptionVal(OPT_ZC_BUDGET_RG_CODE));

    callService(budgetMap);
  }

}
