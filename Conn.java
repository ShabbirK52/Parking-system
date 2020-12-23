import java.sql.*;

public class Conn{
    
    public Connection con;
    public Statement s;
 
    public Conn(){
        try{
            //("jdbc:mysql://localhost:3306/<database>","<username>","<password>")
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Shabbir@53");
            s = con.createStatement();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
 