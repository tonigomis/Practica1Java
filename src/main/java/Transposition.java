public class Transposition {
    static String cypher(String s, int dim) {
        String resultat = "";
        int fila = (int) (Math.ceil(s.length() / (double) dim));
        char[][] codifica = new char[fila][dim];
        for (int i = 0, contador = 0; i < codifica.length; i++) {
            for (int j = 0; j < codifica[i].length; j++) {
                if (contador == s.length()) {
                    codifica[i][j] = '\u0000';
                    continue;
                }
                codifica[i][j] = s.charAt(contador);
                contador++;
            }
        }
        return fillCypher(codifica, resultat);
    }

    static String decypher(String s, int dim) {
        String resultat = "";
        int fila = (int) (Math.ceil(s.length() / (double) dim));
        char[][] decodifica = new char[fila][dim];
        for (int i = 0, contador = 0; i < decodifica[0].length; i++) {
            for (int j = 0; j < decodifica.length; j++) {
                if (!(j == decodifica.length - 1 && i > (decodifica[0].length - 1) - (decodifica.length * decodifica[0].length - s.length())) && contador < s.length()) {
                    decodifica[j][i] = s.charAt(contador);
                    contador++;
                } else {
                    decodifica[j][i] = '\u0000';
                }
            }
        }
        return fillDecypher(decodifica, resultat);
    }

    static String cypher(String s, String key) {
        return null;
    }

    static String decypher(String s, String key) {
        return null;
    }

    static String fillCypher(char[][] codifica, String resultat) {
        for (int i = 0; i < codifica[0].length; i++) {
            for (char[] aCodifica : codifica) {
                if (aCodifica[i] != '\u0000') resultat += aCodifica[i];
            }
        }
        return resultat;
    }

    static String fillDecypher(char[][] decodifica, String resultat) {
        for (char[] aDecodifica : decodifica) {
            for (int k = 0; k < decodifica[0].length; k++) {
                if (aDecodifica[k] != '\u0000') {
                    resultat += aDecodifica[k];
                }
            }
        }
        return resultat;
    }
}
