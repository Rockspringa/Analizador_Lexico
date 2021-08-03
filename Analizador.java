public class Analizador {
    public static String analizar(String s) {
        StringBuilder out = new StringBuilder();
        StringBuilder token = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ' ') out.append(getOutput(token.toString()));
            else 
        }
    }

    public static String getOutput(String s) {
        int l = s.length();
        String[] sa = new String[l];
        char[] chars = s.toCharArray();

        for (int i = 0; i < l; i++) sa[i] = String.valueOf(chars[i]);
        return Token.getToken(sa) + " " + s;
    }
}
