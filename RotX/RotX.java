public class RotX {
    private static final char[] ALFABET_MAJ = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜXYZ".toCharArray();
    private static final char[] ALFABET_MIN = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜXYZ".toLowerCase().toCharArray();
    
    public StringBuilder xifraRotX(String cadena, int desplaçament) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i  < cadena.length(); i++) {
            if(Character.isLetter(cadena.charAt(i))) {
                if(Character.isUpperCase(cadena.charAt(i))) {
                    for (int j = 0; j < ALFABET_MAJ.length; j++) {
                        if (ALFABET_MAJ[j] )

                    }
                }
            }
        }
        return sb;
    }   
    public StringBuilder xifraRotX(String cadena, int desplaçament) {

    }
}