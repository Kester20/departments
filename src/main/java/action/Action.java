package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Arsalan
 */
public interface Action {

    public String execute(HttpServletRequest request, HttpServletResponse response);
}
