package gof.ray.AbstractFactory.mytest.factory;

/**
 * 房子工厂
 * */
public abstract class FangziFactory {

    public static FangziFactory getFangziFactory(String classname){
        FangziFactory factory = null ;
        try {
            factory = (FangziFactory)Class.forName(classname).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory ;
    }
    
    //获取地基 - 创建零件
    public abstract Diji createDiji( String name ,int num);
    
    //获取楼层- 创建零件
    public abstract Louceng createLouceng( String name ,int num );
    
    //创建房子- 创建产品
    public abstract Fangzi createFangzi();
    
}