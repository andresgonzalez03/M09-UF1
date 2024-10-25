package iticbcn.xifratge;

public class ClauNoSuportada extends Exception {
    public ClauNoSuportada() {
        super("Aquesta clau no està suportada");
    }
    public ClauNoSuportada(String missatge) {
        super(missatge);
    }
}
