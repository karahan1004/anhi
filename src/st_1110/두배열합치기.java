package st_1110;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 두배열합치기 {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		
		System.out.println("배열1의 개수");
		int size1= sc.nextInt();
		
		System.out.println("배열2의 개수");
		int size2= sc.nextInt();
		
		System.out.println("배열1에 저장할 값을 오름차순으로 입력");
		int[] arr1 =new int[size1];
		for(int i=0; i<size1; i++) {
			arr1[i]=sc.nextInt();
		}
		
		System.out.println("배열2에 저장할 값을 오름차순으로 입력");
		int[] arr2= new int[size2];
		for(int i=0; i<size2; i++) {
			arr2[i]=sc.nextInt();
		}
		
		List<Integer> list=new ArrayList<>();
		
		for(int i=0; i<size1; i++) {
			list.add(arr1[i]);
		}
		
		for(int i=0; i<size2; i++) {
			list.add(arr2[i]);
		}
		
		Collections.sort(list);
		
		for(Integer i:list) {
			System.out.print(i+" ");
		}
		
	}

}
