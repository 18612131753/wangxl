package gof.test.abstractfactory.factory;

/**
 * 房子工厂
 * */
public abstract class FangziFactory {

    public static FangziFactory getFangziFactory(String classname){
        FangziFactory factory = null ;
        try {
            factory = (FangziFactory)Class.forName(classname).newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return factory ;
    }
    
    //获取地基
    public abstract Diji createDiji( String name ,int num);
    
    //获取楼层
    public abstract Louceng createLouceng( String name ,int num );
    
    //创建房子
    public abstract Fangzi createFangzi();
    
}
