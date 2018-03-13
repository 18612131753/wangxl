package design.ray.a1_yuanze.aa2_lsp.a2;

/**
 * 不遵循里氏替换原则，程序出错
 * */
public class Client {

    public static void main( String[] args ){

       // 任何基类可以出现的地方，子类一定可以出现
       Sheep sheep = new Sheep();
       sheep.eat( "羊");
    }
}
