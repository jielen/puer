	$(function(){
	 $.extend({
	    	calcuPrice:function(){
				var zcMerMPrice = $("input.zcMerMPrice").val();
				var disRate = $(".disRate").val();
				var countPrice=zcMerMPrice*(100-disRate)/100;
				$("#zcmerprice").val(countPrice);
	   		}
	    });
		jQuery.validator.addMethod("decimal", function(value, element) {
	   	 	var decimal = /^-?\d+?$/;
	    	return this.optional(element) || (decimal.test(value));
	  	 },
	    $.validator.format("不允许小数"));
	   
		$(".merchandiseForm").validate(
		{	submitHandler:function(form){
				$.calcuPrice();
				form.submit();
			},
			rules: {
			zcmerprice:"required",
			lastname: "required",
			lowerlimit: {
				required: true,
				digits: true
			},
			upperlimit: {
				required: true,
				digits: true
				
			},
			disRate: {
				required: true,
				decimal:true,
				range: [-100,100]
			},
			email: {
				required: true,
				email: true
			},
			topic: {
				required: "#newsletter:checked",
				minlength: 2
			},
			agree: "required"
		}
		});   
	});      