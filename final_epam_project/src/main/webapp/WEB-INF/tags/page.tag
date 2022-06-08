<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Отель :: ${title}</title>
    <c:url var="cssUrl" value="/w3.css"/>
    <link rel="stylesheet" href="${cssUrl}">
</head>
<body>
<div class="w3-bar w3-yellow w3-card-4">
    <c:url var="mainUrl" value="/info.html"/>
    <a href="${mainUrl}" class="w3-bar-item w3-button w3-hover-amber">Главная</a>
    <c:choose>
        <%--@elvariable id="sessionUser" type="by.vsu.hotel.domain.User"--%>
        <c:when test="${not empty sessionUser}">
            <c:choose>
                <c:when test="${sessionUser.role == 'ADMIN'}">
                    <c:url var="roomListUrl" value="/room/list.html"/>
                    <a href="${roomListUrl}" class="w3-bar-item w3-button w3-hover-amber">Комнаты</a>
                </c:when>
                <c:when test="${sessionUser.role == 'CLIENT'}">
                    <c:url var="orderSearchUrl" value="/order/search.html"/>
                    <a href="${orderSearchUrl}" class="w3-bar-item w3-button w3-hover-amber">Заказ комнаты</a>
                </c:when>
            </c:choose>
            <c:url var="logoutUrl" value="/logout.html"/>
            <a href="${logoutUrl}" class="w3-bar-item w3-button w3-hover-amber w3-right">Выйти</a>
        </c:when>
        <c:otherwise>
            <!--a href="#" class="w3-bar-item w3-button w3-right">Зарегистрироваться</a-->
            <c:url var="loginUrl" value="/login.html"/>
            <a href="${loginUrl}" class="w3-bar-item w3-button w3-hover-amber w3-right">Войти</a>
        </c:otherwise>
    </c:choose>
</div>
<div class="w3-container">
    <h1 class="w3-border-bottom w3-border-yellow">${title}</h1>
    <jsp:doBody/>
</div>
</body>
</html>
