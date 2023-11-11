package miniprojectcontroler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniprogject.dao.WorldcupDAO;
import miniproject.dto.WorldcupVO;

/**
 * Servlet implementation class deleteDataServlet
 */
@WebServlet("/deleteData.do")
public class deleteDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("not_choice"); //화면에서 값 읽어오기
		String url = "worldcup/choiceworld.jsp";	//사용할 곳 url
		
		WorldcupDAO worldcupDao = WorldcupDAO.getInstance();
		WorldcupVO worldcup = worldcupDao.selectWorldcupByNum(num);
		worldcupDao.delete_data(num);
		
		
		request.setAttribute("worldcup", worldcup);
		
		
		System.out.println("worldcup>>>>>"+ worldcup);
		System.out.println("request.getParameter(\"not_choice\")>>>>" + num);

		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String not_choiced_num = request.getParameter("not_choice");	//요청해라.갖고와라 화면에서 보이는(code) 그리고 문자열 타입의 code 안에 넣어줘
		
		WorldcupDAO productDao = WorldcupDAO.getInstance();	// DAO 호출
		productDao.delete_data(not_choiced_num);		//DAO 안에 delete기능을 해줘(code)를
		
		response.sendRedirect("choice_list.do");
		
		System.out.println("post not_choiced_num>>>>" +not_choiced_num);
	}

}
