package iticbcn.xifratge;

public class TextXifrat {
    private byte[] textXifrat;

    public TextXifrat(byte[] textXifrat) {
        this.textXifrat = textXifrat;
    }
    public TextXifrat(String missatge) {
        this.textXifrat = missatge.getBytes();
    }
    public byte[] getBytes() {return textXifrat;}
    @Override 
    public String toString() {
        return new String(textXifrat);
    }
}
