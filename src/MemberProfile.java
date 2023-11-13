import util.FrameUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberProfile{
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


    MemberProfile() {
        FrameUtil.setFrame(mainPanel);

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


    private void populateAccountInfo(ResultSet rs, Statement stmt){

    }//End getAccountInfo
}//End class
