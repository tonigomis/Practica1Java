import java.util.Arrays;

public class Transposition {
    static String cypher(String s, int dim) {
        char[][] codifica = cypherToArray(s, dim);
        return extractCypher(codifica);
    }

    static String decypher(String s, int dim) {
        char[][] decodifica = decypherToArray(s, dim);
        return extractDecypher(decodifica);
    }

    static String cypher(String s, String key) {
        char[][] codifica = cypherToArray(s, key.length());
        codifica = unSort(codifica, key);
        return extractCypher(codifica);
    }

    static String decypher(String s, String key) {
        char[][] decodifica = decypherUnsortedToArray(s, key.length(), key);
        decodifica = Sort(decodifica, key);
        return extractDecypher(decodifica);
    }

    static char[][] cypherToArray(String s, int dim) {
        int fila = (int) (Math.ceil(s.length() / (double) dim));
        char[][] codifica = new char[fila][dim];
        for (int i = 0, contador = 0; i < codifica.length; i++) {
            for (int j = 0; j < codifica[0].length; j++) {
                if (contador == s.length()) {
                    codifica[i][j] = '\u0000';
                    continue;
                }
                codifica[i][j] = s.charAt(contador);
                contador++;
            }
        }
        return codifica;
    }

    static char[][] decypherToArray(String s, int dim) {
        int fila = (int) (Math.ceil(s.length() / (double) dim));
        char[][] decodifica = new char[fila][dim];
        for (int i = 0, contador = 0; i < decodifica[0].length; i++) {
            for (int j = 0; j < decodifica.length; j++) {
                if (!(j == fila - 1 && i > (dim - 1) - (dim * fila - s.length())) && contador < s.length()) {
                    decodifica[j][i] = s.charAt(contador);
                    contador++;
                } else {
                    decodifica[j][i] = '\u0000';
                }
            }
        }
        return decodifica;
    }

    static char[][] decypherUnsortedToArray(String s, int dim, String key) {
        char[] clau = key.toCharArray();
        Arrays.sort(clau);
        int[] ordre = new int[key.length()];
        int[] ordreInicial = new int[key.length()];
        int contador;
        for (int i = 0; i < key.length(); i++) {
            for (contador = 0; contador < key.length(); contador++) {
                if (key.charAt(i) == clau[contador]) {
                    ordre[i] = contador;
                    clau[contador] = 0;
                    ordreInicial[contador] = i;
                    break;
                }
            }
        }
        int fila = (int) (Math.ceil(s.length() / (double) dim));
        char[][] decodifica = new char[fila][dim];
        int posicio = 0;
        for (int x = 0; x < decodifica[0].length; x++) {
            for (int y = 0; y < decodifica.length; y++) {
                if (!((y == fila - 1) && (ordreInicial[x] >= key.length() - (dim * fila - s.length())))) {
                    decodifica[y][x] = s.charAt(posicio);
                    posicio++;
                } else {
                    decodifica[y][x] = 0;
                }
            }
        }
        return decodifica;
    }

    static char[][] unSort(char[][] codifica, String key) {
        char[] desordenat = key.toCharArray();
        Arrays.sort(desordenat);
        int[] ordre = new int[codifica[0].length];
        char[][] xifrat = new char[codifica.length][codifica[0].length];
        for (int i = 0; i < xifrat[0].length; i++) {
            for (int contador = 0; contador < key.length(); contador++) {
                if (key.charAt(i) == desordenat[contador]) {
                    ordre[i] = contador;
                    desordenat[contador] = 0;
                    break;
                }
            }
        }
        for (int x = 0, contador = 0; x < codifica[0].length; x++) {
            for (int y = 0; y < codifica.length; y++) {
                xifrat[y][ordre[contador]] = codifica[y][x];
            }
            contador++;
        }
        return xifrat;
    }

    static char[][] Sort(char[][] decodifica, String key) {
        char[] ordenat = key.toCharArray();
        Arrays.sort(ordenat);
        int[] ordre = new int[decodifica[0].length];
        char[][] desxifrat = new char[decodifica.length][decodifica[0].length];
        int contador;
        for (int i = 0; i <= desxifrat[0].length - 1; i++) {
            for (contador = 0; contador < key.length(); contador++) {
                if (key.charAt(i) == ordenat[contador]) {
                    ordre[i] = contador;
                    ordenat[contador] = 0;
                    break;
                }
            }
        }
        for (int x = 0; x < decodifica[0].length; x++) {
            for (int y = 0; y < decodifica.length; y++) {
                desxifrat[y][x] = decodifica[y][ordre[x]];
            }
        }
        return desxifrat;
    }

    static String extractCypher(char[][] codifica) {
        String resultat = "";
        for (int i = 0; i < codifica[0].length; i++) {
            for (char[] aCodifica : codifica) {
                if (aCodifica[i] != '\u0000') resultat += aCodifica[i];
            }
        }
        return resultat;
    }

    static String extractDecypher(char[][] decodifica) {
        String resultat = "";
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
