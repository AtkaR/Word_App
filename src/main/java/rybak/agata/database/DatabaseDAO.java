package rybak.agata.database;

import rybak.agata.classes.User;
import rybak.agata.classes.Word;

import java.util.List;

/**
 * Created by asus on 2017-05-04.
 */
public interface DatabaseDAO {

    void insertWord(Word w);
    void insertUser(User u);
    void updateWord(Word w);
    void updateUser(User u);
    void deleteWord(int id);
    void deleteUser(int id);
    List<Word> selectWord();
    Word selectWordById(int idd);
    List<User> selectUser();
    User selectUserById(int idd);
    List<Word> selectWordsByCategory(String categoryy);
}
