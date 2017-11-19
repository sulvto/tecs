import java.io.*;

/**
 * Created by sulvto on 17-11-19.
 */
public class JackTokenizer {
    private final InputStream input;

    private String token;
    private char peek = ' ';
    private TokenType currentTokenType;

    public JackTokenizer(InputStream input) {
        this.input = input;
    }

    public JackTokenizer(File input) {
        try {
            this.input = new FileInputStream(input);
        } catch (FileNotFoundException e) {
            throw new Error(e.getMessage());
        }
    }

    public boolean hasMoreTokens() {
        try {
            return input.available()!=0;
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
    }

    private void reach() {
        try {
            peek =  (char)input.read();
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
    }

    public void advance() {
        while (peek == '\n' || peek == '\t') {
             reach();
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (peek == '\"')  {
            stringBuilder.append(peek);
            reach();
            while (peek != '\"'){
                stringBuilder.append(peek);
            }
            stringBuilder.append("\"");
            token = stringBuilder.toString();
            currentTokenType = TokenType.STRING;
            peek = ' ';
            return;
        }
        while (Character.isDigit(peek))  {
            stringBuilder.append(peek);
            reach();
                if (!Character.isDigit(peek)) {

                    token = stringBuilder.toString();
                    currentTokenType = TokenType.INT;
                    return;
                }
        }
        while (Character.isLetter(peek))  {
            stringBuilder.append(peek);
            reach();
            if (!Character.isLetterOrDigit(peek)) {

                token = stringBuilder.toString();
                if (KeyWord.valueOf(token) == null) {
                    currentTokenType = TokenType.IDENTIFIER;
                }else{
                    currentTokenType = TokenType.KEYWORD;
                }
                return;
            }
        }

        token = new String(new char[]{peek});
        peek = ' ';
        currentTokenType = TokenType.SYMBOL;
    }

    public TokenType tokenType() {
        return currentTokenType;
    }

    public KeyWord keyword() {
        return KeyWord.valueOf(token);
    }

    public String symbol() {
        return token;
    }

    public String identifier() {
        return token;
    }

    public int intVal() {
        return Integer.valueOf(token);
    }

    public String stringVal() {
        return token.substring(1).substring(0,token.length()-2);
    }


    enum TokenType {KEYWORD, SYMBOL, IDENTIFIER, INT, STRING}

    enum KeyWord {CLASS, METHOD, INT, FUNCTION, BOOLEAN, CONSTRUCTOR, CHAR, VOID, VAR, STATIC, FIELD, LET, DO, IF, ELSE, WHILE, RETURN, TRUE, FALSE, NULL, THIS}
}
