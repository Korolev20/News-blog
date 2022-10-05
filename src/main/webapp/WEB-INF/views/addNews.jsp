<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<style>
    h2 {font-size: 100%;}
   body{
       background-color: antiquewhite;
   }
</style>
<h2 align="right"><a href="/">Home</a></h2>

<form:form action="addNews" method="POST" modelAttribute="news" name="addNews">
    <table align="center">
        <tr>
            <td><label for="title">News title: </label></td>
            <td><form:input path="title" id="title"/></td>
        </tr>
        <tr>
            <td> News description:</td>
            <td><p><textarea rows="50" cols="80" name="text" value="text" id="title"></textarea></p></td>
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
        <tr>
            <td colspan="3" align="center">
                <input type="submit" value="Add" align="center"/>
            </td>
        </tr>
    </table>

</form:form>

<script type="text/javascript" language="JavaScript">
    function enableTab(id) {
        var el = document.getElementById(id);
        el.onkeydown = function(e) {
            if (e.keyCode === 9) {
                var val = this.value,
                    start = this.selectionStart,
                    end = this.selectionEnd;
                this.value = val.substring(0, start) + '\t' + val.substring(end);
                this.selectionStart = this.selectionEnd = start + 1;
                return false;
            }
        };
    }
    enableTab('title');
</script>

</body>
</html>