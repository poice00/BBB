<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<div>
	<div class="panel panel-default">
	  	<div class="panel-body">
			   页次：${pageBean.currentPage }/${pageBean.pageCount }页 &nbsp; 
			   每页显示：${pageBean.pageSize }条 &nbsp; 总记录数：${pageBean.recordCount }条
	 	</div>
	</div>
	
	<%--总分页--%>
	<div>
		<ul class="pagination">
			<%--首页 --%>
			<li><a href="javascript:gotoPageNum(1)" style="cursor: hand;">首页</a></li>
		
				<c:forEach begin="${pageBean.beginPageIndex }" end="${pageBean.endPageIndex}" var="num">
				<c:choose>
					<c:when test="${num == pageBean.currentPage}">
					 <li class="active"><a>${num }<span class="sr-only">(current)</span></a></li>
					</c:when>
					
					<c:otherwise>
					<li><a onclick="gotoPageNum(${num });" style="cursor: hand;">${num }
					  		 <span class="sr-only" >(current)</span></a></li>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			
				<%--尾页 --%>
			<li><a href="javascript:gotoPageNum(${pageBean.pageCount })" style="cursor: hand;">尾页</a></li>
		</ul>
	</div>
		<%--分页 跳转--%>
		
	<div>
	转到： <select onchange="gotoPageNum(this.value)" id="_pn" style="padding: 5px 0;margin: 2px 0 0;">
				<c:forEach begin="1" end="${pageBean.pageCount }" var="num">
					<option value="${num }">${num }</option>
				</c:forEach>
		</select>
	</div>
		
		
		<%--前一页 后一页 --%>
	<div>
		<ul class="pager">
			<c:choose>
				<c:when test="${pageBean.pageCount==1||pageBean.pageCount==0}">
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${pageBean.currentPage ==1 }">
						<li class="next"><a onclick="gotoPageNum(${pageBean.currentPage+1 });" style="cursor: hand;">下一页 &rarr;</a></li>
						</c:when>
							<c:when test="${pageBean.currentPage==pageBean.pageCount }">
									 <li class="previous"><a onclick="gotoPageNum(${pageBean.currentPage-1 });" style="cursor: hand;">&larr; 上一页</a></li>
							</c:when>
						<c:otherwise>
							<li class="next"><a onclick="gotoPageNum(${pageBean.currentPage+1 });" style="cursor: hand;">下一页 &rarr;</a></li>
		  					<li class="previous"><a onclick="gotoPageNum(${pageBean.currentPage-1 });" style="cursor: hand;">&larr; 上一页</a></li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>

</div>

<script type="text/javascript">
				$("#_pn").val("${pageBean.currentPage}");
</script>
<script type="text/javascript">
		function gotoPageNum(currentPage) {
			$(document.forms["page"]).append("<input type='hidden' name='currentPage' value='"+ currentPage + "'>");
			document.forms["page"].submit();
		}
</script>
