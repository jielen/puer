/*
应用路径：<script type="text/javascript" src="${base}/common_res/js/switch_tab_tag.js"></script>
功能：处理tab样式栏目的切换效果，如首页公告和政策法规
作者：朱晨曦
时间：2009-09-14
*/

/*
功能：处理通过iframe显示数据的tab
参数说明：
index：当前点击的是第几个tab
type：栏目的业务类型。如省级公告(sjgg)
frameName：显示数据的iframe的id
url：iframe中需要放置的链接
*/
function switchTabTag(index,type,frameName,url) {
	//只有iframe中的url需要改变时才执行。
	if(document.getElementById(frameName).src != url){
		/*首先初始化所有tab的样式为默认样式*/
		var maxElement = 1;//最大元素数
		while(true){
			if(document.getElementById(type + "bar" + maxElement)){
				document.getElementById(type + 'bar' + maxElement).className="tabTagBarR";
				document.getElementById(type + 'rec' + maxElement).className="tabTagRecRR";
				document.getElementById(type + 'Span'+ maxElement).className="tabTagSpanR";
			}else{
				break;
			}
			//防止死循环
			if(maxElement == 20){
				break;
			}
			maxElement++;
		}
		//最后一个连接点元素
		document.getElementById(type + 'rec' + maxElement).className="tabTagRecRR";
		
		/*然后然后改变选中tab的样式*/
		document.getElementById(type + 'rec' + index).className="tabTagRecRB";
		document.getElementById(type + 'bar' + index).className="tabTagBarB";
		document.getElementById(type + 'rec' + (index + 1)).className="tabTagRecBR";
		document.getElementById(type + 'Span'+ index).className="tabTagSpanB";
		
		/*最后重置url*/
		document.getElementById(frameName).src = url;
	}
}

/*
功能：处理通过div显示数据的tab
参数说明：
index：当前点击的是第几个tab
type：栏目的业务类型。如省级公告(sjgg)
divName：显示数据的div的id前缀
*/		
function switchTabTagForDiv(index,type,divName) {
	/*首先将所有tab的改为不显示数据*/
	var maxElement = 1;//初始化最大元素数
	while(true){
		if(document.getElementById(divName + maxElement)){
			document.getElementById(divName + maxElement).style.display = "none";
		}else{
			break;
		}
		//防止死循环
		if(maxElement == 20){
			break;
		}
		maxElement++;
	}
	/*首先将选中的tab的数据显示*/
	document.getElementById(divName + index).style.display = "block";
	
	maxElement = 1;//初始化最大元素数
	while(true){
		if(document.getElementById(type + "bar" + maxElement)){
			document.getElementById(type + 'bar' + maxElement).className="tabTagBarR";
			document.getElementById(type + 'rec' + maxElement).className="tabTagRecRR";
			document.getElementById(type + 'Span'+ maxElement).className="tabTagSpanR";
		}else{
			break;
		}
		//防止死循环
		if(maxElement == 20){
			break;
		}
		maxElement++;
	}
	//最后一个连接点元素
	document.getElementById(type + 'rec' + maxElement).className="tabTagRecRR";
	
	document.getElementById(type + 'rec' + index).className="tabTagRecRB";
	document.getElementById(type + 'bar' + index).className="tabTagBarB";
	document.getElementById(type + 'rec' + (index + 1)).className="tabTagRecBR";
	document.getElementById(type + 'Span'+ index).className="tabTagSpanB";
}
