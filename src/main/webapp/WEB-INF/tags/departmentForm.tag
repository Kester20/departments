<%@ tag isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="departmentId" required="true"  %>
<%@ attribute name="action" required="true"%>
<%@ attribute name="button" required="true"  %>

<h2 align="center">Enter new value</h2>


<form id="departments" action="<c:url value='department_controller' />" method="post">

    <table class="table" cellpadding="10" border="1" align="center">
        <tr>
            <td>
                <input type="text" name="name" placeholder="Name">
            </td>

            <td>
                <input type="submit" value="${button}">
            </td>
        </tr>
    </table>

    <input type="hidden" name="departmentId" value="${departmentId}">
    <input type="hidden" name="action" value="${action}">

</form>