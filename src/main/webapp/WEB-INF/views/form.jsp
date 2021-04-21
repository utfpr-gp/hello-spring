<%--
    Document   : form
    Created on : Oct 10, 2018, 6:23:10 PM
    Author     : ronifabio
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Formulário de Mensagem</title>
    </head>
    <body>
        <h1>Envie uma mensagem para o mural:</h1>
        <h2>Nova mensagem de novo.</h2>
        <form:form method="post" modelAttribute="m" action="${pageContext.request.contextPath}/mensagens/form-redirect">
            Nome:
            <form:input path="name"/>
            Mensagem:
            <form:input path="message"/>
            <form:button type="submit">Enviar</form:button>
        </form:form>
    </body>
</html>

