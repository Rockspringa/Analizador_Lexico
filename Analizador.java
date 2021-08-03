import java.util.ArrayList;

public class Analizador {
    public static Object[] analizar(String s) {
        ArrayList<Object> out = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ' ' || c == '\n') {
                out.add(getOutput(token.toString()));
                token = new StringBuilder();
            } else token.append(c);
        }

        if (!token.isEmpty()) out.add(getOutput(token.toString()));

        return out.toArray();
    }

    private static String getOutput(String s) {
        int l = s.length();
        String[] sa = new String[l];
        char[] chars = s.toCharArray();

        for (int i = 0; i < l; i++) sa[i] = String.valueOf(chars[i]);

        return Token.getToken(sa) + ":    " + s;
    }
}
