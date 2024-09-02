import util.DBUtil;
import util.FrameUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminMemberEditPage {
    private JPanel mainPanel;
    private JPanel accountInfoPanel;
    private JButton cancelBtn;
    private JButton saveBtn;
    private JTextField firstNameTF;
    private JTextField lastNameTF;
    private JTextField addressTF;
    private JTextField cityTF;
    private JTextField stateTF;
    private JTextField zipTF;
    private JTextField emailTF;
    private JTextField phoneTF;

    AdminMemberEditPage(int memberID) throws SQLException {
        FrameUtil.setFrame(mainPanel, "Can2Gym");
        populateMemberInfoForEdit(memberID);


        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage ap = new AdminPage("Can2Gym");
            }
        });
        
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateMemberAccount(memberID);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }


//----------------------------------------------------------------------------------------------------------------------


    private void populateMemberInfoForEdit (int memberID) throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        Statement memberInfoStmt = null;

        try {
            conn = DBUtil.getConnection();

            String memberInfoQuery = "SELECT * FROM MEMBER WHERE MEM_ID = " + memberID;
            memberInfoStmt = conn.createStatement();
            rs = memberInfoStmt.executeQuery(memberInfoQuery);

            if(rs != null){
                while(rs.next()){
                    firstNameTF.setText(rs.getString(2));
                    lastNameTF.setText(rs.getString(3));
                    addressTF.setText(rs.getString(4));
                    cityTF.setText(rs.getString(5));
                    stateTF.setText(rs.getString(6));
                    zipTF.setText(rs.getString(7));
                    emailTF.setText(rs.getString(8));
                    phoneTF.setText(rs.getString(9));

                }


            }//End resultset if statement


        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            memberInfoStmt.close();
            rs.close();
            conn.close();
        }

    }//End populateMemberInfoForEdit method


    private void updateMemberAccount(int memberID) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBUtil.getConnection();
            String memberInfoQuery = "SELECT * FROM MEMBER WHERE MEM_ID = " + memberID;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(memberInfoQuery);

            if(rs != null){
                while(rs.next()){
                    if(!firstNameTF.getText().equals(rs.getString(2))){
                        String updateFirstNameQuery = "UPDATE MEMBER SET MEM_FNAME = ? WHERE MEM_ID = " + memberID;
                        PreparedStatement ps = conn.prepareStatement(updateFirstNameQuery);
                        ps.setString(1, firstNameTF.getText());
                        ps.executeUpdate(); //DONT FORGET TO EXECUTEUPDATE TO EXECUTE STATMENT!!!!!!
                    }
                    if(!lastNameTF.getText().equals(rs.getString(3))){
                        String updateLastNameQuery = "UPDATE MEMBER SET MEM_LNAME = ? WHERE MEM_ID = " + memberID;
                        PreparedStatement ps = conn.prepareStatement(updateLastNameQuery);
                        ps.setString(1, lastNameTF.getText());
                        ps.executeUpdate(); //DONT FORGET TO EXECUTEUPDATE TO EXECUTE STATMENT!!!!!!
                    }
                    if(!addressTF.getText().equals(rs.getString(4))){
                        String updateAddressQuery = "UPDATE MEMBER SET MEM_STREET = ? WHERE MEM_ID = " + memberID;
                        PreparedStatement ps = conn.prepareStatement(updateAddressQuery);
                        ps.setString(1, addressTF.getText());
                        ps.executeUpdate(); //DONT FORGET TO EXECUTEUPDATE TO EXECUTE STATMENT!!!!!!
                    }
                    if(!cityTF.getText().equals(rs.getString(5))){
                        String updateCityQuery = "UPDATE MEMBER SET MEM_CITY = ? WHERE MEM_ID = " + memberID;
                        PreparedStatement ps = conn.prepareStatement(updateCityQuery);
                        ps.setString(1, cityTF.getText());
                        ps.executeUpdate(); //DONT FORGET TO EXECUTEUPDATE TO EXECUTE STATMENT!!!!!!
                    }
                    if(!stateTF.getText().equals(rs.getString(6))){
                        String updateStateQuery = "UPDATE MEMBER SET MEM_STATE = ? WHERE MEM_ID = " + memberID;
                        PreparedStatement ps = conn.prepareStatement(updateStateQuery);
                        ps.setString(1, stateTF.getText());
                        ps.executeUpdate(); //DONT FORGET TO EXECUTEUPDATE TO EXECUTE STATMENT!!!!!!
                    }
                    if(!zipTF.getText().equals(rs.getString(7))){
                        String updateZipQuery = "UPDATE MEMBER SET MEM_ZIP = ? WHERE MEM_ID = " + memberID;
                        PreparedStatement ps = conn.prepareStatement(updateZipQuery);
                        ps.setString(1, zipTF.getText());
                        ps.executeUpdate(); //DONT FORGET TO EXECUTEUPDATE TO EXECUTE STATMENT!!!!!!
                    }
                    if(!emailTF.getText().equals(rs.getString(8))){
                        String updateEmailQuery = "UPDATE MEMBER SET MEM_EMAIL = ? WHERE MEM_ID = " + memberID;
                        PreparedStatement ps = conn.prepareStatement(updateEmailQuery);
                        ps.setString(1, emailTF.getText());
                        ps.executeUpdate(); //DONT FORGET TO EXECUTEUPDATE TO EXECUTE STATMENT!!!!!!
                    }
                    if(!phoneTF.getText().equals(rs.getString(9))){
                        String updatePhoneQuery = "UPDATE MEMBER SET MEM_PHONE = ? WHERE MEM_ID = " + memberID;
                        PreparedStatement ps = conn.prepareStatement(updatePhoneQuery);
                        ps.setString(1, phoneTF.getText());
                        ps.executeUpdate(); //DONT FORGET TO EXECUTEUPDATE TO EXECUTE STATMENT!!!!!!
                    }

                }//End while
                JOptionPane.showMessageDialog(null, "Changes Successfully Saved");
            }//End if


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            stmt.close();
            rs.close();
            conn.close();
        }

    }//End updateMemberAccount method






}//End AdminMemberEditPage class
