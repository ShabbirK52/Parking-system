import java.sql.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class insertTest {

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println("Starting " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Correct vehicle no test")
    public void testInsert() {
       
        try {
            Conn cc = new Conn();
            String vehicle_no = "MH12 AB 1111"; 
            System.out.println("Vehicle no. being inserted into db: " + vehicle_no);
            cc.s.executeUpdate("Delete from parking where vehicle_no = '"+vehicle_no+"';");
            String q = "insert into Parking(vehicle_no) values('"+vehicle_no+"')";
            cc.s.executeUpdate(q);
        	String sql = "Select vehicle_no from Parking where vehicle_no = 'MH12 AB 1111';";
            ResultSet rs = cc.s.executeQuery(sql);
            while(rs.next()) {
                System.out.println("Vehicle no obtained from db: " + rs.getString("vehicle_no"));
                assertEquals(vehicle_no, rs.getString("vehicle_no"));
            }
        } catch(Exception e) {
            fail(e.toString());
        }
    }

    @Test
    @DisplayName("Case Sensitivity test")
    public void testCaseSensitivity() {
        try {
            Conn cc = new Conn();
            String vehicle_no = "MH12 AB 1111"; 
            cc.s.executeUpdate("Delete from parking where vehicle_no = '"+vehicle_no+"';");
            String q = "insert into Parking(vehicle_no) values('"+vehicle_no+"')";
            cc.s.executeUpdate(q);
        	String sql = "Select vehicle_no from Parking where vehicle_no = 'MH12 ab 1111';";
            ResultSet rs = cc.s.executeQuery(sql);
            while(rs.next()) {
                System.out.println("The vehicle number obtained from database: " + rs.getString("vehicle_no"));
                assertEquals(vehicle_no, rs.getString("vehicle_no"));
            }
        } catch(Exception e) {
            fail(e.toString());
        }
    }

    @Test
    @DisplayName("Incorrect vehicle no test")
    public void testIncorrectVehicleNo() {
        try {
            Conn cc = new Conn();
            String vehicle_no = "abcd 1234"; 
            cc.s.executeUpdate("Delete from parking where vehicle_no = '"+vehicle_no+"';");
            String q = "insert into Parking(vehicle_no) values('"+vehicle_no+"')";
            int rowCount = cc.s.executeUpdate(q);
        	assertEquals(0, rowCount);
        } catch(Exception e) {
            fail(e.toString());
        }
    }

    @Test
    @DisplayName("Null vehicle no test")
    public void testNull() {
        try {
            Conn cc = new Conn();
            String vehicle_no = "";
            cc.s.executeUpdate("Delete from parking where vehicle_no = '"+vehicle_no+"';");
            String q = "insert into Parking(vehicle_no) values('"+vehicle_no+"')";
            int rowCount = cc.s.executeUpdate(q);
        	assertEquals(0, rowCount);
        } catch(Exception e) {
            fail(e.toString());
        }
    }

    @AfterEach
    public void afterTests (TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " Completed");
    }
}
