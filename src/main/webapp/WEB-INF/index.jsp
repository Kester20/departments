<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>



    <form id="showDepartments" action="department_controller" method="get">
        <input type="text" name="action" />
    <p style="text-align: center;"><input type="submit" style="text-align:center" value="Show departments"/></p>
    </form>




</body>
</html>