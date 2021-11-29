import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.*;

import org.junit.Test;

public class Conn {
    
    public Connection con;
    public Statement s;
 
    public Conn() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqa_project","root","Shabbir@53");
            s = con.createStatement();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ConnTest() {
        Conn connection = new Conn();
        System.out.println("Connection obj testing");
        assertNotNull(connection, "Connected succesfully");
    }
}
 