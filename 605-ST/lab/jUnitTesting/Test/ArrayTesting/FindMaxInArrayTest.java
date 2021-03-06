package ArrayTesting;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FindMaxInArrayTest {
	
	public static FindMaxInArray finMax;
	public static FindMinInArray finMin;
	public static ArrayList<Integer> myArray;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before Class");
		finMax = new FindMaxInArray();
		finMin = new FindMinInArray();
		myArray = new ArrayList<Integer>();
		myArray.add(2000); 
		myArray.add(100);
		myArray.add(40);
		myArray.add(60);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After Class");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Before");
		
	 }

	@After
	public void tearDown() throws Exception {
		System.out.println("After");
	}

	@Test
	public void testMin() {
		//ArrayList<Integer> myArray = new ArrayList<Integer>();
		/*myArray.add(2000); 
		myArray.add(100);
		myArray.add(40);
		myArray.add(60);*/
		
		//assertEquals(2000, finMax.findMax(myArray));
		assertEquals(40, finMin.findMin(myArray));
		
		//fail("Not yet implemented");
	}
	
	@Test
	public void testMax() {
		assertEquals(2000, finMax.findMax(myArray));
	
	}

}
