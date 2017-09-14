<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html ng-app="mainApp">
<head>
    <title>Main page</title>
    <script src="/js/bundle.js"></script>
    <%--<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
    <base href="/">
</head>
<body>

    <div ui-view ></div>

    <div id="loading"></div>

    <toaster-container toaster-options="{'time-out': 3000, 'close-button':true, 'position-class': 'toast-top-right'}"></toaster-container>

</body>
</html>
