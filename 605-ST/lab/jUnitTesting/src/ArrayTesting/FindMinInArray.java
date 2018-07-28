package ArrayTesting;

import java.util.ArrayList;

public class FindMinInArray {
	public int findMin(ArrayList<Integer> list){
	    int min = Integer.MAX_VALUE;
	    for(int i=0; i<list.size(); i++){
	        if(list.get(i) < min){
	            min = list.get(i);
	        }
	    }
	    return min; 
	}
}
