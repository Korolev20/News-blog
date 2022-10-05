%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>

<style>
    h2 {font-size: 100%;}
</style>
<h2 align="right"><a href="/">Home</a></h2>

<form:form action="open?id=${news.id}" method="POST" modelAttribute="news" name="open">
    <form:input type="hidden" path="id" id="id"/>
    <table align="center">

        <td>${news.getDescription()}</td>
    </table>

    <h2 align="center"> <a href="editNews?id=${news.id}">edit</a></h2>
</form:form>
</body>
</html>