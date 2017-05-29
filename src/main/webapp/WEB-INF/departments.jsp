<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 29.05.17
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2 align="center">Departments</h2>

<div id="departments">
    <table align="center" border="1">

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

            </tr>
        </c:forEach>

    </table>
</div>


</body>
</html>
