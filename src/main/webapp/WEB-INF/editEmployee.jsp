<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg" %>

<!DOCTYPE html>
<html>
<head>
    <title>Employee</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<tg:employeeForm departmentId="${departmentId}" employeeId="${employeeId}" action="/employeeAction" button="Edit employee"/>

</body>
</html>
