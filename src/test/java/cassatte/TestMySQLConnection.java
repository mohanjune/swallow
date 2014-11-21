/**
 * 
 */
package cassatte;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author muddana_m
 *
 */
public class TestMySQLConnection {

	final static Logger logger = LoggerFactory.getLogger(TestMySQLConnection.class );
	
	Connection con = null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		String mysqlUrl = "jdbc:mysql://localhost:3306/virtualgoods?";
		logger.info("MySQL URL " + mysqlUrl);
		
		try {
			con = DriverManager.getConnection(mysqlUrl +
                    "user=root&password=");
		} catch (SQLException e) {
			logger.error("Connection Failed");
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		if (null != con) {
			logger.info("Closing database connection");
			con.close();
		}
	}

	@Test
	public void test() {
		if (null != con) {
			logger.info("Connection to the mysql database is successful :)");
		} else {
			logger.info("Connection to the mysql database failed (:");
		}
	}

}
