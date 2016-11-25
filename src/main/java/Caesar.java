
public class Caesar {
    static String cypher(String s, int delta) {
        String xifrat = "";
        s = s.toUpperCase();
        int punter;
        for (int i = 0; i < s.length(); i++) {
            delta = delta % 26;
            if (s.charAt(i) == 32 || s.charAt(i) < 65 || s.charAt(i) > 90) {
                xifrat += s.charAt(i);
            } else if (s.charAt(i) + delta > 'Z') {
                punter = (s.charAt(i) + delta) - 26;
                xifrat += (char) punter;
            } else {
                xifrat += (char) (s.charAt(i) + delta);
            }
        }
        s = xifrat;
        return s;
    }

    static String decypher(String s, int delta) {
        String desxifrat = "";
        int punter;
        for (int i = 0; i < s.length(); i++) {
            delta = delta % 26;
            if (s.charAt(i) == 32 || s.charAt(i) < 65 || s.charAt(i) > 90) {
                desxifrat += s.charAt(i);
            } else if (s.charAt(i) - delta < 'A') {
                punter = (s.charAt(i) - delta + 26);
                desxifrat += (char) punter;
            } else {
                desxifrat += (char) (s.charAt(i) - delta);
            }
        }
        s = desxifrat;
        return s;
    }

    static String magic(String s) {
        String noTanMagic = "";
        int decalatge;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (s.charAt(i)  == 32 || s.charAt(i) < 65 || s.charAt(i) > 90) {
                    noTanMagic += s.charAt(i);
                }
            }

        }
        s = noTanMagic;
        return s;
    }
}
