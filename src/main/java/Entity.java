import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entity {
    public static final String DATE_PATTERN = "dd-MM-yyyy HH:mm:ss";
    private Date date;
    private String value;
    private String[] words;

    public Entity(String value) throws ParseException {
        this.value = value;
        this.words = value.substring(DATE_PATTERN.length() + 1).split(" ");
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        date = formatter.parse(value.substring(0, DATE_PATTERN.length() - 1));
    }

    public Date getDate() {
        return date;
    }

    public String getValue() {
        return value;
    }

    public int getLength() {
        return words.length;
    }

    public String[] getWords() {
        return words;
    }

    public String getWord(int changedWordPlace) {
        return words[changedWordPlace];
    }
}
