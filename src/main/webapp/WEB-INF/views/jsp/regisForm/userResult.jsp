<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>User Registration Result</title>
        </head>

        <body>
            <h1>User Registration Result</h1>
            <mvc:form modelAttribute="user" action="result">
                <table>
                    <tr>
                        <td>
                            Name:
                        </td>
                        <td>
                            ${user.name}
                        </td>
                    </tr>
                    <td>
                        Last Name:
                    </td>
                    <td>
                        ${user.lastName}
                    </td>
                    </tr>
                    <tr>
                        <td>
                            Password:
                        </td>
                        <td>
                            ${user.password}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Detail:
                        </td>
                        <td>
                            ${user.detail}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Birth Date:
                        </td>
                        <td>
                            ${user.birthDate}
                        </td>
                    </tr>
                    <td>
                        Gender:
                    </td>
                    <td>
                        ${user.gender}
                    </td>
                    </tr>
                    <tr>
                        <td>
                            Country:
                        </td>
                        <td>
                            ${user.country}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Non Smoking:
                        </td>
                        <td>
                            ${user.noSmoking}
                        </td>
                    </tr>
                </table>
            </mvc:form>
        </body>

        </html>