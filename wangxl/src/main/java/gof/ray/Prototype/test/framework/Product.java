package gof.ray.Prototype.test.framework;

public interface Product extends Cloneable {

    public Product createClone();
    
    public Product createClone_shendu();
    
    public void productWork();
}
