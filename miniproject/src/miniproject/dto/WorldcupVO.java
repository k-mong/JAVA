package miniproject.dto;

public class WorldcupVO {
	private int num;
	private String name;
	private String pictureurl;
	private int choice;
	
	public WorldcupVO() {
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPictureurl() {
		return pictureurl;
	}

	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	
	
	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	@Override
	public String toString() {
		return "WorldcupVO [num=" + num + ", name=" + name + ", pictureurl=" + pictureurl + "]";
	}


	
}






