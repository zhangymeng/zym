<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'editPwdPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=basePath%>/static/css/layui.css">
	<link rel="stylesheet" href="<%=basePath%>/static/css/editUser.css">
  </head>
  
  <body>
    <div class="layui-row login-page">
		<div class="layui-col-md4 layui-col-md-offset4 login-layout" style="">
			<div class="login-panel layui-row">
				<form class="layui-form layui-col-md8 layui-col-md-offset2" action="" style="margin-top: 160px;position:relative;top:-150px;">
					<div class="layui-form-item">
						<input type="password" name="oldPassword" lay-verify="required" placeholder="请输入旧密码" autocomplete="off" class="layui-input login-pass">
					</div>
	
					<div class="layui-form-item">
						<input type="password" name="password" lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input login-pass">
					</div>
	
					<div class="layui-form-item">
						<input type="password" name="passwords" lay-verify="required" placeholder="请再输一次新密码" autocomplete="off" class="layui-input login-pass">
					</div>
					
					<div class="layui-form-item">
						<button class="layui-btn layui-btn-big-primary" lay-submit="" lay-filter="*" style="width: 100%;">确认修改</button>
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
        
            var _url = "<%=basePath%>/user/editPwd";

            $.post(_url, data.field, function (res) {
                if(res.result) {
                    layer.msg("修改成功");

	                setTimeout(function () {
	                    window.location.href = "<%=basePath%>/login/basic"
	                }, 1000);

                } else {
                    layer.msg(res.reason);
                }
            });

            return false;
        });
    });

</script>
  </body>
</html>
