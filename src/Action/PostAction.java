package Action;

import Dao.ReplyInfoDAO;
import Dao.TopicInfoDAO;
import Entity.ReplyInfo;
import Entity.TopicInfo;
import Entity.UserInfo;
import javafx.scene.input.ScrollEvent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "PostAction",urlPatterns = "/post.jhtml")
public class PostAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String content = request.getParameter("post_content");
        ReplyInfoDAO replyInfoDAO = new ReplyInfoDAO();
        ReplyInfo replyInfo = new ReplyInfo();
//        System.out.println("this is  " + content);
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("users");
        int uid = userInfo.getUid();
        int tid = Integer.parseInt((String) session.getAttribute("did"));
        int bid = Integer.parseInt((String) session.getAttribute("tid"));
//        System.out.println("user:" + uid);
//        replyInfo.setRtid((Integer) session.getAttribute("tid"));
//        replyInfo.setRsid((Integer) session.getAttribute("bid"));
//        replyInfo.setRuid((Integer) session.getAttribute(userInfo.getUid().toString()));
//        replyInfo.setRtopic(content);
//        replyInfo.setRcontents(content);
//        System.out.println("tid"+tid);
//        System.out.println("bid:"+bid)
        boolean rs = replyInfoDAO.insertReplyInfo(content,content,tid,
                bid,uid);
        TopicInfoDAO topicInfoDAO = new TopicInfoDAO();
        TopicInfo topicInfo = topicInfoDAO.getEditTopicInfoById(tid);
        topicInfoDAO.updateTopicReplyCountById(tid,topicInfo.getTreplycount()+1);
//        System.out.println(rs);
//        System.out.println("11111 final !!!!!");





    }
}
