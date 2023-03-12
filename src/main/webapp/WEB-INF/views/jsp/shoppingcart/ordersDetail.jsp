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
 <jsp:include page="header.jsp" />
<body>
<div class="container">
<h3>My Orders Detail List</h3>
<table class="table">
    <c:if test="${empty ordersDetail}">
            <p>No data in Orders Detail List</p>
    </c:if>
   <c:if test="${not empty ordersDetail}">
  <thead>
    <tr>

      <th scope="col">Name</th>
      <th scope="col">Description</th>
      <th scope="col">Unit Price</th>
      <th scope="col">Quantity</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="ordersDetail" items="${ordersDetail}" varStatus="index">
        <tr>
              <td>${ordersDetail.product.name}</td>
              <td>${ordersDetail.product.productDescription}</td>
                           <td>${ordersDetail.product.price}</td>
                           <td>${ordersDetail.quantity}</td>
        </tr>
    </c:forEach>

  </tbody>
  </c:if>
</table>

</body>
</html>