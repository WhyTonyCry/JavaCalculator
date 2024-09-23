import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение");
        // Значение от пользователя
        String infixExpression = scanner.nextLine();

        // Получаем результат
        double result = CalculationsByPostfix.calculatePostfix(InfixToPostfix.infixToPostfix(infixExpression));

        // Выводим результат с округлением
        System.out.println("Ответ: " + String.format("%.3f",result ));
    }
}