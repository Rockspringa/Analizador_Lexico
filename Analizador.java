import java.util.ArrayList;

public class Analizador {
    public static Object[] analizar(String s) {
        ArrayList<Object> out = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ' ' || c == '\n') {
                addOutput(token.toString(), out);
                token = new StringBuilder();
            } else token.append(c);
        }

        if (!token.isEmpty()) addOutput(token.toString(), out);

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
