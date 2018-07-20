$(function () {
    $("select option[value='"+ $('#which-stage').attr("data-sele") +"']").attr("selected", "selected");

    // 点击删除按钮删除指定的广告
    $(".delete-btn").on('click', function () {
        $("#delete-button").attr('data-adId',$(this).attr('data-adId'));
    })

    $("#delete-button").on("click",function() {
        $.ajax({
            url: "deleteAd.do",
            data: {"adId": $(this).attr("data-adId")},
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


    // 得到更新数据
    $(".update-btn").on("click",function() {
        var $this = this;
        $.ajax({
            url: "getUpdateData.do",
            data: {"adId": $(this).attr("data-adId")},
            type: "post",
            success: function(obj) {
                if(obj.statusCode == 0) {
                    $("#update_adPic").val(obj.data.adPicture);
                    $("#update_adId").val($($this).attr("data-adId"));
                    $("#update_title").val(obj.data.adTitle);
                    $("#update_createUser").val(obj.data.adCreateUser);
                    $("#update_order").val(obj.data.adOrder);
                    $("#update_url").val(  obj.data.adLinkUrl);
                    $("#update_pic").prop("src",obj.data.enclosurePath);
                    $("#update-which-stage option[value='"+ obj.data.adStatus +"']").attr("selected", "selected");
                } else {
                    alert(obj.msg);
                }
            },
            error: function () {
                alert("未知错误！");
            }
        })
    })

    // 更新操作
    $("#update-button").on("click",function() {
        let formData = new FormData($("#updateForm" )[0]);
        $.ajax({
            url: "updateAd.do",
            type: 'POST',
            data: formData,
            async: false,
            contentType: false,
            processData: false,
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



    $("#create-btn").on("click", function() {

        if(isBlank($("#addTitle").val())){
            alert("请填写标题");
            return ;
        }
        else if(isBlank($("#addCreateUser").val())){
            alert("请填写采编人");
            return ;
        }
        let formData = new FormData($("#uploadForm" )[0]);
        $.ajax({
            url: 'saveAd.do' ,
            type: 'POST',
            data: formData,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if(data.statusCode != 0) {
                    alert(data.msg);
                } else {
                    window.location.reload();
                }
            },
            error: function () {
                alert("未知错误！");
            }
        });
        return false;
    })

    function  isBlank(str) {
        if(str === '' || str === null || str === undefined)
            return true;
        return false;
    }
})

