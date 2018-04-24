package gof.ray.Decorator.Sample;

public class Main {
    public static void main(String[] args) {
        Display b1 = new StringDisplay("Hello, world.");
        Display b2 = new SideBorder(b1, '#');
        Display b3 = new FullBorder(b2);
        Display b4 = new FullBorder(b3);
        Display b5 = new FullBorder(b4);
        b1.show();
        b2.show();
        b3.show();
        b4.show();
        b5.show();
    }
}
