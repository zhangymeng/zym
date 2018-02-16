<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'povertyPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=basePath%>/static/css/layui.css">
  </head>
  
  <body>
  <div style="float:right;margin-top:40px;margin-right:30px;cursor:pointer;" onclick="backBtn()">
 <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#x1006;</i>  
 </div> 
 
  <c:if test="${not empty loanList}">
<fieldset class="layui-elem-field layui-field-title" style="width:80%;margin-left:100px;">
  <legend>贷款表</legend>
</fieldset>
 
<div class="layui-form" style="width:60%;margin-left:250px;">
  <table class="layui-table">
    <colgroup>
      <col width="200">
      <col width="150">
      <col width="200">
      <col>
    </colgroup>
    <thead>
      <tr>
        <th>贷款信息</th>
        <th>年度</th>
        <th>经办人</th>
      </tr> 
    </thead>
    <tbody>
	 	 <c:forEach items="${loanList}" var="d" varStatus="vs">
	     <td>${d.loanTitle}</td>
	     <td>${d.theYear}</td>
	     <td>${d.adminStr}</td>
	     </c:forEach>
    </tbody>
  </table>
</div>
</c:if>

<c:if test="${not empty socialList}">
<fieldset class="layui-elem-field layui-field-title" style="width:80%;margin-left:100px;">
  <legend>社会资助表</legend>
</fieldset>
 
<div class="layui-form" style="width:60%;margin-left:250px;">
  <table class="layui-table">
    <colgroup>
      <col width="200">
      <col width="150">
      <col width="200">
      <col>
    </colgroup>
    <thead>
      <tr>
        <th>资助信息</th>
        <th>年度</th>
        <th>经办人</th>
      </tr> 
    </thead>
    <tbody>
	 	 <c:forEach items="${socialList}" var="d" varStatus="vs">
	     <td>${d.socialTitle}</td>
	     <td>${d.theYear}</td>
	     <td>${d.adminStr}</td>
	     </c:forEach>
    </tbody>
  </table>
</div>
</c:if>

<c:if test="${not empty shipList}">
<fieldset class="layui-elem-field layui-field-title" style="width:80%;margin-left:100px;">
  <legend>奖学金表</legend>
</fieldset>
 
<div class="layui-form" style="width:60%;margin-left:250px;">
  <table class="layui-table">
    <colgroup>
      <col width="200">
      <col width="150">
      <col width="200">
      <col>
    </colgroup>
    <thead>
      <tr>
        <th>奖学金信息</th>
        <th>年度</th>
        <th>经办人</th>
      </tr> 
    </thead>
    <tbody>
	 	 <c:forEach items="${shipList}" var="d" varStatus="vs">
	     <td>${d.scholarshipTitle}</td>
	     <td>${d.theYear}</td>
	     <td>${d.adminStr}</td>
	     </c:forEach>
    </tbody>
  </table>
</div>
</c:if>

<c:if test="${not empty aidList}">
<fieldset class="layui-elem-field layui-field-title" style="width:80%;margin-left:100px;">
  <legend>助学金表</legend>
</fieldset>
 
<div class="layui-form" style="width:60%;margin-left:250px;">
  <table class="layui-table">
    <colgroup>
      <col width="200">
      <col width="150">
      <col width="200">
      <col>
    </colgroup>
    <thead>
      <tr>
        <th>助学金信息</th>
        <th>年度</th>
        <th>经办人</th>
      </tr> 
    </thead>
    <tbody>
	 	 <c:forEach items="${aidList}" var="d" varStatus="vs">
	     <td>${d.aidTitle}</td>
	     <td>${d.theYear}</td>
	     <td>${d.adminStr}</td>
	     </c:forEach>
    </tbody>
  </table>
</div>
</c:if>

<c:if test="${empty loanList && empty socialList && empty shipList && empty aidList}">
<fieldset class="layui-elem-field layui-field-title" style="width:80%;margin-left:100px;margin-top:50px">
  <legend>该生暂无任何资助信息</legend>
</fieldset>
</c:if>
<script src="<%=basePath%>/static/layui.js"></script>
<script type="text/javascript">
function backBtn(){
	window.location.href = "<%=basePath%>student/page";
} 
</script>
  </body>
</html>
