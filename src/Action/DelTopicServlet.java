package Action;

import Dao.ReplyInfoDAO;
import Dao.TopicInfoDAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelTopicServlet",urlPatterns = "/deltopic.jhtml")
public class DelTopicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");//帖子ID

        TopicInfoDAO topicInfoDAO = new TopicInfoDAO();
        ReplyInfoDAO replyInfoDAO = new ReplyInfoDAO();
        replyInfoDAO.delReplyInfoByTid(Integer.parseInt(id));//删帖子先删评论
        topicInfoDAO.delTopicInfoById(Integer.parseInt(id));



    }
}
