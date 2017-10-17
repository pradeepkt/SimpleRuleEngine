/**
 * 
 */

package com.simpleruleenginetest;
import java.util.ArrayList;
import java.util.Date;

import com.simpleruleengine.Operators;
import com.simpleruleengine.Rule;
import com.simpleruleengine.RuleManager;
import com.simpleruleengine.utils.Constants;
import com.simpleruleengine.utils.DBConnectionManager;
import com.simpleruleengine.utils.OperatorMismatchException;

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

		// Clear the Rules Table
		DBConnectionManager.persist(Constants.DB_TABLE_TRUNCATE_QUERY);
		DBConnectionManager.persist(Constants.DB_TABLE_DROP_QUERY);
		DBConnectionManager.persist(Constants.DB_TABLE_CREATE_QUERY);
		
		RuleManager rules = new RuleManager();
		
		// Add Number comparison rules.
		rules.AddRule(new Rule(0,"attr1", Operators.NUM_EQUAL, new Long(300L)));
		rules.AddRule(new Rule(0,"attr2", Operators.NUM_GREATER_THAN, new Long(300L)));
		rules.AddRule(new Rule(0,"attr3", Operators.NUM_GREATER_THAN_OR_EQUAL, new Long(300L)));
		rules.AddRule(new Rule(0,"attr4", Operators.NUM_LESSER_THAN, new Long(300L)));
		rules.AddRule(new Rule(0,"attr5", Operators.NUM_LESSER_THAN_OR_EQUAL, new Long(300L)));

		// Add String comparison rules.
		rules.AddRule(new Rule(0,"attr6", Operators.STR_CONTAINS, "TestString"));
		rules.AddRule(new Rule(0,"attr7", Operators.STR_DOES_NOT_CONTAIN, "TestString"));
		rules.AddRule(new Rule(0,"attr8", Operators.STR_EQUAL, "TestString"));
		
		// Add Date comparison rules.
		rules.AddRule(new Rule(0,"myatr9", Operators.DATE_EARLIER_THAN, new Date(1500748200000L)));
		rules.AddRule(new Rule(0,"myatr10", Operators.DATE_EARLIER_THAN_OR_EQUAL, new Date(1500748200000L)));
		rules.AddRule(new Rule(0,"myatr11", Operators.DATE_EQUAL, new Date(1500748200000L)));
		rules.AddRule(new Rule(0,"myatr12", Operators.DATE_LATER_THAN, new Date(1500748200000L)));
		rules.AddRule(new Rule(0,"myatr13", Operators.DATE_LATER_THAN_OR_EQUAL, new Date(1500748200000L)));

		rules.displayRules();
		
		
		ArrayList<Rule> rulesbyname = rules.getRulesByName("myatr");
		System.out.println("Rules matching myatr");
		for (Rule currentRule: rulesbyname)
		{
			currentRule.displayRule();
		}
		ArrayList<Rule> rulesbyop = rules.getRulesByOperator(Operators.DATE_EQUAL);
		System.out.println("Rules matching myatr");
		for (Rule currentRule: rulesbyop)
		{
			currentRule.displayRule();
			try
			{
				if (currentRule.evaluateRule(new Date(1500748200000L)))
				{
					System.out.println("True");
				}
				else
				{
					System.out.println("False");
				}
				currentRule.evaluateRule(3000L);
			}
			catch (OperatorMismatchException ex)
			{
				System.out.println(ex.toString());
			}
		}
		
		Rule currRule;
		currRule = rules.getRuleById(2L);
		currRule.setCreatedBy("PradeepKumar.Arasan");
		rules.ModRule(currRule);
		
		currRule = rules.getRuleById(10);
		rules.DelRule(currRule);

	}

}
