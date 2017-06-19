package wangxl;

public class Test {

	public static void main(String[] args) {
		//String[] p_friend = {"B","H"} ;
		String[] p_friend = {"B","D","I","F"} ;
		for(int i=0;i<p_friend.length-1 ; i++ ){ //B,H
			for(int j=i+1;j<p_friend.length ; j++ ){ //B,H
				if( p_friend[i].compareTo( p_friend[j] ) < 0 ){
					System.out.println( p_friend[i]+" "+p_friend[j] + "  f2" );
				} else if( p_friend[i].compareTo( p_friend[j] ) > 0 ){
					System.out.println(p_friend[j] +" "+ p_friend[i] + "  f2" );
				}
			}
		}
	}

}
