package iticbcn.xifratge;
import java.util.*;

public class XifradorMonoalfabetic implements Xifrador {
    private final char[] ALFABET = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVWXYZ".toCharArray();
    private char[] alfabetPermutat;
    
    public XifradorMonoalfabetic() {
        this.alfabetPermutat = null;
    }
    public XifradorMonoalfabetic(char[] alfabet) {
        alfabetPermutat = permutaAlfabet(alfabet);
    }
    
    public char[] permutaAlfabet(char[] alfabet) {
        char[] alfabetPermutat = new char[alfabet.length];
        List<Character> permutat = new ArrayList<>(Arrays.asList(convertirACharacter(alfabet)));
        Collections.shuffle(permutat);
        for (int j = 0; j < permutat.size(); j++) {
            alfabetPermutat[j] = permutat.get(j);
        }
        return alfabetPermutat;
    }

    private Character[] convertirACharacter(char[] alfabet) {
        Character alfabetCharacter[] = new Character[alfabet.length];
        for (int i = 0; i< alfabet.length; i++) {
            alfabetCharacter[i] = alfabet[i];
        }
        return alfabetCharacter;  
    }
    public String xifraMonoAlfa(String cadena) {
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
    public String desxifraMonoAlfa(String cadena) {
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
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if(clau!=null) { throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");}
        String xifrat =  xifraMonoAlfa(msg);
        return new TextXifrat(xifrat);
    }
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if(clau!=null) { throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");}
        String xifra = desxifraMonoAlfa(xifrat.toString());
        return xifra;
    }
}

