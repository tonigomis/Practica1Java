

public class Transposition {
    static String cypher(String s, int dim) {
        String resultat = "";
        int col = s.length() / dim;
        int resta = s.length() % dim;
        if (resta != 0) {
            col += 1;
        }
        int t = 0;
        char[][] codifica = new char[col][dim];
        for (int i = 0; i < codifica.length; i++) {
            for (int j = 0; j < codifica[i].length; j++) {
                if (t == s.length()) break;
                codifica[i][j] = s.charAt(t);
                t++;
            }
        }
        t = 0;
        char[][] temp = new char[codifica[0].length][codifica.length];
        for (int x = 0; x < codifica.length; x++) {
            for (int y = 0; y < codifica[0].length; y++) {
                if (t == s.length()) break;
                temp[y][x] = codifica[x][y];
                t++;
            }
        }
        t = 0;
        for (char[] aTemp : temp) {
            for (int j = 0; j < temp[0].length; j++) {
                if (t == s.length()) break;
                resultat += aTemp[j];
                t++;
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
