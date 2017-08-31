<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html ng-app="mainApp">
<head>
    <title>Main page</title>
    <script src="/js/bundle.js"></script>
    <base href="/">
</head>
<body>

    <div ui-view ></div>

    <div id="loading"></div>

    <toaster-container toaster-options="{'time-out': 3000, 'close-button':true, 'animation-class': 'toast-center'}"></toaster-container>

</body>
</html>
