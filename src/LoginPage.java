import util.DBUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginPage extends JFrame{
    private JPanel mainPanel;
    private JTextField usernameTF;
    private JLabel usernameLbl;
    private JLabel loginTitleLbl;
    private JLabel passwordLbl;
    private JTextField passwordTF;
    private JButton loginBtn;
    private JButton exitBtn;
    private JButton createAcctBtn;

    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 300;
    LoginPage(){
        setFrame();
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginUser();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        createAcctBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegisterPage rp = new RegisterPage();
            }
        });
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }//End Constructor

    private void setFrame(){
        setTitle("KC Gym");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//End setFrame()

    //Method to login the user when the 'Login' Button is clicked
    private void loginUser() throws SQLException {
        String username = usernameTF.getText().trim();
        String password = passwordTF.getText().trim();
        Connection conn = null;

        try {
            conn = DBUtil.getConnection();
            String userPassQuery = "SELECT ACCOUNT_USERNAME, ACCOUNT_PASSWORD FROM ACCOUNT;";
            Statement userPassStmt = conn.createStatement();
            ResultSet usernameResultSet = userPassStmt.executeQuery(userPassQuery);

            if(validateUser(usernameResultSet, username, password)){
                dispose();
                MemberProfile mp = new MemberProfile();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Login!");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            conn.close();

        }

    }//End loginUser()


    //Method to check if the username and password entered matches to that in the DB
    private boolean validateUser(ResultSet rs, String username, String password) throws SQLException {
        while(rs.next()){
            String user = rs.getString("ACCOUNT_USERNAME");
            String pass = rs.getString("ACCOUNT_PASSWORD");

            if(username.equals(user) && password.equals(pass)){
                return true;
            }

        }
        return false;
    }
}//End Class
