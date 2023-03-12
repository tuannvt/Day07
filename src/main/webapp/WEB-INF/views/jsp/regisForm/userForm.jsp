<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>User Registration Form</title>
        </head>

        <body>
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
                            <input type="submit" value="Submit">
                        </td>
                    </tr>
                </table>
            </mvc:form>
        </body>

        </html>