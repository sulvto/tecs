import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sulvto on 17-11-9.
 */
public class Parser {
    private final InputStream inputStream;

    private String command;


    public Parser(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public boolean hasMoreCommands() {
        try {
            return inputStream.available() > 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void advance() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while (true) {
                char read = (char) inputStream.read();
                if (read == '\n') {
                    break;
                }
                stringBuilder.append(read);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int i = stringBuilder.indexOf("//");
        if (i == -1) {
            command = stringBuilder.toString();
        } else {
            command = stringBuilder.substring(0, i);
        }

    }

    public CommandType commandType() {
        if (command.startsWith("@")) {
            return CommandType.A;
        } else if (command.contains("=") || command.contains(";")) {
            return CommandType.C;
        } else if (command.startsWith("(")) {
            return CommandType.L;
        } else {
            throw new Error("error command");
        }
    }

    public void symbol() {

    }

    public void dest(String code) {

    }

    public void comp(String code) {

    }

    public void jump(String code) {

    }


    enum CommandType {A, C, L,}

}