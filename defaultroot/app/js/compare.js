var expandState = 0;
var oldPosition = 0;
var basketOrgTop=90;
var ie = document.all ? 1 : 0;
var ns = document.layers ? 1 : 0;
var firstStep = 20;
function showBox(){
	if (expandState == 0)
  {
      if(ie){
				if(isRight()){
					rightIncrease(firstStep);
				}else{
					leftIncrease(firstStep);
				}
      }
      expandState = 1;
  }
}


function hideBox(){
	if (expandState == 1)
  {
      if(ie){
	      if(isRight()){
					rightDecrease(firstStep);
				}else{
					leftDecrease(firstStep);
				}
      }
      expandState = 0;
  }
}

function selectProduct(productId, productName, checkValue) {
    if (checkValue == true) {
        document.c.id.value = productId;
        document.c.name.value = productName;
        document.c.method.value = "a";
        document.c.submit();
    }
    if (checkValue == false) {
        document.c.id.value = productId;
        document.c.name.value = productName;
        document.c.method.value = "r";
        document.c.submit();
    }

    if (expandState == 0)
    {
        showBox();
    }
}

function selectProduct(productId, productZlid,productName, checkValue) {
	
    if (checkValue == true) {
        document.c.id.value = productId;
        document.c.zlid.value = productZlid;
        document.c.name.value = productName;
        document.c.method.value = "a";
        document.c.submit();
    }
    if (checkValue == false) {
        document.c.id.value = productId;
        document.c.zlid.value = productZlid;
        document.c.name.value = productName;
        document.c.method.value = "r";
        document.c.submit();
    }

    if (expandState == 0)
    {
        showBox();
    }
}

function flow() {
		var scrollPos;
		var cHeight;
		
		if (typeof window.pageYOffset != 'undefined') {
		   	scrollPos = window.pageYOffset;
		   	cHeight=document.documentElement.clientHeight;

		}
		else if (typeof document.compatMode != 'undefined' &&
			document.compatMode != 'BackCompat') {
		   	scrollPos = document.documentElement.scrollTop;
		   	cHeight=document.documentElement.clientHeight;
		}
		else if (typeof document.body != 'undefined') {
		   	scrollPos = document.body.scrollTop;//ie
		   	cHeight=document.body.clientHeight;
		}
		var a = scrollPos;
		if(a!=oldPosition){
			document.getElementById("basket").style.top = (a+basketOrgTop)+'px';
			document.getElementById("basket_show").style.top = (a+basketOrgTop)+'px';
			document.getElementById("basket_close").style.top = (a+basketOrgTop)+'px';						
			oldPosition = a;
		}
}

function init(w,l,v){
   	if(isRight()){
		while(w>0){
			w = (w-v) < 0 ? 0 : (w-v);
			l = (l+v)> (initLeft+initWidth)?(initLeft+initWidth):(l+v);
		}	
		document.getElementById("basket_show").style.width=w+"px";
		document.getElementById("basket_show").style.left=l+"px";
		document.getElementById("basket_show").style.border = "0px";
	}else{
		document.getElementById("basket_show").style.width="0px";
		document.getElementById("basket_show").style.border = "0px";
	}
	document.getElementById("basket_close").style.display = "";		
	document.getElementById("basket_show").style.overflow = "hidden";
	window.setInterval("flow()",10);
}


function rightIncrease(v){
	var w = document.getElementById("basket_show").offsetWidth-2 ;
	var l = document.getElementById("basket_show").offsetLeft ;
	var step = (w+v) > initWidth ? initWidth : (w+v);
	var step2 = (l-v) < initLeft? initLeft : (l-v);
	document.getElementById("basket_show").style.width=step+"px";
	document.getElementById("basket_show").style.left=step2+"px";
	if(step>0){
		document.getElementById("basket_show").style.border = "1px solid #000";
		document.getElementById("basket_close").style.display = "none";
	}
	if(step < initWidth){
		setTimeout('rightIncrease('+v+')',10);
	}
}

function leftIncrease(v){
	var w = document.getElementById("basket_show").offsetWidth-2 ;
	var step = (w+v) > initWidth ? initWidth : (w+v);
	document.getElementById("basket_show").style.width=step+"px";
	if(step>0){
		document.getElementById("basket_show").style.border = "1px solid #000";
		document.getElementById("basket_close").style.display = "none";
	}
	if(step < initWidth){
		setTimeout('leftIncrease('+v+')',10);
	}
}

function rightDecrease(v){
	var w = document.getElementById("basket_show").offsetWidth-2 ;
	var l = document.getElementById("basket_show").offsetLeft ;
	var step = (w-v) < 0 ? 0 : (w-v);
	var step2 = (l+v)> (initLeft+initWidth)?(initLeft+initWidth):(l+v);
	document.getElementById("basket_show").style.width=step+"px";
	document.getElementById("basket_show").style.left=step2+"px";
	if(step<=0){
		document.getElementById("basket_show").style.border = "0px";
		document.getElementById("basket_close").style.display = "";
	}
	if(step > 0){
		setTimeout('rightDecrease('+v+')',10);
	}
}

function leftDecrease(v){
	var w = document.getElementById("basket_show").offsetWidth-2 ;
	var step = (w-v) < 0 ? 0 : (w-v);
	document.getElementById("basket_show").style.width=step+"px";
	if(step<=0){
		document.getElementById("basket_show").style.border = "0px";
		document.getElementById("basket_close").style.display = "";
	}
	if(step > 0){
		setTimeout('leftDecrease('+v+')',10);
	}
}

function isRight(){
	return initLeft > document.getElementById("basket").offsetWidth/2;
}

 var initWidth;
 var initLeft;
 if(ie) {
	document.write('   <div id="basket">');
	document.write('			<div id="basket_close" onclick="showBox()"><img src="../../img/there_show.gif" alt="展开产品比较篮" /><strong>产品比较篮</strong></div>');
	document.write('			<div id="basket_show" onclick="hideBox()">');
	document.write('				<div id="basket_top"><img src="../../img/there_close.gif" alt="收缩产品比较篮" /><strong>产品比较篮</strong></div>');
	document.write('             <iframe name=compareBox width="155" height="440" src="menugoods_compareSplist.do" frameborder="0" SCROLLING=no></iframe>');
	document.write('			</div>');
	document.write('		</div>');
	document.write("<FORM name=c METHOD=POST ACTION='menugoods_compareSplist.do' target=compareBox>");
	document.write("<INPUT TYPE=hidden NAME=id >");
	document.write("<INPUT TYPE=hidden NAME=zlid >");
	document.write("<INPUT TYPE=hidden NAME=name >");
	document.write("<INPUT TYPE=hidden NAME=method VALUE='a'>");
	document.write("</FORM>");
	initWidth = document.getElementById("basket_show").offsetWidth-2 ;
	initLeft = document.getElementById("basket_show").offsetLeft ;
	init(initWidth,initLeft,firstStep);
}

