package com.ray.suanfa;

public class Yanghuisanjiao {

    public static void main(String[] args) {
        int s = 10;
        int[][] arr = new int[s][s];
        for( int i=0;i<s;i++){
            for(int j=0;j<s;j++){
                arr[i][j]=0;
            }
        }
        arr[0][0] = 1;
        System.out.println(arr[0][0]+"  ");
        for( int i=1;i<s;i++){
            for(int j=0;j<s;j++){
                if( j==0 ) {
                    arr[i][j] =1;
                } else {
                    arr[i][j] = arr[i-1][j]+arr[i-1][j-1];
                }
                if(arr[i][j] != 0 ) System.out.print(arr[i][j]+"  ");
            }
            System.out.println();
        }
        
    }

}
