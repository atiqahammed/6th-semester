package ArrayTesting;

import java.util.ArrayList;

public class FindMaxInArray {
	
	public int findMax(ArrayList<Integer> list){
	    int max = Integer.MIN_VALUE;
	    for(int i=0; i<list.size(); i++){
	        if(list.get(i) > max){
	            max = list.get(i);
	        }
	    }
	    return max; 
	}
	
	

}
