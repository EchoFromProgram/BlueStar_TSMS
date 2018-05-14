function insert_user(){
	$.ajax({
	    url:"insert_user.do",
	    type:"POST",
	    dataType:"json",
	    data:{
	    		"userName":$("#insert-username").val(),
	    		"password":$("#insert-password").val(),
	    		"name":$("#insert-name").val(),
	    		"roleId":$("#insert-role-select").val(),
	    		"typeId":$("#insert-type-select").val()
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




