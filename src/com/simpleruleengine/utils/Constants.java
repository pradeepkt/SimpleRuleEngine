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
    
    // Queries
    public static final String DB_TABLE_DROP_QUERY = "DROP TABLE `RuleDB`.`Rules`;";
    public static final String DB_TABLE_CREATE_QUERY = "CREATE TABLE `Rules` ( "
			+ "`idRules` int(11) NOT NULL, "
			+ "`Attr_name` varchar(256) NOT NULL,"
			+ "`Operation` int(10) NOT NULL, "
			+ "`Threshold_Val` varchar(256) NOT NULL, "
			+ "`IsNull` int(11) DEFAULT NULL, "
			+ " PRIMARY KEY (`idRules`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
    public static final String DB_TABLE_TRUNCATE_QUERY = "TRUNCATE `RuleDB`.`Rules`;";
}
