package gof.ray.Interpreter.Sample;

//表达式接口
public interface Expression {
    public boolean interpret(String context);
}