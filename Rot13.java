import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
                            cadenaCodificada = cadenaCodificada + abecedariMajuscules[(j-13)%abecedariMajuscules.length];
                        }
                    }
                } else {
                    for(int j = 0; j < abecedariMinuscules.length; j++) {
                        if(abecedariMinuscules[j].equals(Character.toString(cadena.charAt(i)))) {
                            cadenaCodificada = cadenaCodificada + abecedariMinuscules[(j-13)%abecedariMinuscules.length];
                        }
                    }
                }
            }
        }
        return cadenaCodificada;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("Hola, que vols fer, desencriptar un missatge i encriptar-lo? \nEscriu 1 si vols encriptar o 2 si vols desencriptar.");
            String cadena = reader.readLine();
            System.out.println("Perfecte, ara introdueix el teu text:");
            String cadena2 = reader.readLine();
            if(cadena.equals("1")) {
                Rot13 pepe = new Rot13();
                System.out.println(pepe.xifraRot13(cadena2));
            } else if (cadena.equals("2")) {
                Rot13 pepe = new Rot13();
            System.out.println(pepe.desxifraRot13(cadena2));
            }
            if (cadena2.isEmpty() || cadena.isEmpty()) {
                break;
            }
        }
    }
}