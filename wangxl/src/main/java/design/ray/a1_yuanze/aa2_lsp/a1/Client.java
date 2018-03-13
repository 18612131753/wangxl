package design.ray.a1_yuanze.aa2_lsp.a1;

/**
 * 不遵循里氏替换原则，程序出错
 * */
public class Client {

    public static void main( String[] args ){
       Animal animal = new Animal();
       animal.eat( "羊");

    }
}
