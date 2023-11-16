package utils;

/*
 * 현재 페이지와 관련된 정보 저장 클래스
 * - 현재 페이지 번호
 * - 페이지 당 게시글의 수
 */
public class Criteria {
	private int PageNum; // 현재 페이지 번호
	private int rowsPerPage; // 페이지당 게시글의 수
	
	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int rowsPerPage) {
		this.PageNum = pageNum;
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNum() {
		return PageNum;
	}

	public void setPageNum(int pageNum) {
		if (pageNum <= 0)
			this.PageNum = 1;
		else
			this.PageNum = pageNum;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	/*
	 * 페이지 당 게시글의 수 제한: 1 ~ 20
	 */
	public void setRowsPerPage(int rowsPerPage) {
		if(rowsPerPage <= 0 || rowsPerPage > 20)
			this.rowsPerPage = 20;
		else
			this.rowsPerPage = rowsPerPage;
			
	}

	@Override
	public String toString() {
		return "Criteria [PageNum=" + PageNum + ", rowsPerPage=" + rowsPerPage + "]";
	}
	
	
}
