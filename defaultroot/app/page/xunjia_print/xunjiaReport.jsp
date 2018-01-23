<%@page contentType="application/msword;charset=GBK" %>
<%@page language="java" pageEncoding="GBK" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%
    response.setHeader("Content-disposition", "attachment;filename=result.doc");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>询价汇总表</title>
    </head>
    <body  lang=ZH-CN style='text-justify-trim:punctuation'>
        <div class=Section1 style='layout-grid:15.6pt'>
            <p class=MsoNormal><span lang=EN-US style='font-size:22.0pt'><bean:write name="zcEbRfqPack" property="projCode"></bean:write></span><span
                    style='font-size:22.0pt;font-family:宋体'>号</span></p>

            <p class=MsoNormal style='text-indent:177.1pt'><span style='font-size:24.0pt;font-family:华文细黑'>询价汇总表</span><span style='font-size:24.0pt'> </span></p>
            <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
                    style='font-size:14.0pt;font-family:宋体'>西安市市级单位政府采购中心</span>
                <span lang=EN-US style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p>

        <table class=MsoNormalTable  border=1 cellspacing=0 cellpadding=0 width=1019
            style="width:764.2pt;margin-left:36.9pt;border-collapse:collapse;border:none">
            <tr style='page-break-inside:avoid;height:30.9pt'>
                <td width=196 colspan=2 valign=top style='width:147.0pt;border:solid windowtext 1.0pt;
                padding:0cm 5.4pt 0cm 5.4pt;height:30.9pt'>
                <p class=MsoNormal><span lang=EN-US style='font-size:9.0pt;font-family:"仿宋_GB2312","serif"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span><span style='font-size:9.0pt;font-family:"仿宋_GB2312","serif"'>数量及供应商报价\<span
                        lang=EN-US>(</span>元<span lang=EN-US>)</span></span></p>
                <p class=MsoNormal><span lang=EN-US style='font-size:9.0pt;font-family:"仿宋_GB2312","serif"'>&nbsp;</span></p>
                <p class=MsoNormal><span style='font-size:9.0pt;font-family:"仿宋_GB2312","serif"'>商品名称、品牌型号及主要配置</span></p>
            </td>
                <logic:iterate id="xjd" name="xjdList">
                    <td width=224 style='width:168.15pt;border-top:solid windowtext 1.0pt;
            border-left:none;border-bottom:none;border-right:solid windowtext 1.0pt;
            padding:0cm 5.4pt 0cm 5.4pt;height:30.9pt'>
                        <p><bean:write property="providerName" name="xjd"></bean:write></p>
                    </td>
                </logic:iterate>
            </tr>
            <logic:iterate id="baoJia" name="baoJiaList">
            <tr style='page-break-inside:avoid;height:29.35pt'>
                <td width=196 colspan=2 style='width:147.0pt;border:solid windowtext 1.0pt;
                border-top:none;border-bottom:none;padding:0cm 5.4pt 0cm 5.4pt;height:29.35pt'>
                    <bean:write name="baoJia" property="spName"></bean:write>
                </td>
                <logic:iterate id="xjd" name="xjdList">
                    <td width=224 style='width:168.15pt;border-top:solid windowtext 1.0pt;
                    border-left:none;border-bottom:none;border-right:solid windowtext 1.0pt;
                    padding:0cm 5.4pt 0cm 5.4pt;height:30.9pt' >
                        <bean:write name="xjd" property="spTotalSum"></bean:write>
                    </td>
                </logic:iterate>
            </tr>
            </logic:iterate>
        </table>
        <table class=MsoNormalTable  border=1 cellspacing=0 cellpadding=0 width=1019
style="width:764.2pt;margin-left:36.9pt;border-collapse:collapse;border:none">
            <tr>
                <td height="89" colspan="5">
                    <font size="4" face="黑体">询价小组意见：</font>
                    <font size="4" face="仿宋_GB2312"><bean:write name="zcEbRfqPack" property="rfqTeamOpinion"></bean:write>
                    </font>
                </td>
            </tr>
            <tr>
                <td width="261" height="29">
                    <font size="4" face="黑体">成交单位</font>
                </td>
                <td colspan="4"><bean:write name="zcEbRfqPack" property="winBidProviderName"></bean:write></td>
            </tr>
            <tr height="120">
                <td colspan="3">
                    <p>
                        <font size="4" face="黑体">科长意见：</font>
                    </p>
                </td>
                <td colspan="2">
                    <font size="4" face="黑体">副主任意见：</font>
                </td>
            </tr>
        </table>
    <p class=MsoNormal style='text-indent:49.0pt'><span style='font-size:14.0pt;
      font-family:宋体'>项目责任人：
                        <logic:iterate id="sheet" name="sheetList"><bean:write property="superintendentName" name="sheet"></bean:write>
                        </logic:iterate>
                        <logic:iterate id="xbPerson" name="xbPersonList"><bean:write property="userName" name="xbPerson"></bean:write></logic:iterate>
    </span><span lang=EN-US style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;</span><span style='font-size:14.0pt;font-family:宋体'>项目报告人：</span><span
            style='font-size:14.0pt;font-family:华文行楷'>：<bean:write name="svEmpName"></bean:write><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;</span></span><span lang=EN-US style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;</span><span
            style='font-size:14.0pt;font-family:宋体'>时间：</span><span lang=EN-US
                                                                    style='font-size:14.0pt'>
        <bean:write name="date"></bean:write></span></p>
        </div>
    </body>
</html>