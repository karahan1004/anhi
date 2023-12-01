package st_1110;

import java.util.Scanner;

public class 국어성적등수구하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("학생 수 입력");
		int hak = sc.nextInt();
		
		int[] jum=new int[hak];
		System.out.println("국어 성적 "+hak+"개 입력");
		for(int i=0; i<hak; i++) {
			jum[i]=sc.nextInt();
		}
		
		int[] result=new int[hak];
		
		for(int i=0; i<hak; i++) {
			int count =1;
			for(int j=0; j<hak; j++) {
				if(jum[i]<jum[j]) {
					count++;
				}
			}
			
			result[i] =count;
		}
		for(int i:result) {
			System.out.print("  "+i);
		}
		
		
	}

}
