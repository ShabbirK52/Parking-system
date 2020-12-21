import java.sql.*;

public class Conn{
    
    public Connection con;
    public Statement s;
 
    public Conn(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/<database>","<username>","<password>");
            s = con.createStatement();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
 