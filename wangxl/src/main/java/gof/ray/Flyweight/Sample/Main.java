package gof.ray.Flyweight.Sample;

public class Main {
    public static void main(String[] args) {
        String s = "123456";
        BigString bs = new BigString(s);
        bs.print();
    }
}
