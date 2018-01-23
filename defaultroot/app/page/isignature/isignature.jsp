<%
  String mServerName="/Service.jsp";
  String mHttpUrlName=request.getRequestURI();
  String mServerUrl = "http://"+request.getServerName()+":"+request.getServerPort()+"/GB/portal/page/isignature"+mServerName;
%>

<script type="text/javascript">
function DoJFSignature()
{
  //DocForm.SignatureControl.FieldsList="XYBH=协议编号;BMJH=保密级别;JF=甲方签章;HZNR=合作内容;QLZR=权利责任;CPMC=产品名称;DGSL=订购数量;DGRQ=订购日期"       //所保护字段
  var mx=event.clientX + document.body.scrollLeft - document.body.clientLeft;  //获取X坐标值
  var my=event.clientY + document.body.scrollTop  - document.body.clientTop;   //获取Y坐标值
  DocForm.SignatureControl.Position(mx,my);                      //签章位置，屏幕坐标
  DocForm.SignatureControl.UserName="lyj";                         //文件版签章用户
  DocForm.SignatureControl.RunSignature();                         //执行签章操作
}

</script>

<form name="DocForm" method="post">
<script type="text/javascript">
	var plugin = '<OBJECT id="SignatureControl"  classid="clsid:D85C89BE-263C-472D-9B6B-5264CD85B36E" codebase="iSignatureHTML.cab#version=7,1,0,196" width=0 height=0 VIEWASTEXT>';
	plugin+='<input type="hidden" name="DocumentID" value="'+documentID+'">';
	plugin+='<param name="ServiceUrl" value="<%=mServerUrl%>">';
	plugin+='<param name="WebAutoSign" value="0">';
	plugin+='<param name="PrintControlType" value=2>';
	plugin+='</OBJECT>';
	document.write(plugin);
</script>
</form>

<script type="text/javascript">
window.onload=function(){
	DocForm.SignatureControl.ShowSignature(documentID);
	document.body.setAttribute("title","双击签章");
	document.body.ondblclick=DoJFSignature;
}
</script>