
public class Main {
    public static void main(String[] args) {
        String prueba1 = "hola";
        Rot13 rot13 = new Rot13();
        System.out.println(rot13.xifraRot13(prueba1));
        System.out.println(rot13.desxifraRot13("pxúi"));
    }
}
