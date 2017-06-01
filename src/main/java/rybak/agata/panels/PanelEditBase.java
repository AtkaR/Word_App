package rybak.agata.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by asus on 2017-05-04.
 */
public class PanelEditBase extends JPanel {
    private JButton btnAddWord = new JButton("Add word");
    private JButton btnModify = new JButton("Modify word");

    private PanelWord panelWord;
    private PanelAddWord panelAddWord;

    public PanelEditBase(){
        super(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();

        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 0;
        gbcPanelOperations.gridy = 0;
        btnAddWord.addActionListener(e -> {generatePanelAddWordWindow();});
        panelOperations.add(btnAddWord, gbcPanelOperations);

        gbcPanelOperations.gridx = 0;
        gbcPanelOperations.gridy = 1;
        btnModify.addActionListener(e -> {generatePanelWordWindow();});
        panelOperations.add(btnModify, gbcPanelOperations);

        panelOperations.setBackground(new Color(220,220,220));
        setBackground(new Color(220,220,220));

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        add(panelOperations, gbcMain);

        panelAddWord = new PanelAddWord(panelWord);
        panelWord = new PanelWord(panelAddWord);
    }

    private void generatePanelAddWordWindow()
    {
        JFrame frame = new JFrame("ADD WORD");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PanelAddWord panel = new PanelAddWord(panelWord);
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

        frame.pack(); //dopasowuje rozmiar okienka do jego zawartosci

    }

    private void generatePanelWordWindow()
    {
        JFrame frame = new JFrame("WORD");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PanelWord panel = new PanelWord(panelAddWord);
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
