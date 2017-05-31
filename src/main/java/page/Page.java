package page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Arsalan
 */
public interface Page {

    void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
