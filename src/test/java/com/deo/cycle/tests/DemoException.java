package com.deo.cycle.tests;

import org.testng.annotations.Test;

public class DemoException {
	
	@Test
	public void byZero() throws ArithmeticException{
		
//		try {
			int z = 10/0 ;  // returns me infinity
			System.out.println(z);
		
			
//			
//		} catch ( ArithmeticException  e ) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.out.println(e.getMessage() + e.toString());
//			
//		}
		
		System.out.println("continuing even after exception");
		
	}
	

}
