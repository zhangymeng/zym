<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>大学贫困生资助管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=basePath%>/static/css/layui.css">
	<link rel="stylesheet" href="<%=basePath%>/static/css/admin.css">
  </head>
<body>
<div class="layui-row login-page">
	<div class="layui-col-md4 layui-col-md-offset4 login-layout" style="">
		<div class="login-panel layui-row">
			<form class="layui-form layui-col-md8 layui-col-md-offset2" action="" style="margin-top: 160px;">
				<div class="layui-form-item">
					<input type="text" name="username" lay-verify="required" placeholder="帐号" autocomplete="off" class="layui-input login-account">
				</div>

				<div class="layui-form-item">
					<input type="password" name="password" lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input login-pass" minlength="6">
				</div>

				<div class="layui-form-item">
					<button class="layui-btn layui-btn-big-primary" lay-submit="" lay-filter="*" style="width: 100%;">登录</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="<%=basePath%>/static/layui.js"></script>
<script>
    layui.use(['jquery', 'form'], function(){
        var form = layui.form;
        var $ = layui.jquery;

        form.on('submit(*)', function(data){
            var _url = "<%=basePath%>/login/login";

            $.post(_url, data.field, function (res) {
                if(res.result) {
                    layer.msg("登录成功");

	                setTimeout(function () {
	                    window.location.href = "<%=basePath%>/login/index";
	                }, 1000);

                } else {
                    layer.msg(res.reason, {icon: 5});
                }
            });

            return false;
        });
    });

</script>
</body>
</html>
