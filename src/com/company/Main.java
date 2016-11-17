package com.company;

public class Main {

    public static void main(String[] args) {
        Parser p = new Parser();
        ReversePolishNotation rpn = new ReversePolishNotation();
        System.out.print("5*3+7-10/2+3 = ");
        rpn.calculate(p.parseExpression("5*3+7-10/2+3"));
        System.out.print("\n23E-1+0.7 = ");
        rpn.calculate(p.parseExpression("23E-1+0.7"));
        System.out.print("\n200e1/5 = ");
        rpn.calculate(p.parseExpression("200e1/5"));
        System.out.print("\n(2+2)*2 = ");
        rpn.calculate(p.parseExpression("(2+2)*2"));
    }
}
