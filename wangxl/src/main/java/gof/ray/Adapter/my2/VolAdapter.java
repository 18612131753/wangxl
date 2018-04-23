package gof.ray.Adapter.my2;

/**
 * 适配器核心类（委托）
 * */
public class VolAdapter extends VolAdapterClass {

    private ChinaVoltage cv;

    public VolAdapter() {
        this.cv = new ChinaVoltage();
    }

    @Override
    public int getJapanVol() {
        return this.cv.getNum() - 110;
    }

    @Override
    public int getEnglandVol() {
        return this.cv.getNum() + 20;
    }

}
