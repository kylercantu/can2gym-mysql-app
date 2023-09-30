import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    RegisterPage (){
        setFrame();
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginPage lp = new LoginPage();
            }
        });
    }

    private void setFrame(){
        setTitle("KC Gym");
        setContentPane(mainPanel);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
