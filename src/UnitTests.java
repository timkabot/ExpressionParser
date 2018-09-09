import junit.framework.TestCase;

public class UnitTests extends TestCase {
    public void testParseInteger()
    {
        Parser parser = new Parser();

        Expression actual = parser.parseInteger("2");

        assert (actual.getNode().equals("2"));
    }

    public void testIsInteger()
    {
        Parser parser = new Parser();

        boolean actual = parser.isInteger("2");

        assertTrue (actual);

        actual = parser.isInteger("a");

        assertFalse(actual);
    }

    public void testSum()
    {
        Expression expression;
        Parser parser = new Parser();
        expression = parser.parse("7+5");
        long correct = 7 + 5;
        assert(expression.calculate()==correct);
    }

    public void testMultiplication()
    {
        Expression expression;
        Parser parser = new Parser();
        expression = parser.parse("7*5");
        long correct = 7 * 5;
        assert(expression.calculate()==correct);
    }

    public void testSubtraction()
    {
        Expression expression;
        Parser parser = new Parser();
        expression = parser.parse("7-5");
        long correct = 7 - 5;
        assert(expression.calculate()==correct);
    }

    public void testLessRight()
    {
        Expression expression;
        Parser parser = new Parser();
        expression = parser.parse("5<6");
        long correct = 1;
        assert(expression.calculate()==correct);
    }

    public void testLessWrong()
    {
        Expression expression;
        Parser parser = new Parser();
        long correct = 0;

        expression = parser.parse("6<4");

        assert(expression.calculate()==correct);


    }

    public void testBiggerRight()
    {
        Expression expression;
        Parser parser = new Parser();
        expression = parser.parse("6>4");
        long correct = 1;
        assert(expression.calculate()==correct);
    }

    public void testBiggerWrong()
    {
        Expression expression;
        Parser parser = new Parser();
        long correct = 0;

        expression = parser.parse("4>6");

        assert(expression.calculate()==correct);
    }

    public void testEqualRight()
    {
        Expression expression;
        Parser parser = new Parser();
        long correct = 1;

        expression = parser.parse("6=6");

        assert(expression.calculate()==correct);
    }

    public void testEqualWrong()
    {
        Expression expression;
        Parser parser = new Parser();
        long correct = 0;

        expression = parser.parse("5=6");

        assert(expression.calculate()==correct);
    }

    public void testFindFacOpCorrect()
    {
        Parser parser = new Parser();

        Factor.Opcode correct = Factor.Opcode.multiplication;
        String input = "2*2";

        assert (parser.findFacOp(input) == correct);
    }

    public void testFindFacOpWrong()
    {
        Parser parser = new Parser();

        Factor.Opcode correct = Factor.Opcode.none;
        String input = "2+2";

        assert (parser.findFacOp(input) == correct);

        input = "2-2";

        assert (parser.findFacOp(input) == correct);

        input = "2<2";

        assert (parser.findFacOp(input) == correct);

        input = "2>2";

        assert (parser.findFacOp(input) == correct);

        input = "2=2";

        assert (parser.findFacOp(input) == correct);
    }

    public void testfindTermOpRight()
    {
        Parser parser = new Parser();

        Term.Opcode correct = Term.Opcode.plus;
        String input = "2+2";

        assert (parser.findTermOp(input) == correct);

        correct = Term.Opcode.minus;
        input = "2-2";

        assert (parser.findTermOp(input) == correct);
    }

    public void testfindTermOpWrong()
    {
        Parser parser = new Parser();

        Term.Opcode correct = Term.Opcode.none;
        String input = "2*2";

        assert (parser.findTermOp(input) == correct);

        input = "2<2";

        assert (parser.findTermOp(input) == correct);

        input = "2>2";

        assert (parser.findTermOp(input) == correct);

        input = "2=2";

        assert (parser.findTermOp(input) == correct);
    }

    public void testfindRelOpWrong()
    {
        Parser parser = new Parser();

        Relation.Opcode correct = Relation.Opcode.none;
        String input = "2+2";

        assert (parser.findRelOp(input) == correct);

        input = "2-2";

        assert (parser.findRelOp(input) == correct);

        input = "2*2";

        assert (parser.findRelOp(input) == correct);
    }

    public void testfindRelOpRight()
    {
        Parser parser = new Parser();

        Relation.Opcode correct = Relation.Opcode.less;
        String input = "2<2";

        assert (parser.findRelOp(input) == correct);

        correct = Relation.Opcode.greater;
        input = "2>2";

        assert (parser.findRelOp(input) == correct);

        correct = Relation.Opcode.equal;
        input = "2=2";

        assert (parser.findRelOp(input) == correct);
    }

    public void testParsers()
    {
        Parser parser = new Parser();

        String input = "2<2", correct = "<";
        Expression answer = parser.parseRelation(input);

        assert (answer.getNode().equals(correct));

        input = "2>2";
        correct = ">";
        answer = parser.parseRelation(input);

        assert (answer.getNode().equals(correct));

        input = "2=2";
        correct = "=";
        answer = parser.parseRelation(input);

        assert (answer.getNode().equals(correct));

        input = "2+2";
        correct = "+";
        answer = parser.parseRelation(input);

        assert (answer.getNode().equals(correct));

        input = "2-2";
        correct = "-";
        answer = parser.parseRelation(input);

        assert (answer.getNode().equals(correct));

        input = "2*2";
        correct = "*";
        answer = parser.parseRelation(input);

        assert (answer.getNode().equals(correct));

        input = "(2*2)";
        correct = "*";
        answer = parser.parseRelation(input);

        assert (answer.getNode().equals(correct));
    }
}
