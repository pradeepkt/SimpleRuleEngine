/**
 * 
 */
package com.simpleruleengine.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                Object threshold_value;
                
                //Display values
                System.out.print("idRules: " + id);
                System.out.print(", Attr_name: " + name);
                System.out.print(", Operation: " + oper);
                System.out.print(", Threshold_Val: " + thresh);
                System.out.print(", IsNull: " + phone);
        		switch (oper)
        		{
        			case 10:
        			case 11:
        			case 12:
        			case 13:
        			case 14:
        				threshold_value = new Long(thresh);
        				break;
        			case 20:
        			case 21:
        			case 22:
        				threshold_value = new String(thresh);
        				break;
        			case 30:
        			case 31:
        			case 32:
        			case 33:
        			case 34:
        				threshold_value = new Date(Long.parseLong(thresh));
        				break;
        			default:
        				return null;
        		}
                listOfRules.put((long)id, new Rule(id, name, Operators.fromInteger(oper), (Object)threshold_value));
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
