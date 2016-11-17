package com.company;

public class Main {

    public static void main(String[] args) {
        Parser sm = new Parser();
        ReversePolishNotation rpn = new ReversePolishNotation();
        rpn.calculate(sm.parseExpression("5*3+7-10/2+3"));
    }
}
