//兼容所有版本，待测试，使Iframe自适应高度
function reSetIframe(name){
	var iframe = document.getElementById(name); 
	try{ 
		var bHeight = iframe.contentWindow.document.body.scrollHeight; //iframe内容高度
		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight; //iframe当前高度
		var height = Math.max(bHeight,dHeight); //取较大值，兼容各浏览器
		iframe.height = height; 
	}catch (ex){} 

}

//已兼容IE、FF
function reSetIframeXH(){
	var iframe = document.getElementById('xh'); 
	try{ 
		var bHeight = iframe.contentWindow.document.body.scrollHeight; //iframe内容高度
		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
		var height;
		if(bHeight!=0){					
			height =bHeight;
		}else{
			height =dHeight;
		}
		iframe.height = height; 
	}catch (ex){} 						
}
window.setInterval("reSetIframeXH()",200);//重复设置ID为XH的Iframe的高度

function reSetIframeSearchRseult(){
	var iframe = document.getElementById('searchRseult'); 
	try{ 
		var bHeight = iframe.contentWindow.document.body.scrollHeight; //iframe内容高度					
		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
		var height;
		if(bHeight!=0){					
			height =bHeight;
		}else{
			height =dHeight;
		}
		if(height<580)height=580;
		iframe.height = height; 
	}catch (ex){} 						
}
window.setInterval("reSetIframeSearchRseult()",200);//重复设置ID为searchRseult的Iframe的高度
	
function getElementsByName(name){  //有些IE版本不支持getElementsByName，自己写 
	var all = document.all;   
	var result = [];   
	for(var i=0;i<all.length;i++)   
	if(all[i]["name"]==name)   
	result[result.length] = all[i];   
	return result;   
}

//选一个
function chooseOne(mod){
	var checkID;
	if(mod=="true") checkID = "ImgTrue";
	else if(mod=="false") checkID = "ImgFalse";
	else checkID = "ImgNo";
	var cb=document.getElementById(checkID);     
	//先取得同name的chekcBox的集合      
	var obj = document.all?getElementsByName("ImgMod"):document.getElementsByName("ImgMod");      
	for (i=0; i<obj.length; i++){      
 		//判断obj集合中的i元素是否为cb，若否则表示未被选中      
 		if (obj[i]!=cb) obj[i].checked = false;      
 		//若是 但原先未被选中 则变成选中；反之 则变为未勾选      
 		//else  obj[i].checked = cb.checked;      
 		//若要至少勾选一个的话，则把上面那行else拿掉，换用下面那行      
         		else obj[i].checked = true;      
     		}      
}
 
var toImg = GetCookie("ResultImgMod");
function switchShowMod(mod){
	SetCookieForYear("ResultImgMod",mod) 
	toImg=mod;
	chooseOne(toImg);
	document.getElementById('searchRseult').src=orderURL+"&toImg="+toImg;
}
var imgBaseSrc="../../img/";
var imgBaseName="pl";
var imgBaseId="pic";
function orderPic(baseCode){//
	var orderCode = baseCode;//+orderCodeSc;
	var orderValue = "";
	//orderCodeSc=(orderCodeSc+1)%2;
	var obj = document.all?getElementsByName("orders"):document.getElementsByName("orders");
	try{
		var orders = parseInt(baseCode);
		obj[orders-1].checked = true;
		orderValue = obj[orders-1].value;
		document.getElementById("order"+baseCode).selected = true;
	}catch (ex){}
	document.getElementById('searchRseult').src=orderURL+"&orders="+orderValue+"&toImg="+toImg;

	for(i=0;i<3;i++){//遍历3张排序图片
		var curimgObj=document.getElementById(imgBaseId+(i+1));
		if(i==orders-1){
			curimgObj.src=imgBaseSrc+imgBaseName+(i+1)+".gif";
		}else{
			curimgObj.src=imgBaseSrc+imgBaseName+(i+1)+"1.gif";
		}
	}
}

function initOrderImg(){
	for(i=1;i<4;i++){
		document.getElementById(imgBaseId+(i)).src=imgBaseSrc+imgBaseName+(i)+"1.gif";
	}
}