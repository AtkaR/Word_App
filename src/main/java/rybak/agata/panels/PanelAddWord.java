package rybak.agata.panels;

import rybak.agata.classes.Word;
import rybak.agata.database.Database;
import rybak.agata.models.CustomComboboxModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by asus on 2017-05-04.
 */
public class PanelAddWord extends JPanel {
    private JButton btnAdd = new JButton("ADD");
    private JButton btnCancel = new JButton("CANCEL");

    private JLabel lPl= new JLabel("PL: ");
    private JLabel lEng = new JLabel("ENG: ");
    private JLabel lCategory = new JLabel("Category: ");
    private JLabel lNewCategory = new JLabel("New category: ");
    private JTextField tfPl = new JTextField(15);
    private JTextField tfEng = new JTextField(15);
    private JTextField tfNewCategory = new JTextField(15);

    private JCheckBox cCat = new JCheckBox();

    private CustomComboboxModel<String> modelCCategory;
    private JComboBox cCategory;

    private PanelWord pw;

    public PanelAddWord(PanelWord pw) {
        super(new GridBagLayout());
        this.pw = pw;
        GridBagConstraints gbcAdd = new GridBagConstraints();

        //-------------------------------PANEL FIELDS------------------------------------

        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "ADD WORD",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", Font.ITALIC, 16),
                Color.BLACK
        ));

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 0;
        panelFields.add(lPl, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 0;
        panelFields.add(tfPl, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 1;
        panelFields.add(lEng, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 1;
        panelFields.add(tfEng, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 2;
        panelFields.add(lCategory, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 2;
        modelCCategory = new CustomComboboxModel<>(Database.getInstance().selectWordCategories());
        cCategory =new JComboBox<>(modelCCategory);
        panelFields.add(cCategory, gbcPanelFields);

        gbcPanelFields.gridx = 2;
        gbcPanelFields.gridy = 2;
        cCat.addActionListener(e ->
        {
            if (cCat.isSelected())
            {
                cCategory.setEnabled(false);
                tfNewCategory.setVisible(true);
            }
            else
            {
                cCategory.setEnabled(true);
                tfNewCategory.setVisible(false);
            }
        });
        panelFields.add(cCat, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 3;
        panelFields.add(lNewCategory, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 3;
        panelFields.add(tfNewCategory, gbcPanelFields);

        //-------------------------------PANEL OPERATIONS------------------------------------

        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 1;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnAdd, gbcPanelOperations);

        gbcPanelOperations.gridx = 2;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnCancel, gbcPanelOperations);

        panelFields.setBackground(new Color(220, 220, 220));
        panelOperations.setBackground(new Color(220, 220, 220));
        setBackground(new Color(220, 220, 220));

        //--------------------------------PANEL ADD-----------------------------------

        gbcAdd.gridx = 0;
        gbcAdd.gridy = 0;
        add(panelFields, gbcAdd);

        gbcAdd.gridx = 0;
        gbcAdd.gridy = 1;
        add(panelOperations, gbcAdd);

        btnAdd.addActionListener(e -> {
            if (
                    tfPl.getText().isEmpty() ||
                            tfEng.getText().isEmpty()  // no i lipton bo chce z comboboxa
                    )
            {
                JOptionPane.showMessageDialog(null, "FILL ALL FIELDS TO INSERT NEW STUDENT!");
                return;
            }

            Word w = new Word(
                    0,
                    tfPl.getText(),
                    tfEng.getText(),
                    cCategory.getSelectedItem().toString(),
                    0,
                    pw.getCurrentWord().getUser_id() //jak wstawic tu id obecnego usera?

            );
            Database.getInstance().insertWord(w);
            pw.fillFieldsAfterAdd();
            pw.getPanelAddWord().updateComboBoxes();

            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();

        });

        btnCancel.addActionListener(e -> {
            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();
        });
    }

    public void updateComboBoxes() {
        modelCCategory.update(Database.getInstance().selectWordCategories());
        cCategory.updateUI();
    }
}
