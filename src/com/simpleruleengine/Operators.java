/**
 * 
 */
package com.simpleruleengine;

/**
 * @author z0027pb
 *
 */
public enum Operators {
	
	INVALID(0),
	
	NUM_GREATER_THAN(10),
	NUM_GREATER_THAN_OR_EQUAL(11),
	NUM_LESSER_THAN(12),
	NUM_LESSER_THAN_OR_EQUAL(13),
	NUM_EQUAL(14),
	
	STR_EQUAL(20),
	STR_CONTAINS(21),
	STR_DOES_NOT_CONTAIN(22),
	
	DATE_EQUAL(30),
	DATE_EARLIER_THAN(31),
	DATE_EARLIER_THAN_OR_EQUAL(32),
	DATE_LATER_THAN(33),
	DATE_LATER_THAN_OR_EQUAL(34);
	
	private final int code;
	
	/**
	 * Constructor
	 * @param code
	 */
	Operators(int code)
	{
		this.code=code;
	}
	

	/**
	 * Operation
	 * @param oper
	 * @return
	 */
	public static Operators fromInteger(int oper)
	{
		switch(oper)
		{
			case 0:
				return INVALID;
			case 10:
				return NUM_GREATER_THAN;
			case 11:
				return NUM_GREATER_THAN_OR_EQUAL;
			case 12:
				return NUM_LESSER_THAN;
			case 13:
				return NUM_LESSER_THAN_OR_EQUAL;
			case 14:
				return NUM_EQUAL;
			case 20:
				return STR_EQUAL;
			case 21:
				return STR_CONTAINS;
			case 22:
				return STR_DOES_NOT_CONTAIN;
			case 30:
				return DATE_EQUAL;
			case 31:
				return DATE_EARLIER_THAN;
			case 32:
				return DATE_EARLIER_THAN_OR_EQUAL;
			case 33:
				return DATE_LATER_THAN;
			case 34:
				return DATE_LATER_THAN_OR_EQUAL;
			default:
				return INVALID;		
		}
	}
	
	/**
	 * Get the code
	 * @return
	 */
	public int getCode()
	{
		return this.code;
	}
	
	public String toString()
	{
		switch(this)
		{
		case INVALID:
			return "INVALID";
		case NUM_GREATER_THAN:
			return "NUM_GREATER_THAN";
		case NUM_GREATER_THAN_OR_EQUAL:
			return "NUM_GREATER_THAN_OR_EQUAL";
		case NUM_LESSER_THAN:
			return "NUM_LESSER_THAN";
		case NUM_LESSER_THAN_OR_EQUAL:
			return "NUM_LESSER_THAN_OR_EQUAL";
		case NUM_EQUAL:
			return "NUM_EQUAL";
		case STR_EQUAL:
			return "STR_EQUAL";
		case STR_DOES_NOT_CONTAIN:
			return "STR_DOES_NOT_CONTAIN";
		case STR_CONTAINS:
			return "STR_CONTAINS";
		case DATE_EQUAL:
			return "DATE_EQUAL";
		case DATE_EARLIER_THAN:
			return "DATE_EARLIER_THAN";
		case DATE_EARLIER_THAN_OR_EQUAL:
			return "DATE_EARLIER_THAN_OR_EQUAL";
		case DATE_LATER_THAN:
			return "DATE_LATER_THAN";
		case DATE_LATER_THAN_OR_EQUAL:
			return "DATE_LATER_THAN_OR_EQUAL";
		default:
			return "INVALID";
		}
	}
}
