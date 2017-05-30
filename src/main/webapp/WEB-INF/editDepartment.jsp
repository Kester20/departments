<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <title>Departments</title>
</head>
<body>

<h2 align="center">Enter new value</h2>


<form id="departments" action="<c:url value='department_controller' />" method="post">

    <table class="table" cellpadding="10" border="1" align="center">
        <tr>
            <td>
                <input type="text" name="name" placeholder="Name">
            </td>

            <td>
                <input type="submit" value="Edit">
            </td>
        </tr>
    </table>

    <input type="hidden" name="id" value="${id}">
    <input type="hidden" name="action" value="/editDepartment">


</form>


</body>
</html>