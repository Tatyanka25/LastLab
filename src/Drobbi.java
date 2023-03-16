import java.util.HashMap;
import java.util.HashSet;

public class Drobbi {
    private int UpperNumber;//числитель
    private int BottomNumber;//знаменатель

    public Drobbi(int upperNumber, int bottomNumber) {//создаем конструктор
        if (bottomNumber == 0) throw new ArithmeticException("Ошибка. Деление на 0");//исключение
        UpperNumber = upperNumber;
        BottomNumber = bottomNumber;
        FractionReduction();
    }
    public Drobbi() {//создаем еще один конструктор по умолчанию
        UpperNumber = 1;
        BottomNumber = 1;
    }

    public int GetUpperNumber() { return UpperNumber; }//возвращаем числитель конкретно этой дроби
    public int GetBottomNumber() { return BottomNumber; }//возвращаем знаменатель конкретно этой дроби

    public Drobbi Addition(Drobbi other) {//сложение
        return Drobbi.Addition(this, other);
    }

    public static Drobbi Addition(Drobbi first, Drobbi second) {//сложение двух дробей
        var newBottomNumber = SmallestCommonMultiple(first.BottomNumber, second.BottomNumber);//наменьшее общее кратное
        var firstUpperNumber = first.UpperNumber * (newBottomNumber / first.BottomNumber);
        var secondUpperNumber = second.UpperNumber * (newBottomNumber / second.BottomNumber);
        return new Drobbi(firstUpperNumber + secondUpperNumber, newBottomNumber);
    }

    public Drobbi Deduction(Drobbi other) {//вычитание
        return Drobbi.Deduction(this, other);
    }

    public static Drobbi Deduction(Drobbi first, Drobbi second) {
        var newBottomNumber = SmallestCommonMultiple(first.BottomNumber, second.BottomNumber);//наименьшее общее кратное
        var firstUpperNumber = first.UpperNumber * (newBottomNumber / first.BottomNumber);
        var secondUpperNumber = second.UpperNumber * (newBottomNumber / second.BottomNumber);
        return new Drobbi(firstUpperNumber - secondUpperNumber, newBottomNumber);
    }

    public Drobbi Multiplication(Drobbi other) {//умножение
        return Drobbi.Multiplication(this, other);
    }
    public static Drobbi Multiplication(Drobbi first, Drobbi second) {
        return new Drobbi(first.UpperNumber * second.UpperNumber, first.BottomNumber * second.BottomNumber);
    }

    public Drobbi Splitting(Drobbi other) {//деление
        return Drobbi.Splitting(this, other);
    }

    public static Drobbi Splitting(Drobbi first, Drobbi second) {
        if (second.UpperNumber == 0) throw new ArithmeticException("Can't / zero");//исключение
        return new Drobbi(first.UpperNumber * second.BottomNumber, second.UpperNumber * first.BottomNumber);
    }

    private void FractionReduction() {//сокращение дроби
        var numeratorNumbers = GetPrimeFactors(UpperNumber);//раскладываем на простые множители числитель
        var denominatorNumbers = GetPrimeFactors(BottomNumber);//раскладываем на простые множители знаменатель
        var commonKeys = new HashSet<>(numeratorNumbers.keySet());
        commonKeys.retainAll(denominatorNumbers.keySet());
        for (var key: commonKeys) {
            int minCount = Math.min(numeratorNumbers.get(key), denominatorNumbers.get(key));
            for (int i = 0; i < minCount; i++) {
                UpperNumber /= key;
                BottomNumber /= key;
            }
        }
    }

    private HashMap<Integer, Integer> GetPrimeFactors(int num) {
        var res2  = new HashMap<Integer, Integer>();
        if (num < 0) {
            res2.put(-1, 1);
            num *= -1;
        }
        int i = 2;
        while (i <= num) {
            while (num % i == 0) {
                res2.put(i, res2.getOrDefault(i, 0) + 1);
                num /= i;
            }
            i++;
        }
        return res2;
    }

    private static int BigestCommonDivisor(int a, int b) {//находим наибольший общий делитель
        while (a>0 && b>0)
            if (a>b) a%=b;
            else b%=a;
        return a+b;
    }

    private static int SmallestCommonMultiple(int a, int b) {//находим наименьшее общее кратное
        return (a * b) / BigestCommonDivisor(a, b);
    }

    @Override//механизм переопределение методов
    public String toString() {
        return UpperNumber + "/" + BottomNumber;
    }
}




