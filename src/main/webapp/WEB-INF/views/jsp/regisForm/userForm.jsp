<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
        <html>

        <head>
        <link rel="stylesheet" type="text/css" href='${pageContext.request.getContextPath()}/webjars/bootstrap/5.1.3/css/bootstrap.min.css' />
        <script type="text/javascript" src="${pageContext.request.getContextPath()}/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>User Registration Form</title>
         <style>
           <%@include file="/resources/css/main.css" %>
         </style>
            <jsp:include page="header.jsp" />
        </head>

        <body>
        <div class="contain pt-10">
            <h1>User Registration Form</h1>
            <mvc:form modelAttribute="user" action="result">
                <table>
                    <tr>
                        <td>
                            First Name (*)
                        </td>
                        <td>
                            <mvc:input path="name" required="true" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Last Name (*)
                        </td>
                        <td>
                            <mvc:input path="lastName" required="true" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Password(*)
                        </td>
                        <td>
                            <mvc:password path="password" required="true" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Detail
                        </td>
                        <td>
                            <mvc:textarea path="detail"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Birth Date
                        </td>
                        <td>
                            <mvc:input path="birthDate" type="date" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Gender
                        </td>
                        <td>
                            <mvc:radiobuttons path="gender" items="${genders}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Country
                        </td>
                        <td>
                            <mvc:select path="country" items="${countries}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                             Non Smoking
                        </td>
                        <td>
                            <mvc:checkbox path="noSmoking" checked="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input class="btn btn-primary" type="submit" value="Submit">
                        </td>
                    </tr>
                </table>
            </mvc:form>
        <div>
        </body>

        </html>