package gof.ray.Prototype.test;

public class Author implements Cloneable {

    private String name;

    public Author(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // clone是protected，所以要再写一份
    public Object clone_author() {
        Object a = null;
        try {
            a = (Author) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return a;
    }

}
