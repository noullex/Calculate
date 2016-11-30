package com.company;

import java.util.*;

public class Parser {

    public Stack<String> parseExpression(String inputExpression) {
        inputExpression.replace(" ", "");
        inputExpression.replace(",", ".");
        char[] expression = inputExpression.toCharArray();

        MathOperation operation = new MathOperation();
        Stack<String> exitStack = new Stack<>();
        Stack<String> operationsStack = new Stack<>();
        ArrayList<Character> sub_expresion = new ArrayList<>();

        int index = 0;
        while (index < expression.length) {
            int type = getTypeToken(expression[index]);
            switch (type) {
                case 1:
                    boolean ok = true;

                    while (index < expression.length &&
                            (Character.isDigit(expression[index]) || String.valueOf(expression[index]).matches("[.eE+-]"))) {
                        if (String.valueOf(expression[index]).matches("[+-]")) {
                            if (String.valueOf(expression[index - 1]).matches("[eE]")) {
                                ok = true;
                            } else {
                                ok = false;
                            }
                        }
                        if (ok) {
                            sub_expresion.add(expression[index]);
                            index++;
                        } else {
                            break;
                        }
                    }
                    try {
                        String str = sub_expresion.stream().map(e -> e.toString()).reduce((acc, e) -> acc + e).get();
                        sub_expresion.clear();
                        Double.parseDouble(str);
                        exitStack.push(str);
                    } catch (Exception e) {
                        System.out.print("Недопустимая операция.");
                        System.exit(0);
                    }
                    break;
                case 2:
                    while (index < expression.length && Character.isLetter(expression[index])) {
                        sub_expresion.add(expression[index]);
                        index++;
                    }
                    String str = String.valueOf(sub_expresion);
                    if (operation.mapOperations.containsKey(str)) {
                        operationsStack.push(str);
                    } else {
                        System.out.print("Недопустимая операция.");
                        System.exit(0);
                    }
                    break;
                case 3:
                    String op = String.valueOf(expression[index]);
                    if (operation.mapOperations.containsKey(op)) {
                        if (!operationsStack.empty()) {
                            if (op.equals(")")) {
                                while (!operationsStack.peek().equals("(")) {
                                    exitStack.push(operationsStack.pop());
                                }
                                operationsStack.pop();
                            } else {
                                if (operation.mapOperations.get(operationsStack.peek()).getPriority() >=
                                        operation.mapOperations.get(op).getPriority() && !op.equals("(")) {
                                    exitStack.push(operationsStack.pop());
                                    operationsStack.push(op);
                                } else {
                                    operationsStack.push(op);
                                }
                            }
                        } else {
                            operationsStack.push(op);
                        }
                    } else {
                        System.out.print("Недопустимая операция.");
                        System.exit(0);
                    }
                    index++;
                    break;
            }
        }
        while (!operationsStack.empty()) {
            exitStack.push(operationsStack.pop());
        }
        return exitStack;
    }

    private int getTypeToken(char c) {
        if (Character.isDigit(c)) {
            return 1;
        } else if (Character.isLetter(c)) {
            return 2;
        } else {
            return 3;
        }
    }
}
