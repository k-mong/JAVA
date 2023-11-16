package utils;

/*
 * ���� �������� ���õ� ���� ���� Ŭ����
 * - ���� ������ ��ȣ
 * - ������ �� �Խñ��� ��
 */
public class Criteria {
	private int PageNum; // ���� ������ ��ȣ
	private int rowsPerPage; // �������� �Խñ��� ��
	
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
	 * ������ �� �Խñ��� �� ����: 1 ~ 20
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
