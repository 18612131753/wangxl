package gof.ray.Adapter.my2;

public class Client {

    public static void main(String[] args){
        VolAdapterClass china = new VolAdapter( );
        
        System.out.println( "Japan :"+china.getJapanVol() );
        System.out.println( "England :"+china.getEnglandVol() );
    }
}
