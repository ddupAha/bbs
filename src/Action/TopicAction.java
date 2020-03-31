package Action;

import Dao.SectionInfoDAO;
import Dao.TopicInfoDAO;
import Entity.SectionInfo;
import Entity.TopicInfo;
import page.ListPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TopicAction",urlPatterns = "/topic.jhtml")
public class TopicAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TopicInfoDAO topicInfoDAO = new TopicInfoDAO();
        String id = request.getParameter("tid");
        HttpSession session = request.getSession();
        session.setAttribute("tid",id);
        List<TopicInfo> list = topicInfoDAO.getTopicById(Integer.parseInt(id));
        request.setAttribute("topic_list",list);
        int pageNos;
        if (request.getParameter("pageNos") == null
                || Integer.parseInt(request.getParameter("pageNos")) < 1) {
            pageNos = 1;
        } else {
            pageNos = Integer.parseInt(request.getParameter("pageNos"));
        }
        session.setAttribute("pageNos", pageNos);
        // 定义总页数并存到session中
        int countPage = list.size()/5+1;
        if(list.size() % 5 == 0)
            countPage = list.size() / 5;
        session.setAttribute("countPage", countPage);
        SectionInfoDAO sectionInfoDAO = new SectionInfoDAO();
        String board_name = sectionInfoDAO.getPSectionById(Integer.parseInt(id)).getSname();
        String location=null;
        if(session.getAttribute("location")!=null)
             location = (String) session.getAttribute("location");
        if(!location.contains(">"))
            session.setAttribute("location",session.getAttribute("location")+">>>"+board_name);



//        System.out.println("查找帖子   府板块ID："+id);

        request.getRequestDispatcher("/Topics.jsp").forward(request,response);

    }
}
