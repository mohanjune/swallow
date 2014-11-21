/**
 * 
 */
package cassatte;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author muddana_m
 *
 */
public class FetchDatabaseMetaData {

	final static Logger logger = LoggerFactory.getLogger(FetchDatabaseMetaData.class );
	
	
	public boolean testConnection(String url, String user, String password) {

		boolean result = false;
		Connection con = null;;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url +
                    "user="+ user + "&password=" + password);
			if (null != con) { 
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			logger.error("Connection Failed");
		}
		
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Connection con = null;;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String mysqlUrl = "jdbc:mysql://localhost:3306/virtualgoods?";
		//logger.info("MySQL URL " + mysqlUrl);
		
		try {
			con = DriverManager.getConnection(mysqlUrl +
                    "user=root&password=");
			String[] types = {"TABLE"};
			if (null != con){
				DatabaseMetaData metadata = con.getMetaData();
				logger.info("Database Type: " + metadata.getDatabaseProductName());
				logger.info("Database Version: " + metadata.getDatabaseProductVersion());
				
				ResultSet rsTables = metadata.getTables("virtualgoods", "", "", types );
				List<String> tableNames = new ArrayList<String>();
				while (rsTables.next()) {
					logger.info(rsTables.getString("TABLE_NAME"));
					tableNames.add(rsTables.getString("TABLE_NAME"));
					
					ResultSet rsCols = metadata.getColumns(null, null, rsTables.getString("TABLE_NAME"), null);
					
					logger.info("Printing Column Names and Types -------------: ");
					while (rsCols.next()) {
						logger.info(rsCols.getString("COLUMN_NAME") + " - " + rsCols.getString("TYPE_NAME"));
						
					}
				}
				
			}
			
		} catch (SQLException e) {
			logger.error("Connection Failed");
			e.printStackTrace();
		}
		
	}

}
