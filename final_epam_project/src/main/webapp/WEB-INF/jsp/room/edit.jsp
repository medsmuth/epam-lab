<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--@elvariable id="room" type="by.vsu.hotel.domain.Room"--%>
<c:choose>
    <c:when test="${not empty room.id}">
        <c:set var="title" value="Изменение комнаты ${room.number}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление комнаты"/>
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Отель :: ${title}</title>
    <c:url var="cssUrl" value="/w3.css"/>
    <link rel="stylesheet" href="${cssUrl}">
</head>
<body>
    <div class="w3-container">
        <h1 class="w3-border-bottom w3-border-yellow">${title}</h1>
        <c:if test="${not empty param.error}">
            <div class="w3-panel w3-pale-red w3-round-small">
                <h2>Ошибка</h2>
                <p>${param.error}</p>
            </div>
        </c:if>
        <c:url var="saveUrl" value="/room/save.html"/>
        <form action="${saveUrl}" method="post" class="w3-container w3-pale-yellow w3-padding">
            <c:if test="${not empty room.id}">
                <input type="hidden" name="id" value="${room.id}">
            </c:if>
            <div class="w3-panel">
                <label for="number-input" class="w3-large">Номер комнаты:</label>
                <input type="text" id="number-input" name="number" value="${room.number}" class="w3-input w3-border">
            </div>
            <div class="w3-panel">
                <label class="w3-large">Тип комнаты:</label>
                <%--@elvariable id="types" type="java.util.List"--%>
                <c:forEach var="type" items="${types}">
                    <%--@elvariable id="type" type="by.vsu.hotel.domain.ApartmentType"--%>
                    <c:choose>
                        <c:when test="${type == room.apartmentType}">
                            <c:set var="checked" value="checked"/>
                        </c:when>
                        <c:otherwise>
                            <c:remove var="checked"/>
                        </c:otherwise>
                    </c:choose>
                    <div>
                        <input type="radio" id="type-${type.id}-input" name="type" value="${type.id}" class="w3-radio" ${checked}>
                        <label for="type-${type.id}-input">${type.name}</label>
                    </div>
                </c:forEach>
            </div>
            <div class="w3-panel">
                <label for="numberOfSeats-input" class="w3-large">Количество мест:</label>
                <input type="text" id="numberOfSeats-input" name="numberOfSeats" value="${room.numberOfSeats}" class="w3-input w3-border">
            </div>
            <div class="w3-panel">
                <label for="price-rub-input" class="w3-large">Стоимость за 1 день:</label>
                <div class="w3-cell">
                    <fmt:formatNumber var="priceRub" value="${room.price / 100}" maxFractionDigits="0"/>
                    <input type="text" id="price-rub-input" name="priceRub" value="${priceRub}" class="w3-input w3-border">
                </div>
                <div class="w3-container w3-cell w3-cell-middle">руб.,</div>
                <div class="w3-cell">
                    <fmt:formatNumber var="priceKop" value="${room.price % 100}" pattern="00"/>
                    <label for="price-kop-input"></label>
                    <input type="text" id="price-kop-input" name="priceKop" value="${priceKop}" class="w3-input w3-border">
                </div>
                <div class="w3-cell w3-container w3-cell-middle">коп.</div>
            </div>
            <div class="w3-panel">
                <button type="submit" class="w3-button w3-green w3-round-medium">Сохранить</button>
                <c:url var="backUrl" value="/room/list.html"/>
                <a href="${backUrl}" class="w3-button w3-green w3-round-medium">Отменить</a>
                <c:url var="deleteUrl" value="/room/delete.html"/>
                <c:if test="${not empty room.id}">
                    <button type="submit" formaction="${deleteUrl}" class="w3-button w3-red w3-round-medium">Удалить</button>
                </c:if>
            </div>
        </form>
    </div>
</body>
</html>
