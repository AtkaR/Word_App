package rybak.agata.classes;

/**
 * Created by asus on 2017-05-04.
 */
public class Word {
    private int id;
    private String pl;
    private String eng;
    private String category;
    private int result;
    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Word() {

    }

    public Word(int id, String pl, String eng, String category, int result, int user_id) {
        super();
        this.id = id;
        this.pl = pl;
        this.eng = eng;
        this.category = category;
        this.result = result;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", pl='" + pl + '\'' +
                ", eng='" + eng + '\'' +
                ", category='" + category + '\'' +
                ", result=" + result +
                ", user_id=" + user_id +
                '}';
    }
}