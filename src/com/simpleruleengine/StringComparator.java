/**
 * 
 */
package com.simpleruleengine;

import com.simpleruleengine.utils.OperatorMismatchException;

/**
 * @author z0027pb
 *
 */
public class StringComparator implements BasicRuleEngine {

	/* (non-Javadoc)
	 * @see com.simpleruleengine.BasicRuleEngine#compare(java.lang.Object, java.lang.Object, int)
	 */
	@Override
	public boolean compare(Object val1, Object val2, Operators operation) throws OperatorMismatchException {

		if (!(val1 instanceof String)) // (val1.getClass().isInstance(String.class))
		{
			// Type check for the LHS and RHS
			throw new OperatorMismatchException("This is NOT string");
		}
		switch (operation)
		{
		case STR_CONTAINS:
			return ((String)val1).contains((String)val2);
		case STR_DOES_NOT_CONTAIN:
			return !((String)val1).contains((String)val2);
		case STR_EQUAL:
			return ((String)val1).equals((String)val2);
		default:
			return false;
		}

	}

}
