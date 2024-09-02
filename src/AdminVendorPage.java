import util.DBUtil;
import util.FrameUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AdminVendorPage {
    private JPanel mainPanel;
    private JTextField vendorNameTF;
    private JTextField venderPhoneTF;
    private JTextField vendorAddressTF;
    private JButton cancelBtn;
    private JButton saveBtn;

    AdminVendorPage () {
        FrameUtil.setFrame(mainPanel, "Can2Gym");

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addVendor();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage ap = new AdminPage("Can2Gym");
            }
        });
    }//End AdminVendorPage constructor

    private void addVendor() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        if(vendorAddressTF.getText().isEmpty() || venderPhoneTF.getText().isEmpty() || vendorAddressTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Fill in Empty Fields");
        } else {
            try{
                conn = DBUtil.getConnection();
                String insertVendorQuery = "INSERT INTO VENDOR (VENDOR_NAME, VENDOR_PHONE, VENDOR_ADDRESS) VALUES (?, ?, ?);";
                ps = conn.prepareStatement(insertVendorQuery);
                ps.setString(1, vendorNameTF.getText());
                ps.setString(2, venderPhoneTF.getText());
                ps.setString(3, vendorAddressTF.getText());
                ps.executeUpdate();


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.close();
                ps.close();
            }
            JOptionPane.showMessageDialog(null, "Successfully Saved");
        }// End if-else

    }//End addVendor method

}//End AdminVendorPage class
