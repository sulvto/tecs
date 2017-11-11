import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sulvto on 17-11-9.
 */
public class Assembler {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            return;
        }
        String file = args[0];

        new Assembler(new File(file)).program();
    }

    public Assembler(File input) {
        this.input = input;
        this.output = new File(input.getParentFile().getPath() + File.separator + input.getName().replaceAll("\\.\\w+","")+ ".hack");
        symbolTable = new SymbolTable();
    }

    public void program() throws IOException {
        Parser parser = new Parser();

        parser.init(new FileInputStream(input));

        // two pass

        int n = 0;
        while (parser.hasMoreCommands()) {
            parser.advance();
            if (parser.commandType() == Parser.CommandType.L) {
                symbolTable.addEntry(parser.symbol(), n);
            }
            n++;
        }

        List<String> output = new ArrayList<>();

        parser.init(new FileInputStream(input));
        n = 0;
        while (parser.hasMoreCommands()) {
            parser.advance();

            switch (parser.commandType()) {
                case C:
                    String asm = "111";
                    asm += new String(Code.dest(parser.dest()));
                    asm += new String(Code.comp(parser.comp()));
                    asm += new String(Code.jump(parser.jump()));
                    output.add(asm);
                    break;
                case L:
                    break;
                case A:
                    asm = "0";
                    String symbol = parser.symbol();
                    if (symbol.matches("\\w+")) {
                        if (symbolTable.contains(symbol)) {
                            asm += toHexString(symbolTable.getAddress(symbol), 15);
                        } else {
                            symbolTable.addEntry(symbol, n);
                            asm += toHexString(n, 15);
                        }
                    } else if (symbol.matches("\\d+")) {
                        asm += toHexString(Integer.valueOf(symbol), 15);
                    } else {
                        throw new Error("Unknown symbol");
                    }
                    output.add(asm);
                    break;
                default:
                    throw new Error("Unknown command type:" + parser.commandType());
            }
            n++;
        }


        FileWriter fileWriter = new FileWriter(this.output);

        String write = output.stream().reduce((s, s2) -> s + "\n" + s2).get();
        fileWriter.write(write);
        fileWriter.flush();
        fileWriter.close();
    }

    private final File input;
    private final File output;
    private final SymbolTable symbolTable;

    private String toHexString(int input, int bit) {
        StringBuilder result = new StringBuilder(Integer.toHexString(input));
        while (result.length() < bit) {
            result.insert(0, "0");
        }
        return result.toString();
    }
}
