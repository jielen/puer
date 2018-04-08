package com.ufgov.zc.common.system.constants;

public interface SfElementConstants {
  
  /**
   * 科室通用受理人userid
   */
  public static final String KESHI_SHOULI = "KESHI_SHOULI";
  
  /**
   * 综合值班人userid
   */
  public static final String ZONGHE_SHOULI = "ZONGHE_SHOULI";

  /**
   * 性别值集。0：男，1：女
   */
  public static final String VS_SEX = "VS_SEX";

  /**
   * 性别值集。Y：是，N：否
   */
  public static final String VS_Y_N = "VS_Y/N";

  public static final String VAL_Y = "Y";

  public static final String VAL_N = "N";

  /**
   * VS_Y/N有个空值，其valid是K
   */
  //  public static final String VAL_Y_N_NULL = "K";

  /**
   * 内部部门。sql形式实现，支持动态变化 
   * 存放在表as_valset中
   * SELECT 'SF_VS_ORG' AS VALSET_ID,O.ORG_CODE AS VAL_ID,O.ORG_NAME AS VAL FROM AS_ORG O WHERE O.ND='@@svNd' AND O.CO_CODE='@@svCoCode'
   */
  public static final String VS_SF_ORG = "VS_SF_ORG";

  /**
   * 鉴定机构信息：名称
   */
  public static final String OPT_SF_JD_COMPANY_NAME = "OPT_SF_JD_COMPANY_NAME";

  /**
   * 鉴定机构信息：许可证号
   */
  public static final String OPT_SF_JD_COMPANY_XKZ = "OPT_SF_JD_COMPANY_XKZ";

  /**
   * 鉴定机构信息：地址
   */
  public static final String OPT_SF_JD_COMPANY_ADDRESS = "OPT_SF_JD_COMPANY_ADDRESS";

  /**
   * 鉴定机构信息：邮编
   */
  public static final String OPT_SF_JD_COMPANY_ZIP = "OPT_SF_JD_COMPANY_ZIP";

  /**
   * 鉴定机构信息：联系电话
   */
  public static final String OPT_SF_JD_COMPANY_TEL = "OPT_SF_JD_COMPANY_TEL";
  /**
   * 鉴定机构信息：联系电话
   */
  public static final String OPT_SF_JD_COMPANY_LINK_MAN = "OPT_SF_JD_COMPANY_LINK_MAN";
  /**
   * 鉴定机构信息：联系电话
   */
  public static final String OPT_SF_JD_COMPANY_FAX = "OPT_SF_JD_COMPANY_FAX";

  /**
   * 法医鉴定专业编号
   */
  public static final String OPT_SF_MAJOR_FA_YI_CODE = "OPT_SF_MAJOR_FA_YI_CODE";
  

  /**
   * 是否使用ftp
   */
  public static final String OPT_SF_FTP_USED = "OPT_SF_FTP_USED";
  /**
   * ftp ip
   * 
   */
  public static final String OPT_SF_FTP_HOST = "OPT_SF_FTP_HOST";
  /**
   * ftp 端口
   */
  public static final String OPT_SF_FTP_PORT = "OPT_SF_FTP_PORT";
  /**
   * ftp 用户
   */
  public static final String OPT_SF_FTP_USER = "OPT_SF_FTP_USER";
  /**
   * ftp 用户密码
   */
  public static final String OPT_SF_FTP_PASSWORD = "OPT_SF_FTP_PASSWORD";
  /**
   * ftp 最大连接数
   */
  public static final String OPT_SF_FTP_MAX_COLLECTION = "OPT_SF_FTP_MAX_COLLECTION";
  /**
   * ftp 中鉴定过程文件存放的根目录
   */
  public static final String OPT_SF_FTP_DIR_RECORD = "OPT_SF_FTP_DIR_RECORD";

  // word文件加密的密码
  public static final String WORD_PASSWORD = "sfjd";

  public static final String FUNC_NEW = "fnew";

  public static final String FUNC_ADD = "fadd";

  public static final String FUNC_WATCH = "fwatch";

}
