package Tampilan;

import Vlogin.FLogin;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

public class Splash extends JFrame{
    private JPanel mainPanel1;

    public Splash() {
        setContentPane(mainPanel1);
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        Preferences pref = Preferences.userRoot().node(Main.class.getName());
        String userID = pref.get("USER_ID","");
        System.out.println(userID);

        try {
            TimeUnit.SECONDS.sleep(2);
            if (userID.equals("")){
                setVisible(false);
                FLogin lf = new FLogin();
                lf.setVisible(true);
                lf.setSize(320, 140);
            } else {
                setVisible(false);
                FLogin mf = new FLogin();
                mf.setVisible(true);
                    mf.setSize(320, 120);
            }
        } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }