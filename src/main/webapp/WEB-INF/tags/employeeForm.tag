<%@ tag isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="departmentId" required="true"  %>
<%@ attribute name="employeeId" required="false"  %>
<%@ attribute name="action" required="true"%>
<%@ attribute name="button" required="true"  %>

<h2 align="center">Enter new values</h2>

<form action="<c:url value='controller' />" method="post">

    <table class="table" align="center" cellpadding="10">
        <tr>
            <td>
                <input type="text" name="name" placeholder="Name"  value="${errorInputName}">
            </td>

        </tr>
        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${name}</span>
            </td>
        </tr>
        <tr>
            <td>
                <input type="number" name="age" placeholder="Age"  value="${errorInputAge}">
            </td>
        </tr>
        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${age}</span>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="dateOfBirth" placeholder="Date of Birth"  value="${errorInputDate}">
            </td>
        </tr>
        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${dateOfBirth}</span>
            </td>
        </tr>
        <tr>
            <td>
                <input type="email" name="email" placeholder="Email"  value="${errorInputEmail}">
            </td>
        </tr>
        <tr class="noBorder">
            <td colspan="2">
                <span class="errorText">${email}</span>
            </td>
        <tr>

        <tr>
            <td>
                <input type="submit" value="${button}">
            </td>
        </tr>

    </table>

    <input type="hidden" name="employeeId" value="${employeeId}">
    <input type="hidden" name="departmentId" value="${departmentId}">
    <input type="hidden" name="action" value="${action}">

</form>