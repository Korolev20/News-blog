<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns:th="http://www.thymeleaf.org">

<%@ page contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" language="java" %>
<html>
<head>
  <title>News by Category</title>
</head>
<body>


<style>
  h2 {font-size: 100%;}
</style>
<h2 align="right"><a href="/">Home</a></h2>

<table align="top">
  <style>
    h1 {
      text-decoration: underline; /* Подчеркивание заголовка */
      font-size: 100%; /* Размер шрифта в процентах */
    }
  </style>

  <tr>
    <td width="50" align="top"><h1><b>Categories</b></h1></td>
  </tr>

  <a href="Category.jsp">
    <c:forEach items="${newsCategoryList}" var="newsCategory">
      <tr>
        <td width="50" align="top"><a href="newsByCategory?category=${newsCategory.title}">${newsCategory.title}</a>

        </td>
      </tr>
    </c:forEach>
  </a>
</table>


<c:forEach items="${newsList}" var="news">

  <table align="center" border="1" cellpadding="1" cellspacing="1" style="width: 50%">
    <tbody>
    <tr>
      <td rowspan="2" style="width: 50%;"><a href="open?id=${news.id}"><h1/>${news.title}</a>&nbsp;</td>
      <td rowspan="2" style="width: 100px;"><h1>Category:<br></h1>${news.newsCategory.title}</td>
      <td rowspan="2" style="width: 100px;"><h1>Date of creation:<br></h1>${news.date}&nbsp;</td>

      <td style="width: 100px;"><a href="editNews?id=${news.id}">edit</a>&nbsp;</td>
    </tr>
    <tr>
      <td><a href="delete?id=${news.id}">delete</a>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="4" style="width: 50%; height: 100px;">${news.getIntro()}&nbsp;</td>
    </tr>
    </tbody>
  </table>

  <p>&nbsp;</p>
</c:forEach>


<table align="center">
  <tr>
    <c:forEach begin="1" end="${pages}" step="1" var="i">
      <td><a href="pages?page=${i}">${i}</a></td>
    </c:forEach>
  </tr>
</table>


</body>
</html>