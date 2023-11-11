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
 * Servlet implementation class drama_choiceServlet
 */
@WebServlet("/drama_choice.do")
public class drama_choiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public drama_choiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "worldcup/dramaForm.jsp";
		String winner_url = "worldcup/winner.jsp";	// 최종 한 개만 남으면 전송될 URL
		
		DramaDAO dramaDao = DramaDAO.getInstance();
		
		List<WorldcupVO> dramaList = dramaDao.choiceList();
		
		if(dramaList.size()==1) {
			 request.setAttribute("worldcupList", dramaList);
			   
	         request.getRequestDispatcher(winner_url).forward(request, response);
		
		}else if (!dramaList.isEmpty()) {	//만약 worldcupList가 비어있지 않다면
           request.setAttribute("worldcupList", dramaList);
  
           request.getRequestDispatcher(url).forward(request, response);
       }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
