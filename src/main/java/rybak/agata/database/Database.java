package rybak.agata.database;

import org.sqlite.SQLiteConfig;
import rybak.agata.classes.User;
import rybak.agata.classes.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by asus on 2017-05-04.
 */
public class Database implements DatabaseDAO {
    private static Database database = null;
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DATABASE = "jdbc:sqlite:Test.db";
    private Connection connection;
    private Statement statement;

    private Database()
    {
        try {
            Class.forName(DRIVER);
            SQLiteConfig configuration = new SQLiteConfig();
            configuration.enforceForeignKeys(true);
            connection = DriverManager.getConnection(DATABASE, configuration.toProperties());
            statement = connection.createStatement();
            createTable();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Database getInstance()
    {
        if (database == null)
        {
            database = new Database();
        }
        return database;
    }


    public void createTable()
    {
        try {
            String sqlCreateTableUser =
                    "CREATE TABLE IF NOT EXISTS User "
                            + "("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "name VARCHAR(50) NOT NULL, "
                            + "surname VARCHAR(50) NOT NULL, "
                            + "age INTEGER NOT NULL, "
                            + "mail VARCHAR(50) NOT NULL, "
                            + "username VARCHAR(50) NOT NULL, "
                            + "password VARCHAR(50) NOT NULL, "
                            + "role VARCHAR(50) NOT NULL"
                            + ")";

            String sqlCreateTableWord =
                    "CREATE TABLE IF NOT EXISTS Word "
                            + "("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "pl VARCHAR(50) NOT NULL, "
                            + "eng VARCHAR(50) NOT NULL, "
                            + "category VARCHAR(50) NOT NULL, "
                            + "result INTEGER NOT NULL, "
                            + "user_id INTEGER NOT NULL,"
                            + "FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE "
                            + ")";

            statement.execute(sqlCreateTableUser);
            statement.execute(sqlCreateTableWord);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertWord(Word w)
    {
        try {
            String sqlInsertWord =
                    "INSERT INTO Word (pl, eng, category, result, user_id) "
                            + "VALUES "
                            + "(?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sqlInsertWord);
            ps.setString(1, w.getPl());
            ps.setString(2, w.getEng());
            ps.setString(3, w.getCategory());
            ps.setInt(4, w.getResult());
            ps.setInt(5, w.getUser_id());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertUser(User u)
    {
        try {
            String sqlInsertUser =
                    "INSERT INTO User (name, surname, age, mail, username, password, role) "
                            + "VALUES "
                            + "(?,?,?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sqlInsertUser);
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setInt(3, u.getAge());
            ps.setString(4, u.getMail());
            ps.setString(5, u.getUsername());
            ps.setString(6, u.getPassword());
            ps.setString(7, u.getRole());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateWord(Word w)
    {
        try {
            String sqlUpdateWord =
                    "UPDATE Word SET pl=?, eng=?, category=?, result=?, user_id=? "
                            + "WHERE id = ?; ";
            PreparedStatement ps = connection.prepareStatement(sqlUpdateWord);
            ps.setString(1, w.getPl());
            ps.setString(2, w.getEng());
            ps.setString(3, w.getCategory());
            ps.setInt(4, w.getResult());
            ps.setInt(5, w.getUser_id());
            ps.setInt(6,w.getId());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateUser(User u)
    {
        try {
            String sqlUpdateUser =
                    "UPDATE User SET name=?, surname=?, age=?, mail=?, username=?, password=?, role=? "
                            + "WHERE id = ? ";
            PreparedStatement ps = connection.prepareStatement(sqlUpdateUser);
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setInt(3, u.getAge());
            ps.setString(4, u.getMail());
            ps.setString(5, u.getUsername());
            ps.setString(6, u.getPassword());
            ps.setString(7, u.getRole());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteWord(int id)
    {
        try {
            String deleteWordSql = "DELETE FROM Word WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteWordSql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteUser(int id)
    {
        try {
            String deleteUserSql = "DELETE FROM User WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteUserSql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Word> selectWord()
    {
        List<Word> words = null;

        try {
            String selectWordsSql = "SELECT * FROM Word";
            ResultSet rs = statement.executeQuery(selectWordsSql);
            int id, result, user_id;
            String pl, eng, category;
            words = new ArrayList<>();
            while(rs.next())
            {
                id = rs.getInt(1);
                pl = rs.getString(2);
                eng = rs.getString(3);
                category = rs.getString(4);
                result = rs.getInt(5);
                user_id = rs.getInt(6);
                words.add(new Word(id, pl, eng, category, result, user_id));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return words;
    }

    public Word selectWordById(int idd)
    {
        Word word = null;

        try {
            String selectWordsSql = "SELECT * FROM Word WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(selectWordsSql);
            ps.setInt(1, idd);
            ResultSet rs = ps.executeQuery();
            int id, result, user_id;
            String pl, eng, category;
            if(rs.next())
            {
                id = rs.getInt(1);
                pl = rs.getString(2);
                eng = rs.getString(3);
                category = rs.getString(4);
                result = rs.getInt(5);
                user_id = rs.getInt(6);
                word = new Word(id, pl, eng, category, result, user_id);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return word;
    }

    public Word selectWordByCategory(String categoryy)
    {
        Word word = null;

        try {
            String selectWordsSql = "SELECT * FROM Word WHERE category = ?";
            PreparedStatement ps = connection.prepareStatement(selectWordsSql);
            ps.setString(1, categoryy);
            ResultSet rs = ps.executeQuery();
            int id, result, user_id;
            String pl, eng, category;
            if(rs.next())
            {
                id = rs.getInt(1);
                pl = rs.getString(2);
                eng = rs.getString(3);
                category = rs.getString(4);
                result = rs.getInt(5);
                user_id = rs.getInt(6);
                word = new Word(id, pl, eng, category, result, user_id);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return word;
    }

    public List<String> selectWordCategories()
    {
        List<String> wordList = null;

        try {
            String selectStringSql = "SELECT DISTINCT category FROM Word";
            ResultSet rs = statement.executeQuery(selectStringSql);
            String category;
            wordList= new ArrayList<>();
            while(rs.next())
            {
                category = rs.getString(1);
                wordList.add(category);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return wordList;
    }

    public List<Word> selectWordsByCategory(String categoryy)
    {
        List<Word> words = null;

        try {
            String selectWordsSql = "SELECT * FROM Word WHERE category =?";
            PreparedStatement ps = connection.prepareStatement(selectWordsSql);
            ps.setString(1, categoryy);
            ResultSet rs = ps.executeQuery();
            int id, result, user_id;
            String pl, eng, category;
            words = new ArrayList<>();
            while(rs.next())
            {
                id = rs.getInt(1);
                pl = rs.getString(2);
                eng = rs.getString(3);
                category = rs.getString(4);
                result = rs.getInt(5);
                user_id = rs.getInt(6);
                words.add(new Word(id, pl, eng, category, result, user_id));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return words;
    }

    public List<User> selectUser()
    {
        List<User> users = null;

        try {
            String selectUsersSql = "SELECT * FROM User";
            ResultSet rs = statement.executeQuery(selectUsersSql);
            int id, age;
            String name, surname, mail, username, password, role;
            users = new ArrayList<>();
            while(rs.next())
            {
                id = rs.getInt(1);
                name = rs.getString(2);
                surname = rs.getString(3);
                age = rs.getInt(4);
                mail = rs.getString(5);
                username = rs.getString(6);
                password = rs.getString(7);
                role = rs.getString(8);
                users.add(new User(id, name, surname, age, mail, username, password, role));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return users;
    }

    public User selectUserById(int idd)
    {
        User user = null;

        try {
            String selectUserSql = "SELECT * FROM User WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(selectUserSql);
            ps.setInt(1, idd);
            ResultSet rs = ps.executeQuery();
            int id, age;
            String name, surname, mail, username, password, role;
            if(rs.next())
            {
                id = rs.getInt(1);
                name = rs.getString(2);
                surname = rs.getString(3);
                age = rs.getInt(4);
                mail = rs.getString(5);
                username = rs.getString(6);
                password = rs.getString(7);
                role = rs.getString(8);
                user = new User(id, name, surname, age, mail, username, password, role);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }

    public Optional<User> getUserByUsernameAndPassword(String username, String password)
    {
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return Optional.of(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return Optional.ofNullable(null);
    }
}
