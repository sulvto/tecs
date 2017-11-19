import java.io.*;

/**
 * Created by sulvto on 17-11-19.
 */
public class CompilationEngine {
    private InputStream input;
    private OutputStream output;

    public CompilationEngine(InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    public CompilationEngine(File input, File output) {
        try {
            this.input = new FileInputStream(input);
            this.output = new FileOutputStream(output);
        } catch (FileNotFoundException e) {
            throw new Error(e.getMessage());
        }
    }

    public void compileClass() {

    }

    public void compileClassVarDec() {

    }

    public void compileSubroutine() {

    }

    public void compileParameterList() {

    }

    public void compileVarDec() {

    }

    public void compileStatements() {

    }

    public void compileDo() {

    }

    public void compileLet() {

    }

    public void compileWhile() {

    }

    public void compileReturn() {

    }

    public void compileIf() {

    }

    public void compileExpression() {

    }

    public void compileTerm() {

    }

    public void compileExpressionList() {

    }


}
