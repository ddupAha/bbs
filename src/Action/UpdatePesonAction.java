package Action;

import Dao.UserInfoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UpdatePesonAction",urlPatterns = "/updateperson.jhtml")
public class UpdatePesonAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserInfoDAO userInfoDAO = new UserInfoDAO();
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String face = request.getParameter("face");
        String sex = request.getParameter("sex");
        int s ;
        if(sex.equals("男"))
            s = 0;
        else
            s = 0;

        int id = (int) session.getAttribute("login_uid");
        System.out.println("name :" + name + "  face:" + face + "  sex:"+s);
        userInfoDAO.updateUser(id,name,face,s);


    }
}
