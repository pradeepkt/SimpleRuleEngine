/**
 * 
 */
package com.simpleruleenginetest;
import java.util.Date;

import com.simpleruleengine.*;

/**
 * @author z0027pb
 *
 */
public class SimpleRuleEngineUnitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SimpleRuleEngineUnitTest test = new SimpleRuleEngineUnitTest();
		
		test.unitTest();

	}
	
	public void unitTest()
	{
		BasicRuleEngine bre;
		Object val1;
		Object val2;
		val1 = new Integer(23);
		val2 = new Integer(24);
		System.out.println("val1 class is " + val1.getClass().toString()) ;
		System.out.println("val2 class is " + val2.getClass().toString()) ;
		bre = new NumericComparator();
		if (bre.compare(val1, val2, Operators.NUM_EQUAL))
		{
			System.out.println(val1.toString() + " is equal to " + val2.toString());
		}
		if (bre.compare(val1, val2, Operators.NUM_GREATER_THAN))
		{
			System.out.println(val1.toString() + " is greater than " + val2.toString());
		}
		if (bre.compare(val1, val2, Operators.NUM_GREATER_THAN_OR_EQUAL))
		{
			System.out.println(val1.toString() + " is greater than or equal to " + val2.toString());
		}
		if (bre.compare(val1, val2, Operators.NUM_LESSER_THAN))
		{
			System.out.println(val1.toString() + " is lesser than " + val2.toString());
		}
		if (bre.compare(val1, val2, Operators.NUM_LESSER_THAN_OR_EQUAL))
		{
			System.out.println(val1.toString() + " is lesser than or equal to " + val2.toString());
		}

		val1 = new String("Tester");
		val2 = new String("Test");
		System.out.println("val1 class is " + val1.getClass().toString()) ;
		System.out.println("val2 class is " + val2.getClass().toString()) ;
		bre = new StringComparator();
		if (bre.compare(val1, val2, Operators.STR_CONTAINS))
		{
			System.out.println(val1.toString() + " contains " + val2.toString());
		}
		if (bre.compare(val1, val2, Operators.STR_DOES_NOT_CONTAIN))
		{
			System.out.println(val1.toString() + " does not contain " + val2.toString());
		}
		if (bre.compare(val1, val2, Operators.STR_EQUAL))
		{
			System.out.println(val1.toString() + " equals " + val2.toString());
		}
		
		val1 = new Date(1473609189);
		val2 = new Date(1441986789);
		System.out.println("val1 class is " + val1.getClass().toString()) ;
		System.out.println("val2 class is " + val2.getClass().toString()) ;
		bre = new DateComparator();
		if (bre.compare(val1, val2, Operators.DATE_EARLIER_THAN))
		{
			System.out.println(val1.toString() + " is earlier than " + val2.toString());
		}
		if (bre.compare(val1, val2, Operators.DATE_EARLIER_THAN_OR_EQUAL))
		{
			System.out.println(val1.toString() + " is earlier than or equal " + val2.toString());
		}
		if (bre.compare(val1, val2, Operators.DATE_EQUAL))
		{
			System.out.println(val1.toString() + " is equal to " + val2.toString());
		}
		if (bre.compare(val1, val2, Operators.DATE_LATER_THAN))
		{
			System.out.println(val1.toString() + " is later than " + val2.toString());
		}
		if (bre.compare(val1, val2, Operators.DATE_LATER_THAN_OR_EQUAL))
		{
			System.out.println(val1.toString() + " is later than or equal to " + val2.toString());
		}
		
	}

}
