
function ajaxReq(base,city){
	var partxmlhttp = XmlHttp.create();
	partxmlhttp.open("post",base+"/weather/getWeather.do?city="+city, false);
	partxmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	partxmlhttp.send(null);
	var returnStr = partxmlhttp.responseText;
	if(""!=returnStr){
		var result=returnStr.split('#');
		document.getElementById("weather").innerHTML='<b>天气：</b>'+result[0]+' '+result[2]+' '+result[1];
	}else{
		document.getElementById("weather").innerHTML='';
	}
}

function webOnline(base, url){
		var partxmlhttp = XmlHttp.create();
		partxmlhttp.open("POST",base + "/online/online_operate.do", false);
		partxmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		partxmlhttp.send("url=" + url);
			if (partxmlhttp.readyState ==4) {
			   if (partxmlhttp.status == 200){
						var flag = partxmlhttp.responseText;
			   }else return false;
			}else return false;
		return flag;
	}
	
function getWebOnline(base, url){
			webOnline(base, url);
			var partxmlhttp = XmlHttp.create();
			partxmlhttp.open("POST",base + "/online/online_getCount.do", false);
			partxmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			partxmlhttp.send("url=" + url);
			var  result;
			var ljfw = document.getElementById('ljfw');
			var nsjtdjw = document.getElementById('nsjtdjw');
			var dqzxrs = document.getElementById('dqzxrs');
			if (partxmlhttp.readyState ==4) 
			   if (partxmlhttp.status == 200) {
					   var returnStr = partxmlhttp.responseText;
			  		 if (returnStr!=null && returnStr!='') {
			 			 result = returnStr.split(',');
						if(ljfw != null && nsjtdjw != null && dqzxrs != null){
							if(result.length != 3){
								ljfw.innerHTML = '';
								nsjtdjw.innerHTML = '';
								dqzxrs.innerHTML = '';
							}else {
								ljfw.innerHTML = result[0];
								nsjtdjw.innerHTML = result[1];
								dqzxrs.innerHTML = result[2];
							}
			   }
			}
				
			}
	}
	
	function searchFormSubmit() {
		var hv=document.getElementById('search').value
	    if(hv==''||hv.split(' ').length-1==hv.length||hv=='输入关键字'){
		   alert('请输入搜索内容');
		   return false;
	    }else{
	       document.getElementById('headSearchForm').submit();	
	       document.getElementById('search').value='输入关键字';
	    }
    }
	
	function clearSearchKey(){
		var hv=document.getElementById('search').value
		if(hv=='输入关键字'){
			document.getElementById('search').value='';
		}
	}
	
	function addSearchKey(){
		var hv=document.getElementById('search').value
		if(hv==''||hv.split(' ').length-1==hv.length){
			document.getElementById('search').value='输入关键字';
		}
	}