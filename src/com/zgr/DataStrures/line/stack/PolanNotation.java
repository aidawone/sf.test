package com.zgr.DataStrures.line.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 19:33 2020/1/13
 */
public class PolanNotation {
    public static void main(String[] a504rgs) {

//        String suffixExpression = "3 4 + 5 * 6 -";
//        23+29*12/2+3-4
//        String suffixExpression= "23 29 12 * 2 / + 3 + 4 -";
//        List<String> listString = getListString(suffixExpression);
//        int caclue = caclue(listString);
//        System.out.println(caclue);
        String expression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(expression);
        System.out.println(strings);
        List<String> strings1 = StrgintoPolantationList(strings);
        System.out.println("后缀表达式：" + strings1);
        System.out.println("逆波兰表达式：" + caclue(strings1));


    }

    //将List类型的字符转化成逆波兰表达式
    public static List<String> StrgintoPolantationList(List<String> s) {
        Stack<String> operStack = new Stack<>();
        List<String> numList = new ArrayList<>();
        for (String ls : s) {
            if (ls.matches("\\d+")) {
                numList.add(ls);
            } else if (ls.equals("(")) {
                operStack.push(ls);
            } else if (ls.equals(")")) {
                while (!operStack.peek().equals("(")) {
                    numList.add(operStack.pop());
                }
                operStack.pop();
            } else {
                //判断s中的符号优先级小于等于operStack中的优先级，依次弹出加入到numStack中
                while (operStack.size() != 0 && operProity.getPrity(operStack.peek()) > operProity.getPrity(ls)) {
                    numList.add(operStack.pop());
                }
                operStack.push(ls);
            }
        }
        while (operStack.size() != 0) {
            numList.add(operStack.pop());
        }
        return numList;

    }


    //中缀转后缀需要的List类型
    public static List<String> toInfixExpressionList(String expression) {
        String str;
        List<String> list = new ArrayList<>();
        char c;
        int i = 0;
        do {
            if ((c = expression.charAt(i)) < 48 || (c = expression.charAt(i)) > 57) {
                //字符
                list.add("" + c);
                i++;
            } else {
                //考虑多位数的拼接
                str = "";
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && (c = expression.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }

        } while (i < expression.length());
        return list;
    }

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    public static int caclue(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String obj : list) {
            if (obj.matches("\\d+")) {
                stack.push(obj);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (obj.equals("+")) {
                    res = num1 + num2;

                } else if (obj.equals("-")) {
                    res = num1 - num2;
                } else if (obj.equals("*")) {
                    res = num1 * num2;
                } else if (obj.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class operProity {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getPrity(String s) {
        int result = 0;
        switch (s) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;

    }
}
