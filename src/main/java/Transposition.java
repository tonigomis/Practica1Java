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
            for (char[] aCodifica : codifica) {
                if (aCodifica[i] != '\u0000') resultat += aCodifica[i];
            }
        }
        return resultat;
    }

    static String decypher(String s, int dim) {
        String resultat = "";
        int col = (int) (Math.ceil(s.length() / (double) dim));
        //System.out.println(col);
        //System.out.println(s.length());
        //System.out.println(dim);
        char[][] decodifica = new char[dim][col];
        for (int i = 0, t = 0; i < decodifica.length; i++) {
            //System.out.println(s.charAt(i));
            for (int j = 0; j < decodifica[0].length; j++) {
                //System.out.println(s.charAt(j));
                if (t == s.length()) {
                    decodifica[i][j] = '\u0000';
                    continue;
                }
                decodifica[i][j] = s.charAt(t);
                t++;
            }
        }
        for (int i = 0; i < decodifica[0].length; i++) {
            for (char[] aCodifica : decodifica) {
                if (aCodifica[i] != '\u0000') resultat += aCodifica[i];
            }
        }

        return resultat;
    }

    static String cypher(String s, String key) {
        return null;
    }

    static String decypher(String s, String key) {
        return null;
    }

}
