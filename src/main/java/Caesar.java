
public class Caesar {
    static int alfa = 26;
    static String cypher(String s, int delta) {
        String xifrat = "";
        s = s.toUpperCase();
        int punter;
        delta %= alfa;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' || s.charAt(i) < 'A' || s.charAt(i) > 'Z') {
                xifrat += s.charAt(i);
            } else if (s.charAt(i) + delta > 'Z') {
                punter = (s.charAt(i) + delta) - alfa;
                xifrat += (char) punter;
            } else {
                xifrat += (char) (s.charAt(i) + delta);
            }
        }
        return xifrat;
    }

    static String decypher(String s, int delta) {
        String desxifrat = "";
        int punter;
        delta %= alfa;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' || s.charAt(i) < 'A' || s.charAt(i) > 'Z') {
                desxifrat += s.charAt(i);
            } else if (s.charAt(i) - delta > 'Z') {
                punter = s.charAt(i) - delta - alfa;
                desxifrat += (char) punter;
            } else if (s.charAt(i) - delta < 'A') {
                punter = (s.charAt(i) - delta + alfa);
                desxifrat += (char) punter;
            } else {
                desxifrat += (char) (s.charAt(i) - delta);
            }
        }
        return desxifrat;
    }

    /* Aquesta era la versió original de la funció magic abans d'intentar-la amb un càlcul estadístic d'ocurrències */
/*    static String magic(String s) {
        int delta = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                i++;
                if (s.charAt(i) - s.charAt(i + 1) == 11) {
                    delta = s.charAt(i) - 'L';
                } else if (s.charAt(i) - s.charAt(i + 1) == -7) {
                    delta = s.charAt(i) - 'E';
                } else if (s.charAt(i) - s.charAt(i + 1) == -1) {
                    delta = s.charAt(i) - 'D';
                }
            }
        }
        return decypher(s, delta);
    }*/

    static String magic(String s) {
        String st = normalitza(s);
        int valorTemporal = findMax(st);
        int delta = guessDelta(valorTemporal);
        return decypher(s, delta);
    }

    static char findMax(String s) {
        char findMax;
        int[] ocurrencies = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ocurrencies[s.charAt(i) - 'A']++;
        }
        int num = -1;
        int major = ocurrencies[0];
        for (int j = 0; j < ocurrencies.length; j++) {
            if (ocurrencies[j] > major) {
                major = ocurrencies[j];
                num = j + 'A';
            }
        }
        findMax = (char) num;
        return findMax;
    }

    static int guessDelta(int valorTemporal) {
        int delta = valorTemporal - 'E';
        if (delta < 0) delta += alfa;
        return delta;
    }

    static String normalitza(String s) {
        char caracter;
        String normalitzat = "";
        for (int i = 0; i < s.length(); i++) {
            caracter = s.charAt(i);
            if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                normalitzat += caracter;
                continue;
            } else if (s.charAt(i) == ' ') {
                continue;
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
            } else {
                continue;
            }
            normalitzat += caracter;
        }
        return normalitzat;
    }
}