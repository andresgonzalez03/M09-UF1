package iticbcn.xifratge;
public class XifradorRotX implements Xifrador {
    private final char[] ALFABET_MAJ = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜXYZ".toCharArray();
    private final char[] ALFABET_MIN = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜXYZ".toLowerCase().toCharArray();

    
    public String xifraRotX(String cadena, int desplaçament) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i  < cadena.length(); i++) {
            if(Character.isLetter(cadena.charAt(i))) {
                if(Character.isUpperCase(cadena.charAt(i))) {
                    for (int j = 0; j < ALFABET_MAJ.length; j++) {
                        if (ALFABET_MAJ[j] ==  cadena.charAt(i)) {
                            int index = (j + desplaçament) % ALFABET_MAJ.length;
                            sb.append(ALFABET_MAJ[index]);
                            break;
                        } 
                    }
                } else if(Character.isLowerCase(cadena.charAt(i))) {
                    for(int j = 0; j < ALFABET_MIN.length; j++) {
                        if(ALFABET_MIN[j] == cadena.charAt(i)) {
                            int index = (j + desplaçament) % ALFABET_MIN.length;
                            sb.append(ALFABET_MIN[index]);
                            break;
                        }
                    }
                }
             } else {
                sb.append(cadena.charAt(i));
            }
        }
        return sb.toString();
    }
                
    public String desxifraRotX(String cadena, int desplaçament) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i  < cadena.toString().length(); i++) {
            if(Character.isLetter(cadena.toString().charAt(i))) {
                if(Character.isUpperCase(cadena.toString().charAt(i))) {
                    for (int j = 0; j < ALFABET_MAJ.length; j++) {
                        if (ALFABET_MAJ[j] ==  cadena.toString().charAt(i)) {
                            int index = (j - desplaçament) % ALFABET_MAJ.length;
                            if(index < 0) {
                                index += ALFABET_MAJ.length;
                            }
                            sb.append(ALFABET_MAJ[index]);
                            break;
                        } 
                    }
                } else if(Character.isLowerCase(cadena.toString().charAt(i))) {
                    for(int j = 0; j < ALFABET_MIN.length; j++) {
                        if(ALFABET_MIN[j] == cadena.toString().charAt(i)) {
                            int index = (j - desplaçament) % ALFABET_MIN.length;
                            if(index < 0) {
                                index += ALFABET_MIN.length;
                            }
                            sb.append(ALFABET_MIN[index]);
                            break;
                        }
                    }
                }
             } else {
                sb.append(cadena.toString().charAt(i));
            }
        }
        return sb.toString();
    }
    public void forcaBrutaRotX(String cadenaXifrada) {
        for(int i = 1; i <= ALFABET_MIN.length; i++) {
            String sb = desxifraRotX(cadenaXifrada, i);
            System.out.println("El desplaçament " + i + " és: " + sb);
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
       try {
        int desplaçament = Integer.parseInt(clau);
        if(desplaçament < 0 || desplaçament > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        return new TextXifrat(xifraRotX(msg, desplaçament));
       } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
       }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            int desplaçament = Integer.parseInt(clau);
            if(desplaçament < 0 || desplaçament > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            String xifra = desxifraRotX(xifrat.toString(), desplaçament);
            return xifra;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }
}