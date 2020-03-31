package Action;

import Dao.ManagerDAO;
import Dao.SectionInfoDAO;
import Entity.SectionInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DelBoardAction",urlPatterns = "/delBoard.jhtml")
public class DelBoardAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManagerDAO managerDAO=new ManagerDAO();
        SectionInfoDAO sectionInfoDAO=new SectionInfoDAO();
        int id=Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        if(sectionInfoDAO.isParentById(id)){
            ArrayList<SectionInfo> list= (ArrayList<SectionInfo>) sectionInfoDAO.getSectionById(id);
            for (int i = 0; i < list.size(); i++) {
                managerDAO.delSectionInfo(list.get(i).getSid());
            }
        }
        managerDAO.delSectionInfo(id);
    }
}
