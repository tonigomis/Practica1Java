

public class Vigenere {

    static String encode(String s, String password) {
        s = normalitza(s).toUpperCase();
        password = normalitza(password).toUpperCase();
        String codificat = "";
        int punter;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (esEspecial(s.charAt(i))) {
                codificat += s.charAt(i);
                continue;
            }
            punter = (passaNum(s.charAt(i)) + passaNum(password.charAt(j)));
            codificat += passaChar(punter);
            j++;
            if (j >= password.length()) j = 0;
        }
        return codificat;
    }

    static String decode(String s, String password) {
        s = normalitza(s).toUpperCase();
        password = normalitza(password).toUpperCase();
        String decodificat = "";
        int punter;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (esEspecial(s.charAt(i))) {
                decodificat += s.charAt(i);
                continue;
            }
            punter = (passaNum(s.charAt(i)) - passaNum(password.charAt(j)));
            if (punter <= 0) punter += 26;
            decodificat += (char) (punter + 64);
            j++;
            if (j >= password.length()) j = 0;
        }
        return decodificat;
    }

    static int passaNum(char c) {
        return c - 64;
    }

    static char passaChar(int r) {
        if (r > 26) r %=  26;
        return (char) (64 + r);
    }

    static boolean esEspecial(char w) {
        return (w < 65 || w > 90);
    }

    static String normalitza(String s) {
        char caracter;
        String codificat = "";
        for (int i = 0; i < s.length(); i++) {
            caracter = s.charAt(i);
            if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                codificat += caracter;
                continue;
            } else if (s.charAt(i) == ' ') {
                caracter = ' ';
            } else if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
                caracter -= 32;
            } else if (s.charAt(i) >= 224 && s.charAt(i) <= 230 || s.charAt(i) >= 192 && s.charAt(i) <= 198) {
                caracter = 'A';
            } else if (s.charAt(i) >= 232 && s.charAt(i) <= 235 || s.charAt(i) >= 200 && s.charAt(i) <= 203) {
                caracter = 'E';
            } else if (s.charAt(i) >= 236 && s.charAt(i) <= 239 || s.charAt(i) >= 204 && s.charAt(i) <= 207) {
                caracter = 'I';
            } else if (s.charAt(i) >= 242 && s.charAt(i) <= 246 || s.charAt(i) >= 210 && s.charAt(i) <= 214) {
                caracter = 'O';
            } else if (s.charAt(i) >= 249 && s.charAt(i) <= 252 || s.charAt(i) >= 217 && s.charAt(i) <= 220) {
                caracter = 'U';
            }
            codificat += caracter;
        }
        return codificat;
    }
}


