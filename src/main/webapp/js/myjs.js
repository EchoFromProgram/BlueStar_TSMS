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