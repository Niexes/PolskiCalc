package com.company;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Double> numbers = new Stack<>();

        String exp = sc.nextLine();//входящая строка
        String m = convert(exp);//конвертированная строка в польскую запись
        System.out.println(m); //проверка корректности конвертации

        String expression2 = m.replace(')', ' ');
        String expression3 = expression2.replace('(', ' ');


        String[] newExp = expression3.split(" ");

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

    public static String convert(String exp) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> operators = new Stack<>(); //ТЕСТ 1 5*6+(2-9) //56*29-+
//ТЕСТ 2 3+4*2/(1-5)*2
//3 4 2 * 1 5 - / 2 * +
//ТЕСТ 3 (8+2)*2-1*5/4+2 .. 8 2 + 2 * 1 5 * 4 / - 2 +
        for (int i = 0; i < exp.length(); i++) {
            char op = exp.charAt(i);

            if (checkIsItNumber(op)) {
                int number = Integer.parseInt(String.valueOf(exp.charAt(i)));
                postfix.append(number);
                postfix.append(" "); //если число - добавляем его в стрингбилдер
            }
//если нам попался оператор
            else if (op == '+' || exp.charAt(i) == '-' || op == '/' || op == '*') {
                if (operators.isEmpty() || operators.peek() == '(') {
                    operators.push(op);
                } //Если стек (STACK) пуст или содержит левую скобку в вершине (TOP), то добавляем (PUSH) входящий оператор в стек (STACK).
                else if (getPriority(op) > getPriority(operators.peek())) { //getPriority + = 2, getPriority * = 3
                    operators.push(op);
                }//Если входящий оператор имеет более высокий приоритет чем вершина (TOP), поместите (PUSH) его в стек (STACK).
                else if (getPriority(op) < getPriority(operators.peek()) || getPriority(op) == getPriority(operators.peek())) {
                    while (!(operators.isEmpty()) && (getPriority(op)<getPriority(operators.peek()) || getPriority(op)==getPriority(operators.peek()))){
                        postfix.append(operators.pop());
                        postfix.append(" "); }
                    operators.push(op);
                }
//Если входящий оператор имеет более низкий или равный приоритет, чем вершине (TOP), выгружаем POP в строку,
// пока не увидите оператор с меньшим приоритетом или левую скобку на вершине (TOP), затем добавьте (PUSH) входящий оператор в стек (STACK).
            } else if (op == '(') {
                operators.push(op);
            } else if (op == ')') {
                while (operators.peek() != '(') {
                    postfix.append(operators.pop());
                    postfix.append(" ");
                }
                operators.pop();
            }//Если входящий элемент является правой скобкой,
// выгружаем стек (POP) и добавляем его элементы в очередь (QUEUE), пока не увидите левую круглую скобку. Удалите найденную скобку из стека (STACK).
//else postfix.append(operators.pop());

        }
        while (operators.size()!=0) {
            postfix.append(operators.pop());
            postfix.append(" ");
        }
        return postfix.toString();
    }
    public static int getPriority(char operator) {
        int priority = 0;
        if (operator == '(') priority = 4;
        if (operator == ')') priority = 4;//4 cамый высокий
        if (operator == '+') priority = 2;
        if (operator == '-') priority = 2; // низкий приоритет
        if (operator == '*') priority = 3;// 3 высокий
        if (operator == '/') priority = 3;
        return priority;
    }

    public static boolean checkIsItNumber(char num) {
        try {
            Integer.parseInt(String.valueOf(num));
            return true;
        } catch (Exception ex) {
            return false;
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
