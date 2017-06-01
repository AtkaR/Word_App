package rybak.agata.panels;

import rybak.agata.classes.User;
import rybak.agata.database.Database;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by asus on 2017-05-04.
 */
public class PanelUserRegistration extends JPanel {
    private JButton btnRegister = new JButton("REGISTER");
    private JButton btnCancel = new JButton("CANCEL");

    private JLabel lName = new JLabel("Name");
    private JLabel lSurname = new JLabel("Surname");
    private JLabel lAge = new JLabel("Age");
    private JLabel lMail = new JLabel("Mail");
    private JLabel lUsername = new JLabel("Username");
    private JLabel lPassword = new JLabel("Password");

    private JTextField tfName = new JTextField(14);
    private JTextField tfSurname= new JTextField(14);
    private JTextField tfAge = new JTextField(14);
    private JTextField tfMail = new JTextField(14);
    private JTextField tfUsername = new JTextField(14);
    private JPasswordField pfPassword = new JPasswordField(14);


    public PanelUserRegistration()
    {
        super(new GridBagLayout());
        GridBagConstraints gbcUserRegister = new GridBagConstraints();

        //-------------------------------PANEL FIELDS------------------------------------

        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "Sign up",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", Font.ITALIC, 16),
                Color.BLACK
        ));

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 0;
        panelFields.add(lName, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 0;
        panelFields.add(tfName, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 1;
        panelFields.add(lSurname, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 1;
        panelFields.add(tfSurname, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 2;
        panelFields.add(lAge, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 2;
        panelFields.add(tfAge, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 3;
        panelFields.add(lMail, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 3;
        panelFields.add(tfMail, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 4;
        panelFields.add(lUsername, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 4;
        panelFields.add(tfUsername, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 5;
        panelFields.add(lPassword, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 5;
        panelFields.add(pfPassword, gbcPanelFields);


        //-------------------------------PANEL OPERATIONS------------------------------------

        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 1;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnRegister, gbcPanelOperations);

        gbcPanelOperations.gridx = 2;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnCancel, gbcPanelOperations);

        panelFields.setBackground(new Color(220,220,220));
        panelOperations.setBackground(new Color(220,220,220));
        setBackground(new Color(220,220,220));


        //--------------------------------PANEL USER REGISTER-----------------------------------

        gbcUserRegister.gridx = 0;
        gbcUserRegister.gridy = 0;
        add(panelFields, gbcUserRegister);

        gbcUserRegister.gridx = 0;
        gbcUserRegister.gridy = 1;
        add(panelOperations, gbcUserRegister);

        btnRegister.addActionListener(e -> {
            if (
                    tfName.getText().isEmpty() ||
                            tfSurname.getText().isEmpty() ||
                            tfAge.getText().isEmpty() ||
                            tfMail.getText().isEmpty() ||
                            tfUsername.getText().isEmpty() ||
                            pfPassword.getText().isEmpty()
                    )
            {
                JOptionPane.showMessageDialog(null, "FILL ALL FIELDS TO REGISTER!");
                return;
            }

            User u = new User(
                    0,
                    tfName.getText(),
                    tfSurname.getText(),
                    Integer.parseInt(tfAge.getText()),
                    tfMail.getText(),
                    tfUsername.getText(),
                    String.valueOf(pfPassword.getPassword()),
                    "user" //z automatu na poczatku jest sie userem
            );
            Database.getInstance().insertUser(u);

            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();

        });

        btnCancel.addActionListener(e -> {
            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();
        });

    }
}
