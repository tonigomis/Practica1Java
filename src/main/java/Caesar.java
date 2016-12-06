public class Caesar {
    /* Declaram la constant estàtica alfa, que contindrà el número de lletres que composen l'alfabet que farem servir
    (26 en aquest cas), i que si és necessari pot ser modificada sense haver de retocar tot el codi */
    static final int alfa = 26;

    /* En aquesta primera funció agafam la String s i l'int delta com a paràmetres. La primera variable ens passa un
    text pla que nosaltres hem de xifrar, mentre que la segona ens diu quin és el desplaçament que farem servir. A
    diferència de Juli Cèsar, el delta que utilitzarem no és sempre 3 */
    static String cypher(String s, int delta) {

        // Declaram i inicialitzam una String anomenada xifrat per guardar el nostre resultat
        String xifrat = "";

        // Convertim el contingut de la String s a majúscules per simplificar la tasca de xifrat
        s = s.toUpperCase();

        /* Feim el mòdul de la variable delta amb alfa, per obtenir el delta correcte en cas que aquest sigui més gran
        que l'alfabet que feim servir */
        delta %= alfa;

        /* Cream un bucle que recorrerà l'String que hem rebut per paràmetres, n'avaluarà el contingut i l'incorporarà
        a la nostra String xifrat com a resultat */
        for (int i = 0; i < s.length(); i++) {

            /* La primera condició diu que si el bucle troba un espai o un caràcter especial (com un signe de puntuació
            o una vocal accentuada) el copiarà directament a la resposta. S'ha de tenir en compte que en aquest punt ja
            no ens podem trobar lletres minúscules */
            if (s.charAt(i) == ' ' || s.charAt(i) < 'A' || s.charAt(i) > 'Z') {
                xifrat += s.charAt(i);

            /* La segona condició determina que, en cas que la suma dels valors ASCII del caràcter a estudi més el delta
            del xifrat sigui superior al nostre valor màxim, en aquest cas 90 ('Z'), li restarem alfa per mantenir-nos
            dins els límits de l'alfabet usat */
            } else if (s.charAt(i) + delta > 'Z') {
                xifrat += (char) ((s.charAt(i) + delta) - alfa);

            /* La condició final afegeix el valor trobat a la iteració més el delta del xifrat a la nostra resposta quan
            no es dóna cap dels casos anteriors */
            } else {
                xifrat += (char) (s.charAt(i) + delta);
            }
        }

        // Retornam la String xifrat, que compararem al joc de proves per determinar si el resultat és correcte
        return xifrat;
    }

    /* El segon mètode funciona igual que el primer, però a la inversa. Agafant els paràmetres String s (que en aquest
    cas és un text xifrat) i int delta hem de desfer la codificació per xifrat de Cèsar */
    static String decypher(String s, int delta) {

        // Declaram i inicialitzam una String anomenada desxifrat per guardar el nostre resultat
        String desxifrat = "";

        /* Feim el mòdul de la variable delta amb alfa, per obtenir el delta correcte en cas que aquest sigui més gran
        que l'alfabet que feim servir */
        delta %= alfa;

        /* Cream un bucle que recorrerà l'String que hem rebut per paràmetres, n'avaluarà el contingut i l'incorporarà
        a la nostra String xifrat com a resultat */
        for (int i = 0; i < s.length(); i++) {

            // Si el bucle troba un espai o un caràcter especial el copiarà directament a la resposta
            if (s.charAt(i) == ' ' || s.charAt(i) < 'A' || s.charAt(i) > 'Z') {
                desxifrat += s.charAt(i);

            /* Si el resultat de restar el delta al caràcter avaluat és menor que 'A', li sumarem el valor de alfa per
            tornar a estar dins els valors del nostre alfabet */
            } else if (s.charAt(i) - delta < 'A') {
                desxifrat += (char) (s.charAt(i) - delta + alfa);

            /* La nostra condició per defecte restarà, en aquest cas, el delta al valor del caràcter a estudi i
            l'afegirà a la nostra resposta*/
            } else {
                desxifrat += (char) (s.charAt(i) - delta);
            }
        }

        // Retornam la String desxifrat per fer la comparació al joc de proves
        return desxifrat;
    }

    /* Aquest mètode ha d'endevinar el delta que s'ha fet servir per encriptar una cadena de text i desencriptar-la. Per
    a aconseguir-ho hem hagut de recórrer a un càlcul de probabilitats basat en la freqüència d'aparició de caràcters
    específics en l'idioma donat */
    static String magic(String s) {

        // En primer lloc, guardam en una nova variable una versió normalitzada de l'String s que agafam per paràmetre.
        String st = normalitza(s);

        /* Declaram una variable de tipus enter anomenada valorTemporal on guardarem el caràcter (xifrat) que presenta
        el major número d'ocurrències en el nostre text encriptat */
        int valorTemporal = findMax(st);

        /* Retornam el resultat de la funció guessDelta, on avaluam les diferents possibilitats de xifrat i en
        obtenir-ne una de correcta la desxifram */
        return guessDelta(valorTemporal, s, st);
    }

    // Cream un mètode de tipus char que afagant l'String s ens calcularà quin caràcter es repeteix més vegades
    static char findMax(String s) {

        // Declaram una variable de tipus char anomenada findMax on guardarem el resultat del mètode
        char findMax;

        /* Declaram i inicialitzam a tamany d'alfa una array d'enters anomenada ocurrencies on es guardarà un comptador
        de repeticions de tots els caràcters de la cadena*/
        int[] ocurrencies = new int[alfa];

        /* Amb aquest bucle recorrem la cadena de text i cada vegada que apareix un caràcter afegim 1 a la seva posició
        a l'array.  Per a aconseguir-ho hem agafat el caràcter de la iteració i li hem restat el valor de 'A'. S'ha de
        tenir en compte que 'A' en ASCII val 65, així que els valors entre 'A' i 'Z' menys 65 equivalen als valors entre
        0 i 25, que són precisament les posicions de l'array que hem declarat prèviament */
        for (int i = 0; i < s.length(); i++) {
            ocurrencies[s.charAt(i) - 'A']++;
        }

        /* Declaram la variable int num i la inicialitzam amb el valor -1 per assegurar-nos que es modifica (si no ho
        fes ens llençaria un error d'ArrayIndexOutOfBounds) */
        int num = -1;

        /* Declaram la variable major amb el valor inicial de la primera posició de l'array. Així ens asseguram que el
        resultat sigui correcte malgrat la lletra més repetida al codi xifrat fos la 'A' */
        int major = ocurrencies[0];

        // Cream un bucle per recórrer tota l'array i trobar el valor de comptador més gran que hem obtingut
        for (int j = 0; j < ocurrencies.length; j++) {

            /* Amb aquest a condició comparam el valor de la posició on ens trobam amb el valor de major, i els
             intercanviam si la condició es compleix */
            if (ocurrencies[j] > major) {
                major = ocurrencies[j];

                /* Aquí guardam la posició de l'array on es troba el caràcter que més vegades es repeteix al nostre codi
                xifrat, que és en realitat el que més ens interessa saber a l'hora d'endevinar la clau */
                num = j;
            }
        }

        /* Assignam a la variable findMax el resultat de sumar 'A' (perquè torni a tenir el valor de la lletra que li
         correspon, i no el de la posició a l'array) a la posició més repetida, convertida en un char */
        findMax = (char) (num + 'A');

        // Retornam aquest caràcter, que serà el valorTemporal
        return findMax;
    }

    static String guessDelta(int valorTemporal, String s, String st) {
        int delta = valorTemporal - 'E';
        if (delta < 0) delta += alfa;
        st = decypher(s, delta);
        if (!(st.contains("EL") || st.contains("LA") || st.contains("SE") || st.contains("DE") || st.contains("EN"))) {
            delta = valorTemporal - 'A';
            if (delta < 0) delta += alfa;
            st = decypher(s, delta);
        } else if (!(st.contains("EL") || st.contains("LA") || st.contains("SE") || st.contains("DE") || st.contains("EN"))) {
            delta = valorTemporal - 'I';
            if (delta < 0) delta += alfa;
            st = decypher(s, delta);
        } else if (!(st.contains("EL") || st.contains("LA") || st.contains("SE") || st.contains("DE") || st.contains("EN"))) {
            delta = valorTemporal - 'S';
            if (delta < 0) delta += alfa;
            st = decypher(s, delta);
        }
        return st;
    }

    static String normalitza(String s) {
        char caracter;
        String normalitzat = "";
        for (int i = 0; i < s.length(); i++) {
            caracter = s.charAt(i);
            if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                normalitzat += caracter;
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