package Action;

import Dao.UserInfoDAO;
import Entity.User;
import Entity.UserInfo;
import globle.Constants;
import globle.Globle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginAction",urlPatterns = "/login.jhtml")
public class LoginAction extends HttpServlet {
    private UserInfoDAO udao = new UserInfoDAO();
    private ServletConfig config;
    private UserInfo user = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String s = config.getInitParameter("character");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //
        String userName = request.getParameter("uname");
        String passWord = request.getParameter("upass");


        String code = request.getParameter("cap");
        if(!Globle.getCode().equalsIgnoreCase(code)){
            request.setAttribute("error_msg","验证码不正确");
            request.setAttribute(Constants.GLOBAL_ERROR_KEY, "验证码不正确！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }


        if (udao.checkLogin(userName, passWord)) {
            user = udao.getUserInfo(userName);
            HttpSession session = request.getSession();//用户登录会话开始
            session.setAttribute("users", user);
            session.setAttribute("type", user.getUtype());
            session.setAttribute("login_uid", user.getUid());
            session.setAttribute("username", userName);
            session.setAttribute("location", "所有版块");
            session.setAttribute("is_login", 1);
            out.println("<h1>" + "登录！" + "</h1>");
            request.getRequestDispatcher("/mainborad.jhtml?bid=-1").forward(request,response);
        } else {
            out.print("<script>" + "alert('用户名或密码错误');"
                     + "window.history.back();"+"</script>");
        }

    }

    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }
}
