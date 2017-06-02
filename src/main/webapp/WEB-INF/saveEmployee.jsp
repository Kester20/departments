<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <title>Employee</title>
</head>
<body>

<h2 align="center">Enter new values</h2>

<form action="<c:url value='/employeeAction' />" method="post">

    <table class="table" align="center" cellpadding="10">
        <tr>
            <td>
                <input type="text" name="name" placeholder="Name"  value="${nameErrorInput}">
            </td>

        </tr>
        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${errorMap['name']}</span>
            </td>
        </tr>
        <tr>
            <td>
                <input type="number" name="age" placeholder="Age"  value="${ageErrorInput}">
            </td>
        </tr>
        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${errorMap['age']}</span>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="dateOfBirth" placeholder="Date of Birth"  value="${dateOfBirthErrorInput}">
            </td>
        </tr>
        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${errorMap['dateOfBirth']}</span>
            </td>
        </tr>
        <tr>
            <td>
                <input type="email" name="email" placeholder="Email"  value="${emailErrorInput}">
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

    <input type="hidden" name="employeeId" value="${employeeId}">
    <input type="hidden" name="departmentId" value="${departmentId}">

</form>

</body>
</html>