
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] input) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Boolean fRome = null;
        Boolean sRome = null;
        Boolean isArab = null;
        // задаю разделитель между значениями и включаю в строковый массив эти значения
        String[] objects = line.split(" ");
        //проверка на количество операндов
        if (objects.length == 1) throw new Exception("т.к. строка не является математической операцией");
        if (objects.length != 3) throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        // создаю массив, в котором будут хранится числа
        int num1 = 0;
        int num2 = 0;
        // указываю все возможные цифры
        String[] rome = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arab = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        // проверка на принадлежность числа к ряду чисел + преобоазование из римских в арабские
        for (int i = 0; i < rome.length; i++) {
            if (objects[0].equals(rome[i])) {
                fRome = true;
                num1 = Integer.parseInt(arab[i]);
            } if(objects[0].equals(arab[i])) {
                fRome = false;
                num1 = Integer.parseInt(objects[0]);
            }
            if (objects[2].equals(rome[i])) {
                sRome = true;
                num2 = Integer.parseInt(arab[i]);
            } if(objects[2].equals(arab[i])) {
                sRome = false;
                num2 = Integer.parseInt(objects[2]);
            }
        }
        // проверка оба ли числа принадлежат одному типу
        if (sRome != fRome){
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }
        // проверка размерности значения, меньше ли 11 и больше ли 0
        if(num1 == 0) {
            throw new Exception("Числа должны быть целые и от 1 до 10");
        } if(num2 ==0){
            throw new Exception("Числа должны быть целые и от 1 до 10");
        }

        // Запоминаем арабские или римские значения у нас
        if(sRome == true){
            if(fRome == true){
                isArab = false;
            }
        } else if(sRome == false){
            if(fRome == false){
                isArab = true;
            }
        }
        Convert converter = new Convert();

        // Калькулятор
        int math;
        switch (objects[1]){
            case "+":
                math = num1 + num2;
                break;
            case "-":
                math = num1 - num2;
                break;
            case "/":
                math = num1 / num2;
                break;
            case "*":
                math = num1 * num2;
                break;
            default:
                throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        // проверяю в каком формате должен быть ответ, римскими или арабскими цифрами
        if(isArab == true){
            System.out.println(math);
        } else{
            if(math<1){
                throw new Exception("т.к. в римской системе нет отрицательных чисел");
            } else{
                System.out.println(converter.arabToRoman(math));
            }
        }
        scanner.close();
    }
}
class Convert {
    TreeMap<Integer, String> romeKey = new TreeMap<>();

    public Convert(){
        romeKey.put(1000, "M");
        romeKey.put(900, "CM");
        romeKey.put(500, "D");
        romeKey.put(400, "CD");
        romeKey.put(100, "C");
        romeKey.put(90, "XC");
        romeKey.put(50, "L");
        romeKey.put(40, "XL");
        romeKey.put(10, "X");
        romeKey.put(9, "IX");
        romeKey.put(5, "V");
        romeKey.put(4, "IV");
        romeKey.put(1, "I");

    }

    public String arabToRoman(int number){
        String roman = "";
        int arabKey;

        do {
            arabKey = romeKey.floorKey(number);
            roman += romeKey.get(arabKey);
            number -= arabKey;
        } while (number != 0);
        return roman;
    }
}





