package random;

import java.util.Random;

public class RandomNumber {
	
	public static int[] number() {
		int[] num = new int[8];	//값 8개 받는 배열 생성 (16강)
		Random r = new Random();
		
		for(int i=0; i<num.length; i++) {
			num[i] = r.nextInt(10) + 1;		//1~10까지의 랜덤한 숫자 생성 (데이터에 10개밖에 없음)
			
			for(int j=0; j<i; j++) {
				if(num[i] == num[j]) {
					i--;
				}
			}
		}
		
		return num;
	}
	
	
	
}






