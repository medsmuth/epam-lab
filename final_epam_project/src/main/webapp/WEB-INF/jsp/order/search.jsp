<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<u:page title="Заказ комнаты">
    <c:if test="${not empty param.error}">
        <div class="w3-panel w3-pale-red w3-round-small">
            <h2>Ошибка</h2>
            <p>${param.error}</p>
        </div>
    </c:if>
    <c:url var="saveUrl" value="/order/create.html"/>
    <form action="${saveUrl}" method="post" class="w3-container w3-pale-yellow w3-padding">
        <div class="w3-panel">
            <label for="numberOfSeats-input" class="w3-large">Количество мест:</label>
            <input type="text" id="numberOfSeats-input" name="numberOfSeats" class="w3-input w3-border">
        </div>
        <div class="w3-panel">
            <label class="w3-large">Тип комнаты:</label>
                <%--@elvariable id="types" type="java.util.List"--%>
            <c:forEach var="type" items="${types}">
                <%--@elvariable id="type" type="by.vsu.hotel.domain.ApartmentType"--%>
                <div>
                    <input type="radio" id="type-${type.id}-input" name="type" value="${type.id}" class="w3-radio">
                    <label for="type-${type.id}-input">${type.name}</label>
                </div>
            </c:forEach>
        </div>
        <div class="w3-panel">
            <label for="date-arrival-input" class="w3-large">Дата заезда:</label>
            <input type="date" id="date-arrival-input" name="dateArrival" class="w3-input w3-border">
        </div>
        <div class="w3-panel">
            <label for="date-departure-input" class="w3-large">Дата заезда:</label>
            <input type="date" id="date-departure-input" name="dateDeparture" class="w3-input w3-border">
        </div>
        <div class="w3-panel">
            <button type="submit" class="w3-button w3-green w3-round-medium">Заказать</button>
        </div>
    </form>
</u:page>
