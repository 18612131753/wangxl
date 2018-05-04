package gof.ray.Facade.Sample;

import gof.ray.Facade.Sample.pagemaker.PageMaker;

public class Main {
    public static void main(String[] args) {
        
        //文件写入的路径
        String filename = 
                "D:\\work\\gitspace\\wangxl\\wangxl\\src\\main\\java\\gof\\ray\\Facade\\Sample\\welcome.html";
        PageMaker.makeWelcomePage("ray@163.com", filename);
        
    }
}
