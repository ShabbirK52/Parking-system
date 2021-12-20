import java.sql.ResultSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BillTest {
    @BeforeEach
    void before() {
        System.out.println("Starting bill testing");
    }

    @Test
    public void testBill () {
        try {
            Conn con = new Conn();
            String str = "select * from Bill where vehicle_no = 'MH12 AB 1111'";
            ResultSet rs= con.s.executeQuery(str);

            while(rs.next()){
                String hours = rs.getString(4);
                if(Integer.parseInt(hours) <= 1) {
                    System.out.println("Hours = " + hours);
                    assertEquals("5", rs.getString(5));
                    System.out.println("Bill = " + rs.getString(5));
                }
                else {
                    assertEquals("" + 10 * Integer.parseInt(hours), rs.getString(5));
                }
            }
        } catch(Exception e) {
            fail(e.toString());
        }
    }
}
