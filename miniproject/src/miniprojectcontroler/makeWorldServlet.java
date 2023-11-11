package miniprojectcontroler;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import miniprogject.dao.MakeWorldDAO;
import miniproject.dto.WorldcupVO;

/**
 * Servlet implementation class makeWorldServlet
 */
@WebServlet("/makeWorld.do")
public class makeWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public makeWorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "worldcup/insertForm.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int uploadFileSize = 5 * 1024 * 1024;	// 업로드 파일 크기 제한(byte 단위)
		String encType = "UTF-8";
		
		String savePath = "upload";		//멀티파트에 지정해줄 값 임의로 지정
		ServletContext context = getServletContext();	// 폴더를 찾는 메소드
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("실제 업로드 경로: " + uploadFilePath);
		
		
		MultipartRequest multi  = new MultipartRequest(
				request,
				uploadFilePath,
				uploadFileSize,
				encType,
				new DefaultFileRenamePolicy()
		);
		
		
		WorldcupVO world = new WorldcupVO();
		world.setName(multi.getParameter("name"));
		String fileName = multi.getFilesystemName("pictureurl");
		world.setPictureurl(fileName);
		
		MakeWorldDAO makeworldDao = MakeWorldDAO.getInstance();
		makeworldDao.insertWorld(world);
		
		
	}

}
