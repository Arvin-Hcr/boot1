/**
 * 初始列表
 */
$(function () {
    goodsList.initList();

})
/**
 * 定义函数
 */
var goodsList = {};


goodsList.initList = function () {
    $("#showGoodsList").bootstrapTable({
        url: "/GetGoodsAllServlet1", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: goodsList.queryParams, //传递参数(*)
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
            field: 'goodsId',
            title: "器材编号"

        }, {
            field: 'categoryName',
            title: "器材名称"
        }, {
            field: 'code',
            title: "空闲台数"
        }, {
            field: 'name',
            title: "器材功能"
        },  {
            field: 'price',
            title: "价格/时"
        }, /*{
            field: 'status',
            title: "状态",
            formatter: function (value) {
                if (value == 1) {
                    return '<span class="label label-success">正常</span>';
                } else {
                    return '<span class="label label-danger ">维修</span>';
                }
            }
        },*/{
            field: 'operation',
            events:buttonOperateEvent,
            title: '操作',
            formatter: function (value, row,index) {
                return goodsList.buttonOption(value, row,index);
            }
        }
        ]

    });
}

window.buttonOperateEvent = {
    'click .updateGoods': function (e, value, row, index) {
        //row 这一行的数据
        goodsList.update(row);
    },
    'click .delGoods': function (e, value, row, index) {
        goodsList.del(row);
    }
}
/**
 * 给 update/del添加了 点击事件
 */
goodsList.buttonOption = function (value, row,index) {
    var returnButton = [];
    returnButton.push('<button class="btn btn-info updateGoods">详情</button>');
   /* if (row.status == 1) {
        returnButton.push('<button class="btn btn-danger delGoods">维修</button>');
    } else {
        returnButton.push('<button class="btn btn-success delGoods">正常</button>');
    }*/
    switch (row.categoryName) {
        case 1:returnButton.push("")

    }
    return returnButton.join('');
}
/**
 参数
 */
goodsList.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchGoodsId": $("#searchGoodsId").val(),
        "searchName": $("#searchName").val(),
        "searchCode": $("#searchCode").val(),
        "searchPrice": $("#searchPrice").val(),
        "searchUnitName": $("#searchUnitName").val(),
        "searchCategoryName": $("#searchCategoryName").val(),
        "searchStatus": $("#searchStatus").val()
    }
}
/**当我们点击搜索按钮的时候 table  进行刷新并且跳转到 第一页
 */
goodsList.search = function () {
    $("#showGoodsList").bootstrapTable('refresh', {pageNumber: 1})
}

/**
 * 修改器材信息update
 */
goodsList.update = function (row) {
    $("#goodsId").val(row.goodsId);
    $("#name").val(row.name);
    $("#code").val(row.code);
    $("#categoryName").val(row.categoryId);
    $("#unitName").val(row.unitId);
    $("#price").val(row.price);

    $("#myModal").modal('show');
};
/**
 * 器材上架与下架del
 */
goodsList.del = function (row) {

    Modal.confirm({
        msg: "确认当前操作"
    }).on(function (e) {
        if (e) {
            $.ajax({
                url: "/DelGoodsServlet",
                type: 'post',
                data: {
                    "goodsId": row.goodsId,
                    "status":row.status
                },
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    if (result.status == 1) {
                        toastr['success']("修改状态成功");
                        $("#showGoodsList").bootstrapTable('refresh');
                    } else {
                        toastr['error']("修改失败哦了");
                    }
                }
            })
        }
    })
}



/**
 *  * 关闭模态框  触发操作
 * 1.将验证重置
 * 2.重置表单内容
 */


$("#myModal").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#goodsForm").data('bootstrapValidator').resetForm();
    $("#goodsForm")[0].reset();
})

/**
 * 确认按钮
 **/
goodsList.operate = function () {
    var bootstrapValidator = $("#goodsForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        $.ajax({
            url: "/UpdateGoodsServlet",
            type: "post",
            data: {
                "goodsId": $("#goodsId").val(),
                "name": $("#name").val(),
                "code": $("#code").val(),
              //  "categoryName": $("#categoryName").val(),
                "unitName": $("#unitName").val(),
                "price": $("#price").val(),

            },
            dataType: 'json',
            success: function (result) {
                if (result.data >= 1) {

                    toastr['success']("修改成功");
                    $("#showGoodsList").bootstrapTable('refresh');
                    $("#myModal").modal('hide');
                } else {
                    toastr['success']("修改失败");
                }
            }        })
    }
}

$("#goodsForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
   /* fields: {
        name: {
            validators: {
                notEmpty: {
                    message: "器材名字不能为空"
                }
            }
        },
        code: {
            validators: {
                notEmpty: {
                    message: "空闲台数不能为空"
                }
            }
        },
        categoryName: {
            validators: {
                notEmpty: {
                    message: "器材类型不能为空"
                }
            }
        },
         unitName: {
             validators: {
                 notEmpty: {
                     message: "器材单位不能为空"
                 }
             }
         },
        price: {
            validators: {
                notEmpty: {
                    message: "器材价格/时不能为空"
                }
            }
        }
    }*/
});

goodsList.initCategory=function(){
    $.ajax({
        url:"/GetGoodsCategoryServlet",
        type:"post",
        dataType:"json",
        success:function(res){
            if(res.status==1){
                for (var i = 0; i < res.data.length; i++) {
                    console.log(res.data[i]);
                    $("#categoryName").append("<option value="+res.data[i].id+">"+res.data[i].name+"</option>>")

                }
            }
        }
    })
};

window.onload=function(){
    goodsList.initCategory();
    goodsList.initUnit();
};



goodsList.initUnit=function() {
    $.ajax({
        url: "/GetAllUnitServlet",
        type: "post",
        dataType: "json",
        success: function (res) {
            if (res.status == 1) {
                for (var i = 0; i < res.data.length; i++) {
                    console.log(res.data[i]);
                    $("#unitName").append("<option value=" + res.data[i].id + ">" + res.data[i].name + "</option>>")

                }
            }
        }
    })
}








