import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lab5 = new Scanner(System.in);
        while (true) {
            try {
                String equation = lab5.nextLine();
                if (equation.equals("quit")) {
                    System.out.println("Program is finished");
                    break;
                }
                var total = Vichislitel.Evaluate(equation);
                System.out.println(total);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка. Некорректное выражение");
                break;
            }
        }
            lab5.close();
        }
    }