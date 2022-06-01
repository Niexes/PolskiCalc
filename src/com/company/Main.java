package com.company;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Double> numbers = new Stack<>();

        String exp = sc.nextLine();
        String expression2 = exp.replace(')', ' ');
        String expression3 = expression2.replace('(', ' ');


        String[] newExp = expression3.split(" ");
        // System.out.println(newExp.length);
        // System.out.println(newe.length);

        for (int i = 0; i <= newExp.length - 1; i++) {
            if (check(newExp[i])) {
                double a;
                a = Double.parseDouble(newExp[i]);
                numbers.push(a);
            } else if (newExp[i].equals(" ")) {
                continue;
            } else if (newExp[i].equals("+")) {
                double val1, val2;
                val2 = numbers.pop();
                val1 = numbers.pop();
                double result = val1 + val2;
                numbers.push(result);
            } else if (newExp[i].equals("-")) {
                double val1, val2;
                val2 = numbers.pop();
                val1 = numbers.pop();
                double result = val1 - val2;
                numbers.push(result);
            } else if (newExp[i].equals("*")) {
                double val1, val2;
                val2 = numbers.pop();
                val1 = numbers.pop();
                double result = val1 * val2;
                numbers.push(result);
            } else if (newExp[i].equals("/")) {
                double val1, val2;
                val2 = numbers.pop();
                val1 = numbers.pop();
                double result = val1 / val2;
                numbers.push(result);
            }
        }
        double result = numbers.pop();
        System.out.println(result);
        if (!numbers.isEmpty()) {
            System.out.println("В стеке что-то осталось, некорректное выражение");
        }
    }


    public static boolean check(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
