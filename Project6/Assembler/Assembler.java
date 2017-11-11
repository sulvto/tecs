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
        this.output = new File(input.getParentFile().getPath() + File.separator + input.getName().replaceAll("\\.\\w+", "") + ".hack");
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
            } else {
                n++;
            }
        }

        List<String> output = new ArrayList<>();

        parser.init(new FileInputStream(input));
        int nextRAM = 16;
        while (parser.hasMoreCommands()) {
            parser.advance();

            switch (parser.commandType()) {
                case C:
                    String asm = "111";
                    String comp = parser.comp();
                    asm += new String(Code.comp(comp));
                    String dest = parser.dest();
                    asm += new String(Code.dest(dest));
                    String jump = parser.jump();
                    asm += new String(Code.jump(jump));
                    output.add(asm);
                    break;
                case L:
                    break;
                case A:
                    asm = "0";
                    String symbol = parser.symbol();
                    if (symbol.matches("\\d+")) {
                        asm += toBinaryString(Integer.valueOf(symbol), 15);
                    } else if (symbol.matches("\\w+")) {
                        if (symbolTable.contains(symbol)) {
                            asm += toBinaryString(symbolTable.getAddress(symbol), 15);
                        } else {
                            int addr = nextRAM++;
                            symbolTable.addEntry(symbol, addr);
                            asm += toBinaryString(addr, 15);
                        }
                    } else {
                        throw new Error("Unknown symbol:" + symbol);
                    }
                    output.add(asm);
                    break;
                default:
                    throw new Error("Unknown command type:" + parser.commandType());
            }

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

    private String toBinaryString(int input, int bit) {
        StringBuilder result = new StringBuilder(Integer.toBinaryString(input));
        while (result.length() < bit) {
            result.insert(0, "0");
        }
        return result.toString();
    }
}
