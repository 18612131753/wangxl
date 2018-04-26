package gof.ray.Builder.strtest;

public class Test {

    public static void main(String[] args) {
        //String
        String str="abc";
        System.out.println(str);
        
        str=str+"de";  //String是常量，其实JVM又创建了一个新的对象也名为str，然后再把原来的str的值和“de”加起来再赋值给新的str，而原来的str就会被JVM的垃圾回收机制（GC）给回收掉了
        System.out.println(str);
        
        //StringBuffer
        StringBuffer stringBuffer=new StringBuffer().append("abc").append("de");
        System.out.println(stringBuffer.toString());
        
        //StringBuffer
        StringBuilder stringBuilder=new StringBuilder().append("abc").append("de");
        System.out.println(stringBuilder.toString());
    }

}
