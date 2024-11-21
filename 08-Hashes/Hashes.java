import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public int npass = 0;
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");
        
            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();
        
            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        String concatPwSalt = pw + salt;
        byte[] concat = concatPwSalt.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(concat);
        byte[] hash = md.digest();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    }
    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        int iteracions = 65536;
        int longitud = 128;
        char[] passwd = pw.toCharArray();
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
        PBEKeySpec spec = new PBEKeySpec(passwd, saltBytes, iteracions, longitud);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    }
    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        char[] charset = "abcdefABCDEF1234567890!".toCharArray();
        char[] password = new char[6];  
        int longitudMax = 6;  
        for (int longitud = 1; longitud <= longitudMax; longitud++) {
            for (int i1 = 0; i1 < charset.length; i1++) {
                password[0] = charset[i1];  
                if (longitud > 1) {
                    for (int i2 = 0; i2 < charset.length; i2++) {
                        password[1] = charset[i2]; 
                        if (longitud > 2) {
                            for (int i3 = 0; i3 < charset.length; i3++) {
                                password[2] = charset[i3]; 
                                if (longitud > 3) {
                                    for (int i4 = 0; i4 < charset.length; i4++) {
                                        password[3] = charset[i4];  
                                        if (longitud > 4) {
                                            for (int i5 = 0; i5 < charset.length; i5++) {
                                                password[4] = charset[i5];  
                                                if (longitud > 5) {
                                                    for (int i6 = 0; i6 < charset.length; i6++) {
                                                        password[5] = charset[i6]; 
                                                        if (verificarHash(password, longitud, alg, hash, salt)) {
                                                            return new String(password, 0, longitud);  
                                                        }
                                                    }
                                                } else {
                                                    if (verificarHash(password, longitud, alg, hash, salt)) {
                                                        return new String(password, 0, longitud);  
                                                    }
                                                }
                                            }
                                        } else {
                                            if (verificarHash(password, longitud, alg, hash, salt)) {
                                                return new String(password, 0, longitud);  
                                            }
                                        }
                                    }
                                } else {
                                    if (verificarHash(password, longitud, alg, hash, salt)) {
                                        return new String(password, 0, longitud);  
                                    }
                                }
                            }
                        } else {
                            if (verificarHash(password, longitud, alg, hash, salt)) {
                                return new String(password, 0, longitud);  
                            }
                        }
                    }
                } else {
                    if (verificarHash(password, longitud, alg, hash, salt)) {
                        return new String(password, 0, longitud);  
                    }
                }
            }
        }
        System.out.println("No se encontró la contraseña.");
        return null;
    }  
    private boolean verificarHash(char[] password, int longitud, String alg, String hash, String salt) throws Exception {
        String pw = new String(password, 0, longitud);
        String newHash = null;
        if (alg.equals("SHA-512")) {
            newHash = getSHA512AmbSalt(pw, salt);
        } else if (alg.equals("PBKDF2")) {
            newHash = getPBKDF2AmbSalt(pw, salt);
        }
        npass++;
        return newHash.equals(hash);
    }
    
    
    
    public String getInterval(long t1, long t2) {
        long interval = t2 - t1;
        long dias = interval / (1000* 60 * 60 *24);
        long hores = (interval / (1000* 60 * 60)) % 24;
        long minuts = (interval / (1000 *60)) % 60;
        long segons = (interval / 1000) % 60;
        long milisegons = interval % 1000;
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dias, hores, minuts, segons, milisegons);
    }
}