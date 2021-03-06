/**

 * 初始化
 **/
$(function () {
    roleManager.initList();
})

/**

 * 定义函数
 **/
var roleManager = {};

/**

 * 加载列表
 **/
roleManager.initList = function () {
    $("#roleRuleList").bootstrapTable({
        url: "/RoleMangerServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: roleManager.queryParams, //传递参数(*)
        queryParamsType: '',
        sidePagination: 'server', // 分页方式有两种 1.client 客户端分页  2.server分页
        pageNumber: 1, //初始化页数为第一页
        pageSize: 5, //默认每页加载行数
        pageList: [10, 25, 50, 100], //每页可选择记录数
        strictSearch: true,
        showColumns: false, // 是否显示所有的列
        showRefresh: false, // 是否显示刷新按钮
        minimumCountColumns: 2, // 最少允许的列数
        clickToSelect: true, // 是否启用点击选中行
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        showToggle: false, // 是否显示详细视图和列表视图的切换按钮
        cardView: false, // 是否显示详细视图
        detailView: false, // 是否显示父子表
        smartDisplay: false,
        onClickRow: function (e, row, element) {
            $(".success").removeClass("success");
            $(row).addClass("success");
        },
        responseHandler: function (result) {
            if (result != null) {
                return {
                    'total': result.data.count, //总条数
                    'rows': result.data.list //所有的数据
                };
            }
            return {
                'total': 0, //总条数
                'rows': [] //所有的数据
            }
        },
        //列表显示
        columns: [{
            field: 'id',
            title: "编号",
            visible: false
        },{
            field: 'roleName',
            title: "职位名称"
        }, {
            field: 'description',
            title: "描述"
        }, {
            field: 'status',
            title: "状态",
            formatter: function (value) {
                if (value == 1) {
                    return '<span class="label label-info">启用</span>';
                }else{
                    return '<span class="label label-danger">禁用</span>';
                }
            }
        }, {
            field: 'operation',
            events: buttonOperateEvent,
            title: '操作',
            formatter: function (value, row, index) {
                return roleManager.buttonOption(value, row, index);
            }
        }
        ]
    });
}

/**
 * 添加事件  给 updateRule/delRule  添加了 点击事件
 **/
window.buttonOperateEvent = {
    'click .updateRule': function (e, value, row, index) {
        //row 这一行的数据
        roleManager.update(row);
    },
    'click .delRule': function (e, value, row, index) {
        roleManager.del(row);
    },
    'click .roleTree': function (e, value, row, index) {
        roleManager.roleTree(row);
    }

}

/**

 * 给操作添加 对象 button
 **/
roleManager.buttonOption = function (value, row, index) {
    var returnButton = [];
    returnButton.push('<button class="btn btn-info updateRule">修改</button>');
    if (row.status == 1) {
        returnButton.push('<button class="btn btn-danger delRule">禁用</button>');
    } else {
        returnButton.push('<button class="btn btn-success delRule">启用</button>');
    }
    returnButton.push('<button class="btn btn-group roleTree">权限修改</button>');
    return returnButton.join('');
}



/**
 *
 * 传递参数
 **/
roleManager.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber,
        "pageSize": params.pageSize,
        "roleId":localStorage.getItem("roleId"),
        "searchName": $("#searchName").val()
    }
}

/**
 *
 * 搜索按钮
 **/
roleManager.search = function () {
    //重新加载 bootstrap table
    $("#roleRuleList").bootstrapTable('refresh', {pageNumber: 1})
}

/**
 * 这个是添加角色
 */
roleManager.add=function(){
    $("#myModal").modal('show');
}

/**
 * 点击修改时模态框添加
 **/
roleManager.update = function (row) {
    $("#myModal").modal('show');
    $("#Id").val(row.id);
    $("#roleName").val(row.roleName);
    $("#miao").val(row.description);
    $("#roleStatusl").val(row.status);


};

/**
 * 规则删除
 **/
roleManager.del = function (row) {
    Modal.confirm({
        msg: "确认当前操作"
    }).on(function (e) {
        if (e) {
            $.ajax({
                url: "/DelRoleIDServlet",
                type: "post",
                data: {
                    Id:row.id
                },
                dataType: "json",
                success: function (result) {
                    if (result.status==1) {
                        toastr['success']("修改成功");
                        $("#roleRuleList").bootstrapTable('refresh');
                    } else {
                        toastr['error']("修改失败");
                    }
                }
            })
        }
    })
}

/**
 * 这个权限修改
 * @param row
 */
roleManager.roleTree=function(row){
    $("#myRoleModal").modal('show');
    roleManager.initTree(row.id);

}

// var tree = [
//     {
//         text: "Parent 1",
//         state: {
//             checked: "122456",
//         },
//         nodes: [
//             {
//                 text: "Child 1",
//                 nodes: [
//                     {
//                         text: "Grandchild 1"
//                     },
//                     {
//                         text: "Grandchild 2"
//                     }
//                 ]
//             },
//             {
//                 text: "Child 2"
//             }
//         ]
//     },
//     {
//         text: "Parent 2"
//     },
//     {
//         text: "Parent 3"
//     },
//     {
//         text: "Parent 4"
//     },
//     {
//         text: "Parent 5"
//     }
// ];

var roleId="";  //这是用来传递角色id便于操作
roleManager.initTree=function(id){
    roleId=id;
    $.ajax({
        url: "/MenTreeServlet",
        type: "post",
        data:{
            id:id
        },
        dataType: "json",
        success: function (result) {
            if (result.status==1) {
                console.log(result.data);
                $("#tree").treeview({
                    levels: 1,//设置继承树的等级
                    expandIcon: 'glyphicon glyphicon-chevron-right',//设置列表树展开的图标
                    collapseIcon: 'glyphicon glyphicon-chevron-down',//设置列表树收缩的图标
                    selectedBackColor: false,
                    selectedColor: '#337AB7',
                    showCheckbox: true,
                    multiSelect: true,
                    data:result.data,

                    onNodeChecked: function (event, node) { //选中节点
                        var selectNodes = getChildNodeIdArr(node); //获取所有子节点
                        if (selectNodes) { //子节点不为空，则选中所有子节点
                            $('#tree').treeview('checkNode', [selectNodes, {
                                silent: true
                            }]);
                        }
                        $("#tree").treeview("getNode", node.parentId);
                        setParentNodeCheck(node);
                    },
                    onNodeUnchecked: function (event, node) {//取消选中节点
                        var selectNodes = getChildNodeIdArr(node); //获取所有子节点
                        if (selectNodes) { //子节点不为空，则取消选中所有子节点
                            $('#tree').treeview('uncheckNode', [selectNodes, {
                                silent: true
                            }]);
                        }
                    }

                });
            } else {
                toastr['error']("请求失败");
            }
        }
    })
}


/**
 * 设置父节点
 * @param {Object} node
 */
function setParentNodeCheck(node) {
    var parentNode = $("#tree").treeview("getNode", node.parentId);
    if (parentNode.nodes) {
        var checkedCount = 0;
        for (x in parentNode.nodes) {
            if (parentNode.nodes[x].state.checked) {
                checkedCount ++;
            } else {
                break;
            }
        }
        if (checkedCount === parentNode.nodes.length) {
            $("#tree").treeview("checkNode", parentNode.nodeId);
            setParentNodeCheck(parentNode);
        }
    }
}

/**
 * 获取所有的字节点
**/
function getChildNodeIdArr(node) {
    var ts = [];
    if (node.nodes) {
        for (x in node.nodes) {
            ts.push(node.nodes[x].nodeId);
            if (node.nodes[x].nodes) {
                var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
                for (j in getNodeDieDai) {
                    ts.push(getNodeDieDai[j]);
                }
            }
        }
    } else {
        ts.push(node.nodeId);
    }
    return ts;
}

/**
 * 这个是修改权限
 * 修改权限我只需要修改权限——角色表
 */
roleManager.updateTree=function(){
    /**
     * 这个是获取所有选中的节点信息
     * @type {jQuery|*}
     */
    var getNodes = $('#tree').treeview('getChecked');
   console.log(getNodes);
    var TreeNodes=new Array();
    for(var i=0;i<getNodes.length;i++){
        TreeNodes[i]=getNodes[i].nodeid;
    }
    Modal.confirm({
        msg:"确定当前操作"
    }).on(function (e) {
        if(e){
            $.ajax({
                url:"/UpdateTreeServlet",
                type:'post',
                data:{
                    'roleId':roleId,
                    'getNodeList':JSON.stringify(TreeNodes)
                },
                dataType:'json',
                success:function(result){
                    if(result.status==1){
                        $("#myRoleModal").modal('hide');
                        $("#roleRuleList").bootstrapTable('refresh');
                        toastr['success']("设置成功");
                    }else{
                        toastr['error']("设置失败");
                    }
                }
            });
        }
    })
}


/**
 * 关闭模态框  触发操作
 * 1.将验证重置
 * 2.重置表单内容
 **/
$("#myModal").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#ruleForm").data('bootstrapValidator').resetForm();
    $("#ruleForm")[0].reset();
})

/**
 * 模态框确认提交按钮
 **/
roleManager.operate = function () {
    var bootstrapValidator = $("#ruleForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        $.ajax({
            url: "/UpdateRoleServlet",
            type: "post",
            data: {
                roleid:$("#Id").val(),
                roleName:$("#roleName").val(),
                miao:$("#miao").val(),
                roleStatus:$("#roleStatusl").val()
            },
            dataType:"json",
            success: function (result) {
                if (result.status == 1) {
                    toastr['success']("操作成功");
                    $("#roleRuleList").bootstrapTable('refresh');
                    $("#myModal").modal('hide');
                } else {
                    toastr['error'](result.message);
                }
            }
        })
    }
}

/**
 * 验证
 **/
$("#ruleForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        roleName: {
            validators: {
                notEmpty: {
                    message: "角色名称不能为空"
                }
            }
        },
        miao: {
            validators: {
                notEmpty: {
                    message: "角色名称不能为空"
                },
                stringLength: {
                    min:3,
                    max:6,
                    message: '描述长度在3-6之间'
                }
            }
        },
        staffIdcard: {
            validators: {
                notEmpty: {
                    message: "身份证号不能为空"
                }
            }
        },


    }
});
