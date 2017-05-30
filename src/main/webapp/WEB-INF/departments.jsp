<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <title>Departments</title>
</head>
<body>

<h2 align="center">Departments list</h2>

<form id="departments" action="<c:url value='department_controller' />" method="post"></form>

<table class="table" cellpadding="10" border="1" align="center">

    <th>Id</th>
    <th>Name</th>
    <th>Edit</th>
    <th>Delete</th>
    <th>Employees</th>


    <c:forEach var="department" items="${departments}">

        <tr>
            <td>
                    ${department.id}
            </td>

            <td>
                    ${department.name}
            </td>

            <td>
                <a href="department_controller?action=/getPage&page=editDepartment&departmentId=${department.id}">Edit</a>
            </td>

            <td>
                <a href="department_controller?action=/deleteDepartment&departmentId=${department.id}">X</a>
            </td>

            <td>
                <a href="department_controller?action=/getEmployees&departmentId=${department.id}">Employees</a>
            </td>
        </tr>

    </c:forEach>

    <tr>
        <td>
            <a href="javascript:history.back()">Go Back</a>
        </td>

        <td></td>

        <td></td>

        <td colspan="2"><a href="employee_controller?action=/getPage&page=createDepartment">Add new Department</a></td>
    </tr>

</table>
</body>
</html>