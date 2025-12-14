import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Введи математическое равенство (например: 5+3=8):");
        String text = scan.nextLine();

        // Разбиваем по знаку =
        StringTokenizer ravnoTokenizer = new StringTokenizer(text, "=");
        if (ravnoTokenizer.countTokens() != 2) {
            System.out.println("Неправильный формат! Должен быть один знак =");
            return;
        }

        String left = ravnoTokenizer.nextToken();  // левая часть до =
        String right = ravnoTokenizer.nextToken(); // правая часть после =

        // Вычисляем левую часть
        double leftResult = calculateExpression(left);

        // Парсим правую часть (там должно быть просто число)
        double rightResult;
        try {
            rightResult = Double.parseDouble(right.trim());
        } catch (NumberFormatException e) {
            System.out.println("Справа от = должно быть число!");
            return;
        }

        // Сравниваем
        if (Math.abs(leftResult - rightResult) < 0.000001) {
            System.out.println("Равенство ВЕРНО");
        } else {
            System.out.println("Равенство НЕВЕРНО");
            System.out.println("Левая часть: " + leftResult);
            System.out.println("Правая часть: " + rightResult);
        }

        scan.close();
    }

    // Метод для вычисления выражения (только + и -)
    static double calculateExpression(String expr) {
        // Убираем пробелы
        expr = expr.replaceAll(" ", "");

        // Разбиваем по + и - но сохраняем разделители
        StringTokenizer tokenizer = new StringTokenizer(expr, "+-", true);

        double result = 0;
        String currentToken;
        char lastOp = '+';  // первый оператор считаем +

        while (tokenizer.hasMoreTokens()) {
            currentToken = tokenizer.nextToken();

            if (currentToken.equals("+") || currentToken.equals("-")) {
                lastOp = currentToken.charAt(0);
            } else {
                // Это число
                double num = Double.parseDouble(currentToken);
                if (lastOp == '+') {
                    result += num;
                } else {
                    result -= num;
                }
            }
        }

        return result;
    }
}