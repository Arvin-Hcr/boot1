/**
 * 定义URl
**/
var cardListUrl = "/GetAllCardServlet";
var delcardServletUrl = "/DelCardServlet";



/**
**/
$(function () {
    var a  =localStorage.getItem("staffId");
    cardManage.initList();
})
var cardManage = {};
/**
 * 加载table
**/
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
            title: "会员等级"
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
                    case 0 :
                        return "<span class='label label-danger'>禁用</span>";
                }
            }
        }, {
            field: 'operation',
            events: buttonOperateEvent,
            title: '操作',
            formatter: function (value, row, index) {
                return cardManage.buttonOption(value, row, index);
            }
        }
        ]
    });
}
/**
 * 传递参数
**/
cardManage.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchId": $("#searchId").val(),
        "searchName": $("#searchName").val()
    }
}
/*
 * @date 2019/11/20
 * 按钮源 (按钮的操作事件)
**/
window.buttonOperateEvent = {
    'click .updatecard': function (e, value, row, index) {
        //row 这一行的数据
        cardManage.update(row);
    },
    'click .delcard': function (e, value, row, index) {
        cardManage.del(row);
    }
}
/**
 * 给bootstrapTable增加按钮
**/
cardManage.buttonOption = function (value, row, index) {
    var returnButton = [];

    returnButton.push('<button class="btn btn-info updatecard">销卡</button>');
    if(row.status==1){
        returnButton.push('<button class="btn btn-danger delcard">挂失</button>');
    }else{
        returnButton.push('<button class="btn btn-success delcard">激活</button>');
    }
    return returnButton.join('');
}
/**
 * 搜索
**/
cardManage.search = function () {
    //bootstrapTable 刷新
    $("#cardList").bootstrapTable('refresh');
}
/**
 * 销卡
**/
cardManage.update = function (row) {
    $.ajax({
        url:"/UpdateCardStatusServlet",
        type:"post",
        data:{
            "cardId":row.cardId,
        },
        dataType:"json",
        success:function (result) {
            if(result.status==1){
                $("#cardList").bootstrapTable('refresh');
                cardManage.initList();
            }


        }

    })

}


/**
 *  挂失
**/
cardManage.del = function (row) {
    /**
     * 一般情况下删除要加confirm
     */
    Modal.confirm({
        msg: "确认当前操作"
    }).on(function (e) {
        if (e) {
            $.ajax({
                url: delcardServletUrl,
                type: 'post',
                data: {
                    "cardId": row.cardId,
                    "status":row.status
                },
                dataType: 'json',
                success: function (result) {
                    if (result.status == 1) {
                        toastr['success']("操作成功");
                        $("#cardList").bootstrapTable('refresh');
                    } else {
                        toastr['error']("操作失败");
                    }
                }
            })
        }
    })
}



