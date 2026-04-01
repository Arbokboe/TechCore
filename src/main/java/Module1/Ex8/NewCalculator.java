package Module1.Ex8;

import java.util.Scanner;

public class NewCalculator {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        return a / b;
    }

    public static boolean isExitCommand(String input) {
        return input.equalsIgnoreCase("exit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Калькулятор");
        System.out.println("Доступные операции: +, -, *, /");
        System.out.println("Для выхода введите 'exit'");

        while (true) {
            System.out.print("\nВведите первое число: ");
            String firstInput = scanner.nextLine();

            if (isExitCommand(firstInput)) {
                break;
            }

            System.out.print("Введите второе число: ");
            String secondInput = scanner.nextLine();

            if (isExitCommand(secondInput)) {
                break;
            }

            System.out.print("Введите операцию (+, -, *, /): ");
            String operation = scanner.nextLine();

            if (isExitCommand(operation)) {
                break;
            }

            try {
                double num1 = Double.parseDouble(firstInput);
                double num2 = Double.parseDouble(secondInput);
                double result;

                switch (operation) {
                    case "+":
                        result = add(num1, num2);
                        System.out.printf("Результат: %.2f + %.2f = %.2f%n", num1, num2, result);
                        break;
                    case "-":
                        result = subtract(num1, num2);
                        System.out.printf("Результат: %.2f - %.2f = %.2f%n", num1, num2, result);
                        break;
                    case "*":
                        result = multiply(num1, num2);
                        System.out.printf("Результат: %.2f * %.2f = %.2f%n", num1, num2, result);
                        break;
                    case "/":
                        if (num2 == 0 || num1 == 0) {
                            System.out.println("Ошибка: Деление на ноль невозможно!");
                            continue;
                        }
                        result = divide(num1, num2);
                        System.out.printf("Результат: %.2f / %.2f = %.2f%n", num1, num2, result);
                        break;
                    default:
                        System.out.println("Ошибка: Неизвестная операция! Используйте +, -, *, /");
                        continue;
                }

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Пожалуйста, введите корректные числа!");
            }
        }

        System.out.println("Программа завершена");
        scanner.close();
    }
}