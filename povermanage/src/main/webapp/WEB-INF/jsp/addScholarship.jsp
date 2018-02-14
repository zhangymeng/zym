<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addStu.jsp' starting page</title>
    
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
 <div style="float:right;margin-right:30px;cursor:pointer;" onclick="backBtn()">
 <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#x1006;</i>  
 </div>  
   
<div id="add" class="layui-row" style="margin-top: 80px;">
<form class="layui-form" action="">
  <div class="layui-form-item">
    <label class="layui-form-label">描述</label>
    <div class="layui-input-block">
      <input type="text" name="title" lay-verify="required" autocomplete="off" placeholder="请输入描述" class="layui-input" style="width:30%;">
    </div>
  </div>
  
  <div class="layui-form-item"  style="width:30%;">
    <label class="layui-form-label">年度</label>
    <div class="layui-input-block">
      <select name="theYear" lay-filter="aihao">
      	<option value="2017">2017</option>
      	<option value="2018">2018</option>
      	<option value="2019">2019</option>
      	<option value="2020">2020</option>
      	<option value="2021">2021</option>
      	<option value="2022">2022</option>
      </select>
    </div>
  </div>
  
<%--   <div class="layui-form-item"  style="width:30%;">
    <label class="layui-form-label">所属院系</label>
    <div class="layui-input-block">
      <select name="dId" lay-filter="aihao">
		<option value="0">请选择</option>
		<c:if test="${not empty dList}"> 
	      	<c:forEach items="${dList}" var="d" varStatus="vs">
	        <option value="${d.id}">${d.name}</option>
	        </c:forEach>
        </c:if>
      </select>
    </div>
  </div> --%>
  
	<div class="layui-inline">
      <label class="layui-form-label">名额</label>
      <div class="layui-input-inline">
        <input type="text" name="num" lay-verify="required|number" placeholder="请输入名额" autocomplete="off" class="layui-input">
      </div>
    </div>
  
  <div class="layui-form-item" style="margin-top:30px">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">确认添加</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
  
</form>
</div>
<script src="<%=basePath%>/static/layui.js"></script>
<script src="<%=basePath%>/static/js/jquery.js"></script>
<script>
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //日期
  laydate.render({
    elem: '#date'
  });
  laydate.render({
    elem: '#date1'
  });
  
  //创建一个编辑器
  var editIndex = layedit.build('LAY_demo_editor');
 
  //自定义验证规则
  form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });
  
  //监听指定开关
  form.on('switch(switchTest)', function(data){
    layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
      offset: '6px'
    });
    layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
  });
  
  
  //监听提交
form.on('submit(demo1)', function(data){
	var title = data.field.title;
	var theYear = parseInt(data.field.theYear);
	var num = parseInt(data.field.num);
	if(num<1){
		layer.msg("分配人数需大于零");
		return;
	}
    $.ajax({
		url: "<%=basePath%>scholarship/addScholarship",
        data: {
            "title" : title,
            "theYear" : theYear,
            "num" : num,
        },
        success: function (data) {
			if(data.result==true){
				window.location.href = "<%=basePath%>scholarship/scPage";
			}else{
				layer.msg(data.reason);
			}
        }

     });
     return false;
  });
});

function backBtn(){
	window.location.href = "<%=basePath%>scholarship/scPage";
} 
</script>
  </body>
</html>
