package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
        Parser sm = new Parser();
        ReversePolishNotation rpn = new ReversePolishNotation();
        rpn.calculate(sm.parseExpression("54*2+10/2-3"));
    }
}
