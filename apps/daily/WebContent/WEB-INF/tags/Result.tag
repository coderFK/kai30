<%@ tag language="java" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<c:if test="${requestScope.searchResult!=null && requestScope.searchResult.isEmpty()}">
		抱歉，未在标题中查找到您要的内容
	</c:if>
	
	<c:forEach var="daily" items="${requestScope.searchResult}">
   		<a href='&#35;${daily.getDate().getTime() }' id="daily_link2"><c:out value="${daily.getTitle()}"/></a>
   		<br />
    </c:forEach>
