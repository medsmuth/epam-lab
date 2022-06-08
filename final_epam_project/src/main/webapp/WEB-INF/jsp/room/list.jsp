<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<u:page title="Список комнат">
    <c:if test="${not empty param.message}">
        <div class="w3-panel w3-pale-green w3-round-small">
            <p>${param.message}</p>
        </div>
    </c:if>
    <table class="w3-table-all">
        <tr class="w3-yellow">
            <th>Номер</th>
            <th>Тип</th>
            <th>Количество мест</th>
            <th>Цена</th>
            <th></th>
        </tr>
            <%--@elvariable id="rooms" type="java.util.List"--%>
        <c:forEach var="room" items="${rooms}">
            <%--@elvariable id="room" type="by.vsu.hotel.domain.Room"--%>
            <tr>
                <td>${room.number}</td>
                <td>${room.apartmentType.name}</td>
                <td>${room.numberOfSeats}</td>
                <td><fmt:formatNumber value="${room.price / 100}" maxFractionDigits="0"/>&nbsp;руб.,&nbsp;<fmt:formatNumber value="${room.price % 100}" pattern="00"/>&nbsp;коп.</td>
                <td>
                    <c:url var="editUrl" value="/room/edit.html">
                        <c:param name="id" value="${room.id}"/>
                    </c:url>
                    <a href="${editUrl}" class="w3-button w3-small w3-green w3-round-medium">&#9997;</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:url var="editUrl" value="/room/edit.html"/>
    <div class="w3-margin-top"><a href="${editUrl}" class="w3-button w3-xlarge w3-circle w3-green">+</a></div>
</u:page>
