/**
 * 
 */
package com.simpleruleengine;

import java.util.Date;

import com.simpleruleengine.utils.OperatorMismatchException;

/**
 * @author z0027pb
 *
 */
public class DateComparator implements BasicRuleEngine {

	/* (non-Javadoc)
	 * @see com.simpleruleengine.BasicRuleEngine#compare(java.lang.Object, java.lang.Object, com.simpleruleengine.Operators)
	 */
	@Override
	public boolean compare(Object val1, Object val2, Operators operation) throws OperatorMismatchException {
		
		if (!(val1 instanceof Date) || !(val2 instanceof Date))
		{
			// Type check for the LHS and RHS
			throw new OperatorMismatchException("This is NOT Date");
		}
		System.out.println("Comparing " + ((Date)val1).toString() + " " + operation.toString() + " "
							+ ((Date)val2).toString());
		switch (operation)
		{
		case DATE_EARLIER_THAN:
			return ((Date)val1).before((Date)val2);
		case DATE_LATER_THAN:
			return ((Date)val1).after((Date)val2);
		case DATE_EARLIER_THAN_OR_EQUAL:
			return ((Date)val1).before((Date)val2) || ((Date)val1).equals((Date)val2);
		case DATE_LATER_THAN_OR_EQUAL:
			return ((Date)val1).after((Date)val2) || ((Date)val1).equals((Date)val2);
		case DATE_EQUAL:
			return ((Date)val1).equals((Date)val2);
		default:
			return false;
		}
	}

}
