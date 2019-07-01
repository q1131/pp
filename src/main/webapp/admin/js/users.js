/**
 * 获取表格
 */
$(function () {
    $("#dg").datagrid({
        url:'/user/getUsers',
        fitColumns: true,
        pagination: true,
        pageList: [2,3],
        toolbar:"#ToolBar",
        pageSize:5,
        columns: [[
            {field:'ck',checkbox:true},  //复选框列
            { field: 'id', title: '编号', width: 50, align: "center" },
            { field: 'name', title: '名字', width: 50, align: "center" },
            { field: 'age', title: '年龄', width: 50, align: "center" },
            { field: 'password', title: '密码', width: 50, align: "center" },
            { field: 'telephone', title: '电话', width: 50, align: "center"}
        ]]
    })
})
/**
 *
 */
function Add() {
    $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
}
function CloseDialog() {
    $("#AddDialog").dialog("close");
}
function SaveDialog() {
    $("#saveDialogForm").form("submit", {
        url: '/user/insertUser',
        success: function (data) {
            var obj = $.parseJSON(data)
            if (obj.result > 0) {
                $("#AddDialog").dialog("close")
                $.messager.alert("提示框","添加成功")
            } else {
                $.messager.alert("提示框","功能升级中...")
            }
        }
    })
}
function ModifyBySelect() {
    var SelectRows=$("#dg").datagrid("getSelections");
    if(SelectRows.length!=1){
        $.messager.alert('提示框','你还没有选中行，或者选择了多行');
        return;
    }
    var SelectRow = SelectRows[0];
    $("#upDialog").dialog("open").dialog('setTitle',"修改区域");
    $("#upDialogForm").form('load',SelectRow);
}
function SaveDialog2() {
    $("#upDialogForm").form("submit",{
        url:"/District/updateByPrimaryKey",
        success:function (data) {
            var obj1 = $.parseJSON(data)
            if (obj1.result > 0) {
                $("#upDialog").dialog("close")
                $.messager.alert("提示框","修改成功")
            } else {
                $.messager.alert("提示框","功能升级中...")
            }
        }
    })
}
function delMore() {
    var SelectRows =$("#dg").datagrid('getSelections')
    if(SelectRows.length==0){
        $.messager.alert('提示框','你还没有选中行');
        return;
    }
    alert(SelectRows.length)
    var delValue='';
    for(var i=0;i<SelectRows.length;i++){
        delValue=delValue+SelectRows[i].id+","
    }
    var s = delValue.substring(0,delValue.length-1);
    $.post("/District/delMoreDistrict",{"ids":delValue},function(data){
        if(data.result>0){
            $("#dg").datagrid('reload'); //刷新
            $.messager.alert('提示框','恭喜你成功删除'+data.result+'行!');
        }
        else
        {
            $.messager.alert('提示框','系统正在维护!');
        }
    },"json");
}
/**
 * 删除单条
 * @param id
 */
function delSingle(id) {
    $.messager.confirm('确认一下','确认删除',function(r){
        if (r){
            $.post("/District/deleteByPrimaryKey",{"id":id},function (data) {
                if(data.result>0){
                    $("#dg").datagrid('reload');
                }else
                {
                    $.messager.alert('提示框','系统正在维护!');
                }
            },"json");
        }
    });
}

/**
 * 用户条件查询
 */
function searchUser() {
    var $telephone=$("#tel").val();
    var $startAge=$("#startAge").val();
    var $endAge=$("#endAge").val();
    $("#dg").datagrid("load",{"telephone":$telephone,"startAge":$startAge,"endAge":$endAge});
}