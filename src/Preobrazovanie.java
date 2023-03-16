public class Preobrazovanie {
    private final PreobrazovanieType Type;
    private final Drobbi Number;
    private final Procedure Procedure;

    public Preobrazovanie(Procedure procedure) {
        Type = PreobrazovanieType.Operator;
        Procedure = procedure;
        Number = null;
    }
    public Preobrazovanie(Drobbi number) {
        Type = PreobrazovanieType.Number;
        Number = number;
        Procedure = null;
    }
    public PreobrazovanieType GetPreobrazovanieType() {
        return Type;
    }
    public Drobbi GetNumber() {
        if (Number == null) {
            throw new RuntimeException("Preobrazovanie is an operator");
        }
        return Number;
    }
    public Procedure GetProcedure() {
        if (Procedure == null) {
            throw new RuntimeException("Preobrazovanie is a number");
        }
        return Procedure;
    }
}

enum PreobrazovanieType {
    Number,
    Operator
}