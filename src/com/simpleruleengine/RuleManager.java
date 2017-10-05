/**
 * 
 */
package com.simpleruleengine;

import java.util.ArrayList;
import java.util.Hashtable;
import com.simpleruleengine.utils.Constants;
import com.simpleruleengine.utils.DBConnectionManager;

/**
 * @author z0027pb
 *
 */
public class RuleManager {
	
	/**
	 * List of rules
	 */
	private Hashtable<Long,Rule> _ruleSet = new Hashtable<Long,Rule>();
	
	/**
	 * 
	 */
	public RuleManager()
	{
		initialize();
	}
	
	/**
	 * Initialize the cache with the rules from DB
	 */
	private void initialize()
	{
		_ruleSet = DBConnectionManager.getAllRules();
	}
	
	/**
	 * Display the set of rules.
	 */
	public void displayRules()
	{
		for (Rule curr_Rule:_ruleSet.values())
		{
            //Display values
			curr_Rule.displayRule();
		}
	}
	
	/**
	 * 
	 * @param attribute
	 * @param oper
	 * @param value
	 */
	public void AddRule(String attribute, Operators oper, Object value)
	{
		long maxID = getMaxId();
		Rule rule = new Rule(maxID+1,attribute,oper,value);
		persist(rule);
		_ruleSet.put(maxID+1,rule);
	}
	
	public void AddRule(Rule newRule)
	{
		long maxID = getMaxId();
		newRule.setId(maxID+1);
		persist(newRule);
		_ruleSet.put(maxID+1,newRule);
	}
	
	/**
	 * Returns the max Id
	 * @return
	 */
	private long getMaxId()
	{
		long maxId = -1L;
		for (Long entry:_ruleSet.keySet()) 
		{
			maxId = (maxId < entry.longValue())?entry.longValue():maxId;
		}
		return maxId;
	}
	
	/**
	 * 
	 * @param rule
	 */
	private void persist(Rule rule)
	{
		long maxID = DBConnectionManager.getMaxID();
		if (maxID == -1)
		{
			// something wrong
			System.out.println("Something wrong with max id");
			return;
		}
		//System.out.println("The max ID is "+ maxID);
		String built = "INSERT INTO `RuleDB`.`Rules` "
				+ "(`idRules`, `Attr_name`, `Operation`, `Threshold_Val`, `IsNull`) VALUES ("
				+ "'" + (maxID+1) + "', "
				+ "'" + rule.getAttrName() + "', "
				+ rule.getOperator().getCode() + ", " 
			    + "'" + rule.getThreshold().toString() + "', " 
			    + "'0')"; 
		//System.out.println("the insert query "+built);
		long result = DBConnectionManager.persist(built);
		if (result != Constants.SUCCESS)
		{
			System.out.println("There is some problem while updating");
		}
		return;
	}
	
	/**
	 * Get the list of rules with matching attribute name
	 * @param wildCard
	 * @return
	 */
	public ArrayList<Rule> getRulesByName(String wildCard)
	{
		ArrayList<Rule> list = new ArrayList<Rule>();
		for (Rule currentRule:_ruleSet.values())
		{
			if (currentRule.getAttrName().contains(wildCard)) 
			{
				list.add(currentRule);
			}
		}
		return list;
	}
	
	/**
	 * Get the list of rules with matching Operator
	 * @param operatorCard
	 * @return
	 */
	public ArrayList<Rule> getRulesByOperator(Operators operatorCard)
	{
		ArrayList<Rule> list = new ArrayList<Rule>();
		for (Rule currentRule:_ruleSet.values())
		{
			if (currentRule.getOperator() == operatorCard) 
			{
				list.add(currentRule);
			}
		}
		return list;
	}
	

}
