package encryptdecrypt;

public abstract class Algorithm {
    protected String data;

    // Constructor
    public Algorithm(String data) {
        this.data = data;
    }

    public abstract String encrypt(int offset);

    public abstract String decrypt(int offset);
}
