package action.impl;

import action.Action;
import page.Page;
import page.PageFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.ServiceConstants.PAGE;

/**
 * @author Arsalan
 */
public class GetPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter(PAGE);
        Page page = PageFactory.getPage(path);
        page.show(request, response);
    }
}
