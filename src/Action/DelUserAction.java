package Action;

import Dao.UserInfoDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelUserAction",urlPatterns = "/delUser.jhtml")
public class DelUserAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("user_id"));
        System.out.println(id);
        UserInfoDAO userInfoDAO=new UserInfoDAO();
        userInfoDAO.delUserInfo(id);

    }
}
