package gof.ray.Adapter.my1;

public class VolAdapter extends ChinaVoltage implements VolAdapterInterface{

    public VolAdapter( int num ){
        super( num );
    }
    
    @Override
    public int getJapanVol() {
        return this.getNum() - 110;
    }

    @Override
    public int getEnglandVol() {
        return  this.getNum() + 20;
    }

}
