<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: marcin
  Date: 21.09.2022
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css"/>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>
    <div id="container">
        <h3>Save Customer</h3>
        <form:form action="saveCustomer" modelAttribute="customer" method="POST">

            <!-- hidden form to update and not save new customer (app populates this field with existing customers id -->
            <form:hidden path="id"/>
            <table>
                <tbody>
                    <tr>
                        <td>First Name:</td>
                        <td><form:input path="firstName"/></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><form:input path="lastName"/></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><form:input path="email"/></td>
                    </tr>
                    <tr>
                        <td><label></label></td>
                        <td><input type="submit" value="Save" class="save"></td>
                    </tr>
                </tbody>
            </table>
        </form:form>
        <div style="clear: both"></div>
        <input type="button" value="Back to list"
               onclick="window.location.href='${pageContext.request.contextPath}/customer/list'; return false"
               class="add-button">
    </div>
</body>
</html>
