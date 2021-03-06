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
        delta = setDelta(delta);

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
        delta = setDelta(delta);

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
        return guessDelta(valorTemporal, s);
    }

    // Cream un mètode de tipus char que afagant l'String s ens calcularà quin caràcter es repeteix més vegades
    static char findMax(String s) {

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
        for (int pos = 0; pos < ocurrencies.length; pos++) {

            /* Amb aquest a condició comparam el valor de la posició on ens trobam amb el valor de major, i els
             intercanviam si la condició es compleix */
            if (ocurrencies[pos] > major) {
                major = ocurrencies[pos];

                /* Aquí guardam la posició de l'array on es troba el caràcter que més vegades es repeteix al nostre codi
                xifrat, que és en realitat el que més ens interessa saber a l'hora d'endevinar la clau */
                num = pos;
            }
        }
        /* Retornam el resultat de sumar 'A' (perquè torni a tenir el valor de la lletra que li correspon, i no el de la
        posició a l'array) a la posició més repetida, convertida en un char. Això serà el valorTemporal */
        return (char) (num + 'A');
    }

    /* Aquest mètode avalua els possibles valors de delta en relació al major número d'ocurrències dels caràcters més
    freqüents en llengua catalana, que són, per aquest ordre: 'E', 'A', 'I' i 'S'*/
    static String guessDelta(int valorTemporal, String s) {

        // En primer lloc provam amb la lletra 'E', ja que és la més freqüent
        int delta = valorTemporal - 'E';

        // Ens asseguram que el valor de delta no sigui negatiu
        setDelta(delta);

        // Cridam la funció de desxifrar amb el delta que hem determinat d'inici
        String st = decypher(s, delta);

        /* Comprovam si al text desxifrat s'hi poden trobar algunes combinacions de dos caràcters freqüents en català i,
        en cas contrari, provam amb altres caràcters freqüents de la llengua catalana. Condició dividida en dos if per a
        facilitar-ne la lectura */
        if (!(st.contains("EL") || st.contains("LA") || st.contains("DE") || st.contains("EN") || st.contains("ET"))) {
            if (!(st.contains("SI") || st.contains("NA"))) {
                delta = valorTemporal - 'A';
                setDelta(delta);
                st = decypher(s, delta);
            }
        }

        if (!(st.contains("EL") || st.contains("LA") || st.contains("DE") || st.contains("EN") || st.contains("ET"))) {
            if (!(st.contains("SI") || st.contains("NA"))) {
                delta = valorTemporal - 'I';
                setDelta(delta);
                st = decypher(s, delta);
            }
        }

        if (!(st.contains("EL") || st.contains("LA") || st.contains("DE") || st.contains("EN") || st.contains("ET"))) {
            if (!(st.contains("SI") || st.contains("NA"))) {
                delta = valorTemporal - 'S';
                setDelta(delta);
                st = decypher(s, delta);
            }
        }
        // Retornam la funció de desxifrar aquest text, ara ja sí, amb el delta correcte segons el joc de proves
        return st;
    }

    // Amb aquest mètode ens asseguram que el valor de delta sempre estigui dins el rang de l'alfabet
    static int setDelta(int delta) {
        while (delta < 0) delta += alfa;
        delta %= alfa;
        return delta;
    }

    /* Aquesta funció avalua el contingut de l'String que li passam per paràmetre i el simpifica, eliminant els espais
    en blanc i símbols de puntuació, passant les minúscules (si existeixen) a majúscules i convertint les vocals
    accentuades en vocals majúscules i sense accents */
    static String normalitza(String s) {

        // Declaram una variable char per emmagatzemar el caràcter a estudi
        char caracter;

        // Declaram i inicialitzam en blanc una String buida on guardar el text normalitzat
        String normalitzat = "";

        // Recorrem la cadena de text amb aquest bucle per convertir cada posició en el que necessitam
        for (int i = 0; i < s.length(); i++) {
            caracter = s.charAt(i);

            // Si es tracta d'una lletra majúscula, l'afegim directament al nostre resultat
            if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                normalitzat += caracter;
                continue;

                // Si és una lletra minúscula, li restam 32 per passar-la a majúscula
            } else if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
                caracter -= 32;

            /* A partir d'aquí, consideram tots els caràcters especials que afecten les vocals i les convertim en la
            seva majúscula genèrica */
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

                // Si no es compleix cap de les condicions anteriors, enviam el fil cap al principi
            } else {
                continue;
            }
            // Aquí incorporam el caràcter normalitzat al nostre resultat final
            normalitzat += caracter;
        }
        // Retornam la String normalitzada per poder treballar-hi més còmodament en endavant
        return normalitzat;
    }
}

    /* Aquesta era la versió original de la funció magic abans d'intentar-la amb un càlcul d'ocurrències */
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