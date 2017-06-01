<%@ tag isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="departmentId" required="true"  %>
<%@ attribute name="action" required="true"%>
<%@ attribute name="button" required="true"  %>

<h2 align="center">Enter new value</h2>


<form id="departments" action="<c:url value='controller' />" method="post">

    <table class="table" align="center" cellpadding="10">
        <tr>
            <td>
                <input type="text" name="name" placeholder="Name" value="${nameErrorInput}">
            </td>

            <td>
                <input type="submit" value="${button}">
            </td>
        </tr>

        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${name}</span>
            </td>
        <tr>
    </table>

    <input type="hidden" name="departmentId" value="${departmentId}">
    <input type="hidden" name="action" value="${action}">

</form>