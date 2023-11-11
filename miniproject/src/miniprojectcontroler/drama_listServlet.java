package miniprojectcontroler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniprogject.dao.DramaDAO;
import miniproject.dto.WorldcupVO;

/**
 * Servlet implementation class drama_listServlet
 */
@WebServlet("/drama_list.do")
public class drama_listServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public drama_listServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "worldcup/dramaForm.jsp";
		
		DramaDAO dramaDao = DramaDAO.getInstance();
		
		dramaDao.clearLoadDb();
		
		List<WorldcupVO> dramaList = dramaDao.selectRandom();
		
		if(!dramaList.isEmpty()) {
			dramaDao.insertLoadDb(dramaList);
			
			request.setAttribute("dramaList", dramaList);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
