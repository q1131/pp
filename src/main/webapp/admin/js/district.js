$(function () {
    $("#dg").datagrid({
        url:'/district/getDistrict',
        fitColumns: true,
        pagination: true,
        pageList: [5, 10, 15, 20],
        toolbar:"#ToolBar",
        pageSize:5,
        columns: [[
            {field:'ck',checkbox:true},  //复选框列
            { field: 'id', title: '编号', width: 50, align: "center" },
            { field: 'name', title: '名称', width: 50, align: "center" },
            { field: 'opt', title: '操作', width: 50, align: "center" ,formatter: function(value,row,index){
                    return "<a href='javascript:delSingle("+row.id+")'>删除</a>|<a href='javascript:openStreetDialog("+row.id+")'>查看街道</a>";
                }}
        ]]
    })
})
function Add() {
    $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
}
function CloseDialog() {
    $("#AddDialog").dialog("close");
}
function SaveDialog() {
    $("#saveDialogForm").form("submit", {
        url: '/district/addDistrict',
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
        url:"/district/upDistrict",
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
    $.post("/district/delMoreDistrict",{"ids":delValue},function(data){
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
function delSingle(id) {
    $.messager.confirm('确认一下','确认删除',function(r){
        if (r){
            $.post("/district/delDistrict",{"id":id},function (data) {
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
function openStreetDialog(obj) {
    //显示对话框
    $("#aa").dialog("open").dialog('setTitle',"街道信息");
    //加载对话框中展示的街道  发请求显示数据
        alert(obj)
    $('#streetDg').datagrid({
        title:">>>>街道信息",
        url:'/street/selectByDid?did='+obj,
        fitColumns: true,
        //queryParams: {"did": obj},  //发送参数
        pagination: true,
        pageList: [5, 10, 15, 20],
        //toolbar:"#ToolBar",
        //singleSelect:true,  //只能设置单选
        pageSize:5,
        columns: [[
            {field:'ck',checkbox:true},  //复选框列
            { field: 'id', title: '编号', width: 50, align: "center" },
            { field: 'name', title: '名称', width: 50, align: "center" },
            { field: 'opt', title: '操作', width: 50, align: "center",
                formatter: function(value,row,index){
                    //同步跳转 "<a href='delDistrict?id="+row.id+"'>删除</a>"
                    return "<a href='javascript:delSingle(\"+row.id+\")'>删除</a> | <a href='javascript:openStreetDialog(\"+row.id+\")'>查看街道</a>";
                }
            }
        ]]
    });

    //将区域编号存于表单中,以便后期添加街道
    $("#districtId").val(obj);
}