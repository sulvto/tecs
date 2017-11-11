import java.util.HashMap;
import java.util.Map;

/**
 * Created by sulvto on 17-11-9.
 */
public class Code {
    private final static Map<String, Dest> destTable = new HashMap<>();
    private final static Map<String, Comp> compTable = new HashMap<>();
    private final static Map<String, Jump> jumpTable = new HashMap<>();

    static {
        destTable.put("null", new Dest('0', '0', '0'));
        destTable.put("m", new Dest('0', '0', '1'));
        destTable.put("d", new Dest('0', '1', '0'));
        destTable.put("md", new Dest('0', '1', '1'));
        destTable.put("a", new Dest('1', '0', '0'));
        destTable.put("am", new Dest('1', '0', '1'));
        destTable.put("ad", new Dest('1', '1', '0'));
        destTable.put("amd", new Dest('1', '1', '1'));

        compTable.put("0", new Comp('0', '1', '0', '1', '0', '1', '0'));
        compTable.put("1", new Comp('0', '1', '1', '1', '1', '1', '1'));
        compTable.put("-1", new Comp('0', '1', '1', '1', '0', '1', '0'));
        compTable.put("d", new Comp('0', '0', '0', '1', '1', '0', '0'));
        compTable.put("a", new Comp('0', '1', '1', '0', '0', '0', '0'));
        compTable.put("m", new Comp('1', '1', '1', '0', '0', '0', '0'));
        compTable.put("!d", new Comp('0', '0', '0', '1', '1', '0', '1'));
        compTable.put("!a", new Comp('0', '1', '1', '0', '0', '0', '1'));
        compTable.put("!m", new Comp('1', '1', '1', '0', '0', '0', '1'));
        compTable.put("-d", new Comp('0', '0', '0', '1', '1', '1', '1'));
        compTable.put("-a", new Comp('0', '1', '1', '0', '0', '1', '1'));
        compTable.put("-m", new Comp('1', '1', '1', '0', '0', '1', '1'));
        compTable.put("d+1", new Comp('0', '0', '1', '1', '1', '1', '1'));
        compTable.put("a+1", new Comp('0', '1', '1', '0', '1', '1', '1'));
        compTable.put("m+1", new Comp('1', '1', '1', '0', '1', '1', '1'));
        compTable.put("d-1", new Comp('0', '0', '0', '1', '1', '1', '0'));
        compTable.put("a-1", new Comp('0', '1', '1', '0', '0', '1', '0'));
        compTable.put("m-1", new Comp('1', '1', '1', '0', '0', '1', '0'));
        compTable.put("d+a", new Comp('0', '0', '0', '0', '0', '1', '0'));
        compTable.put("d+m", new Comp('1', '0', '0', '0', '0', '1', '0'));
        compTable.put("d-a", new Comp('0', '0', '1', '0', '0', '1', '1'));
        compTable.put("d-m", new Comp('1', '0', '1', '0', '0', '1', '1'));
        compTable.put("a-d", new Comp('0', '0', '0', '0', '1', '1', '1'));
        compTable.put("m-d", new Comp('1', '0', '0', '0', '1', '1', '1'));
        compTable.put("d&a", new Comp('0', '0', '0', '0', '0', '0', '0'));
        compTable.put("d&m", new Comp('1', '0', '0', '0', '0', '0', '0'));
        compTable.put("d|a", new Comp('0', '0', '1', '0', '1', '0', '1'));
        compTable.put("d|m", new Comp('1', '0', '1', '0', '1', '0', '1'));


        jumpTable.put("null", new Jump('0', '0', '0'));
        jumpTable.put("jgt", new Jump('0', '0', '1'));
        jumpTable.put("jeq", new Jump('0', '1', '0'));
        jumpTable.put("jge", new Jump('0', '1', '1'));
        jumpTable.put("jlt", new Jump('1', '0', '0'));
        jumpTable.put("jne", new Jump('1', '0', '1'));
        jumpTable.put("jle", new Jump('1', '1', '0'));
        jumpTable.put("jmp", new Jump('1', '1', '1'));
    }

    public static char[] dest(String code) {
        if (destTable.containsKey(code)) {
            Dest dest = destTable.get(code);
            return new char[]{dest.d1, dest.d2, dest.d3};
        }
        throw new Error("Code dest:'" + code+"'");
    }

    public static char[] comp(String code) {
        if (compTable.containsKey(code)) {
            Comp comp = compTable.get(code);
            return new char[]{comp.a, comp.c1, comp.c2, comp.c3, comp.c4, comp.c5, comp.c6};
        }
        throw new Error("Code comp:'" + code+"'");
    }

    public static char[] jump(String code) {
        if (jumpTable.containsKey(code)) {
            Jump jump = jumpTable.get(code);
            return new char[]{jump.j1, jump.j2, jump.j3};
        }
        throw new Error("Code jump:'" + code+"'");
    }

    static class Comp {
        public final char a, c1, c2, c3, c4, c5, c6;

        Comp(char a, char c1, char c2, char c3, char c4, char c5, char c6) {
            this.a = a;
            this.c1 = c1;
            this.c2 = c2;
            this.c3 = c3;
            this.c4 = c4;
            this.c5 = c5;
            this.c6 = c6;
        }
    }

    static class Jump {
        public final char j1, j2, j3;

        Jump(char j1, char j2, char j3) {
            this.j1 = j1;
            this.j2 = j2;
            this.j3 = j3;
        }
    }

    static class Dest {
        public final char d1, d2, d3;

        Dest(char d1, char d2, char d3) {
            this.d1 = d1;
            this.d2 = d2;
            this.d3 = d3;
        }
    }
}
