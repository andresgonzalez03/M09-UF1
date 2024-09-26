

public class Rot13 {
    private static final String abecedari = "a,à,á,b,c,ç,d,e,è,é,f,g,h,i,í,ï,j,k,l,m,n,ñ,o,ò,ó,p,q,r,s,t,u,ú,ü,v,w,x,y,z";
    String[] abecedariMinuscules = abecedari.toLowerCase().split(",");
    String[] abecedariMajuscules = abecedari.toUpperCase().split(",");


    public String xifraRot13(String cadena) {
        String cadenaCodificada = "";
        for(int i = 0; i < cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) {
                if(Character.isUpperCase(cadena.charAt(i))) {
                    for(int j=0; j<abecedariMajuscules.length; j++) {
                        if(abecedariMajuscules[j].equals(Character.toString(cadena.charAt(i)))) {
                            cadenaCodificada = cadenaCodificada + abecedariMajuscules[(j+13)%abecedariMajuscules.length];
                        }
                    }
                } else {
                    for(int j = 0; j < abecedariMinuscules.length; j++) {
                        if(abecedariMinuscules[j].equals(Character.toString(cadena.charAt(i)))) {
                            cadenaCodificada = cadenaCodificada + abecedariMinuscules[(j+13)%abecedariMinuscules.length];
                        }
                    }
                }
            }
        }
        return cadenaCodificada;
    }
    public String desxifraRot13(String cadena) {
        String cadenaCodificada = "";
        for(int i = 0; i < cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) {
                if(Character.isUpperCase(cadena.charAt(i))) {
                    for(int j=0; j<abecedariMajuscules.length; j++) {
                        if(abecedariMajuscules[j].equals(Character.toString(cadena.charAt(i)))) {
                            cadenaCodificada = cadenaCodificada + abecedariMajuscules[Math.abs(j-13)%abecedariMajuscules.length];
                        }
                    }
                } else {
                    for(int j = 0; j < abecedariMinuscules.length; j++) {
                        if (abecedariMinuscules.length < 36) break;
                        if(abecedariMinuscules[j].equals(Character.toString(cadena.charAt(i)))) {
                            cadenaCodificada = cadenaCodificada + abecedariMinuscules[Math.abs(j-13)%abecedariMinuscules.length];
                        }
                    }
                }
            }
        }
        return cadenaCodificada;
    }
}