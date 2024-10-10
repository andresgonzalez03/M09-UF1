import java.util.*;

public class Monoalfabetic {
    private static final char[] ALFABET = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVWXYZ".toCharArray();
    private static char[] alfabetPermutat;
    public static void main(String args[]) {
        String[] prova = {"Hola, como estás me llamo andrés.", "?????? qué haces??? visca barça", "667392232 ese es mi número de teléfono.", 
                        "Me encantan los buñuelos de bacalao; A mi también jajajaja :))","àáááááä probando letras ññññçççç pingüino ñandú",
                        "para instalar un .deb tienes que hacer: sudo dpkg -i package_file.deb","linga guli guli guli wacha lingan gu lingan gu"};
        for (String s : prova) {
            System.out.println("Missatge xifrat: " + xifraMonoAlfa(s));
            System.out.println("Missatge desxifrat: " + desxifraMonoAlfa(xifraMonoAlfa(s)));
        }
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
        char[] cadenaNormal = cadena.toCharArray();
        alfabetPermutat = permutaAlfabet(ALFABET);
        // for per recórrer el String
        for (char c : cadenaNormal) {
            if (Character.isLetter(c)) {
                // recórrer per trobar el caracter permutat
                for (int i = 0; i < ALFABET.length; i++) {
                    if(ALFABET[i] == Character.toUpperCase(c)) {
                        if (Character.isUpperCase(c)) {
                            sb.append(Character.toUpperCase(alfabetPermutat[i]));
                        }  else {
                            sb.append(Character.toLowerCase(alfabetPermutat[i]));
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
    public static String desxifraMonoAlfa(String cadena) {
        StringBuilder sb = new StringBuilder();
        char[] cadenaNormal = cadena.toCharArray();
        for (char c: cadenaNormal) {
            if (Character.isLetter(c)) {
                for (int i = 0; i < alfabetPermutat.length; i++) {
                    if (alfabetPermutat[i] == Character.toUpperCase(c)) {
                        if (Character.isUpperCase(c)) {
                            sb.append(ALFABET[i]);
                        } else {
                            sb.append(Character.toLowerCase(ALFABET[i]));
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

