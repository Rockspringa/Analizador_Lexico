import java.util.ArrayList;

public class Analizador {
    public static Object[] analizar(String s) {
        ArrayList<Object> out = new ArrayList<>();

        addOutput(s, out);

        return out.toArray();
    }

    private static void addOutput(String s, ArrayList<Object> out) {
        int l = s.length();
        String[] sa = new String[l];
        char[] chars = s.toCharArray();

        for (int i = 0; i < l; i++) sa[i] = String.valueOf(chars[i]);

        Token.putToken(sa, s, out);
    }
}
