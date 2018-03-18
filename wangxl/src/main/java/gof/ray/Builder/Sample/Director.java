package gof.ray.Builder.Sample;

public class Director {
    private Builder builder;

    public Director(Builder builder) { // 因为接收的参数是Builder类的子类
        this.builder = builder; // 所以可以将其保存在builder字段中
    }

    public void construct() { // 编写文档
        builder.makeTitle("Greeting"); // 标题
        builder.makeString("从早上至下午"); // 字符串
        builder.makeItems(new String[] { "早上好。", "下午好。", }); // 条目
                
        builder.makeString("晚上"); // 其他字符串
        builder.makeItems(new String[] {"晚上好。", "晚安。", "再见。", }); // 其他条目
                
        builder.close(); // 完成文档
    }
}
