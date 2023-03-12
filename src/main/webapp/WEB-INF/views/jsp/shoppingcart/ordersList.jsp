<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>">
<title>Products to buy</title>
</head>

<body>
 <jsp:include page="header.jsp" />

<div class="container">
<h3>My Orders List</h3>
<br>
<table class="table">
    <c:if test="${empty ordersList}">
        <p>No data in Orders List</p>
    </c:if>
   <c:if test="${not empty ordersList}">
  <thead>
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Customer Name</th>
      <th scope="col">Customer Address</th>
      <th scope="col">Orders Detail</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="orders" items="${ordersList}" varStatus="index">
        <tr>
              <th scope="row">${orders.id}</th>
              <td>${orders.customerName}</td>
              <td>${orders.customerAddress}</td>
               <td><a href="ordersDetail/${orders.id}">View Detail</a></td>
        </tr>
    </c:forEach>

  </tbody>
  </c:if>
</table>

</body>
</html>