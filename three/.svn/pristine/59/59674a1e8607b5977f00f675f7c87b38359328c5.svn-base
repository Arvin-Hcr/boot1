var GetCardInfoByIdServlet = "/GetCardByCardIdServlet";
var cardListUrl = "/GetAllCardServlet";
var initRoleUrl = "/GetCardAllRuleServlet";

var initRechargeRuleUrl="/GetRechargeRuleServlet";

var oderListUrl = "/GetOrderByIdServlet";

var rechargeCardOrder = "/GetRechargerecordBycardIdServlet";

/**
根据选中的会员获取会员信息
 */
var getUserAllUrl = "/GetAllUserServlet";
/**
 * 初始化
**/
$(function () {
    cardManage.initList();
})
var cardManage = {};


cardManage.search = function () {
    $.ajax({
        url: GetCardInfoByIdServlet,
        type: 'post',
        data: {
            "cardId": $("#serchCardId").val(),
        },
        dataType: 'json',
        success: function (result) {
            console.log(result);
            if(result.status==0){
                toastr['error']("此卡已销毁");
            }else {
                if (result.data == 1) {
                    toastr['error']("账号未找到");
                } else {
                    if (result.data[0].status == 1) {
                        toastr['success']("读卡成功");
                        $("#userId").val(result.data[0].userId);
                        $("#userName").val(result.data[0].userName);
                        $("#amout").val(result.data[0].amount);
                        $("#cardId").val(result.data[0].cardId);
                        $("#cardLevel").val(result.data[0].levelName);
                        $("#credit").val(result.data[0].credit);
                        getUserInfoById(result.data[0].userId);
                        cardManage.initList1();
                        cardManage.initList2();
                    } else if (result.data[0].status == 0) {
                        toastr['error']("此卡已挂失");
                    }
                }
            }
        }
    })
}

function getUserInfoById (ros){
    $.ajax({
        url:getUserAllUrl,
        type:'post',
        data: {
            "pageNumber":1,
            "pageSize":100000,
            "searchId":ros,
            "searchName":$("#userName").text()
        },
        dataType:'json',
        success:function (result) {
            console.log(result.data.userlist[0].sex);
            var sexShow = result.data.userlist[0].sex;
            if (sexShow == 1){
                sexShow = "男";
            }
            else {
                sexShow = "女";
            }
            var statusShow = result.data.userlist[0].status;
            if (statusShow == 1){
                statusShow ="正常";
            }else if(statusShow == 0){
                statusShow = "挂失";
            }else if(statusShow == 2){
                statusShow = "冻结";
            }
            $("#userIdShow").text(result.data.userlist[0].userId),
            $("#userNameShow").text(result.data.userlist[0].userName),
            $("#phoneShow").text(result.data.userlist[0].phone),
            $("#adressShow").text(result.data.userlist[0].address),
            $("#idNoShow").text(result.data.userlist[0].idCard),
            $("#statusShow").text(statusShow),
            $("#cardIdShow").text(result.data.userlist[0].cardId),
            $("#amountShow").text(result.data.userlist[0].amount),
            $("#cardLevelShow").text($("#cardLevel").val()),
            $("#sexShow").text(sexShow)
        }
    })
}

cardManage.initList = function () {
    $("#cardList").bootstrapTable({
        url: cardListUrl, //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: cardManage.queryParams, //传递参数(*)
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
                    'rows': result.data.cardlist //所有的数据
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
            field: 'cardId',
            title: "会员卡号"
        }, {
            field: 'userName',
            title: "持卡人姓名"
        }, {
            field: 'userId',
            title: "会员ID"
        }, {
            field: 'levelName',
            title: "会员等级",
        }, {
            field: 'amount',
            title: "卡余额"
        }, {
            field: 'credit',
            title: "会员积分"
        }, {
            field: 'status',
            title: "状态",
            formatter: function (value) {
                switch (value) {
                    case 1 :
                        return "<span class='label label-info'>启用</span>";
                    case 0:
                        return "<span class='label label-danger'>禁用</span>";
                }
            }
        }
        ]
    });
}
/*
 * 双击表格加载 指定区域的数据
**/
$("#myModal").on('dbl-click-row.bs.table', function (e, row) {
    if(row.status==1){
        $("#cardId").val(row.cardId);
        $("#userName").val(row.userName);
        $("#userId").val(row.userId);
        $("#amout").val(row.amount);
        $("#cardLevel").val(row.levelName);
        $("#credit").val(row.credit);
        $("#myModal").modal('hide');
        getUserInfoById(row.userId);
        cardManage.initList1();
        cardManage.initList2();
        toastr['success']("读卡成功");
    }else if(row.status==2){
        toastr['error']("此卡已经销毁");
    }else{
        toastr['error']("此卡已被挂失");
    }
})

cardManage.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchOderId": "",
        "searchName": ""
    }
}

cardManage.queryParams1 = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchOderId": "",
        "cardId": $("#cardId").val()
    }
}
cardManage.queryParams2 = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "cardId": $("#cardId").val(),
        "searchName": ""
    }
}



/*
 * 加载充值规则
**/
cardManage.initRole = function () {
    $.ajax({
        url: initRoleUrl,
        type: 'get',
        dataType: 'json',
        success: function (result) {
            if (result.status == 1) {
                var res = result.data;
                for (var i = 0; i < res.length; i++) {
                    var opt = $("<option value='" + res[i].id + "'>" + res[i].Name + "</option>");
                    $("#ruleId").append(opt);
                }
            }
        }
    })
}

/*
加载会员详情
 */
cardManage.show=function(){
    $("#myModal").modal('show');
    cardManage.initList();
}




/*
 * 关闭模态框
**/
$("#myModal1").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#cardForm").data('bootstrapValidator').resetForm();
    $("#cardForm")[0].reset();
})



cardManage.initList1 = function () {
    $("#orderList").bootstrapTable({
        url: oderListUrl, //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: cardManage.queryParams1, //传递参数(*)
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
            title: "序号",
            visible: false
        }, {
            field: 'orderId',
            title: "订单编号"
        }, {
            field: 'cardId',
            title: "会员卡号"
        },  {
            field: 'levelName',
            title: "会员等级"
        }, {
            field: 'price',
            title: "应付金额"
        },{
            field: 'pay',
            title: "实付金额"
        }, {
            field: 'credit',
            title: "商品积分"
        }, {
            field: 'status',
            title: "订单状态",
            formatter: function (value) {
                switch (value) {
                    case 0 :
                        return "未核验";
                    case 1 :
                        return "已核验";
                }
            }
        },
            {
                field: 'momo',
                title: "备注"
            }, {
                field: 'createdTime',
                title: "创建时间"
            }
        ]
    });
}



cardManage.initList2 = function () {
    $("#rechargeList").bootstrapTable({
        url: rechargeCardOrder, //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: cardManage.queryParams2, //传递参数(*)
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
            console.log(result);
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
            title: "序号",
            visible: false
        }, {
            field: 'userName',
            title: "会员姓名"
        }, {
            field: 'cardId',
            title: "会员卡号"
        },  {
            field: 'rechargeAmount',
            title: "充值金额"
        }, {
            field: 'afterAmount',
            title: "充值后金额"
        },{
            field: 'beforeAmount',
            title: "充值前金额"
        }, {
            field: 'ruleId',
            title: "充值规则"
        }, {
                field: 'momo',
                title: "备注"
            }, {
                field: 'createdTime',
                title: "创建时间"
            }
        ]
    });
}

