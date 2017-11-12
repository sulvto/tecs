import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sulvto on 17-11-11.
 */
public class Parser {
    private final List<String> input = new ArrayList<>();

    private String command;


    public void init(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while (true) {
                int read = inputStream.read();
                if (read != -1) stringBuilder.append((char) read);
                else break;
            }
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
        input.clear();
        input.addAll(Stream.of(stringBuilder.toString().split("\n"))

                .map(s -> {
                    int i = s.indexOf("//");
                    if (i != -1) {
                        return s.substring(0, i);
                    }
                    return s;
                }).map(String::trim)
                .filter(s -> s.length() > 0).collect(Collectors.toList()));
    }

    public boolean hasMoreCommands() {
        return input.size() > 0;
    }

    public void advance() {
        command = input.remove(0).toLowerCase();
        System.out.println("advance:" + command);
    }

    public CommandType commandType() {
        if (command.startsWith("return")) {
            return CommandType.RETURN;
        } else if (command.startsWith("call")) {
            return CommandType.CALL;
        } else if (command.startsWith("function")) {
            return CommandType.FUNCTION;
        } else if (command.startsWith("if-goto")) {
            return CommandType.IF;
        } else if (command.startsWith("goto")) {
            return CommandType.GOTO;
        } else if (command.startsWith("label")) {
            return CommandType.LABEL;
        } else if (command.startsWith("push")) {
            return CommandType.PUSH;
        } else if (command.startsWith("pop")) {
            return CommandType.POP;
        } else {
            return CommandType.ARITHMETIC;
        }
    }

    public String arg1() {
        return "";
    }

    public int arg2() {
        return 1;
    }

    enum CommandType {ARITHMETIC, PUSH, POP, LABEL, GOTO, IF, FUNCTION, RETURN, CALL}

}
