package Action;

import Dao.SectionInfoDAO;
import Dao.TopicInfoDAO;
import Entity.SectionInfo;
import Entity.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ApostAction",urlPatterns = "/ap.jhtml")
public class ApostAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进来了");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String h = request.getParameter("h");
//        System.out.println(h);

        String content = request.getParameter("add_content");
        String title = request.getParameter("title");
//        title = new String(title.getBytes("ISO-8859-1"),"UTF-8");

        SectionInfoDAO sectionInfoDAO = new SectionInfoDAO();
        String bo_name = request.getParameter("bo_name");
        System.out.println("this is title "+title);
//        title = new String(title.getBytes("ISO-8859-1"), "UTF-8");
//        System.out.println("this is title22 "+title);
        System.out.println("this is boname "+bo_name);
        SectionInfo sectionInfo = sectionInfoDAO.getSectionByName(bo_name);
        int bid = sectionInfo.getSid();//下拉的选中版块ID
        System.out.println("this is bid "+bid);
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("users");
        int uid = userInfo.getUid();
        TopicInfoDAO topicInfoDAO = new TopicInfoDAO();
        topicInfoDAO.insertTopicInfo(title,content,bid,uid);



    }
}
