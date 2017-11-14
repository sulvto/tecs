import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by sulvto on 17-11-11.
 */
public class VMtranslator {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            return;
        }
        String file = args[0];

        new VMtranslator(new File(file)).program();
    }

    private final File input;
    private final String outputFilename;

    public VMtranslator(File input) {
        this.input = input;
        this.outputFilename = input.getParentFile().getPath() + File.separator + input.getName().replaceAll("\\.\\w+", "") + ".asm";
    }

    public void program() throws IOException {
        Parser parser = new Parser();

        parser.init(new FileInputStream(input));

        CodeWriter writer = new CodeWriter();
        writer.setFileName(outputFilename);

        while (parser.hasMoreCommands()) {
            parser.advance();

            switch (parser.commandType()) {
                case ARITHMETIC: writer.writeArithmetic(parser.command()); break;
                case POP: writer.writePop(parser.arg1(),parser.arg2()); break;
                case PUSH: writer.writePush(parser.arg1(),parser.arg2()); break;
            }
        }

        writer.close();
    }

}
