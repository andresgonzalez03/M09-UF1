import java.util.*;

public class Monoalfabetic {
    private static final char[] ALFABET = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜXYZ".toCharArray();
    public static void main(String args[]) {
        System.out.println(permutaAlfabet(ALFABET));
        System.out.println(Arrays.toString(permutaAlfabet(ALFABET)));
        System.out.println(xifraMonoAlfa("Hola"));
    }

    public static char[] permutaAlfabet(char[] alfabet) {
        char[] alfabetPermutat = new char[alfabet.length];
        List<Character> permutat = new ArrayList<>(Arrays.asList(convertirACharacter(alfabet)));
        Collections.shuffle(permutat);
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
    public static String xifraMonoAlfa(String cadena) {
        StringBuilder sb = new StringBuilder();
        char[] cadenaArray =  permutaAlfabet(cadena.toCharArray());
        char[] cadenaNormal = cadena.toCharArray();
        for(int i = 0; i < cadenaNormal.length; i++) {
            char c = cadenaNormal[i];
            if(Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    sb.append(Character.toUpperCase(cadenaArray[i]));
                } else {
                    sb.append(Character.toLowerCase(cadenaArray[i]));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    } 
    public static String desxifraMonoAlfa(String cadena) {
        StringBuilder sb = new StringBuilder();
        char[] cadenaArray =  permutaAlfabet(cadena.toCharArray());
        char[] cadenaNormal = cadena.toCharArray();
        for(int i = 0; i<cadena.length(); i++) {
            char c = cadenaNormal[i];
            if(Character.isLetter(c)) {
                char descifrat = ' ';
                for (int j = 0; j < cadenaArray.length; j++) {
                    char d = cadenaArray[j];
                    if (Character.toUpperCase(c) == Character.toUpperCase(d)) {
                        if (Character.isUpperCase(c)) {
                            descifrat = d;
                        } else {
                            descifrat = Character.toLowerCase(d);
                        }
                        break;
                    }
                }
                sb.append(descifrat);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

