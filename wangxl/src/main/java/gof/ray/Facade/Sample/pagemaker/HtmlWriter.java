package gof.ray.Facade.Sample.pagemaker;

import java.io.Writer;
import java.io.IOException;

public class HtmlWriter {

    private Writer writer;

    public HtmlWriter(Writer writer) { // 构造函数
        this.writer = writer;
    }

    public void title(String title) throws IOException { // 输出标题
        this.writer.write("<html>");
        this.writer.write("<head>");
        this.writer.write("<title>" + title + "</title>");
        this.writer.write("</head>");
        this.writer.write("<body>\n");
        this.writer.write("<h1>" + title + "</h1>\n");
    }

    public void paragraph(String msg) throws IOException { // 输出段落
        this.writer.write("<p>" + msg + "</p>\n");
    }

    public void mailto(String mailaddr, String username) throws IOException { // 输出邮件地址
        this.link("mailto:" + mailaddr, username);
    }

    public void link(String href, String caption) throws IOException { // 输出超链接
        this.paragraph("<a href=\"" + href + "\">" + caption + "</a>");
    }

    public void close() throws IOException { // 结束输出HTML
        this.writer.write("</body>");
        this.writer.write("</html>\n");
        this.writer.close();
    }
}
