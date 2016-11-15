<%@ tag language="java" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<c:forEach var="b" items="${requestScope.dailys}">
    <hr />
    	用户：<em>${b.getUsername()}</em>
    	 发布日期：<em><fmt:formatDate value="${b.getDate()}" dateStyle="full" timeStyle="full"/></em>
    	<br />
    	 主题：<em><c:out value="${b.getSubject()}"/></em>
    	 标题：<em><c:out value="${b.getTitle()}"/></em>
        <br />
        <br />
                    内容：
        <br />           	
        <b><c:out value="${b.getContent()}"/></b>
        <br />
        <br />
        <c:if test="${sessionScope.login!=null}">
        	<a href='delete.do?date=${b.getDate().getTime()}' id="daily_link">删除</a>
        </c:if>
    </c:forEach>
