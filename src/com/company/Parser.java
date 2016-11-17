package com.company;

import java.util.Stack;

public class Parser {

    public Stack<String> parseExpression(String inputExpression) {
        Stack<String> exitStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        String[] expression = inputExpression.split("(?<=\\G.{1})");
        MathOperation operation = new MathOperation();
        String symb = "";

        //добавить возможность распознавания double
        for (int i = 0; i < expression.length; i++) {
            if (expression[i].matches("[0-9]*")) {
                symb += expression[i];
            } else {
                exitStack.push(symb);
                symb = "";
                if (operation.mapOperations.containsKey(expression[i])) {
                    if (!operatorStack.empty()) {
                        if (operation.mapOperations.get(operatorStack.peek()).getPriority() >
                                operation.mapOperations.get(expression[i]).getPriority()) {
                            while (!operatorStack.empty()) {
                                exitStack.push(operatorStack.pop());
                            }
                            operatorStack.push(expression[i]);
                        } else {
                            operatorStack.push(expression[i]);
                        }
                    } else {
                        operatorStack.push(expression[i]);
                    }
                } else {
                    System.out.print("Недопустимая операция в выражении! Вызов справки: -h");
                }
            }
        }
        if (symb != "") {
            exitStack.push(symb);
        }
        while (!operatorStack.empty()) {
            exitStack.push(operatorStack.pop());
        }
        return exitStack;
    }
}
