package gof.test.iterator;

public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("Around the World in 80 Days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("java"));
        bookShelf.appendBook(new Book("c++"));
        
        Book[] books = bookShelf.getBooks() ;
        for( Book book : books ){
            System.out.println(book.getName());
        }
    }
}
