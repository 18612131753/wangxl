package gof.ray.AbstractFactory.A2.tablefactory;
import gof.ray.AbstractFactory.A2.factory.*;

public class TableLink extends Link {
    public TableLink(String caption, String url) {
        super(caption, url);
    }
    public String makeHTML() {
        return "<td><a href=\"" + url + "\">" + caption + "</a></td>\n";
    }
}
