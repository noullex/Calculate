package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File f = new File("info.txt");
        String helpInfo = "";
        try (FileReader reader = new FileReader(f)) {
            char[] buffer = new char[(int) f.length()];
            reader.read(buffer);
            helpInfo = new String(buffer);
            System.out.println(helpInfo);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }

        ReversePolishNotation rpn = new ReversePolishNotation();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String expression = scanner.next();
            if (expression.equals("-q")) {
                System.exit(0);
            } else if (expression.equals("-h")) {
                System.out.print(helpInfo);
            } else {
                rpn.calculate(expression);
            }
        }
    }
}
