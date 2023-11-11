package miniprojectcontroler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniprogject.dao.WorldcupDAO;
import miniproject.dto.WorldcupVO;

/**
 * Servlet implementation class deleteServlet
 */
@WebServlet("/choice_list.do")
public class choice_listServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public choice_listServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "worldcup/world.jsp";	//사용할 곳 url
		String winner_url = "worldcup/winner.jsp";	// 최종 한 개만 남으면 전송될 URL
		
		WorldcupDAO worldcupDao = WorldcupDAO.getInstance();
				
		List<WorldcupVO> choiceList = worldcupDao.choiceList();
		
		System.out.println(">>>>>>> 검색 결과(choice_listServlet):");
		for(WorldcupVO item : choiceList) {
			System.out.println(item);
		}
		if(choiceList.size()==1) {
			 request.setAttribute("worldcupList", choiceList);
			   
	         request.getRequestDispatcher(winner_url).forward(request, response);
		
		}else if (!choiceList.isEmpty()) {	//만약 worldcupList가 비어있지 않다면
            request.setAttribute("worldcupList", choiceList);
   
            request.getRequestDispatcher(url).forward(request, response);
        }
		

//		int choiceListSize = choiceList.size();
//		String arr[]=choiceList.toArray(new String[choiceListSize]);
//		
//		 System.out.println(arr);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
