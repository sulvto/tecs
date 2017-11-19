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

    private final File[] inputFiles;
    private final String outputFilename;

    public VMtranslator(File input) {
        if (input.isDirectory()) {
            this.inputFiles = input.listFiles((dir, name) -> name.endsWith(".vm"));
            this.outputFilename = input.getPath() + File.separator + input.getName() + ".asm";
        } else {
            this.inputFiles = input.getParentFile().listFiles((dir, name) -> name.endsWith(".vm"));
            File parentFile = input.getParentFile();
            this.outputFilename = parentFile.getPath() + File.separator + parentFile.getName() + ".asm";
        }
    }

    public void program() throws IOException {
        Parser parser = new Parser();

        CodeWriter writer = new CodeWriter();

        writer.writeInit();

        for (File input : inputFiles) {

            writer.setCurrentFileName(input.getName().replaceAll("\\..*",""));
            parser.init(new FileInputStream(input));

            while (parser.hasMoreCommands()) {
                parser.advance();

                switch (parser.commandType()) {
                    case ARITHMETIC: writer.writeArithmetic(parser.command()); break;
                    case RETURN: writer.writeReturn(); break;
                    case IF: writer.writeIf(parser.arg1()); break;
                    case GOTO: writer.writeGoto(parser.arg1()); break;
                    case LABEL: writer.writeLabel(parser.arg1()); break;
                    case POP: writer.writePop(parser.arg1(), parser.arg2()); break;
                    case CALL: writer.writeCall(parser.arg1(),parser.arg2());break;
                    case PUSH: writer.writePush(parser.arg1(), parser.arg2()); break;
                    case FUNCTION: writer.writeFunction(parser.arg1(),parser.arg2()); break;
                    default: throw new Error("");
                }
            }
        }

        writer.setOutputFileName(outputFilename);
        writer.close();
    }

}
