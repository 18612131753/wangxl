package gof.ray.Mediator.Sample;

public interface Mediator {
    public abstract void createColleagues();

    //组建发生变化时
    public abstract void colleagueChanged();
}
