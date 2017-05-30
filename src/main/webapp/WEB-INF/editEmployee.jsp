<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Employee</title>
</head>
<body>

<h2 align="center">Enter new values</h2>

<form action="<c:url value='employee_controller' />" method="post">

    <table class="table" cellpadding="10" border="1" align="center">
        <tr>
            <td>
                <input type="text" name="name" placeholder="Name">
            </td>

            <td>
                <input type="number" name="age" placeholder="Age">
            </td>

            <td>
                <input type="text" name="dateOfBirth" placeholder="Date of Birth">
            </td>

        </tr>

        <tr>
            <td>
                <input type="submit" value="Edit">
            </td>
        </tr>

    </table>

    <input type="hidden" name="employeeId" value="${employeeId}">
    <input type="hidden" name="departmentId" value="${departmentId}">
    <input type="hidden" name="action" value="/editEmployee">


</form>

</body>
</html>
