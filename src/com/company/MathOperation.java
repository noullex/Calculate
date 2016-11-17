package com.company;

import java.util.HashMap;

interface Function {
    double calculate(double[] arg);

    int getCountOfArg();

    int getPriority();
}


public class MathOperation {

    public HashMap<String, Function> mapOperations;

    public MathOperation() {
        mapOperations = new HashMap<>();
        mapOperations.put("(", new Function() {
            @Override
            public double calculate(double[] arg) {
                return 0;
            }

            @Override
            public int getCountOfArg() {
                return 0;
            }

            @Override
            public int getPriority() {
                return 0;
            }
        });
        mapOperations.put(")", new Function() {
            @Override
            public double calculate(double[] arg) {
                return 0;
            }

            @Override
            public int getCountOfArg() {
                return 0;
            }

            @Override
            public int getPriority() {
                return 0;
            }
        });
        mapOperations.put("+", new Function() {
            @Override
            public double calculate(double[] arg) {
                return arg[0] + arg[1];
            }

            @Override
            public int getCountOfArg() {
                return 2;
            }

            @Override
            public int getPriority() {
                return 1;
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

            @Override
            public int getPriority() {
                return 1;
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

            @Override
            public int getPriority() {
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

            @Override
            public int getPriority() {
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

            @Override
            public int getPriority() {
                return 3;
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

            @Override
            public int getPriority() {
                return 3;
            }
        });
    }
}
