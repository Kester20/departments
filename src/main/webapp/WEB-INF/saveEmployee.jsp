<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="/css/style.css" rel="stylesheet" type="text/css"/>
    <title>Employee</title>
</head>
<body>

<h2 align="center">Enter new values</h2>

<form:form action="/employee/save" method="post" modelAttribute="employeeForm">

    <table class="table" align="center" cellpadding="10">
        <tr>
            <td>
                <spring:bind path="name">
                    <input type="text" name="name" placeholder="Name"  value="${param.name}">
                </spring:bind>
            </td>

        </tr>
        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${errorMap['name']}</span>
            </td>
        </tr>
        <tr>
            <td>
                <spring:bind path="age">
                    <input type="number" name="age" placeholder="Age"  value="${param.age}">
                </spring:bind>
            </td>
        </tr>
        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${errorMap['age']}</span>
            </td>
        </tr>
        <tr>
            <td>
                <spring:bind path="dateOfBirth">
                    <input type="text" name="dateOfBirth" placeholder="Date of Birth"  value="${param.dateOfBirth}">
                </spring:bind>
            </td>
        </tr>
        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${errorMap['dateOfBirth']}</span>
            </td>
        </tr>
        <tr>
            <td>
                <spring:bind path="email">
                    <input type="email" name="email" placeholder="Email"  value="${param.email}">
                </spring:bind>
            </td>
        </tr>
        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${errorMap['email']}</span>
            </td>
        <tr>

        <tr>
            <td>
                <input type="submit" value="Save">
            </td>
        </tr>

    </table>

    <input type="hidden" name="employeeId" value="${param.employeeId}">
    <input type="hidden" name="departmentId" value="${param.departmentId}">

</form:form>

</body>
</html>