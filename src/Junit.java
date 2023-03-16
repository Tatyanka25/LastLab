import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.InvalidApplicationException;
import java.rmi.UnexpectedException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class Junit {
    @Test
    public void Addition_Is_Correct_When_Simple_Example_Is_Given() {
        String stroka1 = "1/4 + 3/7";
        Drobbi add = Vichislitel.Evaluate(stroka1);

        assertSame(add.GetBottomNumber(), 28);
        assertSame(add.GetUpperNumber(), 19);

    }
    @Test
    public void Deduction_Is_Correct_When_Simple_Example_Is_Given() {
        String stroka2 = "1/4 - 3/7";
        Drobbi ded = Vichislitel.Evaluate(stroka2);

        assertSame(ded.GetBottomNumber(), 28);
        assertSame(ded.GetUpperNumber(), -5);
    }
    @Test
    public void Multiplication_Is_Correct_When_Simple_Example_Is_Given(){
        String stroka3 = "1/4 * 3/7";
        Drobbi mul = Vichislitel.Evaluate(stroka3);

        assertSame(mul.GetBottomNumber(), 28);
        assertSame(mul.GetUpperNumber(), 3);
    }
    @Test
    public void Splitting_Is_Correct_When_Simple_Example_Is_Given(){
        String stroka4 = "1/4 / 3/7";
        Drobbi spl = Vichislitel.Evaluate(stroka4);

        assertSame(spl.GetBottomNumber(), 12);
        assertSame(spl.GetUpperNumber(), 7);
    }
    @Test
    public void The_Same_Priority_Is_Correct_When_Simple_Example_Is_Given() {
        String stroka5 = "1/4 - 3/7 + 1/2";
        Drobbi spl = Vichislitel.Evaluate(stroka5);

        assertSame(spl.GetBottomNumber(), 28);
        assertSame(spl.GetUpperNumber(), 9);
    }
    @Test
    public void Different_Priority_1_Is_Correct_When_Simple_Example_Is_Given() {
        String stroka6 = "1/4 + 3/7 * 1/2";
        Drobbi spl = Vichislitel.Evaluate(stroka6);

        assertSame(spl.GetBottomNumber(), 28);
        assertSame(spl.GetUpperNumber(), 13);
    }
    @Test
    public void Different_Priority_2_Is_Correct_When_Simple_Example_Is_Given() {
        String stroka7 = "( 1/4 - 3/7 ) / 1/2";
        Drobbi spl = Vichislitel.Evaluate(stroka7);

        assertSame(spl.GetBottomNumber(), 14);
        assertSame(spl.GetUpperNumber(), -5);
    }
    @Test ()
    public void No_Right_Simbols_1_Is_Correct_When_Simple_Example_Is_Given() {
        assertThrows(Exception.class, () -> {
        Vichislitel.Evaluate("( 1/4 # 3/7 ) / 1/2");
        });
    }
    @Test ()
    public void No_Right_Simbols_2_Is_Correct_When_Simple_Example_Is_Given() {
        assertThrows(Exception.class, () -> {
            Vichislitel.Evaluate("( a/d - 3/7 ) / 1/2");
        });
    }
    @Test ()
    public void Splitting_On_Zero_Is_Correct_When_Simple_Example_Is_Given() {
        assertThrows(Exception.class, () -> {
            Vichislitel.Evaluate("( 1/0 - 3/7 ) / 1/2");
        });
    }
}