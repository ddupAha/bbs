package Action;

import Dao.UserInfoDAO;
import Entity.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModifyUserTypeAction", urlPatterns = "/muserType.jhtml")
public class ModifyUserTypeAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String type = request.getParameter("type");

        UserInfoDAO userInfoDAO = new UserInfoDAO();
        UserInfo userInfo = userInfoDAO.getUserInfoByID(Integer.parseInt(id));
        userInfoDAO.updateUserType(Integer.parseInt(id),Integer.parseInt(type));


    }
}
