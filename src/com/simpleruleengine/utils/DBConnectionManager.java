/**
 * 
 */
package com.simpleruleengine.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import com.simpleruleengine.Operators;
import com.simpleruleengine.Rule;

/**
 * @author z0027pb
 *
 */
public class DBConnectionManager {
    // JDBC driver name and database URL
	
	/**
	 * Returns the operator
	 * @param strCode
	 * @return
	 */
	private static Operators getOperator(String strCode)
	{
		switch(strCode)
		{
		case "INVALID":
			return Operators.INVALID;
		case "NUM_GREATER_THAN":
			return Operators.NUM_GREATER_THAN;
		case "NUM_GREATER_THAN_OR_EQUAL":
			return Operators.NUM_GREATER_THAN_OR_EQUAL;
		case "NUM_LESSER_THAN":
			return Operators.NUM_LESSER_THAN;
		case "NUM_LESSER_THAN_OR_EQUAL":
			return Operators.NUM_LESSER_THAN_OR_EQUAL;
		case "NUM_EQUAL":
			return Operators.NUM_EQUAL;
		case "STR_EQUAL":
			return Operators.STR_EQUAL;
		case "STR_CONTAINS":
			return Operators.STR_CONTAINS;
		case "STR_DOES_NOT_CONTAIN":
			return Operators.STR_DOES_NOT_CONTAIN;
		case "DATE_EQUAL":
			return Operators.DATE_EQUAL;
		case "DATE_EARLIER_THAN":
			return Operators.DATE_EARLIER_THAN;
		case "DATE_EARLIER_THAN_OR_EQUAL":
			return Operators.DATE_EARLIER_THAN_OR_EQUAL;
		case "DATE_LATER_THAN":
			return Operators.DATE_LATER_THAN;
		case "DATE_LATER_THAN_OR_EQUAL":
			return Operators.DATE_LATER_THAN_OR_EQUAL;
		default:
			return Operators.INVALID;
		}

	}


    /**
     * Used for insert queries
     * @param insertQuery
     * @return 
     */
    public static long persist(String insertQuery)
    {
        
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(Constants.JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, 
                    Constants.DB_PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            int result = stmt.executeUpdate(insertQuery);
            if (result == 0)
            {
            	throw new Exception("Issues with updates");
            }
            //STEP 6: Clean-up environment
            stmt.close();
            conn.close();
            return Constants.SUCCESS;
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println(se.getMessage());
        } catch (Exception e) {
            //Handle errors for Class.forName
            System.out.println(e.getMessage());
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                System.out.println(se2.getMessage());
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return Constants.DB_CONN_ERR;
    }
    
    /**
     * 
     */
    public static Hashtable<Long,Rule> getAllRules() {
        Connection conn = null;
        Statement stmt = null;
        Hashtable<Long,Rule> listOfRules = new Hashtable<Long,Rule>();
        try {
            //STEP 2: Register JDBC driver
            Class.forName(Constants.JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, 
                    Constants.DB_PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Rules";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                long id = rs.getLong("idRules");
                String name = rs.getString("Attr_name");
                int oper = rs.getInt("Operation");
                String thresh = rs.getString("Threshold_Val");
                long phone = rs.getLong("IsNull");
                
                //Display values
                System.out.print("idRules: " + id);
                System.out.print(", Attr_name: " + name);
                System.out.print(", Operation: " + oper);
                System.out.print(", Threshold_Val: " + thresh);
                System.out.print(", IsNull: " + phone);
                listOfRules.put((long)id, new Rule(id, name, Operators.fromInteger(oper), (Object)thresh));
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return listOfRules;
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println(se.getMessage());
        } catch (Exception e) {
            //Handle errors for Class.forName
            System.out.println(e.getMessage());
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                System.out.println(se2.getMessage());
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    /**
     * 
     */
    public static long getMaxID() {
        Connection conn = null;
        Statement stmt = null;
        long id = 0;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(Constants.JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, 
                    Constants.DB_PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT max(idRules) FROM RuleDB.Rules;";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                id = rs.getLong(1);

                //Display values
                System.out.print("idRules: " + id);
            }
            else
            {
            	return 0;
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return id;
        } catch (SQLException se) {
            //Handle errors for JDBC
            System.out.println(se.getMessage());
            
        } catch (Exception e) {
            //Handle errors for Class.forName
            System.out.println(e.getMessage());
            
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                System.out.println(se2.getMessage());
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }//end finally try
            
        }//end try
        System.out.println("Goodbye!");
        return -1;
    }

}
