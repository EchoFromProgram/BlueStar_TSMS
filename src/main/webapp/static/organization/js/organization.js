/* 组织部门模块脚本 */
/* @author Fish */

$(()=>{
    // 构建树状图信息
    function buildTree() {
        return [
            {
                text: "福州开发部",
                nodes: [
                    {
                        text: "Child 1",
                        nodes: [
                            {
                                text: "Grandchild 1"
                            },
                            {
                                text: "Grandchild 2"
                            }
                        ]
                    },
                    {
                        text: "Child 2"
                    }
                ]
            },
            {
                text: "深圳开发部"
            }
        ];
    }

    // 构建树状图
    $('#tree').treeview({data: buildTree()});

    setTimeout(()=>{
        const selected = $('#tree').treeview('getSelected')[0];
        if (selected) {
            sessionStorage.setItem("id", selected.nodeId);
        }

        const selectedId = sessionStorage.getItem("id");
        if (selectedId) {
            $('#tree').treeview('expandNode', selectedId);
        }
        console.log("done...");
        window.location.reload();
    }, 5000);
});