package Action;

import Dao.SectionInfoDAO;
import Entity.SectionInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MainBoardAction",urlPatterns = "/mainborad.jhtml")
public class MainBoardAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SectionInfoDAO sectionInfoDAO = new SectionInfoDAO();
        String id = request.getParameter("bid");
        HttpSession session = request.getSession();
        session.setAttribute("bid",id);
        ArrayList<SectionInfo> list = null;


        if(id.equals("-1"))
             list = (ArrayList<SectionInfo>) sectionInfoDAO.getAll();
        else
            list = (ArrayList<SectionInfo>) sectionInfoDAO.getSectionById(Integer.parseInt(id));
//        System.out.println("****"+id);
//        System.out.println("////"+list);

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


        request.setAttribute("boad_list",list);
        String board_name;
        if(id.equals("-1")){
            session.setAttribute("location","所有板块");
        }
        else{
            board_name = sectionInfoDAO.getPSectionById(Integer.parseInt(id)).getSname();
            session.setAttribute("location",board_name+"专区");
        }


        request.getRequestDispatcher("/front_index.jsp").forward(request,response);



    }
}
