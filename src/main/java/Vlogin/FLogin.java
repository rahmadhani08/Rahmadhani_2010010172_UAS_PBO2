package Vlogin;

import Fdata.Fdata;
import Fdata.IFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;

import java.util.prefs.Preferences;

public class FLogin extends JFrame {
    private JPanel mainPanel;
    private JLabel Username;
    private JLabel Password;
    private JButton keluarButton;
    private JButton loginButton;
    private JTextField textField1;
    private JTextField textField2;

    public FLogin() {
        setContentPane(mainPanel);
        loginButton.addActionListener(e -> {
            if (textField1.getText().equals("admin")) {
                if (new String(textField2.getText()).equals("admin")) {
                    Preferences pref = Preferences.userRoot().node(Main.class.getName());
                    pref.put("USER_ID", "1");
                    Fdata mf = new Fdata();
                    mf.setVisible(true);
                    dispose();
                }
            }
        });

    }
}
