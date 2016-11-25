
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

        return null;
    }

    static String magic(String s) {
        return null;
    }
}
