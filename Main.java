import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();
        System.out.println("Результат: " + calc(input));
    }
    public static String calc(String input) {
        String[] arrayInput = input.split(" ");
        if (arrayInput.length > 3) {
            try {
                throw new Exception();
            } catch (Exception e) {
                return "Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
            }
        } else if (arrayInput.length < 2) {
            try {
                throw new Exception();
            } catch (Exception e) {
                return "Строка не является математической операцией";
            }
        }
        String result = "";
        if (isNumeric(arrayInput[0]) && isNumeric(arrayInput[2])) {
            result = arabic(arrayInput);
        }
        else if (!isNumeric(arrayInput[0]) && !isNumeric(arrayInput[2])) {
            result = roman(arrayInput);}
        else {
            try {
                throw new Exception();
            } catch (Exception e) {
                return "Используются одновременно разные системы счисления";
            }
        }
        return result;
    }
    public static String arabic(String[] arrayInput) {
        try {
            int operandOne = Integer.parseInt(arrayInput[0]);
            int operandTwo = Integer.parseInt(arrayInput[2]);
            String operatorString = arrayInput[1];
            char operator = operatorString.charAt(0);
            int result = 0;

            if ((operandOne > 10 || operandOne < 1) || (operandTwo > 10 || operandTwo < 0)) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    return "Число в не диапазоне от 1 до 10";
                }
            }
            if (operator != '/') {
                if (operandTwo == 0) {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        return "Число в не диапазоне от 1 до 10";
                    }
                }
            }
            switch (operator) {
                case '*':
                    result = operandOne * operandTwo;
                    break;
                case '/':
                    try {
                        result = operandOne / operandTwo;
                    } catch (ArithmeticException e) {
                        return "На 0 делить нельзя";
                    }
                    break;
                case '+':
                    result = operandOne + operandTwo;
                    break;
                case '-':
                    result = operandOne - operandTwo;
                    break;
                default:
                    return "Формат математической операции не удовлетворяет заданию операторы (+, -, /, *)";
            }
            return result + "";
        } catch (NumberFormatException e) {
            return "Число не целое";
        }
    }

    public static String roman(String[] arrayInput) {
        String[] arrayRoman = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",};
        String operandOne = arrayInput[0];
        String operandTwo = arrayInput[2];
        int indexOperandOne = Arrays.asList(arrayRoman).indexOf(operandOne);
        int indexOperandTwo = Arrays.asList(arrayRoman).indexOf(operandTwo);
        if (indexOperandOne < 0 || indexOperandTwo < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                return "Число в не диапазоне от I до X";
            }
        }
        String operatorString = arrayInput[1];
        char operator = operatorString.charAt(0);
        try {
            int result = 0;
            switch (operator) {
                case '*':
                    result = indexOperandOne * indexOperandTwo;
                    break;
                case '/':
                    result = indexOperandOne / indexOperandTwo;
                    break;
                case '+':
                    result = indexOperandOne + indexOperandTwo;
                    break;
                case '-':
                    result = indexOperandOne - indexOperandTwo;
                    if (result < 1) {
                        try {
                            throw new Exception();
                        } catch (Exception e) {
                            return "В римской системе нет нуля и отрицательных чисел";
                        }
                    }
                    break;
                default:
                    return "Формат математической операции не удовлетворяет заданию операторы (+, -, /, *)";
            }
            RomanNumerals[] roman = RomanNumerals.values();
            return roman[result - 1] + "";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "В результате вычесление, результат равен 0, в римской системе нет нуля";
        }
    }
    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
