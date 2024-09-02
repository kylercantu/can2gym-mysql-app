import util.DBUtil;
import util.FrameUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminPage {

    private JPanel mainPanel;
    private JPanel membersJPanel;
    private JTable membersJTable;
    private JButton addMemberBtn;
    private JButton editBtn;
    private JPanel vendorJPanel;
    private JButton addVendorBtn;
    private JButton removeVendorBtn;
    private JTable vendorJTable;

    AdminPage(String title){
        FrameUtil.setFrame(mainPanel, title);
        populateMemberTable();
        populateVendorTable();


        /*-----------------------------------------Edit Members Buttons-----------------------------------------------*/

        //Button to edit a member's information
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Popup that asks admin to enter the ID of the member they'd like to change info of
                String input = JOptionPane.showInputDialog(null, "Enter Member's ID");

                if(input.isEmpty() || input == null){
                    JOptionPane.showMessageDialog(null, "Invalid Member ID");

                } else {
                    int memberID = Integer.parseInt(input); //Holds the member's ID to be edited by Admin
//                    System.out.println(memberID);

                    try {
                        AdminMemberEditPage editPage = new AdminMemberEditPage(memberID);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }//end of else

            }//end of override method for editbtn
        });//End of editBtn actionlistener


        addMemberBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterPage rp = new RegisterPage("Can2Gym");
                rp.registerUser();

            }
        });

        /*-----------------------------------------Edit Vendors Buttons-----------------------------------------------*/


        addVendorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminVendorPage avp = new AdminVendorPage();

            }
        });

        //Button to delete a vendor from the DB
        removeVendorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input = JOptionPane.showInputDialog(null, "Enter Vendor's ID");

                //Checks to make sure there is input in the textfields
                if(input.isEmpty() || input == null){
                    JOptionPane.showMessageDialog(null, "Invalid Vendor ID");

                } else {
                    int vendorID = Integer.parseInt(input); //Holds the vendor's ID to be deleted by Admin
//                    System.out.println(memberID);

                    Connection conn = null;
                    PreparedStatement ps = null;

                    try {
                        conn = DBUtil.getConnection();

                        //Deletes corresponding vendor from DB
                        String deleteVendorQuery = "DELETE FROM VENDOR WHERE VENDOR_ID = ?;";
                        ps = conn.prepareStatement(deleteVendorQuery);
                        ps.setString(1, String.valueOf(vendorID));
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Successfully Deleted");

                        //Clears the JTable and repopulates it with updated DB entries
                        clearTable(vendorJTable);
                        populateVendorTable();


                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {

                        try {
                            conn.close();
                            ps.close();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        clearTable(vendorJTable);
                        populateVendorTable();

                    }

                }//end of else

            }
        });
    }//end AdminPage()



    private void populateMemberTable() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBUtil.getConnection();
            String allMembersQuery = "SELECT * FROM MEMBER;";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(allMembersQuery);
            DefaultTableModel tableModel = (DefaultTableModel) membersJTable.getModel();
            membersJTable.setRowHeight(30);
            //String array storing the name of the columns for JTable
            String[] colName = {"ID", "FIRST NAME", "LAST NAME", "STREET", "CITY", "STATE", "ZIP", "EMAIL", "PHONE"};
            //Sets the column names using the colName array
            tableModel.setColumnIdentifiers(colName);

            //Go through the Result Set and get the string/value of each column
            while(rs.next()){
                //Getting each column data
                String id = String.valueOf(rs.getInt(1));
                String fName = rs.getString(2);
                String lName = rs.getString(3);
                String streetAddr = rs.getString(4);
                String city = rs.getString(5);
                String state = rs.getString(6);
                String zip = rs.getString(7);
                String email = rs.getString(8);
                String phone = rs.getString(9);

                //String array containing each member's info
                String[] rowData = {id, fName, lName, streetAddr, city, state, zip, email, phone};

                //Add string data to JTable
                tableModel.addRow(rowData);


            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }//end populateMemberTable()


    private void populateVendorTable() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBUtil.getConnection();
            String allMembersQuery = "SELECT * FROM VENDOR;";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(allMembersQuery);
            DefaultTableModel tableModel = (DefaultTableModel) vendorJTable.getModel();
            vendorJTable.setRowHeight(30);
            //String array storing the name of the columns for JTable
            String[] colName = {"ID", "VENDOR NAME", "PHONE", "ADDRESS"};
            //Sets the column names using the colName array
            tableModel.setColumnIdentifiers(colName);

            //Go through the Result Set and get the string/value of each column
            while(rs.next()){
                //Getting each column data
                String id = String.valueOf(rs.getInt(1));
                String vendorName = rs.getString(2);
                String vendorPhone = rs.getString(3);
                String vendorAddress = rs.getString(4);


                //String array containing each member's info
                String[] rowData = {id, vendorName, vendorPhone, vendorAddress};

                //Add string data to JTable
                tableModel.addRow(rowData);


            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }//end populateMemberTable method

    private void clearTable (JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        while(model.getRowCount() >0){
            for(int i = 0; i < model.getRowCount(); i++){
                model.removeRow(i);
            }
        }

    }





}//end class
