
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

var roleTable;
function to_page(page){
    $.ajax({
        type: "POST",
        url: "get_all_role.do",
        dataType: "json",
        data:{"page":page},
        success: function(data){
        	roleTable = data.data;
            //显示table
            build_table(data.data);
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
    //jquery遍历,emps为遍历对象，function(index索引,item得到的每一个对象)为回调函数
    $.each(data,function(index, item){
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



//显示签到信息的总函数
function getAllRole(page){
	to_page(page);
};

$(function(){
	getAllRole(1);
});

//点击创建角色，在页面添加选择框，并并且删除另一个页面的选择框
$("#add-role").click(function (){
	$("#select-box-add").empty();
	$("#select-box-add").append('<div class="ue-container" style="margin-bottom: 15px">'+
	    '<select multiple="multiple" size="10" name="doublebox" class="demo">'+
	    '</select>'+
		'</div>'
	);
	$("#select-box-update").empty();
	//获取权限表以及设置左右选择框
	    $.ajax({
	        url:"getPowerTable.do",
	        type:"POST",
	        dataType:"json",
	        success:function (data) {
	            var nonSelectPower = new Array();
	            $.each(data, function (index, item) {
	            	nonSelectPower.push(item);
	            })
	            
	            var demo2 = $('.demo').doublebox({
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

})

//点击修改角色，在页面添加选择框，并并且删除另一个页面的选择框
$("#update-role").click(function (){
	$("#select-box-update").empty();
	$("#select-box-update").append('<div class="ue-container" style="margin-bottom: 15px">'+
	    '<select multiple="multiple" size="10" name="doublebox" class="demo">'+
	    '</select>'+
		'</div>'
	);
	$("#select-box-add").empty();

})

//输入姓名之后，判定是否存在，如果不存在，就无法点击按钮
$("#delete-role-input").mouseleave(function(){
	var flag = 1;
	var roleId;
	inputId = $(this).val();
	$.each(roleTable, function(index, item){	
		if(item.role == inputId){
			roleId = item.roleId;
			flag = 0;
			return false;
		}
	});
	if(flag == 1){
		$("#modal-role-delete-a").attr("disabled", true); 
		$("#role-delete").addClass("has-error");
		$("#role-delete").removeClass("has-success");
		$("#delete-user-help").text("角色名不存在，请核对后再输入");
	}else{
		$("#modal-role-delete-a").removeAttr("disabled");
		$("#role-delete").removeClass("has-error");
		$("#role-delete").addClass("has-success");
		$("#delete-user-help").text("角色名输入正确");
		$("#delete-role-button").attr("delete-roleId", roleId);
	}
});

//删除角色
$("#delete-role-button").click(function(){
    $.ajax({
        type: "POST",
        url: "delete_role.do",
        dataType: "json",
        data:{"roleId":$(this).attr("delete-roleId")},
        success: function(data){
        	window.location.reload();
        	alert(data.info);
        },
        error:function () {
            alert("删除角色出现错误，请重试");
        }
    });
});

$("#update-rolename").mouseleave(function(){
	var flag = 1;
	var roleId;
	var personPower = new Array();
	inputId = $(this).val();
	$.each(roleTable, function(index, item){	
		if(item.role == inputId){
			roleId = item.roleId;
			$.each(item.powerNames, function(index, item){
				personPower.push(item);
			});
			flag = 0;
			return false;
		}
	});
	
	if(flag == 1){
		$("#submit-update-role").attr("disabled", true); 
		$("#rolename-change").addClass("has-error");
		$("#rolename-change").removeClass("has-success");
		$("#update-rolename-help").text("角色名不存在，请核对后再输入");
	}else{
		$("#submit-update-role").removeAttr("disabled");
		$("#rolename-change").removeClass("has-error");
		$("#rolename-change").addClass("has-success");
		$("#submit-update-role").attr("update-prop", roleId);
		$("#update-rolename-help").text("角色名输入正确，请操作其权限");
		//获取权限表以及设置左右选择框
		$.ajax({
		    url:"getPowerTable.do",
		    type:"POST",
		    dataType:"json",
		    success:function (data) {
		    	$("#select-box-update").empty();
		    	$("#select-box-update").append('<div class="ue-container" style="margin-bottom: 15px">'+
		    	    '<select multiple="multiple" size="10" name="doublebox" class="demo">'+
		    	    '</select>'+
		    		'</div>'
		    	);
		        var nonSelect = new Array();
		        $.each(data, function (index, item) { 
		        	var exist = 1;
		        	for(var i = 0; i < personPower.length; i++)
		        	{
		        		if(item.powerId == personPower[i].powerId)
		        		{
		        			exist = 0;
		        			break;
		        		}
		        	}
		        	if(exist == 1){
		        		nonSelect.push(item);
		        	}
		        })
		        var demo1 = $('.demo').doublebox({
			        nonSelectedListLabel: '选择角色',
			        selectedListLabel: '授权用户角色',
			        preserveSelectionOnMove: 'moved',
			        moveOnSelect: false,
			        nonSelectedList:nonSelect,
			        selectedList:personPower,
			        optionValue:"powerId",
			        optionText:"powerName",
			        doubleMove:true,
			    });
		    },
		    error:function () {
		        alert("网络错误");
		    }
		});
		
	}
});

//点击修改角色
$("#submit-update-role").click(updateRole);
function updateRole(){
	var selectArr = new Array();
	$("#bootstrap-duallistbox-selected-list_doublebox option").each(function () {
		var item = $(this).val();
		selectArr.push(item);
	})
	$.ajax({
	    url:"update_role.do",
	    type:"POST",
	    dataType:"json",
	    traditional: true,
	    data:{"roleId":$("#submit-update-role").attr("update-prop"), "powerIds":selectArr},
	    success: function(data){
	    	window.location.reload();
	        alert(data.info);
	    },
	    error:function () {
	        alert("角色修改发生错误，请检查后重试");
	    }
	});
	return false;
}