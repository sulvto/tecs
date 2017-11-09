import java.util.HashMap;
import java.util.Map;

/**
 * Created by sulvto on 17-11-9.
 */
public class SymbolTable {
    private final Map<String, Integer> table = new HashMap<>();

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
