var classTable;
//获取班级列表
$(function(){
	$.ajax({
		url:"getSessionHisClasses.do",
		type:"POST",
		success: function(data){
			classTable = data;
			//便签1班级获取
			$("#mul-class-on-add").empty();
//			$("#add-class-select").empty();
		    $.each(data,function(index, item){
//		        var option = $("<option></option>").append(item.className);
//		        option.attr("value", item.classId);
//		        option.appendTo("#add-class-select");
//		        
		    	$("#mul-class-on-add").append(
		    			'<input type="checkbox" id="check-update-'+ item.classId +'" value="'+ item.classId +'" name="class-select-box-add">'+
		    			'<label for="check-update-'+ item.classId +'">'+ item.className +'</label>'
		    			);
		    });
		    
		    //便签2班级获取
		    $("#mul-class-on-update").empty();
//		    $("#update-class-select").empty();
		    $.each(data,function(index, item){
//		        var option = $("<option></option>").append(item.className);
//		        option.attr("value", item.classId);
//		        option.appendTo("#update-class-select");
//		        
		        $("#mul-class-on-update").append(
		    			'<input type="checkbox" id="check-add-'+ item.classId +'" value="'+ item.classId +'" name="class-select-box-update">'+
		    			'<label for="check-add-'+ item.classId +'">'+ item.className +'</label>'
		    			);
		    });
		},
		error:function () {
          alert("网络错误");
      }
	});
	
    $.ajax({
        type: "POST",
        url: "get_all_role.do",
        dataType: "json",
        success: function(data){
        	//添加用户便签内的角色
			$("#add-role-select").empty();
		    $.each(data.data,function(index, item){
		        var option = $("<option></option>").append(item.role);
		        option.attr("value", item.roleId);
		        option.appendTo("#add-role-select");
		    });
		    //更新用户便签内的角色
		    $("#update-role-select").empty();
		    $.each(data.data,function(index, item){
		        var option = $("<option></option>").append(item.role);
		        option.attr("value", item.roleId);
		        option.appendTo("#update-role-select");
		    });
        },
        error:function () {
            alert("网络错误");
        }
    });
});



//创建用户
function insert_user(){
	var addClassArr = new Array();
	$.each($('input:checkbox[name=class-select-box-add]:checked'),function(){
        	addClassArr.push($(this).val());
    });
	$.ajax({
	    url:"insert_user.do",
	    type:"POST",
	    dataType:"json",
	    traditional: true,
	    data:{
	    		"userName":$("#insert-username").val(),
	    		"password":$("#insert-password").val(),
	    		"name":$("#insert-name").val(),
	    		"roleId":$("#add-role-select").val(),
	    		"typeId":$("#add-type-select").val(),
	    		"classArr":addClassArr
	    	},
	    success: function(data){
	        alert(data.info);
	    },
	    error:function () {
	        alert("网络错误");
	    }
	});
	return false;
}



function to_page(page, typeId){
    $.ajax({
        type: "POST",
        url: "get_all_accounts.do",
        dataType: "json",
        data:{"page":page, "typeId":typeId},
        success: function(data){
            //显示table
            build_table(data);

            //显示分页文字
            buile_page_info(data);

            //显示分页条
            buile_page_nav(data);
        },
        error:function () {
            alert("网络错误");
        }
    });
};

//解析显示table
function build_table(data) {
    //清空
    $("#user-table tbody").empty();
    var dataList = data.list;
    //jquery遍历,emps为遍历对象，function(index索引,item得到的每一个对象)为回调函数
    $.each(dataList,function(index, item){
        //创建td并朝里面追加内容
        var name = $("<td></td>").append(item.name);
        var userName = $("<td></td>").append(item.userName);
        var password = $("<td></td>").append(item.password);
        var role = $("<td></td>");
        if(item.roleId == 1) {
        	role.append("教师")
        }else if(item.roleId == 2){
        	role.append("学生")
        }else if(item.roleId == 3){
        	role.append("管理员")
        }
        var type = $("<td></td>").append(item.typeId==0?"员工":"客户");
        //向一个tr中添加所有的td
        $("<tr></tr>").append(name).append(userName).append(password)
            .append(role).append(type).appendTo("#user-table tbody");
    })
}

//解析显示分页文字
function buile_page_info(data) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前第"+ data.pageNum +"页,总"+data.pages+
        "页,共"+data.total +"条记录");
    totalRecord = data.total ;
    currentPage = data.pageNum;
}

//解析显示分页条
function buile_page_nav(data) {
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    //如果没有前一页，首页和前一页无法点击
    if(data.hasPreviousPage == false){
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    }else{
        //跳转到首页
        firstPageLi.click(function () {
            to_page(1, $("#which-stage").val());
        })
        //上一页
        prePageLi.click(function () {
            to_page(data.pageNum - 1, $("#which-stage").val());
        })
    }


    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页"));
    //如果没有下一页，末页和下一页无法点击
    if(data.hasNextPage== false){
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    }else{
        //跳转到末页
        lastPageLi.click(function () {
            to_page(data.pages, $("#which-stage").val());
        })
        //下一页
        nextPageLi.click(function () {
            to_page(data.pageNum + 1, $("#which-stage").val());
        })
    }

    //添加首页和下一页按钮
    ul.append(firstPageLi).append(prePageLi);
    //遍历分页条
    var navPageNums = data.navigatepageNums;//里面为1,2,3,4,5 ..
    $.each(navPageNums,function(index,item){
        var numLi =$("<li></li>").append($("<a></a>").append(item));
        if(data.pageNum == item){
            numLi.addClass("active")
        }
        numLi.click(function() {
            to_page(item, $("#which-stage").val());
        });
        ul.append(numLi);
    })
    //添加下一页和末页按钮
    ul.append(nextPageLi).append(lastPageLi);

    var navEle = $("<nav></nav>").append(ul).appendTo("#page_nav_area");
}



//显示签到信息的总函数
function getAllUser(page, typeId){
	to_page(page, typeId);
};

//点击查询按钮获取相应的签到信息
$("#submit-which-stage").click(function(){
	getAllUser(1,  $("#which-stage").val());
});

$(function(){
	getAllUser(1, -1);
});

//创建班级
$("#add-class-button-on-add").click(function (){
	$.ajax({
	    url:"add_class.do",
	    type:"POST",
	    dataType:"json",
	    data:{"className":$("#class-name-input-on-add").val()},
	    success: function(data){
	        alert(data.info);
	    },
	    error:function () {
	        alert("班级创建失败");
	    }
	});
});
//创建班级
$("#add-class-button-on-update").click(function(){
	$.ajax({
	    url:"add_class.do",
	    type:"POST",
	    dataType:"json",
	    data:{"className":$("#class-name-input-on-update").val()},
	    success: function(data){
	        alert(data.info);
	    },
	    error:function () {
	        alert("班级创建失败");
	    }
	});
});

//输入姓名之后，判定是否存在，如果不存在，就无法点击按钮
$("#update-username").mouseleave(function(){
	var flag;
	$.ajax({
	    url:"find_user.do",
	    type:"POST",
	    dataType:"json",
	    data:{"userName":$(this).val()},
	    success: function(data){
	        flag = data.code;
	    	if(flag == -2){
	    		$("#update-user-display-box").hide();
	    		$("#submit-change").attr("disabled", true); 
	    		$("#username-change").addClass("has-error");
	    		$("#username-change").removeClass("has-success");
	    		$("#update-username-help").text("角色名不存在，请核对后再输入");
	    	}else if(flag == 0){
	    		$("#update-user-display-box").show();
	    		$("#submit-change").removeAttr("disabled");
	    		$("#username-change").removeClass("has-error");
	    		$("#username-change").addClass("has-success");
	    		$("#update-username-help").text("角色名输入正确");
	    		$("#submit-change").attr("update-username", data.data.userId);
	    		$("#update-password").val(data.data.password);
	    		$("#update-name").val(data.data.name);
	    		$("#update-role-select").val(data.data.role);
	    		$("#update-type-select").val(data.data.typeId);
	    		for(var i=0;i<data.data.classNames.length;i++){  
	                $("input[name=class-select-box-update]").each(function(){  
	                    if($(this).val()==data.data.classNames[i].classId){  
	                        $(this).attr("checked","checked");  
	                    }  
	                })  
	            }  

	    		$("#mul-class-on-update").val();
	    	}else{
	    		$("#update-user-display-box").hide();
	    		$("#submit-change").attr("disabled", true); 
	    		$("#username-change").addClass("has-error");
	    		$("#username-change").removeClass("has-success");
	    		$("#update-username-help").text(data.info);
	    	}
	    },
	    error:function () {
	        alert("网络错误");
	    }
	});
});


