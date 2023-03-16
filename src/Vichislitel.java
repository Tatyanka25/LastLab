import java.util.*;

public class Vichislitel {
    public static Drobbi Evaluate(String equation) {
        var tokens = ParseTokens(equation);
        return EvaluateTokens(tokens);
    }

    private static ArrayList<Preobrazovanie> ParseTokens(String equation) {
        var sections = equation.split(" ");
        var tokens = new ArrayList<Preobrazovanie>(sections.length);
        for (var section: sections) {
            switch (section) {
                case "+" -> tokens.add(new Preobrazovanie(Procedure.Addition));
                case "-" -> tokens.add(new Preobrazovanie(Procedure.Deduction));
                case "*" -> tokens.add(new Preobrazovanie(Procedure.Multiplication));
                case "/" -> tokens.add(new Preobrazovanie(Procedure.Splitting));
                case "(" -> tokens.add(new Preobrazovanie(Procedure.OpenParenthesis));
                case ")" -> tokens.add(new Preobrazovanie(Procedure.CloseParenthesis));
                default -> {
                    var fractionParts = section.split("/");
                    tokens.add(new Preobrazovanie(new Drobbi(Integer.parseInt(fractionParts[0]), Integer.parseInt(fractionParts[1]))));
                }
            }
        }
        return tokens;
    }

        private static Drobbi EvaluateTokens(ArrayList<Preobrazovanie> tokens) {
            var procedures = new Stack<Procedure>();
            var reversePolishNotation = new ArrayDeque<Preobrazovanie>();
            for (var token: tokens) {
                if (token.GetPreobrazovanieType() == PreobrazovanieType.Number) {
                    reversePolishNotation.addLast(token);
                    continue;
                }
                switch (token.GetProcedure()) {
                    case Addition: case Deduction:
                        while (!procedures.empty() && procedures.peek() != Procedure.OpenParenthesis)
                            reversePolishNotation.addLast(new Preobrazovanie(procedures.pop()));
                        procedures.push(token.GetProcedure());
                        break;
                    case OpenParenthesis:
                        procedures.push(token.GetProcedure());
                        break;
                    case CloseParenthesis:
                        while (procedures.peek() != Procedure.OpenParenthesis)
                            reversePolishNotation.addLast(new Preobrazovanie(procedures.pop()));
                        procedures.pop();
                        break;
                    case Splitting: case Multiplication:
                        while (!procedures.empty() && procedures.peek() != Procedure.OpenParenthesis && procedures.peek().GetPrecedence().equals(token.GetProcedure().GetPrecedence()))
                            reversePolishNotation.addLast(new Preobrazovanie(procedures.pop()));
                        procedures.push(token.GetProcedure());
                        break;
                }
            }
            while (!procedures.empty()) reversePolishNotation.addLast(new Preobrazovanie(procedures.pop()));
            var solution = new Stack<Preobrazovanie>();
            while (!reversePolishNotation.isEmpty()) {
                var cur = reversePolishNotation.pop();
                if (cur.GetPreobrazovanieType() == PreobrazovanieType.Number) {
                    solution.push(cur);
                    continue;
                }
                var first = solution.pop();
                var second = solution.pop();
                Drobbi res = switch (cur.GetProcedure()) {
                    case Addition -> first.GetNumber().Addition(second.GetNumber());
                    case Deduction -> second.GetNumber().Deduction(first.GetNumber());
                    case Multiplication -> first.GetNumber().Multiplication(second.GetNumber());
                    case Splitting -> second.GetNumber().Splitting(first.GetNumber());
                    default -> null;
                };
                solution.push(new Preobrazovanie(res));
            }
            return solution.pop().GetNumber();
        }
    }