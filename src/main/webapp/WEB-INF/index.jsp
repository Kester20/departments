<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Departments</title>
</head>
<body>


<form id="departments" action="<c:url value='department_controller' />" method="get"></form>

<div style="text-align:center">
    <input type="hidden" name="action" value="/showDepartments" form="departments"/>
    <input type="submit" value="Show departments" form="departments" />
</div>

</body>
</html>