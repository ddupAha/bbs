package Action;

import Dao.ReplyInfoDAO;
import Dao.TopicInfoDAO;
import Entity.ReplyInfo;
import Entity.TopicInfo;
import page.DetailPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TopicDetailAction",urlPatterns = "/topicDetail.jhtml")
public class TopicDetailAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TopicInfoDAO topicInfoDAO = new TopicInfoDAO();
        String id = request.getParameter("did");
        HttpSession session = request.getSession();
        session.setAttribute("did",id);
        List<TopicInfo> list = topicInfoDAO.getSelfByid(Integer.parseInt(id));
//        System.out.println(list);
        TopicInfo topicInfo = list.get(0);

        ReplyInfoDAO replyInfoDAO = new ReplyInfoDAO();
        List<DetailPage> replyInfoList = replyInfoDAO.getReplyInfoAndUserInfoById(Integer.parseInt(id));
//        System.out.println("帖子ID："+replyInfoList.get(0).getId());
        request.setAttribute("topicInfo",topicInfo);
        request.setAttribute("comments",replyInfoList);
        topicInfoDAO.updateTopicClickCountById(Integer.parseInt(id));//阅读数加一 访问一次


        int cu_nuid;
        if(session.getAttribute("login_uid")!=null)
            cu_nuid = (int) session.getAttribute("login_uid");
        else
            cu_nuid = - 1;
//        System.out.println("cuid:"+cu_nuid);
        System.out.println(topicInfo.getTuid());
        if(cu_nuid == topicInfo.getTuid())
        {
            request.setAttribute("is_master",1);
        }


//        System.out.println("查找帖子   府板块ID："+id);

        request.getRequestDispatcher("/TopicDetail.jsp").forward(request,response);
    }
}
