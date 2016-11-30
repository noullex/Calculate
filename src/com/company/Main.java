package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Parser p = new Parser();
        ReversePolishNotation rpn = new ReversePolishNotation();
        /*File f = new File("info.txt");
        try (FileReader reader = new FileReader(f)) {
            char[] buffer = new char[(int) f.length()];
            reader.read(buffer);
            System.out.println(new String(buffer));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.next();*/
        rpn.calculate(p.parseExpression("(6+10-4)/(1+1*2)+1"));
    }
}
