<%@page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.EvalReportPrintForm"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcEbEvalReport"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html:html lang="true">
<head>
	<title>评审报告</title>
	<meta http-equiv=Content-Type content="text/html; charset=gb2312">
	<link href="../../css/stylesheet.css" type="text/css" rel="Stylesheet" />
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
	mso-font-charset: 1;
	mso-generic-font-family: roman;
	mso-font-format: other;
	mso-font-pitch: variable;
	mso-font-signature: 0 0 0 0 0 0;
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
	font-family: 仿宋_GB2312;
	panose-1: 2 1 6 9 3 1 1 1 1 1;
	mso-font-charset: 134;
	mso-generic-font-family: modern;
	mso-font-pitch: fixed;
	mso-font-signature: 1 135135232 16 0 262144 0;
}

@font-face {
	font-family: "\@宋体";
	panose-1: 2 1 6 0 3 1 1 1 1 1;
	mso-font-charset: 134;
	mso-generic-font-family: auto;
	mso-font-pitch: variable;
	mso-font-signature: 3 135135232 16 0 262145 0;
}

@font-face {
	font-family: "\@仿宋_GB2312";
	panose-1: 2 1 6 9 3 1 1 1 1 1;
	mso-font-charset: 134;
	mso-generic-font-family: modern;
	mso-font-pitch: fixed;
	mso-font-signature: 1 135135232 16 0 262144 0;
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

/* Page Definitions */
@page {
	mso-page-border-surround-header: no;
	mso-page-border-surround-footer: no;
	mso-footnote-separator: url("评审报告.files/header.htm") fs;
	mso-footnote-continuation-separator: url("评审报告.files/header.htm") fcs;
	mso-endnote-separator: url("评审报告.files/header.htm") es;
	mso-endnote-continuation-separator: url("评审报告.files/header.htm") ecs;
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
	<!--[if gte mso 10]>
<style>
 /* Style Definitions */
 table.MsoNormalTable
	{mso-style-name:普通表格;
	mso-tstyle-rowband-size:0;
	mso-tstyle-colband-size:0;
	mso-style-noshow:yes;
	mso-style-priority:99;
	mso-style-qformat:yes;
	mso-style-parent:"";
	mso-padding-alt:0cm 5.4pt 0cm 5.4pt;
	mso-para-margin:0cm;
	mso-para-margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	font-size:10.5pt;
	mso-bidi-font-size:11.0pt;
	font-family:"Calibri","sans-serif";
	mso-ascii-font-family:Calibri;
	mso-ascii-theme-font:minor-latin;
	mso-hansi-font-family:Calibri;
	mso-hansi-theme-font:minor-latin;
	mso-font-kerning:1.0pt;}
table.MsoTableGrid
	{mso-style-name:网格型;
	mso-tstyle-rowband-size:0;
	mso-tstyle-colband-size:0;
	mso-style-priority:59;
	mso-style-unhide:no;
	border:solid black 1.0pt;
	mso-border-themecolor:text1;
	mso-border-alt:solid black .5pt;
	mso-border-themecolor:text1;
	mso-padding-alt:0cm 5.4pt 0cm 5.4pt;
	mso-border-insideh:.5pt solid black;
	mso-border-insideh-themecolor:text1;
	mso-border-insidev:.5pt solid black;
	mso-border-insidev-themecolor:text1;
	mso-para-margin:0cm;
	mso-para-margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	font-size:10.5pt;
	mso-bidi-font-size:11.0pt;
	font-family:"Calibri","sans-serif";
	mso-ascii-font-family:Calibri;
	mso-ascii-theme-font:minor-latin;
	mso-fareast-font-family:"Times New Roman";
	mso-hansi-font-family:Calibri;
	mso-hansi-theme-font:minor-latin;
	mso-font-kerning:1.0pt;}
</style>
<![endif]-->
	<!--[if gte mso 9]><xml>
 <o:shapedefaults v:ext="edit" spidmax="7170"/>
</xml><![endif]-->
	<!--[if gte mso 9]><xml>
 <o:shapelayout v:ext="edit">
  <o:idmap v:ext="edit" data="2"/>
 </o:shapelayout></xml><![endif]-->
</head>
<body lang=ZH-CN
	style='tab-interval: 21.0pt; text-justify-trim: punctuation'>
	<div class=Section1 style='layout-grid: 15.6pt'>

		<p class=MsoNormal align=center style='text-align: center'>
			<b style='mso-bidi-font-weight: normal'><span
				style='font-size: 22.0pt; font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>评标结论</span>
			</b><b style='mso-bidi-font-weight: normal'><span lang=EN-US
				style='font-size: 22.0pt'><o:p></o:p>
			</span>
			</b>
		</p>

		<p class=MsoNormal>
			<span lang=EN-US><o:p>&nbsp;</o:p>
			</span>
		</p>

		<p class=MsoNormal>
			<span style='font-size: 14.0pt; font-family: 仿宋_GB2312'><b>项目名称：</b><bean:write name="evalReportPrintForm" property="zcEbEvalReport.projName" />
			</span>
			</span>
		</p>

		<p class=MsoNormal>
			<span style='font-size: 14.0pt; font-family: 仿宋_GB2312'><b>标段：</b><bean:write name="evalReportPrintForm" property="zcEbEvalReport.packName" />
			</span>
			</span>
		</p>

		<p class=MsoNormal>
			<span style='font-size: 14.0pt; font-family: 仿宋_GB2312'><b>招标方式：</b><bean:write name="evalReportPrintForm" property="zcEbEvalReport.purTypeVal" />
			</span>
			</span>
		</p>

		<p class=MsoNormal>
			<span style='font-size: 14.0pt; font-family: 仿宋_GB2312'><b>开标日期：</b><bean:write name="evalReportPrintForm" property="zcEbEvalReport.bidDate" format="yyyy年MM月dd日"/>
			</span>
			</span>
		</p>

		<p class=MsoNormal>
			<span style='font-size: 14.0pt; font-family: 仿宋_GB2312'><b>开标地点：</b><bean:write name="evalReportPrintForm" property="zcEbEvalReport.bidLocation" />
			</span>
			</span>
		</p>

		<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
			style='border-collapse: collapse; border: none; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; mso-yfti-tbllook: 1184; mso-padding-alt: 0cm 5.4pt 0cm 5.4pt'>
			<tr style='mso-yfti-irow: 0; mso-yfti-firstrow: yes'>
			    <td style='width: 40pt;border: solid black 1.0pt; mso-border-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>					
			        <p class=MsoNormal align=center style='text-align: center'>
						<span style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><b>排序</b><span
							lang=EN-US><o:p></o:p>
						</span>
						</span>
					</p>
				</td>
				<td width=158
					style='width: 115.8pt; border: solid black 1.0pt; mso-border-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><b>供应商名称</b><span
							lang=EN-US><o:p></o:p>
						</span>
						</span>
					</p>
				</td>
				<td width=132
					style='width: 105.25pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><b>投标总报价（元）</b><span
							lang=EN-US><o:p></o:p>
						</span>
						</span>
					</p>
				</td>
				<td width=113
					style='width: 3.0cm; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><b>符合性评审结果</b><span
							lang=EN-US><o:p></o:p>
						</span>
						</span>
					</p>
				</td>
				<td width=76
					style='width: 2.0cm; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><b>得分情况</b><span
							lang=EN-US><o:p></o:p>
						</span>
						</span>
					</p>
				</td>
				<td width=88
					style='width: 66.3pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-left: none; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><b>中标候选人</b><span
							lang=EN-US><o:p></o:p>
						</span>
						</span>
					</p>
				</td>
			</tr>
<logic:iterate id="result" name="evalReportPrintForm" property="packEvalResultList" type="com.ufgov.zc.common.zc.model.ZcEbPackEvalResult" scope="request" indexId="indexid">   	
			<tr style='mso-yfti-irow: 1; mso-yfti-lastrow: yes; height: 14.65pt'>
			    <td style='border: solid black 1.0pt; mso-border-themecolor: text1; border-top: none; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 14.65pt'>
			    	<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><%=(indexid.intValue()+1) %></o:p>
						</span>
					</p>
			    </td>
				<td width=158 valign=top
					style='width: 118.8pt; border: solid black 1.0pt; mso-border-themecolor: text1; border-top: none; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 14.65pt'>
					<p class=MsoNormal>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="result" property="providerName" /></o:p>
						</span>
					</p>
				</td>
				<td width=132 valign=top
					style='width: 99.25pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 14.65pt'>
					<p class=MsoNormal align=right style='text-align: right'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="result" property="providerTotalPrice"  format="#.00"/></o:p>
						</span>
					</p>
				</td>
				<td width=113 valign=top
					style='width: 3.0cm; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 14.65pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="result" property="complianceEvalResult" /></o:p>
						</span>
					</p>
				</td>
				<td width=76 valign=top
					style='width: 2.0cm; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 14.65pt'>
					<p class=MsoNormal align=right style='text-align: right'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="result" property="evalScore" format="#.00"/></o:p>
						</span>
					</p>
				</td>
				<td width=88 valign=top
					style='width: 66.3pt; border-top: none; border-left: none; border-bottom: solid black 1.0pt; mso-border-bottom-themecolor: text1; border-right: solid black 1.0pt; mso-border-right-themecolor: text1; mso-border-top-alt: solid black .5pt; mso-border-top-themecolor: text1; mso-border-left-alt: solid black .5pt; mso-border-left-themecolor: text1; mso-border-alt: solid black .5pt; mso-border-themecolor: text1; padding: 0cm 5.4pt 0cm 5.4pt; height: 14.65pt'>
					<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="result" property="evalResult" /></o:p>
						</span>
					</p>
				</td>
			</tr>
</logic:iterate>			
		</table>

		<p class=MsoNormal>
		   <span style='font-size: 14.0pt; font-family: 仿宋_GB2312'><b>专家评标组意见：</b><br></span>
			<span style='font-size: 12.0pt; font-family: 仿宋_GB2312'><span
				lang=EN-US><o:p><bean:write name="evalReportPrintForm" property="zcEbEvalReport.bidEvalOpinion" filter="false"/></o:p>
			</span>
			</span>
		</p>

		<p class=MsoNormal>
			<span lang=EN-US><o:p>&nbsp;</o:p>
			</span>
		</p>

		<p class=MsoNormal>
			<span style='font-size: 14.0pt; font-family: 仿宋_GB2312'><b>专家签字：</b><span
				lang=EN-US><o:p></o:p>
			</span>
			</span>
		</p>

		<p class=MsoNormal>
			<span lang=EN-US><o:p>&nbsp;</o:p>
			</span>
		</p>

		<p class=MsoNormal>
			<span lang=EN-US><o:p>&nbsp;</o:p>
			</span>
		</p>

		<p class=MsoNormal>
			<span lang=EN-US><o:p>&nbsp;</o:p>
			</span>
		</p>

		<p class=MsoNormal>
			<span lang=EN-US><o:p>&nbsp;</o:p>
			</span>
		</p>
	</div>
</body>
</html:html>