import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Purchase_stockTest {
	@BeforeEach
	void before() {
//		Does nothing
		System.out.println("Starting Purchase_Stock Test");
	}
	
	void check(Statement stCheck, String prodId, String prodName, String pricePerItem, int stock, Date sqlDate, Timestamp sqlTime) throws SQLException {
		ResultSet rs = stCheck.executeQuery("SELECT * FROM PRODUCT WHERE PRODID='" + prodId + "';");
		
		while(rs.next()) {
			assertEquals(prodId, rs.getString("prodId"));
			assertEquals(prodName, rs.getString("prodName"));
			assertEquals(pricePerItem, rs.getString("pricePerItem"));
			assertEquals(stock, rs.getInt("stock"));
		}
	}
	
	@Test
	void testAddStock() {
		Purchase_stock purchase_stock = new Purchase_stock();
		
		try(Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");){
			try(Statement stCheck = connection.createStatement()){
				connection.setAutoCommit(false);
				
				java.util.Date date = new java.util.Date();

				Date sqlDate = new Date(date.getTime());
				Timestamp sqlTime = new Timestamp(date.getTime());
				
				connection.commit();
				
				purchase_stock.addStock("test01", "Test1", "10.00", 25, sqlDate, sqlTime);
				purchase_stock.addStock("test02", "Test2", "-10.00", -25, sqlDate, sqlTime);
				purchase_stock.addStock("", "", "12.00", 25, sqlDate, sqlTime);
				
				
				check(stCheck, "test01", "Test1", "10.00", 25, sqlDate,sqlTime);
				check(stCheck, "test02", "Test2", "-10.00", -25, sqlDate,sqlTime);
				check(stCheck, "", "", "12.00", 25, sqlDate,sqlTime);

			}
			finally {
				connection.rollback();
			}
		}
		catch(SQLException e) {
			fail(e.toString());
		}
	}
	
	@AfterEach
	void after() {
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");){
			try(Statement stCheck = connection.createStatement()){
				stCheck.executeUpdate("DELETE from product where prodId='test01';");
				stCheck.executeUpdate("DELETE from product where prodId='test02';");
				stCheck.executeUpdate("DELETE from product where prodId='';");
			}
		}
		catch(SQLException e) {
			fail(e.toString());
		}
	}

}
