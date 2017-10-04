/**
 * 
 */
package com.simpleruleengine.utils;

/**
 * @author z0027pb
 *
 */
public final class Constants {
    
    /**
     * Error codes
     */
    public static final long SUCCESS = 0x00000000L;
    
    /**
     * DB Errors
     */
    public static final long DB_CONN_ERR = 0x10000001L;
    
    /**
     * DB Constants
     */
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/RuleDB";

    //  Database credentials
    public static final String DB_USER = "root";
    public static final String DB_PASS = "root";
}
