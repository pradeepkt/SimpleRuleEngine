/**
 * 
 */
package com.simpleruleengine;

import com.simpleruleengine.utils.OperatorMismatchException;

/**
 * @author z0027pb
 *
 */
public interface BasicRuleEngine {
	
	public boolean compare(Object val1, Object val2, Operators operation) throws OperatorMismatchException;
	
}
