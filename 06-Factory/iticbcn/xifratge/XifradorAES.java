package iticbcn.xifratge;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {
    public String ALGORISME_XIFRAT = "AES";
    public String ALGORISME_HASH = "SHA-256";
    public String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private int MIDA_IV= 16;
    byte[] iv = new byte[MIDA_IV];
    private String CLAU = "ameisin";
    
    public byte[] xifraAES(String msg, String clau) throws Exception {
        //Obtenir els bytes de l’String
        byte[] missatgeBytes =  msg.getBytes();
        // Genera IvParameterSpec
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec spec =  new IvParameterSpec(iv);
        // Genera hash
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(CLAU.getBytes());
        byte[] clauByte = new byte[16];
        System.arraycopy(hash,0,clauByte,0,clauByte.length);
        // Encrypt.
        SecretKeySpec key = new SecretKeySpec(clauByte, ALGORISME_XIFRAT);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] MissatgeXifrat = cipher.doFinal(missatgeBytes);
        // Combinar IV i part xifrada.
        byte[] IvIMissatge = new byte[MIDA_IV + MissatgeXifrat.length];
        System.arraycopy(iv, 0, IvIMissatge, 0, iv.length); // afegir el contingut de l'array iv dins del nou array començant per la posició 0
        System.arraycopy(MissatgeXifrat, 0, IvIMissatge, iv.length, MissatgeXifrat.length); // afegir el contigut de l'array del missatge xifrat dins del nou array començant per la posició on acaba el iv, és a dir la longitud d'aquest array iv.
        // return iv+msgxifrat
        return IvIMissatge;
    }

    public String desxifraAES (byte[] bIvIMsgXifrat , String clau) throws Exception {
        // Extreure l'IV.
        byte[] ivExtret = new byte[MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, 0, ivExtret, 0, iv.length);
        IvParameterSpec spec = new IvParameterSpec(ivExtret);
        // Extreure la part xifrada.
        int partXifrada = bIvIMsgXifrat.length - ivExtret.length;
        byte[] byteEncriptados = new byte[partXifrada];
        System.arraycopy(bIvIMsgXifrat, ivExtret.length, byteEncriptados, 0, partXifrada);
        // Fer hash de la clau
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(CLAU.getBytes());
        byte[] clauByte = new byte[16];
        System.arraycopy(hash,0,clauByte,0,clauByte.length);
        SecretKeySpec key = new SecretKeySpec(clauByte, ALGORISME_XIFRAT);
        // Desxifrar.
        Cipher cipherDescriptat = Cipher.getInstance(FORMAT_AES);
        cipherDescriptat.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] descriptat = cipherDescriptat.doFinal(byteEncriptados);
        // return String desxifrat
        return new String(descriptat);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] xifrat = xifraAES(msg, clau);
            return new TextXifrat(xifrat);
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());  
        }   
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            byte[] xifra = xifrat.getBytes();
            return desxifraAES(xifra, clau);
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());
        }
    }
}
