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
            case "add":
                add();
                break;
            case "sub":
                sub();
                break;
            case "neg":
                neg();
                break;
            case "and":
                and();
                break;
            case "or":
                or();
                break;
            case "not":
                not();
                break;
            case "eq":
                eq();
                break;
            case "lt":
                lt();
                break;
            case "gt":
                gt();
                break;
        }
    }

    private void neg() {
        write("@SP");
        write("A=M-1");
        write("D=0");
        write("M=D-M");
    }

    private void sub() {
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("M=M-D");
    }

    private void add() {
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("M=M+D");
    }

    private void and() {
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("M=M&D");
    }

    private void or() {
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

    private void eq() {
        int flag = jumpFlag++;
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("D=M-D");
        write("@FALSE" + flag);
        write("D;JEQ");

        write("@SP");
        write("A=M-1");
        write("M=0");
        write("@CONTINUE" + flag);
        write("0;JMP");

        write("(FALSE" + flag + ")");
        write("@SP");
        write("A=M-1");
        write("M=-1");
        write("(CONTINUE" + flag + ")");
    }


    private void lt() {
        int flag = jumpFlag++;
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("D=M-D");
        write("@FALSE" + flag);
        write("D;JLT");

        write("@SP");
        write("A=M-1");
        write("M=0");
        write("@CONTINUE" + flag);
        write("0;JMP");

        write("(FALSE" + flag + ")");
        write("@SP");
        write("A=M-1");
        write("M=-1");
        write("(CONTINUE" + flag + ")");
    }

    private void gt() {
        int flag = jumpFlag++;
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("A=A-1");
        write("D=M-D");
        write("@FALSE" + flag);
        write("D;JGT");

        write("@SP");
        write("A=M-1");
        write("M=0");
        write("@CONTINUE" + flag);
        write("0;JMP");

        write("(FALSE" + flag + ")");
        write("@SP");
        write("A=M-1");
        write("M=-1");
        write("(CONTINUE" + flag + ")");
    }


    public void writePush(String segment, int index) {

        switch (segment) {
            case "constant":
                write("@" + index);
                write("D=A");
                pushD();
                break;
            case "argument":
                write("@ARG");
                write("D=M");
                write("@" + index);
                write("A=A+D");
                write("D=M");
                pushD();
                break;
            case "local":
                write("@LCL");
                write("D=M");
                write("@" + index);
                write("A=A+D");
                write("D=M");
                pushD();
                break;
            case "static":
                write("@"+(16+index));
                write("D=M");
                pushD();
            case "this":
                write("@THIS");
                write("D=M");
                write("@" + index);
                write("A=A+D");
                write("D=M");
                pushD();
                break;
            case "that":
                write("@THAT");
                write("D=M");
                write("@" + index);
                write("A=A+D");
                write("D=M");
                pushD();
                break;
            case "pointer":
                write("@" + (index == 0 ? "THIS" : "THAT"));
                write("D=M");
                pushD();
                break;
            case "temp":
                write("@R5");
                write("D=M");
                write("@" + index);
                write("A=A+D");
                write("D=M");
                pushD();
                break;
            default:
                throw new Error("");
        }
    }

    private void pushD() {
        write("@SP");
        write("A=M");
        write("M=D");
        write("@SP");
        write("M=M+1");
    }

    private void popD() {
        write("@R13");
        write("M=D");
        write("@SP");
        write("AM=M-1");
        write("D=M");
        write("@R13");
        write("A=M");
        write("M=D");
    }

    public void writePop(String segment, int index) {
        switch (segment) {
            case "constant":
                break;
            case "argument":
                write("@ARG");
                write("D=M");
                write("@" + index);
                write("D=A+D");
                popD();
                break;
            case "local":
                write("@LCL");
                write("D=M");
                write("@" + index);
                write("D=A+D");
                popD();
                break;
            case "static":
                write("@"+(16+index));
                write("D=A");
                popD();
            case "this":
                write("@THIS");
                write("D=M");
                write("@" + index);
                write("D=A+D");
                popD();
                break;
            case "that":
                write("@THAT");
                write("D=M");
                write("@" + index);
                write("D=A+D");
                popD();
                break;
            case "pointer":
                write("@" + (index == 0 ? "THIS" : "THAT"));
                write("D=A");
                popD();
                break;
            case "temp":
                write("@R5");
                write("D=M");
                write("@" + index);
                write("D=A+D");
                popD();
                break;
            default:
                throw new Error("");
        }
    }

    private void write(String command) {
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
