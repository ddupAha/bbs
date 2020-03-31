package Action;

import Dao.UserInfoDAO;
import Entity.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisteAction",urlPatterns = "/regite.jhtml")
public class RegisteAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("uname");
        UserInfoDAO userInfoDAO = new UserInfoDAO();
        UserInfo userInfo = userInfoDAO.getUserInfoByName(name);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        System.out.println("outout111");
//        System.out.println("uname:"+name);
        if(userInfo!=null) {
//            request.setAttribute("error", "用户名已存在");
            response.getWriter().write("<span style='color:red'>用户名已存在</span>");
            return ;
        }
        else if(!name.equals(""))
            response.getWriter().write("<span style='color:red'>该用户名可用！</span>");
//        System.out.println("outout2222");
        String pwd = request.getParameter("upass");
        String r_pwd = request.getParameter("confirm_password");
//        System.out.println(pwd);
//        System.out.println(r_pwd);
        if(!pwd.equals(r_pwd))
        {
//            System.out.println("两次不一样！");
            request.setAttribute("error", "两次输入密码不一样！");
//            request.getRequestDispatcher("/registe.jsp").forward(request,response);
            return ;
        }
        String sex = request.getParameter("gender");
        int s = Integer.parseInt(sex);
        System.out.println("sex:"+s);
        String face = request.getParameter("face");
        System.out.println("face:"+face);
        userInfoDAO.insert(name,pwd,s,face);
        response.sendRedirect("/login.jsp");
    }
}
