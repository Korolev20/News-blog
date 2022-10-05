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
</style>
<h2  align="right"><a href="/">Home</a></h2>



<form:form action="addCategory" method="POST" modelAttribute="newsCategory" name="addCategory">
  <table align="center">
    <tr>
      <td><label for="title">News title: </label></td>
      <td><form:input path="title" id="title"/></td>
    </tr>
    <tr>
      <td colspan="3" align="center">
        <input type="submit" value="Add" align="center"/>
      </td>
    </tr>
  </table>
</form:form>

</body>
</html>