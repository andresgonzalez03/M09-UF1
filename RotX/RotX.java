public class RotX {
    private static final char[] ALFABET_MAJ = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜXYZ".toCharArray();
    private static final char[] ALFABET_MIN = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜXYZ".toLowerCase().toCharArray();
    
    public StringBuilder xifraRotX(String cadena, int desplaçament) {
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
        return sb;
    }
                
    public StringBuilder desxifraRotX(String cadena, int desplaçament) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i  < cadena.length(); i++) {
            if(Character.isLetter(cadena.charAt(i))) {
                if(Character.isUpperCase(cadena.charAt(i))) {
                    for (int j = 0; j < ALFABET_MAJ.length; j++) {
                        if (ALFABET_MAJ[j] ==  cadena.charAt(i)) {
                            int index = (j - desplaçament) % ALFABET_MAJ.length;
                            if(index < 0) {
                                index += ALFABET_MAJ.length;
                            }
                            sb.append(ALFABET_MAJ[index]);
                            break;
                        } 
                    }
                } else if(Character.isLowerCase(cadena.charAt(i))) {
                    for(int j = 0; j < ALFABET_MIN.length; j++) {
                        if(ALFABET_MIN[j] == cadena.charAt(i)) {
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
                sb.append(cadena.charAt(i));
            }
        }
        return sb;
    }
    public void forcaBrutaRotX(String cadenaXifrada) {
        for(int i = 1; i <= ALFABET_MIN.length; i++) {
            StringBuilder sb = desxifraRotX(cadenaXifrada, i);
            System.out.println("El desplaçament " + i + " és: " + sb.toString());
        }
    }
    public static void main(String[] args) {
        RotX rotX = new RotX();
        String textAXifrar = "Hola, me llamo Andrés";
        System.out.println(rotX.xifraRotX(textAXifrar, 15));
        String textXifrat = rotX.xifraRotX(textAXifrar, 15).toString();
        System.out.println(rotX.desxifraRotX(textXifrat, 15));
        String textXifrat2 = "Pzùi, ün ùùiüz Ixmcñç";
        rotX.forcaBrutaRotX(textXifrat2);
    }
}