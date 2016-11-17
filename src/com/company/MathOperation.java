package com.company;

import java.lang.reflect.Method;
import java.util.HashMap;


public class MathOperation {

    public HashMap<String, Method> mapOperations;
    public HashMap<String, Integer> mapCountArgsOfOperations;

    public MathOperation() throws Exception {
        mapOperations = new HashMap<>();
        mapOperations.put("+", MathOperation.class.getMethod("sum", double.class, double.class));
        mapOperations.put("-", MathOperation.class.getMethod("difference", double.class, double.class));
        mapOperations.put("*", MathOperation.class.getMethod("composition", double.class, double.class));
        mapOperations.put("/", MathOperation.class.getMethod("quotient", double.class, double.class));
        mapOperations.put("sin", MathOperation.class.getMethod("sin", double.class));
        mapOperations.put("cos", MathOperation.class.getMethod("cos", double.class));

        mapCountArgsOfOperations = new HashMap<>();
        mapCountArgsOfOperations.put("+", 2);
        mapCountArgsOfOperations.put("-", 2);
        mapCountArgsOfOperations.put("*", 2);
        mapCountArgsOfOperations.put("/", 2);
        mapCountArgsOfOperations.put("sin", 1);
        mapCountArgsOfOperations.put("cos", 1);
    }

    public double sum(double a, double b) {
        return a + b;
    }

    public double difference(double a, double b) {
        return b - a;
    }

    public double composition(double a, double b) {
        return a * b;
    }

    public double quotient(double a, double b) {
        return b / a;
    }

    public double sin(double a) {
        return Math.sin(a);
    }

    public double cos(double a) {
        return Math.cos(a);
    }
}
