import java.util.*;

public class Monoalfabetic {
    private static final char[] ALFABET = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVWXYZ".toCharArray();
    private static char[] alfabetPermutat;
    public static void main(String args[]) {
        String missatge = "Hola, como estás me llamo andrés.";
        String missatgePermutat = xifraMonoAlfa(missatge);
        System.out.println("Missatge xifrat: " + missatgePermutat);
        System.out.println("Missatge desxifrat: " + desxifraMonoAlfa(missatgePermutat));
        String missatge2 = "?????? qué haces??? visca barça";
        String missatgePermutat2 = xifraMonoAlfa(missatge2);
        System.out.println("Missatge xifrat: " + missatgePermutat2);
        System.out.println("Missatge desxifrat: " + desxifraMonoAlfa(missatgePermutat2));
        String missatge3 = "667392232 ese es mi número de teléfono.";
        String missatgePermutat3 = xifraMonoAlfa(missatge3);
        System.out.println("Missatge xifrat: " + missatgePermutat3);
        System.out.println("Missatge desxifrat: " + desxifraMonoAlfa(missatgePermutat3));
        String missatge4 = "Me encantan los buñuelos de bacalao; A mi también jajajaja :))";
        String missatgePermutat4 = xifraMonoAlfa(missatge4);
        System.out.println("Missatge xifrat: " + missatgePermutat4);
        System.out.println("Missatge desxifrat: " + desxifraMonoAlfa(missatgePermutat4));
        String missatge5 = "àáááááä probando letras ññññçççç pingüino ñandú";
        String missatgePermutat5 = xifraMonoAlfa(missatge5);
        System.out.println("Missatge xifrat: " + missatgePermutat5);
        System.out.println("Missatge desxifrat: " + desxifraMonoAlfa(missatgePermutat5));
        String missatge6 = "para instalar un .deb tienes que hacer: sudo dpkg -i package_file.deb";
        String missatgePermutat6= xifraMonoAlfa(missatge6);
        System.out.println("Missatge xifrat: " + missatgePermutat6);
        System.out.println("Missatge desxifrat: " + desxifraMonoAlfa(missatgePermutat6));
        String missatge7 = "linga guli guli guli wacha lingan gu lingan gu";
        String missatgePermutat7 = xifraMonoAlfa(missatge7);
        System.out.println("Missatge xifrat: " + missatgePermutat7);
        System.out.println("Missatge desxifrat: " + desxifraMonoAlfa(missatgePermutat7));

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

