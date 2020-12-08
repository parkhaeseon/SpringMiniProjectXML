<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 절대경로를 구할 수 있음 -->
<c:set var = "root" value = "${ pageContext.request.contextPath }/" ></c:set>









<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>



<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>





<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form:form action = "${root}board/write_pro" method = "post" modelAttribute="writeContentBean"
					enctype="multipart/form-data">
					
					<!-- 게시판 번호 세팅 -->
					<form:hidden path="content_board_idx"/>
					
					<!-- enctype="multipart/form-data" 를 넣어야 사용자가 선택한 File까지도 전부다 서버에 전송.
					하지만 이걸 설정하면 다른 방식으로 데이터가 전달되어 BoardController.java의 write_pro함수에 있는
					매개변수에 자동으로 주입되지 못한다. 해결방법은 bean(StandardServletMultipartResolver)을 정의한다.
					ServletAppContext.java 참고.-->
					
						<div class="form-group">
							<form:label path="content_subject">제목</form:label>							
							<form:input path="content_subject" class="form-control"/>
							<form:errors path="content_subject" style="color:red"></form:errors>							
						</div>
						<div class="form-group">
							<form:label path="content_text">내용</form:label>							
							<form:textarea path="content_text" rows="10" class="form-control" style="resize:none"/>
							<form:errors path="content_text" style="color:red"></form:errors>					
						</div>
						<div class="form-group">
							<form:label path="upload_file">첨부 이미지</form:label>		
							<form:input type="file" path="upload_file" class="form-control" accept="image/*"/>										
						</div>
						<div class="form-group">
							<div class="text-right">
								<form:button class="btn btn-primary">작성하기</form:button>
							</div>
						</div>
					
					
					</form:form>
					
					
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>


<!-- 하단 -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>







