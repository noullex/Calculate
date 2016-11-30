package com.company;

import java.util.*;

import static com.company.TypeToken.*;

enum TypeToken { DIGHT, LETTER, SYMBOL}

public class Parser {

    private int index = 0;
    private Stack<String> exitStack = new Stack<>();
    private Stack<String> operationsStack = new Stack<>();

    public Stack<String> parseExpression(String inputExpression) {
        inputExpression.replace(" ", "");
        inputExpression.replace(",", ".");
        char[] expression = inputExpression.toCharArray();

        while (index < expression.length) {
            TypeToken type = getTypeToken(expression[index]);
            switch (type) {
                case DIGHT:
                    getNumberValue(expression);
                    break;
                case LETTER:
                    getStringOperationValue(expression);
                    break;
                case SYMBOL:
                    getSymbolOperationValue(expression);
                    break;
            }
        }
        while (!operationsStack.empty()) {
            exitStack.push(operationsStack.pop());
        }
        return exitStack;
    }

    private TypeToken getTypeToken(char c) {
        if (Character.isDigit(c)) {
            return DIGHT;
        } else if (Character.isLetter(c)) {
            return LETTER;
        } else {
            return SYMBOL;
        }
    }

    private void getNumberValue(char[] expression) {
        LinkedList<Character> sub_expresion = new LinkedList<>();
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
            System.out.print("Недопустимая операция.\n");
            System.exit(0);
        }
    }

    private void getStringOperationValue(char[] expression) {
        ArrayList<Character> sub_expresion = new ArrayList<>();
        MathOperation operation = new MathOperation();
        while (index < expression.length && Character.isLetter(expression[index])) {
            sub_expresion.add(expression[index]);
            index++;
        }
        String str = sub_expresion.stream().map(e -> e.toString()).reduce((acc, e) -> acc + e).get();
        sub_expresion.clear();
        if (operation.mapOperations.containsKey(str)) {
            operationsStack.push(str);
        } else {
            System.out.print("Недопустимая операция.\n");
            System.exit(0);
        }
    }

    private void getSymbolOperationValue(char[] expression) {
        MathOperation operation = new MathOperation();
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
            System.out.print("Недопустимая операция.\n");
            System.exit(0);
        }
        index++;
    }

    public void clearResource() {
        index = 0;
        exitStack.clear();
        operationsStack.clear();
    }
}
