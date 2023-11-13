import util.DBUtil;
import util.FrameUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterPage extends JFrame{
    private JPanel mainPanel;
    private JLabel registerTitleLbl;
    private JTextField emailTF;
    private JTextField usernameTF;
    private JTextField passwordTF;
    private JTextField firstNameTF;
    private JTextField lastNameTF;
    private JButton registerBtn;
    private JButton backBtn;
    private JLabel firstNameLbl;
    private JLabel lastNameLbl;
    private JLabel emailLbl;
    private JLabel usernameLbl;
    private JLabel passwordLbl;
    private JTextField streetTF;
    private JLabel streetLbl;
    private JTextField cityTF;
    private JComboBox stateComboBox;
    private JTextField zipCodeTF;
    private JLabel stateLbl;
    private JLabel cityLbl;
    private JLabel zipLbl;
    private JTextField phoneTF;
    private JLabel phoneLbl;

    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 700;

    private Connection conn = null;


    RegisterPage (){
        FrameUtil.setFrame(mainPanel);
        //Sends the user back to the login screen when they click back
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameUtil.disposeFrame();
                LoginPage lp = new LoginPage();
            }
        });

        //When the user clicks the register button, it sends the information to the DB
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }//End constructor

    //Takes all the user's inputs and stores them into the DB
    private void registerUser(){
        String fName = firstNameTF.getText().trim();
        String lName = lastNameTF.getText().trim();
        String street = streetTF.getText().trim();
        String city = cityTF.getText().trim();
        String state = stateComboBox.getSelectedItem().toString();
        String zip = zipCodeTF.getText().trim();
        String email = emailTF.getText().trim();
        String phoneNum = phoneTF.getText().trim();
        String username = usernameTF.getText().trim();
        String password = passwordTF.getText().trim();
        //Declare and Initialize preparestatements that are to be used
        PreparedStatement memberPrepStmt = null;
        PreparedStatement accountPrepStmt = null;
        //Queries that are used to insert the textfields inputs into the DB
        String memberQuery = "INSERT INTO member(MEM_FNAME, MEM_LNAME, MEM_STREET, MEM_CITY, MEM_STATE, MEM_ZIP, " +
                "MEM_EMAIL, MEM_PHONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        String accountQuery = "INSERT INTO account(ACCOUNT_USERNAME, ACCOUNT_PASSWORD, MEM_ID) VALUES (?, ?, ?);";


        try{
            //Establish the DB connection
            conn = DBUtil.getConnection();

            //Get resuletset containing usernaames to check if the username already exists within the DB
            String getAllUsernamesQuery = "SELECT ACCOUNT_USERNAME FROM ACCOUNT;";
            Statement usernameStmt = conn.createStatement();
            ResultSet usernameResultSet = usernameStmt.executeQuery(getAllUsernamesQuery); //Place that stores the retreived usernames to iterate over
            System.out.println(usernameResultSet);
            boolean usernameExists = isRegistered(usernameResultSet, username);

            //Statements to enter user input to  DB if condition allows
            memberPrepStmt = conn.prepareStatement(memberQuery , Statement.RETURN_GENERATED_KEYS); //Returns the auto-generated primary key ID
            accountPrepStmt = conn.prepareStatement(accountQuery);


            //If the username DOESN'T exist in the DB
            if(!usernameExists){

                //Inserts the textfield inputs associated with the member into the Member table of the DB
                memberPrepStmt.setString(1, fName);
                memberPrepStmt.setString(2, lName);
                memberPrepStmt.setString(3, street);
                memberPrepStmt.setString(4, city);
                memberPrepStmt.setString(5, state);
                memberPrepStmt.setString(6, zip);
                memberPrepStmt.setString(7, email);
                memberPrepStmt.setString(8, phoneNum);
                memberPrepStmt.executeUpdate();

                //Resultset of the key that was auto generated
                ResultSet generatedKeys = memberPrepStmt.getGeneratedKeys();

                //Gave default value to the variable that will hold key that will be auto generated
                int generatedKey = -1;

                //Checks to see if the resultset contains a row with a number(generated key)
                if(generatedKeys.next()){
                    generatedKey = generatedKeys.getInt(1); //Get the number in column one
                    System.out.println(generatedKey);
                }

                //Inserts the textfield inputs associated with the account into the Account table of the DB
                accountPrepStmt.setString(1, username);
                accountPrepStmt.setString(2, password);
                accountPrepStmt.setInt(3, generatedKey);
                accountPrepStmt.executeUpdate();

                //Window message to notify user that their account was created successfully
                JOptionPane.showMessageDialog(null, "Account Created!");

            } else {
                JOptionPane.showMessageDialog(null, "Username already registered!");
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                memberPrepStmt.close();
                accountPrepStmt.close();
                conn.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        }//End registerUser()s




    //Method to check if the username already exists in the DB
    private boolean isRegistered(ResultSet rs , String username) throws SQLException {
        while(rs.next()){
            int columnCount = rs.getMetaData().getColumnCount();
            for(int i = 1; i <= columnCount; i++){
                String columnValue = rs.getString(i);
                if((columnValue != null && columnValue.contains(username))){
                    return true;
                }
            }
        }
        return false;
    }//End isRegistered()







}//End Class
