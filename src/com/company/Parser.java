package com.company;

import java.util.HashMap;
import java.util.Stack;

public class Parser {

    public Stack<String> parseExpression(String inputExpression) {
        HashMap<String, Integer> operatorPriority = fillPriorityMap();
        Stack<String> exitStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        String[] expression = inputExpression.split("(?<=\\G.{1})");
        String symb = "";

        //добавить возможность распознавания double
        for (int i = 0; i < expression.length; i++) {
            if (expression[i].matches("[0-9]*")) {
                symb += expression[i];
            } else {
                exitStack.push(symb);
                symb = "";
                if (operatorPriority.containsKey(expression[i])) {
                    if (!operatorStack.empty()) {
                        if (operatorPriority.get(operatorStack.peek()) > operatorPriority.get(expression[i])) {
                            exitStack.push(operatorStack.pop());
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

    public HashMap<String, Integer> fillPriorityMap() {
        HashMap<String, Integer> operatorPriority = new HashMap<>();
        operatorPriority.put("+", 1);
        operatorPriority.put("-", 1);
        operatorPriority.put("*", 2);
        operatorPriority.put("/", 2);
        operatorPriority.put("sin", 3);
        operatorPriority.put("cos", 3);
        return operatorPriority;
    }
}
