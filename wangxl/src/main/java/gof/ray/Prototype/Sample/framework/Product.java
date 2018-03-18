package gof.ray.Prototype.Sample.framework;

import java.lang.Cloneable;

public interface Product extends Cloneable {
    public abstract void use(String s);

    public abstract Product createClone();
}
