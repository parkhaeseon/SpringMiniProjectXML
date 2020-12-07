<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 절대경로를 구할 수 있음 -->
<c:set var = "root" value = "${ pageContext.request.contextPath }/" ></c:set>

<script>

	alert("삭제 됐습니다.")
	location.href = "${ root }board/main?board_info_idx=${board_info_idx}"
</script>







    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    