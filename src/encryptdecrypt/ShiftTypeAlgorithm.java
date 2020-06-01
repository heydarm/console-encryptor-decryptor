package encryptdecrypt;

public abstract class ShiftTypeAlgorithm extends Algorithm {

    // Constructor
    public ShiftTypeAlgorithm(String data) {
        super(data);
    }

    // ==================== Getters and Setters Start ==================== //

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // ==================== Getters and Setters End ==================== //

    protected abstract String en_de_crypt(int offset);
}

class Shift extends ShiftTypeAlgorithm {

    // Constructor
    public Shift(String data) {
        super(data);
    }

    //  A - Z   [65 - 90]
    //  a - z   [97 - 122]

    @Override
    public String encrypt(int offset) {
        return en_de_crypt(offset);
    }

    @Override
    public String decrypt(int offset) {
        return en_de_crypt(26 - offset);
    }

    // offset equals {n} if ENC and {26 - n} if DEC
    @Override
    protected String en_de_crypt(int offset) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char character : data.toCharArray()) {
            if (Character.isUpperCase(character) && character < 123) {
                stringBuilder.append((char) (Math.abs(character + offset - 65) % 26 + 65));
            } else if (Character.isLowerCase(character) && character < 123) {
                stringBuilder.append((char) (Math.abs(character + offset - 97) % 26 + 97));
            } else {
                stringBuilder.append(character);
            }
        }

        return stringBuilder.toString();
    }
}

class Unicode extends ShiftTypeAlgorithm {

    // Constructor
    public Unicode(String data) {
        super(data);
    }

    @Override
    public String encrypt(int offset) {
        return en_de_crypt(offset);
    }

    @Override
    public String decrypt(int offset) {
        return en_de_crypt(-offset);
    }

    @Override
    protected String en_de_crypt(int offset) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            stringBuilder.append((char) (data.charAt(i) + offset));
        }

        return stringBuilder.toString();
    }
}