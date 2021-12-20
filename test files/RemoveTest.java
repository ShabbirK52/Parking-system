import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class RemoveTest {

	@BeforeEach
	void init(TestInfo testInfo) {
		System.out.println("Starting " + testInfo.getDisplayName());
	}
	
	void check(Statement stCheck, String vehicle_no) throws SQLException {
		ResultSet rs = stCheck.executeQuery("SELECT vehicle_no FROM parking WHERE vehicle_no='" + vehicle_no + "' and status = 'P';");
		assertTrue(rs.next());
	}

	@Test
	@DisplayName("Remove non existing vehicle from parking")
	void testRemoveNonExistingVehicle() {
		try {
            Conn con = new Conn();
            check(con.s, "MH12 AB 2222");
            CallableStatement cStmt = con.con.prepareCall("{call cal(?)}"); 
            cStmt.setString(1, "MH12 AB 2222");
            cStmt.execute();
			String sql = "Select * from Parking where vehicle_no = 'MH12 AB 786';";
            ResultSet rs = con.s.executeQuery(sql);
            while(rs.next()) {
                System.out.println("vehicle status in db: " + rs.getString("status"));
                assertEquals("L", rs.getString("status"));
            }
        }
		catch(Exception e) {
			fail(e.toString());
		}
	}

	@Test
	@DisplayName("Remove existing vehicle from parking")
	void testRemoveExistingVehicle() {
		try {
            Conn con = new Conn();
            check(con.s, "MH12 AB 786");
            CallableStatement cStmt = con.con.prepareCall("{call cal(?)}"); 
            cStmt.setString(1, "MH12 AB 786");
            cStmt.execute();
			String sql = "Select * from Parking where vehicle_no = 'MH12 AB 786';";
            ResultSet rs = con.s.executeQuery(sql);
            while(rs.next()) {
                System.out.println("vehicle status in db: " + rs.getString("status"));
                assertEquals("L", rs.getString("status"));
            }
        }
		catch(Exception e) {
			fail(e.toString());
		}
	}

	@AfterEach
	void after(TestInfo testInfo) {
		System.out.println(testInfo.getDisplayName() + " completed.");
	}

}
