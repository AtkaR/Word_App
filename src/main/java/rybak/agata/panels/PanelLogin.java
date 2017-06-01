package rybak.agata.panels;

import rybak.agata.classes.User;
import rybak.agata.database.Database;
import rybak.agata.encryption.Encryption;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Optional;

/**
 * Created by asus on 2017-05-04.
 */
public class PanelLogin extends JPanel {
    private JButton btnLogin = new JButton("LOGIN");
    private JButton btnRegister = new JButton("REGISTER");
    private JLabel lUsername = new JLabel("Username: ");
    private JLabel lPassword = new JLabel("Password: ");
    private JTextField tfUsername = new JTextField(10);
    private JPasswordField pfPassword = new JPasswordField(10);

    public PanelLogin() {
        super(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();

        //-------------------------------PANEL FIELDS------------------------------------

        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "SIGN IN",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", Font.ITALIC, 16),
                Color.BLACK
        ));

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 0;
        panelFields.add(lUsername, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 0;
        panelFields.add(tfUsername, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 1;
        panelFields.add(lPassword, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 1;
        panelFields.add(pfPassword, gbcPanelFields);

        //-------------------------------PANEL OPERATIONS--------------------------------

        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 0;
        gbcPanelOperations.gridy = 0;
        btnLogin.addActionListener(e -> {
            Optional<User> oUser = Database.getInstance().getUserByUsernameAndPassword(tfUsername.getText(), Encryption.encrypt(String.valueOf(pfPassword.getPassword())));
            if (oUser.isPresent())
            {
                generateMainWindow();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "WRONG PASSWORD!");
            }
        });
        panelOperations.add(btnLogin, gbcPanelOperations);

        gbcPanelOperations.gridx = 1;
        gbcPanelOperations.gridy = 0;
        btnRegister.addActionListener(e -> {generateUserRegistrationWindow();});
        panelOperations.add(btnRegister, gbcPanelOperations);

        panelFields.setBackground(new Color(220,220,220));
        panelOperations.setBackground(new Color(220,220,220));
        setBackground(new Color(220,220,220));

        //--------------------------------PANEL MAIN-------------------------------------

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        add(panelFields, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        add(panelOperations, gbcMain);
    }
    private void generateUserRegistrationWindow()
    {
        JFrame frame = new JFrame("APP");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PanelUserRegistration panel = new PanelUserRegistration();
        panel.setVisible(true);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setLocation(590, 270);
        frame.setResizable(false);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        frame.pack();

    }

    private void generateMainWindow()
    {
        JFrame frame = new JFrame("UNIVERSITY APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PanelMain panel = new PanelMain();
        panel.setVisible(true);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setLocation(400, 100);
        frame.setResizable(false);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        frame.pack();
    }

}
