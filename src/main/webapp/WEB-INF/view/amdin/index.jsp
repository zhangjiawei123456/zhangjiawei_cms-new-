<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>

<!-- <link rel="stylesheet" href="/resource/css/index3.css"> -->
<title>CMS后台管理平台</title>

</head>
<body style="background: url(/pic/a6.jpg)">

	<nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">CMS系统</a>
		</div>
		<div>
			<ul class="nav navbar-nav  navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> 管理员: <span
						class="glyphicon glyphicon-user"></span>
						${sessionScope.SESSION_USER_KEY.username} <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#">用户设置</a></li>
						<li><a href="#">个人信息</a></li>
						<li class="divider"></li>
						<li><a href="/user/logout">退出登录</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="row">
		<div class="col-md-2">
			<div style="margin-left: 20px">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a
						href="javascript:showFuction('/admin/articles')">文章管理</a></li>
					<li><a href="javascript:showFuction('/admin/users')">用户管理</a></li>
					<li><a href="javascript:showFuction('/user/postArticle')">公告管理</a></li>
					<li><a href="javascript:showFuction('/link/list')">友情链接管理</a></li>

					<li>投票管理
						<ul class="nav nav-pills nav-stacked">
							<li><a href="#">投票列表</a></li>
							<li><a href="#">新建投票</a></li>
						</ul>
					</li>
					<li class="divider"></li>
					<li><a href="javascript:showFuction('/user/hello')">测试</a></li>
				</ul>
			</div>
		</div>
		<div class="col-md-10" style="min-height: 500px; border-left: solid">
			<!-- 放每个功能右面的展示列表的盒子 -->
			<div id="content"></div>
		</div>

		<!-- 富文本编辑器引入 -->
		<div class="col-md-9" id="content">
			<div id="kindEditor" style="display: none">
				<!-- 引入kindEditor的样式 -->
				<jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
			</div>

		</div>
	</div>


	<nav class="navbar navbar-inverse navbar-fixed-bottom"
		role="navigation">
	<div align="center">
		<font color="#8600FF" size="5"> ----八维大数据学院1707D--- </font>
	</div>
	</nav>

	<script type="text/javascript">
	function showFuction(url){
		$("#content").load(url)
		
	}
</script>

</body>
</html>