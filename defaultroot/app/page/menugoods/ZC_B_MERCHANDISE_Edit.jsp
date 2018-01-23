<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/applus" prefix="applus" %>
<html>
<head>
    <title><applus:resource code='ZC_B_MER_PRICE'/></title>
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

        script.ZC.basedata.ZC_B_MERCHANDISE_Edit;
    </applus:include>
    <applus:init>
        setPageInit();
    </applus:init>
</head>

<body leftmargin="0" topmargin="0" class="clsPageBody">
<applus:pagedata pagetype="edit"/>

<applus:toolbar id="toolbar">
    <call id="fadd" type="command" caption="新增" accesskey="N"/>
    <call id="fedit" type="command" caption="修改" accesskey="E"/>
    <call id="fsave" type="command" caption="保存" accesskey="S"/>
    <call id="fdelete" type="command" caption="删除" accesskey="D"/>
    <call id="fstart" type="command" caption="启用" tip="" accesskey="T"/>
    <call id="fstop" type="command" caption="停用" tip=""/>
    <call id="fcanceled" type="command" caption="作废" tip="" accesskey="C"/>
    <call id="fprn_tpl_set" type="command" caption="打印设置" accesskey="L"/>
    <call id="fprint" type="command" caption="打印" accesskey="P"/>
    <call id="fexit" type="command" caption="退出" accesskey="Q"/>
</applus:toolbar>

<p class="clsEditPageTitle">
    <applus:resource code='ZC_B_MER_PRICE'/>
</p>

<table id="table1" class="clsFreeTable">
<tr class="clsFreeRow">
    <td align="right" class="clsKeyCaption" nowrap>
        <applus:resource code='ZC_MER_CODE' /><!-- 商品编号--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_MER_CODE" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                        fieldname="ZC_MER_CODE" isreadonly="false" style="width:220px;" cssclass="clsEditPageEditBox"
                        tabindex="1"/>
    </td>
    <%-- --%>
    <td align="right" rowspan="12" class="clsNormCaption">
        <applus:resource code='ZC_MER_PIC'/>
    </td>
    <td nowrap align="left" rowspan="12" style="width:35%;">
        <applus:imagebox id="ZC_MER_PIC" componame="ZC_B_MERCHANDISE"
                         tablename="ZC_B_MERCHANDISE" fieldname="ZC_MER_PIC"
                         isreadonly="false"
                         zoomtype="scalebyheight"
                         imgheight="200"
                         isstretch="true" style="width:85%; height:100%;border-width:1 1 1 1;"
                         cssclass="clsEditPageEditBox"/>
    </td>

</tr>

<tr class="clsFreeRow">
    <td align="right" class="clsNormCaption" nowrap>
        <applus:resource code='ZC_DIYU_DAIMA' /><!-- 地域代码--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:foreignbox id="ZC_DIYU_DAIMA" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                           fieldname="ZC_DIYU_DAIMA" isreadonly="false"
                           style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                           tabindex="8"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td align="right" class="clsNormCaption" nowrap>
        <applus:resource code='ZC_DIYU_NAME' /><!-- 地域名称--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_DIYU_NAME" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                        fieldname="ZC_DIYU_NAME" isforcereadonly="true"
                        style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                        tabindex="8"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_PROJ_ID' /><!-- 货物批次代码--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:foreignbox id="ZC_PROJ_ID" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                           fieldname="ZC_PROJ_ID" isreadonly="false"
                           style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                           tabindex="4"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_PROJ_NA' /><!-- 货物批次名称--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_PROJ_NA" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE" fieldname="ZC_PROJ_NA"
                        isforcereadonly="true" style="position:relative;width:220px;border-width:0 0 1 0;"
                        cssclass="clsEditPageEditBox" tabindex="4"/>
    </td>
</tr>

<applus:combobox id="ZC_PROJ_STATUS" componame="ZC_B_MERCHANDISE"
                 tablename="ZC_B_MERCHANDISE" fieldname="ZC_PROJ_STATUS" isvisible="false"
                 isreadonly="true" isallowinput="true" isforcereadonly="true"
                 style="position:relative;width:150px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                 tabindex="10"/>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_YEAR' /><!-- 财政年度--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_YEAR" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE" fieldname="ZC_YEAR"
                        isforcereadonly="true" style="position:relative;width:220px;border-width:0 0 1 0;"
                        cssclass="clsEditPageEditBox" tabindex="4"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td align="right" class="clsNormCaption" nowrap>
        <applus:resource code='ZC_BRA_CODE' /><!-- 品牌代码--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:foreignbox id="ZC_BRA_CODE" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                           fieldname="ZC_BRA_CODE" isreadonly="false"
                           style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                           tabindex="8"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td align="right" class="clsNormCaption" nowrap>
        品牌名称<span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_BRA_NAME" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                        fieldname="ZC_BRA_NAME" isforcereadonly="true"
                        style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                        tabindex="8"/>
    </td>
</tr>


<applus:combobox id="ZC_ZBPP_STATUS" componame="ZC_B_MERCHANDISE"
                 tablename="ZC_B_MERCHANDISE" fieldname="ZC_ZBPP_STATUS" isvisible="false"
                 isreadonly="true" isallowinput="true" isforcereadonly="true"
                 style="position:relative;width:150px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                 tabindex="10"/>


<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_CG_LEIXING'/><%-- 采购类型 --%>
    </td>
    <td align="left" nowrap>
        <applus:combobox id="ZC_CG_LEIXING" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                         fieldname="ZC_CG_LEIXING" isforcereadonly="false"
                         style="position:relative;width:150px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_NAME' /><!-- 商品名称--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_MER_NAME" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                        fieldname="ZC_MER_NAME" isreadonly="false"
                        style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                        tabindex="4"/>
    </td>
</tr>
<%--
            <tr class="clsFreeRow">
                <td nowrap align="right" class="clsNormCaption">
                    <applus:resource code='ZC_SUP_MER_CODE' /><span class="clsPageAsterisk">*</span>
                </td>
                <td nowrap align="left">
                    <applus:foreignbox id="ZC_SUP_MER_CODE" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE" fieldname="ZC_SUP_MER_CODE" isreadonly="false" style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox" tabindex="4"/>
                </td>
            </tr>
            --%>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_STATUS' /><!-- 状态--><span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:combobox id="ZC_MER_STATUS" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
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
        <applus:foreignbox id="ZC_CATALOGUE_CODE" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                           fieldname="ZC_CATALOGUE_CODE" isreadonly="true" isallowinput="false"
                           style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                           tabindex="7"/>
        <applus:textbox id="ZC_CATALOGUE_YEAR" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                           fieldname="ZC_CATALOGUE_YEAR" isreadonly="true" isallowinput="false" isvisible="false"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td align="right" class="clsNormCaption" nowrap>
        商品品目名称<span class="clsPageAsterisk">*</span>
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_CATALOGUE_NAME" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
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
        <applus:textbox id="ZC_MER_SPEC" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                        fieldname="ZC_MER_SPEC" isreadonly="false"
                        style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                        tabindex="5"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_UNIT' /><!-- 计量单位-->
    </td>
    <td nowrap align="left">
        <applus:textbox id="ZC_MER_UNIT" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                        fieldname="ZC_MER_UNIT" isreadonly="false"
                        style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                        tabindex="10"/>
    </td>
</tr>
<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MD_TYPE' /><!-- 商品类型-->
    </td>
    <td nowrap align="left">
        <applus:combobox id="ZC_MD_TYPE" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                         fieldname="ZC_MD_TYPE" isreadonly="false" isallowinput="true"
                         style="position:relative;width:220px;border-width:0 0 1 0" cssclass="clsEditPageEditBox"
                         tabindex="11"/>
    </td>
</tr>
<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_M_PRICE' /><!-- 市场单价-->
    </td>
    <td nowrap align="left">
        <applus:numericbox id="ZC_MER_M_PRICE" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                           fieldname="ZC_MER_M_PRICE" isreadonly="false"
                           style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                           tabindex="12"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_TAX' /><!-- 购置税-->
    </td>
    <td nowrap align="left">
        <applus:numericbox id="ZC_MER_TAX" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                           fieldname="ZC_MER_TAX" isreadonly="false"
                           style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                           tabindex="13"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_IS_SHARED' /><!-- 是否外网共享商品-->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:combobox id="ZC_IS_SHARED" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                         fieldname="ZC_IS_SHARED" isreadonly="false"
                         style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                         tabindex="14"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_IS_ZZCX' /><!-- 是否自主创新产品-->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:combobox id="ZC_MER_IS_ZZCX" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                         fieldname="ZC_MER_IS_ZZCX" isreadonly="false"
                         style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                         tabindex="14"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_IS_LSHB' /><!-- 是否绿色环保产品-->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:combobox id="ZC_MER_IS_LSHB" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                         fieldname="ZC_MER_IS_LSHB" isreadonly="false"
                         style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                         tabindex="14"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_IS_JNJS' /><!-- 是否节能节水产品-->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:combobox id="ZC_IS_JNJS" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                         fieldname="ZC_IS_JNJS" isreadonly="false"
                         style="position:relative;width:220px;border-width:0 0 1 0;" cssclass="clsEditPageEditBox"
                         tabindex="14"/>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_MER_COLLOCATE' /><!-- 详细配置-->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:textareabox id="ZC_MER_COLLOCATE" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                            fieldname="ZC_MER_COLLOCATE" isreadonly="false"
                            style="position:relative;height:40;width:95%;border-width:0 0 1 0;"
                            cssclass="clsEditPageEditBox" tabindex="14"></applus:textareabox>
    </td>
</tr>

<tr class="clsFreeRow">
    <td nowrap align="right" class="clsNormCaption">
        <applus:resource code='ZC_REMARK' /><!-- 备注 -->
    </td>
    <td nowrap align="left" colspan="3">
        <applus:textareabox id="ZC_REMARK" componame="ZC_B_MERCHANDISE" tablename="ZC_B_MERCHANDISE"
                            fieldname="ZC_REMARK" isreadonly="false"
                            style="position:relative;height:30;width:95%;border-width:0 0 1 0;"
                            cssclass="clsEditPageEditBox" tabindex="15"></applus:textareabox>
    </td>
</tr>
</table>
<center>
    <applus:tabstrip id="test1" orientation="up" cssclass="clsPageTabstrip1" style="height:25%;width:88%;">
        <applus:tab id="price" caption="商品供应商和价格" style="padding:1;">
            <applus:grid id="ZC_B_MER_PRICE_Grid" type="DataGrid"
                         componame="ZC_B_MERCHANDISE" tablename="ZC_B_MER_PRICE"
                         cssclass="clsPageGridInTabstrip">
                <applus:meta>
                    <head>
                    </head>
                    <fields>
                        <field name="ZC_SU_CODE" caption="<applus:resource code='ZC_SU_CODE' />"
                               editboxtype="ForeignBox" width="150" align=""
                               isvisible="true" isallowinput="true" isreadonly="false"/>
                        <field name="ZC_SU_NAME" caption="<applus:resource code='ZC_SU_NAME' />" editboxtype="TextBox"
                               width="150" align=""
                               isvisible="true" isallowinput="false" isreadonly="false"/>
                        <field name="ZC_DIYU_DAIMA" caption="<applus:resource code='ZC_DIYU_DAIMA' />"
                               editboxtype="ForeignBox" width="150" align=""
                               isvisible="true" isallowinput="true" isreadonly="false"/>
                        <field name="ZC_MER_CG_PRICE" caption="<applus:resource code='ZC_MER_CG_PRICE' />"
                               editboxtype="NumericBox" width="150" align=""
                               isvisible="true" isallowinput="true" isreadonly="false"/>
                        <field name="ZC_DIS_XIANE1" caption="<applus:resource code='ZC_DIS_XIANE1' />"
                               editboxtype="NumericBox" width="150" align=""
                               isvisible="true" isallowinput="true" isreadonly="false"/>
							   <!-- 修改部分
					    <field name="ZC_DIS_RATE" caption="<applus:resource code='ZC_DIS_RATE' />"
                               editboxtype="NumericBox" width="150" align=""
                               isvisible="true" isallowinput="true" isreadonly="false"/>
						<field name="ZC_DIS_NUMBER" caption="<applus:resource code='ZC_DIS_NUMBER' />"
                               editboxtype="NumericBox" width="150" align=""
                               isvisible="true" isallowinput="true" isreadonly="false"/>
							    -->
                        <field name="ZC_DIS_RATE1" caption="<applus:resource code='ZC_DIS_RATE1' />"
                               editboxtype="NumericBox" width="150" align=""
                               isvisible="true" isallowinput="true" isreadonly="false"/>
                        <field name="ZC_DIS_XIANE2" caption="<applus:resource code='ZC_DIS_XIANE2' />"
                               editboxtype="NumericBox" width="150" align=""
                               isvisible="false" isallowinput="true" isreadonly="false"/>
                        <field name="ZC_DIS_RATE2" caption="<applus:resource code='ZC_DIS_RATE2' />"
                               editboxtype="NumericBox" width="150" align=""
                               isvisible="false" isallowinput="true" isreadonly="false"/>
                        <field name="ZC_DIS_XIANE3" caption="<applus:resource code='ZC_DIS_XIANE3' />"
                               editboxtype="NumericBox" width="150" align=""
                               isvisible="false" isallowinput="true" isreadonly="false"/>
                        <field name="ZC_DIS_RATE3" caption="<applus:resource code='ZC_DIS_RATE3' />"
                               editboxtype="NumericBox" width="150" align=""
                               isvisible="false" isallowinput="true" isreadonly="false"/>
                        <field name="ZC_DIS_XIANE4" caption="<applus:resource code='ZC_DIS_XIANE4' />"
                               editboxtype="NumericBox" width="150" align=""
                               isvisible="false" isallowinput="true" isreadonly="false"/>
                        <field name="ZC_DIS_RATE4" caption="<applus:resource code='ZC_DIS_RATE4' />"
                               editboxtype="NumericBox" width="150" align=""
                               isvisible="false" isallowinput="true" isreadonly="false"/>
                    </fields>
                </applus:meta>
            </applus:grid>
        </applus:tab>
    </applus:tabstrip>
</center>
<applus:endpage/>
</body>
</html>
