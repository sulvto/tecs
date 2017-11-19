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
    private int returnFlag = 0;
    private File outputFile;

    private String currentFileName;

    public void setCurrentFileName(String filename) {
        this.currentFileName = filename;
    }

    public void setOutputFileName(String filename) {
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
        write("D=0");
        write("M=D-M");
    }

    private void sub() {
        pop();
        write("A=A-1");
        write("M=M-D");
    }

    private void add() {
        pop();
        write("A=A-1");
        write("M=M+D");
    }

    private void and() {
        pop();
        write("A=A-1");
        write("M=M&D");
    }

    private void or() {
        pop();
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
        pop();
        write("A=A-1");
        write("D=M-D");
        write("@TRUE" + flag);
        write("D;JEQ");

        write("@SP");
        write("A=M-1");
        write("M=0");
        write("@CONTINUE" + flag);
        write("0;JMP");

        write("(TRUE" + flag + ")");
        write("@SP");
        write("A=M-1");
        write("M=-1");
        write("(CONTINUE" + flag + ")");
    }


    private void lt() {
        int flag = jumpFlag++;
        pop();
        write("A=A-1");
        write("D=M-D");
        write("@TRUE" + flag);
        write("D;JLT");

        write("@SP");
        write("A=M-1");
        write("M=0");
        write("@CONTINUE" + flag);
        write("0;JMP");

        write("(TRUE" + flag + ")");
        write("@SP");
        write("A=M-1");
        write("M=-1");
        write("(CONTINUE" + flag + ")");
    }

    private void gt() {
        int flag = jumpFlag++;
        pop();
        write("A=A-1");
        write("D=M-D");
        write("@TRUE" + flag);
        write("D;JGT");

        write("@SP");
        write("A=M-1");
        write("M=0");
        write("@CONTINUE" + flag);
        write("0;JMP");

        write("(TRUE" + flag + ")");
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
                write("@"+currentFileName+"." + index);
                write("D=M");
                pushD();
                break;
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
                write("@" + (5 + index));
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
        pop();
        write("@R13");
        write("A=M");
        write("M=D");
    }

    private void pop() {
        write("@SP");
        write("AM=M-1");
        write("D=M");
    }

    public void writePop(String segment, int index) {
        switch (segment) {
            case "constant":
                // TODO
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
                write("@"+currentFileName+"." + index);
                write("D=A");
                popD();
                break;
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
                write("@" + (5 + index));
                write("D=A");
                popD();
                break;
            default:
                throw new Error("");
        }
    }

    public void writeInit(){
        // SP = 256
        write("@256");
        write("D=A");
        write("@SP");
        write("M=D");

        writeCall("Sys.init", 0);
    }

    public void writeLabel(String label){
        write("(" + label + ")");
    }

    public void writeGoto(String label){
        // goto label
        write("@" + label);
        write("0;JMP");
    }

    public void writeIf(String label){
        pop();
        write("@" + label);
        write("D;JNE");
    }

    private void pushSegment(String segment) {
        write("@"+segment);
        write("D=A");
        write("A=D");
        write("D=M");
        pushD();
    }

    public void writeCall(String funName, int numArgs){
        int flag = returnFlag++;

        // push return-address
        write("@returnAddress" + flag);
        write("D=A");
        pushD();

        // push lcl
        pushSegment("LCL");

        // push arg
        pushSegment("ARG");

        // push this
        pushSegment("THIS");

        // push that
        pushSegment("THAT");

        // arg = sp-n-5
        write("@SP");
        write("D=M");
        write("@" + (numArgs + 5));
        write("D=D-A");
        write("@ARG");
        write("M=D");

        // lcl = sp
        write("@SP");
        write("D=M");
        write("@LCL");
        write("M=D");

        // goto funName
        writeGoto(funName);

        // return-address
        writeLabel("returnAddress" + flag);
    }

    public void writeReturn(){

        // R5 -> frame
        // R6 -> ret

        // frame = lcl
        write("@LCL");
        write("D=M");
        write("@R5");
        write("M=D");

        // ret = *(frame - 5)
        write("@5");
        write("D=D-A");
        write("@R6");
        write("M=D");

        // *arg = pop()
        pop();
        write("@ARG");
        write("A=M");
        write("M=D");

        // sp = arg+1
        write("@1");
        write("D=A");
        write("@ARG");
        write("D=M+D");
        write("@SP");
        write("M=D");

        // that = *(frame - 1)
        write("@R5");
        write("D=M");
        write("@1");
        write("A=D-A");
        write("D=M");
        write("@THAT");
        write("M=D");

        // this = *(frame - 2)
        write("@R5");
        write("D=M");
        write("@2");
        write("A=D-A");
        write("D=M");
        write("@THIS");
        write("M=D");

        // arg = *(frame - 3)
        write("@R5");
        write("D=M");
        write("@3");
        write("A=D-A");
        write("D=M");
        write("@ARG");
        write("M=D");

        // lcl = *(frame - 4)
        write("@R5");
        write("D=M");
        write("@4");
        write("A=D-A");
        write("D=M");
        write("@LCL");
        write("M=D");

        // goto ret
        write("@R6");
        write("A=M");
        write("A=M");
        write("0;JMP");
    }

    public void writeFunction(String funName, int numLocals){
        // fun
        writeLabel(funName);
        for (int i = 0; i<numLocals; i++) {
            // push constant 0
            write("@0");
            write("D=A");
            pushD();
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
