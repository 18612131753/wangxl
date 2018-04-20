package design.ray.a1_yuanze.aa1_srp.a2;

public class Client {

    /**
     * 细分了animal的分类
     * 遵循了单一性，但是client的代码需要做出修改
     * */
    public static void main( String[] args ){
       Animal animal = new Animal();
       animal.eat( "虎");
       animal.eat( "狼");
       
       Animal2 animal2 = new Animal2();
       animal2.eat("羊");
    }

}
