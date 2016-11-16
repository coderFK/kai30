<%@ tag language="java" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<c:forEach var="b" items="${requestScope.dailys}">
    	用户：<em>${b.getUsername()}</em>
    	 发布日期：<em><fmt:formatDate value="${b.getDate()}" dateStyle="full" timeStyle="full"/></em>
    	<br />
    	 分类：<a href="/daily/user/${b.getUsername()}?subject=${b.getSubject()}" id="daily_link2"><c:out value="${b.getSubject()}"/></a>
    	<br />
    	 标题：<b ><c:out value="${b.getTitle()}"/></b>
        <br />
        <br />
                    内容：
        <br />           	
        
        <c:forEach var="line" items="${b.getContent().getTextList()}">
        	<b>${line}</b>
        </c:forEach>
        
        <br />
        <br />
        <c:if test="${b.getUsername().equals(sessionScope.login)}">
        	<a href='/daily/delete.do?date=${b.getDate().getTime()}' id="daily_link">删除</a>
        </c:if>
        <hr />
    </c:forEach>
