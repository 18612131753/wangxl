package gof.ray.AbstractFactory.Sample.factory;

/**
 * 项目，统一处理link和tray
 * */
public abstract class Item {
    
    protected String caption;

    public Item(String caption) {
        this.caption = caption;
    }

    public abstract String makeHTML();
}
