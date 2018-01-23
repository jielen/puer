<link id=skin_style00 href="../../css/default.css" type="text/css" rel="stylesheet" />
<link id=skin_style10 href="../../css/search.css" type="text/css" rel="stylesheet" />
<link id=skin_style20 href="../../css/menu.css" type="text/css" rel="stylesheet" />
<link id=skin_style30 href="../../css/foot.css" type="text/css" rel="stylesheet" />
<link href="../../css/head.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="/portal/favicon.ico" />
<script type="text/javascript" src="../../js/switch_tab_tag.js"></script>
<script type="text/javascript" src="../../js/xmlextras.js"></script>
<script type="text/javascript" src="../../js/cms2_comm.js"></script>
<script type="text/javascript" src="../../js/skin.js"></script>
<script type="text/javascript">
	var sdate = new Date().getTime();
	var r_count = 0; 
	function document.onkeydown(){
		//F5和ctrl+R
		if(event.keyCode == 116 || (event.ctrlKey && event.keyCode == 82) ){
			if(document.readyState == "complete"){
				var curdate = new Date().getTime();
				r_count++;
				if(curdate - sdate <= 2000){
					event.keyCode = 0;
					return false;
				}else{
					if(r_count >= 2){
						event.keyCode = 0;
						alert("请不要在短时间内重复刷新！");
						sdate = new Date().getTime();
						r_count = 0;
						return false;
					}
				}
			}
		}
	}
</script> 
<script type="text/javascript">
	function searchFormSubmit() {
	if(document.getElementById('headSearchKey').value==''){
		alert('请输入搜索内容');
		return false;
	 }
	document.getElementById('searchForm').submit();	
    }
    
    function gettime(){
		var day="";
		var month="";
		var ampm="";
		var ampmhour="";
		var myweekday="";
		var year="";
		mydate=new Date();
		myweekday=mydate.getDay();
		mymonth=mydate.getMonth()+1;
		myday= mydate.getDate();
		year= mydate.getFullYear();
		if(myweekday == 0)
		weekday=" 星期日 ";
		else if(myweekday == 1)
		weekday=" 星期一 ";
		else if(myweekday == 2)
		weekday=" 星期二 ";
		else if(myweekday == 3)
		weekday=" 星期三 ";
		else if(myweekday == 4)
		weekday=" 星期四 ";
		else if(myweekday == 5)
		weekday=" 星期五 ";
		else if(myweekday == 6)
		weekday=" 星期六 ";
		document.getElementById('time').innerHTML= year+"年"+mymonth+"月"+myday+"日 "+weekday;
		}

	function init(){
	    //gettime();
//	    changeimages('default');
	    //setTimeout("getWebOnline('/new', document.URL)",3000);
	    //getWebOnline('/new', document.URL);
	    //ajaxReq("/new","杭州");
    }
    

	  function viladateFormy(){
		var a = document.getElementById("search").value;
		if(a=="输入关键字"){
		  alert("请输入关键字！");
		  return;
		}
		if(check_space(a)){
		   document.getElementById("formy").submit();
		}
	 }

	 function check_space(str){
		var re = "/^\s+$/";
		if(re.exec(str)||str.length<1) {
			 document.getElementById("search").focus();
			 return false;
		}else{
			return true;
		}
	}
</script>