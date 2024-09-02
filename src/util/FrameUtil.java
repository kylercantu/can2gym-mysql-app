package util;

import javax.swing.*;

public class FrameUtil{
    private static final int FRAME_WIDTH = 1500;
    private static final int FRAME_HEIGHT = 800;

    private static JFrame frame = new JFrame();
    public static void setFrame(JPanel panel, String title){
        frame.setTitle(title);
        frame.setContentPane(panel);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }//end setFrame()

    public static void disposeFrame(){
        frame.dispose();
    }//end disposeFrame()


}//end class
