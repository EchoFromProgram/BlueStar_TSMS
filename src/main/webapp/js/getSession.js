function getSession(){
    $.ajax({
        type: "POST",
        url: "getSessionUser.do",
        dataType: "json",
        success: function(data){
			$('#username').html(data.name);
        }
    });
    return false;
};
getSession();


$(".select").click(function () {
    $('#current_select').html($(this).children().html());
})

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