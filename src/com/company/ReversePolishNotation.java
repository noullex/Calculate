package com.company;

import java.util.Stack;

public class ReversePolishNotation {

    public void calculate(Stack<String> inputStack) throws Exception {
        Stack<String> expression = reverseStack(inputStack);
        Stack<String> exit = new Stack<>();
        MathOperation operation = new MathOperation();
        while (!expression.empty()) {
            if ((expression.peek()).matches("[0-9]*")) {
                exit.push(expression.pop());
            } else {
                if (!exit.empty()) {
                    if (operation.mapOperations.containsKey(expression.peek())) {
                        int countAgr = operation.mapOperations.get(expression.peek()).getCountOfArg();
                        double[] arrayArg = new double[countAgr];
                        for (int i = 0; i < countAgr; i++) {
                            arrayArg[i] = Double.parseDouble(exit.pop());
                        }
                        exit.push(String.valueOf(operation.mapOperations.get(expression.pop()).calculate(arrayArg)));
                    } else {
                        //сообщение об ошибке
                    }
                }
            }
        }
        System.out.print("Result" + exit.pop());
    }

    private Stack<String> reverseStack(Stack<String> inputStack) {
        Stack<String> expression = new Stack<>();
        while (!inputStack.empty()) {
            expression.push(inputStack.pop());
        }
        return expression;
    }
}
