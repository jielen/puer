<%@page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page contentType="text/HTML;charset=GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.EvalResultPrintForm"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html:html lang="true">

<%
Date  dt=  new  Date();  
int year = dt.getYear() + 1900;  
int month = dt.getMonth();
int date = dt.getDate();

String calendar = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date);
String filename = "attachment;filename=CGJH-" + calendar + ".doc";
response.setHeader("Content-disposition",filename);//Word
%>
 
<head>
	<meta http-equiv=Content-Type content="text/html; charset=GBK">
	<!-- <link href="../../css/stylesheet.css" type="text/css" rel="Stylesheet" /> -->
	<style>
<!-- /* Font Definitions */
@font-face {
	font-family: 宋体;
	panose-1: 2 1 6 0 3 1 1 1 1 1;
	mso-font-alt: SimSun;
	mso-font-charset: 134;
	mso-generic-font-family: auto;
	mso-font-pitch: variable;
	mso-font-signature: 3 135135232 16 0 262145 0;
}

@font-face {
	font-family: "Cambria Math";
	panose-1: 2 4 5 3 5 4 6 3 2 4;
	mso-font-charset: 0;
	mso-generic-font-family: roman;
	mso-font-pitch: variable;
	mso-font-signature: -1610611985 1107304683 0 0 159 0;
}

@font-face {
	font-family: Calibri;
	panose-1: 2 15 5 2 2 2 4 3 2 4;
	mso-font-charset: 0;
	mso-generic-font-family: swiss;
	mso-font-pitch: variable;
	mso-font-signature: -1610611985 1073750139 0 0 159 0;
}

@font-face {
	font-family: "\@宋体";
	panose-1: 2 1 6 0 3 1 1 1 1 1;
	mso-font-charset: 134;
	mso-generic-font-family: auto;
	mso-font-pitch: variable;
	mso-font-signature: 3 135135232 16 0 262145 0;
}

/* Style Definitions */
p.MsoNormal,li.MsoNormal,div.MsoNormal {
	mso-style-unhide: no;
	mso-style-qformat: yes;
	mso-style-parent: "";
	margin: 0cm;
	margin-bottom: .0001pt;
	text-align: justify;
	text-justify: inter-ideograph;
	mso-pagination: none;
	font-size: 10.5pt;
	mso-bidi-font-size: 11.0pt;
	font-family: "Calibri", "sans-serif";
	mso-ascii-font-family: Calibri;
	mso-ascii-theme-font: minor-latin;
	mso-fareast-font-family: 宋体;
	mso-fareast-theme-font: minor-fareast;
	mso-hansi-font-family: Calibri;
	mso-hansi-theme-font: minor-latin;
	mso-bidi-font-family: "Times New Roman";
	mso-bidi-theme-font: minor-bidi;
	mso-font-kerning: 1.0pt;
}

p.MsoHeader,li.MsoHeader,div.MsoHeader {
	mso-style-noshow: yes;
	mso-style-priority: 99;
	mso-style-link: "页眉 Char";
	margin: 0cm;
	margin-bottom: .0001pt;
	text-align: center;
	mso-pagination: none;
	tab-stops: center 207.65pt right 415.3pt;
	layout-grid-mode: char;
	border: none;
	mso-border-bottom-alt: solid windowtext .75pt;
	padding: 0cm;
	mso-padding-alt: 0cm 0cm 1.0pt 0cm;
	font-size: 9.0pt;
	font-family: "Calibri", "sans-serif";
	mso-ascii-font-family: Calibri;
	mso-ascii-theme-font: minor-latin;
	mso-fareast-font-family: 宋体;
	mso-fareast-theme-font: minor-fareast;
	mso-hansi-font-family: Calibri;
	mso-hansi-theme-font: minor-latin;
	mso-bidi-font-family: "Times New Roman";
	mso-bidi-theme-font: minor-bidi;
	mso-font-kerning: 1.0pt;
}

p.MsoFooter,li.MsoFooter,div.MsoFooter {
	mso-style-noshow: yes;
	mso-style-priority: 99;
	mso-style-link: "页脚 Char";
	margin: 0cm;
	margin-bottom: .0001pt;
	mso-pagination: none;
	tab-stops: center 207.65pt right 415.3pt;
	layout-grid-mode: char;
	font-size: 9.0pt;
	font-family: "Calibri", "sans-serif";
	mso-ascii-font-family: Calibri;
	mso-ascii-theme-font: minor-latin;
	mso-fareast-font-family: 宋体;
	mso-fareast-theme-font: minor-fareast;
	mso-hansi-font-family: Calibri;
	mso-hansi-theme-font: minor-latin;
	mso-bidi-font-family: "Times New Roman";
	mso-bidi-theme-font: minor-bidi;
	mso-font-kerning: 1.0pt;
}

span.Char {
	mso-style-name: "页眉 Char";
	mso-style-noshow: yes;
	mso-style-priority: 99;
	mso-style-unhide: no;
	mso-style-locked: yes;
	mso-style-link: 页眉;
	mso-ansi-font-size: 9.0pt;
	mso-bidi-font-size: 9.0pt;
}

span.Char0 {
	mso-style-name: "页脚 Char";
	mso-style-noshow: yes;
	mso-style-priority: 99;
	mso-style-unhide: no;
	mso-style-locked: yes;
	mso-style-link: 页脚;
	mso-ansi-font-size: 9.0pt;
	mso-bidi-font-size: 9.0pt;
}

.MsoChpDefault {
	mso-style-type: export-only;
	mso-default-props: yes;
	mso-bidi-font-family: "Times New Roman";
	mso-bidi-theme-font: minor-bidi;
}

@page Section1 {
	size: 595.3pt 841.9pt;
	margin: 72.0pt 90.0pt 72.0pt 90.0pt;
	mso-header-margin: 42.55pt;
	mso-footer-margin: 49.6pt;
	mso-paper-source: 0;
	layout-grid: 15.6pt;
}

div.Section1 {
	page: Section1;
}
-->
</style>

</head>

<body lang=ZH-CN
	style='tab-interval: 21.0pt; text-justify-trim: punctuation'>
	<div align="center">
		<table border=0 cellpadding=0 cellspacing=0>
			<tr height=38 style='mso-height-source: userset; height: 28.5pt'>
				<td height=38 class=xl74 width="100%" colspan="100"
					style='height: 28.5pt; width: 100%'>
					西安市市级单位政府采购项目实施申请审批表
				</td>
			</tr>
		</table>
	</div>
	<div class=Section1 style='layout-grid: 15.6pt' align="center">
<logic:iterate id="make" name="zcProMakePrintForm" property="zcProMakeList" type="com.ufgov.zc.common.zc.model.ZcPProMake" scope="request">   	
		<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
			style='border-collapse: collapse; border: none; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; mso-yfti-tbllook: 1184; mso-padding-alt: 0cm 5.4pt 0cm 5.4pt'>
		
		<tr style='mso-yfti-irow: 0; mso-yfti-firstrow: yes'>
			<td width=126 colspan=3 style='width: 94.65pt; border: solid black 1.0pt; mso-border-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
				<p class=MsoNormal align=center style='text-align: center'>
					<span
						style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>项目名称</span>
				</p>
			</td>
			<td width=126 colspan=8 style='width: 142.05pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
		        <p class=MsoNormal align=center style='text-align: center'>
					<span lang=EN-US><o:p><bean:write name="make" property="zcMakeName" /></o:p>
					</span>
				</p>
			</td>
			
			</tr>
			
			
			<tr style='mso-yfti-irow: 0; mso-yfti-firstrow: yes'>
				<td width=126 colspan=3
					style='width: 94.65pt; border: solid black 1.0pt; mso-border-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>采购单位</span>
					</p>
				</td>
				<td width=189 colspan=3
					style='width: 142.05pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US><o:p><bean:write name="zcProMakePrintForm" property="co_name" /></o:p>
						</span>
					</p>
				</td>
				<td width=63 colspan=2
					style='width: 47.35pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>联系人</span>
					</p>
				</td>
				<td width=73
					style='width: 54.5pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US><o:p><bean:write name="make" property="zcMakeLinkman" /></o:p>
						</span>
					</p>
				</td>
				<td width=54
					style='width: 45.2pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>联系电话</span>
					</p>
				</td>
				<td width=63
					style='width: 47.35pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US><o:p><bean:write name="make" property="zcMakeTel" /></o:p>
						</span>
					</p>
				</td>
			</tr>
		<tr style='mso-yfti-irow: 0; mso-yfti-firstrow: yes'>
			<td width=126 colspan=3 style='width: 94.65pt; border: solid black 1.0pt; mso-border-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
				<p class=MsoNormal align=center style='text-align: center'>
					<span
						style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>采购组织形式</span>
				</p>
			</td>
			<td width=126 colspan=3 style='width: 142.05pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
		        <p class=MsoNormal align=center style='text-align: center'>
					<span lang=EN-US><o:p><bean:write name="make" property="zcMakeSequence" /></o:p>
					</span>
				</p>
			</td>
			<td width=63 colspan=3  style='width: 100.35pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
		        <p class=MsoNormal align=center style='text-align: center'>
					<span
						style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>批复采购方式</span>
				</p>
			</td>
			<td width=63 colspan=2 
					style='width: 47.35pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
				<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US><o:p><bean:write name="make" property="zcPifuCgfs" /></o:p>
						</span>
				</p>
			</td>
			</tr>
			<tr style='mso-yfti-irow: 1; height: 75.6pt'>
				<td width=28 rowspan=<bean:write name="zcProMakePrintForm" property="itemTotalCount" />
					style='width: 21.3pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-top: none; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 75.6pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>采购单位申报意见</span>
					</p>
				</td>
				<td width=540 colspan=10 valign=top
					style='width: 404.8pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 75.6pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>市财政局：</span>
					</p>
					<p class=MsoNormal align=left
						style='text-align: left; text-indent: 20.25pt'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>经你局</span><span
							lang=EN-US><bean:write name="make" property="zcMakeCode" /></span><span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>批准同意我单位采购下列项目，核定采购限额</span><span
							lang=EN-US><bean:write name="make" property="zcMoneyBiSum" /></span><span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>元（其中预算外及自筹</span><span
							lang=EN-US>       </span><span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>元），现将有关采购方案，详细技术参数及要求一同附上，我单位需在</span><span
							lang=EN-US>  月        日</span><span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>前完成此项工作，请按市政府采购有关规定尽快实施采购。</span>
					</p>
					<p class=MsoNormal align=left
						style='text-align: left; text-indent: 20.25pt'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
					<p class=MsoNormal align=left
						style='text-align: left; text-indent: 20.25pt'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
					<p class=MsoNormal align=left
						style='text-align: left; text-indent: 20.25pt'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
					<p class=MsoNormal align=left
						style='text-align: left; text-indent: 20.25pt'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
					<p class=MsoNormal align=left
						style='text-align: left; text-indent: 20.25pt'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
					<p class=MsoNormal align=left
						style='text-align: left; text-indent: 20.25pt'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
					<p class=MsoNormal align=left
						style='text-align: left; text-indent: 20.25pt'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 2'>
				<td width=45
					style='width: 33.75pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>序号</span>
					</p>
				</td>
				<td width=76 colspan=2
					style='width: 2.0cm; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>项目明细</span>
					</p>
				</td>
				<td width=47
					style='width: 35.4pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>品目名称</span>
					</p>
				</td>
				<td width=119
					style='width: 89.55pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>规格技术参数</span>
					</p>
				</td>
				<td width=51
					style='width: 38.05pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>单位</span>
					</p>
				</td>
				<td width=85 colspan=2
					style='width: 63.8pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>市场参考价</span>
					</p>
				</td>
				<td width=54
					style='width: 40.2pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>数量</span>
					</p>
				</td>
				<td width=63
					style='width: 47.35pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>金额</span>
					</p>
				</td>
			</tr>
<logic:iterate id="item" name="zcProMakePrintForm" property="itemList" type="com.ufgov.zc.common.zc.model.ZcPProMitem" scope="request" indexId="indexid">   				
			<tr style='mso-yfti-irow: 3; height: 17.25pt'>
				<td width=45 valign=top
					style='width: 33.75pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 17.25pt'>
					<p class=MsoNormal align=center style='text-align: left'>
						<span lang=EN-US><o:p><%=(indexid.intValue()+1) %></o:p>
						</span>
					</p>
				</td>
				<td width=76 colspan=2 valign=top
					style='width: 2.0cm; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 17.25pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p><bean:write name="item" property="zcPitemName" /></o:p>
						</span>
					</p>
				</td>
				<td width=47 valign=top
					style='width: 35.4pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 17.25pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p><bean:write name="item" property="zcCatalogueName" /></o:p>
						</span>
					</p>
				</td>
				<td width=119 valign=top
					style='width: 89.55pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 17.25pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p><bean:write name="item" property="zcBaseGgyq" /></o:p>
						</span>
					</p>
				</td>
				<td width=51 valign=top
					style='width: 38.05pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 17.25pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p><bean:write name="item" property="zcMerUnit" /></o:p>
						</span>
					</p>
				</td>
				<td width=85 colspan=2 valign=top
					style='width: 63.8pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 17.25pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p><bean:write name="item" property="zcMerPrice" /></o:p>
						</span>
					</p>
				</td>
				<td width=54 valign=top
					style='width: 40.2pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 17.25pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p><bean:write name="item" property="zcCaigNum" /></o:p>
						</span>
					</p>
				</td>
				<td width=63 valign=top
					style='width: 47.35pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 17.25pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p><bean:write name="item" property="zcItemSum" /></o:p>
						</span>
					</p>
				</td>
			</tr>
</logic:iterate>			
			<tr style='mso-yfti-irow: 4; height: 13.95pt'>
				<td width=45 valign=top
					style='width: 33.75pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 13.95pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>合计</span>
					</p>
				</td>
				<td width=76 colspan=2 valign=top
					style='width: 2.0cm; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 13.95pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
				</td>
				<td width=47 valign=top
					style='width: 35.4pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 13.95pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
				</td>
				<td width=119 valign=top
					style='width: 89.55pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 13.95pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
				</td>
				<td width=51 valign=top
					style='width: 38.05pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 13.95pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
				</td>
				<td width=85 colspan=2 valign=top
					style='width: 63.8pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 13.95pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p><bean:write name="zcProMakePrintForm" property="totalMerSum" /></o:p>
						</span>
					</p>
				</td>
				<td width=54 valign=top
					style='width: 40.2pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 13.95pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p><bean:write name="zcProMakePrintForm" property="itemTotalCountCaig" /></o:p>
						</span>
					</p>
				</td>
				<td width=63 valign=top
					style='width: 47.35pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 13.95pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><o:p><bean:write name="zcProMakePrintForm" property="totalItemSum" /></o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 6; mso-yfti-lastrow: yes'>
				<td width=568 colspan=11 valign=top
					style='width: 426.1pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-top: none; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left'>
						<span lang=EN-US><span style='mso-spacerun: yes'>&nbsp;</span>
						</span>					
					</p>
					<p class=MsoNormal align=left style='text-align: left'>				    
						<span lang=EN-US><o:p><bean:write name="zcProMakePrintForm" property="description" filter="false"/></o:p>
						</span>
						<span
							style='font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>市财政局政府采购管理处意见：</span>
					</p>
				</td>
			</tr>
			<![if !supportMisalignedColumns]>
			<tr height=0>
				<td width=28 style='border: none'></td>
				<td width=45 style='border: none'></td>
				<td width=53 style='border: none'></td>
				<td width=23 style='border: none'></td>
				<td width=47 style='border: none'></td>
				<td width=119 style='border: none'></td>
				<td width=51 style='border: none'></td>
				<td width=12 style='border: none'></td>
				<td width=73 style='border: none'></td>
				<td width=54 style='border: none'></td>
				<td width=63 style='border: none'></td>
			</tr>
			<![endif]>
		</table>
</logic:iterate>
		<p class=MsoNormal align=left style='text-align: left'>
			<span lang=EN-US><o:p>&nbsp;</o:p>
			</span>
		</p>

	</div>

</body>
</html:html>