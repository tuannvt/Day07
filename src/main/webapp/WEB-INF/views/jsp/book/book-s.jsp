<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <html>
        <style>
          <%@include file="/resources/css/main.css" %>
        </style>
        <jsp:include page="header.jsp" />

        <body>
          <div class="contain">
            <form action="${action}" method="post" modelAttribute="book">
            <fieldset class="schedule-border">
                <legend class = "schedule-border">
                    <c:out value = "${msg}">
                </legend>



             <div class="form-group">
                <label for="exampleInputEmail1">Book Name (*)</label>
                <form:input type="text" class="form-control col-md-6" path="name" required="true"/>
              </div>
              <div class="form-group">
                <label for="exampleInputEmail1">Author (*)</label>
                <form:input type="text" class="form-control col-md-6" path="author"/>
              </div>
              <div class="form-group">
                <label for="exampleInputEmail1">ISBN (*)</label>
                <form:input type="text" class="form-control col-md-6" path="bookDetails.isbn"/>
              </div>
              <div class="form-group">
                <label for="exampleInputEmail1">Price (*)</label>
                <form:input type="text" class="form-control col-md-6" path="bookDetails.price"/>
              </div>
              <div class="form-group">
                <label for="exampleInputEmail1">Number of page (*)</label>
                <form:input type="number" class="form-control col-md-6" path="bookDetails.numberOfPage"/>
              </div>
              <div class="form-group">
                <label for="exampleInputEmail1">Publish Date (*)</label>
                <form:input type="date" class="form-control col-md-6" path="bookDetails.publishDate"/>
              </div>

              <div class="form-group">
                <label for="inputState">Category (*)</label>
                <form:select path="category.id" class="form-control col-md-6"/>
                  <form:option value ="0">Choose...</option>
                  <form:option items="${categoryList}"></option>
                </select>
              </div>
              <button type="submit" class="btn btn-primary col-md-2">Save</button>
              </fieldset>
            </form>
            <div>
        </body>

        </html>