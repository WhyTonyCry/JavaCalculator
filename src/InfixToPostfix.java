import java.util.Stack;
import java.util.HashMap;

class InfixToPostfix {

    // Карта приоритетов операторов
    private static final HashMap<Character, Integer> precedenceMap = new HashMap<>();

    static {
        precedenceMap.put('+', 1);
        precedenceMap.put('-', 1);
        precedenceMap.put('*', 2);
        precedenceMap.put('/', 2);
        precedenceMap.put('^', 3);
    }

    // Метод для проверки, является ли символ оператором
    private static boolean isOperator(char c) {
        return precedenceMap.containsKey(c);
    }
    // Метод для получения значение оператора
    static boolean getIsOperator(char c) {
        return precedenceMap.containsKey(c);
    }

    // Метод для проверки приоритета операторов
    private static int priority(char operator) {
        return precedenceMap.get(operator);
    }

    // Метод для преобразования инфиксного выражения в постфиксное
    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Если символ — операнд, добавляем его в результат
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            // Если символ — открывающая скобка, помещаем её в стек
            else if (c == '(') {
                stack.push(c);
            }
            // Если символ — закрывающая скобка
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // Удаляем '(' из стека
            }
            // Если символ — оператор
            else if (isOperator(c)) {
                result.append(' ');
                while (!stack.isEmpty() && isOperator(stack.peek()) &&
                        priority(c) <= priority(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Выгружаем оставшиеся операторы из стека
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                throw new IllegalArgumentException("Недопустимое выражение");
            result.append(stack.pop());
        }

        return result.toString();
    }
}