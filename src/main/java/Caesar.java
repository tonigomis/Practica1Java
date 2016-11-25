
public class Caesar {
    static int alfa = 26;

    static String cypher(String s, int delta) {
        String xifrat = "";
        s = s.toUpperCase();
        int punter;
        delta = delta % alfa;
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
        delta = delta % alfa;
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

    static String magic(String s) {
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
    }
}
