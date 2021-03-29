<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- spring security 를 활용한 로그인 체크 start -->

<!-- jsp에서 spring security 를 활용하기 위한 import-->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jsp에서 로그인 여부 판단하여 index페이지로 이동-->
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
}
%>
<!-- spring security 를 활용한 로그인 체크 end -->
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link
      rel="stylesheet"
      href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
    />
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="/css/detail_style.css" />
    <script src="/js/detail_main.js"></script>
    
     <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
	 <script src="/js/reply.js"></script>
	 <script src="/js/common.js"></script>
      <script type="text/javascript">
		let global_username = "<%=usernameJsp%>";
	  </script>
	  
    <title>Spring 자세히 보기</title>
  </head>
  <body>
    <div class="wrap">
      <div class="header">
        <h2>
          Spring <br />
          게시판 만들기
        </h2>
      </div>
      <div class="navbar">
        <i class="fas fa-home" id="home"></i>
        <i class="fas fa-edit" id="edit"></i>
        <i class="fas fa-trash" id="delete"></i>
      </div>
      <div class="content__container">
        <div class="detail__edit">
          <div class="content-header">
            <input
              type="text"
              class="detail-input"
              placeholder="수정하실 제목을 적어주세요!"
            />
            <p class="post-author-edit">
            </p>
            <hr />
          </div>
          <div class="content-body">
            <textarea
              cols="30"
              rows="10"
              class="detail-textarea"
              placeholder="수정하실 내용을 적어주세요!"
            ></textarea>
          </div>
          
          
          <div class="content-footer">
            <button class="edit-btn cancel">취소</button>
            <button class="edit-btn edit" onclick="editArticle()">수정</button>
          </div>
        </div>
        
      </div>
      
      
	      <div class="footer">
        <a href="https://github.com/strong1133"
          ><i class="fab fa-github"></i
        ></a>
      </div>
    </div>
  </body>
</html>

	