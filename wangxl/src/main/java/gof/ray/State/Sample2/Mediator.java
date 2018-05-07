package gof.ray.State.Sample2;

import gof.ray.State.Sample.Context;

//终结者模式
public abstract class Mediator {
    
    public abstract void changeState(Context context, int hour);
    
}
