<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- spring security 를 활용한 로그인 체크 start -->

<!-- jsp에서 spring security 를 활용하기 위한 import-->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="KR">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta property="og:title" content="SJ의 스프링블로그">
    <meta property="og:description" content="스프링부트의 CRUD구현 블로그">
    <meta property="og:image" content="/img/SJ_Main.png">
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
    <link rel="stylesheet" href="/css/style.css" />
    <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
    <script src="/js/common.js"></script>
    <script src="/js/main.js"></script>
    <title>Spring 게시판</title>
  </head>
  <body>
    <div class="wrap">
      <div class="left__container">
        <div class="left__mask"></div>
	        <h1>스프링<br />게시판 만들기</h1>
        <div class="avartar_wrap">
          <img class="avartar" src="/img/SJ_Main.png" alt="" />
        </div>
        <div class="left__container-footer">
          <i class="fas fa-home"></i>
          <i class="fas fa-pen"></i>
          <i class="fas fa-book"></i>
          <i class="fab fa-instagram"></i>
        </div>
      </div>
      <div class="right__container">
        <div class="contents__container">
          <div class="contents__container-header">
            <h2>전체 글</h2>
            <h2><i class="fas fa-pen" onclick="javascript:location.href='/write.html';"></i></h2>
          </div>
          <hr />
          <div class="card__container">
            <div class="card">
              <div class="card-header">
                <a href="/detail" class="article-title"
                  ><h2>항해99_WIL#2 - 챕터2. 알고리즘!! - 1주차 회고</h2></a
                >
              </div>
              <div class="card-body">
                <p>
                  20201/03/05 ~ 2021/03/12 알고리즘 1주 차 우여곡절 끝에 챕터 1
                  미니 프로젝트를 마치고 무사히(?) 챕터 2 알고리즘 1주 차 발제를
                  했다! 😁😁 알고리즘.. 알고리즘.. 언젠간 부딪혀야 할 줄은
                  알았지만 막상 찾아오니 너무 막막했고 숨이 턱턱 막히는
                  기분이었다. 그 이유는 당연하게도 비전공자인 나에게는 태산같이
                  높은 허들이기 때문! 내가 잘 따라갈 수 있을까 하는 우려와
                  중간중간 진절머리가 나서 집중력을 잃어버릴까 너무 걱정되었다.
                  다행스럽게도 좋은 팀원들을 만나 1주 차는 무사히 넘길 수
                  있었다.. 🙋🏻 알고리즘을 풀면서 수없이 많은 구글링을 했다
                  결과적으로 실력이 늘어난 건 구글링 실력..
                </p>
              </div>
              <div class="card-footer">
                <p class="post-author">
                  정석진 <span class="post-date">| 2021. 3. 14. 19:56</span>
                </p>
              </div>
            </div>
          </div>
        </div>
		
		<button onclick="logout();">로그아웃</button>
		
      </div>
    </div>
  </body>
</html>