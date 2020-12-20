
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Add implements ActionListener{
    
    JFrame f;
    JTextField vehicleNo;
    JLabel vn;
    JLabel details;
    JButton b1;
    JButton b2;

    Add() {
        f = new JFrame("New Entery");
        f.setBackground(Color.lightGray);
        f.setLayout(null);
        details = new JLabel("New Vehicle");
        details.setFont(new Font("serif",Font.ITALIC,25));
        details.setBounds(170,50,200,40);
        f.add(details);

        vn = new JLabel("Vehicle Number:");
        vn.setFont(new Font("serif",Font.BOLD,20));
        vn.setBounds(70, 150, 160, 30);
        f.add(vn);

        vehicleNo = new JTextField();
        vehicleNo.setBounds(230,150,150,30);
        f.add(vehicleNo);

        b1= new JButton("Submit"); 
        b1.setBounds(100,350,100,40);
        
        f.add(b1);

        b2=new JButton("Cancel");   
        b2.setBounds(250,350,100,40);
        
        f.add(b2);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        f.setVisible(true);
        f.setSize(500, 500);
        f.setLocation(400,150);
       
    }

    public void actionPerformed(ActionEvent ae){
        String vh_no = vehicleNo.getText();
        
        if(ae.getSource() == b1){
            try{
                Conn cc = new Conn();
                String q = "insert into Parking(vehicle_no) values('"+vh_no+"')";
                cc.s.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Details Successfully Inserted");
                f.setVisible(false);
                new Details();
            }catch(Exception ee){
                System.out.println("The error is:"+ee);
                System.out.println(vh_no);
            }
        }else if(ae.getSource() == b2){
            f.setVisible(false);
            new Details();
        }
    }

    public static void main(String[ ] arg){
        new Add();
    }
}
