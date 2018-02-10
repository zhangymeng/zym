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
    
    <title>助学贷款管理</title>
    
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

<div id="add" style="display:none; margin-top:20px">
<form class="layui-form" action="">  

  <div class="layui-form-item">
    <label class="layui-form-label">学号</label>
    <div class="layui-input-block">
      <input type="text" id="stuNo" name="stuNo" lay-verify="required" placeholder="请输入学号" autocomplete="off" class="layui-input" style="width:70%;">
    </div>
  </div>
  
  <div class="layui-form-item" style="margin-top:30px">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">确认分配</button>
    </div>
  </div>
  </form>
  <div id="stuDesc" style="margin-left:110px;"></div>
</div>

<!-- <div class="layui-btn-group demoTable">
  <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
  <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
  <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div> -->
<div class="student" style="margin-left: 30px;">
<table class="layui-table" lay-data="{width: 1300, height:500, url:'<%=basePath%>/loan/allLS?loanId=${loanId}&dId=${dId}', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
	  <tr>
	  	<th lay-data="{field:'id', width:40, sort: true, fixed: true}" rowspan="2">ID</th>
	  	<th lay-data="{align:'center'}" colspan="2">贷款信息</th>
	  	<th lay-data="{align:'center'}" colspan="6">学生信息</th>
	  	<th lay-data="{field:'adminStr', width:160}" rowspan="2">经办人</th>
		<th lay-data="{fixed: 'right', align:'center', toolbar: '#barDemo'}" rowspan="2">操作</th>
	  </tr>
      
      <tr>
      <th lay-data="{field:'loanTitle', width:190}">贷款项</th>
      <th lay-data="{field:'theYear', width:70}">年度</th>
      <th lay-data="{field:'gradeNo', width:60 sort: true}">级</th>
      <th lay-data="{field:'stuName', width:110, sort: true}">姓名</th>
      <th lay-data="{field:'stuNo', width:130, sort: true}">学号</th>
      <th lay-data="{field:'phone', width:150, sort: true}">联系方式</th>
      <th lay-data="{field:'department', width:145, sort: true}">院系</th>
      <th lay-data="{field:'professional', width:145, sort: true}">专业</th>
    </tr>
  </thead>
</table>
 </div>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
               
          
<script src="<%=basePath%>/static/layui.js"></script>
<script src="<%=basePath%>/static/js/jquery.js"></script>
<script>
layui.use(['table', 'form'],function(){
  var table = layui.table
  ,form = layui.form;
  var ldId = null;
  //监听表格复选框选择
  table.on('checkbox(demo)', function(obj){
    console.log(obj)
  });
  //监听工具条
  table.on('tool(demo)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
	      $.ajax({
			  url: "<%=basePath%>/loan/delLS",
	          data: {
	                "id" : data.id,
	                "ldId":data.ldId
	                },
	          success: function (count) {
				  if(count>0){
					  obj.del();
		    		  layer.close(index);
		    		  layer.msg("删除成功");
				  }else{
					  layer.msg("删除失败");
				   }
	          }
	      });
      });
    } else if(obj.event === 'look'){
      
    } else if(obj.event === 'edit'){
	      //layer.alert('编辑行：<br>'+ JSON.stringify(data));
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

  var $ = layui.$, active = {
    //以下未使用
    getCheckData: function(){ //获取选中数据
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
