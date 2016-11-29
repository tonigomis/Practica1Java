

public class Transposition {
    static String cypher(String s, int dim) {
        String resultat = "";
        int fila = (int) (Math.ceil(s.length() / (double) dim));
        char[][] codifica = new char[fila][dim];
        for (int i = 0, t = 0; i < codifica.length; i++) {
            for (int j = 0; j < codifica[i].length; j++) {
                if (t == s.length()) {
                    codifica[i][j] = '\u0000';
                    continue;
                }
                codifica[i][j] = s.charAt(t);
                t++;
            }
        }

        for (int i = 0; i < codifica[0].length; i++) {
            for (int j = 0; j < codifica.length; j++) {
                if (codifica[j][i] != '\u0000') {
                    resultat += codifica[j][i];
                }
            }
        }
        return resultat;
    }

    static String decypher(String s, int dim) {
        return null;
    }

    static String cypher(String s, String key) {
        return null;
    }

    static String decypher(String s, String key) {
        return null;
    }

}
