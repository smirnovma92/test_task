import java.util.Scanner;
/* Не вижу вариантов, как может получится результатом 0 в риских числах */
public class Main {
    public static void main(String[] args) {
        String s;
        try {
            s = calc(new Scanner(System.in).nextLine());
            if (s.length() > 6)
                throw new Exception(s);
            System.out.println(s);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

    static boolean isRoman(String num) {
        return num.charAt(0) == 'I' || num.charAt(0) == 'V' || num.charAt(0) == 'X';
    }

    static int romanToArabic(String num) {
        int result = 0;
        int new_value = 0;
        int old_value = 0;

        for (int i = 0; i < num.length(); i++) {
            switch (num.charAt(i)) {
                case 'I' -> new_value = 1;
                case 'V' -> new_value = 5;
                case 'X' -> new_value = 10;
            }

            if (new_value > old_value)
                result = result + new_value - 2 * old_value;
            else
                result = result + new_value;


            old_value = new_value;
        }
        return result;
    }

    public static String romanBuild(int num, String d1, String d2, String d3) {
        if (num == 5) return d2;
        if (num == 9) return d1 + d3;
        if (num == 4) return d1 + d2;
        String res = "";
        if (num > 5) {
            res += d2;
            num %= 5;
        }
        for (int i = 0; i < num; i++) res += d1;
        return res;
    }
    public static String intToRome(int num) {
        if (num == 100) return "C";
        return romanBuild(num/10, "X", "L", "C") + romanBuild(num%10, "I", "L", "C");
    }

    static int initNums(String num) {
        int n;
        if (isRoman(num))
            n = romanToArabic(num);
        else
            n = Integer.parseInt(num);
        return n;
    }

    public static String calc(String input) {
        int a, b;
        int resInt;
        String[] arrStr = input.split(" ");
        String resStr;

        if (arrStr.length > 3)
            return "throws Exception //т.к. формат математической операции " +
                    "не удовлетворяет заданию - два операнда и один оператор" +
                    " (+, -, /, *)";

        if (arrStr.length < 3)
            return "throws Exception //т.к. строка не является математической операцией";

        if (arrStr[0].charAt(0) == '-' && arrStr[2].charAt(0) == '-')
            return "Данные числа пока не поддерживаются. Ждите обновлений";

        if (!isRoman(arrStr[0]) && isRoman(arrStr[2]) || isRoman(arrStr[0]) && !isRoman(arrStr[2]))
            return "throws Exception //т.к. используются одновременно разные системы счисления";

        a = initNums(arrStr[0]);
        b = initNums(arrStr[2]);

        if (a > 10 || b > 10 || a < 1 || b < 1)
            return "Данные числа пока не поддерживаются. Ждите обновлений";

        switch (arrStr[1]) {
            case "+":
                resInt = a + b;
                break;
            case "-":
                resInt = a - b;
                break;
            case "*":
                resInt = a * b;
                break;
            case "/":
                resInt = a / b;
                break;
            default:
                return "Неподдерживаемая операция";
        }

        if (isRoman(arrStr[0]))
            if (resInt <= 0)
                return "throws Exception //т.к. в римской системе нет отрицательных чисел";
            else
                resStr = intToRome(resInt);
        else
            resStr = Integer.toString(resInt);

        return resStr;
    }
}
