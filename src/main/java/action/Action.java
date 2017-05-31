package action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Arsalan
 */
public interface Action {

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
