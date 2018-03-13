package design.ray.a1_yuanze.aa1_srp.a4;

public class Client {
    /**
     * 这么修改，没有修改原有的方法，而是添加
     * 在类的层面，违背了单一职责原则，
     * 但是在方法级别上，符合了单一职责原则
     * */
    public static void main( String[] args ){
       Animal animal = new Animal();
       animal.eat( "虎" );
       animal.eat( "狼" );
       animal.eat2("羊");
    }
}
