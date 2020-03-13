/**
 * 初始化
 */
$(function () {
    rechargeManager.initList();
})
/**
 * 定义函数
 */
var rechargeManager = {} ;
/**
 * 加载列表
 */
rechargeManager.initList = function () {
    $("#userList").bootstrapTable({
        url: "/GetAllUserServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: rechargeManager.queryParams, //传递参数(*)
        queryParamsType: '',
        sidePagination: 'server', // 分页方式有两种 1.client 客户端分页  2.server分页
        pageNumber: 1, //初始化页数为第一页
        pageSize: 5, //默认每页加载行数
        pageList: [5,10, 25, 50], //每页可选择记录数
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
            console.log(result.data.userlist);
            console.log(result.data.count);
            if (result != null) {
                return {
                    'total': result.data.count, //总条数
                    'rows': result.data.userlist //所有的数据
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
        }, {
            field: 'userId',
            title: "会员Id"
        }, {
            field: 'userName',
            title: "会员名字"
        }, {
            field: 'phone',
            title: "会员电话"
        }, {
            field: 'status',
            title: "状态",
            formatter: function (value) {
                switch (value) {
                    case 1 :
                        return "<span class='label label-info'>启用</span>";
                    case 0 :
                        return "<span class='label label-danger'>禁用</span>";
                }
            }
        }, {
            field: 'idCard',
            title: "会员身份证号"
        }, {
            field: 'birthday',
            title: "会员生日"
        },{
            field: 'sex',
            title: "性别",
            formatter: function (value) {
                switch (value) {
                    case 1 :
                        return "<span class='label label-info'>男</span>";
                    case 0 :
                        return "<span class='label label-danger'>女</span>";
                }
            }
        },{
            field: 'address',
            title: "会员地址"
        },{
            field: 'area',
            title: "详细地址"
        },{
            field: 'createdTime',
            title: "加入时间"
        },{
            field: 'cardId',
            title: "会员卡号"
        },{
            field: 'momo',
            title: "备注"
        },{
            field: 'amount',
            title: "会员卡余额"
        },{
            field: 'credit',
            title: "会员积分"
        },{
            field: 'levelName',
            title: "会员等级"
        },{
            field: 'operation',
            events: buttonOperateEvent,
            title: '操作',
            formatter: function (value, row, index) {
                return rechargeManager.buttonOption(value, row, index);
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
        rechargeManager.update(row);
    },
    'click .delRule': function (e, value, row, index) {
        rechargeManager.del(row);
    }
}

/**
 * 给操作添加 对象 button
 **/
rechargeManager.buttonOption = function (value, row, index) {
    var returnButton = [];
    returnButton.push('<button class="btn btn-info updateRule">修改</button>');
    if (row.status == 1) {
        returnButton.push('<button class="btn btn-danger delRule">禁用</button>');
    } else {
        returnButton.push('<button class="btn btn-success delRule">启用</button>');
    }
    return returnButton.join('');
}
/**
 * 参数
 **/
rechargeManager.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchId": $("#searchId").val(),
        "searchName": $("#searchName").val()
    }
}
/**
 * 当我们点击搜索按钮的时候 table  进行刷新并且跳转到 第一页
 **/
rechargeManager.search = function () {
    $("#userList").bootstrapTable('refresh', {pageNumber: 1});
    $("#searchId").val("");
    $("#searchName").val("");
}




/**
 * 验证
**/
$("#userForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        userName: {
            validators: {
                notEmpty: {
                    message: "会员名字不能为空"
                }
            }
        },
        userPhone: {
            validators: {
                notEmpty: {
                    message: "会员电话不能为空"
                }
            }
        },
        userIdCard: {
            validators: {
                notEmpty: {
                    message: "充值系数不能为空"
                },
                regexp:{
                    regexp:/^[0-9]{17}[0-9|X|x]$/,
                    messages:"身份证号不符合规则"
                }
            }
        }
    }
});
/**
 * 关闭模态框  触发操作
 * 1.将验证重置
 * 2.重置表单内容
 **/
$("#myModal").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#userForm").data('bootstrapValidator').resetForm();
    $("#userForm")[0].reset();
})
/**
 * 规则修改
 **/
rechargeManager.update = function (row) {
    $("#myModal").modal('show');
    $("#userId").val(row.userId);
    $("#userName").val(row.userName);
    $("#userPhone").val(row.phone);
    $("#userStatus").val(row.status);
    $("#userIdCard").val(row.idCard);
    $("#userBirthday").val(row.birthday);
    $("#userSex").val(row.sex);
    $("#userAddress").val(row.address);
    $("#userMomo").val(row.momo);
    $("#createTime").val(row.createdTime);
    $("#levelName").val(row.levelName);
    $("#area").val(row.area);
};
/**
 * 确认修改
 **/
rechargeManager.operate = function () {
    var bootstrapValidator = $("#userForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        $.ajax({
            url: "/UpdateUserServlet",
            type: "post",
            data: {
                "userId": $("#userId").val(),
                "userName":$("#userName").val(),
                "userPhone": $("#userPhone").val(),
                "userStatus":$("#userStatus").val(),
                "userIdCard":$("#userIdCard").val(),
                "userBirthday":$("#userBirthday").val(),
                "userSex":$("#userSex").val(),
                "userAddress":$("#userAddress").val(),
                "userMomo":$("#userMomo").val(),
                "createTime":$("#createTime").val(),
                "levelName":$("#levelName").val(),
                "area":$("#area").val()
            },
            dataType: 'json',
            success: function (result) {
                console.log(result);
                if (result.data>0) {
                    toastr['success']("修改成功");
                    $("#userList").bootstrapTable('refresh');
                    $("#myModal").modal('hide');

                } else {
                    toastr['success']("修改失败");
                }
            }
        })
    }
}
/**
 * 规则删除
 **/
rechargeManager.del = function (row) {
    Modal.confirm({
        msg: "确认当前操作"
    }).on(function (e) {
        if (e) {
            $.ajax({
                url: "/DelUserServlet",
                type: "post",
                data: {
                    "userId": row.userId
                },
                dataType: 'json',
                success: function (result) {
                    if (result.data >0) {
                        toastr['success']("操作成功");
                        $("#userList").bootstrapTable('refresh');
                    } else {
                        toastr['success']("操作失败");
                    }
                }
            })
        }
    })
}