package com.company;

import java.util.HashMap;

interface Function {
    double calculate(double[] arg);

    int getCountOfArg();
}


public class MathOperation {

    public HashMap<String, Function> mapOperations;

    public MathOperation() throws Exception {
        mapOperations = new HashMap<>();
        mapOperations.put("+", new Function() {
            @Override
            public double calculate(double[] arg) {
                return arg[0] + arg[1];
            }

            @Override
            public int getCountOfArg() {
                return 2;
            }
        });
        mapOperations.put("-", new Function() {
            @Override
            public double calculate(double[] arg) {
                return arg[1] - arg[0];
            }

            @Override
            public int getCountOfArg() {
                return 2;
            }
        });
        mapOperations.put("*", new Function() {
            @Override
            public double calculate(double[] arg) {
                return arg[0] * arg[1];
            }

            @Override
            public int getCountOfArg() {
                return 2;
            }
        });
        mapOperations.put("/", new Function() {
            @Override
            public double calculate(double[] arg) {
                return arg[1] / arg[0];
            }

            @Override
            public int getCountOfArg() {
                return 2;
            }
        });
        mapOperations.put("sin", new Function() {
            @Override
            public double calculate(double[] arg) {
                return Math.sin(arg[0]);
            }

            @Override
            public int getCountOfArg() {
                return 1;
            }
        });
        mapOperations.put("cos", new Function() {
            @Override
            public double calculate(double[] arg) {
                return Math.cos(arg[0]);
            }

            @Override
            public int getCountOfArg() {
                return 1;
            }
        });
    }
}
