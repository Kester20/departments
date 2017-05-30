<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <title>Departments</title>
</head>
<body>

<form id="departments" action="<c:url value='department_controller' />" method="post"></form>

<table id="departmentTable" cellpadding="10" border="1" align="center">

    <th>Id</th>
    <th>Name</th>
    <th>Edit</th>
    <th>Delete</th>


    <c:forEach var="department" items="${departments}">

        <tr>
            <td>
                ${department.id}
            </td>

            <td>
                ${department.name}
            </td>

            <td style="text-align:center;">
                <a href="department_controller?action=/editDepartment}">Edit</a>
            </td>

            <td style="text-align:center;">
                <a href="department_controller?action=/deleteDepartment&id=${department.id}">X</a>
            </td>
        </tr>

    </c:forEach>

</table>
</body>
</html>