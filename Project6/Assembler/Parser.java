import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sulvto on 17-11-9.
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
                }) .map(String::trim)
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
        if (command.startsWith("@")) {
            return CommandType.A;
        } else if (command.startsWith("(")) {
            return CommandType.L;
        } else {
            return CommandType.C;
        }
    }

    public String symbol() {
        if (command.startsWith("@")) {
            return command.substring(1);
        }
        if (command.startsWith("(")) {
            return command.substring(1, command.length() - 1);
        } else {
            throw new Error("dest");
        }
    }

    public String dest() {
        if (command.contains("=")) {
            return command.split("=")[0];
        } else {
            return "null";
        }
    }

    public String comp() {
        if (command.contains("=")) {
            return command.split("=")[1];
        } else if (command.contains(";")) {
            return command.split(";")[0];
        } else {
            throw new Error("comp");
        }
    }

    public String jump() {
        if (command.contains(";")) {
            return command.split(";")[1];
        } else {
            return "null";
        }
    }


    enum CommandType {A, C, L,}

}