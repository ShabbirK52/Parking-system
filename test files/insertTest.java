import java.sql.ResultSet;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class insertTest {

    @Test
    public void testInsert() {
        System.out.println("testing add");
        try {
            Conn cc = new Conn();
            String vehicle_no = "MH12 AB 1111"; 
            cc.s.executeUpdate("Delete from parking where vehicle_no = 'MH12 AB 1111';");
            String q = "insert into Parking(vehicle_no) values('"+vehicle_no+"')";
            cc.s.executeUpdate(q);
        	String sql = "Select vehicle_no from Parking where vehicle_no = 'MH12 AB 1111';";
            ResultSet rs = cc.s.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getString("vehicle_no"));
                assertEquals(vehicle_no, rs.getString("vehicle_no"));
            }
            
        } catch(Exception e) {
            fail(e.toString());
        }
    }
}
