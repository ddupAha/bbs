package Action;

import Dao.UserInfoDAO;
import Entity.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PersonAction",urlPatterns = "/person.jhtml")
public class PersonAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int uid = (int) session.getAttribute("login_uid");
        UserInfoDAO userInfoDAO = new UserInfoDAO();
        UserInfo userInfo = userInfoDAO.getUserInfoByID(uid);

        request.setAttribute("person",userInfo);
        request.getRequestDispatcher("/personal.jsp").forward(request,response);


    }
}
