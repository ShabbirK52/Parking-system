
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Remove implements ActionListener{
    JFrame f;
    JButton search;
    JButton delete;
    JButton cancel;
    static JTextField vehicleNo = new JTextField();
    JLabel details;
    JLabel vn;
    JLabel srNo, vhNo, timeOfEntry, status;

    public Remove(){
        f = new JFrame("Exit Parking");
        f.setBackground(Color.LIGHT_GRAY);
        f.setLayout(null);
        details = new JLabel("Exit from Parking");
        details.setFont(new Font("serif",Font.ITALIC,25));
        details.setBounds(150,30,200,40);
        f.add(details);

        vn = new JLabel("Vehicle No. :");
        vn.setFont(new Font("serif",Font.BOLD,20));
        vn.setBounds(70, 90, 160, 30);
        f.add(vn);

        vehicleNo.setBounds(230,90,150,30);
        f.add(vehicleNo);

        search = new JButton("Search");
        search.setBounds(150,150,90,30);
        f.add(search);

        cancel = new JButton("Cancel");
        cancel.setBounds(260,150,90,30);
        f.add(cancel);

        search.addActionListener(this);
        cancel.addActionListener(this);

        srNo = new JLabel("Sr_no: ");
        srNo.setFont(new Font("serif",Font.BOLD,20));
        srNo.setBounds(70, 210, 300, 30);
        f.add(srNo);
        
        vhNo = new JLabel("Vehicle No.: ");
        vhNo.setFont(new Font("serif",Font.BOLD,20));
        vhNo.setBounds(70, 260, 300, 30);
        f.add(vhNo);

        timeOfEntry = new JLabel("Entry Time: ");
        timeOfEntry.setFont(new Font("serif",Font.BOLD,20));
        timeOfEntry.setBounds(70, 310, 350, 30);
        f.add(timeOfEntry);

        status = new JLabel("Status: ");
        status.setFont(new Font("serif",Font.BOLD,20));
        status.setBounds(70, 360, 350, 30);
        f.add(status);

        delete= new JButton("Get Bill");
        delete.setBounds(205,410,90,30);
        f.add(delete);
        delete.addActionListener(this);

        srNo.setVisible(false);
        vhNo.setVisible(false);
        timeOfEntry.setVisible(false);
        status.setVisible(false);
        delete.setVisible(false);
        
        f.setVisible(true);
        f.setSize(500,500);
        f.setLocation(400,150);
    }

    public static void getVhNo() {
        VehicleNumber.vhNo = vehicleNo.getText();
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == search){
            try{
                Conn con = new Conn();
                getVhNo();
                String str = "select sr_no, vehicle_no, dt_of_entry, status from Parking where vehicle_no = '"+VehicleNumber.vhNo+"' and status = 'P';";
                ResultSet rs = con.s.executeQuery(str);
                
                Boolean found = false;
                if(rs.next()){
                    found = true;
                    srNo.setText(srNo.getText()+rs.getString(1));
                    vhNo.setText(vhNo.getText()+rs.getString(2));
                    timeOfEntry.setText(timeOfEntry.getText()+rs.getString(3));
                    status.setText(status.getText()+rs.getString(4));

                    srNo.setVisible(true);
                    vhNo.setVisible(true);
                    timeOfEntry.setVisible(true);
                    status.setVisible(true);
                    delete.setVisible(true);
                }
                if(!found) {
                    JOptionPane.showMessageDialog(null,"Vehicle not found");
                }

            } 
            catch(Exception e) {
                System.out.println("Error: " + e);
            }
        }

        else if(ae.getSource() == cancel) {
            new Details();
            f.setVisible(false);
        }

        else if(ae.getSource() == delete) {
            try {
                Conn con = new Conn();
                CallableStatement cStmt = con.con.prepareCall("{call cal(?)}");
                cStmt.setString(1, VehicleNumber.vhNo);
                cStmt.execute();
            } catch(Exception e) {
                System.out.println("Error: " + e);
            }


            f.setVisible(false);
            new Bill();
        }
    }

    public static void main(String[] args) {
        new Remove();
    }
}
    

