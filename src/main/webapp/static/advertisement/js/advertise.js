$(function () {
    $("select option[value='"+ $('#which-stage').attr("data-sele") +"']").attr("selected", "selected");
    let adId = $(".delete-btn").attr("data-adId");

    // 点击删除按钮删除指定的广告
    $(".delete-btn").on("click",function () {
        $.ajax({
            url: "deleteAd.do",
            data: {"adId":adId},
            type: "post",
            success: function(data) {
                if(data.statusCode == 0) {
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            },
            error: function () {
                alert("未知错误！");
            }
        })

    })
})

