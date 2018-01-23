<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/applus" prefix="applus" %>
<%@page import="com.anyi.gp.pub.SessionUtils"%>
<%
String svUserID = (String)SessionUtils.getAttribute(request,"svUserID");
%>
<html>
<head>
    <title>商品折扣率</title>
    <applus:include language="javascript">
        gp.page.TextBox;
        gp.page.TextAreaBox;
        gp.page.NumericBox;
        gp.page.PasswordBox;
        gp.page.LabelBox;
        gp.page.ComboBox;
        gp.page.DateBox;
        gp.page.DatetimeBox;
        gp.page.FileBox;
        gp.page.ForeignBox;
        gp.page.ImageBox;
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
        script.ZC.ZCCommon;
        script.ZC.basedata.ZC_B_MERCHANDISE_SUP_Edit;
    </applus:include>
    <applus:init>
        setPageInit();
    </applus:init>
	<script src="jquery-1.4.4.min.js"></script>
</head>

<body leftmargin="0" topmargin="0" class="clsPageBody">
<applus:pagedata pagetype="edit"/>

<applus:toolbar id="toolbar">
    <call id="frpedit" type="command" caption="修改" accesskey="E"/>
    <call id="frpsave" type="command" caption="保存" accesskey="S"/>
    <call id="fexit" type="command" caption="退出" accesskey="Q"/>
</applus:toolbar>

<p class="clsEditPageTitle">
  商品折扣率
</p>

<table id="table1" class="clsFreeTable">
<tr class="clsFreeRow">
    <td align="right" class="clsKeyCaption" nowrap>
        <applus:resource code='ZC_MER_CODE' /><!-- 商品编号--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_MER_CODE" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                        fieldname="ZC_MER_CODE" isreadonly="false" style="width:220px;" cssclass="clsEditPageEditBox"
                        tabindex="1"/>
    </td>
    <td align="right" rowspan="12" class="clsNormCaption">
        <applus:resource code='ZC_MER_PIC'/>
    </td>
    <td nowrap align="left" rowspan="12" style="width:35%;">
        <applus:textbox id="ZC_MER_PIC_BLOBID" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER" fieldname="ZC_MER_PIC_BLOBID" isvisible="false"/>
        <applus:imagebox id="ZC_MER_PIC" componame="ZC_MER_SUP" isvisible="true" tablename="ZC_V_B_MERCHANDISE_SUPPLIER" fieldname="ZC_MER_PIC" isallowinput="false" isreadonly="true" isstretch="true" iszoom="true" zoomtype="scalebyheight" imgheight="200" style="width:85%;height:100%;" cssclass="clsEditPageEditBox"/>
    </td>
</tr>


<applus:textbox id="ZC_PROJ_STATUS" componame="ZC_MER_SUP"
                 tablename="ZC_V_B_MERCHANDISE_SUPPLIER" fieldname="ZC_PROJ_STATUS" isvisible="false"
                 isreadonly="true" isallowinput="true" isforcereadonly="true"
                 style="position:relative;width:150px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                 tabindex="10"/>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_YEAR' /><!-- 财政年度--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_YEAR" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER" fieldname="ZC_YEAR"
                        isforcereadonly="true" isreadonly="true" style="position:relative;width:220px;border-width:0 0 1 0;"
                        cssclass="clsEditPageEditBox" tabindex="4"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td align="right" class="clsNormCaption" nowrap>
        <applus:resource code='ZC_BRA_CODE' /><!-- 品牌代码--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_BRA_CODE" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                           fieldname="ZC_BRA_CODE" isreadonly="true"
                           style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                           tabindex="8"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td align="right" class="clsNormCaption" nowrap>
        品牌名称<span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_BRA_NAME" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                        fieldname="ZC_BRA_NAME" isforcereadonly="true" isreadonly="true"
                        style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                        tabindex="8"/>
    </td>
</tr>



<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_NAME' /><!-- 商品名称--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_MER_NAME" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                        fieldname="ZC_MER_NAME" isreadonly="true"
                        style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                        tabindex="4"/>
    </td>
</tr>


<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_STATUS' /><!-- 状态--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_MER_STATUS" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                         fieldname="ZC_MER_STATUS" isreadonly="true" isforcereadonly="true"
                         style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                         tabindex="4"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td align="right" class="clsNormCaption" nowrap>
        <applus:resource code='ZC_CATALOGUE_CODE' /><!-- 商品品目代码--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_CATALOGUE_CODE" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                           fieldname="ZC_CATALOGUE_CODE" isreadonly="true" isallowinput="false"
                           style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                           tabindex="7"/>
        <applus:textbox id="ZC_CATALOGUE_YEAR" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                           fieldname="ZC_CATALOGUE_YEAR" isreadonly="true" isallowinput="false" isvisible="false"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td align="right" class="clsNormCaption" nowrap>
        商品品目名称<span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_CATALOGUE_NAME" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                        fieldname="ZC_CATALOGUE_NAME" isreadonly="true" isallowinput="false"
                        style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                        tabindex="7"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_SPEC' /><!-- 型号规格--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_MER_SPEC" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                        fieldname="ZC_MER_SPEC" isreadonly="true"
                        style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                        tabindex="5"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_UNIT' /><!-- 计量单位-->
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_MER_UNIT" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                        fieldname="ZC_MER_UNIT" isreadonly="true"
                        style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                        tabindex="10"/>
    </td>
</tr>
<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MD_TYPE' /><!-- 商品类型-->
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_MD_TYPE" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                         fieldname="ZC_MD_TYPE" isreadonly="true" isallowinput="true"
                         style="position:relative;width:220px;border-width:0 0 1 0" cssclass="clsEditPageEditBox"
                         tabindex="11"/>
    </td>
</tr>
<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_TAX' /><!-- 媒体价格 -->
    </td>
    <td nowrap align="left">
        <applus:numericbox id="ZC_MER_TAX" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                           fieldname="ZC_MER_TAX" isreadonly="true"
                           style="position:relative;width:220px;border-width:0 0 1 0;text-align:right;" cssclass="clsEditPageEditBox"
                           tabindex="13"/>
    </td>
</tr>
<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_M_PRICE' /><!-- 最高限价 -->
    </td>
    <td nowrap align="left">
        <applus:numericbox id="ZC_MER_M_PRICE" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                           fieldname="ZC_MER_M_PRICE" isreadonly="true"
                           style="position:relative;width:220px;border-width:0 0 1 0;text-align:right;" cssclass="clsEditPageEditBox"
                           tabindex="12"/>
    </td>
</tr>
<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_IS_SHARED' /><!-- 是否外网共享商品-->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:combobox id="ZC_IS_SHARED" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                         fieldname="ZC_IS_SHARED" isreadonly="true"
                         style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                         tabindex="14"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_IS_ZZCX' /><!-- 是否自主创新产品-->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:combobox id="ZC_MER_IS_ZZCX" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                         fieldname="ZC_MER_IS_ZZCX" isreadonly="true"
                         style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                         tabindex="14"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_IS_LSHB' /><!-- 是否绿色环保产品-->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:combobox id="ZC_MER_IS_LSHB" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                         fieldname="ZC_MER_IS_LSHB" isreadonly="true"
                         style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                         tabindex="14"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_IS_JNJS' /><!-- 是否节能节水产品-->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:combobox id="ZC_IS_JNJS" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                         fieldname="ZC_IS_JNJS" isreadonly="true"
                         style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                         tabindex="14"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_COLLOCATE' /><!-- 详细配置-->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:textareabox id="ZC_MER_COLLOCATE" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                            fieldname="ZC_MER_COLLOCATE" isreadonly="true"
                            style="position:relative;height:40;width:95%;border-width:0 0 1 0;"
                            cssclass="clsEditPageEditBox" tabindex="14"></applus:textareabox>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_REMARK' /><!-- 备注 -->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:textareabox id="ZC_REMARK" componame="ZC_MER_SUP" tablename="ZC_V_B_MERCHANDISE_SUPPLIER"
                            fieldname="ZC_REMARK" isreadonly="true"
                            style="position:relative;height:30;width:95%;border-width:0 0 1 0;"
                            cssclass="clsEditPageEditBox" tabindex="15"></applus:textareabox>
    </td>
</tr>



</table>
<center>
    <applus:tabstrip id="test1" orientation="up" cssclass="clsPageTabstrip1" style="height:35%;width:88%;">
        <applus:tab id="price" caption="商品折扣率" style="padding:1;">
            <applus:grid id="ZC_B_MER_DISCOUNT_WEB_Grid" type="DataGrid" componame="ZC_MER_SUP" tablename="ZC_B_MER_DISCOUNT_WEB"
                         cssclass="clsPageGridInTabstrip">
                <applus:meta>
                    <head>
                    </head>
                    <fields>
						<field name="ZC_MER_CODE" caption="<applus:resource code='ZC_MER_CODE' />" editboxtype="TextBox" width="80" align="" isvisible="true" isallowinput="true" isreadonly="true"/>
                        <field name="ZC_SU_CODE" caption="<applus:resource code='ZC_SU_CODE' />" editboxtype="TextBox" width="80" align="" isvisible="true" isallowinput="true" isreadonly="false" />
						<field name="ZC_TREATY_LOWER_LIMIT" caption="协议数量下限" editboxtype="NumericBox" width="100" align=""  isvisible="true" isallowinput="true" isreadonly="false"/>
                        <field name="ZC_TREATY_UPPER_LIMIT" caption="协议数量上限" editboxtype="NumericBox"  width="100" align="" isvisible="true" isallowinput="true" isreadonly="false"/>

						<field name="ZC_TREATY_DISCOUNT_RATE" caption="协议折扣率" editboxtype="NumericBox" width="100" align="" isvisible="true" isallowinput="true" isreadonly="false"/>

						<field name="ZC_TREATY_PRICE" caption="协议价格" editboxtype="NumericBox" width="100" align="" 
						isvisible="true" isallowinput="true" isreadonly="true"/>
                    </fields>
                </applus:meta>
            </applus:grid>
        </applus:tab>
    </applus:tabstrip>
</center>
<applus:endpage/>
</body>
</html>
