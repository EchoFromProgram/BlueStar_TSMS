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
            if("SUCCESS" == data.status)
            {
                window.location.href="index.do";
            }
            else if("WRONG_PASSWORD" == data.status)
            {
            	$('#password-help').text(data.status);
	            $('#password-div').addClass("has-error");
            }
            else if("WRONG_USERNAME" == data.status)
            {
            	$('#username-help').text(data.status);
	            $('#username-div').addClass("has-error");
            }
        }
    });
    return false;
};