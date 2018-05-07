package gof.ray.State.test;

public interface Status {

    public Value getStatusValue(Switcher switcher);

    public void action(Switcher switcher);
   }
