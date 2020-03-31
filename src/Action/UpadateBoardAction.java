package Action;

import Dao.SectionInfoDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpadateBoardAction",urlPatterns = "/updateBoard.jhtml")
public class UpadateBoardAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("board_id"));
        String name=request.getParameter("name");
        SectionInfoDAO sectionInfoDAO=new SectionInfoDAO();
        sectionInfoDAO.updateSectionInfoById(id,name);
    }
}
