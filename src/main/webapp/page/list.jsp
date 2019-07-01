<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
  <META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <LINK rel=stylesheet type=text/css href="../css/style.css">
  <META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" src="../admin/js/jquery-1.8.3.js"></script>
<script language="JavaScript">
$(function () {
  $.post("/protalType/getType",null,function (data) {
      for(var i=0;i<data.length;i++){
          var option=$("<option value="+data[i].id+">"+data[i].name+"</option>")
          $("#typeid").append(option)
      }
      if(${condition.tid!=null}){
          $("#typeid").val(${condition.tid});
      }
  },"json")
    $.post("/page/getAllDistrict",null,function (data) {
        for(var i=0;i<data.length;i++){
            var option= $("<option value="+data[i].id+">"+data[i].name+"</option>");
            $("#districtid").append(option);
        }
        if(${condition.did!=null}){
            $("#districtid").val(${condition.did});
        }
    },"json");
    $("#districtid").change(function(){
        var did=$(this).val();
        showStreet(did);
    });
});
function showStreet(did){
    $.post("/stre/qq",{"did":did},function(data){
        $("#streetid>option:gt(0)").remove();
        for(var i=0;i<data.length;i++){
            var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
            $("#streetid").append(option);
        }
        if(${condition.sid!=null}){
            $("#streetid").val(${condition.sid});
        }
    },"json");
}
function goPage(num){
    $("#showPage").val(num);
    $("#form").submit();
}
</script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
  <DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
  <DL class="search clearfix">
    <FORM id=form method=post action=/house/goList>

      <input type="text" id="showPage" value="1" name="page">

      标题：<INPUT class=text type=text name=title value="${condition.title}">
      开始价格:<input type="text" name="startPrice" value="${condition.startPrice}" size="10">结束价格:<input size="10" value="${condition.endPrice}" type="text" name="endPrice">

      区域:<SELECT id=districtid name=did>
      <OPTION selected value="">请选择</OPTION>
    </SELECT>
      街道: <SELECT id=streetid name=sid>
      <OPTION selected  value="">请选择</OPTION>
    </SELECT>
      <br/>
      类型:<SELECT name=tid id="typeid">
      <OPTION selected  value="">请选择</OPTION>
    </SELECT>

      面积:<SELECT id="flooa" name=flooa> <OPTION selected  value="">不限</OPTION> <OPTION
            value="0-40">40以下</OPTION> <OPTION value="41-80">41-80</OPTION>
      <OPTION value="81-500">81-500</OPTION>
    </SELECT>
      <script language="JavaScript">
          $("#flooa").val(${condition.flooa});
      </script>

      <LABEL class=ui-blue><INPUT  value=搜索房屋 type=submit name=search></LABEL>
    </FORM></DL></DIV>
<DIV class="main wrap">
  <TABLE class=house-list>
    <TBODY>
    <c:forEach items="${pageInfo.list}" var="h">
      <TR>
        <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="http://localhost:80/${h.path}" width="100" height="75" alt=""></a></span></TD>
        <TD>
          <DL>
            <DT><A href="details.jsp" target="_blank">${h.title}</A></DT>
            <DD>${h.dname}=${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
        <TD class=house-type>${h.tname}</TD>
        <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD>
      </TR>
    </c:forEach>
    </TBODY></TABLE>
  <DIV class=pager>
    <UL>
      <LI class=current><A href="javascript:goPage(1);">首页</A></LI>
      <LI><A href="javascript:goPage(${pageInfo.prePage==0?1:pageInfo.prePage});">上一页</A></LI>
      <LI><A href="javascript:goPage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage});">下一页</A></LI>
      <LI><A href="javascript:goPage(${pageInfo.pages});">末页</A></LI></UL><SPAN
          class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV>
  <DL>
    <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
    <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
