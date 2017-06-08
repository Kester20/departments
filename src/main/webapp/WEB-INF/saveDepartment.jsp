<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="/css/style.css" rel="stylesheet" type="text/css"/>
    <title>Departments</title>
</head>
<body>

<h2 align="center">Enter new value</h2>


<form id="departments" action="<c:url value='/department/save' />" method="post">

    <table class="table" align="center" cellpadding="10">
        <tr>
            <td>
                <input type="text" name="name" placeholder="Name" value="${param.name}">
            </td>

            <td>
                <input type="submit" value="Save">
            </td>
        </tr>

        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${errorMap['name']}</span>
            </td>
        <tr>
    </table>

    <input type="hidden" name="departmentId" value="${param.departmentId}">

</form>

</body>
</html>