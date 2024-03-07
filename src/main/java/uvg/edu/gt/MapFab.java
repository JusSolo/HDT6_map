package uvg.edu.gt;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapFab<K,V> {
    public Map<K,V> getMap(String tipo){
        tipo = tipo.toLowerCase();
        char t = tipo.charAt(0);

        switch (t){
            case 'h':
                return new HashMap<K,V>();
            case 't':
                return new TreeMap<K,V>();

        }
        return new LinkedHashMap<K,V>();
    }
}
