//登陆时用户名提示
$('#username').blur(
    function () {
        if($('#username').val() == "")
        {
            $('#username-div').addClass("has-error");
            $('#username-help').text("用户名不能为空");
        }
        else
        {
            $('#username-div').removeClass("has-error")
            $('#username-help').text("");
        }
    }
);

//登陆时密码提示
$('#password').blur(
    function () {
        if($('#password').val() == "")
        {
            $('#password-div').addClass("has-error");
            $('#password-help').text("密码不能为空");
        }
        else
        {
            $('#password-div').removeClass("has-error")
            $('#password-help').text("");
        }
    }
);

//登陆验证 以及 必要数据的cookie
function submitData() {
    $.ajax({
        type: "POST",
        url: "loginCheck.do",
        data: {"userName":$("#username").val(), "password":$("#password").val()},
        dataType: "json",
        success: function(data){
            if(0 == data.code)
            {
//            	$.cookie('userData', data.data.user.user_id);
//            	$.cookie('infoId', data.data.user.info_id);
//            	$.cookie('typeId', data.data.user.type_id);
//            	$.cookie('name', data.data.user.name);
                window.location.href="index.do";
            }
            else if(-2 == data.code)
            {
            	$('#password-help').text(data.info);
	            $('#password-div').addClass("has-error");
            }
            else if(-1 == data.code)
            {
            	$('#username-help').text(data.info);
	            $('#username-div').addClass("has-error");
            }
        },
        error:function () {
            alert("网络错误");
        }
    });
    return false;
};