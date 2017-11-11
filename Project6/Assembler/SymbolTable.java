import java.util.HashMap;
import java.util.Map;

/**
 * Created by sulvto on 17-11-9.
 */
public class SymbolTable {
    private final Map<String, Integer> table = new HashMap<>();

    public SymbolTable() {
        table.put("sp", 0);
        table.put("lcl", 1);
        table.put("arg", 2);
        table.put("this", 3);
        table.put("that", 4);
        table.put("r0", 0);
        table.put("r1", 1);
        table.put("r2", 2);
        table.put("r3", 3);
        table.put("r4", 4);
        table.put("r5", 5);
        table.put("r6", 6);
        table.put("r7", 7);
        table.put("r8", 8);
        table.put("r9", 9);
        table.put("r10", 10);
        table.put("r11", 11);
        table.put("r12", 12);
        table.put("r13", 13);
        table.put("r14", 14);
        table.put("r15", 0);
        table.put("screen", 16384);
        table.put("kbd", 24576);
    }


    public void addEntry(String symbol, int address) {
        table.put(symbol, address);
    }

    public boolean contains(String symbol) {
        return table.containsKey(symbol);
    }

    public int getAddress(String symbol) {
        return table.get(symbol);
    }

}
