<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

    <title><spring:message code="message.login.title" /></title>
</head>

<body>
    <form:form method="POST" action="${action.path}" modelAttribute="user">
        <table>
            <tr>
                <td><form:label path="name"><spring:message code="message.login.username" /></form:label></td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td><form:label path="pwd"><spring:message code="message.login.password" /></form:label></td>
                <td><form:password path="pwd"/></td>
            </tr>
            <tr>
               <c:if test="${action == 'LOGIN'}">
                    <td>
                        <input type="button" onclick="location.href='<spring:url value='register'/>'" value="<spring:message code='message.register.register'/>"/>
                    </td>
                </c:if>

                <c:if test="${action == 'REGISTER'}">
                    <td>
                        <input type="button" onclick="location.href='<spring:url value='login'/>'" value="<spring:message code='message.login.login'/>"/>
                    </td>
                </c:if>

                <td>
                    <input type="submit" value="<spring:message code="message.submit" />"/>
                </td>
            </tr>
        </table>
    </form:form>

</body>
</html>