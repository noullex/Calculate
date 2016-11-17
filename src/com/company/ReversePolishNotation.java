package com.company;

import java.util.Stack;

public class ReversePolishNotation {

    public void calculate(Stack<String> inputStack) {
        Stack<String> expression = reverseStack(inputStack);
        Stack<String> exit = new Stack<>();
        MathOperation operation = new MathOperation();

        while (!expression.empty()) {
            if (isDouble(expression.peek())) {
                exit.push(expression.pop());
            } else {
                if (operation.mapOperations.containsKey(expression.peek())) {
                    int countAgr = operation.mapOperations.get(expression.peek()).getCountOfArg();
                    double[] arrayArg = new double[countAgr];
                    for (int i = 0; i < countAgr; i++) {
                        arrayArg[i] = Double.parseDouble(exit.pop());
                    }
                    exit.push(String.valueOf(operation.mapOperations.get(expression.pop()).calculate(arrayArg)));
                }
            }
        }
        System.out.print(exit.pop());
    }

    private Stack<String> reverseStack(Stack<String> inputStack) {
        Stack<String> expression = new Stack<>();
        while (!inputStack.empty()) {
            expression.push(inputStack.pop());
        }
        return expression;
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
