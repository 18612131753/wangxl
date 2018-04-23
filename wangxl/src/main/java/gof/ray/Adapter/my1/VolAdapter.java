package gof.ray.Adapter.my1;

/**
 * 适配器的核心类
 */
public class VolAdapter extends ChinaVoltage implements VolAdapterInterface {

    public VolAdapter() {
        super();
    }

    @Override
    public int getJapanVol() {
        return this.getNum() - 110;
    }

    @Override
    public int getEnglandVol() {
        return this.getNum() + 20;
    }

}
