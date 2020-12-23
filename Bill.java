import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//
public class Bill implements ActionListener{
    JFrame f;
    JLabel srNo, vhNo, timeOfExit, bill, hours;
    JLabel details;
    JButton back;

    public Bill() {

        srNo = new JLabel("Sr_no: ");
        vhNo = new JLabel("Vehicle No.: ");
        timeOfExit = new JLabel("Exit Time: ");
        hours = new JLabel("Hours: ");
        bill = new JLabel("Bill: ");
        
        try{
            Conn con = new Conn();
            String str = "select * from Bill where vehicle_no = '"+VehicleNumber.vhNo+"'";
            ResultSet rs= con.s.executeQuery(str);

            while(rs.next()){

                srNo = new JLabel("Sr_no: " + rs.getString(1));
                vhNo = new JLabel("Vehicle No.: "+rs.getString(2));
                timeOfExit = new JLabel("Exit Time: "+rs.getString(3));
                hours = new JLabel("Hours: "+rs.getString(4));
                bill = new JLabel("Bill: "+rs.getString(5));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    
        f = new JFrame("Bill");
        f.setBackground(Color.LIGHT_GRAY);
        f.setLayout(null);

        details = new JLabel("Exit from Parking");
        details.setFont(new Font("serif",Font.ITALIC,25));
        details.setBounds(150,30,200,40);
        f.add(details);

        
        srNo.setFont(new Font("serif",Font.BOLD,20));
        srNo.setBounds(70, 100, 300, 30);
        f.add(srNo);

        
        vhNo.setFont(new Font("serif",Font.BOLD,20));
        vhNo.setBounds(70, 160, 300, 30);
        f.add(vhNo);

        
        timeOfExit.setFont(new Font("serif",Font.BOLD,20));
        timeOfExit.setBounds(70, 220, 300, 30);
        f.add(timeOfExit);

        hours.setFont(new Font("serif",Font.BOLD,20));
        hours.setBounds(70, 280, 270, 30);
        f.add(hours);
        
        bill.setFont(new Font("serif",Font.BOLD,20));
        bill.setBounds(70, 340, 270, 30);
        f.add(bill);

        back = new JButton("Back");
        back.setBounds(200, 410, 80, 30);
        back.addActionListener(this);
        f.add(back);


        f.setVisible(true);
        f.setSize(500,500);
        f.setLocation(400,150);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            f.setVisible(false);
            new Details();
        }
    }

    public static void main(String[] args) {
        new Bill();
    }
}
