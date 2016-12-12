import java.util.Arrays;

public class Transposition {

    /* El primer mètode agafa com a paràmetres una 'String s' i un 'int dim' donats, els converteix en una array, els
    transposa i després n'extreu els valors no nuls de les caselles, afegint-los a una String resultat */
    static String cypher(String s, int dim) {

        // Cream una array bidimensional de caràcters per recollir el nostre missatge xifrat
        char[][] codifica = cypherToArray(s, dim);

        // Retornam una String amb els caràcters xifrats
        return extractCypher(codifica);
    }

    /* El segon mètode agafa els mateixos paràmetres, crea l'array desxifrada i després n'extreu els caràcters a una
    String resultat */
    static String decypher(String s, int dim) {

        // Cream una array bidimensional de caràcters per recollir el missatge desxifrat
        char[][] decodifica = decypherToArray(s, dim);

        // Retornam una String amb els caràcters desxifrats
        return extractDecypher(decodifica);
    }

    /* Aquest mètode funciona d'una forma molt similar al primer, amb la diferència que la clau és una String. S'agafa
    * el seu valor numèric de longitud per determinar el número de columnes que contindrà l'array, però després s'ha de
    * xifrar canviant els valors de columna segons l'ordre alfabètic dels caràcters de la clau */
    static String cypher(String s, String key) {

        // Cream una array bidimensional de caràcters per recollir el missatge xifrat
        char[][] codifica = cypherToArray(s, key.length());

        // Desordenam els valors continguts a l'array segons l'ordre que ens marca la clau
        codifica = unSort(codifica, key);

        // Retornam una String amb el missatge codificat
        return extractCypher(codifica);
    }

    /* Aquest mètode funciona d'una forma molt similar al segon, amb la diferència que la clau és una String. S'agafa
    * el seu valor numèric de longitud per determinar el número de columnes que contindrà l'array, però després s'ha de
    * xifrar canviant els valors de columna segons l'ordre alfabètic dels caràcters de la clau */
    static String decypher(String s, String key) {

        // Cream l'array bidimensional que contindrà els caràcters a desxifrar
        char[][] decodifica = decypherUnsortedToArray(s, key.length(), key);

        // Ordenam dins aquest array els valors continguts a l'array codificada segons l'ordre de la clau
        decodifica = Sort(decodifica, key);

        // Retornam un String amb el missatge decodificat
        return extractDecypher(decodifica);
    }

    /* En aquest mètode agafarem els paràmetres 'String s' que conté el missatge original i 'int dim', que ens determina
    la quantitat de columnes que ha de tenir la nostra array, en calculam les files i cream una array on guardarem el
    resultat */
    static char[][] cypherToArray(String s, int dim) {

        /* El número de files de la nostra array vindrà determinada pel resultat de dividir la longitud del missatge
        entre la quantitat de columnes que ens donen, arrodonit cap amunt */
        int fila = (int) (Math.ceil(s.length() / (double) dim));

        // Cream l'array amb els paràmetres obtinguts en la passa anterior
        char[][] codifica = new char[fila][dim];

        /* Recorrem l'array de dalt a baix, i hi incorporam els caràcters existents al nostre missatge de la mateixa
        manera. Declaram una variable anomenada contador i la inicialitzam a 0 a fi de controlar la longitud del nostre
        missatge. Una vegada acabat el missatge completam l'array amb valors nuls. */
        for (int y = 0, contador = 0; y < codifica.length; y++) {
            for (int x = 0; x < codifica[0].length; x++) {
                if (contador == s.length()) {
                    codifica[y][x] = '\u0000';
                    continue;
                }
                codifica[y][x] = s.charAt(contador);
                contador++;
            }
        }

        // Retornam la nostra array codificada per transposició
        return codifica;
    }

    /* En aquest mètode agafarem els paràmetres 'String s', que en aquest cas conté el missatge xifrat, i 'int dim',
    igual que en la funció anterior, i farem el procés invers */
    static char[][] decypherToArray(String s, int dim) {

        // Calculam la quantitat de files
        int fila = (int) (Math.ceil(s.length() / (double) dim));

        // Cream l'array per guardar el resultat
        char[][] decodifica = new char[fila][dim];

        /* Recorrem l'array d'esquerra a dreta i de dalt a baix, i sempre que no ens trobem en una de les posicions
        sobrants de l'array que hem creat hi introduïrem el caràcter que determini el nostre contador. Omplirem la resta
        de caselles amb valors nuls. */
        for (int x = 0, contador = 0; x < decodifica[0].length; x++) {
            for (int y = 0; y < decodifica.length; y++) {

                /* Aquesta condició és la que determina si esteim en una casella vàlida. En cas afirmatiu, hi guardam el
                caràcter. En cas contrari, a la casella hi va un nul. */
                if (!(y == fila - 1 && x > (dim - 1) - (dim * fila - s.length()))) {
                    decodifica[y][x] = s.charAt(contador);
                    contador++;
                } else {
                    decodifica[y][x] = '\u0000';
                }
            }
        }

        // Retornam la nostra array decodificada per transposició
        return decodifica;
    }

    /* Aquest mètode agafa com a paràmetres String s que conté el missatge xifrat, l'int dim que és en realitat el
    key.length() i la String key que conté la clau per desxifrar. És diferent de l'altre mètode per desxifrar perquè ha
    de tenir en compte que el missatge està desordenat segons la clau */
    static char[][] decypherUnsortedToArray(String s, int dim, String key) {
        char[] clau = key.toCharArray();
        Arrays.sort(clau);
        int[] ordre = new int[key.length()];
        int[] ordreInicial = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            for (int contador = 0; contador < key.length(); contador++) {
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
                    decodifica[y][x] = '\u0000';
                }
            }
        }
        return decodifica;
    }

    /* Aquest mètode agafa com a paràmetre l'array 'char[][] codifica' que hem creat amb cypherToArray(), o sigui,
    transposada, però després la xifra canviant els valors de columna segons l'ordre alfabètic determinat pels
    caràcters de la clau */
    static char[][] unSort(char[][] codifica, String key) {

        // Cream una array i hi guardam els caràcters de la clau
        char[] desordenat = key.toCharArray();

        // Ordenam aquests caràcters per ordre alfabètic amb Arrays.sort()
        Arrays.sort(desordenat);

        // Cream una array d'enters que contindrà l'ordre de les columnes
        int[] ordre = new int[codifica[0].length];

        // Cream l'array bidimensional de chars per guardar els caràcters desordenats
        char[][] xifrat = new char[codifica.length][codifica[0].length];

        // Amb aquest bucle fixarem l'ordre corresponent de les columnes
        for (int i = 0; i < xifrat[0].length; i++) {
            for (int contador = 0; contador < key.length(); contador++) {

                /* Si la posició de i a la clau és igual a la posició contador de l'array desordenat assignarem el valor
                contador a la posició i de l'array ordre. Eliminam el caràcter de la posició contador en haver-lo
                utilitzat */
                if (key.charAt(i) == desordenat[contador]) {
                    ordre[i] = contador;
                    desordenat[contador] = '\u0000';
                    break;
                }
            }
        }

        /* En aquest bucle col·locam els valors de l'array codifica dins l'array xifrat segons l'ordre obtingut al bucle
        anterior */
        for (int x = 0, contador = 0; x < codifica[0].length; x++) {
            for (int y = 0; y < codifica.length; y++) {
                xifrat[y][ordre[contador]] = codifica[y][x];
            }
            contador++;
        }

        // Retornam l'array xifrat amb el resultat de desordenar
        return xifrat;
    }

    /* Aquest mètode agafa com a paràmetre l'array 'char[][] decodifica' que hem creat amb decypherUnsortedToArray(),
    transposada i amb les columnes canviades segons la key, i torna a ubicar els valors al seu lloc segons l'ordre
    original dels caràcters de la clau */
    static char[][] Sort(char[][] decodifica, String key) {

        // Cream una array per guardar els caràcters de la clau
        char[] ordenat = key.toCharArray();

        // Ordenam aquesta array alfabèticament
        Arrays.sort(ordenat);

        // Cream una array d'enters que contindrà l'ordre de les columnes
        int[] ordre = new int[decodifica[0].length];

        // Declaram i inicialitzam l'array que contindrà els caràcters ordenats
        char[][] desxifrat = new char[decodifica.length][decodifica[0].length];

        /* Amb aquest bucle establim l'ordre en què es copiaran els valors i esborrarem els caràcters d'ordenat en
        haver-los fet servir */
        for (int i = 0; i <= desxifrat[0].length - 1; i++) {
            for (int contador = 0; contador < key.length(); contador++) {
                if (key.charAt(i) == ordenat[contador]) {
                    ordre[i] = contador;
                    ordenat[contador] = '\u0000';
                    break;
                }
            }
        }

        /* En aquest bucle col·locam els valors de l'array decodifica dins l'array desxifrat segons l'ordre obtingut al
        bucle anterior de forma transposada */
        for (int x = 0; x < decodifica[0].length; x++) {
            for (int y = 0; y < decodifica.length; y++) {
                desxifrat[y][x] = decodifica[y][ordre[x]];
            }
        }

        // Retornam l'array desxifrat transposat per a la seva extracció
        return desxifrat;
    }

    /* Aquest mètode l'hem implementat per evitar la repetició de codi. La seva funció és extreure els caràcters dels
    arrays xifrats i guardar-los en una String resultat */
    static String extractCypher(char[][] codifica) {

        // Declaram i inicialitzam l'String on guardarem el resultat
        String resultat = "";

        // Recorrem les columnes, agafant cadascun dels elements que contenen i afegint-los al nostre String
        for (int i = 0; i < codifica[0].length; i++) {
            for (char[] aCodifica : codifica) {

                // Només s'incorporen a l'String resultat els valors de les caselles que no contenen un null.
                if (aCodifica[i] != '\u0000') resultat += aCodifica[i];
            }
        }

        // Retornam l'String resultat completat per a la seva comparació amb el joc de proves
        return resultat;
    }

    // Aquest mètode fa exactament el mateix que l'anterior, però recorreguent l'array a la inversa
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
