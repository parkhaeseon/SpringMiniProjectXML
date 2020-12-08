<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 절대경로를 구할 수 있음 -->
<c:set var = "root" value = "${ pageContext.request.contextPath }/" ></c:set>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>

	alert("수정되었습니다.")
	location.href="${root}user/modify"
	
</script>





