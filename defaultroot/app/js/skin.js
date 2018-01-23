function changecss(sid){
     
	 //changeimg(sid);
	 var str=0;
	 var id = 0;
	 //alert('i am here!');
	 switch(sid){
	 	case 0:{str='default';}break;
	 	case 1:{str='danlv';} break;
	 	case 2:{str='shuimo';} break;
	 	case 3:{str='zise';}break;
	 	case 4:{str='putao';}break;
	 	case 5:{str='keai';}break;
	 	case 6:{str='chunni';}break;
	 	case 7:{str='qingxin';}break;
	 	case 8:{str='kafei';}break;
	 	case 9:{str='ganlan';}break;
	 }
	 id = sid;
/**	 document.getElementById('cssUrl').value= str;
	 document.getElementById('divform').action=window.location.href;
	 document.getElementById('divform').submit();
**/
	// changeimg(sid);

	 changehref(str);
     var expdate=new Date();
     expdate.setTime(expdate.getTime()+(24*60*60*1000*30));
     //expdate=null;
       //以下设置COOKIES时间为1年,自己随便设置该时间..
     SetCookie(skinname,str,expdate,"/",null,false);
     changeimg(sid);


	}


	function changeimg(sid){
	   for(i=0;i<10;i++){
     		if (i==sid){
     			eval('document.getElementById("s'+i+'").style.backgroundPosition="-14px '+i*(-11)+'px";');
     		}else{
     			eval('document.getElementById("s'+i+'").style.backgroundPosition="0px  '+i*(-11)+'px";');
     		}
      }

	}

	function changeimages(cssUrl){
		var sid = 0;
		if(cssUrl=='default'){sid = 0;}
		if(cssUrl=='danlv'){sid = 1;}
		if(cssUrl=='shuimo'){sid = 2;}
		if(cssUrl=='zise'){sid = 3;}
		if(cssUrl=='putao'){sid = 4;}
		if(cssUrl=='keai'){sid = 5;}
		if(cssUrl=='chunni'){sid = 6;}
		if(cssUrl=='qingxin'){sid = 7;}
		if(cssUrl=='kafei'){sid = 8;}
		if(cssUrl=='ganlan'){sid = 9;}
		changeimg(sid);
		changehref(cssUrl);
	}

	function SetCookie(name,value){
     var argv=SetCookie.arguments;
     var argc=SetCookie.arguments.length;
     var expires=(2<argc)?argv[2]:null;
     var path=(3<argc)?argv[3]:null;
     var domain=(4<argc)?argv[4]:null;
     var secure=(5<argc)?argv[5]:false;
     document.cookie=name+"="+escape(value)+((expires==null)?"":("; expires="+expires.toGMTString()))+((path==null)?"":("; path="+path))+((domain==null)?"":("; domain="+domain))+((secure==true)?"; secure":"");
}

function GetCookie(Name) {
     var search = Name + "=";
     var returnvalue = "";
     if (document.cookie.length > 0) {
           offset = document.cookie.indexOf(search);
           if (offset != -1) {
                 offset += search.length;
                 end = document.cookie.indexOf(";", offset);
                 if (end == -1)
                       end = document.cookie.length;
                 returnvalue=unescape(document.cookie.substring(offset,end));
           }
     }
     return returnvalue;
}



function changehref(sid){
//	 sid=0;
     thisskin = sid;
	 if (document.getElementById('skin_style0')){document.getElementById('skin_style0').href="res_base/jeecms_com_www/"+sid+"/article/css/content.css";}
	 if (document.getElementById('skin_style1')){document.getElementById('skin_style1').href="res_base/jeecms_com_www/"+sid+"/article/css/search.css";}
	 if (document.getElementById('skin_style2')){document.getElementById('skin_style2').href="res_base/jeecms_com_www/"+sid+"/article/css/menu.css";}
	 if (document.getElementById('skin_style3')){document.getElementById('skin_style3').href="res_base/jeecms_com_www/"+sid+"/article/css/foot.css";}
	 if (document.getElementById('skin_style4')){document.getElementById('skin_style4').src="res_base/jeecms_com_www/"+sid+"/article/img/spacer_01.gif";
	 }
	 if (document.getElementById('skin_style5')){document.getElementById('skin_style5').href="res_base/jeecms_com_www/"+sid+"/article/css/content.css";}
     if (document.getElementById('skin_style6')){document.getElementById('skin_style6').href="res_base/jeecms_com_www/"+sid+"/index/css/other.css";}
     
     if (document.getElementById('skin_style00')){document.getElementById('skin_style00').href="../res_base/jeecms_com_www/"+sid+"/article/css/content.css";}
	 if (document.getElementById('skin_style10')){document.getElementById('skin_style10').href="../res_base/jeecms_com_www/"+sid+"/article/css/search.css";}
	 if (document.getElementById('skin_style20')){document.getElementById('skin_style20').href="../res_base/jeecms_com_www/"+sid+"/article/css/menu.css";}
	 if (document.getElementById('skin_style30')){document.getElementById('skin_style30').href="../res_base/jeecms_com_www/"+sid+"/article/css/foot.css";}
	 if (document.getElementById('skin_style40')){document.getElementById('skin_style40').src="../res_base/jeecms_com_www/"+sid+"/article/img/spacer_01.gif";}
	 if (document.getElementById('skin_style50')){document.getElementById('skin_style50').href="../res_base/jeecms_com_www/"+sid+"/article/css/content.css";}
     if (document.getElementById('skin_style60')){document.getElementById('skin_style60').href="../res_base/jeecms_com_www/"+sid+"/index/css/other.css";}
     if (document.getElementById('skin_style90')){document.getElementById('skin_style90').href="../res_base/jeecms_com_www/"+sid+"/article/css/content1.css";}
     if (document.getElementById('skin_style91')){document.getElementById('skin_style91').href="../res_base/jeecms_com_www/"+sid+"/article/css/content2.css";}
     if (document.getElementById('skin_style92')){document.getElementById('skin_style92').href="../res_base/jeecms_com_www/"+sid+"/article/css/content2.css";}
     if (document.getElementById('skin_style80')){document.getElementById('skin_style80').href="../res_base/jeecms_com_www/"+sid+"/article/css/content2.css";}
     if (document.getElementById('skin_style100')){document.getElementById('skin_style100').href="../res_base/jeecms_com_www/"+sid+"/article/css/content2.css";}
     if (document.getElementById('leftFrame')) {window.frames['leftFrame'].frameSkin();}
     if (document.getElementById('kbdtleftFrame')) {window.frames['kbdtleftFrame'].frameSkin();}
     if (document.getElementById('bsznsearchFrame')) {window.frames['bsznsearchFrame'].frameSkin();}
     if (document.getElementById('cgmlsearchFrame')) {window.frames['cgmlsearchFrame'].frameSkin();}
     if (document.getElementById('dljgsearchFrame')) {window.frames['dljgsearchFrame'].frameSkin();}
     if (document.getElementById('gyssearchFrame')) {window.frames['gyssearchFrame'].frameSkin();}
     if (document.getElementById('zyjysearchFrame')) {window.frames['zyjysearchFrame'].frameSkin();}
     if (document.getElementById('searchRseult')) {window.frames['searchRseult'].frameSkin();}
     if (document.getElementById('dljgFrame')) {window.frames['dljgFrame'].frameSkin();}
     if (document.getElementById('gysFrame')) {window.frames['gysFrame'].frameSkin();}
     if (document.getElementById('goodsFrame')) {window.frames['goodsFrame'].frameSkin();}
     if (document.getElementById('kbdtsearchFrame')) {window.frames['kbdtsearchFrame'].frameSkin();}
     if (document.getElementById('showSpPjLeft')) {window.frames['showSpPjLeft'].frameSkin();}
     if (document.getElementById('shwoSpPpLeft')) {window.frames['shwoSpPpLeft'].frameSkin();}
     if (document.getElementById('shwoSpsxlxLeft')) {window.frames['shwoSpsxlxLeft'].frameSkin();}
     if (document.getElementById('skin_style_bszn')){document.getElementById('skin_style_bszn').href="../res_base/jeecms_com_www/"+sid+"/article/css/bszn.css"}
     if (document.getElementById('xh')) {
     if(document.getElementById('contentXh').style.display=='block'){ window.frames['xh'].frameSkin();}}
}

function SetCookieForYear(name,str){
	
	    var expdate=new Date();
     expdate.setTime(expdate.getTime()+(24*60*60*1000*30));
     //expdate=null;
       //以下设置COOKIES时间为1年,自己随便设置该时间..
     SetCookie(name,str,expdate,"/",null,false);
	
	
}