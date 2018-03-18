package gof.test.prototype;

import gof.test.prototype.framework.Product;

public class Book implements Product {

    private String name ;
    private Author author ;
    
    public Book(String name, Author author) {
        super();
        this.name = name;
        this.author = author;
    }
    @Override
    public Product createClone() {
        Product p = null ;
        try {
            p = (Product)this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
        return p ;
    }

    @Override
    public Product createClone_shendu() {
        Book p = null ;
        try {
            Object au = this.author.clone_author() ;
            p = (Book)this.clone();
            p.setAuthor( (Author)au );
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
        return p ;
    }
    @Override
    public void productWork() {
        System.out.println( " 书对象地址："+this.toString()+" 书名：" +this.name );
        System.out.println( " 作者对象地址："+this.author.toString() +"  姓名： "+this.author.getName());
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

}
