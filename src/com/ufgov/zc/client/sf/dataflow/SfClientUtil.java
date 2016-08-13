/**
 * 
 */
package com.ufgov.zc.client.sf.dataflow;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.StringToModel;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.client.sf.component.JDragPanel;
import com.ufgov.zc.client.sf.util.SfBookmarkUtil;
import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.util.DateUtil;
import com.ufgov.zc.common.util.EmpMeta;

/**
 * @author Administrator
 *
 */
public class SfClientUtil {

	  public static final String MENU_INSERT_CONTENT ="插入内容";
	  
	  public static final String MENU_WTF="委托方";  
	  public static final String MENU_SJR="送检人"; 
	  public static final String MENU_JD_TARGET="鉴定对象"; 
	  
	  public static final String MENU_WTF_NAME=LangTransMeta.translate(SfEntrustor.NAME); 
	  public static final String MENU_WTF_ADDRESS="委托方"+LangTransMeta.translate(SfEntrustor.ADDRESS); 
	  public static final String MENU_WTF_ZIP="委托方"+LangTransMeta.translate(SfEntrustor.ZIP); 
	  public static final String MENU_WTF_LINK_MAN="委托方"+LangTransMeta.translate(SfEntrustor.LINK_MAN); 
	  public static final String MENU_WTF_LINK_TEL="委托方"+LangTransMeta.translate(SfEntrustor.LINK_TEL);
	  
	  public static final String MENU_SJR_SJR=LangTransMeta.translate(SfEntrust.COL_SJR); 
	  public static final String MENU_SJR_SJR_TEL=LangTransMeta.translate(SfEntrust.COL_SJR_TEL);
	  public static final String MENU_SJR_SJR_ZJ_TYPE=LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_TYPE);
	  public static final String MENU_SJR_SJR_ZJ_CODE=LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_CODE); 
	  
	  public static final String MENU_JD_TARGET_NAME=LangTransMeta.translate(SfJdTarget.COL_NAME); 
	  public static final String MENU_JD_TARGET_SEX=LangTransMeta.translate(SfJdTarget.COL_SEX); 
	  public static final String MENU_JD_TARGET_AGE="鉴定对象"+LangTransMeta.translate(SfJdTarget.COL_AGE); 
	  public static final String MENU_JD_TARGET_ID_NAME="鉴定对象"+LangTransMeta.translate(SfJdTarget.COL_ID_NAME); 
	  public static final String MENU_JD_TARGET_ID_CODE="鉴定对象"+LangTransMeta.translate(SfJdTarget.COL_ID_CODE); 
	  public static final String MENU_JD_TARGET_PHONE=LangTransMeta.translate(SfJdTarget.COL_PHONE); 
	  public static final String MENU_JD_TARGET_ADDRESS="鉴定对象"+LangTransMeta.translate(SfJdTarget.COL_ADDRESS); 
	  public static final String MENU_JD_TARGET_ZIP="鉴定对象"+LangTransMeta.translate(SfJdTarget.COL_ZIP);  
	  
	  public static final String MENU_JD_BRIEF=LangTransMeta.translate(SfEntrust.COL_BRIEF); 
	  public static final String MENU_JD_NAME=LangTransMeta.translate(SfEntrust.COL_NAME); 
	  public static final String MENU_JD_CODE=LangTransMeta.translate(SfEntrust.COL_CODE); 
	  public static final String MENU_JD_MATERIALS="检材样本";  
	  public static final String MENU_JD_REQUIRE=LangTransMeta.translate(SfEntrust.COL_JD_REQUIRE); 
	  public static final String MENU_JD_MAJOR=LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME); 
	  public static final String MENU_JD_FZR=LangTransMeta.translate(SfEntrust.COL_JD_FZR);  
	  public static final String MENU_JD_FHR=LangTransMeta.translate(SfEntrust.COL_JD_FHR);  
	  public static final String MENU_JD_COMPANY=LangTransMeta.translate(SfEntrust.COL_JD_COMPANY); 
	  public static final String MENU_JD_ACCEPT_DATE=LangTransMeta.translate(SfEntrust.COL_ACCEPT_DATE); 
	  public static final String MENU_JD_ACCEPT_PERSON=LangTransMeta.translate(SfEntrust.COL_ACCEPTOR); 
	  public static final String MENU_WT_DATE=LangTransMeta.translate(SfEntrust.COL_WT_DATE); 
	  

	  public static final String MENU_REFRENCE="引用";
	  public static final String MENU_REFRENCE_ENTRUST="其他委托";
	  public static final String MENU_REFRENCE_ENTRUST_RECORD="其他记录文件"; 
	  public static final String MENU_REFRENCE_REORT="其他意见书";
	  public static final String MENU_FILL="填充模板";
	  public static final String MENU_FILL_CUR="填充当前模板";

	  public static final String MENU_LOOKUP="查看";
	  public static final String MENU_LOOKUP_ENTRUST_RECORD="鉴定记录";
	  public static final String MENU_LOOKUP_ENTRUST_REPORT="其他鉴定报告";

	  public static final String MENU_INSERT_MODEL ="插入模板";
	  public static final String MENU_SELECT_MODEL ="选择";
	  

	  public static final String MENU_JDJG ="鉴定机构";
	  public static final String MENU_JDJG_NAME =MENU_JDJG+LangTransMeta.translate(SfJdjg.COL_NAME);
	  public static final String MENU_JDJG_XKZH =MENU_JDJG+LangTransMeta.translate(SfJdjg.COL_XKZH);
	  public static final String MENU_JDJG_ADDRESS =MENU_JDJG+LangTransMeta.translate(SfJdjg.COL_ADDRESS);
	  public static final String MENU_JDJG_TEL =MENU_JDJG+LangTransMeta.translate(SfJdjg.COL_TEL);
	  public static final String MENU_JDJG_LINK_MAN =MENU_JDJG+LangTransMeta.translate(SfJdjg.COL_LINK_MAN);
	  public static final String MENU_JDJG_ZIP =MENU_JDJG+LangTransMeta.translate(SfJdjg.COL_ZIP);
	  public static final String MENU_JDJG_FAX =MENU_JDJG+LangTransMeta.translate(SfJdjg.COL_FAX); 
	  
	  //鉴定记录
	  public static final String MENU_RECORD ="鉴定记录";
	  public static final String MENU_RECORD_JD_DATE=LangTransMeta.translate(SfJdResult.COL_JD_DATE); 
	  public static final String MENU_RECORD_JD_OPINION=LangTransMeta.translate(SfJdResult.COL_JD_OPINION); 
	  public static final String MENU_RECORD_JD_PROCESS=LangTransMeta.translate(SfJdResult.COL_JD_PROCESS); 
	  public static final String MENU_RECORD_JD_RESULT=LangTransMeta.translate(SfJdResult.COL_JD_RESULT); 
	  public static final String MENU_RECORD_ZC_PERSONS=LangTransMeta.translate(SfJdResult.COL_ZC_PERSONS); 

	  //鉴定报告
	  public static final String MENU_REPORT ="鉴定报告";
	  public static final String MENU_REPORT_NO=LangTransMeta.translate(SfJdReport.COL_REPORT_CODE); 
	  

	  public static String getTxtFromBill(String menuTitle,Object obj,RequestMeta meta) {
		  StringBuffer sb=new StringBuffer();
			if(obj instanceof SfEntrust){
				SfEntrust bill=(SfEntrust)obj;
				sb.append(getTxtFromEntrust(menuTitle,bill,meta)); 
				sb.append(getTxtFromEntrustor(menuTitle,bill.getEntrustor(),meta)); 
				sb.append(getTxtFromSfJdTarget(menuTitle,bill.getJdTarget(),meta)); 
			}else if(obj instanceof SfEntrustor){
				SfEntrustor bill=(SfEntrustor)obj;
				sb.append(getTxtFromEntrustor(menuTitle, bill,meta));
			}else if(obj instanceof SfJdTarget){
				SfJdTarget bill=(SfJdTarget)obj;
				sb.append(getTxtFromSfJdTarget(menuTitle, bill,meta));
			}else if(obj instanceof SfJdResult){
				SfJdResult result=(SfJdResult)obj;
				SfEntrust bill=result.getEntrust();
				sb.append(getTxtFromEntrust(menuTitle,bill,meta)); 
				sb.append(getTxtFromEntrustor(menuTitle,bill.getEntrustor(),meta)); 
				sb.append(getTxtFromSfJdTarget(menuTitle,bill.getJdTarget(),meta)); 
				sb.append(getTxtFromRecord(menuTitle, result, meta));				
			}else if(obj instanceof SfJdReport){
				SfJdReport report=(SfJdReport)obj;
				SfEntrust bill=report.getEntrust();
				sb.append(getTxtFromEntrust(menuTitle,bill,meta)); 
				sb.append(getTxtFromEntrustor(menuTitle,bill.getEntrustor(),meta)); 
				sb.append(getTxtFromSfJdTarget(menuTitle,bill.getJdTarget(),meta));  
				sb.append(getTxtFromRecord(menuTitle, report.getResult(), meta)); 
				sb.append(getTxtFromReport(menuTitle, report, meta));								
				
			}
			return sb.toString();
		}

  private static Object getTxtFromReport(String menuTitle, SfJdReport bill,			RequestMeta meta) {

	  StringBuffer sb=new StringBuffer();
	  if(SfClientUtil.MENU_REPORT_NO.equals(menuTitle)){ 
			sb.append(bill.getReportCode());
		}   
	  return sb.toString().equalsIgnoreCase("null")?"":sb.toString();
	}

private static Object getTxtFromRecord(String menuTitle, SfJdResult bill,			RequestMeta meta) {

	  StringBuffer sb=new StringBuffer();
	  if(SfClientUtil.MENU_RECORD_JD_DATE.equals(menuTitle)){
			sb.append(DateUtil.dateToChinaString(bill.getJdDate()));
		}else if(SfClientUtil.MENU_RECORD_JD_OPINION.equals(menuTitle)){ 
			sb.append(bill.getJdOpinion());
		}else if(SfClientUtil.MENU_RECORD_JD_PROCESS.equals(menuTitle)){
			sb.append(bill.getJdProcess());
		}else if(SfClientUtil.MENU_RECORD_JD_RESULT.equals(menuTitle)){
			sb.append(bill.getJdResult());
		}else if(SfClientUtil.MENU_RECORD_ZC_PERSONS.equals(menuTitle)){
			sb.append(bill.getZcPersons());
		} 	  
	  return sb.toString().equalsIgnoreCase("null")?"":sb.toString();
	}

private static String getTxtFromSfJdTarget(String menuTitle, SfJdTarget bill,RequestMeta meta) {
	  StringBuffer sb=new StringBuffer();
	  if(SfClientUtil.MENU_JD_TARGET_NAME.equals(menuTitle)){
			sb.append(bill.getName());
		}else if(SfClientUtil.MENU_JD_TARGET_SEX.equals(menuTitle)){
			sb.append(AsValDataCache.getName(SfElementConstants.VS_SEX, bill.getSex()));
		}else if(SfClientUtil.MENU_JD_TARGET_AGE.equals(menuTitle)){
			sb.append(bill.getAge());
		}else if(SfClientUtil.MENU_JD_TARGET_ID_NAME.equals(menuTitle)){
			sb.append(bill.getIdName());
		}else if(SfClientUtil.MENU_JD_TARGET_ID_CODE.equals(menuTitle)){
			sb.append(bill.getIdCode());
		}else if(SfClientUtil.MENU_JD_TARGET_PHONE.equals(menuTitle)){
			sb.append(bill.getPhone());
		}else if(SfClientUtil.MENU_JD_TARGET_ADDRESS.equals(menuTitle)){
			sb.append(bill.getAddress());
		}else if(SfClientUtil.MENU_JD_TARGET_ZIP.equals(menuTitle)){
			sb.append(bill.getZip());
		} 
	  
	  return sb.toString().equalsIgnoreCase("null")?"":sb.toString();
	}

private static String getTxtFromEntrustor(String menuTitle, SfEntrustor bill,RequestMeta meta) {
	StringBuffer sb=new StringBuffer();

	if(SfClientUtil.MENU_WTF_NAME.equals(menuTitle)){
		sb.append(bill.getName());
	}else if(SfClientUtil.MENU_WTF_ADDRESS.equals(menuTitle)){
		sb.append(bill.getAddress());
	}else if(SfClientUtil.MENU_WTF_ZIP.equals(menuTitle)){
		sb.append(bill.getZip());
	}else if(SfClientUtil.MENU_WTF_LINK_MAN.equals(menuTitle)){
		sb.append(bill.getLinkMan());
	}else if(SfClientUtil.MENU_WTF_LINK_TEL.equals(menuTitle)){
		sb.append(bill.getLinkTel());
	} 
		return sb.toString().equalsIgnoreCase("null")?"":sb.toString();
	}

private static String getTxtFromEntrust(String menuTitle, SfEntrust bill,RequestMeta meta) {
	StringBuffer sb=new StringBuffer();
	
	if(SfClientUtil.MENU_SJR_SJR.equals(menuTitle)){
		sb.append(bill.getSjr());
	}else if(SfClientUtil.MENU_SJR_SJR_TEL.equals(menuTitle)){
		sb.append(bill.getSjrTel());
	}else if(SfClientUtil.MENU_SJR_SJR_ZJ_TYPE.equals(menuTitle)){
		sb.append(bill.getSjrZjType());
	}else if(SfClientUtil.MENU_SJR_SJR_ZJ_CODE.equals(menuTitle)){
		sb.append(bill.getSjrZjCode());
	}else if(SfClientUtil.MENU_JD_BRIEF.equals(menuTitle)){
		sb.append(bill.getBrief());
	}else if(SfClientUtil.MENU_JD_NAME.equals(menuTitle)){
		sb.append(bill.getName());
	}else if(SfClientUtil.MENU_JD_CODE.equals(menuTitle)){
		sb.append(bill.getCode());
	}else if(SfClientUtil.MENU_JD_MATERIALS.equals(menuTitle)){
		sb.append(SfBookmarkUtil.getJdclString(bill));
	}else if(SfClientUtil.MENU_JD_REQUIRE.equals(menuTitle)){
		sb.append(bill.getJdRequire());
	}else if(SfClientUtil.MENU_JD_MAJOR.equals(menuTitle)){
		sb.append(bill.getMajor().getMajorName());
	}else if(SfClientUtil.MENU_JD_FZR.equals(menuTitle)){
		sb.append(EmpMeta.getEmpName(bill.getJdFzr()));
	}else if(SfClientUtil.MENU_JD_FHR.equals(menuTitle)){
		sb.append(bill.getJdFhrName());
	}else if(SfClientUtil.MENU_JD_COMPANY.equals(menuTitle)){
		sb.append(meta.getSvCoName());
	}else if(SfClientUtil.MENU_JD_ACCEPT_DATE.equals(menuTitle)){
		sb.append(DateUtil.dateToChinaString(bill.getAcceptDate()));
	}else if(SfClientUtil.MENU_JD_ACCEPT_PERSON.equals(menuTitle)){
		sb.append(bill.getAcceptorName());
	}else if(SfClientUtil.MENU_WT_DATE.equals(menuTitle)){
		sb.append(DateUtil.dateToChinaString(bill.getWtDate()));
	} 
	
	return sb.toString().equalsIgnoreCase("null")?"":sb.toString();
	}

public JDragPanel createPanel(List<AbstractFieldEditor> editorList,int cols, Window parent) {
    // TCJLODO Auto-generated method stub

    JDragPanel panel=new JDragPanel(parent);

    panel.setLayout(new GridBagLayout());
    
    int row = 0;

    int col = 0;

    for (int i = 0; i < editorList.size(); i++) {

      AbstractFieldEditor comp = editorList.get(i);

      if (comp.isVisible()) {

        if (comp instanceof NewLineFieldEditor) {
          row++;
          col = 0;
          continue;
        } else if (comp instanceof TextAreaFieldEditor) {
          // 转到新的一行
          row++;
          col = 0;
          JLabel label = new JLabel(getLabelText(comp));
          if (comp.getMaxContentSize() != 9999) {
            label.setText(comp.getName() + "("+ comp.getMaxContentSize() + "字内)" + "*");
          }
          comp.setPreferredSize(new Dimension(150,  comp.getOccRow() * 26));
          
          panel.add(label, new GridBagConstraints(col,row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST,GridBagConstraints.NONE, new Insets(4, 0, 4, 4), 0,0));

          panel.add(comp, new GridBagConstraints(col + 1,row, comp.getOccCol(), comp.getOccRow(), 1.0, 1.0,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, new Insets(4, 0, 4,4), 0, 0));

          // 将当前所占的行空间偏移量计算上
          row += comp.getOccRow();
          col = 0;

          continue;
        }

        JLabel label = new JLabel(comp.getName());

        comp.setPreferredSize(new Dimension(200, 23));

        panel.add(label, new GridBagConstraints(col, row, 1,1, 1.0, 1.0, GridBagConstraints.EAST,GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));

        panel.add(comp, new GridBagConstraints(col + 1, row,1, 1, 1.0, 1.0, GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5),0, 0));

        if (col == cols * 2 - 2) {
          row++;
          col = 0;
        } else {
          col += 2;
        }
      }

    }
    return panel;
  }
  public String getLabelText(AbstractFieldEditor comp) {

    StringBuffer buff = new StringBuffer();

    buff.append("<html><a>&nbsp;");

    buff.append(comp.getName());

    if (comp.getMaxContentSize() <= 0) {

      buff.append("</a></html>");

    } else {

      if (comp.getOccRow() >= 2) {

        buff.append("<br>(");

      } else {

        buff.append("(");

      }

      buff.append(comp.getMaxContentSize());

      buff.append("字内)</a></html>");

    }

    return buff.toString();

  }

  public List<SfFlowNode> loadModelNode(String xml) throws SfBusinessException {

    List<SfFlowNode> rtn=new ArrayList<SfFlowNode>();
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
//      System.out.println("==================="+this.getClass().getResource(""));
//      System.out.println("/img/sf/wenju.bmp==================="+this.getClass().getResourceAsStream("/img/sf/wenju.bmp"));
//      System.out.println("/img/sf/sf_flow_node_config.xml==================="+this.getClass().getResourceAsStream("/img/sf/sf_flow_node_config.xml"));
//      System.out.println("/img/sf/sffff.xml==================="+this.getClass().getResourceAsStream("/img/sf/sffff.xml"));
//      System.out.println("sffff.xml==================="+this.getClass().getResourceAsStream("sffff.xml"));
//      System.out.println("sf_flow_node_config.xml==================="+this.getClass().getResourceAsStream("sf_flow_node_config.xml"));
//      System.out.println("/com/ufgov/zc/client/sf/dataflow/sf_flow_node_config.xml==================="+this.getClass().getResourceAsStream("/com/ufgov/zc/client/sf/dataflow/sf_flow_node_config.xml"));
      Document doc = db.parse(this.getClass().getResourceAsStream(xml));
      
      
      Element root = doc.getDocumentElement();
      NodeList elements = root.getChildNodes();
      if (elements != null) {
        for (int i = 0; i < elements.getLength(); i++) {
          org.w3c.dom.Node elementNode = elements.item(i);
          if (elementNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
            if (elementNode.getNodeName().equalsIgnoreCase("node")) {
              String text = getStringAttribute(elementNode, "text");
              String compoId = getStringAttribute(elementNode, "compoId");
              String icon = getStringAttribute(elementNode, "icon");
              String toolTip = getStringAttribute(elementNode, "toolTip");
              String nobusi = getStringAttribute(elementNode, "nodeBusiness");
              String isMutliRecord = getStringAttribute(elementNode, "isMutliRecord");

              SfFlowNode flowNode = new SfFlowNode();
              flowNode.setCompoId(compoId);
              flowNode.setText(text);
              flowNode.setToolTip(toolTip);
              flowNode.setIcon(icon);              
              if (nobusi != null) {
                flowNode.setNodeBusiness((ISfFlowNodeBusiness) StringToModel.stringToInstance(nobusi));
              }
              if(isMutliRecord!=null && "true".equalsIgnoreCase(isMutliRecord)){
                flowNode.setMutliRecord(true);
              }
              rtn.add(flowNode);
            }
          }
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new SfBusinessException("加载图形配置文件出错:"+xml,ex);
    }
    return rtn;
  }
  private static String getStringAttribute(org.w3c.dom.Node node, String name) {
    org.w3c.dom.Node attribute = node.getAttributes().getNamedItem(name);
    if (attribute != null) {
      return attribute.getNodeValue();
    } else {
      return null;
    }
  }

  private static int getIntAttribute(org.w3c.dom.Node node, String name) {
    String value = getStringAttribute(node, name);
    if (value != null && !value.isEmpty()) {
      return Integer.valueOf(value);
    }
    return 0;
  }

public static String getTxtFromJdjg(String menuTitle, SfJdjg jdjg) {
	
	if(jdjg==null){
		return "";
	}
	  StringBuffer sb=new StringBuffer();
	  if(SfClientUtil.MENU_JDJG_ADDRESS.equals(menuTitle)){
			sb.append(jdjg.getAddress());
		}else if(SfClientUtil.MENU_JDJG_FAX.equals(menuTitle)){
			sb.append(jdjg.getFax());
		}else if(SfClientUtil.MENU_JDJG_LINK_MAN.equals(menuTitle)){
			sb.append(jdjg.getLinkMan());
		}else if(SfClientUtil.MENU_JDJG_NAME.equals(menuTitle)){
			sb.append(jdjg.getName());
		}else if(SfClientUtil.MENU_JDJG_TEL.equals(menuTitle)){
			sb.append(jdjg.getTel());
		}else if(SfClientUtil.MENU_JDJG_XKZH.equals(menuTitle)){
			sb.append(jdjg.getXkzh());
		}else if(SfClientUtil.MENU_JDJG_ZIP.equals(menuTitle)){
			sb.append(jdjg.getZip());
		} 
	  return sb.toString().equalsIgnoreCase("null")?"":sb.toString();
}
}
