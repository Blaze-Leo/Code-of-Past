import java.io.FileWriter;
import java.io.PrintWriter;

public class AssignmentGrader {

    static double score = 0;
    static double maxScore = 0;

    static PrintWriter writer;

    static void pass(double w) {
        score += w;
        maxScore += w;
    }

    static void fail(String msg, double w) {
        maxScore += w;
        writer.println("FAILED: " + msg);
    }

    static void check(boolean cond, String msg, double w) {
        if (cond) pass(w);
        else fail(msg, w);
    }

    static boolean classExists(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (Throwable e) {
            fail("Missing or broken class: " + name, 0);
            return false;
        }
    }

    static LinearList<Token> build(Object[] seq) {

        LinearList<Token> list;

        try {
            list = new LinearList<Token>();
        } catch (Throwable e) {
            fail("LinearList constructor failed: " + e, 0);
            return null;
        }

        try {

            for (Object o : seq) {

                if (o instanceof Integer)
                    list.add(new Token(Token.TokType.Num, (Integer) o));

                else if (o.equals("+"))
                    list.add(new Token(Token.TokType.Add));

                else if (o.equals("-"))
                    list.add(new Token(Token.TokType.Sub));

                else if (o.equals("*"))
                    list.add(new Token(Token.TokType.Mul));
            }

        } catch (Throwable e) {
            fail("Token creation failed: " + e, 0);
        }

        return list;
    }

    static void evaluateTest(Object[] seq, int expected, double w) {

        try {

            LinearList<Token> list = build(seq);
            if (list == null) {
                fail("Token list creation failed", w);
                return;
            }

            Calculator calc = new Calculator(list);
            Expr e = calc.makeExpr();

            if (e == null) {
                fail("makeExpr() returned null", w);
                return;
            }

            int val = e.evaluate();

            check(val == expected,
                    "Expected " + expected + " but got " + val,
                    w);

        } catch (Throwable ex) {
            fail("Program crashed: " + ex, w);
        }
    }

    static void stringTest(Object[] seq, String expected, double w) {

        try {

            LinearList<Token> list = build(seq);
            if (list == null) {
                fail("Token list creation failed", w);
                return;
            }

            Calculator calc = new Calculator(list);
            Expr e = calc.makeExpr();

            if (e == null) {
                fail("makeExpr() returned null", w);
                return;
            }

            String actual = e.makeString();

            if (actual == null) {
                fail("makeString() returned null", w);
                return;
            }

            String normActual = actual.replaceAll("\\s+", "");
            String normExpected = expected.replaceAll("\\s+", "");

            check(normActual.equals(normExpected),
                    "Expected \"" + expected + "\" but got \"" + actual + "\"",
                    w);

        } catch (Throwable ex) {
            fail("String test crashed: " + ex, w);
        }
    }

    static int iterateSum(LinearList<Integer> list) {

        int sum = 0;

        try {

            Object it = list.getIterator();

            java.lang.reflect.Method hasNext =
                    it.getClass().getMethod("hasNext");

            java.lang.reflect.Method next =
                    it.getClass().getMethod("next");

            while ((Boolean) hasNext.invoke(it)) {
                sum += (Integer) next.invoke(it);
            }

        } catch (Throwable e) {
            fail("Iterator invocation failed: " + e, 10);
        }

        return sum;
    }

    public static void main(String[] args) {

        try {
            writer = new PrintWriter(new FileWriter("grade.txt"));
        } catch (Exception e) {
            return;
        }

        if (!classExists("Expr")) { writer.close(); return; }
        if (!classExists("NumExpr")) { writer.close(); return; }
        if (!classExists("AddExpr")) { writer.close(); return; }
        if (!classExists("SubExpr")) { writer.close(); return; }
        if (!classExists("MulExpr")) { writer.close(); return; }
        if (!classExists("Stack")) { writer.close(); return; }
        if (!classExists("Token")) { writer.close(); return; }
        if (!classExists("LinearList")) { writer.close(); return; }
        if (!classExists("Calculator")) { writer.close(); return; }

        try {

            Stack<Integer> s = new Stack<Integer>();

            check(s.isEmpty(), "Stack should start empty", 2);

            s.push(5);
            s.push(10);

            check(!s.isEmpty(), "Stack should not be empty", 2);
            check(s.peek() == 10, "Peek incorrect", 2);
            check(s.pop() == 10, "Pop incorrect", 2);
            check(s.pop() == 5, "Second pop incorrect", 2);

        } catch (Throwable e) {
            fail("Stack crashed: " + e, 10);
        }

        try {

            LinearList<Integer> list = new LinearList<Integer>();

            list.add(1);
            list.add(2);
            list.add(3);

            int sum = iterateSum(list);

            check(sum == 6, "Iterator traversal incorrect", 4);

            list = new LinearList<Integer>();

            int emptySum = iterateSum(list);

            check(emptySum == 0, "Empty iterator incorrect", 3);

            list.add(10);

            int singleSum = iterateSum(list);

            check(singleSum == 10, "Iterator returned wrong value", 4);

        } catch (Throwable e) {
            fail("LinearList crashed: " + e, 10);
        }

        try {

            NumExpr n = new NumExpr(42);

            check(n.evaluate() == 42, "NumExpr evaluate incorrect", 3);
            check(n.makeString().equals("42"), "NumExpr makeString incorrect", 3);

            Expr a = new NumExpr(5);
            Expr b = new NumExpr(3);

            check(new AddExpr(a,b).evaluate()==8,"AddExpr incorrect",3);
            check(new SubExpr(a,b).evaluate()==2,"SubExpr incorrect",3);
            check(new MulExpr(a,b).evaluate()==15,"MulExpr incorrect",3);

        } catch (Throwable e) {
            fail("Expr classes crashed: " + e, 15);
        }

        try {

            Stack<Expr> stack = new Stack<Expr>();

            new Token(Token.TokType.Num,5).hopOnStack(stack);
            new Token(Token.TokType.Num,7).hopOnStack(stack);
            new Token(Token.TokType.Add).hopOnStack(stack);

            check(stack.pop().evaluate()==12,"Token hopOnStack incorrect",15);

        } catch (Throwable e) {
            fail("Token crashed: "+e,15);
        }

        double w = 1.5;

        evaluateTest(new Object[]{5},5,w);
        evaluateTest(new Object[]{3,4,"+"},7,w);
        evaluateTest(new Object[]{10,2,"*"},20,w);
        evaluateTest(new Object[]{8,3,"-"},5,w);

        evaluateTest(new Object[]{3,10,"-"},-7,w);
        evaluateTest(new Object[]{10,3,"-"},7,w);

        evaluateTest(new Object[]{5,6,"+",2,"*"},22,w);
        evaluateTest(new Object[]{2,3,4,"*","+"},14,w);
        evaluateTest(new Object[]{2,3,"+",4,"*"},20,w);

        evaluateTest(new Object[]{5,2,"-",3,"*"},9,w);
        evaluateTest(new Object[]{5,2,3,"*","-"},-1,w);

        evaluateTest(new Object[]{2,3,"*",4,"*"},24,w);
        evaluateTest(new Object[]{2,3,"+",4,"+",5,"+"},14,w);

        evaluateTest(new Object[]{1,2,"+",3,4,"+","*"},21,w);
        evaluateTest(new Object[]{2,3,"+",4,5,"+","*"},45,w);

        evaluateTest(new Object[]{-5,-6,"+"},-11,w);
        evaluateTest(new Object[]{-5,-6,"*"},30,w);

        evaluateTest(new Object[]{1000,2000,"+",3000,"+"},6000,w);
        evaluateTest(new Object[]{50,2,"*",5,"*"},500,w);

        Object[] deep = new Object[199];
        int index = 0;

        for(int i=1;i<=100;i++) deep[index++]=i;
        for(int i=1;i<100;i++) deep[index++]="+";

        evaluateTest(deep,5050,3);

        stringTest(new Object[]{3,4,"+"},"(3 + 4)",1);
        stringTest(new Object[]{5,6,"+",2,"*"},"((5 + 6) * 2)",2);
        stringTest(new Object[]{2,3,4,"*","+"},"(2 + (3 * 4))",2);
        stringTest(new Object[]{22,5,"+",3,"*"},"((22 + 5) * 3)",2);

        stringTest(new Object[]{5},"5",2);
        stringTest(new Object[]{10,3,"-"},"(10 - 3)",2);
        stringTest(new Object[]{3,10,"-"},"(3 - 10)",2);

        stringTest(new Object[]{5,2,"-",3,"*"},"((5 - 2) * 3)",2);
        stringTest(new Object[]{5,2,3,"*","-"},"(5 - (2 * 3))",2);

        score += 0.5;
        maxScore += 0.5;

        double percent = (score/maxScore)*100.0;

        writer.println(""+score+"/"+maxScore);
        writer.println(String.format("%.2f%%", percent));

        writer.close();
    }
}