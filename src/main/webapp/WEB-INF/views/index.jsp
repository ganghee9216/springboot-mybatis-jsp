<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<h1>스프링부트로 시작하는 웹 서비스 Ver.2</h1>
<div class="col-md-12">
    <div class="row">
        <div class="col-md-6">
            <a href="/posts/save" role="button" class="btn btn-primary">글 등록</a>
            <c:choose>
                <c:when test="${!empty user}">
                    Logged in as: <span id="user">${user.name}</span>
                    <a href="/logout" id="deleteUser" value="${user.id}" class="btn btn-info active" role="button">Logout</a>
                </c:when>
                <c:otherwise>
                    <a href="/oauth2/authorization/google" class="btn btn-success active" role="button">Google Login</a>
                    <a href="/oauth2/authorization/naver" class="btn btn-secondary active" role="button">Naver Login</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <br>
    <!-- 목록 출력 영역 -->
    <table class="table table-horizontal table-bordered">
        <thead class="thead-strong">
        <tr>
            <th>게시글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>최종수정일</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <!--posts라는 List를 순회-->
        <c:forEach var="posts" items="${posts}">
            <tr>
                <!--List에서 뽑아낸 객체의 필드 사용-->
                <td>${posts.id}</td>
                <!--title에 a태그를 추가하여 수정화면으로 연결-->
                <td><a href="/posts/update/${posts.id}">${posts.title}</a></td>
                <td>${posts.author}</td>
                <td>${posts.modifiedDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="layout/footer.jsp"%>
