package rybak.agata;

import rybak.agata.classes.User;
import rybak.agata.classes.Word;
import rybak.agata.database.Database;
import rybak.agata.panels.PanelLogin;

import javax.swing.*;

public class App
{
    public static void createWindow()
    {
        JFrame frame = new JFrame("WORD APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PanelLogin panel = new PanelLogin();
        panel.setVisible(true);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setLocation(400, 100);
        frame.setResizable(false);
        //frame.setJMenuBar(panel.createMenu());

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

    public static void main( String[] args )
    {
        Database db = Database.getInstance();
        db.createTable();

        //db.insertUser(new User(0, "Agata", "Rybak", 22, "aga@rybka.com", "Atka", "123", "admin"));
        //db.insertUser(new User(0, "Krzysztof", "Makowski", 18, "krzysiu@maku.com", "Kris", "qwerty", "user"));

    	/*db.insertWord(new Word(0, "kot", "cat", "animals", 0, 1));
        db.insertWord(new Word(0, "pies", "dog", "animals", 0, 2));
        db.insertWord(new Word(0, "lew", "lion", "animals", 0, 1));
        db.insertWord(new Word(0, "jeden", "one", "numbers", 0, 2));
    	db.insertWord(new Word(0, "dwa", "two", "numbers", 0, 1));
    	db.insertWord(new Word(0, "czerwony", "red", "colors", 0, 2));
    	db.insertWord(new Word(0, "niebieski", "blue", "colors", 0, 1));*/

        db.selectWord().forEach(System.out::println);
        db.selectUser().forEach(System.out::println);

        System.out.println(db.selectWordById(1));
        System.out.println(db.selectUserById(1));

        db.selectWordsByCategory("numbers").forEach(System.out::println);

        javax.swing.SwingUtilities.invokeLater(
                () -> createWindow()
        );
    }
}
