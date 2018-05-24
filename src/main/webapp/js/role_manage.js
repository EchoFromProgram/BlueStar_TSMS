var demo2;
//获取权限表以及设置左右选择框
$(function(){
    $.ajax({
        url:"getPowerTable.do",
        type:"POST",
        dataType:"json",
        success:function (data) {
            console.log(data);
            var nonSelectPower = new Array();
            $.each(data, function (index, item) {
            	nonSelectPower.push(item);
            })
            
            demo2 = $('.demo').doublebox({
                nonSelectedListLabel: '未被选择权限',
                selectedListLabel: '已被选择的权限',
                preserveSelectionOnMove: 'moved',
                moveOnSelect: false,
                nonSelectedList:nonSelectPower,
                selectedList:[],
                optionValue:"powerId",
                optionText:"powerName",
                doubleMove:true,
            });
        },
        error:function () {
            alert("网络错误");
        }
    });
});

//点击创建来创建角色
$("#submit-create-role").click(createRole);
function createRole(){
	var selectArr = new Array();
	$("#bootstrap-duallistbox-selected-list_doublebox option").each(function () {
		var item = $(this).val();
		selectArr.push(item);
	})
	$.ajax({
	    url:"create_role.do",
	    type:"POST",
	    dataType:"json",
	    traditional: true,
	    data:{"roleName":$("#insert-rolename").val(), "roleIds":selectArr},
	    success: function(data){
	        alert(data.info);
	    },
	    error:function () {
	        alert("网络错误");
	    }
	});
	return false;
}


function to_page(page){
    $.ajax({
        type: "POST",
        url: "get_all_role.do",
        dataType: "json",
        data:{"page":page},
        success: function(data){
        	console.log(data.data);
            //显示table
            build_table(data.data);

            //显示分页文字
            buile_page_info(data.data);

            //显示分页条
            buile_page_nav(data.data);
        },
        error:function () {
            alert("网络错误");
        }
    });
};

//解析显示table
function build_table(data) {
    //清空
    $("#role-table tbody").empty();
    var dataList = data.list;
    //jquery遍历,emps为遍历对象，function(index索引,item得到的每一个对象)为回调函数
    $.each(dataList,function(index, item){
        //创建td并朝里面追加内容
        var roleName = $("<td></td>").append(item.role);
        var powerName = $("<td></td>");
        for(var i = 0; i < item.powerNames.length; i++){
        	powerName.append(item.powerNames[i].powerName + " &nbsp; , &nbsp; ");
        }

        //向一个tr中添加所有的td
        $("<tr></tr>").append(roleName).append(powerName).appendTo("#role-table tbody");
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
            to_page(1);
        })
        //上一页
        prePageLi.click(function () {
            to_page(data.pageNum - 1);
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
            to_page(data.pages);
        })
        //下一页
        nextPageLi.click(function () {
            to_page(data.pageNum + 1);
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
            to_page(item);
        });
        ul.append(numLi);
    })
    //添加下一页和末页按钮
    ul.append(nextPageLi).append(lastPageLi);

    var navEle = $("<nav></nav>").append(ul).appendTo("#page_nav_area");
}



//显示签到信息的总函数
function getAllRole(page){
	to_page(page);
};

$(function(){
	getAllRole(1);
});