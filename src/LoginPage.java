import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame{
    private JPanel mainPanel;
    private JTextField usernameTF;
    private JLabel usernameLbl;
    private JLabel loginTitleLbl;
    private JLabel passwordLbl;
    private JTextField passwordTF;
    private JButton loginBtn;
    private JButton exitBtn;
    private JButton registerBtn;

    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 300;
    LoginPage(){
        setFrame();
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        registerBtn.addActionListener(new ActionListener() {
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
    }

    private void setFrame(){
        setTitle("KC Gym");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
