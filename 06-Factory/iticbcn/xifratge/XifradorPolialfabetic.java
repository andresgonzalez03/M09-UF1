package iticbcn.xifratge;
import java.util.*;

public class XifradorPolialfabetic implements Xifrador {
    private char[] ALFABET = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVWXYZ".toCharArray();
    private Random random;
    private long clauSecreta;
    
    public Random initRandom(long clauSecreta) {
       random = new Random(clauSecreta);
       return random;
    }
    private char[] permutaAlfabet(char[] alfabet) {
        char[] alfabetPermutat = new char[alfabet.length];
        List<Character> permutat = new ArrayList<>(Arrays.asList(convertirACharacter(alfabet)));
        Collections.shuffle(permutat, random);
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
    public String xifraPoliAlfa(String cadena) {
        initRandom(clauSecreta);
        StringBuilder sb = new StringBuilder();
        char[] cadenaArray = cadena.toCharArray();
        for(char c : cadenaArray) {
            char[] alfabetPermutat = permutaAlfabet(ALFABET);
            if(Character.isLetter(c)) {
                for(int i = 0; i<ALFABET.length; i++) {
                    if(ALFABET[i]==Character.toUpperCase(c)) {
                        if(Character.isUpperCase(c)) {
                            sb.append((alfabetPermutat[i]));
                        } else {
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
    public String desxifraPoliAlfa(String cadena) {
        initRandom(clauSecreta);
        StringBuilder sb = new StringBuilder();
        char[] cadenaNormal = cadena.toCharArray();
        for (char c: cadenaNormal) {
            char[] alfabetPermutat = permutaAlfabet(ALFABET);
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
        long clauNum;
        try {
            clauNum = Long.parseLong(clau);
        } catch (NumberFormatException e) {
           throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        this.clauSecreta = clauNum;
        String missatge = xifraPoliAlfa(msg);
        return new TextXifrat(missatge);

    }
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long clauNum;
        try {
            clauNum = Long.parseLong(clau);
        } catch (NumberFormatException e) {
           throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        this.clauSecreta = clauNum;
        return desxifraPoliAlfa(xifrat.toString());
        
    }        
}