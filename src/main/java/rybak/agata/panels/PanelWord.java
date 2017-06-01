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
public class PanelWord extends JPanel {
    private JButton btnLeft = new JButton("<");
    private JButton btnRight = new JButton(">");
    private JButton btnModify = new JButton("MODIFY");
    private JButton btnDelete = new JButton("DELETE");
    private JButton btnCancel = new JButton("CANCEL");

    private JLabel lPl = new JLabel("Pl");
    private JLabel lEng = new JLabel("Eng");
    private JLabel lCategory = new JLabel("Category");

    private JTextField tfId = new JTextField(5);
    private JTextField tfPl = new JTextField(15);
    private JTextField tfEng = new JTextField(15);

    private CustomComboboxModel modelCCategory;
    private JComboBox<Integer> cCategory;

    private List<Word> words;
    private int idx;

    private PanelAddWord panelAddWord;

    public PanelWord(PanelAddWord panelAddWord)
    {
        super(new GridBagLayout());
        this.panelAddWord = panelAddWord;
        GridBagConstraints gbcMain = new GridBagConstraints();

        //----------------------------PANEL NAVIGATION-----------------------------------

        JPanel panelNavigation = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelNavigation = new GridBagConstraints();

        gbcPanelNavigation.gridx = 0;
        gbcPanelNavigation.gridy = 0;
        panelNavigation.add(lCategory, gbcPanelNavigation);

        gbcPanelNavigation.gridx = 1;
        gbcPanelNavigation.gridy = 0;
        modelCCategory = new CustomComboboxModel<>(Database.getInstance().selectWordCategories());
        cCategory = new JComboBox<>(modelCCategory);
        cCategory.addActionListener(e -> {
            fillWordPanelDown();
        });
        panelNavigation.add(cCategory, gbcPanelNavigation);

        gbcPanelNavigation.gridx = 0;
        gbcPanelNavigation.gridy = 1;
        btnLeft.addActionListener(e -> {
            --idx;
            if (idx < 0)
            {
                idx = words.size() - 1;
            }
            fillFields();
        });
        panelNavigation.add(btnLeft, gbcPanelNavigation);

        gbcPanelNavigation.gridx = 1;
        gbcPanelNavigation.gridy = 1;
        panelNavigation.add(tfId, gbcPanelNavigation);

        gbcPanelNavigation.gridx = 2;
        gbcPanelNavigation.gridy = 1;
        btnRight.addActionListener(e -> {
            ++idx;
            if (idx >= words.size())
            {
                idx = 0;
            }
            fillFields();
        });
        panelNavigation.add(btnRight, gbcPanelNavigation);

        //-------------------------------PANEL FIELDS------------------------------------

        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "CURRENT WORD",
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

        //-------------------------------PANEL OPERATIONS--------------------------------

        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 0;
        gbcPanelOperations.gridy = 0;
        btnModify.addActionListener(e -> {generateModifyWordWindow();});
        panelOperations.add(btnModify, gbcPanelOperations);

        gbcPanelOperations.gridx = 1;
        gbcPanelOperations.gridy = 0;
        btnDelete.addActionListener(e -> {
            if (!tfId.getText().isEmpty())
            {
                Database.getInstance().deleteWord(Integer.parseInt(tfId.getText()));
                words = Database.getInstance().selectWord();
                if(idx > 0)
                {
                    --idx;
                }
                fillFields();
                panelAddWord.updateComboBoxes();
            }

        });
        panelOperations.add(btnDelete, gbcPanelOperations);

        gbcPanelOperations.gridx = 2;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnCancel, gbcPanelOperations);

        panelNavigation.setBackground(new Color(220,220,220));
        panelFields.setBackground(new Color(220,220,220));
        panelOperations.setBackground(new Color(220,220,220));
        setBackground(new Color(220,220,220));

        //--------------------------------PANEL MAIN-------------------------------------

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        add(panelNavigation, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        add(panelFields, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 2;
        add(panelOperations, gbcMain);

        words = Database.getInstance().selectWord();
        idx = 0;
        fillFields();

        btnCancel.addActionListener(e -> {
            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();
        });
    }

    public PanelAddWord getPanelAddWord() {
        return panelAddWord;
    }

    public void fillFields()
    {
        if (words.isEmpty() || words == null)
        {
            tfId.setText("");
            tfPl.setText("");
            tfEng.setText("");
        }
        else
        {
            Word w = words.get(idx);
            tfId.setText(String.valueOf(w.getId()));
            tfPl.setText(w.getPl());
            tfEng.setText(w.getEng());
        }
    }

    public void fillWordPanelDown()
    {
        String category = (String)cCategory.getSelectedItem();
        words = Database.getInstance().selectWordsByCategory(category);
        fillFields();
    }

    /*public Word getWordFromFields()
    {
        if (
                tfPl.getText().isEmpty() ||
                        tfEng.getText().isEmpty()
                )
        {
            JOptionPane.showMessageDialog(null, "WORD CAN'T BE MODIFY!");
            return null;
        }
        return new Word(
                Integer.parseInt(tfId.getText()),
                tfPl.getText(),
                tfEng.getText(), // no i tu lipa
        );
    }*/

    public void fillFieldsAfterAdd()
    {
        words = Database.getInstance().selectWord();
        if (!words.isEmpty() && words != null)
        {
            idx = words.size() - 1;
            fillFields();
        }
    }

    public void fillFieldsAfterModification()
    {
        words = Database.getInstance().selectWord();
        if (!words.isEmpty() && words != null)
        {
            fillFields();
        }
    }

    public Word getCurrentWord()
    {
        return words.get(idx);
    }

    private void generateModifyWordWindow()
    {
        JFrame frame = new JFrame("WORD APP");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PanelModifyWord panel = new PanelModifyWord(this);
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
}
