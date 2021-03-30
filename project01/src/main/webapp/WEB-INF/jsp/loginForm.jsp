<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- spring security 를 활용한 로그인 체크 start -->

<!-- jsp에서 spring security 를 활용하기 위한 import-->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 
	jsp에서 로그인 여부 판단하여 index페이지로 이동
	spring security 내부 함수 호출하여 로그인 비로그인 판단
 -->
<%
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
Object principal = auth.getPrincipal();
UserDetails userDetails = null;
String usernameJsp = null;

if(principal.equals("anonymousUser")){ //비로그인
	usernameJsp = "";
}else{ //일반 로그인 or 카카오톡
	userDetails = (UserDetails)principal;
	usernameJsp = userDetails.getUsername();
	response.sendRedirect("/index");
}
%>
<!-- spring security 를 활용한 로그인 체크 end -->
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!-- kakao api -->
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/login.js"></script>


<div class="container">
    <div class="row">
    	<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default">
			  	<div class="panel-heading">
			    	<h3 class="panel-title">Login</h3>
			 	</div>
			  	<div class="panel-body">
			  		<!-- spring security에서 /login 액션 캐치 -->
			    	<form accept-charset="UTF-8" role="form" action="/login" method="post">
                    <fieldset>
			    	  	<div class="form-group">
			    		    <input class="form-control" placeholder="닉네임을 입력해 주세요." name="username" type="text" onkeyup="this.value=this.value.replace(/[^a-zA-Z-_0-9]/g,'');">
			    		</div>
			    		<div class="form-group">
			    			<input class="form-control" placeholder="패스워드를 입력해 주세요." name="password" type="password" value="">
			    		</div>
			    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
			    	</fieldset>
			    	   <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
			      	</form>
                    <input class="btn btn-lg btn-facebook btn-block" type="button" style="background-color: #EEE;" value="회원가입" onclick="javascript:location.href='register.html';">
                    <input class="btn btn-lg btn-facebook btn-block" type="button" style="background-color: #E7E600;" value="카카오톡 로그인" onclick="kakaoLogin();">
                    <input class="btn btn-lg btn-facebook btn-block" type="button" style="background-color: #6799FF;" value="게시글 조회" onclick="javascript:location.href='/index';">
			    </div>
			</div>
		</div>
	</div>
</div>

</html>	