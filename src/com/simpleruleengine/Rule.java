/**
 * 
 */
package com.simpleruleengine;

/**
 * @author z0027pb
 *
 */
public final class Rule {
	
	/**
	 * ID
	 */
	private long _id;
	public long getId()
	{
		return _id;
	}
	
	
	/**
	 * Attribute
	 */
	private String _attrName;
	public String getAttrName()
	{
		return _attrName;
	}
	public void setAttrName(String attr)
	{
		_attrName = attr;
	}
	
	/**
	 * Operator
	 */
	private Operators _oper;
	public Operators getOperator()
	{
		return _oper;
	}
	public void setOperator(Operators op)
	{
		_oper = op;
	}
	
	/**
	 * Threshold
	 */
	private Object _threshold;
	public Object getThreshold()
	{
		return _threshold;
	}
	public void setThreshold(Object thresh)
	{
		_threshold = thresh;
	}
	
	/**
	 * Constructor
	 * @param attr
	 * @param op
	 * @param thresh
	 */
	public Rule(long id, String attr, Operators op, Object thresh)
	{
		_id = id;
		_attrName = attr;
		_oper = op;
		_threshold = thresh;
	}
	
	/**
	 * Constructor
	 */
	public Rule()
	{
		_id = -1;
		_attrName = new String();
		_oper = Operators.INVALID;
		_threshold = new Object();
	}

}
