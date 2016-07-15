package com.ufgov.zc.client.zc.ztb.util;

import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.zc.ztb.model.DetailsType;
import com.ufgov.zc.common.system.constants.ZcElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;

public class GV {
  private static JFrame currFrame = null;

  public static JFrame getCurrFrame() {
    return currFrame;
  }

  public static void setCurrFrame(JFrame currFrame) {
    System.out.println(currFrame.getClass());
    GV.currFrame = currFrame;
  }

  public static boolean isInit = false;

  /**
   * NODE NAME
   */
  public final static String MAIN_TBFRAME_TITLE = "投标文件制作软件";

  public final static String MAIN_ZBFRAME_TITLE = "招标文件制作软件";

  public final static String DB_CONFIG_TITLE = "配置";

  public final static String DB_IMPORT_TITLE = "数据库同步导入";

  public final static String DB_IMPORT_PROJECT_LIST = "项目列表";

  public final static String DB_IMPORT_BAGDETAILS_FILE_LIST = "文件列表";

  public final static String ADD_BAG_DETAILS = "当前节点下可新增选项";

  public final static String XML_CHAR_CODE = "GBK"; //xml文件编码

  public final static String FILE_CHAR_CODE = "GBK";//文件默认的编码格式

  public final static String NODE_NAME_BUSINESS = "商务部分";

  public final static String NODE_NAME_TECH = "技术部分";

  public final static String NODE_NAME_TBYLB = "开标一览表";

  public final static String NODE_NAME_BJYLB = "报价一览表";

  public static final String NODE_NAME_ZB = "招标文件_竞争性谈判文件_单一来源采购文件_协议供货二次谈判文件";

  public static final String NODE_NAME_TB = "投标文件_响应文件";

  public static final String NODE_NAME_RESPONSE_POINT = "评标(审)要素一览表";

  /**
   * 显示控制
   */
  public final static String DIS_ZHAOBIAO_ONLY = "ZB";

  public final static String DIS_TOUBIAO_ONLY = "TB";

  public final static String DIS_BOTH = "BOTH";

  /**
   * NODE CODE
   */
  public final static String NODE_CODE_ROOT = "ROOT";

  public final static String NODE_CODE_BUSINESS = "PACK_BUSINESS_";//+pack.getNo()

  public final static String NODE_CODE_TECH = "PACK_TECH_";//++pack.getNo()

  /*
  * 当前支持的节点类型
  */
  public final static String NODE_TYPE_ROOT = "ROOT";

  public final static String NODE_TYPE_DOC = "DOC";

  public final static String NODE_TYPE_TABLE = "TABLE";

  public final static String NODE_TYPE_EXCEL = "EXCEL";

  //开标一览表
  public final static String NODE_TYPE_TBYLB = "TBYLB";

  //二次报价表
  public final static String NODE_TYPE_ECBJB = "ECBJB";

  public final static String NODE_TYPE_PROJECT = "PROJECT";

  public final static String NODE_TYPE_DB = "PROJECT";

  public final static String NODE_TYPE_BAG_DETAIL = "BAG_DETAIL";

  public final static String NODE_TYPE_PACK_DETAIL = "PACK_DETAIL";

  public final static String NODE_TYPE_HAS_CHILDREN = "HAS_CHILDREN";

  public final static String NODE_TYPE_DIR = "DIR";

  public final static String NODE_TYPE_ZB = "TYPE_ZB";

  public final static String NODE_TYPE_TB = "TYPE_TB";

  public final static String NODE_TYPE_PACK_BUSINESS = "PACK_BUSINESS";

  public final static String NODE_TYPE_PACK_TECH = "PACK_TECH";

  public final static String NODE_TYPE_PROPACK = "PROPACK";

  public final static String NODE_TYPE_PACK = "PACK";

  public final static String NODE_TYPE_FOLDER = "FOLDER";

  public final static String NODE_TYPE_PROJECT_LIST = "PROJECTLIST";

  public final static String NODE_TYPE_IMPORT = "IMPORT_PATH";

  public final static String NODE_TYPE_EXPORT = "EXPORT_PATH";

  public final static String NODE_TYPE_STORT = "STORT_PATH";

  public final static String NODE_TYPE_TEMPLATE_BUSINESS = "TEMPLATE_BUSINESS_PATH";

  public final static String NODE_TYPE_TEMPLATE_TECHNICAL = "TEMPLATE_TECHNICAL_PATH";

  public final static String NODE_TYPE_YES_NO = "YES_NO_OPTION";

  public final static String NODE_TYPE_RESPONSE_POINT = "RESPONSE_POINT";

  public static final String NODE_TYPE_PACK_RP = "PACK_RP";

  public static final String NODE_TYPE_RP_ROOT = "RP_ROOT";

  public final static int BUFFER_SIZE = 4096;

  public final static String IMPORT_FILE_DIR = "resource\\impfiles";

  public final static String EXPORT_FILE_DIR = "resource\\expfiles";

  //增加需求文件的路径
  public final static String REQ_FILE_DIR = "resource\\reqfiles";

  public final static String TEMPLATE_FILE_DIR = "resource\\templatefiles";

  public final static String TEMPLATE_BUSINESS_FILE_DIR = "resource\\templatefiles\\business";

  public final static String TEMPLATE_TECHNICAL_FILE_DIR = "resource\\templatefiles\\technical";

  public final static String PROJECTS_CONFIG_XML = "resource\\impfiles\\projects.xml";

  public final static String BUSINESS_CONFIG_XML = "resource\\impfiles\\std_business.xml";

  public final static String TECHNICAL_CONFIG_XML = "resource\\impfiles\\std_technical.xml";

  public final static String TEMPLATES_CONFIG_XML = "resource\\templatefiles\\templates.xml";

  public final static String TEMPLATE_CONFIG_XML = "tplinfo.xml";

  public final static String DB_CONFIG_XML = "resource\\impfiles\\dbconfig.xml";

  public final static String EMPTY_DOC_PATH = "resource\\impfiles\\empty.doc";

  public final static String EMPTY_TPL_DOC_PATH = "resource\\templatefiles\\empty.doc";

  public final static String SUFFIX_DOC = ".doc";

  public final static String SUFFIX_XML = ".xml";

  public final static String SUFFIX_EXCEL = ".xls";

  public final static String SUFFIX_TABLE = ".config";

  public final static String SUFFIX_TABLE_SETTING = ".config.setting";

  public static final String SUFFIX_ZTB_EN_MEG = ".ztb.en.meg";

  public static final String DEFAULT_CONFIG_DIR = "c:\\ufgov\\ztbconfig\\";

  public static final String AVAILABLE_TEMPLATES = "模板库中当前可用模板列表";

  //节点来源：10为自己创建、20为来自项目、30为来自模板、100为其它
  public static final int NODE_SOURCE_FORM_SELF = 10;

  public static final int NODE_SOURCE_FROM_PROJ = 20;

  public static final int NODE_SOURCE_FROM_MOLD = 30;

  public static final int NODE_SOURCE_FROM_SYS = 30;

  public static final int NODE_SOURCE_FORM_OTHER = 100;

  public static final String PROJECT_INFO_XML_SUFFIX = "_project_info.xml";

  /**
   * AS_OPTION 外网服务器地址OPT_ID列值
   */
  public static final String OUT_WEB_SERVER_IP_ADDRESS = "ZC_EB_OUT_WEB_SERVER_IP_ADDRESS";

  public static final String REG_URL = "http://([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|25[0-5])(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}((:\\d+)|(?))/GB/$";

  /**
   * 招标书模板压缩文件后缀
   */
  public static final String TEMPLATE_ZIP = "tpl";

  public static final String TEMPLATE_APPTYPE = "_APT";

  public static final String SUFFIX_ZTB = "ztb";

  /**
   * 表格行次列背景色，在JTableCellRenderers类中被引用
   */
  public static final Color HANGCI_COLUMN_BACKGROUND_COLOR = new Color(219, 112, 147);

  public static final String KBYLB_OPERATOR_DESC = "说明：【开标一览表】中必须包含一列【总价/折扣/折扣率】列类型的数据列，其数据类型必为【数字】型.";

  public static final String MAIN_TTBFRAME_TITLE = "投标书上传专版";

  public static final String NODE_CODE_OTHER = "PACK_OTHER_";

  public static final String NODE_CODE_ZB = "CODE_ZB_";

  public static final String NODE_CODE_TB = "CODE_TB_";

  public static final String NODE_NAME_ZBTB = "招标文件投标文件";

  public static final String NODE_NAME_ECBJB = "再次报价表";

  public static final String NODE_NAME_RP_TREE = "RPTree";

  //文件拷贝中
  public static final String PROGRESS_STATUS_READY = "READY";

  public static final String PROGRESS_STATUS_COPYING = "FILE_COPY";

  public static final String PROGRESS_STATUS_COPY_OK = "FILE_COPY_OK";

  public static final String PROGRESS_STATUS_DELETING = "FILE_DELETING";

  public static final String PROGRESS_STATUS_DELETE_OK = "FILE_DELETE_OK";

  public static final String PROGRESS_STATUS_READING = "FILE_READING";

  public static final String PROGRESS_STATUS_READ_OK = "FILE_READ_OK";

  public static final String PROGRESS_STATUS_GETMD5 = "FILE_MD5_GETING";

  public static final String PROGRESS_STATUS_GETMD5_OK = "FILE_MD5_GET_OK";

  public static final String PROGRESS_STATUS_BUILD_FILE = "FILE_BUILDING";

  public static final String PROGRESS_STATUS_BUILD_FILE_OK = "FILE_BUILD_OK";

  public static final String PROGRESS_STATUS_ZIPING = "FILE_ZIPING";

  public static final String PROGRESS_STATUS_ZIP_OK = "FILE_ZIP_OK";

  public static final String PROGRESS_STATUS_UPLOADING = "FILE_UPLOADING";

  public static final String PROGRESS_STATUS_UPLOAD_OK = "FILE_UPLOAD_OK";

  public static final String PROGRESS_STATUS_EXPORTING = "FILE_EXPORTING";

  public static final String PROGRESS_STATUS_EXPORT_OK = "FILE_EXPORT_OK";

  public static final String PROGRESS_STATUS_UPDATING = "DATA_UPDATING";

  public static final String PROGRESS_STATUS_UPDATE_OK = "DATA_UPDATE_OK";

  public static final String PROGRESS_STATUS_SAVING = "FILE_SAVING";

  public static final String PROGRESS_STATUS_SAVE_OK = "FILE_SAVE_OK";

  public static final String PROGRESS_STATUS_CONFIG_CREATING = "CONFIG_FILE_CREATING";

  public static final String PROGRESS_STATUS_CONFIG_CREATE_OK = "CONFIG_FILE_CREATE_OK";

  public static final String PROGRESS_STATUS_EXP_PROJ_READY = "EXP_PROJ_READY";

  public static final String PROGRESS_STATUS_EXP_TPL_READY = "EXP_TPL_READY";

  public static final String PROGRESS_STATUS_CONFIG_CREATE_READY = "CONFIG_CREATE_READY";

  public static final String PROGRESS_STATUS_UPDATE_READY = "UPDATE_READY";

  public static final String PROGRESS_STATUS_EXPORT_READY = "EXPORT_READY";

  public static final String PROGRESS_STATUS_SERVER_CHECKING = "SERVER_CHECKING";

  public static final String PROGRESS_STATUS_UPLOAD_READY = "UPLOAD_READY";

  public static final String PROGRESS_STATUS_ZIP_READY = "ZIP_READY";

  public static final String PROGRESS_STATUS_BUILD_FILE_READY = "BUILD_FILE_READY";

  public static final String PROGRESS_STATUS_GETMD5_READY = "GETMD5_READY";

  public static final String PROGRESS_STATUS_READ_READY = "READ_READY";

  public static final String PROGRESS_STATUS_DELETE_READY = "DELETE_READY";

  public static final String PROGRESS_STATUS_COPY_READY = "COPY_READY";

  public static final String PROGRESS_STATUS_PROJ_CONFIG_CREATING = "PROJ_CONFIG_CREATING";

  public static final String PROGRESS_STATUS_DELETE_ZB = "DELETE_ZB";

  public static final String PROGRESS_STATUS_PROJ_CONFIG_CREATE_READY = "PROJ_CONFIG_READY";

  public static final String PROGRESS_STATUS_ENCODE_READY = "ENCODE_READY";

  public static final String PROGRESS_STATUS_ENCODING = "ENCODING";

  public static final String PROGRESS_STATUS_ENCODE_OK = "ENCODE_OK";

  public static final String PROGRESS_STATUS_ENCODE_FAIL = "ENCODE_FAIL";

  public static final String PROGRESS_STATUS_FEEDBACK_READY = "FEEDBACK_READY";

  public static final String PROGRESS_STATUS_FEEDBACK_OK = "FEEDBACK_OK";

  public static final String PROGRESS_STATUS_READING_ONLY = "READING_ONLY";

  public static final String PROGRESS_STATUS_START_CLIENT = "STARTING_CLIENT";

  public static final String COM_DES_KEY = "lasjflajfiueoljtrlwkjlfjsaufaoskjflasjflasjflasiufoireuwgqhtk098397584357395738";

  public static final String SUFFIX_ZTBS = ".ztbs";

  //流合并生成的文件后缀
  public static final String SUFFIX_MERGER_FILE = ".meg";

  public static final String PROGRESS_STATUS_ERROR = "ERROR";

  public static final int MINIMUM_FILE_SIZE_ALERT = 1024 * 1024 * 5;

  private static Map<String, String> PROGRESS_STATUS_TRANS_MAP;

  private static Map<String, String> TYPE_CODE_Map;

  private static Map<String, String> TYPE_NAME_Map;

  private static Map<String, String> ztbNodeName;

  private static Map<String, ImageIcon> imageIcon;

  private static Map<String, String> translate;

  private static Map<String, String> msg;

  private static Vector<DetailsType> itemForPackVector;

  private static Vector<DetailsType> itemForPackBusinessVector;

  private static Vector<DetailsType> itemForPackTechVector;

  private static Vector<DetailsType> itemForProjVector;

  private static Map<String, Vector<DetailsType>> addableTypeForNode;

  private static Vector<DetailsType> OptionForPack_RMouse;

  private static Vector<DetailsType> OptionForProj_RMouse;

  private static Vector<DetailsType> OptionForYES_NO;

  private static Map<String, Vector<DetailsType>> NodeOperationsMap;

  private static Map<String, String> projExportStateNames;

  private static Map<String, String> uploadStateNames;

  private static Map<String, String> projVerifyStateNames;

  private static Map<String, String> buttonTextMap;

  private static Map<String, String> BidWayCNNAME_CODDE_MAP;

  private static Map<String, String> EXT_TYPE_MAP;

  private static Map<String, String> STATUS_CODE_MAP;

  private static Map<String, String> tip;

  private static Map<String, String> purTypes;

  private static String historyDirectory;

  public static void initGV() {
    if (isInit) {
      return;
    }
    initProgressStatusTransMap();
    initTypeCodeMap();
    initTypeNameMap();
    initZtbNodeName();
    initImageIcon();
    initTips();
    initTranslate();
    initMsgMap();
    initPurTypes();
    initImportantMsgMap();
    initItemForPackVector();
    initItemForPackBusinessVector();
    initItemForPackTechVector();
    initItemForProjVector();
    initAddableTypeForNode();
    initOptionForPack_RMouse();
    initOptionForProj_RMouse();
    initOptionForYES_NO();
    initNodeOperationsMap();
    initProjExportStateNames();
    initUploadStateNames();
    initProjVerifyStateNames();
    initButtonTextMap();
    initBidWayCNNAME_CODDE_MAP();
    initEXT_TYPE_MAP();
    initStatusCode();
    isInit = true;
  }





  private static void initProgressStatusTransMap() {
    PROGRESS_STATUS_TRANS_MAP = new HashMap<String, String>();
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_READY, "准备就绪...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_EXP_PROJ_READY, "准备导出项目...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_EXP_TPL_READY, "准备导出模板...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_COPY_READY, "准备处理文件...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_COPYING, "正在处理文件：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_COPY_OK, "文件处理完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_DELETE_READY, "准备删除文件...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_DELETING, "正在删除文件：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_DELETE_ZB, "正在删除投标书下的招标文件部分...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_DELETE_OK, "文件删除完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_READ_READY, "准备读取文件...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_READING, "正在读取文件：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_READING_ONLY, "正在读取只读文件：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_READ_OK, "文件读取完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_GETMD5_READY, "准备获取文件MD5码...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_GETMD5, "正在获取文件MD5码：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_GETMD5_OK, "文件MD5码获取完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_BUILD_FILE_READY, "准备创建文件...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_BUILD_FILE, "正在创建文件：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_BUILD_FILE_OK, "文件创建完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_ZIP_READY, "准备压缩文件...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_ZIPING, "正在压缩文件：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_ZIP_OK, "文件压缩完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_SERVER_CHECKING, "正在检查服务器端文件状态...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_UPLOAD_READY, "准备上传文件到服务器...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_UPLOADING, "正在上传文件到服务器：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_UPLOAD_OK, "文件上传到服务器完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_EXPORT_READY, "准备导出标书相关文件...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_EXPORTING, "正在导出标书相关文件：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_EXPORT_OK, "标书相关文件导出完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_UPDATE_READY, "准备更新相关数据...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_UPDATING, "正在更新相关数据...：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_UPDATE_OK, "相关数据更新完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_FEEDBACK_READY, "准备生成投标回执(结果)...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_FEEDBACK_OK, "投标回执(结果)生成完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_ENCODE_READY, "准备加密数据...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_ENCODING, "进入文件加密流程，过程中需要选择【CA数字证书】用于加密...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_ENCODE_OK, "数据加密完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_ENCODE_FAIL, "数据加密失败...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_SAVING, "正在保存文件：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_SAVE_OK, "文件保存完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_CONFIG_CREATE_READY, "准备创建配置文件...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_CONFIG_CREATING, "正在创建配置文件：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_CONFIG_CREATE_OK, "配置文件创建完成.");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_PROJ_CONFIG_CREATE_READY, "准备生成项目配置信息文件...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_PROJ_CONFIG_CREATING, "正在生成项目配置信息文件：");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_ERROR, "过程中出现错误，过程终止...");
    PROGRESS_STATUS_TRANS_MAP.put(GV.PROGRESS_STATUS_START_CLIENT, "正在启动加速客户端，请稍候...");
  }

  public static String getFileOperatorCN(String key) {
    return PROGRESS_STATUS_TRANS_MAP.get(key);
  }

  private static Map<String, String> TYPE_PATH_Map;



  public static String getFilePathFromMap(String typeKey) {
    return TYPE_PATH_Map.get(typeKey);
  }

  private static void initTypeCodeMap() {
    TYPE_CODE_Map = new HashMap<String, String>();
    TYPE_CODE_Map.put(NODE_TYPE_PACK_BUSINESS, NODE_CODE_BUSINESS);
    TYPE_CODE_Map.put(NODE_TYPE_PACK_TECH, NODE_CODE_TECH);
    TYPE_CODE_Map.put(NODE_TYPE_ZB, NODE_CODE_ZB);
    TYPE_CODE_Map.put(NODE_TYPE_TB, NODE_CODE_TB);
  }

  public static String getCodeFromMap(String typeKey) {
    return TYPE_CODE_Map.get(typeKey);
  }

  private static void initTypeNameMap() {
    TYPE_NAME_Map = new HashMap<String, String>();
    TYPE_NAME_Map.put(NODE_TYPE_PACK_BUSINESS, NODE_NAME_BUSINESS);
    TYPE_NAME_Map.put(NODE_TYPE_PACK_TECH, NODE_NAME_TECH);
  }

  public static String getNameFromMap(String typeKey) {
    return TYPE_NAME_Map.get(typeKey);
  }

  private static void initZtbNodeName() {
    ztbNodeName = new HashMap<String, String>();
    ztbNodeName.put(NODE_TYPE_PROJECT_LIST, "项目列表");
    ztbNodeName.put("ZBDocuments", "招标文档");
    ztbNodeName.put("TBDocuments", "投标文档");
    ztbNodeName.put("CURR_UNFINISHED", "当前未完成标书");
    ztbNodeName.put("HISTORY", "历史投标文件夹");
    ztbNodeName.put("ROOT", IMPORT_FILE_DIR);
  }

  public static Map<String, String> getZTBNodeNameList() {
    return ztbNodeName;
  }

  private static void initImageIcon() {
    imageIcon = new HashMap<String, ImageIcon>();
    imageIcon.put("save", getImageIcon("save.jpg"));
    imageIcon.put("saveAsTemplate", getImageIcon("saveas.png"));
    imageIcon.put("importtb", getImageIcon("import.png"));
    imageIcon.put("importzb", getImageIcon("import.png"));
    imageIcon.put("exporttb", getImageIcon("export.png"));
    imageIcon.put("exportzb", getImageIcon("export.png"));
    imageIcon.put("delete", getImageIcon("delete.jpg"));
    imageIcon.put("dbconfig", getImageIcon("dbconfig.png"));
    imageIcon.put("dbimport", getImageIcon("dbimport.png"));
    imageIcon.put("table", getImageIcon("table.png"));
    imageIcon.put("word", getImageIcon("word.png"));
    imageIcon.put("tablelock", getImageIcon("tablelock.png"));
    imageIcon.put("wordlock", getImageIcon("wordlock.png"));
    imageIcon.put("lockWord", getImageIcon("wordlock.png"));
    imageIcon.put("unLockWord", getImageIcon("unlockword.png"));
    imageIcon.put("exit", getImageIcon("exit.png"));
    imageIcon.put("dbImpReq", getImageIcon("dbconbine.png"));
    imageIcon.put("dbFormula", getImageIcon("dbconbine.png"));
    imageIcon.put("about", getImageIcon("about.png"));
    imageIcon.put("verify", getImageIcon("verify.png"));
    imageIcon.put("moveuptofirst", getImageIcon("moveup.png"));
    imageIcon.put("moveup", getImageIcon("moveup.png"));
    imageIcon.put("upload", getImageIcon("moveup.png"));
    imageIcon.put("movedown", getImageIcon("movedown.png"));
    imageIcon.put("movedowntolast", getImageIcon("movedown.png"));
    imageIcon.put("fillMold", getImageIcon("fillmold.png"));
    imageIcon.put("refresh", getImageIcon("refresh.png"));
    imageIcon.put("copyTemplate", getImageIcon("import.png"));
    imageIcon.put("chooeseTemplateBtn", getImageIcon("import.png"));
    imageIcon.put("uploadAndBid", getImageIcon("moveup.png"));
    imageIcon.put("goonUploadPackAndBid", getImageIcon("goonupload.png"));
    imageIcon.put("exportTBProject", getImageIcon("export.png"));
    imageIcon.put("uploadProjectAndBid", getImageIcon("moveup.png"));
    imageIcon.put("goonUploadProjectAndBid", getImageIcon("goonupload.png"));
    imageIcon.put("ecbj", getImageIcon("goonupload.png"));
    imageIcon.put("uploadEcbj", getImageIcon("goonupload.png"));
    imageIcon.put("add", getImageIcon("addnew.png"));
    imageIcon.put("addEctbBagDetails", getImageIcon("addnew.png"));
    imageIcon.put("exportEctbBagDetails", getImageIcon("export.png"));
    imageIcon.put("uploadZBFile", getImageIcon("goonupload.png"));
    imageIcon.put("decode", getImageIcon("decode.png"));
    imageIcon.put("encode", getImageIcon("encode.png"));
    imageIcon.put("fetchPubKey", getImageIcon("dbimport.png"));
    imageIcon.put("copyToSameNode", getImageIcon("copy.png"));
    imageIcon.put("makeProjPlanBtn", getImageIcon("dbconbine.png"));
    imageIcon.put("exportZBFileBtn", getImageIcon("export.png"));
    imageIcon.put("uploadZBFileBtn", getImageIcon("moveup.png"));
    imageIcon.put("openedbook", getImageIcon("book.png"));
    imageIcon.put("list", getImageIcon("list.png"));
    imageIcon.put("responsepoint", getImageIcon("addnew.png"));
    imageIcon.put("unfold", getImageIcon("unfold.png"));
    imageIcon.put("fold", getImageIcon("fold.png"));
    imageIcon.put("rotateword_h", getImageIcon("rotateword_h.png"));
    imageIcon.put("rotateword_v", getImageIcon("rotateword_v.png"));
    imageIcon.put("importZbFileBtn", getImageIcon("dbimport.png"));
    imageIcon.put("full", getImageIcon("full.png"));
    imageIcon.put("zipTest", getImageIcon("zip_test.png"));
    imageIcon.put("loginbtn", getImageIcon("login.png"));
    imageIcon.put("importToNode", getImageIcon("dbimport.png"));
    imageIcon.put("exportZbBook", getImageIcon("export.png"));
    imageIcon.put("mergeword", getImageIcon("wordmerge.png"));
  }

  public static Map<String, ImageIcon> getImageIcon() {
    return imageIcon;
  }

  public static ImageIcon getImageIcon(String imgFileName) {
    String imgFileDir = "/com/ufgov/zc/client/zc/ztb/img/" + imgFileName;
    URL imgURL = new GV().getClass().getResource(imgFileDir);
    if (imgURL == null) {
      return null;
    }
    return new ImageIcon(imgURL);
  }

  public static URL getImageUrl(String imgFileName) {
    String imgFileDir = "/com/ufgov/zc/client/zc/ztb/img/" + imgFileName;
    URL imgURL = new GV().getClass().getResource(imgFileDir);
    if (imgURL == null) {
      return null;
    }
    return imgURL;
  }

  private static void initTips() {
    tip = new HashMap<String, String>();
    tip.put("zipTest", "对文件进行打包后加密之前的校验.");
    tip.put("save", "保存当前打开的文档内容.");
    tip.put("import", "从本地导入标书文件");
    tip.put("dbconfig", "软件相关配置");
    tip.put("dbimport", "从服务器导入项目");
    tip.put("dbConbine", "导入需求");
    tip.put("uploadAndBid", "上传已经制作好的投标书.");
    tip.put("ecbj", "对已投标项目进行二次报价.");
    tip.put("makeProjPlan", "针对当前项目制定执行计划，包括标书发售时间、投标截止时间、开标时间、地点等信息.");
    tip.put("loginbtn", "登录投标系统.");
  }

  public static Map<String, String> getImageIconTips() {
    return tip;
  }

  private static void initTranslate() {
    translate = new HashMap<String, String>();
    translate.put("dbimport", "从服务器导入项目");
    translate.put("save", "保存当前文档");
    translate.put("dbconfig", "配 置");
    translate.put("importzb", "从本地导入标书");
    translate.put("importtb", "从本地导入标书");
    translate.put("exportzb", "导出招标书到本地");
    translate.put("exporttb", "导出投标书到本地");
    translate.put("delete", "删 除");
    translate.put("add", "新 增");
    translate.put("pbComment", "填写质疑");
    translate.put("upload", "上传到数据库");
    translate.put("uploadAndBid", "上传并投标");
    translate.put("exit", "退 出");
    translate.put("file", "文件(F)");
    translate.put("edit", "编辑(E)");
    translate.put("config", "配置(C)");
    translate.put("help", "帮助(H)");
    translate.put("about", "关 于");
    translate.put("dbupdate", "导 入");
    translate.put("dbImpReq", "导入需求");
    translate.put("verify", "校 验");
    translate.put("addEctbBagDetails", "增加再次报价");
    translate.put("exportEctbBagDetails", "导出再次报价");
    translate.put("saveAsTemplate", "另存为模板");
    translate.put("copyTemplate", "引用标书模板");
    translate.put("renameByBtn", "重命名");
    translate.put("moveuptofirst", "上移至顶部");
    translate.put("moveup", "上移");
    translate.put("movedown", "下移");
    translate.put("movedowntolast", "下移至最后");
    translate.put("lockWord", "保护文档");
    translate.put("unLockWord", "解除文档保护");
    translate.put("fillMold", "填充当前打开文档");
    translate.put("refresh", "刷 新");
    translate.put("goonUploadPackAndBid", "续传投标书");
    translate.put("exportTBProject", "导出项目投标书");
    translate.put("uploadProjectAndBid", "上传项目投标书");
    translate.put("uploadAndBid", "上传投标书并投标");
    translate.put("goonUploadProjectAndBid", "续传项目投标书");
    translate.put("ecbj", "再次报价");
    translate.put("uploadEcbj", "上传报价文件");
    translate.put("decode", "解密投标书");
    translate.put("encode", "加密投标书");
    translate.put("copyToSameNode", "复制当前节点");
    translate.put("makeProjPlan", "制定项目执行计划");
    translate.put("responsepoint", "添加响应点");
    translate.put("displayAllNode", "显示所有子节点");
    translate.put("hideCurrNode", "隐藏当前节点");
    translate.put("rotateword_h", "横向WORD页面");
    translate.put("rotateword_v", "纵向WORD页面");
    translate.put("zipTest", "标书检测");
    translate.put("loginbtn", "点击登录");
    translate.put("logoutbtn", "退出登录");
    translate.put("importToNode", "导入文件到当前节点下");
    translate.put("startDecode", "开始解密");
    translate.put("next", "下一步");
    translate.put("brower", "浏  览");
    translate.put("messageDialogTitle", "提示");
    translate.put("executeBid", "执行投标");
    translate.put("addNew", "添  加");
    translate.put("cancel", "取  消");
    translate.put("login", "登  录");
    translate.put("printReceipt", "打印回执");
    translate.put("summitPrice", "提交报价");
    translate.put("notDisplayNextTime", "下次不再显示");
    translate.put("loginAndUpload", "登录并上传");
    translate.put("exportZbBook", "导出WORD版招标书到本地");
    translate.put("mergeword", "合并多个WORD");
  }

  public static Map<String, String> getTranslate() {
    return translate;
  }

  public static String getTranslate(String key) {
    return translate.get(key);
  }


  private static void initMsgMap() {
    msg = new HashMap<String, String>();
    msg.put("exportProject.success", "导出成功！\n导出路径为：${content}。");
    msg.put("exportProject.failure", "导出失败！");
    msg.put("deleteProject.success", "删除成功！\n项目【${content}】已被成功删除。");
    msg.put("deleteProject.confirm", "您确定删除项目【${content}】？");
    msg.put("deleteProjectBag.success", "删除成功！\n【${content}】已被成功删除。");
    msg.put("deleteProjectBag.confirm", "您确定删除【${content}】？");
    msg.put("importProject.confirm", "项目【${content}】如果已经存在，重复导入会覆盖已有项目。\n请确认是否继续？");
    msg.put("importProject.success", "导入成功！\n项目【${content}】已被成功导入。");
    msg.put("dbimportProject.confirm", "您确定导入以下项目？${content}");
    msg.put("dbimportProject.nodata", "请选择需要导入的项目！");
    msg.put("dbimportProject.success", "成功导入以下项目！${content}");
    msg.put("addNewNode.success", "新节点【${content}】添加成功！");
    msg.put("addNewNode.failure", "新节点【${content}】添加失败！");
    msg.put("addNewNode.duplicate", "节点【${content}】已经存在！");
    msg.put("addNewNode.nofilename", "请输入新添加节点的名称！");
    msg.put("save.success", "保存【${content}】成功！");
    msg.put("save.fail", "保存【${content}】失败,或者【${content}】未被修改！");
    msg.put("invalidFileName", "文件名不能包含下列任何字符之一：\n\\ / : * ? \" < > |");
    msg.put("uploadProject.confirm", "您确定上传项目【${content}】至服务器？\n提示：上传操作会覆盖原先上传记录。");
    msg.put("uploadPack.confirm", "您确定上传标书【${content}】至服务器？\n提示：上传操作会覆盖原先上传记录。");
    msg.put("uploadProject.success", "上传成功！\n项目【${content}】已被成功上传。");
    msg.put("uploadProject.failure", "上传项目【${content}】失败，请检查服务器连接配置。\n");
    msg.put("exportPack.success", "导出成功！\n导出路径为：【${content}】。");
    msg.put("exportPack.failure", "导出项目标段(分包)【${content}】失败。\n");
    msg.put("uploadProject.forbidden", "导入失败！\n项目【${content}】采购流程已启动，禁止上传。");
    msg.put("deleteBagDetails.confirm", "您确定删除标段(分包)【${content}】？");
    msg.put("deleteBagDetails.success", "删除成功！\n标段(分包)【${content}】已被成功删除。");
    msg.put("exitProgram", "您确定退出？");
    msg.put("about.info", "版本信息：V1.2.0;\n北京用友政务软件有限公司开发。");
    msg.put("dbImportRequirement.nodata", "请选择需要导入的文件！");

    msg.put("dbImportRequirement.confirm", "您确定导入以下文件？${content}");
    msg.put("dbImportRequirement.success", "成功导入以下文件！${content}");
    msg.put("dbImportRequirement.failure", "导入失败！\n原因：${content}");
    msg.put("dbImportRequirement.noDocOpened", "请先打开需要导入需求的文档！");
    msg.put("dbImportRequirement.unsupportedDocNode", "当前节点不支持导入需求操作，请选择项目标段(分包)下的对应文档。");
    msg.put("dbImportFormula.unsupportedDocNode", "当前节点不支持导入评标方法操作，请选择项目标段(分包)下的对应文档。");

    msg.put("verityProject.success", "校验通过！");
    msg.put("verityProject.failure", "校验未通过！以下为不完善的分包明细：\n${content}");
    msg.put("verityProject.exception", "校验失败！原因：\n${content}");
    msg.put("deleteNode.confirm", "您确定删除【${content}】？");
    msg.put("deleteNode.success", "【${content}】已被成功删除！");
    msg.put("copyTemplate.nodata", "请选择需要引用的模板！");
    msg.put("copyTemplate.twoOrMoreData", "只能引用一个模板。");
    msg.put("copyTemplate.success", "【模板导入成功】！");
    msg.put("copyTemplate.fail", "模板导入失败！");
    msg.put("noMatchingDocument", "未打开需要填充的文档，或当前文档无法填充...");
    msg.put("protectFile.success", "【${content}】文档保护成功...");
    msg.put("protectFile.fail", "【${content}】文档保护失败...");
    msg.put("protectFile.refreshfail", "【${content}】文档保护成功后更新图标失败，重启编辑器后会自动更新...");
    msg.put("unprotectFile.success", "【${content}】解除文档保护成功...");
    msg.put("unprotectFile.fail", "【${content}】解除文档保护失败...");
    msg.put("unprotectFile.refreshfail", "【${content}】解除文档保护成功后更新图标失败，重启编辑器后会自动更新...");
    msg.put("uploadTemplate.success", "上传成功！\n模板【${content}】已被成功上传。");
    msg.put("uploadTemplate.failure", "上传模板【${content}】失败，错误如下：\n");
    msg.put("versioncheck.failure", "进行版本检测时，发现您的制作软件版本太低，请马上更新后继续投标书制作！");
    msg.put("askIsEncoding", "您好，请仔细阅读以下内容：\n    为了您的标书内容的保密和安全，系统支持加密功能，是否进行加密？\n选择【是】将进行加密，选择【否】将跳过加密.\n    如果您放弃加密，标书内容的安全将完全由您自己负责。");
    msg.put("importBeforeLogin", "请先导入标书后再登录，否则无法确定你的投标目标服务器地址！");
    msg.put("sureToLogout", "您确定要退出登录吗？\n  这将导致您的标书无法预传输，有可能影响您的标书提交速度！\n点击【确定】退出登录，点击【取消】将保持登录状态。");
    msg.put("userLoginTypeSelect", "用户登录方式选择");
    msg.put("notAskToLogin", "不登录且不再提示该窗口.");
    msg.put("notAskToLoginTip", "下一次启动本软件之前不再提示.");
    msg.put("autoToLogin", "自动登录且不再提示该窗口(必须手动登录一次之后才能自动登录).");
    msg.put("offlineAttention", "您好，发现您的客户端当前处于离线状态，是否进行登录？");
    msg.put("loginUsefull", "—>1、登录后，客户端将自动进行数据字典后台同步更新；\n—>2、登录后，数据字典后台同步将经由SSL加密安全通道进行，传输过程安全可靠；\n—>3、通过数据字典后台同步，将可以极大提高您的标书提交速度；");
    msg.put("responseFrameTitle", "评标响应点添加");
    msg.put("askForCAkey", "如果您拥有数字证书，请使用数字证书登录.");
    msg.put("pleaseSelectLoginType", "请选择登录方式：");
    msg.put("userLoginTitle", "用户登录");
    msg.put("typeCALogin", "CA数字证书登陆");
    msg.put("typePwdLogin", "账号密码登陆");
    msg.put("careCAPwdDialog", "点击【登录】按钮后,请留意【CA数字证书】密码输入框...");
    msg.put("askCAAndRefresh", "请先插入【CA数字证书】,然后点击【刷新】按钮获取证书信息.");
    msg.put("passAndWaitNext", "用户验证通过，请继续下一步操作...");
    msg.put("onlyPwdLogin", "招标机构人员解密目前只能通过项目经办人的用户名/密码方式登录！");
    msg.put("userNotPass", "用户验证未通过，原因是【${content}】");
    msg.put("CAReadErr", "【CA数字证书】读取或认证出错...");
    msg.put("waitForDecode", "【${content}】待(已)解密标书列表");
    msg.put("fileCheckErr", "文件检查时发现文件异常，导出失败！可以再次尝试导出...");
    msg.put("clientStartErr", "无法启动加速上传客户端，请检查本软件安装目录下是否存在：\n${content}");
    msg.put("sysnClientStartErr", "无法启动后台数据字典同步更新客户端，请检查本软件安装目录下是否存在：\n${content}");
    msg.put("serverExistZBBook", "服务器端存在该项目招标书，是否覆盖？\n点【否】将继续导出到本地操作...");
    msg.put("notEncodeAndUpload", "确定不加密标书直接上传吗？\n  1、点击【是】不加密直接上传；\n  2、点击【否】将中断上传过程；");
    msg.put("askForEncode", "您的标书没有加密，是否加密后再上传？\n  1、点击【是】将接着对文件进行加密；\n  2、点击【否】将继续上传；");
    msg.put("missingMasterAndMinorPubKey",
      "您好，标书中缺少招标机构的主/辅公钥信息，将无法在加密过程中加入招标\n机构的加密，是否继续？\n1、如果继续，那么将只需要您(当前投标商)的公钥信息及口令进行一层加密。 \n2、否则您将需要联系招标机构重新获取招标书。\n   继续请点击【是】，退出请点击【否】？");
    msg.put("sysAskForEncode", "投标系统支持标书加密，您的当前标书没有加密，是否加密后再上传？\n点击【是】将继续不加密上传，点击【否】将退出上传.");
    msg.put("toubiaofile", "投标文件");
    msg.put("xiangyingfile", "响应文件");
    msg.put("userCancelEncode", "用户放弃了对标书的加密...");
    msg.put("commonUploadMold", "(1)普通上传模式；");
    msg.put("commonUploadMoldCN", "普通上传模式");
    msg.put("hightUploadMold", "(2)高速上传模式；");
    msg.put("hightUploadMoldCN", "高速上传模式");
    msg.put("hightUploadMoldExplain", "说明：\n    高速上传模式将经由【西安九智信管理咨询有限公司】\n提供的SSL通道进行数据传输。");
    msg.put("uploadMoldSelect", "请选择上传模式");
    msg.put("confirmUploadBid", "请确认上传投标书并投标");
    msg.put("myToubiaoList", "我的投标列表");
    msg.put("wrongPubKey", "招标书中的公钥及对应的哈希码校验失败，主持人Key公钥数据可能被修改过，请检查！");
    msg.put("askForSupPubKey", "您好，将需要读取您的【CA数字证书】公钥信息用于加密标书:\n 1、如果需要加密标书，请点击【确认】，然后开始读取证书公钥；\n 2、如果不需要加密标书，请点击【取消】，然后跳过读取证书。");
    msg.put("askForSupPubKeyInsert", "您好，将需要读取您的【CA数字证书】公钥信息用于加密标书:\n请确认您已经插入【CA数字证书】并已经被系统识别。");
    msg.put("noRecordToDecode", "没有查询到当前需要解密的投标书，原因：");
    msg.put("noRecordForSupToDecode", "没有查询到当前需要解密的投标书，请确认【是否已投标】且【是否已允许开始解密】...");
    msg.put("noRecordForMasterToDecode", "没有查询到当前需要解密的投标书，请确认【商家是否已经完成了解密】？...");
    msg.put("pleaseSelectToDecode", "请勾选需要解密的项，然后点击【开始解密】按钮进行解密...");
    msg.put("selectOneAtLeast", "请选择至少一个需要解密的标书...");
    msg.put("sureToDecode", "确定要解密所选中的【${content}】个标书吗？\n点【确定】按钮继续，【取消】按钮终止...");
    msg.put("useSamePwdToDecode", "所有标书是否使用相同密码进行解密？");
    msg.put("askForPwd", "请输入您的口令进行解密...");
    msg.put("ifUsingPwdToDecode", "如果尝试另一个【CA数字证书】进行解密，请直接点确定继续。");
    msg.put("decodeSuccess", "恭喜，您的标书解密成功！");
    msg.put("decodeFailure", "很遗憾，您的标书解密失败，密码不正确！");
    msg.put("successAndExecuteServerDecode", "之前已经成功解密过该标书，现在将继续进行服务器端解密...");
    msg.put("missingMastMinorPubKey", "没有获取到主辅key的加密信息...");
    msg.put("masterDecodeSuccess", "标书解密成功！");
    msg.put("masterDecodeFailureAndReason", "标书解密失败，详细：");
    msg.put("masterDecodeFailure", "标书解密失败...");
    msg.put("partDecodeFinish", "以下标书已经完成了解密，后续解密过程将忽略：\n");
    msg.put("decodeFinish", "解密过程完毕！");
    msg.put("detailList", "列表明细：");
    msg.put("decodeFailAndNeedPwd", "、解密失败的标书也可以使用制作投标书时【自设口令】进行继续解密。\n");
    msg.put("decodeFailTotal", "解密失败结果汇总：\n");
    msg.put("partNeedPwdToDecode", "有【${content}】份标书使用【CA数字证书】解密失败，请使用自设口令进行解密...");
    msg.put("foundDecodeFailureItems", "编号为【${content}】的标书在服务器端解密失败,原因：\n");
    msg.put("pleaseConfirm", "请确认");
    msg.put("checkServerLinkConfig", "没有查询到当前可以投标的项目，请检查【服务器连接配置】...");
    msg.put("checkBidEndtime", "没有查询到当前可以投标的项目，请检查项目的【投标截止时间】...");
    msg.put("bidFiles", "标书文件：");
    msg.put("projList", "项目列表：");
    msg.put("signupPacks", "报名标段：");
    msg.put("fileNotExist", "所选择的文件不存在，请选择文件！");
    msg.put("goonToUploadFile", "该文件已上传过，开始续传！");
    msg.put("firstToUploadFile", "该文件第一次上传，开始上传！");
    msg.put("toCoverExistFile", "该文件和之前已上传的部分不是同一个文件，是否覆盖服务器端已上传部分？");
    msg.put("willCoverExistFile", "此次上传将覆盖服务器端已上传部分！");
    msg.put("updateServerDataPrepare", "更新服务器端相关信息，以准备接收数据...");
    msg.put("updateServerDataFinish", "更新服务器端相关信息【完毕】，开始发送数据...");
    msg.put("updateLocalConfig", "更新本地相关信息...");
    msg.put("readingLocalData", "开始读取文件数据...");
    msg.put("CAAuthErr", "CA认证服务器验证CA身份时出错，详细：");
    msg.put("userNameNotNull", "用户名不能为空！");
    msg.put("CAReadErr", "【CA数字证书】获取异常，详细：");
    msg.put("fileSizeCheck", "您的电子标书文件小于【${content}】,可能缺少内容,确认要继续上传吗？");
    msg.put("fileToReadNotExist", "指定的文件不存在！请检查该文件是否存在：\n");
    msg.put("fileInfoInsertErr", "服务器端文件信息插入数据库失败，请稍候再试！");
    msg.put("fileNotNeedUpload", "当前文件已上传过，无需再上传！");
    msg.put("dataSentFinish", "全部文件数据包发送完毕...");
    msg.put("exitAskConfirm", "退出提示");
    msg.put("sureToExit", "您真的要退出登录？");
    msg.put("unknowable", "未知");
    msg.put("encoded", "已加密");
    msg.put("notEncoded", "未加密");
    msg.put("unknowableStatus", "未知状态");
    msg.put("waitToUpload", "等待上传");
    msg.put("uploading", "正在上传");
    msg.put("uploadFinish", "完成上传");
    msg.put("uploadAndBidFinish", "完成投标");
    msg.put("uploadFailToRetry", "上传失败(待再试)");
    msg.put("nobiddata", "当前没有投标相关数据...");
    msg.put("selectAndNext", "请选择相应的标段，然后进行相应的操作...");
    msg.put("askForEncodeBeforeNext",
      "您好，请仔细阅读以下内容：\n    投标系统支持标书加密，您的当前标书没有加密，是否加密后再上传？\n    1、点【是】将进行加密，后可继续点【执行投标】进行投标；\n    2、点【否】将不加密直接上传；\n    3、如果您放弃加密，标书内容的安全将完全由您自己负责。");
    msg.put("noNeedToEncode", "标书不需要提前加密，将会在上传时进行加密！");
    msg.put("noNeedToEncodeForHight", "标书采用高速上传模式进行上传时，不需要提前加密，将会在上传时进行加密！");
    msg.put("reEncode", "该项目当前标段标书已经完成加密，是否需要重新加密？");
    msg.put("bidFinishYet", "该项目当前标段已经完成了投标，无需再提交！");
    msg.put("uploadFinishAndWaitToBid", "标书已经上传到服务器端，但还未提交到系统，确定要进行投标吗？\n  1、点【确定】按钮继续；\n  2、点【取消】按钮终止提交过程.");
    msg.put("askSureToSummit", "确定要提交该标书吗？\n点【确定】按钮继续，【取消】按钮终止...");
    msg.put("selectOneOnly", "每次请选择一项进行投标...");
    msg.put("cancelImport", "您已经取消了标书导入！");
    msg.put("bidLoading", "正在加载标书,请稍候……");
    msg.put("loadSucc", "标书加载成功！");
    msg.put("loadFail", "标书加载失败，请再次尝试！");
    msg.put("commonMessage", "消息");
    msg.put("askIsEncode", "标书没有加密，是否加密后再上传？\n  1、点击【否】将继续上传；\n  2、点击【是】将接着对文件进行加密；");
    msg.put("askForInputName", "请输入新名称： ");
    msg.put("functionNotSupport", "该功能尚未实现...");
    msg.put("askForInputEffectName", "请输入有效的名称！");
    msg.put("notFoundPriceReport", "该标段下未发现再次报价表的数据文件，请核查！");
    msg.put("exportSuccAndPath", "导出成功！\n导出路径为：");
    msg.put("askForTreeNode", "请先在左边节点树上选择一个项目节点！");
    msg.put("notExistExportableProject", "当前没有可以导出到本地的招标书！");
    msg.put("fillError", "填充出错，原因：");
    msg.put("fillSucess", "填充成功");
    msg.put("notNeedToProtect", "当前文档无需保护...");
    msg.put("askForOpenWord", "请先打开需要保护的文档.");
    msg.put("wordWasProtected", "文档已经处于保护状态，无需再保护...");
    msg.put("askForInputPwd", "请输入密码:");
    msg.put("notNeedToUnprotect", "当前文档无需解除保护...");
    msg.put("askForOpenWordToUnprotect", "请先打开需要解除保护的文档.");
    msg.put("wordWasUnprotected", "文档已经处于未保护状态，无需解除保护...");
    msg.put("sureToCoverEachNodes", "确定要将该节点(章节)及其子节点的所有内容，\n拷贝并覆盖到另一标段对应的节点(章节)下？");
    msg.put("sureToCoverSingleNode", "确定要将当前节点内容拷贝并覆盖到另一标段对应的节点下？");
    msg.put("sameNodeCopy", "相似节点拷贝");
    msg.put("selectPacksToCover", "请选择要覆盖的标段:");
    msg.put("sureToCoverSelectedPacks", "确定要将该节点(章节)及其子节点的所有内容，\n拷贝并覆盖到所选标段对应的节点(章节)下？");
    msg.put("sureToCoverSelectedSinglePacks", "确定要将当前节点内容拷贝并覆盖到所选标段对应的节点下？");
    msg.put("yes", "是");
    msg.put("no", "否");
    msg.put("notNeedDecode", "无需解密");
    msg.put("decodeFail", "失败");
    msg.put("decodeSucc", "成功");
    msg.put("notEncodedYet", "尚未解密");
    msg.put("projectName", "项目名称");
    msg.put("packName", LangTransMeta.translate(ZcElementConstants.FIELD_TRANS_PAKE_NAME));
    msg.put("supplierName", "供应商名称");
    msg.put("isMasterEncoded", "主持人加密");
    msg.put("isProviderEncoded", "商家加密");
    msg.put("masterDecoded", "主持人解密");
    msg.put("providerDecoded", "商家解密");
    msg.put("isFullDecoded", "完全解密？");
    msg.put("masterDecodedTime", "主持人解密时间");
    msg.put("providerDecodedTime", "商家解密时间");
    msg.put("uploadMold", "上传模式");
    msg.put("bidEndTime", "投标截止时间");
    msg.put("wasEncoded", "是否加密");
    msg.put("uploadStatus", "上传状态");
    msg.put("startUploadTime", "开始上传时间");
    msg.put("endUploadTime", "完成上传时间");
    msg.put("type", "类   别：");
    msg.put("name", "名   称：");
    msg.put("openPackForAddingResponse", "请在【标书文件】页签中打开将要添加响应点的标段...");
    msg.put("onlyParentNodeCreatableWhileNoOpenWord", "当前没有打开可设置响应点的文档，只能创建父类节点，\n往后可以根据需要往该节点下添加子节点，是否继续？");
    msg.put("onlyParentNodeCreatableUnderTBNode", "【投标文件】下不能创建响应点，只能创建父类节点，\n往后可以根据需要往该节点下添加子节点，是否继续？");
    msg.put("onlyParentNodeCreatableWhileNotMatch", "当前【选中区域】和标段下不匹配！只能创建父类节点，\n往后可以根据需要往该节点下添加子节点，是否继续？\n或者先在【标书导航】页签中选中一个节点然后再试。");
    msg.put("onlyParentNodeCreatableWhileNotSelected", "当前打开的文档中【没有选中用于设置响应点的区域】，只能创建父类节点，\n往后可以根据需要往该节点下添加子节点，是否继续？\n或者先在打开的文档中先选中一段内容作为响应点内容.");
    msg.put("currentSelectedLen", "当前选中内容长度为：");
    msg.put("mustInputTip", "带*号的为必填项.");
    msg.put("pleaseInputNodeName", "请输入节点名称：");
    msg.put("selfDefinePoint", "自定义响应点");
    msg.put("evalElements", "评审要素指标：");
    msg.put("responseName", "响应点名称");
    msg.put("responseDesc", "响应点描述");
    msg.put("pleaseSelectParentNode", "请先在响应点树节点上选中一个节点作为该新建响应点的父节点！");
    msg.put("nameTooSimple", "【响应点名称】不能为空或太简单！");
    msg.put("descToolSimple", "【响应点描述】不能为空或太简单！");
    msg.put("failToCreateDecodedList", "创建待解密列表失败，详细：");
    msg.put("loginSucc", "登录状态：登录成功！");
    msg.put("loginFail", "登录状态：登录失败！");
    msg.put("failReasonLabel", "失败原因：");
    msg.put("supplierUser", "供应商用户");
    msg.put("masterUser", "主持人用户");
    msg.put("userNameLabel", "用户名称：");
    msg.put("userIDLabel", "机构代码：");
    msg.put("caNOLabel", "证书序号：");
    msg.put("userTypeLabel", "用户类别：");
    msg.put("userTypeSupplier", "供应商。");
    msg.put("userTypeMaster", "招标执行机构。");
    msg.put("digitalCertificateLabel", "证书选择：");
    msg.put("operatorNotCorrect", "操作无法进行，原因：");
    msg.put("notExistEcbjProject", "没有查询到当前可以再次报价的项目，请检查【服务器连接配置】...");
    msg.put("notExistEcbjProjectThisTime", "没有查询到当前可以再次报价的项目，请检查【报价截止时间】...");
    msg.put("pricedPack", "报价标段：");
    msg.put("noPriceItems", "当前没有项目和标段可以进行报价！");
    msg.put("pleaseSelectPriceFile", "请指定需要上传的报价文件;");
    msg.put("mustSelectOnePack", "必须选择一个标段进行报价;");
    msg.put("userPassAndNext", "用户验证通过，请继续下一步操作...");
    msg.put("userNotPassReason", "用户验证未通过，原因：");
    msg.put("userPwdLabel", "用户密码：");
    msg.put("authType", "认证方式：");
    msg.put("priceFileLabel", "报价文件：");
    msg.put("zbFlowChartTabTitle", "招标流程示意图");
    msg.put("projSummaryInfo", "项目概要信息");
    msg.put("tbFileTabTitle", "标书文件");
    msg.put("bidBookNavigation", "标书导航");
    msg.put("unfoldAll", "展开所有");
    msg.put("foldAll", "合拢所有");
    msg.put("displayAll", "显示所有节点");
    msg.put("projNameLabel", "项目名称：");
    msg.put("projCodeLabel", "项目编号：");
    msg.put("purTypeLabel", "招标方式：");
    msg.put("bidEndTimeLabel", "投标截止时间：");
    msg.put("bidOpenTimeLabel", "开标时间：");
    msg.put("bidOpenAddressLabel", "开标地址：");
    msg.put("bidOrg", "招标机构：");
    msg.put("bidOrgManager", "招标项目负责人：");
    msg.put("contactPhoneLabel", "联系电话：");
    msg.put("emailLabel", "电子邮件：");
    msg.put("pleaseInsertMasterAndMinorKey", "请依次插入开标解密时使用的主和辅【CA数字证书】...");
    msg.put("needingIPAddress", "您好，您的标书中缺少标书提交目标服务器IP地址：");
    msg.put("notCorrectIPAddress", "您好，您的标书中给定的标书提交目标服务器IP地址非法：");
    msg.put("suggestToCallForIPAddress", "1、您可以电话联系标书发布机构获取服务器地址或新的标书；");
    msg.put("suggestToSetIPAddress", "2、我们强烈建议您现在就指定本次项目投标服务器地址；");
    msg.put("inputIPLikeThis", "3、如果已经知道本次标书投标地址，请在下面输入，请参照【http://10.76.28.22:7001】。");
    msg.put("pleaseInput", "请输入:");
    msg.put("attentionPlease", "请注意");
    msg.put("noConnectWithoutIPAddress",
      "您好，请注意，因为缺少投标书提交目标服务器地址：\n 1、您的标书将无法通过本客户端进行后台数据字典同步更新；\n 2、标书也将无法通过本客户端提交到服务器；\n 3、再次点击【点击登录】可以输入地址，或正式提交标书时也可以输入服务器地址。");
    msg.put("notCorrectIPAndRetry", "您输入的服务器地址不正确，请参照【http://61.185.224.41:7001】，请重新输入:");
    msg.put("pleaseRetryInput", "请重新输入");
    msg.put("bidReceiptTitle", "投标回执");
    msg.put("receiptContent000", "系统已接受了贵单位于[");
    msg.put("receiptContent001", "]就编号为[");
    msg.put("receiptContent002", "]，名称为[");
    msg.put("receiptContent003", "]的投标，报名投标标段如下：");
    msg.put("receiptContent004", "最终的投标标段将以投标书实际响应的标段为准。");
    msg.put("receiptContent005", "项目联系人：");
    msg.put("receiptContent006", "联系电话：");
    msg.put("receiptContent007", "办公地址：");
    msg.put("receiptContent008", "哈希码值：");
    msg.put("receiptContent009", "回执时间：");
    msg.put("notSettedResponsePoint", "当前节点未设置响应点！");
    msg.put("configInfoTitle", "配置信息");
    msg.put("serverIPLabel", "服务器IP：");
    msg.put("serverPortLabel", "端  口：");
    msg.put("connectOK", "连接正常！");
    msg.put("connectErrAndReason", "连接出错，原因：");
    msg.put("saveSucc", "保存成功！");
    msg.put("pleaseInputIPAddress", "请填写服务器IP!");
    msg.put("serverIPErr", "所填写服务器IP不正确!");
    msg.put("pleaseInputPort", "请填写端口号!");
    msg.put("serverPortErr", "端口号不正确!");
    msg.put("decodedFinishAndResult", "标书解密完毕，结果如下：\n");
    msg.put("asking", "询问");
    msg.put("tbBookImportErr", "导入标书时出错，详细:");
    msg.put("pleaseSelectTbFile", "请指定需要上传的投标文件;");
    msg.put("mustSelectOnePack", "必须选择一个标段进行报价;");
    msg.put("bidUploadTitle", "标书上传");
    msg.put("ecbjTitle", "再次报价");
    msg.put("configErrNeedDelete", "配置文件读取出错，请先删除【${content}】文件后重新启动该工具。");
    msg.put("pleaseSettingIPAndPort", "请先配置服务器地址及端口.");
    msg.put("insertCAAndRefresh", "请先插入【CA数字证书】,然后点击【刷新】以获取证书信息.");
    msg.put("caReadErrAndAuth", "CA证书读取或认证出错，详细：");
    msg.put("userPassAndBeginPrice", "用户验证通过，开始进行报价文件上传和相关数据更新...");
    msg.put("userNotPassAndReason", "抱歉，用户验证未通过，原因是【${content}】");
    msg.put("bidBookLocation", "标书存放位置：");
    msg.put("bidSaveLocationNotNull", "【标书存放位置】项不能为空，请指定...");
    msg.put("bidSaveLocationErr", "【标书存放位置】指定的路径非法或不存在，请重新指定...");
    msg.put("operatorInProgress", "文件检查进行中，不能中断该操作！");
    msg.put("fileChecking", "文件检查进度明细");
    msg.put("templateUploadSucc", "模板文件上传成功!!!");
    msg.put("templateUploadFailAndReason", "模板文件上传失败，原因：");
    msg.put("serverIPAndPortGotErr", "获取服务器地址及端口信息出错，原因：");
    msg.put("directoryCreateErr", "创建目录失败!");
    msg.put("fileCreatedErrAndRestartToTry", "文件创建失败，请重试，如果多次失败，请重启本软件然后再次尝试.\n需要创建的文件：");
    msg.put("templateListSearchErr", "查询模板列表出错，原因：");
    msg.put("databaseNoMatchTemplate", "数据库模板库中当前没有【${content}】的模板，您可以引用本地模板.");
    msg.put("serverLinkErr", "网络连接有错！详细：");
    msg.put("serverLinkErrUrl", "连接服务器${content}时出错！详细：");
    msg.put("identityCheckNotPass", "投标前身份检查结果【不通过】，详情：");
    msg.put("readingDataPackage", "正在读取文件[数据包${content}]...");
    msg.put("sendingDataPackage", "正在发送[数据包${content}]...");
    msg.put("dataPackageTransErr", "文件传输出错，详细:${content}");
    msg.put("tellToRetry", ",请检查,稍候会自动尝试重新连接...");
    msg.put("dataPackageSentErr", "数据包发送出错：");
    msg.put("dataPackageSentFinish", "数据包发送完成：");
    msg.put("tbBookUploadErr", "投标文件上传失败...");
    msg.put("errLinkShouldCheck", "连接无效,请确定连接配置...");
    msg.put("tabCAInfo", "CA用户信息");
    msg.put("tabCommonInfo", "普通用户信息");
    msg.put("fileStatusLabel", "文件状态：");
    msg.put("uploadProgressLabel", "上传进度：");
    msg.put("fileNameLabel", "文件名：");
    msg.put("supplierNameLabel", "供应商：");
    msg.put("passwdLabel", "密  码：");
    msg.put("userNameLabel", "用户名：");
    msg.put("fileChooseTitle", "文件选择");
    msg.put("bidUploadLabel", "标书上传");
    msg.put("caReadErrAndCheck", "读取证书出错，请检查是否正确插入了【CA数字证书】...");
    msg.put("fileCopyErr", "文件拷贝出错，详细：");
    msg.put("lackNeedingFile", "缺少【${content}】，无法另存为模板！");
    msg.put("lackingFiles", "缺少文件，无法导出！");
    msg.put("fileMissingNeedCheck", "文件不存在,请检查或删除相应节点！");
    msg.put("beginToCreateConfigFile", "开始创建标书相关信息配置文件...");
    msg.put("configFileCreateFinish", "标书相关信息配置文件创建完成...");
    msg.put("createWaitingUploadListErr", "创建待上传文件列表时出错，详细：");
    msg.put("missingSoftConfigFile", "本制作软件缺少版本配置信息，请重新获取该制作软件！");
    msg.put("missingProjectConfigInfo", "电子投标书中缺少项目相关信息文件，请重新获取！");
    msg.put("missingImportantFile", "当前标段节点下没有找到【" + NODE_NAME_TBYLB + "】或【" + NODE_NAME_BJYLB + "】，无法添加再次报价...");
    msg.put("encodedFinishAndSummit", "您的投标书加密成功,您可以通过【${content}】按钮提交标书!");
    msg.put("encodedFailAndRetry", "您的投标书加密失败，请再次尝试！");
    msg.put("foundErrFiles", "文件检查时发现文件异常，导出失败！请检查异常文件，然后再次尝试导出...");
    msg.put("notEncodedAndPath", "但未对标书进行加密,路径如下：\n");
    msg.put("andPathAs", "路径如下：\n");
    msg.put("bidExportFinish", "投标书导出完成！");
    msg.put("bidExportFail", "投标书导出失败！");
    msg.put("xmlDescription", "XML文件(*.xml)");
    msg.put("wordDocumentDescription", "Microsoft Word文件(*.doc)");
    msg.put("wordDocumentDescription2007", "Microsoft Word 2007文件(*.docx)");
    msg.put("excelDocumentDescription", "Microsoft Excel文件(*.xls)");
    msg.put("excelDocumentDescription2007", "Microsoft Excel 2007文件(*.xlsx)");
    msg.put("ufgovZTBDescription", "用友政务招投标文件(*.ztb)");
    msg.put("ufgovZBTemplateDescription", "用友政务招标书模板文件(*.tpl)");
    msg.put("notExistNotCopy", "文件不存在，无法拷贝！");
    msg.put("errFilePath", "无效的文件路径!");
    msg.put("fileNotExistAutoCreate", "文件不存在，无法拷贝！\n是否自动创建？");
    msg.put("md5ForFileOnly", "只能对文件进行MD5码生成，不能对目录进行MD5码的生成！");
    msg.put("notNeedMasterMinorPubKey", "您好，暂不需要主辅KEY公钥信息，无需导入...");
    msg.put("pubKeyExistIsNeedUpdate", "主辅KEY公钥信息已经导入过，是否需要更新？");
    msg.put("firstCAOKAndAskForSecondCA", "第一张证书公钥已经成功导入，现在请选择第二张证书...");
    msg.put("askForDiffCert", "请选择不同的证书...");
    msg.put("bothKeyImportOK", "主辅KEY公钥信息已经成功导入...");
    msg.put("pleaseInsertUkey", "请插入您的U-Key...");
    msg.put("lackingMainMinorPubKey", "招标书中缺少主辅key的公钥信息(该信息将用于供应商加密标书)，\n是否现在导入？");
    msg.put("lackingServerURL", "招标书中缺少投标书上传服务器地址信息，是否现在补充？");
    msg.put("pubKeyModifyShouldRefetch", "标书文件中的公钥数据可能已经被篡改，请重新获取招标书！\r\n否则将导致加密后无法解密！");
    msg.put("missingTbFileNodeUnderPack", "标书中缺少【投标文件】内容!");
    msg.put("missingTBYLBUnderTbFileNode", "【投标文件】中缺少【开标一览表】!");
    msg.put("missingZbNodeUnderProject", "标书中缺少【招标文件】内容!");
    msg.put("exportSuccAndSyns", "招标书导出成功，且成功同步到服务器上！\n导出路径为：");
    msg.put("exportSuccAndPath", "招标书导出成功！\n导出路径为：");
    msg.put("exportSuccAndSynsFail", "招标书导出成功，但【未成功】同步到服务器上！原因：\n");
    msg.put("exportPath", "\n导出路径为：");
    msg.put("zbFileUploadFail", "【${content}】招标书上传失败，原因：\n");
    msg.put("pleaseSelectHightUploadPack", "请选择要使用高速通道上传的标段:");
    msg.put("pleaseSelectPacksToExp", "请选择要导出的标段:");
    msg.put("bidFailAndReason", "投标失败，原因：${content}，请检查...");
    msg.put("pleaseSelectPackToUpload", "请选择要上传的标段:");
    msg.put("pleaseSelectOnePack", "请先选择一个标段！");
    msg.put("readyToGenerateMD5", "正在生成加密后的标书密文的MD5码...");
    msg.put("finishToGenerateMD5", "加密后的标书密文的MD5码生成完成...");
    msg.put("mergingFiles", "正在进行文件合并...");
    msg.put("mergedFilesFinish", "文件合并过程完成...");
    msg.put("toDeletingTmpFiles", "正在删除过程产生的临时文件...");
    msg.put("tmpFilesDeletingFinish", "相关临时文件删除完成...");
    msg.put("encodedFail", "文件加密失败，详细：");
    msg.put("readySendDataToServerUrl", "正在上传投标书到服务器【${content}】...");
    msg.put("fileNotExistOrBroken", "文件不存在或者已损坏...");
    msg.put("zipTestProgressInfo000", "--------------------<检查需知>--------------------");
    msg.put("zipTestProgressInfo001", ">>1、该文件检查【只支持】本软件支持的若干指定格式文件;");
    msg.put("zipTestProgressInfo002", ">>2、对于非本软件支持的格式文件检查时会被判定为异常文件，检查将无法通过;");
    msg.put("zipTestProgressInfo003", "------------------<开始文件检查>------------------");
    msg.put("zipTestProgressInfo004", ">>正在读取，如果文件格式存在异常，该过程可能需要消耗几分钟时间，请稍候...");
    msg.put("zipTestProgressInfo005", ">>开始文件检查...");
    msg.put("zipTestProgressInfo006", "出现空文件，请检查：");
    msg.put("zipTestProgressInfo007", ">正在检查【${content}】文件...");
    msg.put("zipTestProgressInfo008", ">归档前[");
    msg.put("zipTestProgressInfo009", "],归档后[");
    msg.put("zipTestProgressInfo010", ">检查完毕,状态--------正常！");
    msg.put("zipTestProgressInfo011", ">检查完毕,状态--------【不】正常！");
    msg.put("zipTestProgressInfo012", ">>读取文件时出错，文件已经被损坏或文件格式无法支持!");
    msg.put("zipTestProgressInfo013", ">>出错原因：");
    msg.put("zipTestProgressInfo014", "-----------------<检查结果汇总>------------------");
    msg.put("zipTestProgressInfo015", ">>文件总大小【${content}】;");
    msg.put("zipTestProgressInfo016", ">>子文件总个数【${content}】个;");
    msg.put("zipTestProgressInfo017", ">>正  常文件个数【${content}】个;");
    msg.put("zipTestProgressInfo018", ">>不正常文件个数【${content}】个;");
    msg.put("zipTestProgressInfo019", ">>正在生成MD5码:--大小【${content}】");
    msg.put("zipTestProgressInfo020", ">>文件的MD5校验码【${content}】;");
    msg.put("zipTestProgressInfo021", ">>文件的MD5校验码生成失败，但是【不】影响标书使用！");
    msg.put("zipTestProgressInfo022", ">>文件的MD5校验码生成失败原因：");
    msg.put("zipTestProgressInfo023", ">>检查过程耗时【${content}】分钟;");
    msg.put("zipTestProgressInfo024", ">>检查过程耗时【${content}】秒钟;");
    msg.put("zipTestProgressInfo025", "-----------------<整体检查结论>------------------");
    msg.put("zipTestProgressInfo026", ">>检查【不通过】！！！<<");
    msg.put("zipTestProgressInfo027", ">>检查【通过】！！！<<");
    msg.put("versionControl000", "版本检查通过...");
    msg.put("versionControl001", "版本检测时，发现您的【投标书制作软件】缺少版本信息，请重新获取【软件】.");
    msg.put("versionControl002", "版本检测时，发现您的【电子标书】缺少版本号，请重新获取【标书】.");
    msg.put("versionControl003", "进行版本检测时，系统出现异常，请再次尝试.");
    msg.put("versionControl004", "版本检测时，发现您的【投标书制作软件】版本太低，请重新获取【软件】.");
    msg.put("versionControl005", "系统出现无法处理的异常，请联系软件开发商...");
    msg.put("wordExportPath", "Word版招标文件导出成功，路径如下：\n");
    msg.put("zbBookName", "招标书");
    msg.put("nodeNameErr", "非【开标一览表】类型的节点不能命名为【${content}】!");
    msg.put("mergedWordName", "【${content}】完整标书");
    msg.put("wordMerge.success", "【${content}】合并成功，为保证您标书的内容格式及美观，请重新进行排版！");
    msg.put("mergeWordAlert", "您好，合并产生完整标书请注意：\n   1、如果您已经合并过完整标书，请先清空里面的所有内容，否则将自动追加到已有内容后面！\n   2、如果您的部分WORD内容比较多，合并后可能会因为内容太多而无法正常打开！\n是否继续？");
    msg.put("wordSizeAlert", "您好，合并产生完整标书请注意：\n  1、新节点【${content}】创建成功，点击【确定】按钮将自动开始文件合并！\n   2、如果您的某些WORD内容比较多，合并后可能会因为内容太多而无法打开！\n是否继续？");
    msg.put("bidSelectTitle", "标段选择");
    msg.put("pleaseSelectPacksToMerge", "请选择一个要合并标书的标段：");
    msg.put("PURTYPE_GKZB", "公开招标");
    msg.put("PURTYPE_YQZB", "邀请招标");
    msg.put("PURTYPE_JZXTP", "竞争性谈判");
    msg.put("PURTYPE_DYLYCG", "单一来源采购");
    msg.put("PURTYPE_XYGHECJJ", "协议供货二次竞价");
    msg.put("PURTYPE_QITA", "其他");
    msg.put("noMergeableWord", "没有打开可以合并到的目标WORD文件，请先打开一个将要合并到的文件。");
    msg.put("startingLabelText", "<html><font color='red'>.....正在启动.....</font></html>");
  }

  public static String getOperateMsg(String megId, String content) {
    if (content == null) {
      return msg.get(megId);
    } else {
      return msg.get(megId).replace("${content}", content);
    }
  }

  public static String getSimpleMsg(String key) {
    return msg.get(key);
  }

  public static void initPurTypes() {
    purTypes = new HashMap<String, String>();
    purTypes.put("gongkaizhaobiao", "招标文件");
    purTypes.put("danyilaiyuan", "单一来源采购文件");
    purTypes.put("jingzhengxingtanpan", "竞争性谈判文件");
    purTypes.put("xieyigonghuo", "协议供货二次谈判文件");
  }

  public static String getPurType(String key) {
    return purTypes.get(key);
  }

  public static String NODE_TYPE_FROM_MOLD = "FROM_MOLD";

  public static String NODE_TYPE_FROM_PROJ = "FROM_PROJ";

  private static String importantNodeTypeString = null;

  private static Map<String, String> importantMsgMap = null;

  private static void initImportantMsgMap() {
    StringBuffer buff = new StringBuffer();
    buff.append(GV.NODE_TYPE_PROJECT);
    buff.append("@@");
    buff.append(GV.NODE_TYPE_PACK);
    buff.append("@@");
    buff.append(GV.NODE_TYPE_PACK_BUSINESS);
    buff.append("@@");
    buff.append(GV.NODE_TYPE_PACK_TECH);
    buff.append("@@");
    buff.append(GV.NODE_TYPE_TBYLB);
    importantNodeTypeString = buff.toString();
    importantMsgMap = new HashMap<String, String>();
    importantMsgMap.put(GV.NODE_TYPE_PROJECT, "当前节点为【项目】节点，请谨慎删除...\r\n是否继续？");
    importantMsgMap.put(GV.NODE_TYPE_PACK, "当前节点为【标段(分包)】节点，请谨慎删除...\r\n是否继续？");
    importantMsgMap.put(GV.NODE_TYPE_PACK_BUSINESS, "当前节点为常用的【商务部分】节点，请谨慎删除...\r\n是否继续？");
    importantMsgMap.put(GV.NODE_TYPE_PACK_TECH, "当前节点为常用的【技术部分】节点，请谨慎删除...\r\n是否继续？");
    importantMsgMap.put(GV.NODE_TYPE_TBYLB, "当前节点为常用的【投标一览表】节点，删除后将【投标人将无法报价】，请谨慎删除...\r\n是否继续？");
    importantMsgMap.put(GV.NODE_TYPE_HAS_CHILDREN, "当前节点存在子节点，删除当前节点将级联删除所有子节点，请谨慎删除...\r\n是否继续？");
    importantMsgMap.put(GV.NODE_TYPE_FROM_MOLD, "当前节点由引入模板时自动创建，建议保留...");
    importantMsgMap.put(GV.NODE_TYPE_FROM_PROJ, "当前节点根据项目数据自动创建，建议保留...");
  }

 

  public static void showMessageDialog(JComponent component, String content) {
    JOptionPane.showMessageDialog(component, content, "提示", JOptionPane.INFORMATION_MESSAGE);
  }

  public static int showConfirmDialog(JComponent component, String content, int type) {
    return JOptionPane.showConfirmDialog(component, content, "确认", type);
  }

  private static void initItemForPackVector() {
    itemForPackVector = new Vector<DetailsType>();
    itemForPackVector.add(new DetailsType("Microsoft Word文件", GV.NODE_TYPE_DOC));
    itemForPackVector.add(new DetailsType("自定义表格", GV.NODE_TYPE_TABLE));
    itemForPackVector.add(new DetailsType("文件组(夹)", GV.NODE_TYPE_FOLDER));
    itemForPackVector.add(new DetailsType(GV.NODE_NAME_TBYLB, GV.NODE_TYPE_TBYLB));
    itemForPackVector.add(new DetailsType("竞争性谈判文件", GV.NODE_TYPE_ZB));
    itemForPackVector.add(new DetailsType("协议供货二次谈判文件", GV.NODE_TYPE_ZB));
    itemForPackVector.add(new DetailsType("招标文件", GV.NODE_TYPE_ZB));
    itemForPackVector.add(new DetailsType("响应文件", GV.NODE_TYPE_TB));
    itemForPackVector.add(new DetailsType("投标文件", GV.NODE_TYPE_TB));
  }

  private static void initItemForPackBusinessVector() {
    itemForPackBusinessVector = new Vector<DetailsType>();
    itemForPackBusinessVector.add(new DetailsType("Microsoft Word文件", GV.NODE_TYPE_DOC));
    itemForPackBusinessVector.add(new DetailsType("自定义表格", GV.NODE_TYPE_TABLE));
    itemForPackBusinessVector.add(new DetailsType("文件组(夹)", GV.NODE_TYPE_FOLDER));
    itemForPackBusinessVector.add(new DetailsType(GV.NODE_NAME_TBYLB, GV.NODE_TYPE_TBYLB));
  }

  private static void initItemForPackTechVector() {
    itemForPackTechVector = new Vector<DetailsType>();
    itemForPackTechVector.add(new DetailsType("Microsoft Word文件", GV.NODE_TYPE_DOC));
    itemForPackTechVector.add(new DetailsType("自定义表格", GV.NODE_TYPE_TABLE));
    itemForPackTechVector.add(new DetailsType("文件组(夹)", GV.NODE_TYPE_FOLDER));
    itemForPackTechVector.add(new DetailsType(GV.NODE_NAME_TBYLB, GV.NODE_TYPE_TBYLB));
  }

  private static void initItemForProjVector() {
    itemForProjVector = new Vector<DetailsType>();
    itemForProjVector.add(new DetailsType("Microsoft Word文件", GV.NODE_TYPE_DOC));
    itemForProjVector.add(new DetailsType("自定义表格", GV.NODE_TYPE_TABLE));
    itemForProjVector.add(new DetailsType("文件组(夹)", GV.NODE_TYPE_FOLDER));
    itemForProjVector.add(new DetailsType(GV.NODE_NAME_TBYLB, GV.NODE_TYPE_TBYLB));
    //itemForProjVector.add(new DetailsType("合并所有子目录文件", "mergerAllFiles"));
  }

  private static void initAddableTypeForNode() {
    addableTypeForNode = new HashMap<String, Vector<DetailsType>>();
    addableTypeForNode.put(GV.NODE_TYPE_DIR, itemForPackVector);
    addableTypeForNode.put(GV.NODE_TYPE_PACK_BUSINESS, itemForPackBusinessVector);
    addableTypeForNode.put(GV.NODE_TYPE_PACK_TECH, itemForPackTechVector);
    addableTypeForNode.put(GV.NODE_TYPE_PROJECT, itemForProjVector);
    addableTypeForNode.put(GV.NODE_TYPE_FOLDER, itemForProjVector);
    addableTypeForNode.put(GV.NODE_TYPE_PACK, itemForPackVector);
    addableTypeForNode.put(GV.NODE_TYPE_ZB, itemForProjVector);
    addableTypeForNode.put(GV.NODE_TYPE_TB, itemForProjVector);
  }

  public static Vector<DetailsType> getAddNodeTypeList(String nodeType) {
    return addableTypeForNode.get(nodeType);
  }

  /**
   * 节点允许的右键下拉菜单组
   */
  //分包右键下可以做的操作
  private static void initOptionForPack_RMouse() {
    OptionForPack_RMouse = new Vector<DetailsType>();
    OptionForPack_RMouse.add(new DetailsType("新增文件", "addFile"));
    OptionForPack_RMouse.add(new DetailsType("新建文件组", "addFolder"));//将限定不能以“标段”、“分包”结尾，以区别于标段组
    OptionForPack_RMouse.add(new DetailsType("导入需求", "importRequirement"));
    OptionForPack_RMouse.add(new DetailsType("另存为模板", "saveAsTemplate"));
  }

  private static void initOptionForProj_RMouse() {
    OptionForProj_RMouse = new Vector<DetailsType>();
    OptionForProj_RMouse.add(new DetailsType("另存为模板", "saveAsTemplate"));
  }

  private static void initOptionForYES_NO() {
    OptionForYES_NO = new Vector<DetailsType>();
    OptionForYES_NO.add(new DetailsType("是", "true"));
    OptionForYES_NO.add(new DetailsType("否", "false"));
  }

  private static void initNodeOperationsMap() {
    NodeOperationsMap = new HashMap<String, Vector<DetailsType>>();
    NodeOperationsMap.put(GV.NODE_TYPE_PACK, OptionForPack_RMouse);
    NodeOperationsMap.put(GV.NODE_TYPE_YES_NO, OptionForYES_NO);
  }

  public static Vector<DetailsType> getNodeOptionByNodeType(String nodeType) {
    return NodeOperationsMap.get(nodeType);
  }

  private static void initProjExportStateNames() {
    projExportStateNames = new HashMap<String, String>();
    projExportStateNames.put("ready", "正在准备导出...");
    projExportStateNames.put("begin", "开始导出...");
    projExportStateNames.put("copy", "正在复制${content}");
    projExportStateNames.put("config", "正在生成配置信息${content}");
    projExportStateNames.put("exportFile", "正在生成导出文件");
    projExportStateNames.put("deleteTempFile", "正在删除临时文件...");
    projExportStateNames.put("finish", "导出完成！");
  }

  public static Map<String, String> getProjectExportStateInfoMap() {
    return projExportStateNames;
  }

  private static void initUploadStateNames() {
    uploadStateNames = new HashMap<String, String>();
    uploadStateNames.put("ready", "正在准备上传...");
    uploadStateNames.put("creating", "正在生成上传文件${content}");
    uploadStateNames.put("uploading", "正在上传文件");
    uploadStateNames.put("deleteTempFile", "正在删除临时文件...");
    uploadStateNames.put("finish", "上传完成！");
  }

  public static Map<String, String> getProjectUploadStateInfoMap() {
    return uploadStateNames;
  }

  private static void initProjVerifyStateNames() {
    projVerifyStateNames = new HashMap<String, String>();
    projVerifyStateNames.put("ready", "正在准备校验...");
    projVerifyStateNames.put("begin", "开始校验...");
    projVerifyStateNames.put("verify", "正在校验${content}");
    projVerifyStateNames.put("finish", "校验完成！");
  }

  public static Map<String, String> getProjectVerifyStateInfoMap() {
    return projVerifyStateNames;
  }

  private static void initButtonTextMap() {
    buttonTextMap = new HashMap<String, String>();
    buttonTextMap.put("confirm", "确 认");
    buttonTextMap.put("dbimport", "从服务器导入项目");
    buttonTextMap.put("save", "保 存");
    buttonTextMap.put("moveup", "上  移");
    buttonTextMap.put("movedown", "下  移");
    buttonTextMap.put("dbImpReq", "导入需求");
    buttonTextMap.put("dbFormula", "导入评标方法");
    buttonTextMap.put("dbconfig", "配置");
    buttonTextMap.put("testLink", "测试链接");
    buttonTextMap.put("apply", "应  用");
    buttonTextMap.put("copyTemplate", "选用当前选中模板");
    buttonTextMap.put("cancel", "取 消");
    buttonTextMap.put("fillMold", "填充当前打开文档");
    buttonTextMap.put("lockWord", "保护文档");
    buttonTextMap.put("unLockWord", "解除文档保护");
    buttonTextMap.put("chooeseTemplateBtn", "从本地导入模板到模板库");
    buttonTextMap.put("uploadZBFile", "覆盖服务器端招标书");
    buttonTextMap.put("fetchPubKey", "导入主辅KEY公钥信息");
    buttonTextMap.put("makeProjPlanBtn", "制定项目执行计划");
    buttonTextMap.put("exportZBFileBtn", "导出招标书到本地");
    buttonTextMap.put("uploadZBFileBtn", "上传招标书到服务器");
    buttonTextMap.put("importZbFileBtn", "从本地加载招标书");
  }

  public static Map<String, String> getButtonText() {
    return buttonTextMap;
  }

  public static Map<String, String> getButtonTipText() {
    Map<String, String> map = new HashMap<String, String>();
    return map;
  }

  /**
   * 根据中文名称来获得编号
   */
  private static void initBidWayCNNAME_CODDE_MAP() {
    BidWayCNNAME_CODDE_MAP = new HashMap<String, String>();
    BidWayCNNAME_CODDE_MAP.put(getSimpleMsg("PURTYPE_GKZB"), "1");
    BidWayCNNAME_CODDE_MAP.put(getSimpleMsg("PURTYPE_YQZB"), "2");
    BidWayCNNAME_CODDE_MAP.put(getSimpleMsg("PURTYPE_JZXTP"), "3");
    BidWayCNNAME_CODDE_MAP.put(getSimpleMsg("PURTYPE_DYLYCG"), "4");
    BidWayCNNAME_CODDE_MAP.put(getSimpleMsg("PURTYPE_XYGHECJJ"), "7");
    BidWayCNNAME_CODDE_MAP.put(getSimpleMsg("PURTYPE_QITA"), "6");
  }

  public static String getBidWayCodeByCnName(String cnName) {
    return BidWayCNNAME_CODDE_MAP.get(cnName);
  }

  private static void initEXT_TYPE_MAP() {
    EXT_TYPE_MAP = new HashMap<String, String>();
    EXT_TYPE_MAP.put(".doc", GV.NODE_TYPE_DOC);
    EXT_TYPE_MAP.put(".config", GV.NODE_TYPE_TABLE);
    EXT_TYPE_MAP.put(".setting", GV.NODE_TYPE_TBYLB);
    EXT_TYPE_MAP.put("dir", GV.NODE_TYPE_DIR);
    EXT_TYPE_MAP.put(".xml", GV.NODE_TYPE_ECBJB);
  }

  public static String getTypeByExt(String ext) {
    return EXT_TYPE_MAP.get(ext);
  }

  /**
   * 检查内存的大小是否可以放下一个文件数组
   *
   * @param fileSize
   * @param fileName
   * @return
   */
  public boolean checkMemory(int fileSize, String fileName) {
    int resultSize = fileSize / 1024 / 1024;
    long vmMaxMemSize = Runtime.getRuntime().maxMemory() / 1024 / 1024;//M
    long vmTotalMemSize = Runtime.getRuntime().totalMemory() / 1024 / 1024;//M
    int useableMemSize = (int) (vmMaxMemSize - vmTotalMemSize);
    if ((resultSize - useableMemSize) > 5) {
      JOptionPane.showMessageDialog(null, "文件" + fileName + "太大,无法处理[" + resultSize + "MB],不能超过[" + (useableMemSize - 20) + "MB].");
      return false;
    }
    return true;
  }

  /**
   * 获得招标方式的数组
   *
   * @return
   * @throws Exception
   */
  public static String[] getTplBelongBidWayList() {
    return new String[] { getSimpleMsg("PURTYPE_GKZB"), getSimpleMsg("PURTYPE_YQZB"), getSimpleMsg("PURTYPE_JZXTP"), getSimpleMsg("PURTYPE_DYLYCG"),
      getSimpleMsg("PURTYPE_XYGHECJJ"), getSimpleMsg("PURTYPE_QITA") };
  }

  /**
   * 获得招标方式的数组
   *
   * @return
   * @throws Exception
   */
  public static String[] getAppTypeList() {
    return new String[] { "货物类", "工程类", "服务类" };
  }

 


  public static String getLevelOneXmlFullPath() {
    SimpleDateFormat sdf = new SimpleDateFormat(ZcSettingConstants.SIMPLE_DATE_FORMAT_DATE_ONLY);
    String today = sdf.format(new Date());
    return System.getProperty("user.dir") + "\\tools\\levelOne\\" + today.substring(0, today.lastIndexOf("-")) + GV.SUFFIX_XML;
  }

  public static String getFileToUploadXmlFullPath() {
    return System.getProperty("user.dir") + "\\tools\\levelOne\\toUploadFileList" + GV.SUFFIX_XML;
  }

  public static StringBuffer getLevelTwoXmlBasePath() {
    StringBuffer buff = new StringBuffer(System.getProperty("user.dir"));
    buff.append("\\tools\\levelOne\\");
    SimpleDateFormat sdf = new SimpleDateFormat(ZcSettingConstants.SIMPLE_DATE_FORMAT_DATE_ONLY);
    String today = sdf.format(new Date());
    buff.append(today.substring(0, today.lastIndexOf("-")));
    buff.append("_L2\\");
    return buff;
  }

  public static void initStatusCode() {
    STATUS_CODE_MAP = new HashMap<String, String>();
    STATUS_CODE_MAP.put("TS_START", "传输开始");
    STATUS_CODE_MAP.put("TS_OK", "传输正常");
    STATUS_CODE_MAP.put("TS_INT", "传输中断");
    STATUS_CODE_MAP.put("NET_INT", "网络中断");
    STATUS_CODE_MAP.put("TS_END", "传输完成");
    STATUS_CODE_MAP.put("LINK_ERR", "链接无效");
    STATUS_CODE_MAP.put("PARA_MISS", "缺少必要的参数");
    STATUS_CODE_MAP.put("SERVER_WRITE_ERR", "服务器端写文件失败");
    STATUS_CODE_MAP.put("CA_AUTH_READY", "准备向CA服务器发起CA身份验证...");
    STATUS_CODE_MAP.put("CA_AUTH_PWD", "如果是第一次验证，请输入口令...");
    STATUS_CODE_MAP.put("CA_AUTH", "向CA服务器发起CA身份验证中...");
    STATUS_CODE_MAP.put("CA_AUTH_OK", "CA服务器对CA身份验证完成...");
    STATUS_CODE_MAP.put("USER_AUTH", "向服务器发起供应商身份、报名投标情况等验证中...");
    STATUS_CODE_MAP.put("USER_AUTH_OK", "服务器对供应商身份、报名投标情况等验证完成...");
    STATUS_CODE_MAP.put("USER_AUTH_PASS", "供应商身份、报名投标情况等验证【通过】...");
    STATUS_CODE_MAP.put("USER_AUTH_FAIL", "供应商身份、报名投标情况等验证【不通过】...");
  }

  public static String transStatusCode(String statusCode) {
    return STATUS_CODE_MAP.get(statusCode);
  }

  /**
   * 非法字符
   */
  public static String CREATE_ERROR_CHARS = "!@#$%^&*()+=|}'':{,.<>?\\/";

  public static String USER_ID = "USER_ID";

  public static String USER_NAME = "USER_NAME";

  public static String CASERIAL_CODE = "CASERIAL_CODE";

  public static String USER_TYPE = "USER_TYPE";

  public static final String AUTHEN_TYPE_LOGIN_ONLY = "LOGIN_ONLY";

  public static final String AUTHEN_TYPE_LOGIN_AND_UPLOAD = "LOGIN_AND_UPLOAD";

  public static final Map<String, String> SESSION_MAP = new HashMap<String, String>();

  public static final String AUTHEN_TYPE = "AUTHENTYPE";


}
