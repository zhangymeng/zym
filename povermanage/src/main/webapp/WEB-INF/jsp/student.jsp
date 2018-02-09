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
    
    <title>贫困生管理</title>
    
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
<div style="margin-bottom: 5px;">       
   
<ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>
 
</div>

<div id="edit" style="display:none; margin-top:20px">
<form class="layui-form" action="">  

   <div class="layui-form-item">
    <label class="layui-form-label">学生姓名</label>
    <div class="layui-input-block">
      <input type="text" id="name" name="name" lay-verify="required" autocomplete="off" placeholder="请输入学生姓名" class="layui-input" style="width:70%;">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">学号</label>
    <div class="layui-input-block">
      <input type="text" id="stuNo" name="stuNo" lay-verify="required" placeholder="请输入学号" autocomplete="off" class="layui-input" style="width:70%;">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">联系方式</label>
      <div class="layui-input-inline">
        <input type="tel" id="phone" name="phone" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">入学年份</label>
    <div class="layui-input-block" style="width:30%;">
      <select name="gradeNo" id="gradeNo" lay-filter="aihao" >
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
      <select name="dId" id="dId" lay-filter="test">
      	<option value="0">请选择</option>
      	<c:if test="${not empty dList}"> 
	      	<c:forEach items="${dList}" var="d" varStatus="vs">
	        <option value="${d.id}">${d.name}</option>
	        </c:forEach>
        </c:if>
      </select>
    </div>
    <div class="layui-input-inline">
      <select name="pId" id="area">
     	<option value="0">请选择</option>
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
      <input type="radio" id="sex1" name="sex" value="1" title="男" checked="" style="height:8%;">
      <input type="radio" id="sex0" name="sex" value="0" title="女" style="height:8%;">
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">确认修改</button>
    </div>
  </div>
  </form>
</div>
 
<!-- <div class="layui-btn-group demoTable">
  <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
  <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
  <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div> -->
<div class="layui-btn-group demoTable" style="margin-left: 1200px;">
	<button class="layui-btn" data-type="addStu">添加</button>
</div>
<div class="student" style="margin-left: 30px;">
<table class="layui-table" lay-data="{width: 1300, height:500, url:'<%=basePath%>/student/findAll', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
    <tr>
     <!--  <th lay-data="{type:'checkbox', fixed: 'left'}"></th> -->
      <th lay-data="{field:'id', width:60, sort: true, fixed: true}">ID</th>
      <th lay-data="{field:'name', width:100}">姓名</th>
      <th lay-data="{field:'phone', width:130}">联系方式</th>
      <th lay-data="{field:'stuNo', width:130, sort: true}">学号</th>
      <th lay-data="{field:'sexStr', width:70}">性别</th>
      <th lay-data="{field:'gradeNo', width:80}">级</th>
      <th lay-data="{field:'classNo', width:80}">届</th>
      <th lay-data="{field:'department', width:200, sort: true}">院系</th>
      <th lay-data="{field:'professional', width:200, sort: true}">专业</th>
      <th lay-data="{fixed: 'right', align:'center', toolbar: '#barDemo'}">操作</th>
    </tr>
  </thead>
</table>
 </div>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看资助情况</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
               
          
<script src="<%=basePath%>/static/layui.js"></script>
<script src="<%=basePath%>/static/js/jquery.js"></script>
<script>
layui.use(['table', 'form'],function(){
  var table = layui.table
  ,form = layui.form;
  var id;
  //监听表格复选框选择
  table.on('checkbox(demo)', function(obj){
    console.log(obj)
  });
  //监听工具条
  table.on('tool(demo)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
      layer.msg('ID：'+ data.id + ' 的查看操作');
    } else if(obj.event === 'del'){
    //删除操作
      layer.confirm('真的删除行么', function(index){
      $.ajax({
		url: "<%=basePath%>/student/del",
                    data: {
                        "id" : JSON.stringify(data.id)
                    },
                    success: function (data) {
                    	//var dataobj = typeof data === "object" ? data : eval("(" + data + ")");
						if(data.result==true){
							obj.del();
        					layer.close(index);
        					layer.msg("删除成功");
						}else{
							layer.msg(data.reason);
						}
                    }
            });
      });
      
    } else if(obj.event === 'edit'){
      //layer.alert('编辑行：<br>'+ JSON.stringify(data));
	      layer.open({
	          type: 1,
	          closeBtn: 1,
	          area: ['600px', '500px'],
	          shift: 2,
	          shadeClose: true,
	          content: $("#edit")
	      });
	      document.getElementById("name").value=data.name;
	      document.getElementById("stuNo").value=data.stuNo;
	      document.getElementById("phone").value=data.phone;
	      $("#gradeNo option[value='"+ data.gradeNo +"']").prop('selected', true);
	      $("#dId option[value='"+ data.dId +"']").prop('selected', true);
	      var dId=parseInt(data.dId);
	      $.ajax({
	         url: "<%=basePath%>student/getProfessional",
	         data: {"dId": dId},//发送的参数
	         success:function(data){
	             var proHtml = '';
	             for(var o in data){
	               proHtml += '<option value="' + data[o].id +  '">' + data[o].name + '</option>';
	             }
	             $('#area').html(proHtml);
	             $("#pId option[value='"+ data.pId +"']").prop('selected', true);
	             form.render();  
	         },
	         error:function(){
	             //失败执行的方法
	             alert("error");
	         }
	     });
	     $("#sex"+data.sex).prop('checked', true);
	     id = data.id;
    }
  });
  
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
        	"id":id,
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
			   layer.msg("修改成功");

               setTimeout(function () {
                   window.location.href = "<%=basePath%>student/page";
               }, 1000);
				
			}else{
				layer.msg(data.reason);
			}
        }

     });
     return false;
  });

  var $ = layui.$, active = {
  	//添加
    addStu: function(){ 
		window.location.href = "<%=basePath%>/student/addPage";
    }
    
    
    //以下未使用
    ,getCheckData: function(){ //获取选中数据
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.alert(JSON.stringify(data));
    }
    ,getCheckLength: function(){ //获取选中数目
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.msg('选中了：'+ data.length + ' 个');
    }
    ,isAll: function(){ //验证是否全选
      var checkStatus = table.checkStatus('idTest');
      layer.msg(checkStatus.isAll ? '全选': '未全选')
    }
  };
  
  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
});
</script>

</body>
</html>
