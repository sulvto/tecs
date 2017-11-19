import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by sulvto on 17-11-19.
 */
public class JackTokenizer {
    private final InputStream input;

    public JackTokenizer(InputStream input) {
        this.input = input;
    }
    public JackTokenizer(File input) {
        try {
            this.input = new FileInputStream(input);
        } catch (FileNotFoundException e) {
            throw new Error(e.getMessage());
        }
    }

    public boolean hasMoreTokens() {
        return false;
    }

    public void advance() {

    }

    public String tokenType() {

    }

    public String keyword() {

    }

    public String symbol() {

    }

    public String identifier() {

    }

    public String intVal() {

    }

    public String stringVal() {

    }


}
