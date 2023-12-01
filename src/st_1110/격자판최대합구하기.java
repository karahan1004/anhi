package st_1110;

import java.util.Scanner;

public class 격자판최대합구하기 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("격자판 사이즈를 입력하세요(2~50 중 선택)");
		int size=sc.nextInt();
		int[][] arr=new int[size][size];
		
		System.out.println("격자판에 들어갈 숫자를 입력하세요");
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		
		int line=0; 
		int max=0;
		int row=0;
		int col=0;
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				row+= arr[i][j];
				col+= arr[j][i];
			}
			line=Math.max(line, row);
			line=Math.max(line, col);
		}
		row=col=0;
		for(int i=0; i<size; i++) {
			row+=arr[i][i];
			col+=arr[i][size-i-1];
		}
		
		line=Math.max(max, row);
		line=Math.max(max, col);
		
		System.out.println("최대합은 "+line+"입니다.");
	}
	


}
