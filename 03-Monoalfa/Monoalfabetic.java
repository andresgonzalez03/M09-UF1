import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    private static final char[] ALFABET = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜXYZ".toCharArray();
    public static void main(String args[]) {
        System.out.println(permutaAlfabet(ALFABET));
        System.out.println(Arrays.toString(permutaAlfabet(ALFABET)));
    }

    public static char[] permutaAlfabet(char[] alfabet) {
        char[] alfabetPermutat = new char[alfabet.length];
        List<Character> permutat = Arrays.asList(convertirACharacter(alfabet));
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
    public String xifraMonoAlfa(String cadena) {
        StringBuilder sb = new StringBuilder();
        char[] cadenaArray =  permutaAlfabet(cadena.toCharArray());
        for(int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if(Character.isLetter(c)) {
                sb.append(Character.toUpperCase(cadenaArray[i]));
                
            } else {
                sb.append(c);
            }
        }
        for(int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if(Character.isLetter(c)) {
                sb.append(Character.toLowerCase(cadenaArray[i]));
                
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    } 
}
