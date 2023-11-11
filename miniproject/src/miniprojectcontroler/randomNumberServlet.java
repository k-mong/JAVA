package miniprojectcontroler;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniprogject.dao.WorldcupDAO;
import miniproject.dto.WorldcupVO;
import random.RandomNumber;

/**
 * Servlet implementation class randomNumberServlet
 */
@WebServlet("/randomNumber.do")
public class randomNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public randomNumberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "worldcup/world.jsp";	//사용할 곳 url
		
		WorldcupDAO worldcupDao = WorldcupDAO.getInstance();
		
		// 게임 시작전 LoadDB 초기화
		worldcupDao.clearLoadDb();
		
		List<WorldcupVO> worldcupList = worldcupDao.selectRandom();
		
//		System.out.println(">>>>>>> 검색 결과:");
//		for(WorldcupVO item : worldcupList) {
//			System.out.println(item);
//		}
		

		if (!worldcupList.isEmpty()) {	//만약 worldcupList가 비어있지 않다면
			// 월드컵 게임을 위한 목록을 LoadDB에 insert
			worldcupDao.insertLoadDb(worldcupList);
			
            request.setAttribute("worldcupList", worldcupList);		//8개 리스트 전부 JSP로 보내는것.
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
