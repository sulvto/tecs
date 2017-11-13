import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sulvto on 17-11-12.
 */
public class CodeWriter {
    private List<String> output = new ArrayList<>();
    private int jumpFlag = 0;
    private File outputFile;

    public void setFileName(String filename) {
        outputFile = new File(filename);
    }

    public void writeArithmetic(String command) {
        switch (command) {
            case "add": add(); break;
            case "sub": sub(); break;
            case "neg": neg(); break;
            case "and": and(); break;
            case "or":  or();  break;
            case "not": not(); break;
            case "eq":  eq();  break;
            case "lt":  lt();  break;
            case "gt":  gt();  break;
        }
    }

    private void neg() {
        write("@SP");
        write("A=M-1");
        write("M=-M");
    }

    public void sub() {
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("M=M-D");
    }

    public void add() {
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("M=M+D");
    }

    public void and() {
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("M=M&D");
    }

    public void or() {
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("M=M|D");
    }

    public void not() {
        write("@SP");
        write("A=M-1");
        write("M=!M");
    }

    public void eq() {
        int flag = jumpFlag++;
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("D=M-D");
        write("@TRUE" + flag);
        write("D;JEQ");

        write("@SP");
        write("A=M");
        write("M=0");
        write("@CONTINUE" + flag);
        write("0;JMP");

        write("(TRUE" + flag + ")");
        write("@SP");
        write("A=M");
        write("M=1");
        write("(CONTINUE" + flag + ")");
    }


    public void lt() {
        int flag = jumpFlag++;
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("D=M-D");
        write("@TRUE" + flag);
        write("D;JLT");

        write("@SP");
        write("A=M");
        write("M=0");
        write("@CONTINUE" + flag);
        write("0;JMP");

        write("(TRUE" + flag + ")");
        write("@SP");
        write("A=M");
        write("M=1");
        write("(CONTINUE" + flag + ")");
    }

    public void gt() {
        int flag = jumpFlag++;
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("D=M-D");
        write("@TRUE" + flag);
        write("D;JGT");

        write("@SP");
        write("A=M");
        write("M=0");
        write("@CONTINUE" + flag);
        write("0;JMP");

        write("(TRUE" + flag + ")");
        write("@SP");
        write("A=M");
        write("M=1");
        write("(CONTINUE" + flag + ")");
    }

    public void writePush(String command, String segment, int index) {
        write("@" + index);
        write("D=A");
        write("@SP");
        write("A=M");
        write("M=D");
        write("@SP");
        write("M=M+1");
    }

    public void writePop(String command, String segment, int index) {

    }

    public void write(String command) {
        output.add(command);
    }

    public void close() {
        if (outputFile != null) {
            try {
                FileWriter fileWriter = new FileWriter(outputFile);
                fileWriter.write(output.stream().reduce((s, s2) -> s + "\n" + s2).get());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
