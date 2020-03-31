package Action;

import Dao.ReplyInfoDAO;
import Dao.TopicInfoDAO;
import Entity.TopicInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelReplyAction",urlPatterns = "/delr.jhtml")
public class DelReplyAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        ReplyInfoDAO replyInfoDAO = new ReplyInfoDAO();
        replyInfoDAO.delReplyInfoById(Integer.parseInt(id));
        String tid = request.getParameter("tid");
        TopicInfoDAO topicInfoDAO = new TopicInfoDAO();
        TopicInfo topicInfo = topicInfoDAO.getEditTopicInfoById(Integer.parseInt(tid));
        topicInfoDAO.updateTopicReplyCountById(Integer.parseInt(tid),topicInfo.getTreplycount()-1);
    }
}
