package gof.ray.Strategy.A4;

import java.lang.Comparable;

public interface Sorter {
    @SuppressWarnings("rawtypes")
    public abstract void sort(Comparable[] data);
}
