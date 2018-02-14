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

<div id="edit" style="display:none; margin-top:20px">
<form class="layui-form" action="">  

   <div class="layui-form-item">
    <label class="layui-form-label">描述</label>
    <div class="layui-input-block">
      <input type="text" id="title" name="title" lay-verify="required" autocomplete="off"  class="layui-input" style="width:70%;">
    </div>
  </div>

  <div class="layui-form-item"  style="width:70%;">
    <label class="layui-form-label">年度</label>
    <div class="layui-input-block">
      <select id="theYear" name="theYear" lay-filter="aihao" disabled="disabled">
      	<option value="2017">2017</option>
      	<option value="2018">2018</option>
      	<option value="2019">2019</option>
      	<option value="2020">2020</option>
      	<option value="2021">2021</option>
      	<option value="2022">2022</option>
      </select>
    </div>
  </div>
  
<%--   <div class="layui-form-item">
    <label class="layui-form-label">所属院系</label>
    <div class="layui-input-block" style="width:30%;">
      <select name="dId" id="dId" lay-filter="aihao" >
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
        <input type="text" id="num" name="num" lay-verify="required|number" autocomplete="off" class="layui-input">
      </div>
    </div>
  
  <div class="layui-form-item" style="margin-top:30px">
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
	<button class="layui-btn" data-type="add">添加</button>
</div>
<div class="student" style="margin-left: 30px;">
<table class="layui-table" lay-data="{width: 1300, height:500, url:'<%=basePath%>/aid/findAll', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
    <tr>
     <!--  <th lay-data="{type:'checkbox', fixed: 'left'}"></th> -->
      <th lay-data="{field:'id', width:60, sort: true, fixed: true}">ID</th>
      <th lay-data="{field:'title', width:200}">描述</th>
      <th lay-data="{field:'theYear', width:200}">年度</th>
      <th lay-data="{field:'num', width:300, sort: true}">总名额</th>
      <th lay-data="{field:'remainingNum', width:200, sort: true}">现剩余名额</th>
      <th lay-data="{fixed: 'right', align:'center', toolbar: '#barDemo'}">操作</th>
    </tr>
  </thead>
</table>
 </div>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">分配院系名额</a>
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
      //layer.msg('ID：'+ data.id + ' 的查看操作');
      window.location.href = "<%=basePath%>aid/addADPage?aidId="+data.id;
    } else if(obj.event === 'del'){
    //删除操作
      layer.confirm('真的删除行么', function(index){
      $.ajax({
		url: "<%=basePath%>/aid/delAid",
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
	          area: ['400px', '400px'],
	          shift: 2,
	          shadeClose: true,
	          content: $("#edit")
	      });
	      document.getElementById("title").value=data.title;
	      document.getElementById("num").value=data.num;
	      $("#theYear option[value='"+ data.theYear +"']").prop('selected', true);
	      form.render();  
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

  //监听提交
form.on('submit(demo1)', function(data){
	var title = data.field.title;
	var num = parseInt(data.field.num);
	if(num<1){
		layer.msg("分配人数需大于零");
		return;
	}
    $.ajax({
		url: "<%=basePath%>aid/addAid",
        data: {
        	"id":id,
            "title" : title,
            "num" : num,
        },
        success: function (data) {
			if(data.result==true){
			   layer.msg("修改成功");

               setTimeout(function () {
                   window.location.href = "<%=basePath%>aid/aidPage";
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
    add: function(){ 
		window.location.href = "<%=basePath%>/aid/addPage";
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
