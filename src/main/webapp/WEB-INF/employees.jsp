<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Employees</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<h2 align="center">Employee list</h2>

<table class="table" cellpadding="10" border="1" align="center">

    <th>Name</th>
    <th>Age</th>
    <th>Birthday</th>
    <th>Edit</th>
    <th>Delete</th>


    <c:forEach var="employee" items="${employees}">

        <tr>
            <td>
                    ${employee.name}
            </td>

            <td>
                    ${employee.age}
            </td>

            <td>
                    ${employee.dateOfBirth}
            </td>

            <td>
                <a href="employee_controller?action=/getPage&page=editEmployee&employeeId=${employee.id}&departmentId=${departmentId}&getPage=true}">Edit</a>
            </td>

            <td>
                <a href="employee_controller?action=/deleteEmployee&employeeId=${employee.id}&departmentId=${departmentId}">X</a>
            </td>

        </tr>



    </c:forEach>

        <tr>
            <td>
                <a href="javascript:history.back()">Go Back</a>
            </td>

            <td></td>

            <td></td>

            <td colspan="2"><a href="employee_controller?action=/getPage&page=createEmployee&departmentId=${departmentId}">Add new Employee</a></td>
        </tr>


</table>

</body>
</html>
