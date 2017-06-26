<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html ng-app="mainApp">
<head>
    <title>Main page</title>
    <script src="/js/bundle.js"></script>
    <base href="/">
</head>
<body>

    <%--<div id="app">

    </div>

    <ul>
        <li><a href="home">Home</a></li>
        <li><a href="about">About</a></li>
        <li><a href="contact">Contact</a></li>
    </ul>--%>

    <div ng-view id="app"></div>

</body>
</html>
