package encryptdecrypt;

public class AlgorithmFactory {
    public Algorithm makeAlgorithm(AlgorithmList type, String data) {
        switch (type) {
            case UNICODE:
                return new Unicode(data);
            case SHIFT:
                return new Shift(data);
            default:
                return null;
        }
    }
}
