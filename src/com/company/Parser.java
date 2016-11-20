package com.company;

import java.util.Stack;

public class Parser {

    public Stack<String> parseExpression(String inputExpression) {
        Stack<String> exitStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        MathOperation operation = new MathOperation();
        String symb = "";

        String[] expression = inputExpression.split("(?<=\\G.{1})");

        for (int i = 0; i < expression.length; i++) {
            if (expression[i].matches("[0-9.Ee]")) {
                symb += expression[i];
            } else {
                if (expression[i].matches("[+-]") && i != 0 && expression[i - 1].matches("[eE]")) {
                    symb += expression[i];
                } else {
                    if (symb != "") {
                        exitStack.push(symb);
                    }
                    symb = "";
                    if (operation.mapOperations.containsKey(expression[i])) {
                        if (!operatorStack.empty()) {
                            if (operation.mapOperations.get(operatorStack.peek()).getPriority() >
                                    operation.mapOperations.get(expression[i]).getPriority()) {
                                while (!operatorStack.empty()) {
                                    if (!operatorStack.peek().equals("(")) {
                                        exitStack.push(operatorStack.pop());
                                    } else {
                                        operatorStack.pop();
                                    }
                                }
                                if (!expression[i].equals(")")) {
                                    operatorStack.push(expression[i]);
                                }
                            } else {
                                operatorStack.push(expression[i]);
                            }
                        } else {
                            operatorStack.push(expression[i]);
                        }
                    } else {
                        System.out.print("Недопустимая операция!");
                    }
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
