package by.vyun;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {

    private static int lineWidth = 80;
    private static String text = "";
    private static String path;
    private static boolean smartDisplay;
    private static boolean fileInput;

    public static void main(String[] args) {

        //*** example string for run from terminal:
        // java -jar TextView.jar -w 12 input.txt -s
        //***

        try {
            parseArguments(args);
            readText();
            displayText();

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.exit(-2);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-3);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }


    private static void parseArguments(String[] args) throws NumberFormatException {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.equals("-w") && i + 1 != args.length) {    //can't be the last argument without <width> param
                arg = args[++i];
                lineWidth = Integer.parseInt(arg);
                if (lineWidth < 1) {
                    throw new NumberFormatException("Length should be a positive integer value");
                }
            } else if (arg.equals("-s")) {
                smartDisplay = true;
            } else if (new File(arg).isFile()) {
                path = arg;
                fileInput = true;
            }
        }
    }


    private static void readText() throws IOException {
        if (fileInput) {
            StringBuilder stringBuilder = new StringBuilder();
            try (Scanner scanner = new Scanner(new File(path), StandardCharsets.UTF_8)) {
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine().trim()).append(' ');
                }
            }
            text = stringBuilder.toString();
        } else {
            System.out.print("Enter text: ");
            try (Scanner scanner = new Scanner(System.in)) {
                text = scanner.nextLine();
            }
        }
    }


    private static void displayText() {
        if (text.length() <= lineWidth) {
            System.out.println(text);
        } else {
            int startIndex = 0;
            int endIndex = lineWidth;
            if (smartDisplay) {
                while (endIndex <= text.length()) {
                    for (int i = endIndex; i > startIndex; i--) {
                        if (text.charAt(i) == ' ') {
                            endIndex = i + 1;
                            break;
                        }
                    }
                    System.out.println(text.substring(startIndex, endIndex));
                    startIndex = endIndex;
                    endIndex += lineWidth;
                }
            } else {
                while (endIndex <= text.length()) {
                    System.out.println(text.substring(startIndex, endIndex));
                    startIndex = endIndex;
                    endIndex += lineWidth;
                }
            }
            System.out.println(text.substring(startIndex));
        }
    }


}