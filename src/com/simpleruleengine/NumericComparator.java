/**
 * 
 */
package com.simpleruleengine;

/**
 * @author z0027pb
 *
 */
public final class NumericComparator implements BasicRuleEngine {

	/* (non-Javadoc)
	 * @see com.simpleruleengine.BasicRuleEngine#compare(java.lang.Object, java.lang.Object, int)
	 */
	@Override
	public boolean compare(Object val1, Object val2, Operators operation) {
		
		if (!val1.getClass().isInstance(Number.class) || val1.getClass().isInstance(Number.class))
		{
			// Type check for the LHS and RHS
			return false;
		}
		switch (operation)
		{
		case NUM_EQUAL:
			return ((Number)val1).longValue() == ((Number)val2).longValue();
		case NUM_GREATER_THAN:
			return ((Number)val1).longValue() > ((Number)val2).longValue();
		case NUM_GREATER_THAN_OR_EQUAL:
			return ((Number)val1).longValue() >= ((Number)val2).longValue();
		case NUM_LESSER_THAN:
			return ((Number)val1).longValue() < ((Number)val2).longValue();
		case NUM_LESSER_THAN_OR_EQUAL:
			return ((Number)val1).longValue() <= ((Number)val2).longValue();
		default:
			return false;
		}

	}

}
