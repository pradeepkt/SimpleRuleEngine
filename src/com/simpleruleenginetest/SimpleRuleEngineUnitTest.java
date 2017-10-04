/**
 * 
 */

package com.simpleruleenginetest;
import java.util.Date;

import com.simpleruleengine.*;
import com.simpleruleengine.utils.DBConnectionManager;

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
		DBConnectionManager.persist("TRUNCATE `RuleDB`.`Rules`;");
		
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
		rules.AddRule(new Rule(0,"attr9", Operators.DATE_EARLIER_THAN, new Date(1500748200000L)));
		rules.AddRule(new Rule(0,"attr9", Operators.DATE_EARLIER_THAN_OR_EQUAL, new Date(1500748200000L)));
		rules.AddRule(new Rule(0,"attr9", Operators.DATE_EQUAL, new Date(1500748200000L)));
		rules.AddRule(new Rule(0,"attr9", Operators.DATE_LATER_THAN, new Date(1500748200000L)));
		rules.AddRule(new Rule(0,"attr9", Operators.DATE_LATER_THAN_OR_EQUAL, new Date(1500748200000L)));

	}

}
