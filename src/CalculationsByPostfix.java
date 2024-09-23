import java.util.Stack;

public class CalculationsByPostfix {

    public static double calculatePostfix(String expression) {
        Stack<Double> stack = new Stack<>();
        StringBuilder number = new StringBuilder();  // для накопления многозначного числа

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // Если это цифра, накапливаем её в строку
            if (Character.isDigit(ch) || ch == '.') {
                number.append(ch);
            }
            // Если встречаем пробел или оператор — это конец числа
            else if (ch == ' ' || InfixToPostfix.getIsOperator(ch)) {
                // Если накопили число, добавляем его в стек
                if (!number.isEmpty()) {
                    stack.push(Double.parseDouble(number.toString()));
                    number.setLength(0);  // Очищаем строку для следующего числа
                }

                // Если это оператор, выполняем соответствующую операцию
                if (InfixToPostfix.getIsOperator(ch)) {
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();

                    switch (ch) {
                        case '+':
                            stack.push(operand1 + operand2);
                            break;
                        case '-':
                            stack.push(operand1 - operand2);
                            break;
                        case '*':
                            stack.push(operand1 * operand2);
                            break;
                        case '/':
                            if (operand2 == 0) {
                                throw new ArithmeticException("Деление на 0");
                            }
                            stack.push(operand1 / operand2);
                            break;
                        case '^':
                            stack.push(Math.pow(operand1, operand2));
                            break;
                    }
                }
            }
        }

        // Если строка закончилась, но осталось число в буфере, помещаем его в стек
        if (!number.isEmpty()) {
            stack.push(Double.parseDouble(number.toString()));
        }

        return stack.pop();
    }
}
