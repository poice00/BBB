<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>用户信息</title> 
<script language="javascript"
src="<%=request.getContextPath()%>/resources/js/jquery-1.11.3.min.js"></script>
<script language="javascript"
src="<%=request.getContextPath()%>/resources/js/main.js"></script>
<script language="javascript"
src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css" />
</head>  
<body>  
	<nav class="navbar navbar-default navbar-fixed-top">
	  <div class="container">
	    <ul class="nav nav-pills">
		  <li role="presentation" ><a href="/BBB/result/list/max">degree</a></li>
		  <li role="presentation" ><a href="/BBB/result/list/rc">centerDistance</a></li>
		  <li role="presentation" ><a href="/BBB/result/list/ks">KS</a></li>
		  <li role="presentation" ><a href="/BBB/result/list/pr">MDD</a></li>
		  <li role="presentation" ><a href="/BBB/result/list/mypr">MIB</a></li>
		</ul>
	  </div>
	</nav>
	<div class="main" align="center">
		<div align="center"><span>${aname}</span></div>
	<form id="page" action="" >
		<table class="table table-hover">
			<tr align="center">
				<td width="100px">排名</td>
		    	<td>用户id</td>
		    	<td>用户昵称</td>
		    	<td>value</td>
		    	<td width="100px">被转发数</td>
		    	<td>被评论数</td>
		    	<td>关注数</td>
		    	<td>粉丝数</td>
		    	<td>博客数</td>
		    </tr>
		    <c:forEach items="${resultList}" var="item" varStatus="stauts">
		    	<tr align="center">
		    		<td>${stauts.count}</td>
			    	<td>${item.id }</td>
			    	<td><a href="/BBB/user/show?id=${item.id }">${item.name }</a></td>
			    	<td>${item.value }</td>
			    	<td>${item.user.repost }</td>
			    	<td>${item.user.comment }</td>
			    	<td>${item.user.attnum }</td>
	    			<td>${item.user.fansNum }</td>
	    			<td>${item.user.mblogNum }</td>
		    	</tr>
		  	</c:forEach>
		 </table>
	 </form>
	 
	
	 </div>
<script type="text/javascript">
		function search() {
			document.forms["page"].submit();
		}
</script>	 
	 
</body>  
</html>