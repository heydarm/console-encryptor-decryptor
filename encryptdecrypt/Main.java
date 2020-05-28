package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static AlgorithmList alg = AlgorithmList.SHIFT;
    private static String data = "";
    private static String inPath = "";
    private static String outPath = "";
    private static int operation = 1; // for enc (1); for dec (-1)
    private static int offset = 0;

    public static void main(String[] args) {
        setAllParameters(args);
        checkData();
        start();
    }

    public static void setAllParameters(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                    operation = args[i + 1].equals("dec") ? -1 : args[i + 1].equals("enc") ? 1 : 0;

                    if (operation == 0) {
                        System.out.println("Error. There is no such operation.");
                        return;
                    }
                    break;
                case "-alg":
                    alg = args[i + 1].equals("unicode") ? AlgorithmList.UNICODE
                            : args[i + 1].equals("shift") ? AlgorithmList.SHIFT
                            : null;

                    if (alg == null) {
                        System.out.println("Error. There is no such algorithm.");
                        return;
                    }
                    break;
                case "-key":
                    offset = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-out":
                    outPath = args[i + 1];
                    break;
                case "-in":
                    inPath = args[i + 1];
                    break;
            }
        }
    }

    private static void checkData() {
        if (!inPath.isBlank()) {
            try (Scanner scanner = new Scanner(new File(inPath))) {
                data = data.isEmpty() ? scanner.nextLine() : data;
            } catch (Exception e) {
                System.out.printf("Error: %s\n", e.getMessage());
            }
        }
    }

    private static void start() {
        String output = "";

        AlgorithmFactory algorithmFactory = new AlgorithmFactory();
        Algorithm algorithm = algorithmFactory.makeAlgorithm(alg, data);

        if (operation == 1) {
            output = algorithm.encrypt(offset);
        } else {
            output = algorithm.decrypt(offset);
        }

        if (!outPath.isBlank()) {
            writeDataToFile(output);
        } else {
            System.out.println(output);
        }
    }

    private static void writeDataToFile(String data) {
        try (FileWriter fileWriter = new FileWriter(outPath)) {
            fileWriter.write(data);
        } catch (IOException e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }
}