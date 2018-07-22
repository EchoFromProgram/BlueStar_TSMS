/* 组织部门模块脚本 */
/* @author Fish */

$(() => {
    // 不是一个类标识
    const NOT_A_CLASS = "not-a-class-";

    // 点击加一个
    $("#tree").delegate("p", "click", (e) => {
        const $target = $(e.target);
        $("#tree p.active").removeClass("active");
        $target.addClass("active");

        // 判断是开还是关
        if ($target.hasClass("fish-open")) {
            closeList($target);
        } else {
            openList($target);
        }
        $target.toggleClass("fish-open fish-close");

        if ($target.attr("hasAppended") !== "true") {
            getDeptAjax($target.attr("data-dept-code"), (resp) => {
                // 得到数据之后，插入到树形结构中
                appendNodes($target, resp.data);
            });
        }

        // 刷新画面
        reflash();

        // 更换详情页标题
        $("#deptDetail").text($target.attr("data-dept-name"));

        // 显示这个部门下的用户
        showUsers($target.attr("data-dept-code"));
    });

    // 新增一个树形节点
    function appendNode(father, dept) {
        if (dept === null || typeof dept === "undefined") {
            return;
        }

        const $root = $("#tree");
        const $p = $("<p class='fish-close'>" + dept.deptName + "</p>");
        $p.addClass(NOT_A_CLASS + dept.deptPCode);
        $p.attr({
            "data-dept-id": dept.deptId,
            "data-dept-code": dept.deptCode,
            "data-dept-level": dept.deptLevel,
            "data-dept-name": dept.deptName,
            "data-dept-order": dept.deptOrder,
            "data-dept-p-code": dept.deptPCode,
            "data-dept-remark": dept.deptRemark
        });

        // 父节点为空，直接添加到根容器
        if (father === null) {
            $root.append($p);
            return;
        }

        if (father.attr("hasAppended") !== "true") {
            // 向父节点后面插入数据
            father.after($p);
        }
    }

    // 插入一个部门列表
    function appendNodes(father, arr) {
        if (arr === null || typeof arr === "undefined") {
            return;
        }

        for (let i = 0; i < arr.length; i++) {
            appendNode(father, arr[i]);
        }

        // 标识已经添加过了
        if (father !== null) {
            father.attr("hasAppended", true);
        }

        // 刷新画面
        reflash();
    }

    // 刷新画面
    function reflash() {
        $("#tree p").each((index, item) => {
            $(item).css({
                "text-indent": ($(item).attr("data-dept-level") - 1) * 2 + "em"
            });
        });
    }

    // 展开列表
    function openList(ele) {
        $("#tree p").each((index, item) => {
            if ($(item).hasClass(NOT_A_CLASS + ele.attr("data-dept-code"))) {
                $(item).stop();
                $(item).slideDown("slow");
            }
        });
    }

    // 关闭列表
    function closeList(ele) {
        const minLevel = ele.attr("data-dept-level");
        let current = ele.next();
        while (true) {
            if (current.length === 0 || current.attr("data-dept-level") <= minLevel) {
                break;
            }
            current.stop();
            current.slideUp("slow");
            current.removeClass("fish-open");
            current.addClass("fish-close");
            current = current.next();
        }
    }

    // 得到选中的卡片
    function getActive() {
        return $("#tree p.active");
    }

    // 判断是否有选中
    function hasActive() {
        return $("#tree p.active").length > 0;
    }

    // 一加载页面就初始化树形结构
    $.ajax({
        url: "getDepartmentsByLevelAndDeptPCode.do",
        type: "GET",
        data: {
            "deptLevel": "1",
            "isGetAllDepartments": false
        },
        success: (resp) => {
            // 将数据贴到树形结构上
            if (resp.data === null) {
                return;
            }

            appendNodes(null, resp.data);
        },
        error: () => {
            alert("网络错误！");
        }
    });

    // 根据 deptCode 得到部门旗下的其他子部门信息 ajax 请求
    function getDeptAjax(deptCode, callback) {
        $.ajax({
            url: "getChildrenDepartments.do",
            type: "GET",
            data: {
                "deptCode": deptCode,
                "isGetAllDepartments": false
            },
            success: (resp) => {
                callback(resp);
            },
            error: () => {
                alert("网络错误！");
            }
        });
    }

    // 删除部门 ajax 请求
    function deleteDept(deptId) {
        $.ajax({
            url: "deleteDepartment.do",
            type: "GET",
            data: {
                "deptId": deptId
            },
            success: (resp) => {
                if (resp.code !== 0) {
                    alert(resp.msg);
                    return;
                }

                // 成功了，刷新界面
                window.location.reload();
            },
            error: () => {
                alert("网络错误！");
            }
        });
    }

    // 删除部门按钮
    $("#deleteDept").on("click", () => {
        if (!hasActive()) {
            alert("您还没有选中任何部门！！");
            return;
        }

        if (window.confirm("您确定要删除这个部门？")) {
            deleteDept(getActive().attr("data-dept-id"));
        }
    });

    // 新增部门 ajax 请求
    function saveDept(dept) {
        $.ajax({
            url: "addDepartment.do",
            type: "POST",
            data: dept,
            success: (resp)=>{
                if (resp.code === 0) {
                    // 新增成功就刷新界面
                    //$('#saveModal').modal('hide');
                    window.location.reload();
                    return;
                }

                alert(resp.msg);
            },
            error: ()=>{
                alert("网络错误！");
            }
        });
    }

    // 新增部门按钮
    $("#saveDept").on("click", ()=>{
        const $currentDept = getActive();
        const $deptPCode = $("#saveDeptPCode");
        const $deptLevel = $("#saveDeptLevel");
        if ($currentDept.length === 0) {
            // 没有选中任何对象
            $deptPCode.val(null);
            $deptLevel.val("1");
        } else {
            // 选中了某个部门
            $deptPCode.val($currentDept.attr("data-dept-code"));
            $deptLevel.val(Number($currentDept.attr("data-dept-level")) + 1);
        }

        // 清空表单
        $("#saveModal form")[0].reset();
    });

    // 处理新增部门业务按钮
    $("#doSaveDept").on("click", ()=>{
        saveDept($("#saveModal form").serialize());
    });

    // 修改部门 ajax 请求
    function updateDept(dept) {
        $.ajax({
            url: "updateDepartment.do",
            type: "POST",
            data: dept,
            success: (resp)=>{
                if (resp.code === 0) {
                    // 新增成功就刷新界面
                    //$('#saveModal').modal('hide');
                    window.location.reload();
                    return;
                }

                alert(resp.msg);
            },
            error: ()=>{
                alert("网络错误！");
            }
        });
    }

    // 修改部门按钮
    $("#updateDept").on("mousedown", ()=>{
        if (!hasActive()) {
            alert("您还没有选中任何部门！！");

            // 这里选用 mousedown 事件的原因是要后于 click 事件的发生，
            // 不然这里的方法执行完了，才执行 bootstrap 的显示模态框事件，
            // 这样的话，这行代码就不起作用了
            $("#updateModal").modal("hide");
            return;
        }

        const $currentDept = getActive();
        $("#updateDeptId").val($currentDept.attr("data-dept-id"));
        $("#updateDeptCode").val($currentDept.attr("data-dept-code"));
        $("#updateOldDeptCode").val($currentDept.attr("data-dept-code"));
        $("#updateDeptName").val($currentDept.attr("data-dept-name"));
        $("#updateDeptPCode").val($currentDept.attr("data-dept-p-code"));
        $("#updateDeptLevel").val($currentDept.attr("data-dept-level"));
        $("#updateDeptOrder").val($currentDept.attr("data-dept-order"));
        $("#updateDeptRemark").text($currentDept.attr("data-dept-remark"));
    });

    // 处理更新部门业务按钮
    $("#doUpdateDept").on("click", ()=>{
        updateDept($("#updateModal form").serialize());
    });

    // 获得某个部门下的用户
    function getUserInDept(deptCode, callback) {
        $.ajax({
            url: "getUsersInDepartment.do",
            type: "GET",
            data: {
                "deptCode": deptCode
            },
            success: (resp)=>{
                if (resp.code !== 0) {
                    alert(resp.msg);
                    return;
                }

                callback(resp.data);
            },
            error: ()=>{
                alert("网络错误！");
            }
        });
    }

    // 显示这个部门下的用户
    function showUsers(deptCode) {
        getUserInDept(deptCode, (users)=>{
            const table = $("#userDeptTable");
            table.empty();
            for (let i = 0; i < users.length; i++) {
                const tr =
                    `
                    <tr>
                        <td>` + users[i].username + `</td>
                        <td>` + users[i].name + `</td>
                        <td>
                            <button class="btn btn-danger btn-xs navbar-right ` + users[i].userDeptId + `" style="margin-right: 2px"><span
                                 class="glyphicon glyphicon-trash"></span> 移出部门
                            </button>
                            <button class="btn btn-primary btn-xs navbar-right ` + users[i].userDeptId + `" style="margin-right: 2px"><span
                                 class="glyphicon glyphicon-pencil"></span> 更换部门
                            </button>
                        </td>
                    </tr>
                    `;

                // 添加到表格
                table.append(tr);

                // 设置属性，设置完之后移除类名
                const tempClass = $("." + users[i].userDeptId);
                tempClass.attr({
                    "data-user-dept-id": users[i].userDeptId,
                    "data-username": users[i].username,
                    "data-name": users[i].name,
                    "data-user-id": users[i].userId,
                    "data-user-dept-order": users[i].userDeptOrder
                });
                tempClass.removeClass(users[i].userDeptId);
            }
        })
    }
});