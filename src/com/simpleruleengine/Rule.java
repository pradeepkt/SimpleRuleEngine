/**
 * 
 */
package com.simpleruleengine;

import java.sql.Date;
import java.util.Calendar;

import com.simpleruleengine.utils.OperatorMismatchException;

/**
 * @author z0027pb
 *
 */
public final class Rule {
	
	/**
	 * ID
	 */
	private long _id = -1;
	public long getId()
	{
		return _id;
	}
	public void setId(long id)
	{
		_id = id;
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
	 * Created by - User.name
	 */
	private String _createdBy;
	public String getCreatedBy()
	{
		return _createdBy;
	}
	public void setCreatedBy(String userName)
	{
		_createdBy = userName;
	}
	
	/**
	 * Created Date for this rule
	 */
	private Date _createdDate;
	public Date getCreatedDate()
	{
		return _createdDate;
	}
	public void setCreatedDate(Date createdDate)
	{
		_createdDate = createdDate;
	}

	/**
	 * Last Modified by - User.name
	 */
	private String _lastModifiedBy;
	public String getLastModifiedBy()
	{
		return _lastModifiedBy;
	}
	public void setLastModifiedBy(String userName)
	{
		_lastModifiedBy = userName;
	}
	
	/**
	 * last Modified Date for this rule
	 */
	private Date _lastModifiedDate;
	public Date getLastModifiedDate()
	{
		return _lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate)
	{
		_lastModifiedDate = lastModifiedDate;
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
		_createdBy = "";
		_createdDate = new Date(Calendar.getInstance().getTime().getTime());
		_lastModifiedBy = "";
		_lastModifiedDate = new Date(Calendar.getInstance().getTime().getTime());
	}
	
	/**
	 * Copy the rule parameters
	 * @param newRule
	 */
	public void copyRule(Rule newRule)
	{
		_id = newRule.getId();
		_attrName = newRule.getAttrName();
		_oper = newRule.getOperator();
		_threshold = newRule.getThreshold();
		_createdBy = newRule.getCreatedBy();
		_createdDate = newRule.getCreatedDate();
		_lastModifiedBy = newRule.getLastModifiedBy();
		_lastModifiedDate = newRule.getLastModifiedDate();
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
	
	public boolean evaluateRule(Object compare) throws OperatorMismatchException
	{
		BasicRuleEngine engine;
		int operCode = _oper.getCode();
		switch (operCode)
		{
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
				engine = new NumericComparator();
				break;
			case 20:
			case 21:
			case 22:
				engine = new StringComparator();
				break;
			case 30:
			case 31:
			case 32:
			case 33:
			case 34:
				engine = new DateComparator();
				break;
			default:
				return false;
		}
		return engine.compare(compare, _threshold, _oper);
	}
	
	/**
	 * Display the rule details
	 */
	public void displayRule()
	{
        System.out.println(_id + " : " + _attrName + " "
        		+ _oper.toString() + " " + _threshold);
	}

}
