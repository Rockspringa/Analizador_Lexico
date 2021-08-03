public enum Token {
    ENTERO("1234567890", "Entero"),
    DECIMAL(".", "Decimal"),
    IDENTIFICADOR("Identificador"),
    SIMBOLO("[]{};,", "Simbolo"),
    ERROR("Error");

    private final String chars;
    private final String nombre;

    private Token(String s, String nombre) {
        this.chars = s;
        this.nombre = nombre;
    }

    private Token(String nombre) {
        this.chars = null;
        this.nombre = nombre;
    }

    private boolean contains(CharSequence s) {
        return chars.contains(s);
    }

    public static Token getToken(String[] s) {
        Token token = null;
        for (String c : s) {
            Token tmp = null;
            if (ENTERO.contains(c.toString())) tmp = ENTERO;
            else if (SIMBOLO.contains(c.toString())) tmp = SIMBOLO;
            else if (DECIMAL.contains(c.toString())) tmp = DECIMAL;
            else tmp = IDENTIFICADOR;

            if (token != tmp) {
                if (token == null || (token == ENTERO && tmp == DECIMAL)) token = tmp;
                else return ERROR;
            }
        }
        return ERROR;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}