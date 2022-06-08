<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<u:page title="Авторизация">
    <c:if test="${not empty param.error}">
        <div class="w3-panel w3-pale-red w3-round-small">
            <h2>Ошибка</h2>
            <p>${param.error}</p>
        </div>
    </c:if>
    <c:url var="loginUrl" value="/login.html"/>
    <form action="${loginUrl}" method="post" class="w3-container w3-pale-yellow w3-padding">
        <div class="w3-panel">
            <label for="login-input" class="w3-large">Имя пользователя:</label>
            <input type="text" id="login-input" name="login" class="w3-input w3-border">
        </div>
        <div class="w3-panel">
            <label for="password-input" class="w3-large">Пароль:</label>
            <input type="password" id="password-input" name="password" class="w3-input w3-border">
        </div>
        <div class="w3-panel">
            <button type="submit" class="w3-button w3-green w3-round-medium">Войти</button>
        </div>
    </form>
</u:page>
