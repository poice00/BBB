<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>微博</title> 
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
		  <li role="presentation" id="blog" class="active"><a href="/BBB/blog/list"">微博信息</a></li>
		  <li role="presentation" id="user"><a href="/BBB/user/list">用户信息</a></li>
		</ul>
	  </div>
	</nav>
	<div class="main" align="center">
		<div align="center"><span>微博信息</span></div>
	<form id="page" action="" >
		<table class="table table-hover">
			<tr align="center">
		    	<td>微博内容</td>
		    	<td width="50px">赞</td>
		    	<td width="50px">评论</td>
		    	<td width="50px">转发</td>
		    	<td width="100px">发布时间</td>
		    	<td width="80px">作者</td>
		    </tr>
		    <c:forEach items="${pageBean.recordList}" var="item">
		    	<tr align="center">
		    	<td id="content">${item.weibotext }</td>
		    	<td>${item.attitudesCount }</td>
		    	<td>${item.commentsCount }</td>
		    	<td>${item.repostsCount }</td>
		    	<td>${item.createdTime }</td>
		    	<td><a href="/BBB/user/show?id=${item.user.id }">${item.user.name }</a></td>
		    	</tr>
		  	</c:forEach>
		 </table>
	 </form>
	 <%@ include file="/WEB-INF/jsps/public/pageView.jsp"%>
	 
	
	 </div>
<script type="text/javascript">
		function search() {
			document.forms["page"].submit();
		}
</script>	 
	 
</body>  
</html>