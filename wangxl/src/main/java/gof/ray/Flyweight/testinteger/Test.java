package gof.ray.Flyweight.testinteger;

public class Test {

    public static void main(String[] args) {
        Integer a1_1 = 127;
        Integer a1_2 = 127;
        System.out.println(" 127: "+(a1_1 == a1_2));

        Integer a2_1 = 128;
        Integer a2_2 = 128;
        System.out.println(" 128: "+(a2_1 == a2_2));
        
        Integer a3_1 = -128;
        Integer a3_2 = -128;
        System.out.println("-128: "+(a3_1 == a3_2));

        Integer a4_1 = -129;
        Integer a4_2 = -129;
        System.out.println("-129: "+(a4_1 == a4_2));

    }

}
