package learn.String;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Compare {

	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(100);
		set.add(90);
		set.add(95);
		ArrayList<Integer> listVal = new ArrayList<Integer>(set);
		Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1.intValue());
            }
        };
        listVal.stream().forEach(val->{
        	System.out.print(val+" ");
        });
        System.out.println("\n");
        int insertPosition = Collections.binarySearch(listVal, 85, cmp);
		System.out.println("\n"+"85 : "+Math.abs(insertPosition+1)); 
		
		int val = 90;
		insertPosition = Collections.binarySearch(listVal, val, cmp);
		System.out.println("\n"+val+" : "+insertPosition);
		
		val = 93;
		insertPosition = Collections.binarySearch(listVal, val, cmp);
		System.out.println("\n"+val+" : "+insertPosition);
		
		val = 95;
		insertPosition = Collections.binarySearch(listVal, val, cmp);
		System.out.println("\n"+val+" : "+insertPosition);
		
		val = 97;
		insertPosition = Collections.binarySearch(listVal, val, cmp);
		System.out.println("\n"+val+" : "+insertPosition);
		
		val = 100;
		insertPosition = Collections.binarySearch(listVal, val, cmp);
		System.out.println("\n"+val+" : "+insertPosition);
		
		val = 105;
		insertPosition = Collections.binarySearch(listVal, val, cmp);
		System.out.println("\n"+val+" : "+insertPosition);

	}

}
