<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link href="../resources/css/partyApplicants.css" rel="stylesheet" type="text/css">
    <link
        href=“https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700;900&family=Roboto:ital,wght@0,300;0,400;0,500;0,700;0,900;1,300&display=swap”
        rel=“stylesheet”>
    <link rel=“stylesheet”
    href=“https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.css”>
    <title>Party 신청 목록</title>
</head>
<body>
<header>
        <div class="head">
            <!-- 로고 -->
            <a href="/miraclebird">
                <img src="../resources/img/logo.svg" alt="로고" class="logo">
            </a>
            <!-- 네비게이션바 -->
            <nav>
                <ul class="nav-container animate__animated animate__fadeIn">
                    <li class="nav-item"><a href="/miraclebird">COMMUNITY</a></li>
                    <li class="nav-item"><a href="/miraclebird/recruit/list">JOIN</a></li>
                    <c:if test="${partyId != null}">
                    	<li class="nav-item"><a href="/miraclebird/party/main">MY PARTY</a></li>
                    </c:if>
                    <li class="nav-item"><a href="/miraclebird/store/productList?page=1&categoryId=1">STORE</a></li>
                    <li class="nav-item"><a href="/miraclebird/myFeed">PROFILE</a></li>
                    <li class="nav-item">
                    	<c:choose>
                    		<c:when test="${userId == null}">
                    			<a href="/miraclebird/loginPage"><button>LOGIN</button></a>
                    		</c:when>
                    		<c:otherwise>
                    			<a href="/miraclebird/logout"><button>LOGOUT</button></a>
                    		</c:otherwise>
                    	</c:choose>
                    </li>
                </ul>
            </nav>
        </div>
    </header>
    <div >
        <div class="cate">
            <a href="/miraclebird/party/main">
            	<button class="btn">Main</button>
            </a>
            <button class="btn">Feed</button>
            <button class="btn">Community</button>
            <button class="btn">Style</button>
            <button class="btn">People</button>
            <a href="/miraclebird/party/applicants">
            	<button class="btn">Applicants</button>
            </a>
        </div>
    <div class="mainInfo">
        <div class="main">
            <div class="mainContent">
                <div class="content">
                	<c:forEach var="vo" items="${list}">
                		<div class="people">
                        	<img src="../resources/img/profile-circle.png" class="icon">
                            <ul>
                                <li>
                                    회원 이름
                                </li>
                                <li>
                                    가입 신청 모임
                                </li>
                                <li>
                                    ${vo.applicantDate}
                                </li>
                            </ul>
                            <div class="partyApBtn">
                                <button class="btnA">수락</button>
                                <button class="btnB">거절</button>
                            </div>
                    	</div>
                	</c:forEach>
                    <div class="people">
                        <img src="../resources/img/profile-circle.png" class="icon">
                        
                    </div>          
                    <div class="people">
                        <img src="../resources/img/profile-circle.png" class="icon">
                    </div> 
                </div>
            </div>
            <div class="mainText">
                <a>환영합니다. 지옥에서 돌아온 닭 파티룸입니다.</a>
            </div>
        </div>
    </div>
    </div>

</body>
</html>