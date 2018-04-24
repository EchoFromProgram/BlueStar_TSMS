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