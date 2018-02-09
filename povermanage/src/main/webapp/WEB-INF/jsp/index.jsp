<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>大学贫困生资助管理系统</title>
  <link rel="stylesheet" href="<%=basePath%>/static/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">大学贫困生资助管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <!-- <li class="layui-nav-item"><a href="">控制台</a></li> -->
      <li class="layui-nav-item">
        <a href="javascript:;">个人中心</a>
        <dl class="layui-nav-child">
          <dd><a href="javascript:void(0);" onclick="toPage(this)" data-url="<%=basePath%>/user/editPwdPage">修改密码</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
    	${userInfo.username}
      </li>
      <li class="layui-nav-item"><a href="<%=basePath%>/login/logout">注销</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
		<c:forEach items="${menuList}" var="menu" varStatus="vs"> 
			<li class="layui-nav-item">
			<c:choose>
				<c:when test="${not empty menu.child}"> 
			          <a href="javascript:;">${menu.title}</a>
			          <dl class="layui-nav-child">
			          	<c:forEach items="${menu.child}" var="child" varStatus="vs"> 
			            <dd><a href="javascript:void(0);" onclick="toPage(this)" data-url="<%=basePath%>${child.url}">
			            ${child.title}
			            </a></dd>
			            </c:forEach>
			          </dl>
        		</c:when>
        		<c:otherwise>  
                	<a href="javascript:void(0);" onclick="toPage(this)" data-url="<%=basePath%>${menu.url}">
                	${menu.title}</a>
                </c:otherwise>
        	</c:choose>
        	</li>
        </c:forEach>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    	<div id="frame-div" style="position: absolute; top: 0;left: 0;right: 0;bottom: 0;">
			<iframe src="${request.contextPath}/login/basic" frameborder="0" id="admin-frame" style="width: 100%;height: 100%;position: absolute;bottom: 0;"></iframe>
		</div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    大学贫困生资助管理系统
  </div>
</div>
<script src="<%=basePath%>/static/layui.js"></script>
<script src="<%=basePath%>/static/js/jquery.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});

function toPage(aa){
    var the_link = $(aa).attr("data-url");
    alert(the_link);
    if(the_link) {
        $("#admin-frame").attr('src', the_link);
    }
}

</script>
</body>
</html>
