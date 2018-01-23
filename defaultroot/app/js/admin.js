/***
 *	SHOP++ Admin JavaScript
 *
 *	http://www.shopxx.net
 *
 *	Copyright © 2010 shopxx.net All Rights Reserved.
 **/

shopxx = {
	base: "",
	currencySign: "￥",
	currencyUnit: "元",
	priceScale: "2",
	priceRoundType: "roundHalfUp",
	orderScale: "2",
	orderRoundType: "roundHalfUp"
};

// 编辑器
if(typeof(KE) != "undefined") {
	KE.show({
		id: "editor",
		imageUploadJson: shopxx.base + "/admin/file!ajaxUpload.action",
		fileManagerJson: shopxx.base + "/admin/file!ajaxBrowser.action",
		allowFileManager: true
	});
}

$().ready( function() {

	/* ---------- List ---------- */
	
	var $listForm = $("#listForm");// 列表表单
	if ($listForm.size() > 0) {
		var $searchButton = $("#searchButton");// 查找按钮
		var $allCheck = $("#listTable input.allCheck");// 全选复选框
		var $idsCheck = $("#listTable input[name='ids']");// ID复选框
		var $deleteButton = $("#deleteButton");// 删除按钮
		var $pageNumber = $("#pageNumber");// 当前页码
		var $pageSize = $("#pageSize");// 每页显示数
		var $sort = $("#listTable .sort");// 排序
		var $orderBy = $("#orderBy");// 排序方式
		var $order = $("#order");// 排序字段
		
		// 全选
		$allCheck.click( function() {
			if ($(this).attr("checked") == true) {
				$idsCheck.attr("checked", true);
				$deleteButton.attr("disabled", false);
			} else {
				$idsCheck.attr("checked", false);
				$deleteButton.attr("disabled", true);
			}
		});
		
		// 无复选框被选中时,删除按钮不可用
		$idsCheck.click( function() {
			var $idsChecked = $("#listTable input[name='ids']:checked");
			if ($idsChecked.size() > 0) {
				$deleteButton.attr("disabled", false);
			} else {
				$deleteButton.attr("disabled", true)
			}
		});
		
		// 批量删除
		/*
		$deleteButton.click( function() {
			var url = $(this).attr("url");
			var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
			$.dialog({type: "warn", content: "您确定要删除吗？", ok: "确 定", cancel: "取 消", modal: true, okCallback: batchDelete});
			function batchDelete() {
				$.ajax({
					url: url,
					data: $idsCheckedCheck.serialize(),
					dataType: "json",
					success: function(data) {
						if (data.status == "success") {
							$idsCheckedCheck.parent().parent().remove();
						}
						$deleteButton.attr("disabled", true);
						$allCheck.attr("checked", false);
						$idsCheckedCheck.attr("checked", false);
						$.message({type: data.status, content: data.message});
					}
				});
			}
		});
		*/
	
		// 查找
		$searchButton.click( function() {
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 每页显示数
		$pageSize.change( function() {
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 排序
		$sort.click( function() {
			var $currentOrderBy = $(this).attr("name");
			if ($orderBy.val() == $currentOrderBy) {
				if ($order.val() == "") {
					$order.val("asc")
				} else if ($order.val() == "desc") {
					$order.val("asc");
				} else if ($order.val() == "asc") {
					$order.val("desc");
				}
			} else {
				$orderBy.val($currentOrderBy);
				$order.val("asc");
			}
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 排序图标效果
		if ($orderBy.val() != "") {
			$sort = $("#listTable .sort[name='" + $orderBy.val() + "']");
			if ($order.val() == "asc") {
				$sort.removeClass("desc").addClass("asc");
			} else {
				$sort.removeClass("asc").addClass("desc");
			}
		}
		
		// 页码跳转
		$.gotoPage = function(id) {
			$pageNumber.val(id);
			$listForm.submit();
		}
	}
	
	/* ---------- Validate ---------- */
		
	var $validateForm = $("#validateForm");
	if ($validateForm.size() > 0) {
		$validateForm.validate({
			errorClass: "validateError",
			ignore: ".ignoreValidate",
			errorPlacement: function(error, element) {
				var messagePosition = element.metadata().messagePosition;
				if("undefined" != typeof messagePosition && messagePosition != "") {
					var $messagePosition = $(messagePosition);
					if ($messagePosition.size() > 0) {
						error.insertAfter($messagePosition).fadeOut(300).fadeIn(300);
					} else {
						error.insertAfter(element).fadeOut(300).fadeIn(300);
					}
				} else {
					error.insertAfter(element).fadeOut(300).fadeIn(300);
				}
			},
			submitHandler: function(form) {
				$(form).find(":submit").attr("disabled", true);
				form.submit();
			}
		});
	}
	
});