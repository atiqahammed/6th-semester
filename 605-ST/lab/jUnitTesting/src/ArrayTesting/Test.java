package ArrayTesting;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		
		ArrayList<Integer> myArray = new ArrayList<Integer>();
		myArray.add(2000); 
		myArray.add(100);
		myArray.add(40);
		myArray.add(60);
		
		FindMaxInArray AMF1 = new FindMaxInArray();
		FindMinInArray AMF2 = new FindMinInArray();
		
		System.out.println("max value = " + AMF1.findMax(myArray));
		System.out.println("min value = " + AMF2.findMin(myArray));
		
	}

}