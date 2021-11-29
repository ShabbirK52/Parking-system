import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;


//
public class NewTable implements ActionListener{
    JButton yes, no;
    JLabel message;
    JFrame f;

    NewTable() {
        f = new JFrame("Clear tables");
        f.setSize(300, 150);
        f.setLayout(null);
        
        message = new JLabel("Are you sure you want to clear all tables?");
        message.setBounds(25, 20, 300, 50);
        f.add(message);

        yes = new JButton("Yes");
        yes.setBounds(65, 70, 80, 30);
        yes.addActionListener(this);
        f.add(yes);

        no = new JButton("No");
        no.setBounds(155, 70, 80, 30);
        no.addActionListener(this);
        f.add(no);

        f.setLocation(500,300);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == yes) {
            try{
                Conn con = new Conn();
                String q1 = "truncate table Parking;";
                String q2 = "truncate table Bill;";
                String q3 = "truncate table seq;";
                con.s.executeUpdate(q1);
                con.s.executeUpdate(q2);
                con.s.executeUpdate(q3);
    
                JOptionPane.showMessageDialog(null,"All tables cleared");
            } catch(Exception e) {
                System.out.println("Error: "+e);
            }
            f.setVisible(false);
        }
        else if(ae.getSource() == no) {
            f.setVisible(false);
            new Details();
        }
    }

    public static void main(String[] args) {
        new NewTable();
    }
}
