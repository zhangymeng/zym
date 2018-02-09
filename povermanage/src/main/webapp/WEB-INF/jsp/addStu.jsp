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
<div id="add" class="layui-row" style="margin-top: 80px;">
<form class="layui-form" action="">
  <div class="layui-form-item">
    <label class="layui-form-label">学生姓名</label>
    <div class="layui-input-block">
      <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入学生姓名" class="layui-input" style="width:30%;">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">学号</label>
    <div class="layui-input-block">
      <input type="text" name="stuNo" lay-verify="required" placeholder="请输入学号" autocomplete="off" class="layui-input" style="width:30%;">
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">联系方式</label>
      <div class="layui-input-inline">
        <input type="tel" name="phone" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
  
  <div class="layui-form-item"  style="width:30%;">
    <label class="layui-form-label">入学年份</label>
    <div class="layui-input-block">
      <select name="gradeNo" lay-filter="aihao">
      	<option value="2013">2013</option>
      	<option value="2014">2014</option>
      	<option value="2015">2015</option>
      	<option value="2016">2016</option>
      	<option value="2017">2017</option>
      	<option value="2018">2018</option>
      </select>
    </div>
  </div>

  
  <div class="layui-form-item">
    <label class="layui-form-label">所属院系</label>
    <div class="layui-input-inline">
      <select name="dId" lay-filter="test">
      	<c:if test="${not empty dList}"> 
	      	<c:forEach items="${dList}" var="d" varStatus="vs">
	        <option value="${d.id}">${d.name}</option>
	        </c:forEach>
        </c:if>
      </select>
    </div>
    <div class="layui-input-inline">
      <select name="pId" id="area">
		<c:if test="${not empty pList}"> 
			<c:forEach items="${pList}" var="d" varStatus="vs">
				<option value="${d.id}">${d.name}</option>
			</c:forEach>
		</c:if>
      </select>
    </div>
  </div>
  
 
  <div class="layui-form-item">
    <label class="layui-form-label">单选框</label>
    <div class="layui-input-block">
      <input type="radio" name="sex" value="1" title="男" checked="">
      <input type="radio" name="sex" value="0" title="女">
    </div>
  </div>
  
  <div class="layui-form-item">
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
  
 form.on('select(test)',function(data){
     var dId=parseInt(data.value);
     $.ajax({
         url: "<%=basePath%>student/getProfessional",
         data: {"dId": dId},//发送的参数
         success:function(data){
             var proHtml = '';
             for(var o in data){
               proHtml += '<option value="' + data[o].id +  '">' + data[o].name + '</option>';
             }
             $('#area').html(proHtml);
             form.render();  
         },
         error:function(){
             //失败执行的方法
             alert("error");
         }
     });
});
  
  
  
  //监听提交
form.on('submit(demo1)', function(data){
	var name = data.field.name;
	var stuNo = data.field.stuNo;
	var phone = data.field.phone;
	var sex = parseInt(data.field.sex);
	var gradeNo = parseInt(data.field.gradeNo);
	var dId = parseInt(data.field.dId);
	var pId = parseInt(data.field.pId);
    $.ajax({
		url: "<%=basePath%>student/add",
        data: {
            "name" : name,
            "stuNo" : stuNo,
            "phone" : phone,
            "sex" : sex,
            "gradeNo" : gradeNo,
            "dId" : dId,
            "pId" : pId,
        },
        success: function (data) {
			if(data.result==true){
				window.location.href = "<%=basePath%>student/page";
			}else{
				layer.msg(data.reason);
			}
        }

     });
     return false;
  });
  
  
});
</script>
  </body>
</html>
