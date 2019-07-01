<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/6/21
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="Css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript" src="js/street.js"></script>
</head>
<body>
<table id="dg"></table>
<div id="ToolBar">
    <div style="height: 40px;">
        <a
                href="javascript:Add()" class="easyui-linkbutton"
                iconCls="icon-add" plain="true">添加</a>
        <a
                href="javascript:ModifyBySelect()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a>
        <a
                href="javascript:delMore()" class="easyui-linkbutton"
                iconCls="icon-remove" plain="true">批量删除</a>
    </div>
</div>
<!--添加窗口-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="saveDialogForm" method="post">
        <table>
            <tr>
                <td>名称:</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>区号:</td>
                <td><input type="text" name="districtId"/></td>
            </tr>
        </table>
    </form>
</div>
<!--修改对话框-->
<div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="upDialogForm" method="post">
        <table>
            <tr>
                <td>编号</td>
                <td><input type="text" readonly name="id" id="id1"></td>
            </tr>
            <tr>
                <td>名称</td>
                <td><input type="text" name="name" id="name1"/></td>
            </tr>
            <tr>
                <td>区号</td>
                <td><input type="text" name="districtId" id="did"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('AddDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<div id="upDialogButtons">
    <a href="javascript:SaveDialog2()" class="easyui-linkbutton"
       iconCls="icon-ok">修改</a>
    <a href="javascript:CloseDialog('upDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
