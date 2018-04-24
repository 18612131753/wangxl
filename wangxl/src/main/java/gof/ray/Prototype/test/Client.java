package gof.ray.Prototype.test;

public class Client {

    public static void main(String[] args) {
        Author author = new Author("结城浩");
        Book book = new Book( "图解设计模式",author);
        book.productWork() ;
        
        Book book_clo = (Book)book.createClone() ;
        book_clo.productWork() ;
        
        Book book_clo_shen = (Book)book.createClone_shendu();
        book_clo_shen.productWork() ;
    }

}
