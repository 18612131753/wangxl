package gof.ray.Builder.Sample;

public class Main {
    public static void main(String[] args) {

        // 编写纯文本文档
        TextBuilder textbuilder = new TextBuilder();
        Director director1 = new Director(textbuilder);
        director1.construct();
        String result = textbuilder.getResult();
        System.out.println(result);
        
        // 编写HTML文档
        HTMLBuilder htmlbuilder = new HTMLBuilder();
        Director director2 = new Director(htmlbuilder);
        director2.construct();
        String filename = htmlbuilder.getResult();
        System.out.println(filename + "文件编写完成。");

    }

    public static void usage() {
        System.out.println("Usage: java Main plain      编写纯文本文档");
        System.out.println("Usage: java Main html       编写HTML文档");
    }
}
