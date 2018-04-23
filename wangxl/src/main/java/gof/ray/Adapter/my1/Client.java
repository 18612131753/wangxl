package gof.ray.Adapter.my1;

public class Client {

    public static void main(String[] args){
        
        VolAdapterInterface china = new VolAdapter();
        
        System.out.println( "Japan :"+china.getJapanVol() );
        System.out.println( "England :"+china.getEnglandVol() );
    }
}
