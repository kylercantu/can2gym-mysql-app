import util.DBUtil;
import util.FrameUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MemberProfilePage {
    private JPanel mainPanel;
    private JPanel accoutInfoPanel;
    private JPanel classesPanel;
    private JButton exitBtn;
    private JButton logoutBtn;
    private JLabel firstNameLbl;
    private JLabel lastNameLbl;
    private JLabel addressLbl;
    private JLabel cityLbl;
    private JLabel stateLbl;
    private JLabel zipLbl;
    private JLabel emailLbl;
    private JLabel phoneLbl;


    MemberProfilePage(String username) throws SQLException {
        FrameUtil.setFrame(mainPanel, "Can2Gym");
        populateAccountInfo(username);

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameUtil.disposeFrame();
                LoginPage lp = new LoginPage();
            }
        });
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    //Populate the account info section on the member's profile
    //username parameter which represents the username that the user inputs in during the login page
    private void populateAccountInfo(String username) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        LoginPage lp = new LoginPage();

        try{
            conn = DBUtil.getConnection();
            //Query to get all of the members account information (Returns 11 columns)
            String memberInfoQuery = "SELECT  ACCOUNT.ACCOUNT_USERNAME, ACCOUNT.ACCOUNT_PASSWORD, MEMBER.*\n" +
                    "FROM MEMBER\n" +
                    "INNER JOIN ACCOUNT ON ACCOUNT.MEM_ID=MEMBER.MEM_ID\n" +
                    "WHERE ACCOUNT_USERNAME = '" + username + "';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(memberInfoQuery);

            if(rs !=  null){
                while(rs.next()){
                    firstNameLbl.setText(rs.getString(4));
                    lastNameLbl.setText(rs.getString(5));
                    addressLbl.setText(rs.getString(6));
                    cityLbl.setText(rs.getString(7));
                    stateLbl.setText(rs.getString(8));
                    zipLbl.setText(rs.getString(9));
                    emailLbl.setText(rs.getString(10));
                    phoneLbl.setText(rs.getString(11));
                }
            } else {
                System.out.println("empty");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            stmt.close();
            rs.close();
            conn.close();
        }



        

    }//End getAccountInfo
}//End class
