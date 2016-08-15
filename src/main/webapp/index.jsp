<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <title>Dashboard Template for Bootstrap</title>
    <link href="<%=request.getContextPath()%>/resources/dash/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/dash/dashboard.css" rel="stylesheet">
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">Help</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Overview</a></li>
            <li><a target="ifrm" href="<%=request.getContextPath()%>/home/index">数据</a></li>
             <li><a target="ifrm" href="<%=request.getContextPath()%>/home/index">Comics</a></li>
             <li><a target="ifrm" href="<%=request.getContextPath()%>/home/index">Reports</a></li>
             <li><a target="ifrm" href="<%=request.getContextPath()%>/home/index">Analytics</a></li>
             <li><a target="ifrm" href="<%=request.getContextPath()%>/a.jsp">Export</a></li>
          </ul>
        </div>
        <!-- DashBoard -->
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div style="margin-top: 30px">
	       		<iframe  src="<%=request.getContextPath()%>/home/index" name="ifrm" frameborder=0 width="1300px" height="780px">
				</iframe>
			</div>
        </div>
        <!-- DashBoard end-->
      </div>
    </div>
    <!-- Bootstrap core JavaScript -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.3.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/dash/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/dash/holder.js"></script>
  </body>
</html>
