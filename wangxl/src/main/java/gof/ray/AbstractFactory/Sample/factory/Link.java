package gof.ray.AbstractFactory.Sample.factory;

/**
 * 链接
 */
public abstract class Link extends Item {
    protected String url;

    public Link(String caption, String url) {
        super(caption);
        this.url = url;
    }
}
