package miniprojectcontroler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniprogject.dao.WorldcupDAO;

/**
 * Servlet implementation class drama_deleteSerVlet
 */
@WebServlet("/drama_delete.do")
public class drama_deleteSerVlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public drama_deleteSerVlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String not_choiced_num = request.getParameter("not_choice");	//요청해라.갖고와라 화면에서 보이는(code) 그리고 문자열 타입의 code 안에 넣어줘
		
		WorldcupDAO productDao = WorldcupDAO.getInstance();	// DAO 호출
		productDao.delete_data(not_choiced_num);		//DAO 안에 delete기능을 해줘(not_choiced_num)를
		
		response.sendRedirect("choice_list.do");
		
		System.out.println("post not_choiced_num>>>>" +not_choiced_num);
	}

}
