

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Details  implements ActionListener{

    JFrame f;
    JLabel l1;
    JButton b1,b2,b3,b4;
    final String font = "serif";

    Details(){
        f=new JFrame("Member Detail");
        f.setBackground(Color.lightGray);
        f.setLayout(null);


        l1 = new JLabel("HOME PAGE");
        l1.setBounds(175,20,250,90);
        l1.setFont(new Font(font,Font.BOLD,25));
        l1.setForeground(Color.black);
        f.add(l1);

        b1=new JButton("Add");
        b1.setBounds(200,140,100,40);
        b1.setFont(new Font(font,Font.BOLD,15));
        b1.addActionListener(this);
        f.add(b1);


        b2=new JButton("View");
        b2.setBounds(200,220,100,40);
        b2.setFont(new Font(font,Font.BOLD,15));
        b2.addActionListener(this);
        f.add(b2);

        b3=new JButton("Remove");
        b3.setBounds(200,300,100,40);
        b3.setFont(new Font(font,Font.BOLD,15));
        b3.addActionListener(this);
        f.add(b3);

        b4=new JButton("New table");
        b4.setBounds(200,380,100,40);
        b4.setFont(new Font(font,Font.BOLD,15));
        b4.addActionListener(this);
        f.add(b4);

        f.setVisible(true);
        f.setSize(500,500);
        f.setLocation(450,150);

    }

    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==b1)
        {
            f.setVisible(false);
            new Add();
        }
        else if(ae.getSource()==b2)
        {
            f.setVisible(false);
            new View();
        }
        else if(ae.getSource()==b3)
        {
            f.setVisible(false);
            new Remove();
        }
         if(ae.getSource()==b4)
        {
            try{
                Conn con = new Conn();
                String q1 = "truncate table Parking;";
                String q2 = "truncate table Bill;";
                con.s.executeUpdate(q1);
                con.s.executeUpdate(q2);
    
                JOptionPane.showMessageDialog(null,"All tables cleared");
            } catch(Exception e) {
                System.out.println("Error: "+e);
            }
        }
       
    }

    public static void main(String[ ] arg){
        new Details();
    }
}
