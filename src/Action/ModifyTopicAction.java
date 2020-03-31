package Action;

import Dao.TopicInfoDAO;
import Entity.TopicInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModifyTopicAction",urlPatterns = "/mtopic.jhtml")
public class ModifyTopicAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        System.out.println("modify : "+id + "   " + name);

        TopicInfoDAO topicInfoDAO = new TopicInfoDAO();
        TopicInfo topicInfo = topicInfoDAO.getEditTopicInfoById(Integer.parseInt(id));
        topicInfoDAO.updateTopicInfoById(name,topicInfo.getTcontents(),Integer.parseInt(id));

    }
}
