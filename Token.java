import java.util.ArrayList;

public enum Token {
    ENTERO("1234567890", "Entero"),
    DECIMAL(".", "Decimal"),
    IDENTIFICADOR("Identificador"),
    CARACTER("[]{};,().:", "Caracter"),
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

    public static void putToken(String[] arr, String s, ArrayList<Object> out) {
        Token token = null;
        String obj = "";

        for (String c : arr) {
            Token tmp = null;
            
            if (ENTERO.contains(c.toString())) tmp = ENTERO;
            else if (DECIMAL.contains(c.toString())) tmp = DECIMAL;
            else if (CARACTER.contains(c.toString())) tmp = CARACTER;
            else tmp = IDENTIFICADOR;

            if (c.equals(" ") || c.equals("\n")) {
                if (!obj.equals("")) {
                    out.add(token + ":     " + obj);
                    token = null;
                    obj = "";
                    continue;
                } if (token == ERROR) {
                    out.add(token + ": Hay un error cerca de " + s);
                    token = null;
                    obj = "";
                    continue;
                }
            }
            
            else if (token == DECIMAL && tmp == DECIMAL) {
                token = ERROR;
            }
            
            else if (token != tmp && token != ERROR) {
                if (token == null) token = tmp;
                
                else if (token == IDENTIFICADOR) {
                    if (tmp == CARACTER || tmp == DECIMAL) {
                        out.add(IDENTIFICADOR + ":     " + obj);
                        obj = "";
                        token = CARACTER;
                    }
                }

                else if (token == CARACTER && tmp == IDENTIFICADOR) {
                    out.add(CARACTER + ":     " + obj);
                    obj = "";
                    token = tmp;
                }

                else if ((token == ENTERO && tmp == DECIMAL)
                            || (token == DECIMAL && tmp == ENTERO))
                                    token = DECIMAL;
                
                else {
                    out.add(ERROR + ": Hay un error cerca de " + s);
                    obj = "";
                    break;
                }
            }
            obj += c;
        }
        if (token == ERROR) out.add(token + ": Hay un error cerca de " + obj);
        if (!obj.equals("")) out.add(token + ":     " + obj);
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}