public enum Procedure {
    Addition(1),
    Deduction(1),
    Multiplication(2),
    Splitting(2),
    OpenParenthesis(3),
    CloseParenthesis(3);
    private final Integer Precedence;

    Procedure(int precedence) {
        Precedence = precedence;
    }

    public Integer GetPrecedence() { return Precedence; }
}