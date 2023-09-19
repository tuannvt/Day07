<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC</title>
</head>
<body>
	<h2>User Registration Resul</h2>
         <table>
                    <tr>
                        <td>First Name </td>
                        <td>${user.firstName}</td>
                    </tr>
                    <tr>
                        <td>Last Name </td>
                        <td>${user.lastName} </td>
                    </tr>
                    <tr>
                        <td>Password </td>
                        <td>${user.password}</td>
                    </tr>
                    <tr>
                        <td>Detail </td>
                        <td>${user.detail}</td>
                    </tr>
                    <tr>
                        <td>Birth Date </td>
                        <td>${user.birthDate}</td>
                    </tr>
                    <tr>
                        <td>Gender </td>
                        <td>${user.gender} </td>
                    </tr>
                    <tr>
                        <td>Country </td>
                        <td>${user.country} </td>
                    </tr>
                    <tr>
                        <td>Non Smoking </td>
                        <td>${user.nonSmoking}  </td>
                    </tr>
                </table>
</body>
</html>
