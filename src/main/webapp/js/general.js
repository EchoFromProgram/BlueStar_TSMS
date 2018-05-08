//个人资料转跳url设置
$(function(){
	$.ajax({
        type: "POST",
        url: "getSessionUser.do",
        dataType: "json",
        success: function(data){
			$('#username').html(data.name);
			if(data.typeId == 1){
				$('#index_info').attr('href', "staff_info.do");
			}
			else{
				$('#index_info').attr('href', "customer_info.do");
			}
        },
        error:function () {
            alert("网络错误");
        }
    });
});

//搜索框样式
$(".select").click(function () {
    $('#current_select').html($(this).children().html());
})

//响应式菜单隐藏
var jump_index = 0;
$('#huge').click(
    function () {
        if(jump_index == 0) {
            $('.sidebar').css("display", "block");

            jump_index = 1;
        }
        else{
            $('.sidebar').css("display", "none");
            jump_index = 0;
        }
    }
);

//获取权限表（以后会用application代替）
var powerTable;
$(function(){
	   $.ajax({
	       url:"getPowerTable.do",
	       type:"POST",
	       dataType:"json",
	       success:function (data) {
	            powerTable = data;
	       },
	       error:function () {
	           alert("网络错误");
	       }
	   });
	});

//获取个人权限
$(function(){
	for(var i = 0; i < 100000000; i++)
	{
		i++;
	}
	   $.ajax({
	       url:"getSessionHisPowers.do",
	       type:"POST",
	       dataType:"json",
	       success:function (data) {
	    	   var path = window.location.pathname;
	    	   var suffix = path.substr(path.lastIndexOf('/') + 1);
	    	 	$.each(data,function(i, item){
	    	 		if(suffix == powerTable[item]){
	    	 			$("#nav-list").append(
		    	 				'<li class="active"><a href="#">' +
		    	 				powerTable[item]
		    	 				+ "</a></li>"
		    	 				);
	    	 		}else{
	    	 			$("#nav-list").append(
		    	 				"<li><a href=" + 
		    	 				powerTable[item] + 
		    	 				">" +
		    	 				powerTable[item]
		    	 				+ "</a></li>"
		    	 				);
	    	 		}
	    	 	});
	       },
	       error:function () {
	           alert("网络错误");
	       }
	   });
	});

//时间格式转换
function dateFormat(date) {  
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? '0' + m : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    return y + '-' + m + '-' + d;  
}; 