<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<portlet:defineObjects/>



<div ng-app="mainApp">
    <div ui-view ></div>
</div>

<script type="text/javascript">
    angular.module('mainApp').constant('config', {
        ns : '<portlet:namespace/>',
        getAllDepartments : '<portlet:resourceURL id="getAllDepartments"/>',
        saveDepartment : '<portlet:resourceURL id="saveDepartment"/>',
        deleteDepartment : '<portlet:resourceURL id="deleteDepartment"/>',
        getAllEmployees : '<portlet:resourceURL id="getAllEmployees"/>',
        saveEmployee : '<portlet:resourceURL id="saveEmployee"/>',
        deleteEmployee : '<portlet:resourceURL id="deleteEmployee"/>'
    });
</script>

