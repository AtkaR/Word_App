package rybak.agata.panels;

import rybak.agata.classes.Word;
import rybak.agata.database.Database;
import rybak.agata.database.DatabaseDAO;
import rybak.agata.models.CustomComboboxModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

/**
 * Created by asus on 2017-05-05.
 */
public class PanelModifyWord extends JPanel {
    private JButton btnModify = new JButton("MODIFY");
    private JButton btnCancel = new JButton("CANCEL");

    private JLabel lId = new JLabel("ID");
    private JLabel lPl = new JLabel("Pl");
    private JLabel lEng = new JLabel("Eng");
    private JLabel lCategory = new JLabel("Category");

    private JTextField tfId = new JTextField(3);
    private JTextField tfPl = new JTextField(15);
    private JTextField tfEng = new JTextField(15);
    private JTextField tfCategory = new JTextField(15);

    private PanelWord panelWord;

    public PanelModifyWord(PanelWord panelWord)
    {
        super(new GridBagLayout());
        this.panelWord = panelWord;
        GridBagConstraints gbcModify = new GridBagConstraints();

        //-------------------------------PANEL FIELDS------------------------------------

        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "MODIFY WORD",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", Font.ITALIC, 16),
                Color.BLACK
        ));

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 0;
        panelFields.add(lId, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 0;
        panelFields.add(tfId, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 1;
        panelFields.add(lPl, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 1;
        panelFields.add(tfPl, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 2;
        panelFields.add(lEng, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 2;
        panelFields.add(tfEng, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 3;
        panelFields.add(lCategory, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 3;
        panelFields.add(tfCategory, gbcPanelFields);

        //-------------------------------PANEL OPERATIONS--------------------------------

        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 1;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnModify, gbcPanelOperations);

        gbcPanelOperations.gridx = 2;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnCancel, gbcPanelOperations);

        panelFields.setBackground(new Color(220, 220, 220));
        panelOperations.setBackground(new Color(220, 220, 220));
        setBackground(new Color(220, 220, 220));

        //--------------------------------PANEL MODIFY-----------------------------------

        gbcModify.gridx = 0;
        gbcModify.gridy = 0;
        add(panelFields, gbcModify);

        gbcModify.gridx = 0;
        gbcModify.gridy = 1;
        add(panelOperations, gbcModify);

        fillFields(panelWord.getCurrentWord());

        btnModify.addActionListener(e -> {
            if (
                    tfId.getText().isEmpty() ||
                            tfPl.getText().isEmpty() ||
                            tfEng.getText().isEmpty() ||
                            tfCategory.getText().isEmpty()
                    )
            {
                JOptionPane.showMessageDialog(null, "FILL ALL FIELDS TO UPDATE CURRENT STUDENT!");
                return;
            }

            Word ps = new Word(
                    Integer.parseInt(tfId.getText()),
                    tfPl.getText(),
                    tfEng.getText(),
                    tfCategory.getText(),
                    0,
                    panelWord.getCurrentWord().getUser_id() //jak pozostawić to co było w bazie w tym polu?
            );


            Database.getInstance().updateWord(ps);
            panelWord.fillFieldsAfterModification();

            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();
        });

        btnCancel.addActionListener(e -> {
            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();
        });
    }


    public void fillFields(Word w)
    {
        if (w != null)
        {
            tfId.setText(String.valueOf(w.getId()));
            tfPl.setText(w.getPl());
            tfEng.setText(w.getEng());
            tfCategory.setText(w.getCategory());
        }
    }

}
