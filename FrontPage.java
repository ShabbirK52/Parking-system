import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FrontPage implements ActionListener{

    JButton home;
    JFrame f;

    FrontPage() {
        f = new JFrame("PAY'N PARK");
        f.setLayout(null);
        f.setSize(500,500);
        JLabel title = new JLabel("PAY'N PARK");
        title.setFont(new Font("serif",Font.ITALIC, 50));
        title.setBounds(115,100,400,100);
        f.add(title);

        home = new JButton("Home");
        home.setBounds(200, 250, 100, 40);
        home.addActionListener(this);
        f.add(home);
 
        f.setVisible(true);
        f.setLocation(450,150);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == home) {
            f.setVisible(false);
            new Details();
        }
    }

    public static void main(String[] args) {
        new FrontPage();
    }

}
