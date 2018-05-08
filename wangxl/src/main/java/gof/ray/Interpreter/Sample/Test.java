package gof.ray.Interpreter.Sample;

//使用 Expression 类来创建规则
public class Test {

    public static void main(String[] args) {
        // 规则：Robert 和 John 是男性
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        Expression orMale = new OrExpression(robert, john);
        
        System.out.println("John is male? " + orMale.interpret("John"));
        System.out.println("Robert is male? " + orMale.interpret("Robert"));
        System.out.println("Has a male? " + orMale.interpret("Robert 1 Julie"));

        // 规则：Julie 是一个已婚的女性
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        Expression andMarriedWoman = new AndExpression(julie, married);
        System.out.println("Julie is a married women? " + andMarriedWoman.interpret("Married Julie"));
        System.out.println("John is a married women? " + andMarriedWoman.interpret("Married John"));
    }
}
