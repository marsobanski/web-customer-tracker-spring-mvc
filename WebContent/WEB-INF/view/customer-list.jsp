<%--
  Created by IntelliJ IDEA.
  User: marcin
  Date: 11.07.2022
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.luv2code.springdemo.util.SortUtils" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Collections" %>
<html>
<head>
    <title>List Customers</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">

            <!-- add customer button -->
            <input type="button" value="Add Customer"
                   onclick="window.location.href='showFormForAdd'; return false"
                   class="add-button"/>

            <!--  add a search box -->
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName"/>
                <input type="submit" value="Search" class="add-button"/>
            </form:form>

            <!-- construct a sort link for first name -->
            <c:url var="sortLinkFirstName" value="/customer/list">
                <c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
            </c:url>

            <!-- construct a sort link for last name -->
            <c:url var="sortLinkLastName" value="/customer/list">
                <c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
            </c:url>

            <!-- construct a sort link for email -->
            <c:url var="sortLinkEmail" value="/customer/list">
                <c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
            </c:url>

            <table>
                <tr>
                    <th><a href="${sortLinkFirstName}">First Name</a></th>
                    <th><a href="${sortLinkLastName}">Last Name</a></th>
                    <th><a href="${sortLinkEmail}">Email</a></th>
                    <th>Action</th>
                </tr>

                <!-- loop customers -->
                <c:forEach var="customer" items="${customers}">

                    <!-- create link to update customer with customerId param-->
                    <c:url var="updateLink" value="/customer/showFormForUpdate">
                        <c:param name="customerId" value="${customer.id}"/>
                    </c:url>

                    <!-- create link to delete customer with customerId param-->
                    <c:url var="deleteLink" value="/customer/deleteCustomer">
                        <c:param name="customerId" value="${customer.id}"/>
                    </c:url>

                    <tr>
                        <td>${customer.firstName}</td>
                        <td>${customer.lastName}</td>
                        <td>${customer.email}</td>
                        <td>
                            <a href="${updateLink}">Update</a>
                            |
                            <a href="${deleteLink}" onclick="if (!(confirm('Are you sure You Want to delete customer ${customer.firstName} ${customer.lastName}?'))) return false">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
