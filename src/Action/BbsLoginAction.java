package Action;

import Dao.UserDao;
import Dao.UserInfoDAO;
import Entity.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BbsLoginAction",urlPatterns = "/blogin.jhtml")
public class BbsLoginAction extends HttpServlet {
    private UserInfo user = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("bname");
        String passWord = request.getParameter("password");
        UserInfoDAO udao = new UserInfoDAO();
        if (udao.checkLogin(userName, passWord)) {
            user = udao.getUserInfo(userName);
            HttpSession session = request.getSession();//用户登录会话开始
//            session.setAttribute("users", user);
//            session.setAttribute("login_uid", user.getUid());
            session.setAttribute("b_username", userName);
//            session.setAttribute("location", "所有版块");
            session.setAttribute("b_is_login", 1);
//            out.println("<h1>" + "登录！" + "</h1>");
            request.getRequestDispatcher("/bbs_index.jsp").forward(request,response);
        } else {

//            out.print("<script>" + "alert('用户名或密码错误');"
//                    + "window.history.back();"+"</script>");
        }


    }
}
