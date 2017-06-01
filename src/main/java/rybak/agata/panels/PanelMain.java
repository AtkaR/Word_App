package rybak.agata.panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by asus on 2017-05-04.
 */
public class PanelMain extends JPanel {
    private JButton btnLearning = new JButton("Learning");
    private JButton btnRepeat = new JButton("Repeat");
    private JButton btnEditBase = new JButton("Edit base");

    public PanelMain(){
        super(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();

        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 0;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnLearning, gbcPanelOperations);

        gbcPanelOperations.gridx = 0;
        gbcPanelOperations.gridy = 1;
        panelOperations.add(btnRepeat, gbcPanelOperations);

        gbcPanelOperations.gridx = 0;
        gbcPanelOperations.gridy = 2;
        btnEditBase.addActionListener(e -> {generateEditBaseWindow();});
        panelOperations.add(btnEditBase, gbcPanelOperations);

        panelOperations.setBackground(new Color(220,220,220));
        setBackground(new Color(220,220,220));

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        add(panelOperations, gbcMain);
    }

    private void generateEditBaseWindow()
    {
        JFrame frame = new JFrame("EDIT BASE");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PanelEditBase panel = new PanelEditBase();
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
}
