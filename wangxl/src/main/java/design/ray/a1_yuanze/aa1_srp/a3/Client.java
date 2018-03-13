package design.ray.a1_yuanze.aa1_srp.a3;

public class Client {
    /**
     * 这么定义animal违背了类的单一性的原则
     * 虽然开销是最小的，但是隐患是最大的，如果还有其他的动物怎么办
     * */
    public static void main( String[] args ){
       Animal animal = new Animal();
       animal.eat( "虎");
       animal.eat( "狼");
       animal.eat( "羊");
    }
}
