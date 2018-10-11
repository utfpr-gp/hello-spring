<%--
    Document   : message
    Created on : Oct 10, 2018, 3:36:25 PM
    Author     : ronifabio
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Lista de Mensagens</title>
    </head>
    <body>
        <h1>Lista de Mensagens</h1>
        <ul>
            <c:forEach var="m" items="${messages}">
                <li>${m.name} --> ${m.message}</li>
                </c:forEach>
        </ul>
    </body>
</html>

