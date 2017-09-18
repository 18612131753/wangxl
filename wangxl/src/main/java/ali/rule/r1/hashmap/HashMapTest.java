package ali.rule.r1.hashmap;

import java.util.HashMap;

public class HashMapTest {

	public static void main(String[] args) {
		//时间计算不一定准确，但是大趋势能保证
		long now = System.currentTimeMillis() ;
		
		HashMapTest.mapDef();
		
		long end = System.currentTimeMillis() ;
		System.out.println( end-now );
		
		HashMapTest.mapInitialCapacity();
		
		long eend = System.currentTimeMillis() ;
		System.out.println( eend-end );
	}
	
	//拥有自定义初始化
	public static void mapInitialCapacity(){
		int initialCapacity = (int) (30240/0.75+1); //initialCapacity =  (需要存储的元素个数  /  负载因子) + 1
		HashMap<String,String> map = new HashMap<String,String>( initialCapacity , 0.75f );
		for( int i=0;i<30240;i++){
			map.put(i+"ab", "ab"+i+""+i);
		}
	}
	
	//不存在自定义初始化
	public static void mapDef(){
		HashMap<String,String> mapN = new HashMap<String,String>( );
		for( int i=0;i<30240;i++){
			mapN.put(i+"ac", "ac"+i+""+i);
		}
	}
}
