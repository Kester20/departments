package com.aimprosoft.noormal.servicebuilder.service.messaging;

import com.aimprosoft.noormal.servicebuilder.service.ClpSerializer;
import com.aimprosoft.noormal.servicebuilder.service.DepartmentLocalServiceUtil;
import com.aimprosoft.noormal.servicebuilder.service.EmployeeLocalServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;


public class ClpMessageListener extends BaseMessageListener {
    public static String getServletContextName() {
        return ClpSerializer.getServletContextName();
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        String command = message.getString("command");
        String servletContextName = message.getString("servletContextName");

        if (command.equals("undeploy") &&
                servletContextName.equals(getServletContextName())) {
            DepartmentLocalServiceUtil.clearService();

            EmployeeLocalServiceUtil.clearService();
        }
    }
}
