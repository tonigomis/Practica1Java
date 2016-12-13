public class Vigenere {
    /* Declaram la constant estàtica alfa, que contindrà el número de lletres que composen l'alfabet que farem servir
    (26 en aquest cas), i que si és necessari pot ser modificada sense haver de retocar tot el codi */
    static final int alfa = 26;

    /* En aquesta primera funció hem de codificar un String s que ens passen per paràmetre utilitzant una substitució
    polialfabètica que ens ve donada per una clau (String password). Després d'haver desenvolupat tot el mètode he optat
    per centralitzar tota l'operació en un sol mètode anomenat solve, que serà el que retornarem aquí amb l'String s,
    l'String password i un valor booleà vertader */
    static String encode(String s, String password) {
        return solve(s, password, true);
    }

    /* En aquesta segona funció hem de decodificar un String s que ens passen per paràmetre utilitzant una substitució
    polialfabètica que ens ve donada per una clau (String password). Després d'haver desenvolupat tot el mètode he optat
    per centralitzar tota l'operació en un sol mètode anomenat solve, que serà el que retornarem aquí amb l'String s,
    l'String password i un valor booleà fals */
    static String decode(String s, String password) {
        return solve(s, password, false);
    }

    /* La funció solve agafa com a paràmetres String s, que conté el missatge original, String password, que conté la
    clau, i un booleà whatToDo que determina si esteim codificant o decodificant. Aquest mètode fa ara la tasca que
    feien la funció encode i decode d'aquesta classe */
    static String solve(String s, String password, boolean whatToDo) {
        /* El primer de tot, normalitzam la cadena de text a xifrar per treballar-hi més còmodament (Veure explicació
        del mètode al final de la classe) */
        s = normalitza(s);

        // Feim el mateix amb la password a fi d'uniformitzar els caràcters amb què hem de treballar
        password = normalitza(password);

        //Declaram una String buida per guardar el resultat obtingut
        String resultat = "";

        /* Cream un bucle per recórrer tota la cadena de text i avaluar-ne les condicions, declaram un comptador per a
        la posició de la password */
        for (int i = 0, contPass = 0; i < s.length(); i++) {

            /* Hem creat un mètode booleà anomenat esEspecial que ens retornarà vertader sempre que el caràcter a estudi
            estigui fora dels límits de 'A' a 'Z' a la taula ASCII. Quan esEspecial és vertader afegirem directament el
            caràcter a la nostra String codificat i tornarem a entrar al bucle */
            if (esEspecial(s.charAt(i))) {
                resultat += s.charAt(i);
                continue;
            }
            /* Aquí determinam si esteim codificant o decodificant, segons el valor booleà que li passam a la funció
            encode (true) i la funció decode (false) */
            if (whatToDo) {
                /* Si el caràcter no és especial l'afegim al nostre resultat després de sumar el valor alfabètic (ASCII - 64)
                del caràcter de s i password que corresponen, fent mòdul del resultat amb alfa si el seu valor és major que
                26 i sumant-li 64 per tornar a tenir el valor ASCII correcte */
                resultat += passaChar(passaNum(s.charAt(i)) + passaNum(password.charAt(contPass)));
            } else {
                /* El mateix que a la sentència anterior, però restant els valors de s i de password ja que esteim
                decodificant */
                resultat += passaChar(passaNum(s.charAt(i)) - passaNum(password.charAt(contPass)));
            }
            // Incrementam el comptador contPass, que és el que feim servir per control·lar la llargada de la clau
            contPass++;

            /* Feim reset al comptador contPass si aquest arriba a la seva darrera posició perquè torni a començar per continuar
            codificant la String s */
            if (contPass == password.length()) contPass = 0;
        }
        return resultat;
    }

    /* La funció booleana esEspecial té com a única funció retornar-nos vertader si el caràcter a avaluar està fora del
    rang del nostre alfabet ('A' -- 65 fins a 'Z' -- 90) i fals en cas contrari */
    static boolean esEspecial(char w) {
        return (w < 65 || w > 90);
    }

    /* La funció passaNum serveix per restar 64 al valor ASCII del caràcter a considerar a fi de simplificar les
    operacions que hi haurem de fer tant per xifrar com per desxifrar */
    static int passaNum(char c) {
        return c - 64;
    }

    /* La funció passaChar avalua si el número que esteim considerant és major que alfa (si és així, li fa el mòdul per
    obtenir el valor correcte) o si és igual o inferior a 0 (li suma alfa). Ens retorna el caràcter resultant de sumar
    64 al número analitzat */
    static char passaChar(int r) {
        if (r > alfa) r %= alfa;
        if (r <= 0) r += alfa;
        return (char) (r + 64);
    }

    /* Aquesta funció avalua el contingut de l'String que li passam per paràmetre i el simpifica, transcrivint espais
    en blanc i símbols de puntuació, passant les minúscules (si existeixen) a majúscules i convertint les vocals
    accentuades en vocals majúscules i sense accents */
    static String normalitza(String s) {
        char caracter;
        String normalitzat = "";
        for (int i = 0; i < s.length(); i++) {
            caracter = s.charAt(i);
            if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                normalitzat += caracter;
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
            normalitzat += caracter;
        }
        return normalitzat;
    }
}