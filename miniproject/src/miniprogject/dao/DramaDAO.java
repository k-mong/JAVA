package miniprogject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Data.ConnectDb;
import miniproject.dto.WorldcupVO;
import random.RandomNumber;

public class DramaDAO {
	
	private DramaDAO() {}
	
	private static DramaDAO instance = new DramaDAO();
	
	public static DramaDAO getInstance() {
		return instance;
	}
	
	/*
	 * 랜덤 숫자 8개와 같은 숫자 조회
	 */
	public List<WorldcupVO> selectRandom(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WorldcupVO drama = null;
		
		List<WorldcupVO> dramaList = new ArrayList<WorldcupVO>();	//List에 담는다

		String sql = "SELECT * FROM drama WHERE num=?";

		int[] randnum = RandomNumber.number();
		
		try {
			conn = ConnectDb.getConnection();
			
			for (int i=0; i<randnum.length; i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, randnum[i]);
				
				rs = pstmt.executeQuery(); 
				
				if(rs.next()) {
					drama = new WorldcupVO();
					drama.setNum(rs.getInt("num"));
					drama.setName(rs.getString("name"));
					drama.setPictureurl(rs.getString("pictureurl"));
					
					dramaList.add(drama);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDb.close(conn, pstmt, rs);	
		}
				
		return dramaList;
	}
	
	
	/*
	 * 월드컵 게임을 시작하기 전에 LoadDB 를 초기화(데이터 모두 삭제)
	 */
	public void clearLoadDb() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM load_db";
		
		try {
			conn = ConnectDb.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDb.close(conn, pstmt);	
		}
	}
	
	
	
	
	/* 
	 * 월드컵 게임용 테이블에 insert
	 */
	public void insertLoadDb(List<WorldcupVO> randomList) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO load_db(num, name, pictureurl) VALUES (?, ?, ?)";
		
		try {
			conn = ConnectDb.getConnection();
			
			for(int i=0; i<randomList.size(); i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, randomList.get(i).getNum());
				pstmt.setString(2, randomList.get(i).getName());
				pstmt.setString(3, randomList.get(i).getPictureurl());
				
				pstmt.executeUpdate();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDb.close(conn, pstmt);	
		}
	}
	
	
	/*
	 * 선택받지 못하면 데이터 삭제
	 * 데이터의 num과 들어온 값의 num 이 같으면 데이터 유지 아니면 삭제
	*/
	public void delete_data(String not_choiced_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM load_db WHERE num=?";
		//choice 가 같지않으면 load_db 데이터에서 삭제해줘(디폴트값은 0) 1이면 삭제하게 해야되니 1이랑 같으면 삭제

		
		try {
			conn = ConnectDb.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, not_choiced_num);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDb.close(conn, pstmt);	
		}
		
		
	}
	
	
	public List<WorldcupVO> choiceList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<WorldcupVO> choiceList = new ArrayList<WorldcupVO>();
		
		String sql = "SELECT * FROM load_db";
		
		try {
			conn = ConnectDb.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WorldcupVO worldcup = new WorldcupVO();
				worldcup.setNum(rs.getInt("num"));
				worldcup.setName(rs.getString("name"));
				worldcup.setPictureurl(rs.getString("pictureurl"));
				worldcup.setChoice(rs.getInt("choice"));
				
				choiceList.add(worldcup);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDb.close(conn, pstmt, rs);	
		}
				
		return choiceList;
	}
	
	

}
