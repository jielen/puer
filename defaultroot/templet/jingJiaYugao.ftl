<html>

<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=Generator content="Microsoft Word 12 (filtered)">
<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:宋体;
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:"Cambria Math";
	panose-1:2 4 5 3 5 4 6 3 2 4;}
@font-face
	{font-family:Calibri;
	panose-1:2 15 5 2 2 2 4 3 2 4;}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Calibri","sans-serif";}
p.MsoHeader, li.MsoHeader, div.MsoHeader
	{mso-style-link:"页眉 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:center;
	layout-grid-mode:char;
	border:none;
	padding:0cm;
	font-size:9.0pt;
	font-family:"Calibri","sans-serif";}
p.MsoFooter, li.MsoFooter, div.MsoFooter
	{mso-style-link:"页脚 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	layout-grid-mode:char;
	font-size:9.0pt;
	font-family:"Calibri","sans-serif";}
p
	{margin-right:0cm;
	margin-left:0cm;
	font-size:12.0pt;
	font-family:宋体;}
span.Char
	{mso-style-name:"页眉 Char";
	mso-style-link:页眉;}
span.Char0
	{mso-style-name:"页脚 Char";
	mso-style-link:页脚;}
 /* Page Definitions */
 @page Section1
	{size:595.3pt 841.9pt;
	margin:72.0pt 90.0pt 72.0pt 90.0pt;
	layout-grid:15.6pt;}
div.Section1
	{page:Section1;}
-->
</style>
<script>
     function confirmBaoJia(){
       if (confirm("对于本次竞价，您已确保有现货，并且不能恶意报价，否则承担相关一切法律责任，同意请继续。")){
          window.location.href="../../../../acceptXieYi.jsp";
        }else{
		  window.close();
		}	 
	 }

</script>
</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>

<div class=Section1 style='layout-grid:15.6pt'>

<p class=MsoNormal style='line-height:150%'><b><span style='font-size:12.0pt;line-height:150%;font-family:宋体'>公告发布日期：</span></b><span lang=EN-US style='font-size:12.0pt;line-height:150%'>${make.zcAttr5}</span></p>

<p class=MsoNormal style='line-height:150%'><b><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>项目名称：</span></b><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>${make.zcMakeName}</span></p>

<p class=MsoNormal style='text-indent:24.0pt;line-height:150%'><span
style='font-size:12.0pt;line-height:150%;font-family:宋体'>西安市市级单位政府采购中心现就“${make.zcMakeName}”邀请合格的协议供应商进行电子竞价。</span></p>

<p class=MsoNormal style='line-height:150%'><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>　　一、竞价标的物</span></p>

<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
 style='border-collapse:collapse;border:none'>
 <tr>
   <td width=83 style='width:62.1pt;border:solid black 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:150%'><b><span
  style='line-height:150%;font-family:宋体'>品牌</span></b></p>
  </td>
  <td width=83 style='width:62.1pt;border:solid black 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:150%'><b><span
  style='line-height:150%;font-family:宋体'>商品名称</span></b></p>
  </td>
  <td width=284 style='width:212.65pt;border:solid black 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:150%'><b><span
  style='line-height:150%;font-family:宋体'>基本规格参数</span></b></p>
  </td>
  <td width=60 style='width:44.8pt;border:solid black 1.0pt;border-left:none;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:150%'><b><span
  style='line-height:150%;font-family:宋体'>数量</span></b></p>
  </td>
 </tr>
 <#list make.itemList as item>
    <#list item.merList as mer>
 <tr>
   <td width=83 valign=top style='width:62.1pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal style='line-height:150%'><span lang=EN-US
  style='font-size:12.0pt;line-height:150%'>${item.zcBraName}</span></p>
  </td>
  <td width=83 valign=top style='width:62.1pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal style='line-height:150%'><span lang=EN-US
  style='font-size:12.0pt;line-height:150%'>${mer.zcMerName}</span></p>
  </td>
  <td width=284 valign=top style='width:212.65pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal style='line-height:150%'><span lang=EN-US
  style='font-size:12.0pt;line-height:150%'>${mer.zcBaseGgyq}</span></p>
  </td>
  <td width=60 valign=top style='width:44.8pt;border-top:none;border-left:none;
  border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal style='line-height:150%'><span lang=EN-US
  style='font-size:12.0pt;line-height:150%'>${mer.zcCaigNum}</span></p>
  </td>
 </tr>
  </#list>
 </#list>
</table>

<p class=MsoNormal style='line-height:150%'><span lang=EN-US style='font-size:
12.0pt;line-height:150%'>&nbsp;</span></p>

<p class=MsoNormal style='line-height:150%'><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>　　二、竞价时间</span></p>

<p class=MsoNormal style='line-height:150%'><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>　　</span><span lang=EN-US style='font-size:
12.0pt;line-height:150%'>1.</span><span style='font-size:12.0pt;line-height:
150%;font-family:宋体'>竞价开始时间：</span><span lang=EN-US style='font-size:12.0pt;
line-height:150%'>${make.zcAttr5}</span></p>

<p class=MsoNormal style='text-indent:23.25pt;line-height:150%'><span
lang=EN-US style='font-size:12.0pt;line-height:150%'>2.</span><span
style='font-size:12.0pt;line-height:150%;font-family:宋体'>竞价结束时间：</span><span
lang=EN-US style='font-size:12.0pt;line-height:150%'>${make.zcAttr4}</span></p>

<p class=MsoNormal style='line-height:150%'><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>　　三、竞价规则</span></p>

<p class=MsoNormal style='line-height:150%'><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>　　</span><span lang=EN-US style='font-size:
12.0pt;line-height:150%'>1.</span><span style='font-size:12.0pt;line-height:
150%;font-family:宋体'>通过注册的供应商为合格供应商；</span></p>

<p class=MsoNormal style='line-height:150%'><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>　　</span><span lang=EN-US style='font-size:
12.0pt;line-height:150%'>2.</span><span style='font-size:12.0pt;line-height:
150%;font-family:宋体'>合格供应商须在竞价时间内参加网上竞价；</span></p>

<p class=MsoNormal style='line-height:150%'><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>　　</span><span lang=EN-US style='font-size:
12.0pt;line-height:150%'>3.</span><span style='font-size:12.0pt;line-height:
150%;font-family:宋体'>竞价时供应商须按系统提示填写产品、价格和服务信息；</span></p>

<p class=MsoNormal style='line-height:150%'><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>　　</span><span lang=EN-US style='font-size:
12.0pt;line-height:150%'>4. </span><span style='font-size:12.0pt;line-height:
150%;font-family:宋体'>在竞价时间内，供应商可以多次报价，最终报价以所报的最低价为准；</span></p>

<p class=MsoNormal style='line-height:150%'><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>　　</span><span lang=EN-US style='font-size:
12.0pt;line-height:150%'>5.</span><span style='font-size:12.0pt;line-height:
150%;font-family:宋体'>在竞价时间内，提供最低报价的供应商为成交供应商；</span></p>

<p class=MsoNormal style='line-height:150%'><span style='font-size:12.0pt;
line-height:150%;font-family:宋体'>　　</span><span lang=EN-US style='font-size:
12.0pt;line-height:150%'>6.</span><span style='font-size:12.0pt;line-height:
150%;font-family:宋体'>在竞价时间内，竞价供应商均未提供有效报价的，则竞价失败。</span></p>

<p class=MsoNormal style='line-height:150%'><span lang=EN-US style='font-size:
12.0pt;line-height:150%'>&nbsp;</span></p>

</div>
<div align="center"><button onclick="confirmBaoJia();">我要竞价</button></div>
</body>

</html>
