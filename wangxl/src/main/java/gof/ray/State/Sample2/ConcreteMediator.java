package gof.ray.State.Sample2;

import gof.ray.State.Sample.Context;
import gof.ray.State.Sample.DayState;
import gof.ray.State.Sample.NightState;


//注意，要将中介者 注入到 DayState，NightState中去
public class ConcreteMediator extends Mediator {

    @Override
    public void changeState(Context context, int hour) {
        if (9 <= hour && hour < 17) {
            context.changeState(DayState.getInstance());
        } else if (hour < 9 || 17 <= hour) {
            context.changeState(NightState.getInstance());
        }
    }

}
