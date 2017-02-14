 //省市县三级联动js
var basePath="/free";
var provinceId ='';
var cityId = '';
var countyId = '';

function initRegion(provinceId_,cityId_,countyId_){
	if(typeof(provinceId_) != "undefined"&&provinceId_!=''&&
			typeof(cityId_) != "undefined"&&cityId_!=''&&
			typeof(countyId_) != "undefined"&&countyId_!=''){
		provinceId = provinceId_;
		cityId = cityId_;
		countyId = countyId_;
	}
	initProvice();
}

function initProvice(){
	$.ajax({  
        type:'post',      
        url:basePath+'/manage/region/getProviceJsons',  
        data:{},  
        cache:false,  
        dataType:'json',  
        success:function(data){ 
        	var json = eval('('+data+')');
      	  	if(json.resCode=='0000'){
      	  		var info = json.info;
      	  		var $div = $(".regionLinkage");
      	  		var selectHt = "<select style='width:137px;height: 28px;' id='province' onchange='changeProvince(this)'></select>";
      	  		$div.append(selectHt);
	      	  	$("<option></option>")
	            .val("-1")
	            .text("请选择")
	            .appendTo($("#provice"));
	      	  	$.each(info, function(i, item) {
	      	  		
	      	  		if(provinceId==item.id){
		      	  		$("<option selected='selected'></option>")
		                    .val(item.id)
		                    .text(item.name)
		                    .appendTo($("#province"));
	      	  		}else{
	      	  			if(i==0&&provinceId==''){
		      	  			$("<option></option>")
		                    .val("-1")
		                    .text("请选择")
		                    .appendTo($("#province"));
	      	  			}
	      	  			$("<option></option>")
	                    .val(item.id)
	                    .text(item.name)
	                    .appendTo($("#province"));
			      	  		
      	  			}
		      	  		
	            });
	      	  	if(provinceId!=''){
	      	  		initCity(provinceId);
	      	  	}
      	  	}else{
      	  	console.log(e);
      	  	}
        },
        error: function(e){
        	console.log(e);
     	}  
   });
}

function changeProvince(obj){
	cityId = "";
	$("#city").remove();
	$("#county").remove();
	var provinceId = $(obj).val();
	if('-1'!=provinceId){
		$("#houseAddress").next().html("");
		initCity(provinceId)
	}
}

function initCity(provinceId){
	$.ajax({  
        type:'post',      
        url:basePath+'/manage/region/getCityJsons',  
        data:{"provinceId":provinceId},  
        cache:false,  
        dataType:'json',  
        success:function(data){ 
        	var json = eval('('+data+')');
      	  	if(json.resCode=='0000'){
      	  		var info = json.info;
      	  		var $div = $(".regionLinkage");
      	  		var selectHt = "<select style='width:148px;margin-left:10px;height: 28px;' id='city' onchange='changeCity(this)'></select>";
      	  		$div.append(selectHt);
	      	  	$("<option></option>")
	            .val("-1")
	            .text("请选择")
	            .appendTo($("#city"));
	      	  	$.each(info, function(i, item) {
		      	  	if(cityId==item.id){
		      	  		$("<option selected='selected'></option>")
		                    .val(item.id)
		                    .text(item.name)
		                    .appendTo($("#city"));
	      	  		}else{
	      	  			$("<option></option>")
	      	  			.val(item.id)
	      	  			.text(item.name)
	      	  			.appendTo($("#city"));
	      	  		}
	            });
	      	  	if(cityId!=''){
	      	  		initCounty(cityId);
	      	  	}
      	  	}else{
      	  	console.log(e);
      	  	}
        },
        error: function(e){
        	console.log(e);
     	}  
   });
}

function changeCity(obj){
	countyId = "";
	$("#county").remove();
	var cityId = $(obj).val();
	if('-1'!=cityId){
		initCounty(cityId)
	}
}

function initCounty(cityId){
	$.ajax({  
        type:'post',      
        url:basePath+'/manage/region/getCountyJsons',  
        data:{"cityId":cityId},  
        cache:false,  
        dataType:'json',  
        success:function(data){ 
        	var json = eval('('+data+')');
      	  	if(json.resCode=='0000'){
      	  		var info = json.info;
      	  		var $div = $(".regionLinkage");
      	  		
      	  		var selectHt = "<select style='width:180px;height: 28px;margin-left:10px' id='county'></select>";
      	  		$div.append(selectHt);
	      	  	$("<option></option>")
	            .val("-1")
	            .text("请选择")
	            .appendTo($("#county"));
	      	  	$.each(info, function(i, item) {
		      	  	if(countyId==item.id){
		      	  		$("<option selected='selected'></option>")
		                    .val(item.id)
		                    .text(item.name)
		                    .appendTo($("#county"));
	      	  		}else{
	      	  			$("<option></option>")
	      	  			.val(item.id)
	      	  			.text(item.name)
	      	  			.appendTo($("#county"));
	      	  		}
	            });
      	  	}else{
      	  	console.log(e); 
      	  	}
        },
        error: function(e){
        	console.log(e);
     	}  
   });
}

function getProvinceId(){
	var province = $("#province").val();
	if(typeof(province) == "undefined"||province==''){
		return "";
	}
	return province;
}
function getCityId(){
	var city = $("#city").val();
	if(typeof(city) == "undefined"||city==''){
		return "";
	}
	return city;
}
function getCountyId(){
	var county = $("#county").val();
	if(typeof(county) == "undefined"||county==''){
		return "";
	}
	return county;
}
function getProvinceText(){
	return $("#province").find("option:selected").text();
}
function getCityText(){
	return $("#city").find("option:selected").text();
}
function getCountyText(){
	return $("#county").find("option:selected").text();
}