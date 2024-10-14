import java.util.*;

public class Polialfabetic {
    private static final int clauSecreta = 328972;
    private static final char[] ALFABET = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVWXYZ".toCharArray();
    private static Random random;
    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
        "Test 02 Taüll, DÍA, año",
        "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
        
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
        System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
        
        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        String msg = desxifraPoliAlfa(msgsXifrats[i]);
        System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
    public static void initRandom(int clauSecreta) {
       random = new Random(clauSecreta);
    }
    private static char[] permutaAlfabet(char[] alfabet) {
        char[] alfabetPermutat = new char[alfabet.length];
        List<Character> permutat = new ArrayList<>(Arrays.asList(convertirACharacter(alfabet)));
        Collections.shuffle(permutat, random);
        for (int j = 0; j < permutat.size(); j++) {
            alfabetPermutat[j] = permutat.get(j);
        }
        return alfabetPermutat;
    }
    private static Character[] convertirACharacter(char[] alfabet) {
        Character alfabetCharacter[] = new Character[alfabet.length];
        for (int i = 0; i< alfabet.length; i++) {
            alfabetCharacter[i] = alfabet[i];
        }
        return alfabetCharacter;  
    }
    public static String xifraPoliAlfa(String cadena) {
        initRandom(clauSecreta);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            char [] alfabetPermutat = permutaAlfabet(ALFABET);
            if(Character.isLetter(c)) {
                for(int j = 0; j<ALFABET.length; j++) {
                    if(ALFABET[j]==Character.toUpperCase(c)) {
                        if(Character.isUpperCase(c)) {
                            sb.append(Character.toUpperCase(alfabetPermutat[j]));
                        } else {
                            sb.append(Character.toLowerCase(alfabetPermutat[j]));
                        }
                        break;
                    }
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static String desxifraPoliAlfa(String cadena) {
        initRandom(clauSecreta);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            char[] alfabetPermutat = permutaAlfabet(ALFABET);
            if (Character.isLetter(c)) {
                for (int j = 0; j < alfabetPermutat.length; j++) {
                    if (alfabetPermutat[j] == Character.toUpperCase(c)) {
                        if (Character.isUpperCase(c)) {
                            sb.append(ALFABET[j]);
                        } else {
                            sb.append(Character.toLowerCase(ALFABET[j]));
                        }
                        break;
                    }
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }        
}