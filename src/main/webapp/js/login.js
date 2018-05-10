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

function submitData() {
    $.ajax({
        type: "POST",
        url: "loginCheck.do",
        data: {"userName":$("#username").val(), "password":$("#password").val()},
        dataType: "json",
        success: function(data){
            if(0 == data.code)
            {
            	$.cookie('userData', data.data.user.userId);
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