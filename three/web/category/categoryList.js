
$(function () {
    categoryList.initList();
})


var categoryList = {};



categoryList.initList=function () {
    $("#ShowCategoryList").bootstrapTable({
        url: "/GetAllCategoryServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: categoryList.queryParams, //传递参数(*)
        queryParamsType: '',
        sidePagination: 'server', // 分页方式有两种 1.client 客户端分页  2.server分页
        pageNumber: 1, //初始化页数为第一页
        pageSize: 5, //默认每页加载行数
        pageList:[5,10, 25, 50],//每页可选择记录数
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
                    'rows': result.data.list.list //所有的数据
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
            title: "类别编号"
        }, {
            field: 'name',
            title: "类别名称"
        }, {
            field: 'momo',
            title: "备注"
        },{
            field: 'status',
            title: "类别状态",
            formatter: function (value) {
                switch (value) {
                    case 1:
                        return "<span class='label label-info'>启用</span>";
                    case 0:
                        return "<span class='label label-danger'>禁用</span>";
                }
            }
        }, {
            field: 'operation',
            events: buttonOperateEvent,
            title: '操作',
            formatter: function (value, row, index) {
                return categoryList.buttonOption(value, row, index);
            }
        }
        ]
    });

}



categoryList.queryParams=function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize, //每页条数
        "searchId": $("#searchId").val(),
        "searchName": $("#searchName").val()
    }
}


categoryList.search=function () {
    $("#ShowCategoryList").bootstrapTable('refresh', {pageNumber: 1})
}


window.buttonOperateEvent={
    'click .updateNews':function (e,value,row,index) {
        categoryList.update(row);
    },
    'click .delNews':function (e,value,row,index) {
        categoryList.del(row);
    }
}


categoryList.buttonOption =function (value,row,index) {
    var returnButton=[] ;
    returnButton.push('<button Class="btn btn-info updateNews">修改</button>');
    returnButton.push('<button Class="btn btn-danger delNews">删除</button>');
    return returnButton.join('');
}



/**
 * 修改
*/
categoryList.update=function (row) {
    $("#categoryId").val(row.id);
    $("#categoryName").val(row.name);
    $("#momo").val(row.momo);
    $("#status").val(row.status);
    $("#myModal").modal('show');
}


/**
 * 删除
*/
categoryList.del = function (row) {
    /**
     * 一般情况下删除要加confirm
     */
    Modal.confirm({
        msg: "确认当前操作"
    }).on(function (e) {
        if (e) {
            $.ajax({
                url: "/DelCategoryServlet",
                type: 'post',
                data: {
                    "id": row.id
                },
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    if (result.id == null) {
                        toastr['success']("删除成功");
                        $("#ShowCategoryList").bootstrapTable('refresh');
                    } else {
                        toastr['error']("删除失败");
                    }
                }
            })
        }
    })
}

/**
 * 关闭模态框
*/

$("#myModal").on('hide.bs.modal', function () {
    //移除上次的校验配置
    $("#categoryForm").data('bootstrapValidator').resetForm();
    $("#categoryForm")[0].reset();
})


/**
 * 修改
 */
categoryList.update=function (row) {
    $("#categoryId").val(row.id);
    $("#categoryName").val(row.name);
    $("#momo").val(row.momo);
    $("#status").val(row.status);
    $("#myModal").modal('show');
}

/**修改
*/
categoryList.operate = function () {
    var bootstrapValidator = $("#categoryForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        $.ajax({
            url: "/UpdateOrAddCategoryServlet",
            type: 'post',
            data: {
                "id": $("#categoryId").val(),
                "name": $("#categoryName").val(),
                "status": $("#status").val(),
                "momo": $("#momo").val(),
            },
            dataType: 'json',
            success: function (result) {
                if (result.status > 0) {
                    toastr['success']("操作成功");
                    $("#ShowCategoryList").bootstrapTable('refresh');
                    $("#myModal").modal('hide');
                } else {
                    toastr['error']("操作失败");
                }
            }
        })
    }
}


categoryList.add=function () {
    $("#myModal").modal('show');
}
/**
 * 验证
*/
$("#categoryForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        name: {
            validators: {
                notEmpty: {
                    message: "类型名不能为空"
                }
            }
        },
        momo: {
            validators: {
                notEmpty: {
                    message: "备注不能为空"
                }
            }
        },
        status: {
            validators: {
                notEmpty: {
                    message: "状态不能为空"
                }
            }
        }
    }
});



