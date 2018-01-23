<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/applus" prefix="applus" %>
<%@page import="com.anyi.gp.pub.SessionUtils"%>
<%
	String sCondition = "1=1";
	String svUserID = (String)SessionUtils.getAttribute(request,"svUserID");
	String conditionStr=" ZC_V_B_MERCHANDISE_SUPPLIER.ZC_SU_CODE= '"+svUserID+"'";
	String condidtionSearch=" ((ZC_V_B_MERCHANDISE_SUPPLIER.ZC_MER_NAME like ltrim('%{/ZC_MER_NAME.value/}%')) or (ZC_V_B_MERCHANDISE_SUPPLIER.ZC_MER_CODE like ltrim('%{/ZC_MER_CODE.value/}%') ) or (ZC_V_B_MERCHANDISE_SUPPLIER.ZC_BRA_NAME like ltrim('%{/ZC_BRA_NAME.value/}%'))) and   ZC_V_B_MERCHANDISE_SUPPLIER.ZC_SU_CODE= '"+svUserID+"'";
%>

<html>
<head>
    <title>供应商商品列表</title>
    <applus:include language="javascript">
        gp.page.TextBox;
        gp.page.TextAreaBox;
        gp.page.NumericBox;
        gp.page.PasswordBox;
        gp.page.LabelBox;
        gp.page.ComboBox;
        gp.page.DateBox;
        gp.page.DatetimeBox;
        gp.page.ForeignBox;

        gp.page.Grid;
        gp.page.DataGrid;
        gp.page.SelectGrid;
        gp.page.PaginationConsole;
        gp.page.Free;
        gp.page.FreeManager;
        gp.page.Tipbar;
        gp.page.Toolbar;
        gp.page.Tabstrip;
        gp.page.Search;
        gp.webpage.script.SearchPage;

        script.ZC.basedata.ZC_B_MERCHANDISE_SUP_List;
    </applus:include>
    <applus:init>
        setPageInit();
    </applus:init>
</head>
<body leftmargin=0 topmargin=0 class="clsPageBody">
<applus:pagedata pagetype="list" pagesize="100" condition="<%=conditionStr.toString()%>" />

<applus:blankrow height="5"></applus:blankrow>

<applus:toolbar id="toolbar">
    <call id="fadd" type="command" caption="新增" tip="" accesskey="N"/>
   
   
</applus:toolbar>

<span class="clsListPageTitle">供应商商品列表</span>
<table class="clsFreeTable">
    <tr class="clsFreeRow">
        <td nowrap width="1%"></td>
		<td nowrap align="left">
            <applus:textbox id="ZC_MER_NAME" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                            fieldname="ZC_MER_NAME"  isfromdb="false"  isvisible="false" groupid="search"
                            tabindex="3"
                            style="position:relative;border-style:solid;border-width:1 1 1 1;height:18px;width:60px;"
                            cssclass="clsEditPageEditBox"/>
        </td>
		<td nowrap align="left">
            <applus:textbox id="ZC_MER_CODE" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                            fieldname="ZC_MER_CODE"  isfromdb="false" isvisible="false" groupid="search"
                            tabindex="3"
                            style="position:relative;border-style:solid;border-width:1 1 1 1;height:18px;width:60px;"
                            cssclass="clsEditPageEditBox"/>
        </td>
		<td nowrap align="left">
            <applus:textbox id="ZC_BRA_NAME" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                            fieldname="ZC_BRA_NAME"  isfromdb="false" isvisible="false" groupid="search"
                            tabindex="3"
                            style="position:relative;border-style:solid;border-width:1 1 1 1;height:18px;width:60px;"
                            cssclass="clsEditPageEditBox"/>
        </td>
        <td nowrap align="right">
            <applus:search id="searchbar" tablename="ZC_V_B_MERCHANDISE_SUPPLIER" function="getlistpagedata" groupid="search"
                           isgroupbtnvisible="false"
                           pattern="<%=condidtionSearch.toString()%>"
                           tabindex="4" style="position:relative;"/>
        </td>
    </tr>
</table>
<applus:grid id="ZC_V_B_MERCHANDISE_SUPPLIER_Grid" type="Grid" pagesize="100" componame="ZC_MER_SUP"
             tablename="ZC_V_B_MERCHANDISE_SUPPLIER" cssclass="clsListPageGrid" isexistcheck="true">
    <applus:meta>
        <head></head>
        <fields><!--序号,商品名称,品牌代码,品牌名称,规格型号,采购价格,状态-->
            <field name="ZC_MER_CODE" caption="<applus:resource code='ZC_MER_CODE' />" editboxtype="TextBox" width="100"
                   align="center" isvisible="true" isallowinput="false" isreadonly="true" iszoomimage="false" 
                   ispopupimage="false"/>
            <field name="ZC_MER_NAME" caption="<applus:resource code='ZC_MER_NAME' />" editboxtype="TextBox" width="250"
                    isvisible="true" isallowinput="false" isreadonly="true" iszoomimage="false"
                   ispopupimage="false"/>
            <field name="ZC_BRA_NAME" caption="<applus:resource code='ZC_BRA_NAME' />" editboxtype="TextBox" width="80"
                    isvisible="true" isallowinput="false" isreadonly="true" iszoomimage="false"
                   ispopupimage="false"/>                   
            <field name="ZC_MER_SPEC" caption="<applus:resource code='ZC_MER_SPEC' />" editboxtype="TextBox" width="150"
                    isvisible="true" isallowinput="false" isreadonly="true" iszoomimage="false" align="left"
                   ispopupimage="false"/>                   
            <field name="ZC_MER_M_PRICE" caption="<applus:resource code='ZC_MER_M_PRICE' />" editboxtype="TextBox" width="100"  align="right" />
            <field name="ZC_MER_CG_PRICE" caption="<applus:resource code='ZC_MER_CG_PRICE' />" editboxtype="TextBox" width="150" align="right" />                   
            <field name="ZC_CATALOGUE_CODE" caption="<applus:resource code='ZC_CATALOGUE_CODE' />" editboxtype="TextBox"
                   width="100" align="" isvisible="true" isallowinput="false" isreadonly="true" iszoomimage="false"
                   ispopupimage="false"/>
            <field name="ZC_CATALOGUE_NAME" caption="<applus:resource code='ZC_CATALOGUE_NAME' />" editboxtype="TextBox"
                   width="150"  isvisible="true" isallowinput="false" isreadonly="true" iszoomimage="false"
                   ispopupimage="false"/>
            <field name="ZC_MD_TYPE" caption="<applus:resource code='ZC_MD_TYPE' />" editboxtype="TextBox" width="80"
                   isvisible="true" isallowinput="false" isreadonly="true" iszoomimage="false"
                   ispopupimage="false"/>
            <field name="ZC_YEAR" caption="<applus:resource code='ZC_YEAR' />" editboxtype="TextBox" width="80" align=""
                   isvisible="true" isallowinput="false" isreadonly="true" iszoomimage="false" ispopupimage="false"/>
            <field name="ZC_BRA_CODE" caption="<applus:resource code='ZC_BRA_CODE' />" editboxtype="TextBox" width="100"
                   isvisible="true" isallowinput="false" isreadonly="true" iszoomimage="false" ispopupimage="false"/>                   
        </fields>
    </applus:meta>
</applus:grid>
<applus:endpage/>
</body>
</html>
