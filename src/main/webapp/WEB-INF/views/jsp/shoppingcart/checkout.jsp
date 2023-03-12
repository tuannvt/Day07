<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <html>

      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <link rel="stylesheet" href="<c:url value=" /resources/bootstrap/css/bootstrap.css" />">
        <link rel="stylesheet" href="<c:url value=" /resources/bootstrap/js/bootstrap.min.js" />">
        <title>Products to buy</title>
      </head>
       <style>
          <%@include file="/resources/css/main.css" %>
       </style>
 <jsp:include page="header.jsp" />
      <body>

        <div class="container">
        <h3>Please fill information</h3>
        <br>
          <form:form action="checkOut" method="post" modelAttribute="orders">
            <div class="form-group">
              <label for="exampleInputEmail1">Customer Name (*)</label>
              <form:input type="text" class="form-control col-md-6" path="customerName"/>
              <small class="form-text text-muted"><form:errors path="customerName" class="text-error" /></small>
            </div>
            <div class="form-group">
              <label for="exampleInputEmail1">Customer Address (*)</label>
              <form:input type="text" class="form-control col-md-6" required="true" path="customerAddress"/>
            </div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary col-md-2">Continue</button>
            </div>

          </form:form>
          <div>
      </body>

      </html>