package gof.ray.Adapter.my2;

public class VolAdapter extends VolAdapterClass{

    private ChinaVoltage cv ;
    
    public VolAdapter( int num ){
       this.cv = new ChinaVoltage( num );
    }
    
    @Override
    public int getJapanVol() {
        return this.cv.getNum() - 110;
    }

    @Override
    public int getEnglandVol() {
        return  this.cv.getNum() + 20;
    }

}
