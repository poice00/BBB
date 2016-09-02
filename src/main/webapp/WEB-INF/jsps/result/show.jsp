<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>用户</title> 
<script language="javascript"
src="<%=request.getContextPath()%>/resources/js/main.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css" />
</head>  
<body>  
	<nav class="navbar navbar-default navbar-fixed-top">
	  <div class="container">
	    <ul class="nav nav-pills">
		  <li role="presentation" id="blog"><a href="/BBB/blog/list"">微博信息</a></li>
		  <li role="presentation" id="user"><a href="/BBB/user/list">用户信息</a></li>
		</ul>
	  </div>
	</nav>
	<div class="main" align="center">
		<table class="table table-hover">
			<tr align="center">
		    	<td width="50px">用户昵称</td>
		    	<td width="50px">关注数</td>
		    	<td width="50px">粉丝数</td>
		    	<td width="100px">博客数</td>
		    	<td width="80px">排名值</td>
		    </tr>
	    	<tr align="center">
	    	<td>${user.name }</td>
	    	<td>${user.attnum }</td>
	    	<td>${user.fansNum }</td>
	    	<td>${user.mblogNum }</td>
	    	<td>${user.mbrank }</td>
	    	</tr>
		 </table>
	 </div>
</body>  
</html>