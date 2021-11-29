import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RemoveTest {

	@BeforeEach
	void before() {
		System.out.println("Starting Remove Vehicle Test");
	}
	
	void check(Statement stCheck, String vehicle_no) throws SQLException {
		ResultSet rs = stCheck.executeQuery("SELECT vehicle_no FROM parking WHERE vehicle_no='" + vehicle_no + "';");
		
		while(rs.next()) {
			assertEquals(vehicle_no, rs.getString("vehicle_no"));
		}
	}
	
	@Test
	void testRemove() {
		
		try {
            Conn con = new Conn();
            check(con.s, "MH12 AB 1111");
            CallableStatement cStmt = con.con.prepareCall("{call cal(?)}");
            cStmt.setString(1, "MH12 AB 1111");
            cStmt.execute();
        }
		catch(Exception e) {
			fail(e.toString());
		}
	}
	
	@AfterEach
	void after() {
		try{
            Conn con = new Conn();
            String sql = "Select * from Parking where vehicle_no = 'MH12 AB 1111';";
            ResultSet rs = con.s.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getString("status"));
                assertEquals("L", rs.getString("status"));
            }
		}
		catch(Exception e) {
			fail(e.toString());
		}
	}

}
