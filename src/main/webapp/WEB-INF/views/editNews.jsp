<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<form:form action="editNews?id=${news.id}" method="POST" modelAttribute="news" name="editNews">
    <form:input type="hidden" path="id" id="id"/>
    <table align="center">
        <tr>
            <td><label for="title">News title: </label></td>
            <td><form:input path="title" id="title"/></td>
        </tr>
        <tr>
            <td>News description: </td>
            <td><p><textarea rows="50" cols="80" name="text" value="text" id="text">${news.getDescription()}</textarea></p></td>

        </tr>
        <tr>
            <td><label for="desc">Date: </label></td>
            <td><form:input path="date" id="desc"/></td>
        </tr>
        <tr>
            <td>Choose a news category: </td>
            <td>
                <select name="category">
                    <c:forEach items="${newsCategoryList}" var="newsCategory">
                        <option value="${newsCategory.title}">${newsCategory.title}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>


        <tr align="center">
            <td colspan="5">
                <input type="submit" value="Update news"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>